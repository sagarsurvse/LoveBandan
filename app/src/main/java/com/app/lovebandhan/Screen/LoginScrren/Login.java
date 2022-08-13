package com.app.lovebandhan.Screen.LoginScrren;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.app.lovebandhan.R;
import com.app.lovebandhan.Screen.FormStep.FormStepFour;
import com.app.lovebandhan.Screen.ShowAllCustomer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.json.JSONArray;

import java.util.concurrent.TimeUnit;

import javax.annotation.Nullable;

public class Login extends AppCompatActivity {
    EditText ed_phone;
    FirebaseFirestore fstore;
    ProgressDialog pd;
    Button Btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        pd = new ProgressDialog(Login.this);
        pd.setMessage("Login..");
        Btnlogin = findViewById(R.id.id_continue);
        ed_phone = findViewById(R.id.phone_editext);

        FirebaseApp.initializeApp(this);
        fstore = FirebaseFirestore.getInstance();

        Btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.show();
                if (ed_phone.getText().toString() != null) {
                    DocumentReference documentReference2 = fstore.collection("Users").document(ed_phone.getText().toString());
                    documentReference2.addSnapshotListener(Login.this, new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            if (documentSnapshot.exists()) {
                                SharedPreferences.Editor editor = getSharedPreferences("loginUser", MODE_PRIVATE).edit();
                                editor.putString("id", ed_phone.getText().toString());
                                editor.putBoolean("isLogged", true);
                                editor.apply();

                                Intent i = new Intent(Login.this, ShowAllCustomer.class);
                                startActivity(i);
                                finish();
                                pd.dismiss();
                            }
                            else {
                                pd.dismiss();
                                Toast.makeText(Login.this, "Your Number Not Match", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });




    }

}