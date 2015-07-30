package com.jai.bc;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button ng = (Button) findViewById(R.id.ng);
        ng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ing = new Intent(MainActivity.this,new_game.class);
                startActivity(ing);
            }
        });
        Button credits = (Button) findViewById(R.id.credits);
        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent icredits = new Intent(MainActivity.this,credits.class);
                startActivity(icredits);
            }
        });
        Button instructions = (Button) findViewById(R.id.instructions);
        instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iinstructions = new Intent(MainActivity.this,instructions.class);
                startActivity(iinstructions);
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

}
