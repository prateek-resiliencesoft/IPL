package com.example.ipl;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements OnClickListener {
TextView txtteamA,txtteamB;
Button btnbet;
EditText edteam1,edteam2;
String team,team2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnbet=(Button)findViewById(R.id.btnbet);
        btnbet.setOnClickListener(this);
        txtteamA=(TextView)findViewById(R.id.txtteam1);
        txtteamB=(TextView)findViewById(R.id.txtteam2);
        edteam1=(EditText)findViewById(R.id.edmatch1);
        edteam2=(EditText)findViewById(R.id.edmatch2);
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		team=edteam1.getText().toString();
		team2=edteam2.getText().toString();
		if(team.equals(team2))
		{
			Intent intent=new Intent(MainActivity.this, MatchActivity.class);
			startActivity(intent);
		}
	}
}
