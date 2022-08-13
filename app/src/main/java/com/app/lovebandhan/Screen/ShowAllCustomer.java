package com.app.lovebandhan.Screen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.app.lovebandhan.Models.UserModel;
import com.app.lovebandhan.R;
import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShowAllCustomer extends AppCompatActivity {

    private static final String TAG = "APPTAG";
    FirestoreRecyclerAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    ProgressBar progressBar;
    RecyclerView friendList;
    List<String> ChatList = new ArrayList<>();
    Query query;
//    BottomNavigationView bottomNav;
    JSONArray res;
    String r;
    String isLoggedID;
    FirestoreRecyclerOptions<UserModel> response;
    FirebaseFirestore db;
    BottomNavigationView bottomNav;
       @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        friendList = findViewById(R.id.friend_list);
        progressBar = findViewById(R.id.progress_bar);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        friendList.setLayoutManager(linearLayoutManager);


        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();
        query = db.collection("Users").whereEqualTo("Gender","male");
        response = new FirestoreRecyclerOptions.Builder<UserModel>()
                .setQuery(query, UserModel.class)
                .build();
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.getResult().isEmpty()){
                    progressBar.setVisibility(View.GONE);
                }
            }
        });


        SharedPreferences prefs = getSharedPreferences("loginUser", MODE_PRIVATE);
        isLoggedID = prefs.getString("id", null);//"No name defined" is the default value.

           DocumentReference documentReferencefollow = db.collection("Users").document(isLoggedID);
           documentReferencefollow.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
               @Override
               public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                   if (documentSnapshot.exists()) {
                       List<String> c_list = (List<String>) documentSnapshot.get("ChatList");
                       if (c_list != null) {
                           ChatList.addAll(c_list);
                       }
                   } else {
                       Log.d("tag", "onEvent: Document do not exists");
                   }
               }
           });


           bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.home);
        bottomNav.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.matches:
                    return true;

                case R.id.chat:
                   startActivity(new Intent(getApplicationContext(), Followers.class));
                   overridePendingTransition(0, 0);
                   return true;


                case R.id.primium:
                   return true;
           }
           return false;
        });


        LoadData();




    }

    public void LoadData(){
        adapter = new FirestoreRecyclerAdapter<UserModel, FriendsHolder>(response) {
            @Override
            public void onBindViewHolder(final FriendsHolder holder, int position, final UserModel model) {
                progressBar.setVisibility(View.GONE);

                Boolean isCheck = false;
                holder.textName.setText(model.getMobile_Number());

                if (ChatList != null){
                    for (int i = 0; i<ChatList.size();i++){
                        if (ChatList.get(i).equals(model.getMobile_Number())){
                            Log.d(TAG,"ChatList.get(i) "+ChatList.get(i)+" "+model.getMobile_Number());
                            isCheck = true;
                        }
                    }
                }

                if (isCheck){
                    holder.connect.setVisibility(View.GONE);
                    holder.send_Message.setVisibility(View.VISIBLE);
                }
                else {
                    holder.connect.setVisibility(View.VISIBLE);
                    holder.send_Message.setVisibility(View.GONE);
                }

                holder.send_Message.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(), Followers.class);
                        startActivity(i);

                    }
                });
                holder.connect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        DocumentReference docRef = db.collection("Users").document(isLoggedID);
                        docRef.update("ChatList", FieldValue.arrayUnion(model.getMobile_Number()));

                        String doc_id = db.collection("UsersConnect").document().getId();
                        String ChatID = db.collection("Chats").document().getId();
                        DocumentReference documentReference = db.collection("UsersConnect").document(doc_id);
                        Map<String, Object> user = new HashMap<>();
                        user.put("First_Name", model.getFirst_Name());
                        user.put("Last_Name",  model.getLast_Name());
                        user.put("ProfileFor",model.getProfileFor());
                        user.put("Religion",model.getReligion());
                        user.put("Gender",model.getGender());
                        user.put("Community",model.getCommunity());
                        user.put("Gmail",model.getGmail());
                        user.put("Mobile_Number",model.getMobile_Number());
                        user.put("RequestedID",isLoggedID);
                        user.put("ProfileUrl",model.getProfileUrl());
                        user.put("Material",model.getMaterial());
                        user.put("Diet",model.getDiet());
                        user.put("Height",model.getHeight());
                        user.put("live",model.getLive());
                        user.put("Sub_Community",model.getSub_Community());
                        user.put("Highest_Qualification",model.getHighest_Qualification());
                        user.put("work",model.getWork());
                        user.put("As",model.getAs());
                        user.put("Income",model.getIncome());
                        user.put("chatDocumentId",ChatID);
                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Intent i = new Intent(getApplicationContext(), Followers.class);
                                startActivity(i);



                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "onFailure: " + e.toString());
                            }
                        });
                    }
                });

                Glide
                        .with(getApplicationContext())
                        .load(model.getProfileUrl())
                        .centerCrop()
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(holder.profile_image);



            }

            @Override
            public FriendsHolder onCreateViewHolder(ViewGroup group, int i) {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.item_user_list, group, false);

                return new FriendsHolder(view);
            }

            @Override
            public void onError(FirebaseFirestoreException e) {
                Log.e("error", e.getMessage());
            }
        };


        adapter.notifyDataSetChanged();
        friendList.setAdapter(adapter);
    }

    public class FriendsHolder extends RecyclerView.ViewHolder {
        TextView textName;
        ImageView profile_image;
        RelativeLayout connect;
        RelativeLayout send_Message;

        public FriendsHolder(View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.Name);
            profile_image = itemView.findViewById(R.id.profile_image);
            connect = itemView.findViewById(R.id.connect);
            send_Message = itemView.findViewById(R.id.sendMessage);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}