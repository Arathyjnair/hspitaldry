package com.example.user.hspitaldry;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_patient_view_prescription extends Fragment

{


    RecyclerView fragpat_view_prescrption;

    ArrayList<String> patpresid, patdocid, pattid, patpremedmrng, patpresmedaft, patpresmedeve, patpresdate, patprestime, patpresreport,
            patprestimeevng, patpresmedtymmrng, patpresmedtymaft, patpresmedeveng;

    Verticaladapter adapt;

    String url = "http://srishti-systems.info/projects/patient_diary/api/patient_viewprescription.php";

    AsyncHttpClient client;
    RequestParams params;
    JSONObject patj1obj;
    JSONArray patj1ary;

    LinearLayoutManager layutmanager;


    public fragment_patient_view_prescription() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_patient_view_prescription, container, false);

        fragpat_view_prescrption=v.findViewById(R.id.recyclrvww);


        client = new AsyncHttpClient();
        params = new RequestParams();

        params.put("doc_id", "1");
        params.put("pat_id", "1");

        patpresid = new ArrayList<String>();
        patdocid = new ArrayList<String>();

        pattid = new ArrayList<String>();
        patpremedmrng = new ArrayList<String>();

        patpresmedaft = new ArrayList<String>();
        patpresmedeve = new ArrayList<String>();

        patpresdate = new ArrayList<String>();
        patprestime = new ArrayList<String>();

        patpresreport = new ArrayList<String>();
        patprestimeevng = new ArrayList<String>();

        patpresmedtymmrng = new ArrayList<String>();
        patpresmedtymaft = new ArrayList<String>();

        patpresmedeveng = new ArrayList<String>();

        Log.e("hhh", "outside");

        client.get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);

                try {

                    patj1obj = new JSONObject(content);
                    Log.e("hhh", "inside");
                    if (patj1obj.getString("status").equals("success")) {
                        patj1ary = patj1obj.getJSONArray("Prescription_details");
                        for (int i = 0; i < patj1ary.length(); i++) {

                            JSONObject patj2obj = patj1ary.getJSONObject(i);

                            patpresid.add("Prescription id : " + patj2obj.getString("pres_id"));
                            patdocid.add("Doctor id : " + patj2obj.getString("doc_id"));

                            pattid.add("Patient id : " + patj2obj.getString("pat_id"));
                            patpremedmrng.add("Prescription medicine morning : " + patj2obj.getString("pres_medicine_mor"));

                            patpresmedaft.add("Prescription medicine Afternoon : " + patj2obj.getString("pres_medicine_aft"));
                            patpresmedeve.add("Prescription medicine Evening : " + patj2obj.getString("pres_medicine_eve"));

                            patpresdate.add("Prescription Date : " + patj2obj.getString("pres_date"));
                            patprestime.add("Prescription Time : " + patj2obj.getString("pres_time"));

                            patpresreport.add("Prescription Report : " + patj2obj.getString("pres_report"));
                            patprestimeevng.add("Prescription Time Evening : " + patj2obj.getString("pres_time_eve"));

                            patpresmedtymmrng.add("Prescription medicine Time morning : " + patj2obj.getString("pres_medicine_time_mor"));
                            patpresmedtymaft.add("Prescription medicine Time AfterNoon : " + patj2obj.getString("pres_medicine_time_aft"));

                            patpresmedeveng.add("Prescription medicine Time Evening : " + patj2obj.getString("pres_medicine_time_eve"));
                            adapt = new Verticaladapter(patpresid);




                        }
                    }
                    layutmanager= new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

                    fragpat_view_prescrption.setAdapter(adapt);
                    fragpat_view_prescrption.setLayoutManager(layutmanager);

                } catch (Exception e) {

                }
            }
        });
        return v;

    }

    class Verticaladapter extends RecyclerView.Adapter<Verticaladapter.MyViewHolder> {
        private List<String> vrticallist;

        Verticaladapter(List<String> vrticallist)


        {
            this.vrticallist = patpresid;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView presidvw, docidvw, patidvw, premedmorvw, premedaftvw, premedevngvw, predatevw, pretimevw, prereportvw, pretimeevngvw,
                    presmedtimemorningvw, presmedtimeaftervw, presmedtimeevngvw;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                presidvw = (TextView) itemView.findViewById(R.id.presid);
                docidvw = (TextView) itemView.findViewById(R.id.docid);
                patidvw = (TextView) itemView.findViewById(R.id.patid);
                premedmorvw = (TextView) itemView.findViewById(R.id.presmedinemrng);
                premedaftvw = (TextView) itemView.findViewById(R.id.presmedineaftr);
                premedevngvw = (TextView) itemView.findViewById(R.id.presmedineevng);
                predatevw = (TextView) itemView.findViewById(R.id.presdate);
                pretimevw = (TextView) itemView.findViewById(R.id.presctym);
                prereportvw = (TextView) itemView.findViewById(R.id.presreport);
                pretimeevngvw = (TextView) itemView.findViewById(R.id.prestymevng);
                presmedtimemorningvw = (TextView) itemView.findViewById(R.id.presmedicinetymmrng);
                presmedtimeaftervw = (TextView) itemView.findViewById(R.id.presmedicinetyaftr);
                presmedtimeevngvw = (TextView) itemView.findViewById(R.id.presmedicinetyevng);


            }
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.patient_view_prescription, viewGroup, false);
            return new MyViewHolder(itemview);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i)

        {
            myViewHolder.presidvw.setText(patpresid.get(i));
            myViewHolder.docidvw.setText(patdocid.get(i));
            myViewHolder.patidvw.setText(pattid.get(i));
            myViewHolder.premedmorvw.setText(patpremedmrng.get(i));
            myViewHolder.premedaftvw.setText(patpresmedaft.get(i));
            myViewHolder.premedevngvw.setText(patpresmedeve.get(i));
            myViewHolder.predatevw.setText(patpresdate.get(i));
            myViewHolder.pretimevw.setText(patprestime.get(i));
            myViewHolder.prereportvw.setText(patpresreport.get(i));
            myViewHolder.pretimeevngvw.setText(patprestimeevng.get(i));
            myViewHolder.presmedtimemorningvw.setText(patpresmedtymmrng.get(i));
            myViewHolder.presmedtimeaftervw.setText(patpresmedtymaft.get(i));
            myViewHolder.presmedtimeevngvw.setText(patpresmedeveng.get(i));


        }

        @Override
        public int getItemCount() {
            return patpresid.size();
        }


    }

}
