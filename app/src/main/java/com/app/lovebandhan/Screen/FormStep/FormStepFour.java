package com.app.lovebandhan.Screen.FormStep;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.app.lovebandhan.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FormStepFour extends AppCompatActivity {
    private static final String TAG = "Fourth form";
    Spinner SpinSchool,SpinWork,SpinAs,SpinIncome;
    Button Btn_Continue;


    ArrayAdapter<CharSequence> adapterSpinHigheSchool;
    ArrayAdapter<CharSequence> adapterSpinwork;
    ArrayAdapter<CharSequence> adapterSpinAs;
    ArrayAdapter<CharSequence> adapterSpinIncome;

    String[] schoolArray = {"bsc", "be","bcom"};
    String[] workarray = {"softwaare developer", "m.enginner","clerk","dilevery boy"};
    String[] AsArray = {"zomato", "swigy","google","amazon","jio"};
    String[] Incomearray = {"1.5 cr", "2 cr","10000","100000000"};


    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_step_four);

        FirebaseApp.initializeApp(this);

        fstore = FirebaseFirestore.getInstance();


        String fname = getIntent().getStringExtra("fname");
        String lname = getIntent().getStringExtra("lname");
        String profile = getIntent().getStringExtra("profile");
        String religin = getIntent().getStringExtra("religion");
        String gender = getIntent().getStringExtra("gender");
        String community = getIntent().getStringExtra("community");
        String strgmail = getIntent().getStringExtra("gmail");
        String strmobile_no = getIntent().getStringExtra("mobile_no");
        String dateofbirth = getIntent().getStringExtra("date_of_birth");
        String material = getIntent().getStringExtra("Meterial");
        String diet = getIntent().getStringExtra("diet");
        String height = getIntent().getStringExtra("height");
        String live = getIntent().getStringExtra("live");
        String sub_community = getIntent().getStringExtra("sub_community");


        SpinSchool = findViewById(R.id.spinner_higher);
        SpinWork = findViewById(R.id.spinner_work);
        SpinIncome = findViewById(R.id.spinner_as);
        SpinAs = findViewById(R.id.spinner_income);


        // for profile
        adapterSpinHigheSchool =    new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,schoolArray);
        adapterSpinHigheSchool.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinSchool.setAdapter(adapterSpinHigheSchool);

        //for  gender
        adapterSpinwork =    new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,workarray);
        adapterSpinwork.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinWork.setAdapter(adapterSpinwork);

        //for  religion
        adapterSpinAs =    new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,AsArray);
        adapterSpinAs.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinAs.setAdapter(adapterSpinAs);

        //for  community
        adapterSpinIncome =    new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,Incomearray);
        adapterSpinIncome.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinIncome.setAdapter(adapterSpinIncome);



        Btn_Continue = findViewById(R.id.final_Btn);
        Btn_Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (strgmail != null || strmobile_no != null || gender != null ){
                    ProgressDialog progressDialog
                            = new ProgressDialog(FormStepFour.this);
                    progressDialog.setTitle("Account Creating...");
                    progressDialog.show();

                    DocumentReference documentReference = fstore.collection("Users").document(strmobile_no);
                    Map<String, Object> user = new HashMap<>();
                    user.put("First_Name", fname);
                    user.put("Last_Name",  lname);
                    user.put("ProfileFor",profile);
                    user.put("Religion",religin);
                    user.put("Gender",gender);
                    user.put("Community",community);
                    user.put("Gmail",strgmail);
                    user.put("Mobile_Number",strmobile_no);
                    user.put("Material",material);
                    user.put("Diet",diet);
                    user.put("Height",height);
                    user.put("live",live);
                    user.put("Sub_Community",sub_community);
                    user.put("Highest_Qualification",SpinSchool.getSelectedItem().toString());
                    user.put("work",SpinWork.getSelectedItem().toString());
                    user.put("As",SpinAs.getSelectedItem().toString());
                    user.put("Income",SpinIncome.getSelectedItem().toString());
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "onSuccess: order is created ");
                            progressDialog.dismiss();

                           /* Intent i = new Intent(RegisterActivity.this, CustomerLogin.class);
                            startActivity(i);
                            finish();*/

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: " + e.toString());
                            progressDialog.dismiss();

                        }
                    });
                }

            }
        });


    }
}