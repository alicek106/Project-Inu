package com.example.alicek.globalhealthcare;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;

/**
 * Created by alicek on 2015-11-14.
 */
public class InfoFragment extends Fragment implements BeaconConsumer {

    static private BluetoothService btService = null;
    private BeaconManager beaconManager;
    private boolean found;
    private int major;
    static boolean isWorking = false;
    private final Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }

    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {

        if(btService == null) {
            btService = new BluetoothService(getActivity(), mHandler);
        }

        if(btService.isBluetoothEnable()){
            startRangeBeacon();
            return inflater.inflate(R.layout.fragment_info, container, false);
        }

        else{
            /*
            btService.enableBluetooth();
            startRangeBeacon();*/
            //isWworking = false;
            return inflater.inflate(R.layout.fragment_info_blutooth_disable, container, false);
        }
    }

    public void startRangeBeacon(){
        beaconManager = BeaconManager.getInstanceForApplication(getApplicationContext());

        // 여기가 중요한데, 기기에 따라서 setBeaconLayout 안의 내용을 바꿔줘야 하는듯 싶다.
        // 필자의 경우에는 아래처럼 하니 잘 동작했음.
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));

        // 비콘 탐지를 시작한다. 실제로는 서비스를 시작하는것.
        //beaconManager.bind(this);
        beaconManager.bind(this);
        handler.sendEmptyMessage(0);
        found = false;
        isWorking = true;
    }

    public void bind(){
        beaconManager.bind(this);
    }

    public void unbind(){
        System.out.println("unbind됨. 상태 : " + isWorking);
        if(isWorking) {
            beaconManager.unbind(this);
            isWorking = false;
            System.out.println("unbind 성공");
        }
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            // 비콘이 감지되면 해당 함수가 호출된다. Collection<Beacon> beacons에는 감지된 비콘의 리스트가,
            // region에는 비콘들에 대응하는 Region 객체가 들어온다.
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                for (Beacon beacon : beacons) {

                    if(Double.parseDouble(String.format("%.3f", beacon.getDistance())) < 0.02 ){
                        major = beacon.getId2().toInt();
                        found = true;
                    }
                }
            }
        });

        try {
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {   }
    }

    @Override
    public Context getApplicationContext() {
        return getActivity().getApplicationContext();
    }

    @Override
    public void unbindService(ServiceConnection serviceConnection) {
        getActivity().unbindService(serviceConnection);
    }

    @Override
    public boolean bindService(Intent intent, ServiceConnection serviceConnection, int i) {
        return getActivity().bindService(intent, serviceConnection, i);
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if(found){
                unbind();
                System.out.println(major);
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();     //닫기
                    }
                });
                alert.setMessage("비콘 ID : " + major + "\n\n테스트");
                alert.show();
            }
            else {
                // 자기 자신을 1초마다 호출
                handler.sendEmptyMessageDelayed(0, 1000);
            }
        }
    };

    static public void OnBluetoothEnable(View view){
        btService.enableBluetooth();
    }

    static public boolean isBluetoothEnabled(){
        return btService.isBluetoothEnable();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbind();
    }
}
