package com.examen.dragos.variantafinala;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WeatherActivity extends ActionBarActivity {
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new WeatherFragment())
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // creeam meniul cu schimbarea orasului
        getMenuInflater().inflate(R.menu.weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*int id = item.getItemId();
        if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);*/
        if (item.getItemId() == R.id.change_city) {
            showInputDialog();
        }
        return false;

    }

        /*public void setBtnBack(View v) {
            btnBack = (Button)findViewById(R.id.btnBack);

            Intent intentiaback=new Intent(v.getContext(),MainActivity.class);
            WeatherActivity.this.startActivity(intentiaback);
        };
        */


    /* fereastra de dialog pentru schimbarea orasului */
    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Introdu orasul");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("Cauta", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeCity(input.getText().toString());
            }
        });
        builder.show();
    }

    /*schimbarea orasului*/
    public void changeCity(String city) {
        WeatherFragment wf = (WeatherFragment) getSupportFragmentManager()
                .findFragmentById(R.id.container);
        wf.changeCity(city);
        new CityPreference(this).setCity(city);
    }

    public void onBackPressed(View v){
        Vibrator vibe=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(50);
        Intent anterior=new Intent(v.getContext(),MainActivity.class);
        WeatherActivity.this.startActivity(anterior);
    }

}
