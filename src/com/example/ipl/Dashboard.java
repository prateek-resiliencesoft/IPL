package com.example.ipl;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Dashboard extends ActionBarActivity implements OnClickListener {
TextView textnewbet;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		textnewbet=(TextView)findViewById(R.id.txtnewbet);
		textnewbet.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dashboard, menu);
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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		try {
			
			
			switch (v.getId()) {
			case R.id.txtnewbet:
				Intent intent=new Intent(Dashboard.this,MainActivity.class);
				startActivity(intent);
				
				break;

			default:
				break;
			}
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
