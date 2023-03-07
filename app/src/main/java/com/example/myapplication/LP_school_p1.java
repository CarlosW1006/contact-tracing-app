package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LP_school_p1 extends AppCompatActivity {

    ImageButton ImageButton;
    EditText sch_id,sch_check_id;
    Button sch_input_btn,sch_check_btn;
    private RadioGroup sch_radio_group;
    StringBuffer sb=new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lp_school_p1);

        sch_input_btn = findViewById(R.id.sch_input_btn);
        sch_check_btn = findViewById(R.id.sch_check_btn);
        sch_check_id = findViewById(R.id.sch_check_id);
        sch_id = findViewById(R.id.sch_id);
        sch_radio_group = findViewById(R.id.sch_radio_group);
        sch_input_btn.setVisibility(View.GONE);

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

//返回鍵
        ImageButton = (ImageButton) findViewById(R.id.back_btn);
        ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LP_school_p1.this, LP_ui_p1.class);
                startActivity(intent);
            }
        });

//生成驗證碼
        sch_check_btn.setOnClickListener(new View.OnClickListener() {
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

                if(sch_check_btn.isClickable())
                {
                    /*設定Button為不能選擇物件*/
                    sch_input_btn.setEnabled(true);
                    sch_input_btn.setVisibility(View.VISIBLE);
                }
                else
                {
                    sch_input_btn.setEnabled(false);
                }
            }
        });

//確認驗證碼、換頁、傳資料庫
        sch_input_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = sch_radio_group.getCheckedRadioButtonId();
                RadioButton select = findViewById(id);

                if(sch_check_id.getText().toString().equals(sb.toString()))
                {
                    Intent intent=new Intent();
                    intent.setClass(LP_school_p1.this,LP_ui_p2.class);
                    startActivity(intent);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // 取得 EditText 資料
                            final EditText tax_id = (EditText) findViewById(R.id.tax_id);

                            String L_ID ="";
                            String L_NAME="NULL";
                            //String P_ID="";
                            //String P_SID="NULL";
                            //String P_PHONE ="";
                            //String OPENING="NULL";
                            //String L_ADDRESS="NULL";
                            //String LONGTITUDE="NULL";
                            //String LATITUDE="NULL";
                            //String L_GOVERN="NULL";
                            //String L_AIRPORT="NULL";
                            String L_KIND ="";
                            //String L_COMPANY ="";
                            String L_SCHOOL="NULL";
                            //String P_PHONE="NULL";
                            //String L_PUBLIC="NULL";
                            //String L_CKIND="NULL";
                            //String L_BUSINESS="NULL";
                            String L_LICENSE="NULL";
                            //String L_A_ID="NULL";
                            //String L_ROBOT="NULL";
                            /*
                            String P_ID ="";
                            String P_HID ="";
                            String P_PHONE ="";
                            String P_PID ="NULL";
                            */
                            // 清空 EditText
                            sch_id.post(new Runnable() {
                                public void run() {
                                    sch_id.setText("");
                                }
                            });

                            // 將資料寫入資料庫
                            L_SCHOOL = sch_id.getText().toString();
                            L_KIND = select.getText().toString();
                            MySqlCon con = new MySqlCon();
                            L_ID = con.addL_ID(con.Column2());
                            con.insertData2(L_ID, L_NAME,  L_KIND, L_SCHOOL, L_LICENSE);
                            // 讀取更新後的資料
                            final String updata = con.getData2();
                            Log.v("OK", updata);
                            Log.v("OK", String.valueOf(con.Column2()));

                            con.Check3(L_SCHOOL);

                        }
                    }).start();
                }
                else
                {
                    Toast.makeText(LP_school_p1.this, "驗證碼錯誤", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
