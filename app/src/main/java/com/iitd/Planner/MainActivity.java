package com.iitd.Planner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button[] bmonth = new Button[12];
    Button[] bage = new Button[3];
    Button[] bweek = new Button[5];
    Button[] bday = new Button[7];

    int age = 0;
    int month = 0;
    int week = 0;
    int day = 0;
    TextView tv;
    Button language;
    ContentToDisplay cont;

    TextView bengali,english,hindi,kannada,marathi,gujrati;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        language = findViewById(R.id.btn_ln);

        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFilterPopup(v);
            }
        });
        tv = findViewById(R.id.hometext);
        cont = new ContentToDisplay(age,month,week,day);
        tv.setText("Welcome");

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





    }

    private void showFilterPopup(View v) {

        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popuplang_layout, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        // focusable lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        bengali = popupView.findViewById(R.id.id_bengali);
        english = popupView.findViewById(R.id.id_english);
        gujrati = popupView.findViewById(R.id.id_gujrati);
        hindi = popupView.findViewById(R.id.id_hindi);
        kannada = popupView.findViewById(R.id.id_kannada);
        marathi = popupView.findViewById(R.id.id_marathi);

        english.setOnClickListener(this);
        gujrati.setOnClickListener(this);
        hindi.setOnClickListener(this);
        kannada.setOnClickListener(this);
        marathi.setOnClickListener(this);


        View container = (View) popupWindow.getContentView().getParent();
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
// add flag
        p.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.3f;
        wm.updateViewLayout(container, p);
    }

    @Override
    public void onClick(View v) {
            selectMonth(v);
            selectAge(v);
            selectWeek(v);
            selectDay(v);
            selectLanguage(v);
            //tv.setText(cont.printContent());
    }

    public void selectLanguage(View v){
        switch (v.getId()) {
            case R.id.id_bengali:
                Toast.makeText(this, "Bengali Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_english:
                Toast.makeText(this, "English Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_gujrati:
                Toast.makeText(this, "Gujrati Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_hindi:
                Toast.makeText(this, "Hindi Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_kannada:
                Toast.makeText(this, "Kannada Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_marathi:
                Toast.makeText(this, "Marathi Selected", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    public void selectAge(View v){
        switch(v.getId()) {
            case R.id.age34:
                age = 1;
                cont.setAge(age);
                changeAgeBtnClr();
                tv.setText("1st Year Overview");
                changeMonthBtnClr();
                changeWeekBtnClr();
                v.setBackgroundResource(R.drawable.selectage);
                break;

            case R.id.age45:
                age = 2;
                cont.setAge(age);
                changeAgeBtnClr();
                tv.setText("2nd Year Overview");
                changeMonthBtnClr();
                changeWeekBtnClr();

                v.setBackgroundResource(R.drawable.selectage);
                break;

            case R.id.age56:
                age = 3;
                cont.setAge(age);
                changeAgeBtnClr();
                tv.setText("3rd Year Overview");
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
                month = 10 ;
                cont.setMonth(month);
                changeMonthBtnClr();
                tv.setText(cont.monthContent());
                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.feb_btn:
                month = 11 ;
                cont.setMonth(month);
                Toast.makeText(this,"February", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                tv.setText(cont.monthContent());

                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.mar_btn:
                month = 12;
                cont.setMonth(month);
                Toast.makeText(this,"March", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                tv.setText(cont.monthContent());

                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.apr_btn:
                month = 1;
                cont.setMonth(month);
                Toast.makeText(this,"April", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                tv.setText(cont.monthContent());

                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.may_btn:
                month = 2;
                cont.setMonth(month);
                Toast.makeText(this,"May", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                tv.setText(cont.monthContent());

                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.jun_btn:
                month = 3;
                cont.setMonth(month);
                Toast.makeText(this,"June", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                tv.setText(cont.monthContent());

                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.jul_btn:
                month = 4 ;
                cont.setMonth(month);
                Toast.makeText(this,"July", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                tv.setText(cont.monthContent());

                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.aug_btn:
                month = 5 ;
                cont.setMonth(month);
                Toast.makeText(this,"August", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                tv.setText(cont.monthContent());

                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.sep_btn:
                month = 6 ;
                cont.setMonth(month);
                Toast.makeText(this,"September", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                tv.setText(cont.monthContent());

                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.oct_btn:
                month = 7 ;
                cont.setMonth(month);
                Toast.makeText(this,"October", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                tv.setText(cont.monthContent());

                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.nov_btn:
                month = 8 ;
                cont.setMonth(month);
                Toast.makeText(this,"November", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                tv.setText(cont.monthContent());

                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.dec_btn:
                month = 9;
                cont.setMonth(month);
                Toast.makeText(this,"December", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                tv.setText(cont.monthContent());

                v.setBackgroundResource(R.drawable.selectmonth);
                break;
        }
    }
    public void selectWeek(View v){
        switch(v.getId()) {
            case R.id.id_week1:
                week = 1;
                cont.setWeek(week);
                changeWeekBtnClr();
                tv.setText(cont.weekContent());

                v.setBackgroundResource(R.drawable.selectsidebtn);
                break;

            case R.id.id_week2:
                week = 2;
                cont.setWeek(week);
                changeWeekBtnClr();
                tv.setText(cont.weekContent());

                v.setBackgroundResource(R.drawable.selectsidebtn);
                break;

            case R.id.id_week3:
                week = 3;
                cont.setWeek(week);
                changeWeekBtnClr();
                tv.setText(cont.weekContent());

                v.setBackgroundResource(R.drawable.selectsidebtn);
                break;
            case R.id.id_week4:
                week = 4;
                cont.setWeek(week);
                changeWeekBtnClr();
                tv.setText(cont.weekContent());

                v.setBackgroundResource(R.drawable.selectsidebtn);
                break;
            case R.id.id_week5:
                week = 5;
                cont.setWeek(week);
                changeWeekBtnClr();
                tv.setText(cont.weekContent());

                v.setBackgroundResource(R.drawable.selectsidebtn);
                break;
        }
    }
    public void selectDay(View v){
        switch(v.getId()) {
            case R.id.id_day1:
                day = 1;
                cont.setDay(day);
                changeDayBtnClr();
                v.setBackgroundResource(R.drawable.selectsidebtn);
                break;

            case R.id.id_day2:
                day = 2;
                cont.setDay(day);
                changeDayBtnClr();
                v.setBackgroundResource(R.drawable.selectsidebtn);
                break;

            case R.id.id_day3:
                day = 3;
                cont.setDay(day);
                changeDayBtnClr();
                v.setBackgroundResource(R.drawable.selectsidebtn);
                break;
            case R.id.id_day4:
                day = 4;
                cont.setDay(day);
                changeDayBtnClr();
                v.setBackgroundResource(R.drawable.selectsidebtn);
                break;
            case R.id.id_day5:
                day = 5;
                cont.setDay(day);
                changeDayBtnClr();
                v.setBackgroundResource(R.drawable.selectsidebtn);
                break;
            case R.id.id_day6:
                day = 6;
                cont.setDay(day);
                changeDayBtnClr();
                v.setBackgroundResource(R.drawable.selectsidebtn);
                break;
            case R.id.id_day7:
                day = 7;
                cont.setDay(day);
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

}