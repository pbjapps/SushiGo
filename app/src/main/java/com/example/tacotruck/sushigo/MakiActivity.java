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


public class MakiActivity extends Activity {

    Button next;
    EditText count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maki);

        System.out.println("currentPlayer = " + MainActivity.currentPlayer.getName());

        count = (EditText) findViewById(R.id.editText);

        next = (Button) findViewById(R.id.button4);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count.getText() != null && !count.getText().toString().isEmpty()){
                    int currentMaki = MainActivity.currentPlayer.getMakiRolls();
                    MainActivity.currentPlayer.setMakiRolls(currentMaki + Integer.parseInt(count.getText().toString()));
                }
                viewTempuraActivity();
            }
        });

        Button back = (Button) findViewById(R.id.button7);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count.getText() != null && !count.getText().toString().isEmpty()){
                    int currentMaki = MainActivity.currentPlayer.getMakiRolls();
                    MainActivity.currentPlayer.setMakiRolls(currentMaki + Integer.parseInt(count.getText().toString()));
                }
                viewMainActivity();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_maki, menu);
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

    public void viewTempuraActivity(){
        Intent myIntent = new Intent(MakiActivity.this, TempuraActivity.class);
        MakiActivity.this.startActivity(myIntent);
    }

    public void viewMainActivity(){
        Intent myIntent = new Intent(MakiActivity.this, MainActivity.class);
        MakiActivity.this.startActivity(myIntent);
    }
}
