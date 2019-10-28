package com.example.nihal.medeasy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nihal.medeasy.Models.AssessmentSheetModel;
import com.example.nihal.medeasy.Models.Drugs;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.hawk.Hawk;

public class AssessmentSheet extends AppCompatActivity {

    private EditText yourComplaint;
    private LinearLayout pastHistory, go_profile;
    private LinearLayout hideLinearLayoutPastHistory;
    private EditText pasthistory_editText;
    FirebaseDatabase database;
    DatabaseReference myRef;
    CheckBox DM_yes, DM_no, Smoking_yes, Smoking_no, hypertension_yes, hypertension_no, dyslipidemia_yes, dyslipidemia_no;
    CheckBox generalLook_normal, generalLook_pale, generalLook_cyanosed, generalLook_sweaty;
    String DM, Smoking, hypertension, dyslipidemia, generalLook;
    TextView next;

    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_sheet);

        yourComplaint = findViewById(R.id.yourComplaint);
        DM_yes = findViewById(R.id.DM_yes);
        DM_no = findViewById(R.id.DM_no);

        if (DM_yes.isChecked()) {
            DM = "1";
        } else {
            DM = "0";
        }

        DM_yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    DM_no.setChecked(false);
                }else{
                    DM_no.setChecked(true);
                }
            }
        });

        DM_no.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    DM_yes.setChecked(false);
                }else{
                    DM_yes.setChecked(true);
                }
            }
        });



        Smoking_yes = findViewById(R.id.Smoking_yes);
        Smoking_no = findViewById(R.id.Smoking_no);
        if (Smoking_yes.isChecked()) {
            Smoking = "1";
        } else {
            Smoking = "0";
        }

        Smoking_no.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    Smoking_yes.setChecked(false);
                }else{
                    Smoking_yes.setChecked(true);
                }
            }
        });

        Smoking_yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    Smoking_no.setChecked(false);
                }else{
                    Smoking_no.setChecked(true);
                }
            }
        });

        hypertension_yes = findViewById(R.id.hypertension_yes);
        hypertension_no = findViewById(R.id.hypertension_no);
        if (hypertension_yes.isChecked()) {
            hypertension = "1";
        } else {
            hypertension = "0";
        }

        hypertension_yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    hypertension_no.setChecked(false);
                }else{
                    hypertension_no.setChecked(true);
                }
            }
        });

        hypertension_no.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    hypertension_yes.setChecked(false);
                }else{
                    hypertension_yes.setChecked(true);
                }
            }
        });


        dyslipidemia_yes = findViewById(R.id.dyslipidemia_yes);
        dyslipidemia_no = findViewById(R.id.dyslipidemia_no);
        if (dyslipidemia_yes.isChecked()) {
            dyslipidemia = "1";
        } else {
            dyslipidemia = "0";
        }

        dyslipidemia_yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    dyslipidemia_no.setChecked(false);
                }else{
                    dyslipidemia_no.setChecked(true);
                }
            }
        });

        dyslipidemia_no.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    dyslipidemia_yes.setChecked(false);
                }else{
                    dyslipidemia_yes.setChecked(true);
                }
            }
        });


        generalLook_normal = findViewById(R.id.generalLook_normal);
        generalLook_pale = findViewById(R.id.generalLook_pale);
        generalLook_cyanosed = findViewById(R.id.generalLook_cyanosed);
        generalLook_sweaty = findViewById(R.id.generalLook_sweaty);
        if (generalLook_normal.isChecked()) {
            generalLook = "1";
        } else if (generalLook_pale.isChecked()) {
            generalLook = "2";
        } else if (generalLook_cyanosed.isChecked()) {
            generalLook = "3";
        } else {
            generalLook="4";
        }


        generalLook_normal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    generalLook_pale.setChecked(false) ;
                    generalLook_cyanosed.setChecked(false) ;
                    generalLook_sweaty.setChecked(false) ;
                }
            }
        });

        generalLook_pale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    generalLook_normal.setChecked(false);
                    generalLook_cyanosed.setChecked(false) ;
                    generalLook_sweaty.setChecked(false) ;
                }
            }
        });

        generalLook_cyanosed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    generalLook_normal.setChecked(false);
                    generalLook_pale.setChecked(false) ;
                    generalLook_sweaty.setChecked(false) ;
                }
            }
        });

        generalLook_sweaty.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    generalLook_normal.setChecked(false);
                    generalLook_pale.setChecked(false) ;
                    generalLook_cyanosed.setChecked(false) ;
                }
            }
        });

        go_profile = findViewById(R.id.go_profile);
        go_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AssessmentSheet.this,ProfileActivity.class));
            }
        });
        next=findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String YourComplaint = yourComplaint.getText().toString();

                if (YourComplaint.isEmpty()) {
                    yourComplaint.setError(" Enter Complaint ");
                } else {
                    Hawk.init(AssessmentSheet.this).build();
                    AssessmentSheetModel model = new AssessmentSheetModel("" + yourComplaint.getText().toString()
                            , "" + pasthistory_editText.getText().toString()
                            , "", "" + DM, "" + Smoking
                            , "" + hypertension, "" + dyslipidemia, "" + generalLook,


                            "", "", "", "", ""
                            , "", "", "", "", "", ""
                            , "", "", "", "", "");
                    Hawk.put("model", model);
                    startActivity(new Intent(AssessmentSheet.this, AssessmentSheet2.class));
                }
            }
        });


        //visible the editText in pastHistory Section
        pastHistory = findViewById(R.id.pastHistory_Section);
        hideLinearLayoutPastHistory = findViewById(R.id.hideLinearLayoutPastHistory);
        pasthistory_editText = findViewById(R.id.pasthistory_editText);
        pastHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == 0) {
                    flag = 1;
                    pasthistory_editText.setVisibility(View.VISIBLE);
                } else {
                    flag = 0;
                    pasthistory_editText.setVisibility(View.INVISIBLE);
                }
            }
        });

    }


}