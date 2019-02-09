package com.example.user.hspitaldry;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_doctor_view_by_id extends Fragment {

    EditText doc_view_pat_id;
    Button pat_btnn;
    public fragment_doctor_view_by_id() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_fragment_doctor_view_by_id, container, false);

        doc_view_pat_id=v.findViewById(R.id.patidd);
        pat_btnn=v.findViewById(R.id.pakokkey);

        pat_btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Log.e("ooo","too");
                Toast.makeText(getActivity(), "cccc", Toast.LENGTH_SHORT).show();
                String idd=doc_view_pat_id.getText().toString();
                SharedPreferences dctrpatview_d=getContext().getSharedPreferences("key",MODE_PRIVATE);
                SharedPreferences.Editor ed=dctrpatview_d.edit();
                ed.putString("pattid",idd);
                ed.commit();
                Log.e("ooo","yoo");
                fragment_view_patient_deatils ty=new fragment_view_patient_deatils();
                android.support.v4.app.FragmentTransaction hmfrag
                        = getFragmentManager().beginTransaction();
                hmfrag.replace(R.id.frame1,ty);
                hmfrag.commit();




                Log.e("ooo","ppp");
            }

        });



        return v;
    }

}