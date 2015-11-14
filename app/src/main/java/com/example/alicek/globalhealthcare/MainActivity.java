package com.example.alicek.globalhealthcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText loginInput;
    boolean autoLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginInput = (EditText)findViewById(R.id.loginInput);

        findViewById(R.id.autoLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoLogin = !autoLogin;
                if(autoLogin == true) {
                    Toast.makeText(getApplicationContext(), "자동 로그인이 설정되었습니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "자동 로그인이 해제되었습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        SharedPreferences pref = getSharedPreferences("PrefName", MODE_PRIVATE);
        String phoneNum = pref.getString("phoneNum", "");
        if(!phoneNum.equals("")){
            finish();
            Intent intent = new Intent(this, FrontActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    public void OnJoinClicked(View view){
        Intent intent = new Intent(this, JoinActivity.class);
        startActivity(intent);
    }

    public void OnLoginButton(View view){
        String url = "http://163.180.117.247:8080/AndroidTest/login.jsp";
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("phoneNum", loginInput.getText().toString());
        URLConnector urlConnector = new URLConnector(url, hashMap);

        urlConnector.start();

        try {
            urlConnector.join();
        }
        catch(InterruptedException e){

        }

        String result = urlConnector.getResult();

        if(result.equals("f")){
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
        }

        else{
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();

            String[] record = result.split(" ");

            User.setUser(loginInput.getText().toString(), record[0], record[1], record[2]);

            if(autoLogin == true){
                SharedPreferences pref = getSharedPreferences("PrefName", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                editor.putString("phoneNum",loginInput.getText().toString());
                editor.commit();
            }

            else{
                SharedPreferences pref = getSharedPreferences("PrefName", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                editor.putString("phoneNum","");
                editor.commit();
            }

            finish();
            Intent intent = new Intent(this, FrontActivity.class);
            startActivity(intent);
        }
    }
}
