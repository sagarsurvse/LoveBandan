package com.app.lovebandhan.Screen.FormStep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.app.lovebandhan.R;
import com.google.android.material.textfield.TextInputEditText;

public class FormStepTwo extends AppCompatActivity {
    TextInputEditText mail,mobile_no,date_of_birth;
    Button Btn_Continue;
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
        });


    }
}