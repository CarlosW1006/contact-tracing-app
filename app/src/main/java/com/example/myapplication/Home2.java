package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home2 extends AppCompatActivity {

    private Button to_place_btn,qr_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        Intent intent = this.getIntent();
//取得傳遞過來的資料
        String PID = intent.getStringExtra("String");

        to_place_btn = findViewById(R.id.to_place_btn);
        qr_btn = findViewById(R.id.qr_btn);

        qr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent();
                intent2.setClass(Home2.this,QR_Code.class);
                intent2.putExtra("String",PID);
                startActivity(intent2);
            }
        });

        to_place_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(Home2.this,Place_ui_p1.class);
                startActivity(intent);
            }
        });

    }
}