package com.example.mp3a1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final String permission = "edu.uic.cs478.sp2020.project3";

    private Button att;
    private Button res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        att = (Button) findViewById(R.id.att);
        res = (Button) findViewById(R.id.res);

        //Checking permission
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_GRANTED) {
            att.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent("Attractions");
                    sendOrderedBroadcast(in,null);
                }
            });

            res.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent("Restaurants");
                    sendOrderedBroadcast(in,null);
                }
            });
        }
        else {
            //If app doesn't have permission then requesting user to accept permission
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, 1);
        }
    }

    //Response from user after asking request
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //If permission accepted
                    att.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent("Attractions");
                            sendOrderedBroadcast(in,null);
                        }
                    });

                    res.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent("Restaurants");
                            sendOrderedBroadcast(in,null);
                        }
                    });
                }
                else{
                    //If permission denied
                    Toast.makeText(getApplicationContext(),"Permission Denied",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}

