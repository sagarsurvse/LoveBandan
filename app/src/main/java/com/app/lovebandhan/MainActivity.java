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
import com.app.lovebandhan.Screen.HomeScreen;
import com.app.lovebandhan.Screen.VerifyScreen;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Urls of our images.
    String url1 = "data:image/jpeg;base64,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";
    String url2 = "https://qphs.fs.quoracdn.net/main-qimg-8e203d34a6a56345f86f1a92570557ba.webp";
    String url3 = "https://bizzbucket.co/wp-content/uploads/2020/08/Life-in-The-Metro-Blog-Title-22.png";
    EditText ed_phone;
    Button Btn_Continue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        SharedPreferences prefs = getSharedPreferences("loginUser", MODE_PRIVATE);
        Boolean isLogged = prefs.getBoolean("isLogged", false);//"No name defined" is the default value.


        if (isLogged){
            Intent i = new Intent(getApplicationContext(), HomeScreen.class);
            startActivity(i);
        }


        ed_phone = findViewById(R.id.ed_phone_number);
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
                /* if (ed_phone.getText() != null ){
                     if (ed_phone.getText().length() > 9){
                         Intent i = new Intent(getApplicationContext(), VerifyScreen.class);
                         i.putExtra("phone_no",ed_phone.getText().toString());
                         startActivity(i);
                     }
                 }*/

                Intent i = new Intent(getApplicationContext(), FormStepOne.class);
                startActivity(i);
            }
        });


    }
}