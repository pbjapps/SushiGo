package com.example.tacotruck.sushigo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;


public class SalmonNigiriActivity extends Activity {

    NumberPicker count, wasabiCount;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salmon_nigiri);

        count = (NumberPicker) findViewById(R.id.editText);
        count.setMaxValue(10);
        count.setMinValue(0);

        wasabiCount= (NumberPicker) findViewById(R.id.editText2);
        wasabiCount.setMaxValue(10);
        wasabiCount.setMinValue(0);

        next = (Button) findViewById(R.id.button4);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScore();
                viewSashimiActivity();
            }
        });

        Button back = (Button) findViewById(R.id.button7);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScore();
                viewMainActivity();
            }
        });
    }

    public void updateScore(){
            int currentTempura = MainActivity.currentPlayer.getSalmonNigiri();
            MainActivity.currentPlayer.setSalmonNigiri(currentTempura + count.getValue());
            MainActivity.currentPlayer.nigiriToWasabiMap.put(1, wasabiCount.getValue());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_salmon_nigiri, menu);
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

    public void viewSashimiActivity(){
        Intent myIntent = new Intent(SalmonNigiriActivity.this, EggNigiriActivity.class);
        SalmonNigiriActivity.this.startActivity(myIntent);
    }

    public void viewMainActivity(){
        Intent myIntent = new Intent(SalmonNigiriActivity.this, MainActivity.class);
        SalmonNigiriActivity.this.startActivity(myIntent);
    }
}
