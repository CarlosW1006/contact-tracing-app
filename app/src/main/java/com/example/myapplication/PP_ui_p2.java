package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class PP_ui_p2 extends AppCompatActivity {

    private ImageButton ImageButton;
    private EditText pp_exit_id,pp_longtitude_id,pp_latitude_id;
    private Button pp_branch_btn;
    private Button pp_finish_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pp_ui_p2);

        pp_branch_btn = findViewById(R.id.pp_branch_btn);
        pp_finish_btn = findViewById(R.id.pp_finish_btn);
        pp_exit_id = findViewById(R.id.pp_exit_id);
        pp_longtitude_id = findViewById(R.id.pp_longtitude_id);
        pp_latitude_id = findViewById(R.id.pp_latitude_id);

        ImageButton = (ImageButton) findViewById(R.id.back_btn);
        ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PP_ui_p2.this, PP_ui_p1.class);
                startActivity(intent);
            }
        });

        pp_branch_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        pp_finish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(PP_ui_p2.this,Contract2.class);
                startActivity(intent);
                }
            });
    }
}