package com.example.mp3a2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) { //Receiver for starting activity
        if("Attractions".equals(intent.getAction())){
            Log.i("REC","Inside R2 attractions");
            Intent in = new Intent(context, Attractions.class);
            context.startActivity(in);
        }
        if("Restaurants".equals(intent.getAction())){
            Log.i("REC","Inside R2 restaurants");
            Intent in = new Intent(context, Restaurants.class);
            context.startActivity(in);
        }
    }
}
