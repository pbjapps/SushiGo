package com.example.tacotruck.sushigo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ScoresActivity extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    int p1total = 0;
    int p2total = 0;
//    http://www.androidhive.info/2013/07/android-expandable-list-view-tutorial/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        //get list view
        expListView = (ExpandableListView) findViewById(R.id.expandableListView);
        //prepare list data
        prepareListData();
        listAdapter = new com.example.tacotruck.sushigo.ExpandableListAdapter(this, listDataHeader, listDataChild);

        //setting list adapter
        expListView.setAdapter(listAdapter);

        Button back = (Button) findViewById(R.id.button5);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewMain();
            }
        });

        Button reset = (Button) findViewById(R.id.button8);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.resetScores();
                viewMain();
                Toast.makeText(getApplicationContext(), "Scores have been reset!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        prepareListData();
    }

    private void prepareListData(){
        Player p1r1 = (Player) MainActivity.nameToPlayer.get("p1r1");
        Player p2r1 = (Player) MainActivity.nameToPlayer.get("p2r1");
        Player p1r2 = (Player) MainActivity.nameToPlayer.get("p1r2");
        Player p2r2 = (Player) MainActivity.nameToPlayer.get("p2r2");
        Player p1r3 = (Player) MainActivity.nameToPlayer.get("p1r3");
        Player p2r3 = (Player) MainActivity.nameToPlayer.get("p2r3");

        HashMap<String, Integer> p1r1Totals = calculateTotal(p1r1, p2r1);
        HashMap<String, Integer> p2r1Totals = calculateTotal(p2r1, p1r1);
        HashMap<String, Integer> p1r2Totals = new HashMap<>();
        HashMap<String, Integer> p2r2Totals = new HashMap<>();
        HashMap<String, Integer> p1r3Totals = new HashMap<>();
        HashMap<String, Integer> p2r3Totals = new HashMap<>();

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Player 1 Round 1, total = " + p1r1Totals.get("total") );
        listDataHeader.add("Player 2 Round 1, total = " + p2r1Totals.get("total"));
        listDataChild.put(listDataHeader.get(0), populateData(p1r1Totals));
        listDataChild.put(listDataHeader.get(1), populateData(p2r1Totals));

        if(MainActivity.roundCount >= 2){
            if(p1r2 != null && p2r2 != null){
                p1r2Totals = calculateTotal(p1r2, p2r2);
                p2r2Totals = calculateTotal(p2r2, p1r2);
                listDataHeader.add("Player 1 Round 2, total = " + p1r2Totals.get("total") );
                listDataHeader.add("Player 2 Round 2, total = " + p2r2Totals.get("total"));
                listDataChild.put(listDataHeader.get(2), populateData(p1r2Totals));
                listDataChild.put(listDataHeader.get(3), populateData(p2r2Totals));
            }
        }
        if(MainActivity.roundCount == 3){
            if(p1r3 != null && p2r3 != null){
                p1r3Totals = calculateTotal(p1r3, p2r3);
                p2r3Totals = calculateTotal(p2r3, p1r3);
                //calculate pudding totals
                calculatePuddingPoints(p1r3, p1r2, p1r1, p2r3, p2r2, p2r1, p1r3Totals);
                calculatePuddingPoints(p2r3, p2r2, p2r1, p1r3, p1r2, p1r1, p2r3Totals);

                listDataHeader.add("Player 1 Round 3, total = " + p1r3Totals.get("total") );
                listDataHeader.add("Player 2 Round 3, total = " + p2r3Totals.get("total"));
                listDataChild.put(listDataHeader.get(4), populateData(p1r3Totals));
                listDataChild.put(listDataHeader.get(5), populateData(p2r3Totals));
            }
        }
    }

    public List<String> populateData(HashMap<String, Integer> totals){
        List<String> playerData = new ArrayList<String>();
        //most = 6, second = 3, split ties
        playerData.add("Maki: " + totals.get("mpoints"));
        //2 = 5 points
        playerData.add("Tempura: " + totals.get("tpoints") + "");
        //3 = 10 points
        playerData.add("Sashimi: " + totals.get("spoints") + "");
        //use dumpling map
        playerData.add("Dumplings: " + totals.get("dpoints") + "");
        //*3, *3 for wasabi
        playerData.add("Squid Nigiri: " + totals.get("sqpoints"));
        //*2, *3 for wasabi
        playerData.add("Salmon Nigiri: " + totals.get("salpoints"));
        //*1, *3 for wasabi
        playerData.add("Egg Nigiri: " + totals.get("eggpoints"));

        if(MainActivity.roundCount == 3){
            //most = 6, least = -6, split ties
            playerData.add("Pudding: " + totals.get("ppoints"));
        }
        return playerData;
    }

    public HashMap<String, Integer> calculateTotal(Player p1, Player p2){
        //calculate p1r1 total points
        HashMap<String, Integer> totals = new HashMap<>();
        int p1total = 0;
        if(p1.getMakiRolls() > p2.getMakiRolls()){
            p1total = p1total + 6;
            totals.put("mpoints", 6);
        }
        else if (p1.getMakiRolls() != 0 && p1.getMakiRolls() == p2.getMakiRolls()){
            p1total = p1total + 3;
            totals.put("mpoints", 3);
        }
        else if (p1.getMakiRolls()!= 0){
            p1total = p1total + 3;
            totals.put("mpoints", 3);
        }
        else{
            totals.put("mpoints", 0);
        }
        int tpoints = (int) Math.floor((double) p1.getTempura() / 2) * 5;
        totals.put("tpoints", tpoints);
        p1total = p1total + tpoints;
        int spoints = (int) Math.floor((double) p1.getSashimi() / 3) * 10;
        totals.put("spoints", spoints);
        p1total = p1total + spoints;

        int dpoints = 0;
        if (p1.getDumplings() >= 5){
            p1total = p1total + 15;
            dpoints = 15;
        }
        else if (p1.getDumplings() <= 0){
        }
        else{
            p1total = p1total + Player.dumplingMap.get(p1.getDumplings());
            dpoints = Player.dumplingMap.get(p1.getDumplings());
        }
        totals.put("dpoints", dpoints);

        p1total = p1total + calculateNigiriTotal(p1.getSquidNigiri(), p1.nigiriToWasabiMap.get(0), 3);
        totals.put("sqpoints", calculateNigiriTotal(p1.getSquidNigiri(), p1.nigiriToWasabiMap.get(0), 3));
        p1total = p1total + calculateNigiriTotal(p1.getSalmonNigiri(), p1.nigiriToWasabiMap.get(1), 2);
        totals.put("salpoints", calculateNigiriTotal(p1.getSalmonNigiri(), p1.nigiriToWasabiMap.get(1), 2));
        p1total = p1total + calculateNigiriTotal(p1.getEggNigiri(), p1.nigiriToWasabiMap.get(2), 1);
        totals.put("eggpoints", calculateNigiriTotal(p1.getEggNigiri(), p1.nigiriToWasabiMap.get(2), 1));

        totals.put("total", p1total);
        return totals;
    }

    public void calculatePuddingPoints(Player p1r1, Player p1r2, Player p1r3, Player p2r1, Player p2r2, Player p2r3, HashMap<String, Integer> p1r3Totals){
        int p1Pudding = p1r1.getPuddings() + p1r2.getPuddings() + p1r3.getPuddings();
        int p2Pudding = p2r1.getPuddings() + p2r2.getPuddings() + p2r3.getPuddings();
        int r3Total = p1r3Totals.get("total");
        if(p1Pudding > p2Pudding){
            r3Total = r3Total + 6;
            p1r3Totals.put("ppoints", 6);
        }
        else if(p1Pudding != 0 && p1Pudding == p2Pudding){
            r3Total = r3Total + 3;
            p1r3Totals.put("ppoints", 3);
        }
        else if (p1Pudding != 0){
            r3Total = r3Total - 6;
            p1r3Totals.put("ppoints", -6);
        }
        else{
            p1r3Totals.put("ppoints", 0);
        }
        p1r3Totals.put("total", r3Total);
    }

    public int calculateNigiriTotal(int nigiriCount, int wasabiCount, int mult){
        int total = 0;
        if(nigiriCount- wasabiCount >= 0){
            total = (nigiriCount- wasabiCount)*mult;
        }
        if(wasabiCount< nigiriCount){
            total = total+ wasabiCount*mult*3;
        }
        else if (wasabiCount>= nigiriCount){
            total = total + nigiriCount*mult*3;
        }
        return total;
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
