package com.iitd.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button[] bmonth = new Button[12];
    Button[] bage = new Button[3];
    Button[] bweek = new Button[5];
    Button[] bday = new Button[7];

    private String age, month, week, day;
    private EditText c1, c2, c3, c4, c5, c6, c7, c8;

    private String heading, duration, resources, instructions, content, activities, klg, assessment;

    Button upload;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        upload = findViewById(R.id.upload_btn);
        bmonth[0] = findViewById(R.id.apr_btn);
        bmonth[1] = findViewById(R.id.may_btn);
        bmonth[2] = findViewById(R.id.jun_btn);
        bmonth[3] = findViewById(R.id.jul_btn);
        bmonth[4] = findViewById(R.id.aug_btn);
        bmonth[5] = findViewById(R.id.sep_btn);
        bmonth[6] = findViewById(R.id.oct_btn);
        bmonth[7] = findViewById(R.id.nov_btn);
        bmonth[8] = findViewById(R.id.dec_btn);
        bmonth[9] = findViewById(R.id.jan_btn);
        bmonth[10] = findViewById(R.id.feb_btn);
        bmonth[11] = findViewById(R.id.mar_btn);
        for(Button btn :bmonth){
            btn.setOnClickListener(this);
        }

        bage[0] = findViewById(R.id.age34);
        bage[1] = findViewById(R.id.age45);
        bage[2] = findViewById(R.id.age56);
        for(Button btn :bage){
            btn.setOnClickListener(this);
        }

        bweek[0] = findViewById(R.id.id_week1);
        bweek[1] = findViewById(R.id.id_week2);
        bweek[2] = findViewById(R.id.id_week3);
        bweek[3] = findViewById(R.id.id_week4);
        bweek[4] = findViewById(R.id.id_week5);
        for(Button btn :bweek){
            btn.setOnClickListener(this);
        }

        bday[0] = findViewById(R.id.id_day1);
        bday[1] = findViewById(R.id.id_day2);
        bday[2] = findViewById(R.id.id_day3);
        bday[3] = findViewById(R.id.id_day4);
        bday[4] = findViewById(R.id.id_day5);
        bday[5] = findViewById(R.id.id_day6);
        bday[6] = findViewById(R.id.id_day7);
        for(Button btn :bday){
            btn.setOnClickListener(this);
        }

        c1 = findViewById(R.id.heading_input);
        c2 = findViewById(R.id.dur_input);
        c3 = findViewById(R.id.res_input);
        c4 = findViewById(R.id.inst_input);
        c5 = findViewById(R.id.cont_input);
        c6 = findViewById(R.id.act_input);
        c7 = findViewById(R.id.klg_input);
        c8 = findViewById(R.id.ass_input);

        upload.setOnClickListener(view -> {
//            if(Integer.parseInt(id) > 31257){
//                Toast.makeText(getApplicationContext(), "Please Enter a valid date", Toast.LENGTH_SHORT).show();
//            }else{
//            if(age.isEmpty())
//                Toast.makeText(getApplicationContext(), "Please Enter a valid age", Toast.LENGTH_SHORT).show();
//            else if(month.isEmpty())
//                Toast.makeText(getApplicationContext(), "Please Enter a valid month", Toast.LENGTH_SHORT).show();
//            else if(week.isEmpty())
//                Toast.makeText(getApplicationContext(), "Please Enter a valid week", Toast.LENGTH_SHORT).show();
//            else if(day.isEmpty())
//                Toast.makeText(getApplicationContext(), "Please Enter a valid date", Toast.LENGTH_SHORT).show();
//            else
                upload();
//            }
        });

    }

    @Override
    public void onClick(View v) {
        selectMonth(v);
        selectAge(v);
        selectWeek(v);
        selectDay(v);
    }
    public void selectAge(View v){
        switch(v.getId()) {
            case R.id.age34:
                age = "1";
                changeAgeBtnClr();
                changeMonthBtnClr();
                changeWeekBtnClr();
                v.setBackgroundResource(R.drawable.selectage);
                break;

            case R.id.age45:
                age = "2";
                changeAgeBtnClr();
                changeMonthBtnClr();
                changeWeekBtnClr();
                v.setBackgroundResource(R.drawable.selectage);
                break;

            case R.id.age56:
                age = "3";
                changeAgeBtnClr();
                changeMonthBtnClr();
                changeWeekBtnClr();
                v.setBackgroundResource(R.drawable.selectage);
                break;
        }
    }
    public void selectMonth(View v){
        switch (v.getId()) {
            case R.id.jan_btn:
                Toast.makeText(this,"January", Toast.LENGTH_SHORT).show();
                month = "10" ;
                changeMonthBtnClr();
                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.feb_btn:
                month = "11" ;
                Toast.makeText(this,"February", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.mar_btn:
                month = "12";
                Toast.makeText(this,"March", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.apr_btn:
                month = "01";
                Toast.makeText(this,"April", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.may_btn:
                month = "02";
                Toast.makeText(this,"May", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.jun_btn:
                month = "03";
                Toast.makeText(this,"June", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.jul_btn:
                month = "04" ;
                Toast.makeText(this,"July", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.aug_btn:
                month = "05" ;
                Toast.makeText(this,"August", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.sep_btn:
                month = "06" ;
                Toast.makeText(this,"September", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.oct_btn:
                month = "07" ;
                Toast.makeText(this,"October", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.nov_btn:
                month = "08" ;
                Toast.makeText(this,"November", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.dec_btn:
                month = "09";
                Toast.makeText(this,"December", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                v.setBackgroundResource(R.drawable.selectmonth);
                break;
        }
    }
    public void selectWeek(View v){
        switch(v.getId()) {
            case R.id.id_week1:
                week = "1";
                changeWeekBtnClr();
                v.setBackgroundResource(R.drawable.selectsidebtn);
                break;

            case R.id.id_week2:
                week = "2";
                changeWeekBtnClr();
                v.setBackgroundResource(R.drawable.selectsidebtn);
                break;

            case R.id.id_week3:
                week = "3";
                changeWeekBtnClr();
                v.setBackgroundResource(R.drawable.selectsidebtn);
                break;
            case R.id.id_week4:
                week = "4";
                changeWeekBtnClr();
                v.setBackgroundResource(R.drawable.selectsidebtn);
                break;
            case R.id.id_week5:
                week = "5";
                changeWeekBtnClr();
                v.setBackgroundResource(R.drawable.selectsidebtn);
                break;

        }
    }
    public void selectDay(View v){
        switch(v.getId()) {
            case R.id.id_day1:
                day = "1";
                changeDayBtnClr();
                v.setBackgroundResource(R.drawable.selectsidebtn);
                break;

            case R.id.id_day2:
                day = "2";
                changeDayBtnClr();
                v.setBackgroundResource(R.drawable.selectsidebtn);
                break;

            case R.id.id_day3:
                day = "3";
                changeDayBtnClr();
                v.setBackgroundResource(R.drawable.selectsidebtn);
                break;
            case R.id.id_day4:
                day = "4";
                changeDayBtnClr();
                v.setBackgroundResource(R.drawable.selectsidebtn);
                break;
            case R.id.id_day5:
                day = "5";
                changeDayBtnClr();
                v.setBackgroundResource(R.drawable.selectsidebtn);
                break;
            case R.id.id_day6:
                day = "6";
                changeDayBtnClr();
                v.setBackgroundResource(R.drawable.selectsidebtn);
                break;
            case R.id.id_day7:
                day = "7";
                changeDayBtnClr();
                v.setBackgroundResource(R.drawable.selectsidebtn);
                break;
        }
    }

    public void changeMonthBtnClr(){
        bmonth[0].setBackgroundResource(R.drawable.aprbtn);
        bmonth[1].setBackgroundResource(R.drawable.maybtn);
        bmonth[2].setBackgroundResource(R.drawable.junbtn);
        bmonth[3].setBackgroundResource(R.drawable.julbtn);
        bmonth[4].setBackgroundResource(R.drawable.augbtn);
        bmonth[5].setBackgroundResource(R.drawable.sepbtn);
        bmonth[6].setBackgroundResource(R.drawable.octbtn);
        bmonth[7].setBackgroundResource(R.drawable.novbtn);
        bmonth[8].setBackgroundResource(R.drawable.decbtn);
        bmonth[9].setBackgroundResource(R.drawable.janbtn);
        bmonth[10].setBackgroundResource(R.drawable.febbtn);
        bmonth[11].setBackgroundResource(R.drawable.marbtn);
    }
    public void changeAgeBtnClr(){
        for (Button btn: bage){
            btn.setBackgroundResource(R.drawable.btnage);
        }
    }
    public void changeWeekBtnClr(){
        for (Button btn: bweek){
            btn.setBackgroundResource(R.drawable.sidebtn);
        }
    }
    public void changeDayBtnClr(){
        for (Button btn: bday){
            btn.setBackgroundResource(R.drawable.sidebtn);
        }
    }

    public void upload(){
        heading = c1.getText().toString().trim();
        duration = c2.getText().toString().trim();
        resources = c3.getText().toString().trim();
        instructions = c4.getText().toString().trim();
        content = c5.getText().toString().trim();
        activities = c6.getText().toString().trim();
        klg = c7.getText().toString().trim();
        assessment = c8.getText().toString().trim();


        if(age==null)
            Toast.makeText(getApplicationContext(), "Please Enter a valid age", Toast.LENGTH_SHORT).show();
        else if(month==null)
            Toast.makeText(getApplicationContext(), "Please Enter a valid month", Toast.LENGTH_SHORT).show();
        else if(week==null)
            Toast.makeText(getApplicationContext(), "Please Enter a valid week", Toast.LENGTH_SHORT).show();
        else if(day==null)
            Toast.makeText(getApplicationContext(), "Please Enter a valid day", Toast.LENGTH_SHORT).show();
        else{
            id = age.trim() + month.trim() + week.trim() + day.trim();

            ContentBean cb = new ContentBean(heading, duration, resources, instructions, content, activities, klg, assessment);
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference root = db.getReference();
            root.child(id).setValue(cb);
            Toast.makeText(this,"Uploaded!", Toast.LENGTH_SHORT).show();

        }

    }

}