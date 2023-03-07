package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    private Button to_user_btn,qr_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        to_user_btn = findViewById(R.id.to_user_btn);
        qr_btn = findViewById(R.id.qr_btn);
        qr_btn.setEnabled(false);

        to_user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,User_ui_p1.class);
                startActivity(intent);
            }
        });
    }
}