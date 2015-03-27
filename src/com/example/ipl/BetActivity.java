package com.example.ipl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BetActivity extends ActionBarActivity implements OnClickListener {
	TextView txtteamA,txtteamB,teamAforidtwo,teamBforidtwo;
	Button btnbet;
	EditText edfirstteam;
	String point,access_token;
	HttpPost httppost,HttpGetMatch;
	Boolean internetactive;
	HttpClient httpclient;
	ProgressDialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bet);
		
		
		
		btnbet=(Button)findViewById(R.id.btnbet);
        btnbet.setOnClickListener(this);
        txtteamA=(TextView)findViewById(R.id.txtteam1);
        txtteamB=(TextView)findViewById(R.id.txtteam2);
        teamAforidtwo=(TextView)findViewById(R.id.txtteam1foridsecond);
        teamBforidtwo=(TextView)findViewById(R.id.txtteam2foridsecond);
       
        
      
        httppost = new HttpPost(HttpUrls.HttpPutpoint);
		HttpGetMatch=new HttpPost(HttpUrls.Httpgetmatchdetails);
	
	}

	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		try {
			
			switch (v.getId()) {
			case R.id.txtteam1:
				
				
				
				
				
				
				
				final Dialog d = new Dialog(BetActivity.this);
				d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
				d.setContentView(R.layout.layoutforbet);
				d.show();
				 edfirstteam=(EditText)d.findViewById(R.id.edmatch1);
				d.findViewById(R.id.btnbet).setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								d.dismiss();
								point=edfirstteam.getText().toString();
								
								if(point.equals(""))
								{
									
									Toast.makeText(BetActivity.this, "Fields are empty",Toast.LENGTH_SHORT).show();	
									 return;
//									Intent intent=new Intent(MainActivity.this, History.class);
//									startActivity(intent);
								}
								else
								{ 
								 internetactive = isNetworkAvailable();//check network
								}
								if (internetactive) 
								{					
								 new getresult().execute();
								}
								else 
							     {
								    Toast.makeText(BetActivity.this,"Internet Not Connected",Toast.LENGTH_SHORT).show();
							    	}	
								
								
							}
						});
				
				break;

			default:
				break;
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
			
		
	}



	protected Boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null&& activeNetworkInfo.isConnectedOrConnecting();
	}
	
	
	
	
	
	public class getresult extends AsyncTask<Void, Void, Void>
    {
      String result=null;
      String result2= null;
  
     
		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			
			try {
				HttpParams params = new BasicHttpParams();				
				params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION,HttpVersion.HTTP_1_1);
				httpclient = new DefaultHttpClient(params);				
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
				nameValuePairs.add(new BasicNameValuePair("accesstoken", point));
				nameValuePairs.add(new BasicNameValuePair("amount", point));
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"utf-8"));
				result = httpclient.execute(httppost, new BasicResponseHandler());
				
				HttpGetMatch.setEntity(new UrlEncodedFormEntity(nameValuePairs,"utf-8"));
				result2 = httpclient.execute(HttpGetMatch, new BasicResponseHandler());
			} 
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;			
		}
    	
		 @Override
		 protected void onPostExecute(Void result1)
		 {
			 super.onPostExecute(result1);
			 
			 
			 try 
			 {				 				
				 JSONArray array=  	new JSONArray(result2);							 
				 for(int i=0;i<array.length();i++)
				 {					
				        JSONObject jsonObject = array.getJSONObject(i);					        				        
				        String matchid = jsonObject.getString("MatchId");
		                String firstteam = jsonObject.getString("FirstTeam");
		                String secondteam = jsonObject.getString("SecondTeam");
//		                String [] show ={matchid,firstteam,secondteam};		               
		                Toast.makeText(BetActivity.this, matchid+firstteam+secondteam, Toast.LENGTH_LONG).show();		                 
		                if(matchid.equals("1"))
		                {
		                	txtteamA.setText(firstteam);
		                	txtteamB.setText(secondteam);
		                }
		                else
		                {
		                	Toast.makeText(BetActivity.this, "There Is No Team", Toast.LENGTH_SHORT).show();
		                }
		                if(matchid.equals("2"))
		                {
		                	teamAforidtwo.setText(firstteam);
		                	teamBforidtwo.setText(secondteam);
		                }
		                else
		                {
		                	Toast.makeText(BetActivity.this, "There Is No Team", Toast.LENGTH_SHORT).show();
		                }
				        				     				  				    
				 }
				 
			} catch (Exception e) {
				// TODO: handle exception
			}
			 


			   
		 }
		 
              
    }
}
