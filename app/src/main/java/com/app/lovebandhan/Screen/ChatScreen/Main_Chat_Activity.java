package com.app.lovebandhan.Screen.ChatScreen;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.lovebandhan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class Main_Chat_Activity extends AppCompatActivity {

    private static final String TAG = "AppTAG";
    FirebaseFirestore db;
    MessageAdapter adapter;
    RecyclerView mrecyclerView;
    List<ChatModel> chatModelList;
    TextView msend;
    EditText message;
    String Strmessage;
    Timestamp timestamp = Timestamp.now();
    String u_id,str_uname,isLoggedID;
    FirebaseFirestore fstore;
    ImageView txt_back;
    String documentId ;
    ChatModel chatModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);


        mrecyclerView = findViewById(R.id.chatRecyclerView);
        msend = findViewById(R.id.send);
        message = findViewById(R.id.message);
        txt_back = findViewById(R.id.backarrow);

        Bundle bundle = getIntent().getExtras();
        documentId = bundle.getString("chatDocumentId");
        u_id = bundle.getString("UserId");
        str_uname = bundle.getString("UserName");

        TextView Text_Uname = findViewById(R.id.Name);
        Text_Uname.setText(str_uname);

        fstore = FirebaseFirestore.getInstance();

        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        SharedPreferences prefs = getSharedPreferences("loginUser", MODE_PRIVATE);
        isLoggedID = prefs.getString("id", null);//"No name defined" is the default value.


        chatModelList = new ArrayList<>();

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mrecyclerView.setHasFixedSize(false);
        mrecyclerView.setLayoutManager(mLayoutManager);

        db = FirebaseFirestore.getInstance();


        msend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (message.getText().toString().equals("")) {
                    return;
                }

                chatModel = new ChatModel(message.getText().toString(), Timestamp.now(), true, null,"113244testid",isLoggedID);

                chatModelList.add(chatModel);

                FirebaseFirestore.getInstance().collection("Chats").document(documentId).collection("chats").add(chatModel).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                    }
                });

                Strmessage = message.getText().toString();
                message.setText("");
               

                adapter = new MessageAdapter(Main_Chat_Activity.this, chatModelList);
                mLayoutManager.setReverseLayout(false);
                mrecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                mrecyclerView.setLayoutManager(mLayoutManager);
                mrecyclerView.scrollToPosition(chatModelList.size()-1);
            }
        });

       LoadData();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

   public void LoadData(){
       db.collection("Chats").document(documentId).collection("chats").orderBy("time", Query.Direction.DESCENDING)
               .get()
               .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                   @Override
                   public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                       if (task.isSuccessful()) {


                           for (QueryDocumentSnapshot document : task.getResult()) {
                               if (isLoggedID.equals((String) document.getData().get("patientID"))){
                                   ChatModel chatModel = new ChatModel();
                                   chatModel.setCurrent(true);
                                   chatModel.setText((String) document.getData().get("text"));
                                   chatModel.setImageurl((String) document.getData().get("imageurl"));
                                   chatModel.setDocumentID(document.getId());
                                   chatModel.setTime((Timestamp) document.getData().get("time"));
                                   Log.e("TAG", document.getId());
                                   chatModelList.add(chatModel);
                                   timestamp = (Timestamp) document.getData().get("time");
                                   Log.d(TAG,"pID: "+(String) document.getData().get("text"));
                               }
                               else {

                                   ChatModel chatModel = new ChatModel();
                                   chatModel.setCurrent(false);
                                   chatModel.setText((String) document.getData().get("text"));
                                   chatModel.setImageurl((String) document.getData().get("imageurl"));
                                   chatModel.setDocumentID(document.getId());
                                   chatModel.setTime((Timestamp) document.getData().get("time"));
                                   Log.e("TAG", document.getId());
                                   chatModelList.add(chatModel);
                                   timestamp = (Timestamp) document.getData().get("time");
                                   Log.d(TAG,"pID: "+(String) document.getData().get("text"));


                               }


                           }

                           adapter = new MessageAdapter(Main_Chat_Activity.this, chatModelList);

                           mrecyclerView.setAdapter(adapter);
                           adapter.notifyDataSetChanged();
                           mrecyclerView.scrollToPosition(0);


                       } else {
                           Toast.makeText(Main_Chat_Activity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                       }
                   }
               });

   }
}


