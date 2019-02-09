package com.example.user.hspitaldry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

public class PatForgetPswd extends AppCompatActivity {
    AsyncHttpClient client;
    JSONObject jjobject;
    JSONArray jjarray;
    RequestParams params;
   EditText patforeml;
   Button patok;
   String url="http://srishti-systems.info/projects/patient_diary/api/patient_forget.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pat_forget_pswd);
        patforeml=findViewById(R.id.patforgeteml);
        patok=findViewById(R.id.patforok);
        client=new AsyncHttpClient();
        params=new RequestParams();
        patok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emaill=patforeml.getText().toString();
                params.put("email",emaill);
                client.get(url,params,new AsyncHttpResponseHandler(){
                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);
                        Log.e("ll","inside");
                        try {
                             jjobject=new JSONObject(content);
                             String s=jjobject.getString("Status");
                             if ((s.equals("success")))
                             {
                                 Toast.makeText(PatForgetPswd.this, "check your email", Toast.LENGTH_SHORT).show();
                             }
                             else
                             {
                                 Toast.makeText(PatForgetPswd.this, "failed", Toast.LENGTH_SHORT).show();
                             }

                    } catch (Exception e) {

                    }

                }

            });
        }
    });
}
}
