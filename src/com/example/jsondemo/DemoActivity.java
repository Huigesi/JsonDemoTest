package com.example.jsondemo;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DemoActivity extends Activity{
	private Button button1;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo);
		button1=(Button)findViewById(R.id.button1);
		textView=(TextView)findViewById(R.id.textView1);
		button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				new Thread(){
					@Override
					public void run() {
						String result="";
						String serverinfo="";
							DefaultHttpClient mHttpClient = new DefaultHttpClient();
							HttpPost mPost = new HttpPost("http://192.168.1.231:8080/transportservice/type/jason/action/"+"GetTrafficLightConfigAction.do");
							try {
								mPost.setEntity(new StringEntity("{\"TrafficLightId\":" +  Integer.valueOf(5) + "}"));
								HttpResponse response = mHttpClient.execute(mPost);
								if (response.getStatusLine().getStatusCode()==200) {
									result=EntityUtils.toString(response.getEntity());
								}
										/***********************/
										//将响应的json字符串转换为JSONObject对象
										JSONObject jsonObject=new JSONObject(result);
										serverinfo = jsonObject.getString("serverinfo");
										System.out.println("serverinf的值："+serverinfo);
										Log.i("ss", serverinfo);
										/***********************/
								}
							catch (ClientProtocolException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ConnectTimeoutException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InterruptedIOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					
				}.start();
			}
		});
		
	}
	

}
