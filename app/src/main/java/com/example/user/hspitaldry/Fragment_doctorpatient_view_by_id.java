package com.example.user.hspitaldry;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_doctorpatient_view_by_id extends Fragment {
    EditText dctridd,patntidd;
    Button doctorbtn;
    public Fragment_doctorpatient_view_by_id() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_fragment_doctorpatient_view_by_id, container, false);
        dctridd=v.findViewById(R.id.edt1);
        patntidd=v.findViewById(R.id.edt2);
        doctorbtn=v.findViewById(R.id.dctrokkl);
        doctorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idd=dctridd.getText().toString();
                String uff=patntidd.getText().toString();
                SharedPreferences dctrciewpres=getContext().getSharedPreferences("key",MODE_PRIVATE);
                SharedPreferences.Editor ed=dctrciewpres.edit();
                ed.putString("docpattid",idd);
                ed.putString("doctordocid",uff);
                ed.commit();

                fragment_doc_view_prescribed_patient ptt=new fragment_doc_view_prescribed_patient();
                android.support.v4.app.FragmentTransaction fraggg
                        = getFragmentManager().beginTransaction();
                fraggg.replace(R.id.frame1,ptt);
                fraggg.commit();

            }
        });

        return v;
    }

}
