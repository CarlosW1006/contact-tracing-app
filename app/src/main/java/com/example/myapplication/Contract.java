package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

public class Contract extends AppCompatActivity {

    public CheckBox checkBox;
    public Button button;
    ImageButton ImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contract);

        Intent intent = new Intent();
        intent=this.getIntent();
        String PID = intent.getStringExtra("String");
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
                Intent intent2=new Intent(Contract.this,Home2.class);
                intent2 .putExtra("String",PID);
                startActivity(intent2);
            }
        });

        ImageButton=(ImageButton) findViewById(R.id.back_btn);
        ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Contract.this,Home.class);
                startActivity(intent);
            }
        });
    }
}