package com.example.tacotruck.sushigo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ScoresActivity extends ActionBarActivity {

    TextView player1, player2;
    TextView maki, tempura, sashimi, dumplings, puddings, sqnigiri, salnigiri, eggnigiri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        player1 = (TextView) findViewById(R.id.textView4);
        player2 = (TextView) findViewById(R.id.textView5);
        maki = (TextView) findViewById(R.id.textView14);
        tempura = (TextView) findViewById(R.id.textView15);
        sashimi = (TextView) findViewById(R.id.textView16);
        dumplings= (TextView) findViewById(R.id.textView17);
        sqnigiri= (TextView) findViewById(R.id.textView18);
        salnigiri= (TextView) findViewById(R.id.textView19);
        eggnigiri= (TextView) findViewById(R.id.textView20);
        puddings= (TextView) findViewById(R.id.textView21);

        int total = 0;

        Player p1 = (Player) MainActivity.nameToPlayer.get("player1");
        //most = 6, second = 3, split ties
        maki.setText(p1.getMakiRolls() + "");
        total = total + 6;
        //2 = 5 points
        int tpoints = (int) Math.floor((double) p1.getTempura() / 2) * 5;
        tempura.setText(tpoints + "");
        total = total + tpoints;
        //3 = 10 points
        int spoints = (int) Math.floor((double) p1.getSashimi() / 3) * 10;
        sashimi.setText(spoints + "");
        total = total + spoints;
        //use dumpling map
        int dpoints = p1.getDumplings();
        if (dpoints >= 5){
            dumplings.setText("15");
            total = total + 15;
        }
        else if (dpoints <= 0){
            dumplings.setText("0");
        }
        else{
            dumplings.setText(Player.dumplingMap.get(p1.getDumplings()) + "");
            total = total + Player.dumplingMap.get(p1.getDumplings());
        }
        //*3
        sqnigiri.setText(p1.getSquidNigiri() * 3 + "");
        total = total + p1.getSquidNigiri() * 3;
        //*2
        salnigiri.setText(p1.getSalmonNigiri() * 2 + "");
        total = total + p1.getSalmonNigiri() * 2;
        //*1
        eggnigiri.setText(p1.getEggNigiri() + "");
        total = total + p1.getEggNigiri();
        //most = 6, least = -6, split ties
        puddings.setText(p1.getPuddings() + "");
        total = total + 6;

        player1.setText(total + "");

        //Player p2 = (Player) MainActivity.nameToPlayer.get("player2");
        //player2.setText(p2.getMakiRolls() + "");

        Button back = (Button) findViewById(R.id.button5);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewMain();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scores, menu);
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

    public void viewMain(){
        Intent myIntent = new Intent(ScoresActivity.this, MainActivity.class);
        ScoresActivity.this.startActivity(myIntent);
    }
}
