package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Place_ui_p1 extends AppCompatActivity {

    private ImageButton ImageButton;
    private Button LP_Button;
    private Button PP_Button;
    private Button PT_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_ui_p1);

        ImageButton = (ImageButton) findViewById(R.id.back_btn);
        ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Place_ui_p1.this, Home2.class);
                startActivity(intent);
            }
        });

        LP_Button=(Button) findViewById(R.id.lp_btn);
        LP_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Place_ui_p1.this,LP_ui_p1.class);
                startActivity(intent);
            }
        });

        PP_Button=(Button) findViewById(R.id.pp_btn);
        PP_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Place_ui_p1.this,PP_ui_p1.class);
                startActivity(intent);
            }
        });

        PT_Button=(Button) findViewById(R.id.pt_btn);
        PT_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Place_ui_p1.this,PT_ui_p1.class);
                startActivity(intent);
            }
        });
    }
}
