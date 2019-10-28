package com.example.nihal.medeasy.Dialoge;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nihal.medeasy.R;
import com.example.nihal.medeasy.Utils.Constants;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.hawk.Hawk;


public class BloodDialoge extends Dialog {
    Context context;
    Dialog d;
    Button save,cancel;
    EditText blood_type;

    FirebaseDatabase database;
    DatabaseReference myRef;

    public BloodDialoge(Context context) {
        super(context);
        this.context=context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blood_dialog);
        d=this;
        blood_type = findViewById(R.id.blood_type);
        save = findViewById(R.id.save);
        cancel= findViewById(R.id.cancel);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference("Users");
                myRef.child(""+Hawk.get(Constants.patientID)).child("Info").child("bloodType").setValue(""+blood_type.getText().toString());
                dismiss();

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
