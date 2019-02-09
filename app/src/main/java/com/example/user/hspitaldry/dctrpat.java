package com.example.user.hspitaldry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class dctrpat extends AppCompatActivity {
    ImageView dctrimg, patientimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dctrpat);
        dctrimg = (ImageView) findViewById(R.id.dctrid);
        patientimage = (ImageView) findViewById(R.id.patentid);
    }


    public void patntclick(View view) {
        Intent in = new Intent(dctrpat.this, patientlog.class);
        startActivity(in);
    }


    public void dctrclick(View view) {
        Intent yt=new Intent(dctrpat.this,doctor_login.class);
        startActivity(yt);
    }
}
