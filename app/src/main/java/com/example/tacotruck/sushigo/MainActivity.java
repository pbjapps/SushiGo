package com.example.tacotruck.sushigo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;


public class MainActivity extends ActionBarActivity {

    private Button player1, player2, score;
    public static Player p1 = null;
    public static Player p2 = null;
    public static final HashMap nameToPlayer = new HashMap<String, Player>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1 = (Button) findViewById(R.id.button);
        player2 = (Button) findViewById(R.id.button2);
        score = (Button) findViewById(R.id.button3);

        initializeP1();
        //initializeP2();

        player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Player ONE was clicked");
                viewMakiActivity(p1);
            }
        });

//        player2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("Player TWO was cliecked");
//                viewMakiActivity(p2);
//            }
//        });

        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewScoresActivity();
            }
        });

        Button reset = (Button) findViewById(R.id.button6);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetScores();
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

    public void viewMakiActivity(Player playerx){
        Intent myIntent = new Intent(MainActivity.this, MakiActivity.class);
        System.out.println("sending extra: name => " + playerx.getName());
        myIntent.putExtra("name", playerx.getName());
        MainActivity.this.startActivity(myIntent);
    }

    public void viewScoresActivity(){
        Intent myIntent = new Intent(MainActivity.this, ScoresActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

//    private void initializePlayers(){
//        if(p1 == null){
//            p1 = new Player("player1");
//            nameToPlayer.put("player1", p1);
//        }
//        if(p2 == null){
//            p2 = new Player("player2");
//            nameToPlayer.put("player2", p2);
//        }
//    }

    private void initializeP1(){
        if(p1 == null){
            p1 = new Player("player1");
            nameToPlayer.put("player1", p1);
        }
    }

    private void initializeP2(){
        if(p2 == null){
            p2 = new Player("player2");
            nameToPlayer.put("player2", p2);
        }
    }

    private void resetScores(){
        Player.resetScore(p1);
        Toast.makeText(MainActivity.this, "Scores have been reset!", Toast.LENGTH_SHORT).show();
    }
}
