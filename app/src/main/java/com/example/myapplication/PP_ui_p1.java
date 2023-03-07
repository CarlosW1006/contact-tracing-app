package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class PP_ui_p1 extends AppCompatActivity {

    private ImageButton ImageButton;
    EditText place_name,pp_check_id;
    Button pp_input_btn,pp_check_btn;
    RadioGroup radio_group_pp;
    StringBuffer sb=new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pp_ui_p1);

        pp_input_btn = findViewById(R.id.pp_input_btn);
        pp_check_btn = findViewById(R.id.pp_check_btn);
        place_name = findViewById(R.id.place_name);
        pp_check_id = findViewById(R.id.pp_check_id);
        radio_group_pp = findViewById(R.id.radio_group_pp);
        pp_input_btn.setVisibility(View.GONE);

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
                Intent intent = new Intent(PP_ui_p1.this, Place_ui_p1.class);
                startActivity(intent);
            }
        });

//生成驗證碼
        pp_check_btn.setOnClickListener(new View.OnClickListener() {
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

                if(pp_check_btn.isClickable())
                {
                    /*設定Button為不能選擇物件*/
                    pp_input_btn.setEnabled(true);
                    pp_input_btn.setVisibility(View.VISIBLE);
                }
                else
                {
                    pp_input_btn.setEnabled(false);
                }
            }
        });

//確認驗證碼、換頁
        pp_input_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id=radio_group_pp.getCheckedRadioButtonId();
                RadioButton select = findViewById(id);

                if(pp_check_id.getText().toString().equals(sb.toString()))
                {
                    Intent intent=new Intent();
                    intent.setClass(PP_ui_p1.this,PP_ui_p2.class);
                    startActivity(intent);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // 取得 EditText 資料
                            final EditText place_name = (EditText) findViewById(R.id.place_name);

                            String L_ID ="";
                            String L_NAME="";
                            String L_KIND ="";
                            String L_COMPANY ="NULL";
                            String L_LICENSE="NULL";

                            // 清空 EditText
                            place_name.post(new Runnable() {
                                public void run() {
                                    place_name.setText("");
                                }
                            });

                            // 將資料寫入資料庫
                            L_NAME = place_name.getText().toString();
                            L_KIND = select.getText().toString();
                            MySqlCon con = new MySqlCon();
                            L_ID = con.addL_ID(con.Column2());
                            con.insertData2(L_ID, L_NAME,  L_KIND, L_COMPANY, L_LICENSE);
                            con.Check4(L_NAME);

                        }
                    }).start();
                }
                else
                {
                    Toast.makeText(PP_ui_p1.this, "驗證碼錯誤", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
