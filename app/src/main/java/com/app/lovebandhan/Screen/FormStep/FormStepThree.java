package com.app.lovebandhan.Screen.FormStep;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.app.lovebandhan.R;

public class FormStepThree extends AppCompatActivity {
    Spinner SpinLive,SpinCity,SpinMaterial,SpinDiet,SpinHeight,SpinSubCommunity;
    Button Btn_Continue;


    ArrayAdapter<CharSequence> adapterSpinlive;
    ArrayAdapter<CharSequence> adapterSpinSelect_city;
    ArrayAdapter<CharSequence> adapterSpinMeterial;
    ArrayAdapter<CharSequence> adapterSpinDiet;
    ArrayAdapter<CharSequence> adapterSpinHeight;
    ArrayAdapter<CharSequence> adapterSpinCommunity;


    String[] liveArray = {"Andhra Pradesh","Bihar","dehli-NCR","Gujrat","Haryana","Jharkhand","Karnataka","Kerala","Madhya Pradesh","Maharashtra","Orissa","Punjab","Rajasthan",
                          "Tamil Nadu","Uttar Pradesh","West Bengal","Sikkim","Uttaranchal","Tripura"};
    String[] select_cityArray ={"Ahmednagar","Akola","Amravati","Aurangabad","Beed","Bhandara","Buldhana","Chandrapur","Dhule","Gadchirli",
                                "Gondiya","Hingoli","Jalgaon","Jalna","Kolhapur","Latur","Malegaon","Mumbai","Nagpur","Nanded","Nandurbar","Nashik",
                                "Navi Mumbai","Oras","Osmanabad","Parbhani","Pune","Raigad","Ratnagiri","Sangli","Satara","Sewagram","Solapur","Thane",
                                 "Wardha","Washim","Yavatmal","Other"};
    String[] meterialarray = {"Never Married", "divorced","Widowed","Awaiting Divorce","Annulled"};
    String[] dietArray = {"Veg", "Non-Veg","Occasionally Non-Veg","Eggetarian","Jain","Vegan"};
    String[] heightarray = {"4ft 5in-134cm", "4ft 6in-137","4ft 7in-139cm","4ft 8in-142cm","4ft 9in-144cm","4ft 10in-147cm","4ft 11in-149cm","5ft- 152cm","5ft 1in-154",
                             "5ft 2in-157cm","5ft 3in-160cm","5ft 4in -162cm","5ft 5in-165cm","5ft 6in-167cm","5ft 7in-170cm","5ft 8in-172cm","5ft 9in-175cm","5ft 10in-177cm",
                             "5ft 11in-180cm","6ft - 182cm","6ft 1in-185cm","6ft 2in-187cm","6ft 3in-190cm","6ft 4in-193cm","6ft 5in-195cm","6ft 6in-198cm","6ft 7in-200cm",
                             "6ft 8in-203cm","6ft 9in-205cm","6ft 10in-208cm","6ft 11in-210cm","7ft- 213cm"};
    String[] subcommunity = {"Agarwal","Aroya","Baniya","Brahmin-Bhumihar","Brahmin-Gour","Brahmin-Kanyakubja","Brahmin-Other","Brahmin-Saryuparin","Gupta","Jat","Jatav","Kayastha","Khatri",
                             "Kshatriya","Kurmi","Kushwaha","Rajput","Scheduled Caste(SC)","Thakur","Yadav","24 Manai Telugu Chettier","96 Kuli Maratha","96K Kokanastha","Adhi Andhra","Adi Dharmi","Adi Dravida",
                              "Adi Karnataka","Agamudayar","Agarwal","Agnikula Kshatriya","Agri","Ahir","Ahom","Ambalavasi","Arcot","Arekatica","Arora","Arunthathiyar","Arya Vysya","Aryasamaj","Ayyaraka",
                               "Badaga","Baghel/Pal/Gaderiya","Bahi","Baidya","Baishya","Bajantri","Balija","Balija-Naidu","Banayat Oriya","Banik","Baniya","Barai","Bari","Barnwal",
                               "Barujibi","Besta","Bhandari","Bhatia","Bhatraju","Bhavsar","Bhovi","Billava","Boya/Nayak/Naik","Boyer","Brahmatt","Brahmin-Anavil","B"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_step_three);

        String fname = getIntent().getStringExtra("fname");
        String lname = getIntent().getStringExtra("lname");
        String profile = getIntent().getStringExtra("profile");
        String religin = getIntent().getStringExtra("religion");
        String gender = getIntent().getStringExtra("gender");
        String community = getIntent().getStringExtra("community");
        String strgmail = getIntent().getStringExtra("gmail");
        String strmobile_no = getIntent().getStringExtra("mobile_no");
        String dateofbirth = getIntent().getStringExtra("date_of_birth");



        SpinLive = findViewById(R.id.spinner_live);
        SpinCity = findViewById(R.id.spinner_city);

        SpinMaterial = findViewById(R.id.spinner_materil);
        SpinDiet = findViewById(R.id.spinner_diet);
        SpinHeight = findViewById(R.id.spinner_height);
        SpinSubCommunity = findViewById(R.id.spinner_sub);


        // for profilea
        adapterSpinlive =    new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,liveArray);
        adapterSpinlive.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinLive.setAdapter(adapterSpinlive);

        adapterSpinSelect_city =    new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,select_cityArray);
        adapterSpinSelect_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinCity.setAdapter(adapterSpinSelect_city);


        //for  gender
        adapterSpinMeterial =    new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,meterialarray);
        adapterSpinMeterial.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinMaterial.setAdapter(adapterSpinMeterial);

        //for  religion
        adapterSpinDiet =    new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,dietArray);
        adapterSpinDiet.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinDiet.setAdapter(adapterSpinDiet);

        //for  community
        adapterSpinHeight =    new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,heightarray);
        adapterSpinHeight.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinHeight.setAdapter(adapterSpinHeight);

        //for  community
        adapterSpinCommunity =    new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,subcommunity);
        adapterSpinCommunity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinSubCommunity.setAdapter(adapterSpinCommunity);


        Btn_Continue = findViewById(R.id.three_continue);
        Btn_Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pass = new Intent(getApplicationContext(), FormStepFour.class);
                pass.putExtra("fname",fname);
                pass.putExtra("lname",lname);
                pass.putExtra("profile",profile);
                pass.putExtra("religion",religin);
                pass.putExtra("gender",gender);
                pass.putExtra("community",community);
                pass.putExtra("gmail",strgmail);
                pass.putExtra("mobile_no",strmobile_no);
                pass.putExtra("date_of_birth",dateofbirth);
                pass.putExtra("live",SpinLive.getSelectedItem().toString());
                pass.putExtra("city",SpinLive.getSelectedItem().toString());
                pass.putExtra("Meterial",SpinLive.getSelectedItem().toString());
                pass.putExtra("diet",SpinLive.getSelectedItem().toString());
                pass.putExtra("height",SpinLive.getSelectedItem().toString());
                pass.putExtra("sub_community",SpinLive.getSelectedItem().toString());
                startActivity(pass);
            }
        });


    }
}