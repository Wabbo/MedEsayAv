package com.example.nihal.medeasy.Dialoge;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nihal.medeasy.Models.Pressure_Sugar_Model;
import com.example.nihal.medeasy.R;
import com.example.nihal.medeasy.Utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.hawk.Hawk;


public class SuagrPressureDialoge extends Dialog {
    Context context;
    Dialog d;
    Button save,cancel;
    EditText sugar_level,high,low;
    Pressure_Sugar_Model pressure_sugar_model;
    FirebaseDatabase database;
    DatabaseReference myRef;

    public SuagrPressureDialoge(Context context) {
        super(context);
        this.context=context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pressure_sugar_level);
        d=this;
        sugar_level = findViewById(R.id.sugar_level);
        high = findViewById(R.id.high);
        low = findViewById(R.id.low);
        save = findViewById(R.id.save);
        cancel= findViewById(R.id.cancel);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(
                        !sugar_level.getText().toString().trim().equals("")
                        &&!high.getText().toString().trim().equals("")
                        &&!low.getText().toString().trim().equals("")
                ){
                    pressure_sugar_model=new Pressure_Sugar_Model( sugar_level.getText().toString(),
                            high.getText().toString(),
                            low.getText().toString());
                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference("Users");

                    myRef.child(""+Hawk.get(Constants.patientID)).child("ChartsData").push()
                            .setValue(pressure_sugar_model).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            dismiss();

                        }
                    });

                }else {
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
    }
}
