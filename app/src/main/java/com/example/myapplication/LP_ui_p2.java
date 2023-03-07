package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class LP_ui_p2 extends AppCompatActivity {

    private ImageButton ImageButton;
    private EditText lp_exit_id, lp_longtitude_id, lp_latitude_id;
    private Button lp_input_btn2;
    private Button lp_finish_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lp_ui_p2);

        lp_input_btn2 = findViewById(R.id.lp_input_btn2);
        lp_finish_btn = findViewById(R.id.lp_finish_btn);
        lp_exit_id = findViewById(R.id.lp_exit_id);
        lp_longtitude_id = findViewById(R.id.lp_longtitude_id);
        lp_latitude_id = findViewById(R.id.lp_latitude_id);

        Intent intent = new Intent();
        intent = this.getIntent();
        String LID = intent.getStringExtra("String");

//確認連線
        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                MySqlCon con = new MySqlCon();
                con.run();
                final String data = con.getData();
                Log.v("OK", data);
            }
        }).start();
        */

//返回鍵
        ImageButton = (ImageButton) findViewById(R.id.back_btn);
        ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LP_ui_p2.this, LP_ui_p1.class);
                startActivity(intent);
            }
        });

        lp_input_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(LP_ui_p2.this, Contract.class);
                startActivity(intent);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 取得 EditText 資料
                        final EditText lp_exit_id = (EditText) findViewById(R.id.lp_exit_id);
                        final EditText lp_longtitude_id = (EditText) findViewById(R.id.lp_longtitude_id);
                        final EditText lp_latitude_id = (EditText) findViewById(R.id.lp_latitude_id);

                        String G_ID = "";
                        String L_ID = "";
                        String G_NAME = "";
                        String Longtitude = "";
                        String Latitude = "";

                        // 清空 EditText
                        lp_exit_id.post(new Runnable() {
                            public void run() {
                                lp_exit_id.setText("");
                            }
                        });
                        lp_longtitude_id.post(new Runnable() {
                            public void run() {
                                lp_longtitude_id.setText("");
                            }
                        });
                        lp_latitude_id.post(new Runnable() {
                            public void run() {
                                lp_latitude_id.setText("");
                            }
                        });

                        // 將資料寫入資料庫
                        MySqlCon con = new MySqlCon();
                        L_ID = con.getlid(L_ID);
                        G_NAME = lp_exit_id.getText().toString();
                        Longtitude = lp_longtitude_id.getText().toString();
                        Latitude = lp_latitude_id.getText().toString();

                        G_ID = con.addG_ID(con.Column3());
                        con.insertData3(G_ID, L_ID, G_NAME, Longtitude, Latitude);
                        Looper.prepare();

                    }
                });

                lp_finish_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(LP_ui_p2.this, Contract2.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}