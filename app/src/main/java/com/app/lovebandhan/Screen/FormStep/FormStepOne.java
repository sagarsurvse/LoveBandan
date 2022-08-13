package com.app.lovebandhan.Screen.FormStep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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



    String[] profileArray = {"Self", "Son","Daughter","Brother", "Sister", "Friend", "Relative"};
    String[] genderarray = {"Male", "Female"};
    String[] religinArray = {"Hindu", "Christian","Muslim","Jain","Sikh", "Buddist", "Parsi", "Jewish","Other"};
    String[] commityarray = {"Hindi", "Marathi","Panjabi","Gujrati", "Urdu","Telugu","Kannada","Tamil","Odia","Marwari","Aka","Arabic","Arunachali","Assamese","Awadhi","Baluchi","Bhojpuri",
                             "Bhutia","Brahui","Chattisgaehi","Chinese","Coorgi","Dorgi","French","Garhwali","Garo","Haryanvi","Himachali/Pahari","Hindko","Kakbarak","Kanauji","Kashmiri",
                             "Khandesi","Khasi","Konkani","Koshali","Kumaoni","Kutchi","Ladakhi","Lepcha","Magahi","Maithili","Malay","Malayalam","Manipuri","Miji",
                             "Mizo","Monpa","Nepali","Pashto","Persian","Rajasthani","Russsian","Sanskrit","Santhsli","Seraiki","Sindhi","Sinhala","Sourashtra","Other"};
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
                if (!Fname.getText().toString().isEmpty() || !Lname.getText().toString().isEmpty() ){
                    Intent pass = new Intent(getApplicationContext(),FormStepTwo.class);
                    pass.putExtra("fname",Fname.getText().toString());
                    pass.putExtra("lname",Lname.getText().toString());
                    pass.putExtra("profile",SpinProfile.getSelectedItem().toString());
                    pass.putExtra("religion",SpinReligion.getSelectedItem().toString());
                    pass.putExtra("gender",SpinGender.getSelectedItem().toString());
                    pass.putExtra("community",SpinCommunity.getSelectedItem().toString());
                    startActivity(pass);
                }
                else {
                    Fname.setError("please enter the name");
                }
                Log.d(TAG,"Fname: "+Fname.getText().toString()+" Lname: "+Lname.getText().toString()+" SpinProfile "+SpinProfile.getSelectedItem().toString()
                +" SpinGender: "+SpinGender.getSelectedItem().toString()+" SpinReligion : "+SpinReligion.getSelectedItem().toString()+ " SpinCommunity: "+SpinCommunity.getSelectedItem().toString());


            }
        });


    }
}