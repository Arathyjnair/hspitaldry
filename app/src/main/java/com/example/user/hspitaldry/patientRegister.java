package com.example.user.hspitaldry;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class patientRegister extends AppCompatActivity {
    AsyncHttpClient client;
    JSONArray jarray;
    JSONObject jobject;
    RequestParams params;

    Button subb;
    EditText registername,registeremai,registerage,registerphone,registerdob,registerpswd;
   String url="http://srishti-systems.info/projects/patient_diary/api/patient_register.php";
    private DatePicker datePicker;

    private Calendar myCalendar ;
    private TextView dateView;

    private int Year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);
        myCalendar = Calendar.getInstance();
        client = new AsyncHttpClient();
        params = new RequestParams();

        registername = findViewById(R.id.patregnmee);
        registeremai = findViewById(R.id.patregeml);
        registerage = findViewById(R.id.patregage);
        registerphone = findViewById(R.id.patregphne);
        registerdob = findViewById(R.id.patregdob);
        registerpswd = findViewById(R.id.patregpswd);
        subb = findViewById(R.id.Registerbtnn);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabel();
            }

        };
        registerdob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                new DatePickerDialog(patientRegister.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        subb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sname = registername.getText().toString();

                String semail = registeremai.getText().toString();
                String sage = registerage.getText().toString();
                String sphone = registerphone.getText().toString();
                String sdob = registerdob.getText().toString();
                String spswd = registerpswd.getText().toString();
                params.put("name", sname);
                params.put("email", semail);
                params.put("age", sage);
                params.put("phone", sphone);
                params.put("dob", sdob);
                params.put("password", spswd);
                Log.e("lll", "outside");
                client.get(url, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);
                        try {

                            jobject = new JSONObject(content);
//                            Log.e("lll", "content");

                            String s = jobject.getString("status");
                            Log.e("lll", "inside");

                            if (s.equals("email exist")) {
                                Toast.makeText(patientRegister.this, "already registered", Toast.LENGTH_SHORT).show();

                            } else if (s.equals("success")) {
                                Toast.makeText(patientRegister.this, "success", Toast.LENGTH_SHORT).show();
                                Intent obj = new Intent(patientRegister.this, patientlog.class);
                                startActivity(obj);
                            }
                        } catch (Exception e) {
                        }


                    }
                });
            }
        });
    }
    private void updateLabel() {

        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);


        registerdob.setText(sdf.format(myCalendar.getTime()));
        registerage.setText(Integer.toString(calculateAge(myCalendar.getTimeInMillis())));

    }

    int calculateAge(long date){

        Date c = Calendar.getInstance().getTime();

        String myFormat = "dd/MM/yyyy";

        SimpleDateFormat df = new SimpleDateFormat(myFormat,Locale.US);
        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(date);
        String formattedDate = df.format(c);
        Toast.makeText(this, ""+formattedDate, Toast.LENGTH_LONG).show();
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if(today.get(Calendar.YEAR) < dob.get(Calendar.YEAR)){
            age--;
        }
        return age;
    }










}
