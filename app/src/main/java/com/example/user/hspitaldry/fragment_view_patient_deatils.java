package com.example.user.hspitaldry;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_view_patient_deatils extends Fragment {

    EditText pat_idA,pat_nameA,pat_ageA,pat_dobA,pat_emailA,patphoneA,pat_oswdA;

    AsyncHttpClient client;
    RequestParams params;
    JSONObject obj1d;
    String urll="http://srishti-systems.info/projects/patient_diary/api/doc_viewpatientbyid.php";
    public fragment_view_patient_deatils() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_fragment_view_patient_deatils, container, false);

        pat_idA=v.findViewById(R.id.vwpatid);
        pat_nameA=v.findViewById(R.id.vwpatnme);
        pat_ageA=v.findViewById(R.id.vwpatage);
        pat_dobA=v.findViewById(R.id.vwpatdob);
        pat_emailA=v.findViewById(R.id.vwpateml);
        patphoneA=v.findViewById(R.id.vwpatphne);
        pat_oswdA=v.findViewById(R.id.vwpatpswd);
        client=new AsyncHttpClient();
        params=new RequestParams();

        SharedPreferences dctrpatview_d=getContext().getSharedPreferences("key",MODE_PRIVATE);
        String iddf=dctrpatview_d.getString("pattid",null);

        params.put("pat_id",iddf);
        Log.e("kkk","out");

        client.get(urll,params,new AsyncHttpResponseHandler()
        {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);

                try
                {
                    Log.e("kkk","in");
                    obj1d=new JSONObject(content);
                    if (obj1d.getString("status").equals("success"))
                    {
                        Log.e("kkk","inside");
                        JSONObject obj2d=obj1d.getJSONObject("PatientDeatils");
                        pat_nameA.setText("Patient Name : "+obj2d.getString("pat_name"));
                        pat_idA.setText("Patient Id : "+obj2d.getString("pat_id"));
                        pat_ageA.setText("Patient Age : "+obj2d.getString("pat_age"));
                        pat_dobA.setText("Patient Date of birth: "+obj2d.getString("pat_dob"));
                        pat_emailA.setText("Patient email id : " +obj2d.getString("pat_email"));
                        patphoneA.setText("Phone Number : "+obj2d.getString("pat_phone"));

                    }
                    else
                    {
                        Toast.makeText(getContext(), ""+obj1d.getString("status"),
                                Toast.LENGTH_SHORT).show();
                    }

                }
                catch (Exception e)
                {

                }
            }
        });



        return v;


    }


}
