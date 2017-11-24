package com.example.jsondemo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
						/*HttpPost request = new HttpPost("http://192.168.1.243:8080/transportservice/type/jason/action/GetTrafficLightConfigAciton.do");   
						// 先封装一个 JSON 对象   
						JSONObject param = new JSONObject();   
						try {
							param.put("TrafficLightId", "1");
							//param.put("password", "123456");   
							// 绑定到请求 Entry   
							StringEntity se = new StringEntity(param.toString());    
							request.setEntity(se);   
							// 发送请求   
							HttpResponse httpResponse = new DefaultHttpClient().execute(request);   
							// 得到应答的字符串，这也是一个 JSON 格式保存的数据   
							String retSrc = EntityUtils.toString(httpResponse.getEntity());   
							// 生成 JSON 对象   
							JSONObject result = new JSONObject( retSrc);   
							String token = (String)result.get("RedTime"); 
							Log.i("ds",token);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}   */
						String result="";
						
						HttpClient httpClient= new DefaultHttpClient();
						HttpGet post=new HttpGet("http://192.168.1.243:8080/transportservice/type/jason/action/GetAllSense.do");
						
						HttpResponse httpResponse;
						try {
							httpResponse = httpClient.execute(post);
							if (httpResponse.getStatusLine().getStatusCode()==200) {
								result=EntityUtils.toString(httpResponse.getEntity());
							}
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} finally{
							httpClient.getConnectionManager().shutdown();
						}
						List<String> list=new ArrayList<String>();
						try {
							JSONObject jsonObject=new JSONObject(result);
							JSONArray jsonArray=jsonObject.getJSONArray("serverinfo");
							for(int i=0;i<jsonArray.length();i++){
								list.add(jsonArray.getString(i));
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Log.i("ds",result);
						
					}
				}.start();
			}
		});
        

    }


    

}
