package com.example.nihal.medeasy.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nihal.medeasy.Adapters.AssessmentSheetsModleAdapter;
import com.example.nihal.medeasy.AssessmentSheetAfterSecion;
import com.example.nihal.medeasy.Models.AssessmentSheetModel;
import com.example.nihal.medeasy.Models.AssessmentSheetsModle;
import com.example.nihal.medeasy.Models.Drugs;
import com.example.nihal.medeasy.R;
import com.example.nihal.medeasy.Utils.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

public class AssessmentSheetsFragment extends Fragment {

    RecyclerView recyclerView;

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference myRef;

    List<AssessmentSheetModel> ASModelList = new ArrayList<>();
    //AssessmentSheetModel assessmentSheetModel=new AssessmentSheetModel("", "", "","","","", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");


    AssessmentSheetsModleAdapter adapter;


    public AssessmentSheetsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        ASModelList.clear();
        View view = inflater.inflate(R.layout.fragment_assessment_sheets, container, false);
        adapter = new AssessmentSheetsModleAdapter(ASModelList, new AssessmentSheetsModleAdapter.OnItemClick() {
            @Override
            public void setOnItemClick(int position) {
                Hawk.put(Constants.assmentModel,ASModelList.get(position));
                getContext().startActivity(new Intent(getContext(), AssessmentSheetAfterSecion.class));
            }
        });
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users").child(FirebaseAuth.getInstance().getUid()).child("Roshetat");
        recyclerView = view.findViewById(R.id.AssessmentSheetsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);


        getDataFromFireBase();


        return view;
    }


    public void getDataFromFireBase() {

        Query query = myRef.orderByKey();

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //ASModelList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Log.d("TTTTTT", "onDataChange: " + snapshot.toString());
                    for (final DataSnapshot tData : snapshot.child("Rosheta").getChildren()) {
                        Log.d("Rosheta/Genaral", "onDataChange: " + tData.toString());
                        Log.d("last  <>   Loop", "onDataChange: " + tData.toString());

                        try {
                            AssessmentSheetModel sheetModel = tData.getValue(AssessmentSheetModel.class);
                            Log.d("TTTTTTTTTT", "onDataChange:1111111 " + sheetModel.getChest_pain());
                            if (sheetModel.getChest_pain() != null) {
                                ASModelList.add(sheetModel);
                            }
                            Log.d("TTTTTTTTTT", "onDataChange:1111111 " + ASModelList.size());
                            adapter.notifyDataSetChanged();

                        } catch (Exception e) {
                            Log.d("TTTTTTTTTT", "onDataChange:1111111 " + e.toString());

                        }


                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /**  -------------------------------  **/


    }


    /**/

}
