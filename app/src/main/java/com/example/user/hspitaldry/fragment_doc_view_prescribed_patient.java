package com.example.user.hspitaldry;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_doc_view_prescribed_patient extends Fragment {

    RecyclerView doc_recycler_view;
    ArrayList<String> docpres_id, docdc_id, docpat_id, docpres_medicine_mrng, docpres_medicine_aft,
            docpres_medicine_eve, doc_pres_date, doc_pres_tym, doc_pres_rpt, doc_pres_ty_evng,
            doc_pres_medicine_tym_mrng, doc_pres_medicine_tym_aft, doc_pres_medicine_tym_evng,
            doc_pat_name, doc_pat_age, doc_pat_dob, doc_pat_email, doc_pat_phone, doc_pat_password;

    vertcladpt adapter;


    String url = "http://srishti-systems.info/projects/patient_diary/api/doc_viewprescribedpatient.php";

    AsyncHttpClient client;
    RequestParams params;
    JSONObject jobjkk;
    JSONArray jarykk;

    LinearLayoutManager lk;

    public fragment_doc_view_prescribed_patient() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_doc_view_prescribed_patient, container, false);
        doc_recycler_view = v.findViewById(R.id.docrecyclr);


                                             SharedPreferences shred = getActivity().getSharedPreferences("pref", MODE_PRIVATE);

                                             String docpatidd = shred.getString("idkey", null);
                                             String docpatdocid = shred.getString("doccid", null);

                                             params.put("pat_id", docpatidd);
                                             params.put("doc_id", docpatdocid);

                client = new AsyncHttpClient();
                params = new RequestParams();

                docpres_id = new ArrayList<String>();
                docdc_id = new ArrayList<String>();

                docpat_id = new ArrayList<String>();
                docpres_medicine_mrng = new ArrayList<String>();

                docpres_medicine_aft = new ArrayList<String>();
                docpres_medicine_eve = new ArrayList<String>();

                doc_pres_date = new ArrayList<String>();
                doc_pres_tym = new ArrayList<String>();

                doc_pres_rpt = new ArrayList<String>();
                doc_pres_ty_evng = new ArrayList<String>();

                doc_pres_medicine_tym_mrng = new ArrayList<String>();
                doc_pres_medicine_tym_aft = new ArrayList<String>();

                doc_pres_medicine_tym_evng = new ArrayList<String>();
                doc_pat_name = new ArrayList<String>();

                doc_pat_age = new ArrayList<String>();
                doc_pat_dob = new ArrayList<String>();

                doc_pat_email = new ArrayList<String>();
                doc_pat_phone = new ArrayList<String>();

                doc_pat_password = new ArrayList<String>();


                client.get(url, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);
                        try {

                            jobjkk = new JSONObject(content);
                            Log.e("hhh", "inside");
                            if (jobjkk.getString("status").equals("success")) {
                                jarykk = jobjkk.getJSONArray("Prescription_details");
                                for (int i = 0; i < jarykk.length(); i++) {

                                    JSONObject patj2obj = jarykk.getJSONObject(i);

                                    docpres_id.add("Prescrription id is : " + patj2obj.getString("pres_id"));
                                    docdc_id.add("Prescrription id is : " + patj2obj.getString("doc_id"));


                                    docpat_id.add("Prescrription id is : " + patj2obj.getString("pat_id"));
                                    docpres_medicine_mrng.add("Prescrription id is : " + patj2obj.getString("pres_medicine_mor"));

                                    docpres_medicine_aft.add("Prescrription id is : " + patj2obj.getString("pres_medicine_aft"));
                                    docpres_medicine_eve.add("Prescrription id is : " + patj2obj.getString("pres_medicine_eve"));

                                    doc_pres_date.add("Prescrription id is : " + patj2obj.getString("pres_date"));
                                    doc_pres_tym.add("Prescrription id is : " + patj2obj.getString("pres_time"));

                                    doc_pres_rpt.add("Prescrription id is : " + patj2obj.getString("pres_report"));
                                    doc_pres_ty_evng.add("Prescrription id is : " + patj2obj.getString("pres_time_eve"));

                                    doc_pres_medicine_tym_mrng.add("Prescrription id is : " + patj2obj.getString("pres_medicine_time_mor"));
                                    doc_pres_medicine_tym_aft.add("Prescrription id is : " + patj2obj.getString("pres_medicine_time_aft"));

                                    doc_pres_medicine_tym_evng.add("Prescrription id is : " + patj2obj.getString("pres_medicine_time_eve"));
                                    doc_pat_name.add("Prescrription id is : " + patj2obj.getString("pat_name"));

                                    doc_pat_age.add("Prescrription id is : " + patj2obj.getString("pat_age"));
                                    doc_pat_dob.add("Prescrription id is : " + patj2obj.getString("pat_dob"));

                                    doc_pat_email.add("Prescrription id is : " + patj2obj.getString("pat_email"));
                                    doc_pat_phone.add("Prescrription id is : " + patj2obj.getString("pat_phone"));

                                    doc_pat_password.add("Prescrription id is : " + patj2obj.getString("pat_password"));
                                    adapter = new vertcladpt(docpres_id);

                                }
                            }
                            lk = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

                            doc_recycler_view.setAdapter(adapter);
                            doc_recycler_view.setLayoutManager(lk);
                        } catch (Exception e) {

                        }

                    }


                });
                return v;


    }


class vertcladpt extends RecyclerView.Adapter<vertcladpt.MyViewHolder> {
    private List<String> vrticallist;

    vertcladpt(List<String> vrticallist)



    {
        this.vrticallist = docpres_id;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView pres_idA,doc_idA,pat_idA,pres_medicine_morA,pres_medicine_aftA,pres_medicine_eveA,pres_dateA,
                            pres_timeA,pres_reportA,pres_time_eveA,pres_medicine_time_morA,
                    pres_medicine_time_aftA,pres_medicine_time_eveA,pat_nameA
                    ,pat_ageA,pat_dobA,pat_emailA,pat_phoneA,pat_passwordA;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pres_idA = (TextView) itemView.findViewById(R.id.docpresid);
            doc_idA= (TextView) itemView.findViewById(R.id.dctrdocid);

            pat_idA=(TextView) itemView.findViewById(R.id.docpatid);
            pres_medicine_morA=(TextView) itemView.findViewById(R.id.docpres_medicine_mor);

            pres_medicine_aftA=(TextView) itemView.findViewById(R.id.docpres_medicine_aft);
            pres_medicine_eveA=(TextView) itemView.findViewById(R.id.docpres_medicine_eve);

            pres_dateA=(TextView) itemView.findViewById(R.id.docpres_date);
            pres_timeA=(TextView) itemView.findViewById(R.id.docpres_time);

            pres_reportA=(TextView) itemView.findViewById(R.id.docpres_report);
            pres_time_eveA=(TextView) itemView.findViewById(R.id.docpres_time_eve);

            pres_medicine_time_morA=(TextView) itemView.findViewById(R.id.docpres_medicine_time_mor);
            pres_medicine_time_aftA=(TextView) itemView.findViewById(R.id.docpres_medicine_time_aft);

            pres_medicine_time_eveA=(TextView) itemView.findViewById(R.id.docpres_medicine_time_eve);
            pat_nameA=(TextView) itemView.findViewById(R.id.docpat_name);

            pat_ageA=(TextView) itemView.findViewById(R.id.docpat_age);
            pat_dobA=(TextView) itemView.findViewById(R.id.docpat_dob);

            pat_emailA=(TextView) itemView.findViewById(R.id.docpat_email);
            pat_phoneA=(TextView) itemView.findViewById(R.id.docpat_phone);

            pat_passwordA=(TextView) itemView.findViewById(R.id.docpat_password);


        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_doc_view_prescription_items, viewGroup, false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i)

    {
        myViewHolder.pres_idA.setText(docpres_id.get(i));
        myViewHolder.doc_idA.setText(docdc_id.get(i));

        myViewHolder.pat_idA.setText(docpat_id.get(i));
        myViewHolder.pres_medicine_morA.setText(docpres_medicine_mrng.get(i));

        myViewHolder.pres_medicine_aftA.setText(docpres_medicine_aft.get(i));
        myViewHolder.pres_medicine_eveA.setText(docpres_medicine_eve.get(i));

        myViewHolder.pres_dateA.setText(doc_pres_date.get(i));
        myViewHolder.pres_timeA.setText(doc_pres_tym.get(i));

        myViewHolder.pres_reportA.setText(doc_pres_rpt.get(i));
        myViewHolder.pres_time_eveA.setText(doc_pres_ty_evng.get(i));

        myViewHolder.pres_medicine_time_morA.setText(doc_pres_medicine_tym_mrng.get(i));
        myViewHolder.pres_medicine_time_aftA.setText(doc_pres_medicine_tym_aft.get(i));

        myViewHolder.pres_medicine_time_eveA.setText(doc_pres_medicine_tym_evng.get(i));
        myViewHolder.pat_nameA.setText(doc_pat_name.get(i));

        myViewHolder.pat_ageA.setText(doc_pat_age.get(i));
        myViewHolder.pat_dobA.setText(doc_pat_dob.get(i));

        myViewHolder.pat_emailA.setText(doc_pat_email.get(i));
        myViewHolder.pat_phoneA.setText(doc_pat_phone.get(i));

        myViewHolder.pat_passwordA.setText(doc_pat_password.get(i));



    }

    @Override
    public int getItemCount() {
        return docpres_id.size();
    }




}

}

