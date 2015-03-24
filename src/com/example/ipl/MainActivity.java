package com.example.ipl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
import org.json.JSONException;
import org.json.JSONObject;
import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements OnClickListener {
TextView txtteamA,txtteamB;
Button btnbet;
EditText edfirstteam;
String point,access_token;
HttpPost httppost,HttpGetMatch;
Boolean internetactive;
HttpClient httpclient;
ProgressDialog dialog;
private String[] states;
private Spinner spinner;
List<String> teams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
       
        btnbet=(Button)findViewById(R.id.btnbet);
        btnbet.setOnClickListener(this);
        txtteamA=(TextView)findViewById(R.id.txtteam1);
        txtteamB=(TextView)findViewById(R.id.txtteam2);
        edfirstteam=(EditText)findViewById(R.id.edmatch1);
        spinner=(Spinner)findViewById(R.id.spmatch);
        
        httppost = new HttpPost(HttpUrls.HttpPutpoint);
		HttpGetMatch=new HttpPost(HttpUrls.Httpgetmatchdetails);
		
		
//		ArrayList<String> result= getCountries(Environment.getExternalStorageDirectory()+"/jsoncountries.txt");
//	     ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,R.id.txt, result);
//	        spinner.setAdapter(adapter);  
        
    }


   


	




	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		point=edfirstteam.getText().toString();
		
		if(point.equals(""))
		{
			
			Toast.makeText(MainActivity.this, "Fields are empty",Toast.LENGTH_SHORT).show();	
			 return;
//			Intent intent=new Intent(MainActivity.this, History.class);
//			startActivity(intent);
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
		    Toast.makeText(MainActivity.this,"Internet Not Connected",Toast.LENGTH_SHORT).show();
	    	}		
	}





	private Boolean isNetworkAvailable() {
		// TODO Auto-generated method stub
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
		               
		                Toast.makeText(MainActivity.this, matchid+firstteam+secondteam, Toast.LENGTH_LONG).show();		                 
//		                if(matchid.equals("1"))
//		                {
//		                	txtteamA.setText(firstteam);
//		                	txtteamB.setText(secondteam);
//		                }
				        				     				  				    
				 }				 				 				
			} catch (Exception e) {
				// TODO: handle exception
			}
			 

//	 Toast.makeText(MainActivity.this, result2, Toast.LENGTH_LONG).show();
			    
			   
		 }
		 
//		 private void show()
//		 {
//			 try 
//			 {				 				
//				 JSONArray array=  	new JSONArray(result2);							 
//				 for(int i=0;i<array.length();i++)
//				 {					
//				        JSONObject jsonObject = array.getJSONObject(i);					        				        
//				        String matchid = jsonObject.getString("MatchId");
//		                String firstteam = jsonObject.getString("FirstTeam");
//		                String secondteam = jsonObject.getString("SecondTeam");
//		               
//		                Toast.makeText(MainActivity.this, matchid+firstteam+secondteam, Toast.LENGTH_LONG).show();		                 
////		                if(matchid.equals("1"))
////		                {
////		                	txtteamA.setText(firstteam);
////		                	txtteamB.setText(secondteam);
////		                }
//				        				     				  				    
//				 }				 				 				
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		 }
    }
	
	
}
