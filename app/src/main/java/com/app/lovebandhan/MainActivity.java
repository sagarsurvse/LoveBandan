package com.app.lovebandhan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.lovebandhan.Adapaters.SliderAdapter;
import com.app.lovebandhan.Models.SliderData;
import com.app.lovebandhan.Screen.FormStep.FormStepOne;
import com.app.lovebandhan.Screen.ShowAllCustomer;
import com.app.lovebandhan.Screen.VerifyScreen;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Urls of our images.
    String url1 ="https://firebasestorage.googleapis.com/v0/b/lovebandhan-a7b98.appspot.com/o/WhatsApp%20Image%202022-08-07%20at%2011.17.48%20PM.jpeg?alt=media&token=74eddbc9-09dc-4672-8c50-22eb16670f16";
    String url2 = "https://firebasestorage.googleapis.com/v0/b/lovebandhan-a7b98.appspot.com/o/WhatsApp%20Image%202022-08-08%20at%204.00.32%20PM.jpeg?alt=media&token=a1327d0f-98fc-46bd-b00c-e424ffa7470a";
    String url3 = "https://firebasestorage.googleapis.com/v0/b/lovebandhan-a7b98.appspot.com/o/WhatsApp%20Image%202022-08-08%20at%204.00.33%20PM%20(1).jpeg?alt=media&token=f0b6e192-0c93-4c92-8e4b-c66944d26ab1";
    Button Btn_Continue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        SharedPreferences prefs = getSharedPreferences("loginUser", MODE_PRIVATE);
        Boolean isLogged = prefs.getBoolean("isLogged", false);//"No name defined" is the default value.


        if (isLogged){
            Intent i = new Intent(getApplicationContext(), ShowAllCustomer.class);
            startActivity(i);
        }


        Btn_Continue = findViewById(R.id.btn_continue);



        // we are creating array list for storing our image urls.
        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();

        // initializing the slider view.
        SliderView sliderView = findViewById(R.id.slider);

        // adding the urls inside array list
        sliderDataArrayList.add(new SliderData(url1));
        sliderDataArrayList.add(new SliderData(url2));
        sliderDataArrayList.add(new SliderData(url3));

        // passing this array list inside our adapter class.
        SliderAdapter adapter = new SliderAdapter(this, sliderDataArrayList);

        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

        // below method is used to
        // setadapter to sliderview.
        sliderView.setSliderAdapter(adapter);

        // below method is use to set
        // scroll time in seconds.
        sliderView.setScrollTimeInSec(3);

        // to set it scrollable automatically
        // we use below method.
        sliderView.setAutoCycle(true);

        // to start autocycle below method is used.
        sliderView.startAutoCycle();

        Btn_Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), FormStepOne.class);
                startActivity(i);
            }
        });


    }
}