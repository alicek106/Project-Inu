package com.example.alicek.globalhealthcare;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by alicek on 2015-11-14.
 */
public class FrontActivity extends AppCompatActivity {

    private final int ALARM_ACTIVITY = 100;
    private final int FIND_ACTIVITY = 100;
    private final int INFO_ACTIVITY = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);

    }

    public void OnLogoutClicked(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Logout")        // 제목 설정
                .setMessage("로그아웃됩니다.")        // 메세지 설정
                .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                .setPositiveButton("확인", new DialogInterface.OnClickListener(){
                    // 확인 버튼 클릭시 설정
                    public void onClick(DialogInterface dialog, int whichButton){

                        SharedPreferences pref = getSharedPreferences("PrefName", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();

                        editor.putString("phoneNum","");
                        editor.commit();

                        finish();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    // 취소 버튼 클릭시 설정
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기
    }

    public void OnAlarmClicked(View view){
        Toast.makeText(getApplicationContext(), "Alarm", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), IndexActivity.class);
        startActivityForResult(intent, ALARM_ACTIVITY);

    }

    public void OnFindClicked(View view){
        Toast.makeText(getApplicationContext(), "Find", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), IndexActivity.class);
        startActivityForResult(intent, FIND_ACTIVITY);
    }

    public void OnInfoClicked(View view){
        Toast.makeText(getApplicationContext(), "Info", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), IndexActivity.class);
        startActivityForResult(intent, INFO_ACTIVITY);
    }

}
