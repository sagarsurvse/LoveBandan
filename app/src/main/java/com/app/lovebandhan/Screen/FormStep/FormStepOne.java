package com.app.lovebandhan.Screen.FormStep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.app.lovebandhan.R;
import com.google.android.material.textfield.TextInputEditText;

public class FormStepOne extends AppCompatActivity {
    private static final String TAG = "App Tag";
    TextInputEditText Fname,Lname;
    Spinner SpinProfile,SpinGender,SpinReligion,SpinCommunity;
    Button Btn_continue;

    ArrayAdapter<CharSequence> adapterSpinProfile;
    ArrayAdapter<CharSequence> adapterSpinGender;
    ArrayAdapter<CharSequence> adapterSpinReligion;
    ArrayAdapter<CharSequence> adapterSpinCommunity;


    String[] profileArray = {"brother", "sister","self"};
    String[] genderarray = {"male", "female"};
    String[] religinArray = {"hindu", "budist","muslim","critin","none"};
    String[] commityarray = {"test1", "test2","test3","test4"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_step_one);

        Fname = findViewById(R.id.f_name);
        Lname = findViewById(R.id.l_name);
        SpinProfile = findViewById(R.id.spinner_profile_for);
        SpinGender = findViewById(R.id.spinner_gender);
        SpinReligion = findViewById(R.id.spinner_religion);
        SpinCommunity = findViewById(R.id.spinner_community);
        Btn_continue = findViewById(R.id.id_continue);


        // for profile
        adapterSpinProfile =    new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,profileArray);
        adapterSpinProfile.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinProfile.setAdapter(adapterSpinProfile);

       //for  gender
        adapterSpinGender =    new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,genderarray);
        adapterSpinGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinGender.setAdapter(adapterSpinGender);

        //for  religion
        adapterSpinReligion =    new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,religinArray);
        adapterSpinReligion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinReligion.setAdapter(adapterSpinReligion);

        //for  community
        adapterSpinCommunity =    new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,commityarray);
        adapterSpinCommunity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinCommunity.setAdapter(adapterSpinCommunity);



        Btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Fname: "+Fname.getText().toString()+" Lname: "+Lname.getText().toString()+" SpinProfile "+SpinProfile.getSelectedItem().toString()
                +" SpinGender: "+SpinGender.getSelectedItem().toString()+" SpinReligion : "+SpinReligion.getSelectedItem().toString()+ " SpinCommunity: "+SpinCommunity.getSelectedItem().toString());
            }
        });


    }
}