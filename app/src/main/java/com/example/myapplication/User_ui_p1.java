package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class User_ui_p1 extends AppCompatActivity {

    private ImageButton ImageButton;
    Spinner people_classify;
    EditText u_health_id, u_phone_id, u_check_id;
    Button u_input_btn, u_check_btn;
    StringBuffer sb = new StringBuffer();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_ui_p1);

        u_input_btn = findViewById(R.id.u_input_btn);
        u_check_btn = findViewById(R.id.u_check_btn);
        u_health_id = findViewById(R.id.u_health_id);
        u_phone_id = findViewById(R.id.u_phone_id);
        u_input_btn.setEnabled(true);
        u_check_id = findViewById(R.id.u_check_id);
        u_input_btn.setVisibility(View.GONE);

//確認連線
        new Thread(new Runnable() {
            @Override
            public void run() {
                MySqlCon con = new MySqlCon();
                con.run();
                final String data = con.getData();
                Log.v("OK", data);

            }
        }).start();

//下拉選單
        people_classify = (Spinner) findViewById(R.id.people_classify);
        people_classify.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("本國居民")) {
                    return; //do nothing
                } else if (parent.getItemAtPosition(position).equals("外籍人士")) {
                    Intent intent = new Intent(User_ui_p1.this, User_ui_p2.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(User_ui_p1.this, User_ui_p3.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//返回鍵
        ImageButton = (ImageButton) findViewById(R.id.back_btn);
        ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_ui_p1.this, Home.class);
                startActivity(intent);
            }
        });

//生成驗證碼
        u_check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                Set<Integer> set = new HashSet<Integer>();
                while (set.size() < 2)
                {
                    int randomInt = random.nextInt(10);
                    set.add(randomInt);
                }

                for (Integer i : set)
                {
                    sb.append(""+i+(char) (Math.random() * 26 + 'a'));
                }
                Toast.makeText(getBaseContext(),"驗證碼為:"+sb.toString(),Toast.LENGTH_SHORT).show();

                if(u_check_btn.isClickable())
                {
                    u_input_btn.setEnabled(true);
                    u_input_btn.setVisibility(View.VISIBLE);
                }
                else
                {
                    u_input_btn.setEnabled(false);
                }
            }
        });

//確認驗證碼、換頁、傳資料庫
        u_input_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(u_check_id.getText().toString().equals(sb.toString()))
                {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            // 取得 EditText 資料
                            final EditText u_health_id = (EditText) findViewById(R.id.u_health_id);
                            final EditText u_phone_id = (EditText) findViewById(R.id.u_phone_id);
                            String P_ID ="";
                            String P_HID ="";
                            String P_PHONE ="";
                            String P_PID ="NULL";

                            // 清空 EditText
                            u_health_id.post(new Runnable() {
                                public void run() {
                                    u_health_id.setText("");
                                }
                            });
                            u_phone_id.post(new Runnable() {
                                public void run() {
                                    u_phone_id.setText("");
                                }
                            });

                            // 將資料寫入資料庫
                            P_HID = u_health_id.getText().toString();
                            P_PHONE = u_phone_id.getText().toString();
                            MySqlCon con = new MySqlCon();
                            P_ID = con.addP_ID(con.Column());
                            con.insertData(P_ID, P_HID, P_PHONE, P_PID);

                            // 讀取更新後的資料
                            String updata = con.getData();
                            Log.v("OK", updata);
                            Log.v("OK", String.valueOf(con.Column()));
                            Looper.prepare();
                            if(con.Check(P_HID) == false){
                                Log.v("OK", "回傳失敗");
                                Intent intent=new Intent();
                                intent.setClass(User_ui_p1.this,User_ui_p1.class);
                                startActivity(intent);
                                Toast.makeText(User_ui_p1.this, "比對失敗", Toast.LENGTH_LONG).show();
                            }else{
                                Log.v("OK", "回傳成功");
                                Toast.makeText(User_ui_p1.this, "比對成功", Toast.LENGTH_LONG).show();
                                String PID= con.getpid(P_HID) ;
                                Intent intent2=new Intent();
                                intent2.setClass(User_ui_p1.this,Contract.class);
                                intent2.putExtra("String",PID);
                                startActivity(intent2);
                            }

                        }
                    }).start();
                }
                else
                {
                    Toast.makeText(User_ui_p1.this, "驗證碼錯誤", Toast.LENGTH_LONG).show();
                }
            }
        });




    }



}