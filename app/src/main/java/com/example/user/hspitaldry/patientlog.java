package com.example.user.hspitaldry;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

public class patientlog extends AppCompatActivity {
    AsyncHttpClient client;
    RequestParams params;
    EditText email, passsword;
    JSONObject jobj;

    Button login;
    TextView reg;
    TextView forgtpswd;

    String url="http://srishti-systems.info/projects/patient_diary/api/patient_login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientlog);
        client = new AsyncHttpClient();
        params = new RequestParams();
        email = findViewById(R.id.email);
        passsword = findViewById(R.id.pswd);
        reg=findViewById(R.id.patentreg);
        forgtpswd=findViewById(R.id.patlogfrgt);

        login = findViewById(R.id.btnn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emll = email.getText().toString();

                String pswdd = passsword.getText().toString();

                params.put("email", emll);
                params.put("password", pswdd);
               // Log.e("lll","outside");
                client.get(url, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);
                        try {
                          //  Log.e("lll","inside");
                            jobj = new JSONObject(content);
                            String str = jobj.getString("status");
                            Toast.makeText(patientlog.this, ""+str, Toast.LENGTH_SHORT).show();
                            if(str.equals("Success"))
                            {
//
                               Toast.makeText(patientlog.this, "loop", Toast.LENGTH_SHORT).show();

//
                                JSONObject obj2=jobj.getJSONObject("Patient_data");
                                 String patntid=obj2.getString("pat_id");
                                 String patnme=obj2.getString("pat_name");
                                String patage=obj2.getString("pat_age");
                                String patdob=obj2.getString("pat_dob");
                                String pateml=obj2.getString("pat_email");
                                String patphone=obj2.getString("pat_phone");
                                String patpswd=obj2.getString("pat_password");



                                SharedPreferences patshred=getApplicationContext().getSharedPreferences("pref",MODE_PRIVATE);
                                SharedPreferences.Editor edd=patshred.edit();
                                edd.putString("k1",patntid);
                                edd.putString("k2",patnme);
                                edd.putString("k3",patage);
                                edd.putString("k4",patdob);
                                edd.putString("k5",pateml);
                                edd.putString("k6",patphone);
                                edd.putString("k7",patpswd);
                                edd.commit();
                                Intent intent=new Intent(patientlog.this,MainActivity.class);
                               startActivity(intent);

                            }

                        } catch (Exception e) {
                        }
                    }
                });
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(patientlog.this,patientRegister.class);
                startActivity(in);
            }
        });

        forgtpswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jk=new Intent(patientlog.this,PatForgetPswd.class);
                startActivity(jk);
            }
        });


        }


    }




