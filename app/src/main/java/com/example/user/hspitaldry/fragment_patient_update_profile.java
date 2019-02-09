package com.example.user.hspitaldry;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_patient_update_profile extends Fragment {
    AsyncHttpClient client;
    RequestParams params;
    JSONObject patjsonobj;
    TextInputEditText patupname, patupphone, patuppatid, patupage, patupdob;
    Button patokky;
    int upday,upmonth,upyear,uptemp;


    String url = "http://srishti-systems.info/projects/patient_diary/api/patient_updateprofile.php?name=sreelakshmi&phone=1425369685&pat_id=192&age=26&dob=21.10.1993";


    public fragment_patient_update_profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_patient_update_profile, container, false);
        client = new AsyncHttpClient();
        params = new RequestParams();
        patupname = v.findViewById(R.id.ptnmee);
        patupphone = v.findViewById(R.id.ptphn);
        patuppatid = v.findViewById(R.id.ptidd);
        patupage = v.findViewById(R.id.ptage);
        patupdob = v.findViewById(R.id.ptdob);
         patokky=v.findViewById(R.id.okkkkk);
         patupdob.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Calendar ca=Calendar.getInstance();
                 upday=ca.get(Calendar.DAY_OF_MONTH);
                 upmonth=ca.get(Calendar.MONTH);
                 upyear=ca.get(Calendar.YEAR);
                 uptemp=ca.get(Calendar.YEAR);

                 DatePickerDialog DP=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                     @Override
                     public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                     {
                         patupdob.setText(dayOfMonth+" : "+(month+1)+" : "+year);
                         int flag=year;
                         int upage=uptemp-year;
                         patupage.setText(""+upage);


                     }
                 },upyear,upmonth,upday);


                 DP.show();


             }
         });
        patokky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String nmeee = patupname.getText().toString();
                String phnn = patupphone.getText().toString();
                String patidd = patuppatid.getText().toString();
                String agee = patupage.getText().toString();
                String dobb = patupdob.getText().toString();


                params.put("name", nmeee);
                params.put("phone", phnn);
                params.put("pat_id", patidd);
                params.put("age", agee);
                params.put("dob", dobb);

                client.get(url, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);
                        try {
                            patjsonobj = new JSONObject(content);
                            if (patjsonobj.getString("status").equals("success")) {
                                Toast.makeText(getActivity(), "succesfully updated", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "not succesfully updated", Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e) {

                        }

                    }

                });

            }

        });
        return  v;
    }
}



