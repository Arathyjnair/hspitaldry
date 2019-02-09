package com.example.user.hspitaldry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScrn extends AppCompatActivity {
   TextView tvw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_scrn);
        tvw=findViewById(R.id.txtvw1);

        Thread background=new Thread()
        {
            public  void run()
            {
                try
                {


                    Animation fad = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                    tvw.startAnimation(fad);


                    sleep(5000);
                    Intent i = new Intent(SplashScrn.this, dctrpat.class);
                    startActivity(i);
                    finish();
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }


            }
        };

        background.start();
    }
}
