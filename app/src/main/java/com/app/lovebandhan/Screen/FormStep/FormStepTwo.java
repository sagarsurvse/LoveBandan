package com.app.lovebandhan.Screen.FormStep;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;

import com.app.lovebandhan.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FormStepTwo extends AppCompatActivity {
    TextInputEditText mail,mobile_no,date_of_birth;
    Button Btn_Continue;
    final Calendar myCalendar= Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_step_two);

        String fname = getIntent().getStringExtra("fname");
        String lname = getIntent().getStringExtra("lname");
        String profile = getIntent().getStringExtra("profile");
        String religin = getIntent().getStringExtra("religion");
        String gender = getIntent().getStringExtra("gender");
        String community = getIntent().getStringExtra("community");

        mail = findViewById(R.id.email_id);
        mobile_no = findViewById(R.id.phone_no);
        date_of_birth = findViewById(R.id.birth_date);

        Btn_Continue = findViewById(R.id.create_account);


        Btn_Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mail.getText().toString().isEmpty() || !mobile_no.getText().toString().isEmpty() ){
                    Intent pass = new Intent(getApplicationContext(),FormStepThree.class);
                    pass.putExtra("fname",fname);
                    pass.putExtra("lname",lname);
                    pass.putExtra("profile",profile);
                    pass.putExtra("religion",religin);
                    pass.putExtra("gender",gender);
                    pass.putExtra("community",community);
                    pass.putExtra("gmail",mail.getText().toString());
                    pass.putExtra("mobile_no",mobile_no.getText().toString());
                    pass.putExtra("date_of_birth",date_of_birth.getText().toString());
                    startActivity(pass);

                }
                else {

                    mail.setError("please enter the mail");
                    mobile_no.setError("please enter the mobile no");

                }

                 }
        });



        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);

                String myFormat="MM/dd/yy";
                SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
                date_of_birth.setText(dateFormat.format(myCalendar.getTime()));
            }
        };

        date_of_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(FormStepTwo.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

    }
}