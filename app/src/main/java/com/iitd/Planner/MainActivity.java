package com.iitd.Planner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;


    Button[] bmonth = new Button[12];
    Button[] bage = new Button[3];
    Button[] bweek = new Button[5];
    Button[] bday = new Button[7];

    String id;

    int age = 0;
    int month = 0;
    int week = 0;
    int day = 0;
    TextView conttv, headtv, durtv, restv, klgtv, insttv, acttv, asstv;
    Button language;
    Button appChange;
    ContentToDisplay cont;

    TextView bengali,english,hindi,kannada,marathi,gujrati;
    Context context;
    Resources resources;
    String lang = "english";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        language = findViewById(R.id.btn_ln);

        language.setOnClickListener(this::showFilterPopup);
        cont = new ContentToDisplay(age,month,week,day);

        headtv = findViewById(R.id.head_tv);
        durtv = findViewById(R.id.dur_tv);
        restv = findViewById(R.id.res_tv);
        insttv = findViewById(R.id.inst_tv);
        conttv = findViewById(R.id.cont_tv);
        acttv = findViewById(R.id.act_tv);
        klgtv = findViewById(R.id.klg_tv);
        asstv = findViewById(R.id.ass_tv);

        appChange = findViewById(R.id.gotoapp);
        appChange.setOnClickListener(this);
        appChange.setVisibility(View.GONE);

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

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        onAppStart();
    }

    private void onAppStart(){
        content_id = "10111";
        selectAge(bage[0]);
        selectMonth(bmonth[0]);
        selectWeek(bweek[0]);
        selectDay(bday[0]);
        getdata();
    }

    private void getdata() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    HashMap<String, Object> dataMap = (HashMap<String, Object>) dataSnapshot.getValue();
                        Object data = dataMap.get(content_id);
                        System.out.println(content_id);
                        try{
                            HashMap<String, Object> userData = (HashMap<String, Object>) data;
                            ContentBean cBean = new ContentBean(
                                    (String) userData.get("heading"),
                                    (String) userData.get("duration"),
                                    (String) userData.get("resources"),
                                    (String) userData.get("instructions"),
                                    (String) userData.get("content"),
                                    (String) userData.get("activity"),
                                    (String) userData.get("klg"),
                                    (String) userData.get("assessment"));
                            headtv.setText(cBean.getHeading());
                            durtv.setText(cBean.getDuration());
                            restv.setText(cBean.getResources());
                            insttv.setText(cBean.getInstructions());
                            conttv.setText(cBean.getContent());
                            klgtv.setText(cBean.getKlg());
                            asstv.setText(cBean.getAssessment());
                            acttv.setText(cBean.getActivity());
                            Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
                            System.out.println("head: " + cBean.getHeading() + "\n ass: " + cBean.getAssessment() + "cont: " + cBean.getContent() + "\n dur: " + cBean.getDuration());
                        }catch (NullPointerException cce){
                            headtv.setText("Null");
                            durtv.setText("Null");
                            restv.setText("Null");
                            insttv.setText("Null");
                            conttv.setText("Null");
                            klgtv.setText("Null");
                            asstv.setText("Null");
                            acttv.setText("Null");
                        }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
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

    String content_id;
    @Override
    public void onClick(View v) {
            selectMonth(v);
            selectAge(v);
            selectWeek(v);
            selectDay(v);
            selectLanguage(v);
            openUnity(v);
            content_id = Integer.toString(age) + "0" + Integer.toString(month) + Integer.toString(week) + Integer.toString(day);
                getdata();
    }
    
    String appToOpen = "null";
    public void openUnity(View v){
        if(v.getId() == R.id.gotoapp){
            String packageName;
            String className = "com.google.android.youtube.app.honeycomb.Shell$HomeActivity";
            switch (appToOpen){
                case "alphabets":
                    packageName = "com.DivineLab.Alphabet";
                    className = "com.unity3d.player.UnityPlayerActivity";
                    break;
                case "face":
                    packageName = "com.iitdelhi.facear";
                    className = "com.unity3d.player.UnityPlayerActivity";
                    break;
                case "silhouette":
                    packageName = "com.AryanJumani.SilhouetteApp";
                    className = "com.unity3d.player.UnityPlayerActivity";
                    break;
                case "sticks":
                    packageName = "com.AryanJumani.SticksApp";
                    className = "com.unity3d.player.UnityPlayerActivity";
                    break;
                case "portal":
                    packageName = "com.DefaultCompany.ARPortalDemo";
                    className = "com.unity3d.player.UnityPlayerActivity";
                    break;
                case "balloon":
                    packageName = "com.MyCompany.arshooter";
                    className = "com.unity3d.player.UnityPlayerActivity";
                    break;
                case "human":
                    packageName = "com.AryanJumani.humanBody";
                    className = "com.unity3d.player.UnityPlayerActivity";
                    break;
                default:
                    packageName = "com.google.android.youtube";
            }
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setPackage(packageName);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setClassName(packageName, className);
            startActivity(intent);
        }
    }

    public void selectLanguage(View v){
        switch (v.getId()) {
            case R.id.id_bengali:
                Toast.makeText(this, "Bengali Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_english:
                lang = "en";
                break;
            case R.id.id_gujrati:
                Toast.makeText(this, "Gujrati Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_hindi:
                lang = "hi";
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
                changeMonthBtnClr();
                changeWeekBtnClr();
                v.setBackgroundResource(R.drawable.selectage);
                break;

            case R.id.age45:
                age = 2;
                cont.setAge(age);
                changeAgeBtnClr();
                changeMonthBtnClr();
                changeWeekBtnClr();

                v.setBackgroundResource(R.drawable.selectage);
                break;

            case R.id.age56:
                age = 3;
                cont.setAge(age);
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
                month = 10 ;
                cont.setMonth(month);
                changeMonthBtnClr();
                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.feb_btn:
                month = 11 ;
                cont.setMonth(month);
                Toast.makeText(this,"February", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.mar_btn:
                month = 12;
                cont.setMonth(month);
                Toast.makeText(this,"March", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.apr_btn:
                month = 1;
                cont.setMonth(month);
                Toast.makeText(this,"April", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.may_btn:
                month = 2;
                cont.setMonth(month);
                Toast.makeText(this,"May", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.jun_btn:
                month = 3;
                cont.setMonth(month);
                Toast.makeText(this,"June", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.jul_btn:
                month = 4 ;
                cont.setMonth(month);
                Toast.makeText(this,"July", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                conttv.setText(cont.monthContent());

                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.aug_btn:
                month = 5 ;
                cont.setMonth(month);
                Toast.makeText(this,"August", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.sep_btn:
                month = 6 ;
                cont.setMonth(month);
                Toast.makeText(this,"September", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.oct_btn:
                month = 7 ;
                cont.setMonth(month);
                Toast.makeText(this,"October", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.nov_btn:
                month = 8 ;
                cont.setMonth(month);
                Toast.makeText(this,"November", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
                v.setBackgroundResource(R.drawable.selectmonth);
                break;

            case R.id.dec_btn:
                month = 9;
                cont.setMonth(month);
                Toast.makeText(this,"December", Toast.LENGTH_SHORT).show();
                changeMonthBtnClr();
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
                v.setBackgroundResource(R.drawable.selectsidebtn);
                break;

            case R.id.id_week2:
                week = 2;
                cont.setWeek(week);
                changeWeekBtnClr();
                v.setBackgroundResource(R.drawable.selectsidebtn);
                break;

            case R.id.id_week3:
                week = 3;
                cont.setWeek(week);
                changeWeekBtnClr();
                v.setBackgroundResource(R.drawable.selectsidebtn);
                break;
            case R.id.id_week4:
                week = 4;
                cont.setWeek(week);
                changeWeekBtnClr();
                v.setBackgroundResource(R.drawable.selectsidebtn);
                break;
            case R.id.id_week5:
                week = 5;
                cont.setWeek(week);
                changeWeekBtnClr();
                v.setBackgroundResource(R.drawable.selectsidebtn);
                break;
        }
    }
//    public void aprilContent(){
//            if(age == 1 && month == 1 && week == 4){
//                appChange.setVisibility(View.VISIBLE);
//                switch (day){
//                    case 2:
//                        if(lang.equals("hi")){
//                            context = LocaleHelper.setLocale(MainActivity.this, "hi");
//                        }else{
//                            context = LocaleHelper.setLocale(MainActivity.this, "en");
//                        }
//                        resources = context.getResources();
//                        headtv.setText(resources.getString(R.string.head2));
//                        durtv.setText(resources.getString(R.string.duration2));
//                        restv.setText(resources.getString(R.string.res2));
//                        insttv.setText(resources.getString(R.string.inst2));
//                        conttv.setText(resources.getString(R.string.cont2));
//                        klgtv.setText(resources.getString(R.string.klg2));
//                        asstv.setText(resources.getString(R.string.asses2));
//                        acttv.setText(resources.getString(R.string.activity2));
//                        appToOpen = "alphabets";
//                        break;
//                    case 1:
//                        if(lang.equals("hi")){
//                            context = LocaleHelper.setLocale(MainActivity.this, "hi");
//                        }else{
//                            context = LocaleHelper.setLocale(MainActivity.this, "en");
//                        }
//                        resources = context.getResources();
//                        headtv.setText(resources.getString(R.string.head1));
//                        durtv.setText(resources.getString(R.string.duration1));
//                        restv.setText(resources.getString(R.string.res1));
//                        insttv.setText(resources.getString(R.string.inst1));
//                        conttv.setText(resources.getString(R.string.cont1));
//                        klgtv.setText(resources.getString(R.string.klg1));
//                        asstv.setText(resources.getString(R.string.asses1));
//                        acttv.setText(resources.getString(R.string.activity1));
//                        appToOpen = "alphabets";
//                        break;
//                    case 3:
//                        if(lang.equals("hi")){
//                            context = LocaleHelper.setLocale(MainActivity.this, "hi");
//                        }else{
//                            context = LocaleHelper.setLocale(MainActivity.this, "en");
//                        }
//                        resources = context.getResources();
//                        headtv.setText(resources.getString(R.string.head3));
//                        durtv.setText(resources.getString(R.string.duration3));
//                        restv.setText(resources.getString(R.string.res3));
//                        insttv.setText(resources.getString(R.string.inst3));
//                        conttv.setText(resources.getString(R.string.cont3));
//                        klgtv.setText(resources.getString(R.string.klg3));
//                        asstv.setText(resources.getString(R.string.asses3));
//                        acttv.setText(resources.getString(R.string.activity3));
//                        appToOpen = "alphabets";
//                        break;
//                    case 4:
//                        if(lang.equals("hi")){
//                            context = LocaleHelper.setLocale(MainActivity.this, "hi");
//                        }else{
//                            context = LocaleHelper.setLocale(MainActivity.this, "en");
//                        }
//                        resources = context.getResources();
//                        headtv.setText(resources.getString(R.string.head4));
//                        durtv.setText(resources.getString(R.string.duration4));
//                        restv.setText(resources.getString(R.string.res4));
//                        insttv.setText(resources.getString(R.string.inst4));
//                        conttv.setText(resources.getString(R.string.cont4));
//                        klgtv.setText(resources.getString(R.string.klg4));
//                        asstv.setText(resources.getString(R.string.asses4));
//                        acttv.setText(resources.getString(R.string.activity4));
//                        appToOpen = "alphabets";
//                        break;
//                    case 5:
//                        if(lang.equals("hi")){
//                            context = LocaleHelper.setLocale(MainActivity.this, "hi");
//                        }else{
//                            context = LocaleHelper.setLocale(MainActivity.this, "en");
//                        }
//                        resources = context.getResources();
//                        headtv.setText(resources.getString(R.string.head5));
//                        durtv.setText(resources.getString(R.string.duration5));
//                        restv.setText(resources.getString(R.string.res5));
//                        insttv.setText(resources.getString(R.string.inst5));
//                        conttv.setText(resources.getString(R.string.cont5));
//                        klgtv.setText(resources.getString(R.string.klg5));
//                        asstv.setText(resources.getString(R.string.asses5));
//                        acttv.setText(resources.getString(R.string.activity5));
//                        appToOpen = "alphabets";
//                        break;
//                    case 6:
//                        appToOpen = "face";
//                        break;
//                    case 7:
//                        appToOpen = "human";
//                        break;
//                }
//                appChange.setText(appToOpen);
//            }else{
//                appChange.setVisibility(View.GONE);
//
//            }
//    }
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