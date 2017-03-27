package com.examen.dragos.variantafinala;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private static long back_pressed_time;
    private static long PERIOD = 2000;
    Button btnV;
    Button btnViteza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
        final Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
        ;

        btnV = (Button) findViewById(R.id.btnVreme);
        btnV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentieVreme = new Intent(v.getContext(), WeatherActivity.class);
                MainActivity.this.startActivity(intentieVreme);
                overridePendingTransition(R.animator.slide_in_left,R.animator.slide_out_left);
                vibe.vibrate(100);
            }
        });

        btnViteza = (Button) findViewById(R.id.btnViteza);
        btnViteza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentieViteza = new Intent(v.getContext(), Vitezometru.class);
                MainActivity.this.startActivity(intentieViteza);

                overridePendingTransition(R.animator.slide_out_left,R.animator.slide_in_left);
                vibe.vibrate(100);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    /* apasarea de 2 ori consecutiv a buttonului back pentru a iesi */
    @Override
    public void onBackPressed() {
        final Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(50);
        if (back_pressed_time + PERIOD > System.currentTimeMillis())
            super.onBackPressed();
        else
            Toast.makeText(getBaseContext(), "Apasa din nou pentru a iesi!", Toast.LENGTH_SHORT).show();
        back_pressed_time = System.currentTimeMillis();
    }
}
