package com.example.user.hspitaldry;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

public class doctor_login extends AppCompatActivity {
    AsyncHttpClient client;
    RequestParams params;
    EditText docemail, docpasssword;
    JSONObject jobj;

    Button llogin;
    TextView regir;
    TextView forgtpswdss;

    String url="http://srishti-systems.info/projects/patient_diary/api/patient_login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);

            client = new AsyncHttpClient();
            params = new RequestParams();
        docemail = findViewById(R.id.email);
        docpasssword = findViewById(R.id.pswd);
        regir=findViewById(R.id.patentreg);
        forgtpswdss=findViewById(R.id.patlogfrgt);

        llogin = findViewById(R.id.btnn);
        llogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String emll = docemail.getText().toString();

                    String pswdd = docpasssword.getText().toString();

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
                                Toast.makeText(doctor_login.this, ""+str, Toast.LENGTH_SHORT).show();
                                if(str.equals("Success"))
                                {
//
                                    Toast.makeText(doctor_login.this, "loop", Toast.LENGTH_SHORT).show();

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
                                    Intent intent=new Intent(doctor_login.this,Main3Activity.class);
                                    startActivity(intent);

                                }

                            } catch (Exception e) {
                            }
                        }
                    });
                }
            });
    }
}
