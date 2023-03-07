package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class QR_Code extends AppCompatActivity {

    TextView longtitude, latitude;
    TextClock textclock;
    Button ok_button;
    TextView textView,P_Con;


    public static final String TAG = MainActivity.class.getSimpleName() + "My";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);

        Intent intent = this.getIntent();
//取得傳遞過來的資料
        String PID = intent.getStringExtra("String");



        textView =(TextView) findViewById(R.id.P_Con);
        textclock = (TextClock) findViewById(R.id.textclock);
        // 设置24时制显示格式
        textclock.setFormat12Hour("yyyy-MM-dd hh:mm:ss");

        longtitude = findViewById(R.id.longtitude);
        latitude = findViewById(R.id.latitude);
        ok_button = findViewById(R.id.ok_button);
        textView = findViewById(R.id.text_view);

        P_Con = findViewById(R.id.P_Con);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }
        final TextView text_view = (TextView) findViewById(R.id.text_view);
        new Thread(new Runnable() {

            @Override
            public void run() {


                MySqlCon con = new MySqlCon();
                con.run();


                String data = con.getData_qr(PID);

                Log.v("OK", data);
                final String color = con.getCon(PID);
                Log.v("OK", color);


                text_view.post(new Runnable() {
                    public void run() {

                        text_view.setText(PID);
                        P_Con.setText(color);
                        if(color.equals("健康") ){
                            P_Con.setBackgroundColor(Color.GREEN);
                        }else if(color.equals("匡列")) {P_Con.setBackgroundColor(Color.YELLOW);}else {P_Con.setBackgroundColor(Color.RED);}
                    }
                });
            }
        }).start();



        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genCode(v);
            }

            public void genCode(View view) {

                TextView input = (TextView) findViewById(R.id.text_view);
                TextView latitude = (TextView) findViewById(R.id.latitude);
                TextView longtitude = (TextView) findViewById(R.id.longtitude);
                TextClock textClock = (TextClock) findViewById(R.id.textclock);
                ImageView image_view = (ImageView) findViewById(R.id.image_view);
                String qr = input.getText() + "&" + longtitude.getText() + "&" + latitude.getText() + "&" + textClock.getText();

                BarcodeEncoder encoder = new BarcodeEncoder();
                try {
                    Bitmap bit = encoder.encodeBitmap(qr.toString(), BarcodeFormat.QR_CODE,
                            250, 250);
                    image_view.setImageBitmap(bit);
                } catch (WriterException e) {
                    e.printStackTrace();
                }

            }

            final TextView text_view = (TextView) findViewById(R.id.text_view);

        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
                                             @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        getLocal();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getLocal () {
        /**沒有權限則返回*/
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        String localProvider = "";
        /**知道位置後..*/
        Location location = manager.getLastKnownLocation(localProvider);
        if (location != null) {
            showLocation(location);
        } else {
            Log.d(TAG, "getLocal: ");
            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, mListener);
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, mListener);
        }
    }

    /**監聽位置變化*/
    LocationListener mListener = new LocationListener() {
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);
        }
    };



    private void showLocation (Location location){
        String address =""+location.getLongitude();
        String address2 =""+location.getLatitude();
        longtitude.setText(address);
        latitude.setText(address2);

    }
}