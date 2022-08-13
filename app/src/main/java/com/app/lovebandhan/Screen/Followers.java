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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.lovebandhan.Models.UserModel;
import com.app.lovebandhan.R;
import com.app.lovebandhan.Screen.ChatScreen.Main_Chat_Activity;
import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;


public class Followers extends AppCompatActivity {
    private static final String TAG = "APPTAG";
    FirestoreRecyclerAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    ProgressBar progressBar;
    RecyclerView friendList;
    String DoctorName,DoctorNumber;
    ImageView val,Addpatient,Addpatient2;
    Query query;
    BottomNavigationView bottomNav;
//    BottomNavigationView bottomNav;
    JSONArray res;
    String r;
    FirestoreRecyclerOptions<UserModel> response;
    FirebaseFirestore db;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.followers);



        friendList = findViewById(R.id.friend_list);
        progressBar = findViewById(R.id.progress_bar);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        friendList.setLayoutManager(linearLayoutManager);


        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();

        SharedPreferences prefs = getSharedPreferences("loginUser", MODE_PRIVATE);
        String isLoggedID = prefs.getString("id", null);//"No name defined" is the default value.


        LinearLayout btfollowers = findViewById(R.id.following);

        btfollowers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), Following.class));
                overridePendingTransition(0, 0);

            }
        });



        query = db.collection("UsersConnect").whereEqualTo("Mobile_Number",isLoggedID);
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


        bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.chat);
        bottomNav.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.matches:
                    return true;

                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), ShowAllCustomer.class));
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

                holder.textName.setText(model.getFirst_Name());

                holder.pLastMessage.setText(model.getWork());

                Glide
                        .with(getApplicationContext())
                        .load(model.getProfileUrl())
                        .centerCrop()
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(holder.profile_image);


                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent sendStuff = new Intent(Followers.this, Main_Chat_Activity.class);
                        sendStuff.putExtra("UserName", model.getFirst_Name());
                        sendStuff.putExtra("UserId", model.getMobile_Number());
                        sendStuff.putExtra("chatDocumentId", model.getChatDocumentId());
                        startActivity(sendStuff);
                    }
                });



            }

            @Override
            public FriendsHolder onCreateViewHolder(ViewGroup group, int i) {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.chat_main_holder, group, false);

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
        TextView textName,pLastMessage;
        ImageView profile_image;

        public FriendsHolder(View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.pname);
            profile_image = itemView.findViewById(R.id.profile_image);
            pLastMessage = itemView.findViewById(R.id.pLastMessage);
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