package com.example.jsondemo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				new Thread(){
					@Override
					public void run() {
					
						String result="";
						String id="";
						List<String> list=new ArrayList<String>();
						HttpClient httpClient= new DefaultHttpClient();
						HttpPost post=new HttpPost("http://192.168.1.231:8080/transportservice/type/jason/action/GetTrafficLightConfigAction.do");
						
						HttpResponse httpResponse;
						
						try {
							post.setEntity(new StringEntity("{\"TrafficLightId\":" +  Integer.valueOf(5) + "}"));
							httpResponse = httpClient.execute(post);
							if (httpResponse.getStatusLine().getStatusCode()==200) {
								result=EntityUtils.toString(httpResponse.getEntity());
							}
							JSONObject jsonObject=new JSONObject(result);
							String jsonArray=jsonObject.getString("serverinfo");
							jsonObject=new JSONObject(jsonArray);
							Iterator<String> iterator=jsonObject.keys();
							while (iterator.hasNext()) {
								String key= iterator.next();
								String value= jsonObject.getString(key);
								list.add(value);
							}
							System.out.println(jsonArray);
							Log.i("did",list.get(1));

							
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} finally{
							httpClient.getConnectionManager().shutdown();
						}
						
						
					}
				}.start();
			}
		});
        

    }


    

}
