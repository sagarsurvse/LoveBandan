package com.app.lovebandhan.Screen.FormStep;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.app.lovebandhan.R;
import com.app.lovebandhan.Screen.HomeScreen;
import com.app.lovebandhan.Screen.ShowAllCustomer;
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

    String[] schoolArray = {"ENGIEERING", "B.E/B.Tech","M.E/M.Tech","M.S Engineering","B.Eng(Hons)","M.Eng(Hons)","Engineering Diploma","AE","AET","-ARTS/DESIGN-","B.A","B.ED","BJMS","BFA","B.Arch",
                             "B.Des","BMM","MFA","M.ED","M.A","MSW","MJMS","M.Arch","M.Des","BA(Hons)","B.Arch(Hons)","DFA","D.ED","D.Arch","AA","AFA","-FINANCE/COMMERCE-","B.Com",
                             "CA/CPA","CPA","CFA","CS","BSc/BFin","M.Com","MSc/MFin/MS","BCom(Hons)","PGD Finance","-COMPUTER/IT-","BCA","B.IT","BCS","BA Computer Science","MCA","PGDCA","IT Diploma","ADIT","-SCIENCE-","B.Sc",
                             "BSc/(Hons)","DipSc","AS","AAS","-MEDICINE","MBBS","BDS","BPT","BAMS","BHMS","B.Pharma","BVSc","BSN/BScN","MDS","MCH","M.D","M.S Medicine","MPT","DM","M.Pharma","MVSC","MMed","PGD Medicine","ADN", "-MANAGEMENT-","BBA","BHM","BBM",
                              "MBA","PGDM","ABA","ADBus","-LAW-","BL/LLB","ML/LLM","LLB(Hons)","ALA","-DOCTORATE","Ph.D","M.Phil","-OTHERS","Bachelor","Master","Diploma","Honours","Doctorate","Associte",
                               "-NON-GRADUATE-","High school","Less than high school"};
    String[] workarray = {"Private Company", "Government/Public Sector","Defence/Civil Services","Business/Self Employmed","Not Working"};
    String[] AsArray = {"ACCOUNTING,BANKING & FINANCE", "Banking Professional","Chartered Accountant","Company Secretary","Finance Professional","Accounting Professional(Others)","ADMINISTRATION &HR","Admin Professional","Human Resource Professional","ADVERTISING,MEDIA & ENTERTENMENT","Actor","Advertising Professional","Entertainment Professional","Event Manager","Journalist","Media Professional","Publilc Relations Professional","Framing","Horticulturist","Agricultural Professional (Others)","AIRLINE & AVIATION","Air Hostess/Fight Attendant","Pilot/Co-Pilot","Other Airline Professional",
                         "AERCHITECTURE & DESIGN","Architect","Interior Designer","Landscape Architect","ARTISTS,ANIMATORS & WEB DESIGNERS","Animator","Commercial Aertist","Web/UX Desingners","BEAUTY,FASHION & JEWELLARY DESIGNERS","Beautician","Fashion Desinger","Hair"};
    String[] Incomearray = {"Upto INR 1 Lakh", "INR 1 LAKH TO 2 Lakh","INR 2 LAKH TO 4 Lakh","INR 4 LAKH TO 7 Lakh","INR 7 LAKH TO 10 Lakh","INR 10 LAKH TO 15 Lakh","INR 15 LAKH TO 20 Lakh","INR 20 LAKH TO 30 Lakh","INR 30 LAKH TO 50 Lakh",
                             "INR 50 LAKH TO 15 Lakh","INR 75 LAKH TO 1 Coror","INR 1 Coror & above"};


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


                            SharedPreferences.Editor editor = getSharedPreferences("loginUser", MODE_PRIVATE).edit();
                            editor.putString("id", strmobile_no);
                            editor.putBoolean("isLogged", true);
                            editor.apply();

                            Intent i = new Intent(FormStepFour.this, ShowAllCustomer.class);
                            startActivity(i);
                            finish();

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