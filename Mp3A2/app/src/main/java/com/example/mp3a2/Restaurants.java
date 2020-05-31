package com.example.mp3a2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class Restaurants extends AppCompatActivity implements ListFragment.ListSelectionListener {

    BroadcastReceiver br = new MyReceiver();//Broadcast receiver for Toast
    BroadcastReceiver br2 = new MyReceiver2();//Broadcast receiver for starting activity

    public static String[] mLinkArray;
    public static String[] mListArray;

    private WebFragment Link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_restaurants);

        IntentFilter filter = new IntentFilter("Attractions");
        IntentFilter filter_r = new IntentFilter("Restaurants");
        IntentFilter filter2_r = new IntentFilter("Restaurants");
        IntentFilter filter2 = new IntentFilter("Attractions");

        filter.setPriority(5);
        filter_r.setPriority(5);
        filter2.setPriority(1);
        filter2_r.setPriority(1);
        //Registering receiver
        this.registerReceiver(br,filter);
        this.registerReceiver(br,filter_r);
        this.registerReceiver(br2,filter2);
        this.registerReceiver(br2,filter2_r);

        mListArray = getResources().getStringArray(R.array.res_items);
        mLinkArray = getResources().getStringArray(R.array.res_url_items);

        FragmentManager manager = getSupportFragmentManager();


        if(savedInstanceState == null){
            manager.beginTransaction().hide(manager.findFragmentById(R.id.web_container))
                    .show(manager.findFragmentById(R.id.list_container)).commit();
        }

        if(findViewById(R.id.layout_landscape)!=null){ //If screen in landscape at starting
            manager.beginTransaction().hide(manager.findFragmentById(R.id.web_container))
                    .show(manager.findFragmentById(R.id.list_container)).commit();
        }

        Link = (WebFragment) getSupportFragmentManager()
                .findFragmentById(R.id.web_container);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.Attractions) {  //Option Item clicked
            startActivity(new Intent(this,Attractions.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListSelection(int index) {
        if(findViewById(R.id.layout_portrait)!=null){  //If screen is portrait
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().show(manager.findFragmentById(R.id.web_container))
                    .hide(manager.findFragmentById(R.id.list_container)).addToBackStack(null).commit();
        }
        if(findViewById(R.id.layout_landscape)!=null){  //If screen is landscape
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().show(manager.findFragmentById(R.id.web_container))
                    .show(manager.findFragmentById(R.id.list_container)).addToBackStack(null).commit();
        }
        Link.showLinkAtIndex(index);
    }

    @Override
    public void onBackPressed() {  //To maintain fragments on Back press
        super.onBackPressed();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().hide(manager.findFragmentById(R.id.web_container))
                .show(manager.findFragmentById(R.id.list_container)).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Unregistering broadcast receiver
        unregisterReceiver(br);
        unregisterReceiver(br2);
    }
}
