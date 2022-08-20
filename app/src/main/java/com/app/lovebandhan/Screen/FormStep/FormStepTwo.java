package com.app.lovebandhan.Screen.FormStep;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.app.lovebandhan.R;
import com.app.lovebandhan.Screen.LoginScrren.Login;
import com.app.lovebandhan.Screen.ShowAllCustomer;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.annotation.Nullable;

public class FormStepTwo extends AppCompatActivity {
    TextInputEditText mail,mobile_no,date_of_birth;
    Button Btn_Continue;
    final Calendar myCalendar= Calendar.getInstance();
    FirebaseFirestore db;
    Boolean IsAlready = false;
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

        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();

        Btn_Continue = findViewById(R.id.create_account);
        mobile_no.addTextChangedListener(textWatcher);

        Btn_Continue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (IsAlready){
                    mobile_no.setError("User Already Register");
                }
                else {
                    if (!mail.getText().toString().isEmpty() || !mobile_no.getText().toString().isEmpty()) {
                        Intent pass = new Intent(getApplicationContext(), FormStepThree.class);
                        pass.putExtra("fname", fname);
                        pass.putExtra("lname", lname);
                        pass.putExtra("profile", profile);
                        pass.putExtra("religion", religin);
                        pass.putExtra("gender", gender);
                        pass.putExtra("community", community);
                        pass.putExtra("gmail", mail.getText().toString());
                        pass.putExtra("mobile_no", mobile_no.getText().toString());
                        pass.putExtra("date_of_birth", date_of_birth.getText().toString());
                        startActivity(pass);

                    } else {

                        mail.setError("please enter the mail");

                        mobile_no.setError("please enter the mobile no");

                    }
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

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
           if (s.length() > 9){
               DocumentReference documentReference2 = db.collection("Users").document(mobile_no.getText().toString());
               documentReference2.addSnapshotListener(FormStepTwo.this, new EventListener<DocumentSnapshot>() {



                   @Override
                   public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                       if (documentSnapshot.exists()) {
                           IsAlready = true;
                       }
                       else {
                           IsAlready = false;
                       }
                   }
               });
           }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}