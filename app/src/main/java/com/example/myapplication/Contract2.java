package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

public class Contract2 extends AppCompatActivity {

    public CheckBox checkBox;
    public Button button;
    ImageButton ImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contract);

        checkBox=(CheckBox) findViewById(R.id.checkBox);
        button = (Button) findViewById(R.id.button);
        checkBox.setChecked(false);
        button.setEnabled(false);
        checkBox.setOnClickListener(new CheckBox.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
// TODO Auto-generated method stub
                if(checkBox.isChecked())
                {
                    /*設定Button為不能選擇物件*/
                    button.setEnabled(true);
                }
                else
                {
                    button.setEnabled(false);
                }
            }
        });
        button.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Contract2.this,Home2.class);
                startActivity(intent);
            }
        });

        ImageButton=(ImageButton) findViewById(R.id.back_btn);
        ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Contract2.this,Place_ui_p1.class);
                startActivity(intent);
            }
        });
    }
}