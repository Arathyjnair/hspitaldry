package com.example.user.hspitaldry;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
public class patient_fragmentallowedfud extends Fragment {

    RecyclerView patallowdfud;
    ArrayList<String> dietid,patid,docid,dietday,dietmrng,dietaftrnoon,dietevng,updatetym,updatedate;
   verticalAdapter adaptt;

    LinearLayoutManager layoutmanager;
    String url="http://srishti-systems.info/projects/patient_diary/api/patient_allowedfood.php?pat_id=1";
    AsyncHttpClient client;
    RequestParams params;
    JSONObject jalowdobj;
    JSONArray jalowdary;

    public patient_fragmentallowedfud() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v=inflater.inflate(R.layout.fragment_patient_fragmentallowedfud, container, false);

        patallowdfud=v.findViewById(R.id.recyclrvww);
        client=new AsyncHttpClient();
        params=new RequestParams();

        params.put("pat_id", "1");

        dietid=new ArrayList<String>();
        patid=new ArrayList<String>();
        docid=new ArrayList<String>();
        dietday=new ArrayList<String>();
        dietmrng=new ArrayList<String>();
        dietaftrnoon=new ArrayList<String>();
        dietevng=new ArrayList<String>();
        updatetym=new ArrayList<String>();
        updatedate=new ArrayList<String>();

        client.get(url,params,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                try {
                jalowdobj=new JSONObject(content);
                if(jalowdobj.getString("status").equals("success"))
                {
                jalowdary=jalowdobj.getJSONArray("Diet_details");
                for(int i=0;i<jalowdary.length();i++)
                {
                    JSONObject j2alowdobj=jalowdary.getJSONObject(i);

                    dietid.add("Diet id is : " + j2alowdobj.getString("diet_id"));
                    patid.add("Patient id is : " + j2alowdobj.getString("pat_id"));

                    docid.add("Doctor id is : " + j2alowdobj.getString("doc_id"));
                    dietday.add("Diet day is : " + j2alowdobj.getString("diet_day"));

                    dietmrng.add("Diet morning is : " + j2alowdobj.getString("diet_morning"));
                    dietaftrnoon.add("Diet Afternoon is : " + j2alowdobj.getString("diet_afternoon"));

                    dietevng.add("Diet evening is : " + j2alowdobj.getString("diet_evening"));
                    updatetym.add("Update time is : " + j2alowdobj.getString("update_time"));

                    updatedate.add("Update date is : " + j2alowdobj.getString("update_date"));
                    adaptt = new verticalAdapter(dietid);



                }
                }

                    layoutmanager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

                    patallowdfud.setAdapter(adaptt);
                    patallowdfud.setLayoutManager(layoutmanager);

                }
                catch (Exception e)
                {

                }
            }
        });

        return v;
    }

    class verticalAdapter extends RecyclerView.Adapter<verticalAdapter.MVH>
    {
        private List<String> vrtcllist;
        verticalAdapter(List<String> vrtcllist)
        {
            this.vrtcllist=dietid;
        }

        class MVH extends RecyclerView.ViewHolder
        {

            TextView dietidA,patidA,docidA,dietdayA,dietmorningA,dietaftrnoonA,dietEveningA,updtetymA,updatedate;

            public MVH(@NonNull View itemView) {
                super(itemView);
               dietidA = (TextView) itemView.findViewById(R.id.did);
                patidA = (TextView) itemView.findViewById(R.id.pid);
                docidA = (TextView) itemView.findViewById(R.id.doid);
                dietdayA = (TextView) itemView.findViewById(R.id.dietdy);
                dietmorningA = (TextView) itemView.findViewById(R.id.dietmng);
                dietaftrnoonA = (TextView) itemView.findViewById(R.id.dietaftrnn);
                dietEveningA = (TextView) itemView.findViewById(R.id.dietevngz);
                updtetymA = (TextView) itemView.findViewById(R.id.updttym);
                updatedate = (TextView) itemView.findViewById(R.id.udtedte);

            }
        }


        @NonNull
        @Override
        public MVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.patallowedfood, viewGroup, false);
            return new MVH(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MVH myViewHolder, int i) {

            myViewHolder.dietidA.setText(dietid.get(i));
            myViewHolder.patidA.setText(patid.get(i));
            myViewHolder.docidA.setText(docid.get(i));
            myViewHolder.dietdayA.setText(dietday.get(i));
            myViewHolder.dietmorningA.setText(dietmrng.get(i));
            myViewHolder.dietaftrnoonA.setText(dietaftrnoon.get(i));
            myViewHolder.dietEveningA.setText(dietevng.get(i));
            myViewHolder.updtetymA.setText(updatetym.get(i));
            myViewHolder.updatedate.setText(updatedate.get(i));



        }

        @Override
        public int getItemCount() {
            return dietid.size();
        }


    }

}
