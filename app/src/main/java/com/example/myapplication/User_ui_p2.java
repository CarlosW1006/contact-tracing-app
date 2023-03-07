package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class User_ui_p2 extends AppCompatActivity {

    private ImageButton ImageButton;
    EditText passport_id,u_phone_id2,u_check_id2;
    Button u_input_btn2,u_check_btn2;
    StringBuffer sb=new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_ui_p2);

        u_check_btn2 = findViewById(R.id.u_check_btn2);
        u_input_btn2 = findViewById(R.id.u_input_btn2);
        passport_id = findViewById(R.id.passport_id);
        u_phone_id2 = findViewById(R.id.u_phone_id2);
        u_check_id2 = findViewById(R.id.u_check_id2);
        u_input_btn2.setVisibility(View.GONE);

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
                Intent intent = new Intent(User_ui_p2.this, User_ui_p1.class);
                startActivity(intent);
            }
        });

//生成驗證碼
        u_check_btn2.setOnClickListener(new View.OnClickListener() {
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

                if(u_check_btn2.isClickable())
                {
                    /*設定Button為不能選擇物件*/
                    u_input_btn2.setEnabled(true);
                    u_input_btn2.setVisibility(View.VISIBLE);
                }
                else
                {
                    u_input_btn2.setEnabled(false);
                }
            }
        });

//確認驗證碼、換頁、傳資料庫
        u_input_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(u_check_id2.getText().toString().equals(sb.toString()))
                {
                    Intent intent=new Intent();
                    intent.setClass(User_ui_p2.this,Contract.class);
                    startActivity(intent);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            // 取得 EditText 資料
                            final EditText passport_id = (EditText) findViewById(R.id.passport_id);
                            final EditText u_phone_id2 = (EditText) findViewById(R.id.u_phone_id2);
                            String P_ID ="";
                            String P_HID ="NULL";
                            String P_PHONE ="";
                            String P_PID ="";

                            // 清空 EditText
                            passport_id.post(new Runnable() {
                                public void run() {
                                    passport_id.setText("");
                                }
                            });
                            u_phone_id2.post(new Runnable() {
                                public void run() {
                                    u_phone_id2.setText("");
                                }
                            });

                            // 將資料寫入資料庫
                            P_PID = passport_id.getText().toString();
                            P_PHONE = u_phone_id2.getText().toString();
                            MySqlCon con = new MySqlCon();
                            P_ID = con.addP_ID(con.Column());
                            con.insertData(P_ID, P_HID, P_PHONE, P_PID);

                            // 讀取更新後的資料
                            //final String updata = con.getData();
                            //Log.v("OK", updata);
                            //Log.v("OK", String.valueOf(con.Column()));

                            con.Check1(P_PID);
                        }
                    }).start();
                }
                else
                {
                    Toast.makeText(User_ui_p2.this, "驗證碼錯誤", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
