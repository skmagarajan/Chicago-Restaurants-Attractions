package com.example.mp3a2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {  // Receiver for toasting
        if("Attractions".equals(intent.getAction())){
            Log.i("REC","Inside R1 attractions");
            Toast.makeText(context,"Attractions Opening",Toast.LENGTH_SHORT).show();
        }
        if("Restaurants".equals(intent.getAction())){
            Log.i("REC","Inside R1 Restaurant");
            Toast.makeText(context,"Restaurants Opening",Toast.LENGTH_SHORT).show();
        }
    }
}
