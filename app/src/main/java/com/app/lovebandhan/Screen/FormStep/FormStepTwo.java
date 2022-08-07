package com.app.lovebandhan.Screen.FormStep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.app.lovebandhan.R;
import com.google.android.material.textfield.TextInputEditText;

public class FormStepTwo extends AppCompatActivity {
    TextInputEditText ed_email,phone_no,birth_date;
    ArrayAdapter<CharSequence> adapterAge;
    ArrayAdapter<CharSequence> adapterSex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_step_two);
    }
}