package com.example.tacotruck.sushigo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;


public class MainActivity extends Activity {

    private Button player1, player2, score, info;
    public static Player currentPlayer;
    public Player p1 = null;
    public Player p2 = null;
    //private boolean isPlayer1;
    public static final HashMap nameToPlayer = new HashMap<String, Player>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1 = (Button) findViewById(R.id.button);
        player2 = (Button) findViewById(R.id.button2);
        score = (Button) findViewById(R.id.button3);
        info = (Button) findViewById(R.id.infoButton);

        initializePlayers();

        player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Player ONE was clicked");
                currentPlayer = (Player) nameToPlayer.get("player1");
                viewMakiActivity();
            }
        });

        player1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                currentPlayer = p1;
                chooseCardType();
                return false;
            }
        });

        player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Player TWO was cliecked");
                currentPlayer = p2;
                viewMakiActivity();
            }
        });

        player2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                currentPlayer = p2;
                chooseCardType();
                return false;
            }
        });

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

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo();
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

    public void viewMakiActivity(){
        Intent myIntent = new Intent(MainActivity.this, MakiActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void goToSushiType(int index){
        Intent myIntent = null;
        switch(index){
            case 0:
                myIntent = new Intent(MainActivity.this, MakiActivity.class);
                break;
            case 1:
                myIntent = new Intent(MainActivity.this, TempuraActivity.class);
                break;
            case 2:
                myIntent = new Intent(MainActivity.this, SashimiActivity.class);
                break;
            case 3:
                myIntent = new Intent(MainActivity.this, DumplingActivity.class);
                break;
            case 4:
                myIntent = new Intent(MainActivity.this, SquidNigiriActivity.class);
                break;
            case 5:
                myIntent = new Intent(MainActivity.this, SalmonNigiriActivity.class);
                break;
            case 6:
                myIntent = new Intent(MainActivity.this, EggNigiriActivity.class);
                break;
            case 7:
                myIntent = new Intent(MainActivity.this, PuddingActivity.class);
                break;
        }
        MainActivity.this.startActivity(myIntent);
    }

    public void viewScoresActivity(){
        Intent myIntent = new Intent(MainActivity.this, ScoresActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

    private void initializePlayers(){
        if(p1 == null){
            p1 = new Player("player1");
            nameToPlayer.put("player1", p1);
            System.out.println("intitialized " + p1.getName());
        }
        if(p2 == null){
            p2 = new Player("player2");
            nameToPlayer.put(p2.getName(), p2);
            System.out.println("intitialized " + p2.getName());
        }
    }

    private void resetScores(){
        p1.resetScore();
        p2.resetScore();
        Toast.makeText(MainActivity.this, "Scores have been reset!", Toast.LENGTH_SHORT).show();
    }

    public void chooseCardType(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        new AlertDialog.Builder(this)
                .setTitle("Pick a sushi to score")
                .setItems(R.array.sushi_list, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(MainActivity.this, which + "", Toast.LENGTH_SHORT).show();
                        goToSushiType(which);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public void showInfo(){
        String instructions  =  "<b>Maki:</b> most 6, second 3, split ties<br>" +
                "<b>Tempura:</b> set of 2: 5, otherwise 0<br>" +
                "<b>Sashimi:</b> set of 3: 10, otherwise 0<br>" +
                "<b>Dumplings:</b> 1: 1, 2: 3, 3: 6, 4: 10, 5+: 15<br>" +
                "<b>Nigiri:</b> squid 3, salmon 2, egg, 1<br>" +
                "<b>Wasabi:</b> triples value of nigiri<br>" +
                "<b>Chopsticks:</b> swap for 2 cards<br>" +
                "<b>Puddings:</b> most at game end 6, least -6, split ties";
        new AlertDialog.Builder(this)
                .setTitle(Html.fromHtml("<h1>Scoring Info</h1>"))
                .setMessage(Html.fromHtml(instructions))
                .setNegativeButton("Got it!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
