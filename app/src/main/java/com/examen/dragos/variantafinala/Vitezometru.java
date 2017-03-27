package com.examen.dragos.variantafinala;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Vitezometru extends Activity implements LocationListener {
    final int update_interval = 500; // milliseconds
    Double lat, lon, alt;
    // Data shown to user
    float speed = 0.0f;
    float speed_max = 0.0f;
    int num_updates = 0; // GPS update counter
    int no_loc = 0; // Number of null GPS updates
    int no_speed = 0; // Number of GPS updates which don't have speed
    Vibrator vibe;
    LocationManager loc_mgr;
    TextView Speedms, Speedkmh, SpeedMaxms, SpeedMaxkmh;

    // Called when the activity is first created.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitezometru);
        Speedms = (TextView) findViewById(R.id.SpeedMS);
        Speedkmh = (TextView) findViewById(R.id.SpeedKmH);
        SpeedMaxkmh = (TextView) findViewById(R.id.MaxKmH);
        SpeedMaxms = (TextView) findViewById(R.id.MaxMS);
//        coordonate=(TextView)findViewById(R.id.coordonate);
        update_speed(0.0f);

        loc_mgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        loc_mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, update_interval, 0.0f, this);
/*
       lat=loc_mgr.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude();
       lon=loc_mgr.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude();
       alt=loc_mgr.getLastKnownLocation(LocationManager.GPS_PROVIDER).getAltitude();
*/
    }

    void update_speed(float x) {

        speed = x;
        if (x > speed_max)
            speed_max = x;

        Speedms.setText(String.format("%.2f m/s", new Float(speed)));
        Speedkmh.setText(String.format("%.0f Km/h", new Float(speed) * 3.6f));
        SpeedMaxms.setText(String.format("%.2f m/s", new Float(speed_max)));
        SpeedMaxkmh.setText(String.format("%.0f Km/h", new Float(speed_max) * 3.6f));


    }

    public void onLocationChanged(Location loc) {
        num_updates++;

        if (loc == null) {
            no_loc++;
            return;
        }

        if (!loc.hasSpeed()) {
            no_speed++;
            return;
        }
      /*  coordonate.setText(String.format("%.3f lat:",lat.toString())+String.format("%.3f long:"+lon.toString())+
      String.format("%.3f alt:"+alt.toString()));*/

        update_speed(loc.getSpeed());
    }


    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
    }

    public void onProviderEnabled(String arg0) {
    }

    public void onProviderDisabled(String arg0) {
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vitezometru, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onBackPressed(View v) {
        Intent anterior=new Intent(v.getContext(),MainActivity.class);
        Vitezometru.this.startActivity(anterior);
        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(50);

    }
}