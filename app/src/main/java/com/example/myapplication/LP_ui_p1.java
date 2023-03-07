package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
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

public class LP_ui_p1 extends AppCompatActivity {


    ImageButton ImageButton;
    EditText tax_id,lp_check_id;
    Button lp_input_btn,lp_check_btn;
    private RadioGroup radio_group_lp;
    Spinner lp_classify;
    StringBuffer sb=new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lp_ui_p1);

        lp_input_btn = findViewById(R.id.lp_input_btn);
        lp_check_btn = findViewById(R.id.lp_check_btn);
        lp_check_id = findViewById(R.id.lp_check_id);
        tax_id = findViewById(R.id.tax_id);
        radio_group_lp = findViewById(R.id.radio_group_lp);
        lp_input_btn.setVisibility(View.GONE);

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

        //下拉選單
        lp_classify = (Spinner) findViewById(R.id.lp_classify);
        lp_classify.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("法人場所")) {
                    return; //do nothing
                } else if (parent.getItemAtPosition(position).equals("政府機關")) {
                    Intent intent = new Intent(LP_ui_p1.this, LP_govern_p1.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(LP_ui_p1.this, LP_school_p1.class);
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
                Intent intent = new Intent(LP_ui_p1.this, Place_ui_p1.class);
                startActivity(intent);
            }
        });

//生成驗證碼
        lp_check_btn.setOnClickListener(new View.OnClickListener() {
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

                if(lp_check_btn.isClickable())
                {
                    /*設定Button為不能選擇物件*/
                    lp_input_btn.setEnabled(true);
                    lp_input_btn.setVisibility(View.VISIBLE);
                }
                else
                {
                    lp_input_btn.setEnabled(false);
                }
            }
        });

//確認驗證碼、換頁、傳資料庫
        lp_input_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = radio_group_lp.getCheckedRadioButtonId();
                RadioButton select = findViewById(id);

                if(lp_check_id.getText().toString().equals(sb.toString()))
                {
                    Intent intent=new Intent();
                    intent.setClass(LP_ui_p1.this,LP_ui_p2.class);
                    startActivity(intent);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // 取得 EditText 資料
                            final EditText tax_id = (EditText) findViewById(R.id.tax_id);

                            String L_ID ="";
                            String L_NAME="NULL";
                            String L_KIND ="";
                            String L_COMPANY ="";
                            String L_LICENSE="NULL";

                            // 清空 EditText
                            tax_id.post(new Runnable() {
                                public void run() {
                                    tax_id.setText("");
                                }
                            });

                            // 將資料寫入資料庫
                            L_COMPANY = tax_id.getText().toString();
                            L_KIND = select.getText().toString();
                            MySqlCon con = new MySqlCon();
                            L_ID = con.addL_ID(con.Column2());
                            con.insertData2(L_ID, L_NAME,  L_KIND, L_COMPANY, L_LICENSE);
                            Looper.prepare();
                            if(con.Check3(L_COMPANY) == false){
                                Log.v("OK", "回傳失敗");
                                Intent intent=new Intent();
                                intent.setClass(LP_ui_p1.this,LP_ui_p1.class);
                                startActivity(intent);
                                Toast.makeText(LP_ui_p1.this, "比對失敗", Toast.LENGTH_LONG).show();
                            }else{
                                Log.v("OK", "回傳成功");
                                Toast.makeText(LP_ui_p1.this, "比對成功", Toast.LENGTH_LONG).show();
                                String LID= con.getlid(L_COMPANY) ;
                                Intent intent2=new Intent();
                                intent2.setClass(LP_ui_p1.this,LP_ui_p2.class);
                                intent2.putExtra("String",LID);
                                startActivity(intent2);
                            }

                        }
                    }).start();
                }
                else
                {
                    Toast.makeText(LP_ui_p1.this, "驗證碼錯誤", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
