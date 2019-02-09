package com.example.user.hspitaldry;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragchngepswd extends Fragment {
AsyncHttpClient client;
RequestParams params;
JSONObject jobnjj;
JSONArray jaryy;
 TextView patnttid;
 TextInputEditText patnwpswdd,patoddpswd;
 Button sbmit;
    String url="http://srishti-systems.info/projects/patient_diary/api/patient_changepassword.php";
    public fragchngepswd() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragchngepswd, container, false);
        patnwpswdd=(TextInputEditText)v.findViewById(R.id.patnewpswd);
        patoddpswd=(TextInputEditText)v.findViewById(R.id.patoldpswd);
        sbmit=v.findViewById(R.id.patchngsbt);
        patnttid=v.findViewById(R.id.patchngid);
        client=new AsyncHttpClient();
        params=new RequestParams();

        SharedPreferences patshred=getActivity().getSharedPreferences("pref",MODE_PRIVATE);

        patnttid.setText(""+patshred.getString("k1",null));
        sbmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("lll","outside");
                params.put("newpsd", patnwpswdd.getText().toString());
                params.put("oldpsd", patoddpswd.getText().toString());
                params.put("pat_id",patnttid.getText().toString());
                client.get(url, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);
                        try {
                            Log.e("lll","inside");
                            jobnjj = new JSONObject(content);
                            if (jobnjj.getString("status").equals("success")) {
                                Log.e("lll","lol");
                                Toast.makeText(getActivity(), ""+jobnjj.getString("status").equals("success"), Toast.LENGTH_SHORT).show();
                                pat_log_view pf=new pat_log_view();
                                android.support.v4.app.FragmentTransaction homeFragmentTransaction
                                        = getFragmentManager().beginTransaction();
                                homeFragmentTransaction.replace(R.id.frame,pf);
                                homeFragmentTransaction.commit();
                            } else {
                                Log.e("lll","hhjj");
                                Toast.makeText(getActivity(), ""+jobnjj.getString("status").equals("success"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                        }
                    }
                });
            }
        });

        return v;
    }

}
