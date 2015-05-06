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


public class PuddingActivity extends Activity {

    EditText count;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pudding);

        count = (EditText) findViewById(R.id.editText);

        next = (Button) findViewById(R.id.button4);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count.getText() != null && !count.getText().toString().isEmpty()) {
                    int currentTempura = MainActivity.currentPlayer.getPuddings();
                    MainActivity.currentPlayer.setPuddings(currentTempura + Integer.parseInt(count.getText().toString()));
                }
                viewSashimiActivity();
            }
        });

        Button back = (Button) findViewById(R.id.button7);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count.getText() != null && !count.getText().toString().isEmpty()) {
                    int currentTempura = MainActivity.currentPlayer.getPuddings();
                    MainActivity.currentPlayer.setPuddings(currentTempura + Integer.parseInt(count.getText().toString()));
                }
                viewMainActivity();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pudding, menu);
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
        Intent myIntent = new Intent(PuddingActivity.this, MainActivity.class);
        PuddingActivity.this.startActivity(myIntent);
    }

    public void viewMainActivity(){
        Intent myIntent = new Intent(PuddingActivity.this, MainActivity.class);
        PuddingActivity.this.startActivity(myIntent);
    }
}
