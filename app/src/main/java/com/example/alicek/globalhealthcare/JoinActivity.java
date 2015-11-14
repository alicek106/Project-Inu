package com.example.alicek.globalhealthcare;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class JoinActivity extends AppCompatActivity {

    EditText phoneEdit;
    EditText nameEdit;
    EditText belongEdit;
    EditText empNumEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        phoneEdit = (EditText)findViewById(R.id.phoneEdit);
        nameEdit = (EditText)findViewById(R.id.nameEdit);
        belongEdit = (EditText)findViewById(R.id.belongEdit);
        empNumEdit = (EditText)findViewById(R.id.empNumEdit);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void OnJoinCompleleteButton(View view){
        HashMap<String, String> params = new HashMap<String, String>();

        params.put("phoneNum", phoneEdit.getText().toString());
        params.put("name", nameEdit.getText().toString());
        params.put("belong", belongEdit.getText().toString());
        params.put("empNum", empNumEdit.getText().toString());

        URLConnector urlConnector = new URLConnector("http://163.180.117.247:8080/AndroidTest/Join.jsp", params);

        urlConnector.start();
        try {
            urlConnector.join();
        }

        catch(InterruptedException e){

        }
        String result = urlConnector.getResult();

        System.out.println(result);
        if(result.equals("f")){
            Toast.makeText(this, "생성 실패.", Toast.LENGTH_SHORT).show();
        }

        else{
            Toast.makeText(this, "생성 성공.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
