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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ScoresActivity extends Activity {

//    TextView player1, player2;
//    TextView maki, tempura, sashimi, dumplings, puddings, sqnigiri, salnigiri, eggnigiri;
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
    }

    private void prepareListData(){
        Player p1 = (Player) MainActivity.nameToPlayer.get("player1");
        Player p2 = (Player) MainActivity.nameToPlayer.get("player2");
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        //calculate p1 total points
        if(p1.getMakiRolls() > p2.getMakiRolls()){
            p1total = p1total + 6;
        }
        else if (p1.getMakiRolls() != 0 && p1.getMakiRolls() == p2.getMakiRolls()){
            p1total = p1total + 3;
        }
        else if (p1.getMakiRolls()!= 0){
            p1total = p1total + 3;
        }
        int tpoints = (int) Math.floor((double) p1.getTempura() / 2) * 5;
        p1total = p1total + tpoints;
        int spoints = (int) Math.floor((double) p1.getSashimi() / 3) * 10;
        p1total = p1total + spoints;
        int dpoints = p1.getDumplings();
        if (dpoints >= 5){
            p1total = p1total + 15;
        }
        else if (dpoints <= 0){
        }
        else{
            p1total = p1total + Player.dumplingMap.get(p1.getDumplings());
        }
        p1total = p1total + calculateNigiriTotal(p1.getSquidNigiri(), p1.nigiriToWasabiMap.get(0), 3);
        p1total = p1total + calculateNigiriTotal(p1.getSalmonNigiri(), p1.nigiriToWasabiMap.get(1), 2);
        p1total = p1total + calculateNigiriTotal(p1.getEggNigiri(), p1.nigiriToWasabiMap.get(2), 1);
        if(p1.getPuddings() > p2.getPuddings()){
            p1total = p1total + 6;
        }
        else if(p1.getPuddings() != 0 && p1.getPuddings() == p2.getPuddings()){
            p1total = p1total + 3;
        }
        else if (p1.getPuddings()!= 0){
            p1total = p1total + 3;
        }

        //calculate p2 total points
        if(p2.getMakiRolls() > p1.getMakiRolls()){
            p2total = p2total + 6;
        }
        else if (p2.getMakiRolls() != 0 && p1.getMakiRolls() == p2.getMakiRolls()){
            p2total = p2total + 3;
        }
        else if (p2.getMakiRolls()!= 0){
            p2total = p2total + 3;
        }
        int t2points = (int) Math.floor((double) p2.getTempura() / 2) * 5;
        p2total = p2total + t2points;
        int s2points = (int) Math.floor((double) p2.getSashimi() / 3) * 10;
        p2total = p2total + s2points;
        int d2points = p2.getDumplings();
        if (d2points >= 5){
            p2total = p2total + 15;
        }
        else if (d2points <= 0){
        }
        else{
            p2total = p2total + Player.dumplingMap.get(p2.getDumplings());
        }
        p2total = p2total + calculateNigiriTotal(p2.getSquidNigiri(), p2.nigiriToWasabiMap.get(0), 3);
        p2total = p2total + calculateNigiriTotal(p2.getSalmonNigiri(), p2.nigiriToWasabiMap.get(1), 2);
        p2total = p2total + calculateNigiriTotal(p2.getEggNigiri(), p2.nigiriToWasabiMap.get(2), 1);
        if(p2.getPuddings() > p1.getPuddings()){
            p2total = p2total + 6;
        }
        else if(p2.getPuddings() != 0 && p1.getPuddings() == p2.getPuddings()){
            p2total = p2total + 3;
        }
        else if (p2.getPuddings()!= 0){
            p2total = p2total + 3;
        }

        // Adding child data
        listDataHeader.add("Player 1, total = " + p1total );
        listDataHeader.add("Player 2, total = " + p2total );
        //listDataHeader.add("Player N");

        // Adding child data
        List<String> player1Data = new ArrayList<String>();
        //most = 6, second = 3, split ties
        if(p1.getMakiRolls() > p2.getMakiRolls()){
            player1Data.add("Maki: 6");
        }
        else if (p1.getMakiRolls() != 0 && p1.getMakiRolls() == p2.getMakiRolls()){
            player1Data.add("Maki: 3");
        }
        else if (p1.getMakiRolls()!= 0){
            player1Data.add("Maki: 3");
        }
        else{
            player1Data.add("Maki: 0");
        }

        //2 = 5 points
        player1Data.add("Tempura: " + tpoints + "");

        //3 = 10 points
        player1Data.add("Sashimi: " + spoints + "");

        //use dumpling map
        if (dpoints >= 5){
            player1Data.add("Dumplings: 15");
        }
        else if (dpoints <= 0){
            player1Data.add("Dumplings: 0");
        }
        else{
            player1Data.add("Dumplings: " + Player.dumplingMap.get(p1.getDumplings()) + "");
        }

        //*3, *3 for wasabi
        player1Data.add("Squid Nigiri: " + calculateNigiriTotal(p1.getSquidNigiri(), p1.nigiriToWasabiMap.get(0), 3) + "");

        //*2, *3 for wasabi
        player1Data.add("Salmon Nigiri: " + calculateNigiriTotal(p1.getSalmonNigiri(), p1.nigiriToWasabiMap.get(1), 2) + "");

        //*1, *3 for wasabi
        player1Data.add("Egg Nigiri: " + calculateNigiriTotal(p1.getEggNigiri(), p1.nigiriToWasabiMap.get(2), 1) + "");

        //most = 6, least = -6, split ties
        if(p1.getPuddings() > p2.getPuddings()){
            player1Data.add("Pudding: 6");
        }
        else if(p1.getPuddings() != 0 && p1.getPuddings() == p2.getPuddings()){
            player1Data.add("Pudding: 3");
        }
        else if (p1.getPuddings()!= 0){
            player1Data.add("Pudding: 3");
        }
        else{
            player1Data.add("Pudding: 0");
        }

        List<String> player2Data = new ArrayList<String>();
        //most = 6, second = 3, split ties
        if(p2.getMakiRolls() > p1.getMakiRolls()){
            player2Data.add("Maki: 6");
        }
        else if (p2.getMakiRolls() != 0 &&  p1.getMakiRolls() == p2.getMakiRolls()){
            player2Data.add("Maki: 3");
        }
        else if (p2.getMakiRolls()!= 0){
            player2Data.add("Maki: 3");
        }
        else{
            player2Data.add("Maki: 0");
        }

        //2 = 5 points
        player2Data.add("Tempura: " + t2points + "");


        //3 = 10 points
        player2Data.add("Sashimi: " + s2points + "");

        //use dumpling map
        if (d2points >= 5){
            player2Data.add("Dumplings: 15");
        }
        else if (d2points <= 0){
            player2Data.add("Dumplings: 0");
        }
        else{
            player2Data.add("Dumplings: " + Player.dumplingMap.get(p2.getDumplings()) + "");
        }

        //*3, *3 for wasabi
        player2Data.add("Squid Nigiri: " + calculateNigiriTotal(p2.getSquidNigiri(), p2.nigiriToWasabiMap.get(0), 3) + "");

        //*2, *3 for wasabi
        player2Data.add("Salmon Nigiri: " + calculateNigiriTotal(p2.getSalmonNigiri(), p2.nigiriToWasabiMap.get(1), 2) + "");

        //*1, *3 for wasabi
        player2Data.add("Egg Nigiri: " + calculateNigiriTotal(p2.getEggNigiri(), p2.nigiriToWasabiMap.get(2), 1) + "");

        //most = 6, least = -6, split ties
        if(p2.getPuddings() > p1.getPuddings()){
            player2Data.add("Pudding: 6");
        }
        else if(p2.getPuddings()!= 0 && p1.getPuddings() == p2.getPuddings()){
            player2Data.add("Pudding: 3");
        }
        else if (p2.getPuddings()!= 0){
            player2Data.add("Pudding: 3");
        }
        else{
            player2Data.add("Pudding: 0");
        }

        //List<String> playerNData = new ArrayList<String>();
        //playerNData.add("...");

        listDataChild.put(listDataHeader.get(0), player1Data); // Header, Child data
        listDataChild.put(listDataHeader.get(1), player2Data);
//        listDataChild.put(listDataHeader.get(2), playerNData);
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
