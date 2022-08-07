package com.app.lovebandhan.Screen.FormStep;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.app.lovebandhan.R;
import com.google.android.material.textfield.TextInputEditText;

public class FormStepThree extends AppCompatActivity {
    Spinner SpinLive,SpinMaterial,SpinDiet,SpinHeight,SpinSubCommunity;
    Button Btn_Continue;


    ArrayAdapter<CharSequence> adapterSpinlive;
    ArrayAdapter<CharSequence> adapterSpinMeterial;
    ArrayAdapter<CharSequence> adapterSpinDiet;
    ArrayAdapter<CharSequence> adapterSpinHeight;
    ArrayAdapter<CharSequence> adapterSpinCommunity;


    String[] liveArray = {"live1", "live2","live3"};
    String[] meterialarray = {"m1", "m2"};
    String[] dietArray = {"d1", "d2","d3","d4","d5"};
    String[] heightarray = {"a1", "a2","a3","a4"};
    String[] subcommunity = {"c1", "c2","c3","c4"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_step_three);

        String fname = getIntent().getStringExtra("fname");
        String lname = getIntent().getStringExtra("lname");
        String profile = getIntent().getStringExtra("profile");
        String religin = getIntent().getStringExtra("religion");
        String gender = getIntent().getStringExtra("gender");
        String community = getIntent().getStringExtra("community");
        String strgmail = getIntent().getStringExtra("gmail");
        String strmobile_no = getIntent().getStringExtra("mobile_no");
        String dateofbirth = getIntent().getStringExtra("date_of_birth");



        SpinLive = findViewById(R.id.spinner_live);
        SpinMaterial = findViewById(R.id.spinner_materil);
        SpinDiet = findViewById(R.id.spinner_diet);
        SpinHeight = findViewById(R.id.spinner_height);
        SpinSubCommunity = findViewById(R.id.spinner_sub);


        // for profile
        adapterSpinlive =    new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,liveArray);
        adapterSpinlive.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinLive.setAdapter(adapterSpinlive);

        //for  gender
        adapterSpinMeterial =    new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,meterialarray);
        adapterSpinMeterial.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinMaterial.setAdapter(adapterSpinMeterial);

        //for  religion
        adapterSpinDiet =    new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,dietArray);
        adapterSpinDiet.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinDiet.setAdapter(adapterSpinDiet);

        //for  community
        adapterSpinHeight =    new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,heightarray);
        adapterSpinHeight.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinHeight.setAdapter(adapterSpinHeight);

        //for  community
        adapterSpinCommunity =    new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,subcommunity);
        adapterSpinCommunity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinSubCommunity.setAdapter(adapterSpinCommunity);


        Btn_Continue = findViewById(R.id.three_continue);
        Btn_Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pass = new Intent(getApplicationContext(), FormStepFour.class);
                pass.putExtra("fname",fname);
                pass.putExtra("lname",lname);
                pass.putExtra("profile",profile);
                pass.putExtra("religion",religin);
                pass.putExtra("gender",gender);
                pass.putExtra("community",community);
                pass.putExtra("gmail",strgmail);
                pass.putExtra("mobile_no",strmobile_no);
                pass.putExtra("date_of_birth",dateofbirth);
                pass.putExtra("live",SpinLive.getSelectedItem().toString());
                pass.putExtra("Meterial",SpinLive.getSelectedItem().toString());
                pass.putExtra("diet",SpinLive.getSelectedItem().toString());
                pass.putExtra("height",SpinLive.getSelectedItem().toString());
                pass.putExtra("sub_community",SpinLive.getSelectedItem().toString());
                startActivity(pass);
            }
        });


    }
}