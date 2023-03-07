package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class PT_ui_p1 extends AppCompatActivity {

    private ImageButton ImageButton;
    EditText license_id,pt_check_id;
    Button pt_input_btn,pt_check_btn;
    StringBuffer sb=new StringBuffer();
    RadioButton pt_rbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pt_ui_p1);

        pt_input_btn = findViewById(R.id.pt_input_btn);
        pt_check_btn = findViewById(R.id.pt_check_btn);
        license_id = findViewById(R.id.license_id);
        pt_check_id = findViewById(R.id.pt_check_id);
        pt_input_btn.setVisibility(View.GONE);


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
                Intent intent = new Intent(PT_ui_p1.this, Place_ui_p1.class);
                startActivity(intent);
            }
        });

//生成驗證碼
        pt_check_btn.setOnClickListener(new View.OnClickListener() {
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

                if(pt_check_btn.isClickable())
                {
                    /*設定Button為不能選擇物件*/
                    pt_input_btn.setEnabled(true);
                    pt_input_btn.setVisibility(View.VISIBLE);
                }
                else
                {
                    pt_input_btn.setEnabled(false);
                }
            }
        });

//確認驗證碼、換頁
        pt_input_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RadioButton pt_rbtn = findViewById(R.id.pt_rbtn);

                if(pt_check_id.getText().toString().equals(sb.toString()))
                {
                    Intent intent=new Intent();
                    intent.setClass(PT_ui_p1.this,Contract.class);
                    startActivity(intent);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // 取得 EditText 資料
                            final EditText license_id = (EditText) findViewById(R.id.license_id);

                            String L_ID ="";
                            String L_NAME="NULL";
                            String L_KIND ="";
                            String L_COMPANY ="NULL";
                            String L_LICENSE="";

                            // 清空 EditText
                            license_id.post(new Runnable() {
                                public void run() {
                                    license_id.setText("");
                                }
                            });

                            // 將資料寫入資料庫
                            L_LICENSE = license_id.getText().toString();
                            L_KIND = pt_rbtn.getText().toString();
                            MySqlCon con = new MySqlCon();
                            L_ID = con.addL_ID(con.Column2());
                            con.insertData2(L_ID, L_NAME,  L_KIND, L_COMPANY, L_LICENSE);
                            con.Check5(L_LICENSE);

                        }
                    }).start();
                }
                else
                {
                    Toast.makeText(PT_ui_p1.this, "驗證碼錯誤", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
