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


public class SalmonNigiriActivity extends Activity {

    EditText count, wasabiCount;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salmon_nigiri);

        count = (EditText) findViewById(R.id.editText);
        wasabiCount= (EditText) findViewById(R.id.editText2);

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
        if (count.getText() != null && !count.getText().toString().isEmpty()) {
            int currentTempura = MainActivity.currentPlayer.getSalmonNigiri();
            MainActivity.currentPlayer.setSalmonNigiri(currentTempura + Integer.parseInt(count.getText().toString()));
            if(wasabiCount.getText() != null && !wasabiCount.getText().toString().isEmpty()){
                MainActivity.currentPlayer.nigiriToWasabiMap.put(1, Integer.parseInt(wasabiCount.getText().toString()));
            }
        }
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
