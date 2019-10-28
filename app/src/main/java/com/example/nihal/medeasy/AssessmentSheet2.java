package com.example.nihal.medeasy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nihal.medeasy.Models.AssessmentSheetModel;
import com.example.nihal.medeasy.Utils.Constants;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.hawk.Hawk;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AssessmentSheet2 extends AppCompatActivity {

    CheckBox level_of_consclousness_A;
    CheckBox level_of_consclousness_P;
    CheckBox level_of_consclousness_V;
    CheckBox level_of_consclousness_U;
    EditText pulse_rate;
    EditText pulse_rhythm;
    EditText pulse_equality;
    CheckBox peripheral_pulsation_felt;
    CheckBox peripheral_pulsation_notfelt;
    EditText vital_signs_bp_right;
    EditText vital_signs_bp_left;
    EditText vital_signs_temp;
    EditText vital_signs_rr;
    EditText vital_signs_o2_saturation;
    CheckBox chest_pain_yes;
    CheckBox chest_pain_no;
    CheckBox head_and_neck_neck_veins;
    CheckBox head_and_neck_other;
    CheckBox ll_oedema_yes;
    CheckBox ll_oedema_no;
    CheckBox ll_oedema_right;
    CheckBox ll_oedema_left;
    CheckBox ll_oedema_bilateral;
    CheckBox ll_oedema_pitting;
    CheckBox ll_oedema_nonpitting;
    AssessmentSheetModel model;
    String level_of_consclousness, pulse_rates, pulse_rhythms, pulse_equalitys, peripheral_pulsation, chest_pain, head_and_neck_neck, ll_oedema1, ll_oedema2, ll_oedema3;
    FirebaseDatabase database1;
    DatabaseReference myRef1;
    TextView save,  back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_sheet2);

        level_of_consclousness_A = findViewById(R.id.level_of_consclousness_A);
        level_of_consclousness_P = findViewById(R.id.level_of_consclousness_P);
        level_of_consclousness_V = findViewById(R.id.level_of_consclousness_V);
        level_of_consclousness_U = findViewById(R.id.level_of_consclousness_U);
        if (level_of_consclousness_A.isChecked()) {
            level_of_consclousness = "0";
        } else if (level_of_consclousness_P.isChecked()) {
            level_of_consclousness = "1";
        } else if (level_of_consclousness_V.isChecked()) {
            level_of_consclousness = "2";
        } else {
            level_of_consclousness = "3";
        }

        level_of_consclousness_A.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    level_of_consclousness_P.setChecked(false) ;
                    level_of_consclousness_V.setChecked(false) ;
                    level_of_consclousness_U.setChecked(false) ;
                }
            }
        });

        level_of_consclousness_P.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    level_of_consclousness_A.setChecked(false);
                    level_of_consclousness_V.setChecked(false) ;
                    level_of_consclousness_U.setChecked(false) ;
                }
            }
        });

        level_of_consclousness_V.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    level_of_consclousness_A.setChecked(false);
                    level_of_consclousness_P.setChecked(false) ;
                    level_of_consclousness_U.setChecked(false) ;
                }
            }
        });

        level_of_consclousness_U.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    level_of_consclousness_A.setChecked(false);
                    level_of_consclousness_P.setChecked(false) ;
                    level_of_consclousness_V.setChecked(false) ;
                }
            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        pulse_rate = findViewById(R.id.pulse_rate);
        pulse_rhythm = findViewById(R.id.pulse_rhythm);
        pulse_equality = findViewById(R.id.pulse_equality);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        peripheral_pulsation_felt = findViewById(R.id.peripheral_pulsation_felt);
        peripheral_pulsation_notfelt = findViewById(R.id.peripheral_pulsation_notfelt);
        if (peripheral_pulsation_felt.isChecked()) {
            peripheral_pulsation = "0";
        } else {
            peripheral_pulsation = "1";
        }

        peripheral_pulsation_felt.setChecked(true);
        peripheral_pulsation_felt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    peripheral_pulsation_notfelt.setChecked(false);
                }else{
                    peripheral_pulsation_notfelt.setChecked(true);
                }
            }
        });

        peripheral_pulsation_notfelt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    peripheral_pulsation_felt.setChecked(false);
                }else{
                    peripheral_pulsation_felt.setChecked(true);
                }
            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        vital_signs_bp_right = findViewById(R.id.vital_signs_bp_right);
        vital_signs_bp_left = findViewById(R.id.vital_signs_bp_left);
        vital_signs_temp = findViewById(R.id.vital_signs_temp);
        vital_signs_rr = findViewById(R.id.vital_sign_rr);
        vital_signs_o2_saturation = findViewById(R.id.vital_sign_o2_saturation);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        chest_pain_yes = findViewById(R.id.chest_pain_yes);
        chest_pain_no = findViewById(R.id.chest_pain_no);
        if (chest_pain_yes.isChecked()) {
            chest_pain = "0";
        } else {
            chest_pain = "1";
        }

        chest_pain_yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    chest_pain_no.setChecked(false);
                }else{
                    chest_pain_no.setChecked(true);
                }
            }
        });

        chest_pain_no.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    chest_pain_yes.setChecked(false);
                }else{
                    chest_pain_yes.setChecked(true);
                }
            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        head_and_neck_neck_veins = findViewById(R.id.head_and_neck_neck_veins);
        head_and_neck_other = findViewById(R.id.head_and_neck_other);
        if (head_and_neck_neck_veins.isChecked()) {
            head_and_neck_neck = "0";
        } else {
            head_and_neck_neck = "1";
        }

        head_and_neck_other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    head_and_neck_neck_veins.setChecked(false);
                }else{
                    head_and_neck_neck_veins.setChecked(true);
                }
            }
        });

        head_and_neck_neck_veins.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    head_and_neck_other.setChecked(false);
                }else{
                    head_and_neck_other.setChecked(true);
                }
            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ll_oedema_yes = findViewById(R.id.ll_oedema_yes);
        ll_oedema_no = findViewById(R.id.ll_oedema_no);
        if (ll_oedema_yes.isChecked()) {
            ll_oedema1 = "0";
        } else {
            ll_oedema1 = "1";
        }

        ll_oedema_yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    ll_oedema_no.setChecked(false);
                }else{
                    ll_oedema_no.setChecked(true);
                }
            }
        });

        ll_oedema_no.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    ll_oedema_yes.setChecked(false);
                }else{
                    ll_oedema_yes.setChecked(true);
                }
            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ll_oedema_right = findViewById(R.id.ll_oedema_right);
        ll_oedema_left = findViewById(R.id.ll_oedema_left);
        ll_oedema_bilateral = findViewById(R.id.ll_oedema_bilateral);
        if (ll_oedema_right.isChecked()) {
            ll_oedema2 = "0";
        } else if (ll_oedema_left.isChecked()) {
            ll_oedema2 = "1";
        } else {
            ll_oedema2 = "2";
        }

        ll_oedema_right.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true) {
                    ll_oedema_left.setChecked(false);
                    ll_oedema_bilateral.setChecked(false);
                }
            }
        });

        ll_oedema_left.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true) {
                    ll_oedema_right.setChecked(false);
                    ll_oedema_bilateral.setChecked(false);
                }
            }
        });

        ll_oedema_bilateral.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true) {
                    ll_oedema_right.setChecked(false);
                    ll_oedema_left.setChecked(false);
                }
            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ll_oedema_pitting = findViewById(R.id.ll_oedema_pitting);
        ll_oedema_nonpitting = findViewById(R.id.ll_oedema_nonpitting);
        if (ll_oedema_pitting.isChecked()) {
            ll_oedema3 = "0";
        } else {
            ll_oedema3 = "1";
        }


        ll_oedema_pitting.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    ll_oedema_nonpitting.setChecked(false);
                }else{
                    ll_oedema_nonpitting.setChecked(true);
                }
            }
        });

        ll_oedema_nonpitting.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    ll_oedema_pitting.setChecked(false);
                }else{
                    ll_oedema_pitting.setChecked(true);
                }
            }
        });


        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AssessmentSheet2.this,AssessmentSheet.class));
            }
        });


        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String PulseRate              = pulse_rate.getText().toString();
                final String PulseEquality          = pulse_equality.getText().toString();
                final String PulseRhythm            = pulse_rhythm.getText().toString();
                final String VitalSignsBpRight      = vital_signs_bp_right.getText().toString();
                final String VitalSignsBpLeft       = vital_signs_bp_left.getText().toString();
                final String VitalSignsTemp         = vital_signs_temp.getText().toString();
                final String VitalSignsRR           = vital_signs_rr.getText().toString();
                final String VitalSignsOsaturation  = vital_signs_o2_saturation.getText().toString();

                if (PulseRate.isEmpty()) {
                    pulse_rate.setError(" Enter Pulse Rate ");
                }else if (PulseEquality.isEmpty()) {
                    pulse_equality.setError(" Enter Pulse Equality ");
                }else if (PulseRhythm.isEmpty()) {
                    pulse_rhythm.setError(" Enter Pulse Rhythm ");
                }else if (VitalSignsBpRight.isEmpty()) {
                    vital_signs_bp_right.setError(" Enter Bp Right ");
                }else if (VitalSignsBpLeft.isEmpty()) {
                    vital_signs_bp_left.setError(" Enter Bp Left ");
                }else if (VitalSignsTemp.isEmpty()) {
                    vital_signs_temp.setError(" Enter Temp ");
                }else if (VitalSignsRR.isEmpty()) {
                    vital_signs_rr.setError(" Enter RR ");
                }else if (VitalSignsOsaturation.isEmpty()) {
                    vital_signs_o2_saturation.setError(" Enter O2 Saturation ");
                }

                else {

                    model = Hawk.get("model");
                    model.setLevel_of_consclousness(level_of_consclousness);
                    model.setPulse_rate(pulse_rate.getText().toString());
                    model.setPulse_equality(pulse_equality.getText().toString());
                    Log.e("TTT", "onCreate: " + pulse_equality.getText().toString());
                    model.setPulse_rhythm(pulse_rhythm.getText().toString());
                    model.setPeripheral_pulsation(peripheral_pulsation);
                    model.setVital_signs_bp_right(vital_signs_bp_right.getText().toString());
                    model.setVital_signs_bp_left(vital_signs_bp_left.getText().toString());
                    model.setVital_signs_rr(vital_signs_rr.getText().toString());
                    model.setVital_signs_o2_saturation(vital_signs_o2_saturation.getText().toString());
                    model.setChest_pain(chest_pain);
                    model.setHead_and_neck_neck(head_and_neck_neck);
                    model.setLl_oedema_1(ll_oedema1);
                    model.setLl_oedema_2(ll_oedema2);
                    model.setLl_oedema_3(ll_oedema3);
                    model.setVital_signs_temp(vital_signs_temp.getText().toString());
                    Date c = Calendar.getInstance().getTime();
                    System.out.println("Current time => " + c);

                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                    String formattedDate = df.format(c);
                    model.setDate(formattedDate);
                    writeOnDataBase(model);
                }
            }
        });
    }

    public void writeOnDataBase(AssessmentSheetModel model) {
        database1 = FirebaseDatabase.getInstance();
        String key;
        myRef1 = database1.getReference("Users").child(""+Hawk.get(Constants.patientID)).child("Roshetat");
        if(Hawk.contains("Key")){



            key=Hawk.get("Key");



        }else {
            key = myRef1.push().getKey();
            Hawk.put("Key",key);
        }

        myRef1.child(key).child("Rosheta").child("Genaral").setValue(model);
        //Log.d("TTTT", "writeOnDataBase: " + key);
    }
}