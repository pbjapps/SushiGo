package com.example.tacotruck.sushigo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class TempuraActivity extends ActionBarActivity {

    Button next;
    EditText count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempura);

        count = (EditText) findViewById(R.id.editText);

        next = (Button) findViewById(R.id.button4);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count.getText() != null && !count.getText().toString().isEmpty()) {
                    int currentTempura = MakiActivity.currentPlayer.getTempura();
                    MakiActivity.currentPlayer.setTempura(currentTempura+ Integer.parseInt(count.getText().toString()));
                }
                viewSashimiActivity();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tempura, menu);
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
        Intent myIntent = new Intent(TempuraActivity.this, SashimiActivity.class);
        TempuraActivity.this.startActivity(myIntent);
    }
}
