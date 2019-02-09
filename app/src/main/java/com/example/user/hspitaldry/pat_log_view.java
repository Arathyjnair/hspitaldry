package com.example.user.hspitaldry;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class pat_log_view extends Fragment {
TextView pattwid,pattvnme,pattvage,pattvdob,pattvemail,pattvphone,pattvpswd;

    public pat_log_view() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pat_log_view, container, false);
        pattwid = (TextView) v.findViewById(R.id.tt1);
        pattvnme = (TextView) v.findViewById(R.id.tt2);
        pattvage = (TextView) v.findViewById(R.id.tt3);
        pattvdob = (TextView) v.findViewById(R.id.tt4);
        pattvemail = (TextView) v.findViewById(R.id.tt5);
        pattvphone = (TextView) v.findViewById(R.id.tt6);
        pattvpswd=(TextView) v.findViewById(R.id.tt7);

        SharedPreferences patshred=getActivity().getSharedPreferences("pref",MODE_PRIVATE);
        String fragid=patshred.getString( "k1",null);
        String fragnme=patshred.getString("k2",null);
        String fragage=patshred.getString("k3",null);
        String fragdob=patshred.getString("k4",null);
        String frageml=patshred.getString("k5",null);
        String fragphne=patshred.getString("k6",null);
        String fragpswd=patshred.getString("k7",null);

        pattwid.setText("Id is : " +fragid);
        pattvnme.setText("Name is : " +fragnme);
        pattvage.setText("Age is : "+fragage);
        pattvdob.setText("Dob is : "+fragdob);
        pattvemail.setText("Email is : "+frageml);
        pattvphone.setText("Phone is : "+fragphne);

        return v;
    }



}
