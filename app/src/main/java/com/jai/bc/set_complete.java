package com.jai.bc;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class set_complete extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle bundle = getIntent().getExtras();
        String w="";
        int tubullsu = bundle.getInt("p1");
        int tucowsu = bundle.getInt("p3");
        int tcbullsc = bundle.getInt("p2");
        int tccowsc = bundle.getInt("p4");
        int winner=0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_complete);
        TextView user = (TextView) findViewById(R.id.user);
        user.setText("User got " + tubullsu + " bull/s and " + tucowsu + "cow/s.");
        TextView computer = (TextView) findViewById(R.id.computer);
        computer.setText("Comp got " + tcbullsc + " bull/s and " + tccowsc + " cow/s");
        if(tubullsu>tcbullsc)
            winner = 1;
        else if(tubullsu<tcbullsc)
                winner = 2;
        else{
            if(tucowsu>tccowsc) winner =1;
            else if(tucowsu<tccowsc) winner =2;
            else winner = 0;
        }
        if(winner == 0) w = "TIE";
        else if(winner == 1) w = "User";
        else w = "Computer";
        TextView win = (TextView) findViewById(R.id.winner);
        win.setText(w + " WINS!");
        Button done = (Button) findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iii = new Intent(set_complete.this,MainActivity.class);
                startActivity(iii);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_complete, menu);
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
