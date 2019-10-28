package com.example.nihal.medeasy;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.nihal.medeasy.Fragment.AssessmentSheetsFragment;
import com.example.nihal.medeasy.Fragment.PostFragment;
import com.example.nihal.medeasy.Fragment.ProfileFragment;
import com.example.nihal.medeasy.Models.UserModel;
import com.example.nihal.medeasy.Utils.Constants;
import com.example.nihal.medeasy.widget.CanaroTextView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.orhanobut.hawk.Hawk;
import com.yalantis.guillotine.animation.GuillotineAnimation;

import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {
    private static final long RIPPLE_DURATION = 250;
    Toolbar toolbar;
    FrameLayout root;
    View contentHamburger;
    AHBottomNavigation bottom_navigation;
    CanaroTextView addCategory, addSyndr;
    com.example.nihal.medeasy.widget.CanaroTextView Account_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.toolbar);
        root = findViewById(R.id.root);
        contentHamburger = findViewById(R.id.guillotine_icon_nav);
        bottom_navigation = findViewById(R.id.bottom_navigation);
        final ProfileFragment profileFragment = new ProfileFragment();
        final AssessmentSheetsFragment assessmentSheetsFragment = new AssessmentSheetsFragment();
        final PostFragment postFragment = new PostFragment();


        //NavigationDrawer

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.pre, R.drawable.archive, R.color.white);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.Home, R.drawable.homepage, R.color.white);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.Profile, R.drawable.male1, R.color.white);

// Add items
        bottom_navigation.addItem(item1);
        bottom_navigation.addItem(item2);
        bottom_navigation.addItem(item3);

// Set background color
        bottom_navigation.setDefaultBackgroundColor(Color.parseColor("#94d6ff"));

// Disable the translation inside the CoordinatorLayout
        // bottom_navigation.setBehaviorTranslationEnabled(false);

// Enable the translation of the FloatingActionButton
        // bottom_navigation.manageFloatingActionButtonBehavior(floatingActionButton);

// Change colors
        bottom_navigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottom_navigation.setInactiveColor(Color.parseColor("#ffffff"));

// Force to tint the drawable (useful for font with icon for example)
        bottom_navigation.setForceTint(true);

// Display color under navigation bar (API 21+)
// Don't forget these lines in your style-v21
// <item name="android:windowTranslucentNavigation">true</item>
// <item name="android:fitsSystemWindows">true</item>
        //bottom_navigation.setTranslucentNavigationEnabled(true);

// Manage titles
        bottom_navigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
        //  bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        //  bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);

// Use colored navigation with circle reveal effect
        // bottomNavigation.setColored(true);

// Set current item programmatically
        bottom_navigation.setCurrentItem(1);

// Customize notification (title, background, typeface)
        //  bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));

// Add or remove notification for each item
        // bottomNavigation.setNotification("1", 3);
// OR
     /*   AHNotification notification = new AHNotification.Builder()
                .setText("1")
                .setBackgroundColor(ContextCompat.getColor(DemoActivity.this, R.color.color_notification_back))
                .setTextColor(ContextCompat.getColor(DemoActivity.this, R.color.color_notification_text))
                .build();
        bottomNavigation.setNotification(notification, 1);
*/
// Enable / disable item & set disable color
        //  bottomNavigation.enableItemAtPosition(2);
        // bottomNavigation.disableItemAtPosition(2);
        // bottomNavigation.setItemDisableColor(Color.parseColor("#3A000000"));

// Set listeners
        bottom_navigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                // Do something cool here...
                if (position == 0) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container, assessmentSheetsFragment);
                    fragmentTransaction.commit();
                } else if (position == 1) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container, postFragment);
                    fragmentTransaction.commit();
                } else {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container, profileFragment);
                    fragmentTransaction.commit();
                }
                return true;
            }
        });
        bottom_navigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override
            public void onPositionChange(int y) {
                // Manage the new y position
            }
        });


        ////


        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(null);
        }

        View guillotineMenu = LayoutInflater.from(this).inflate(R.layout.guillotine, null);
        Account_name=guillotineMenu.findViewById(R.id.Account_name);

        returnData();



        //here will cntrol galilio items
        final ImageView profile = guillotineMenu.findViewById(R.id.profile_img_galilio);
        try {
            StorageReference mImageRef;
            mImageRef =
                    FirebaseStorage.getInstance()
                            .getReference("images/" + Hawk.get(Constants.userID) + "");

            final long ONE_MEGABYTE = 1024 * 1024;
            mImageRef.getBytes(ONE_MEGABYTE)
                    .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            Log.d("TTTTTTTTTTT", "onSuccess: " + bytes.length);
                            Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            DisplayMetrics dm = new DisplayMetrics();
                            HomeActivity.this.getWindowManager().getDefaultDisplay().getMetrics(dm);
                            profile.setImageBitmap(bm);
                        }

                    });
            mImageRef.getBytes(ONE_MEGABYTE).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("TTTTTTTTTTT", "onSuccess: " + e.getMessage());

                }
            });
        } catch (Exception e) {
            Log.d("TTTTTTTTTTT", "onSuccess: " + e.getMessage());

        }
        LinearLayout test = guillotineMenu.findViewById(R.id.ctyg_section);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoryDialog categoryDialog = new CategoryDialog(HomeActivity.this);
                categoryDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                categoryDialog.show();
            }
        });
        LinearLayout alarm_section = guillotineMenu.findViewById(R.id.alarm_section);
        alarm_section.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, AlarmActivity.class));
            }
        });

        //go to synder
        LinearLayout test1 = guillotineMenu.findViewById(R.id.synd_section);
        test1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, SyndrActivity.class));
            }
        });
        LinearLayout medicine = guillotineMenu.findViewById(R.id.drugs_section);
        medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, SecondActivity.class));
            }
        });


        //here will be initila galilio

        root.addView(guillotineMenu);

        new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_icon_nav), contentHamburger)
                .setStartDelay(RIPPLE_DURATION)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(HomeActivity.this)
                .setTitle("")
                .setMessage("are you sure you want to out ?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        finish();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();


    }
    private void returnData() {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Users").child(Hawk.get(Constants.userID) + "")
                .child("Info").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    UserModel userModel = dataSnapshot.getValue(UserModel.class);
                    Account_name.setText(userModel.getUserName());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
