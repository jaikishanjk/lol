package com.jai.bc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class new_game extends ActionBarActivity {
    int i=0,j;
    int tubulls=0,tcbulls=0,tucows=0,tccows=0;
    int ubullsu =0,cbullsc=0,ucowsu=0,ccowsc=0;
    int tcount = 0;
    int randomNumber[]= new int[4];
    int guessedNumber1[]= new int[4],guessedNumber2[]= new int[4];
    EditText et;
    Button guess;
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);


        et = (EditText) findViewById(R.id.et);
        guess= (Button) findViewById(R.id.guess);
        final int u[][] = new int[5][2],c[][] = new int [5][2];
        container = (LinearLayout)findViewById(R.id.container);
        guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                j=0;

                tubulls=0;
                tcbulls=0;
                tucows=0;
                tccows=0;

                ubullsu =0;
                cbullsc=0;
                ucowsu=0;
                ccowsc=0;

                Random crazy = new Random();
                int guessNum = crazy.nextInt(10000);
                convertToArray(guessNum, randomNumber);
                int getNum1 = (et.getText().toString().isEmpty()) ? 0 : Integer.valueOf(et.getText().toString());

                Random crazy1 = new Random();
                int getNum2 = crazy1.nextInt();
                while (!isFourDigit(getNum2)) {
                    getNum2 = crazy.nextInt(10000);
                }


                if (isFourDigit(getNum1)) {
                    LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View addView = layoutInflater.inflate(R.layout.resultt, null);
                    convertToArray(getNum1, guessedNumber1);
                    convertToArray(getNum2, guessedNumber2);

                    ubullsu = checkBulls(guessedNumber1, ubullsu);
                    TextView ubulls = (TextView) addView.findViewById(R.id.ubulls);
                    String subulls = Integer.toString(ubullsu);
                    ubulls.setText(subulls);
                    ucowsu = checkCows(guessedNumber1, ucowsu, ubullsu);
                    TextView ucows = (TextView) addView.findViewById(R.id.ucows);
                    String sucows = Integer.toString(ucowsu);
                    ucows.setText(sucows);
                    cbullsc = checkBulls(guessedNumber2, cbullsc);
                    TextView cbulls = (TextView) addView.findViewById(R.id.cbulls);
                    String scbulls = Integer.toString(cbullsc);
                    cbulls.setText(scbulls);
                    ccowsc = checkCows(guessedNumber2, ccowsc, cbullsc);
                    TextView ccows = (TextView) addView.findViewById(R.id.ccows);
                    String sccows = Integer.toString(ccowsc);
                    ccows.setText(sccows);

                    TextView cnum = (TextView) addView.findViewById(R.id.cnum);
                    String s = Integer.toString(getNum2);
                    cnum.setText(s);

                    container.addView(addView);
                    et.setText("");

                    if (ubullsu == 4) {
                        Toast.makeText(getApplicationContext(), "BINGO!", Toast.LENGTH_LONG);
                        guess.setEnabled(false);
                    }

                    u[i][j] = ubullsu;
                    c[i][j] = cbullsc;
                    j++;

                    u[i][j] = ucowsu;
                    c[i][j] = ccowsc;
                    i++;
                    tcount++;

                    ubullsu = 0;
                    ucowsu = 0;
                    cbullsc = 0;
                    ccowsc = 0;


                    if (tcount == 5) {
                        for (int k = 0; k <5; k++) {
                            tubulls += u[k][0];
                            tcbulls += c[k][0];
                            tucows += u[k][1];
                            tccows += c[k][1];
                        }
                        Intent i = new Intent(new_game.this, set_complete.class);
                        Bundle b = new Bundle();
                        b.putInt("p1",tubulls);
                        b.putInt("p2",tcbulls);
                        b.putInt("p3",tucows);
                        b.putInt("p4",tccows);
                        i.putExtras(b);
                        startActivity(i);
                    }

                }
                else {
                    LayoutInflater lef = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View popView = lef.inflate(R.layout.popwindow, null);
                    final PopupWindow popUp = new PopupWindow(popView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    Button dismiss = (Button) popView.findViewById(R.id.popBt);
                    dismiss.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popUp.dismiss();
                        }
                    });
                    popUp.showAsDropDown(guess, 50, -30);

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_game, menu);
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

    public void convertToArray(int n, int a[]){
        int index =0;
        int r;
        while(n>0){
            r = n%10;
            a[index]=r;
            n/=10;
            index++;
        }
    }

    public boolean isFourDigit(int num){
        int count=0;
        while(num>0){
            count++;
            num /=10;
        }
        if(count==4)
            return true;
        else
            return false;
    }

    public int checkBulls(int gn[],int b){
        int i;
        for(i=0; i<4; i++){
            if (gn[i]==randomNumber[i])
                b++;
        }
        return b;
    }
    public int checkCows(int gn[],int c,int b){
        int i,j;
        for (i=0; i<4; i++){
            for (j=0; j<4; j++){
                if(gn[i]== randomNumber[j])
                    c++;
            }
        }
        c=c-b;
        return c;
    }
}
