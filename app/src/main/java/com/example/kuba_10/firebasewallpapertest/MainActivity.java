package com.example.kuba_10.firebasewallpapertest;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.example.kuba_10.firebasewallpapertest.Fragments.FragmentUtils;
import com.example.kuba_10.firebasewallpapertest.Fragments.GalleryFragment;
import com.example.kuba_10.firebasewallpapertest.Fragments.MapFragment;
import com.example.kuba_10.firebasewallpapertest.Fragments.NewsFragment;
import com.example.kuba_10.firebasewallpapertest.Fragments.SplashFragment;
import com.example.kuba_10.firebasewallpapertest.Fragments.StartFragment;
import com.example.kuba_10.firebasewallpapertest.Model.Image;
import com.example.kuba_10.firebasewallpapertest.Model.Staff;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FragmentUtils, View.OnClickListener {


    ArrayList<Image> imageList;
    ArrayList<Staff> staffList;
    Bundle listBundle;
    CoordinatorLayout coordinatorLayout;
    Context context;


    public static final String TAAAAG = "MAIN ACTIVITY TAG";
    private static final int REQUEST_WRITE_PERMISSION = 786;

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3, floatingActionButton4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        context = getApplicationContext();


        imageList = new ArrayList<>();
        staffList = new ArrayList<>();

        getImagesFromFirebase();


        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);
        floatingActionButton4 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item4);

        floatingActionButton1.setOnClickListener(this);
        floatingActionButton2.setOnClickListener(this);
        floatingActionButton3.setOnClickListener(this);
        floatingActionButton3.setOnClickListener(this);
        floatingActionButton4.setOnClickListener(this);


        hideTaskAndActrionBar();

        SplashFragment.newInstance().show(getSupportFragmentManager(), "");


        openFragment(StartFragment.newInstance());


        materialDesignFAM.close(true);


        listBundle = new Bundle();
        listBundle.putParcelableArrayList("imagelist", imageList);
        listBundle.putParcelableArrayList("newslist", staffList);


    }


    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        hideTaskAndActrionBar();
    }


    @Override
    public void openFragment(Fragment fragment) {

        this.getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                .replace(R.id.main_container, fragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {


            case R.id.material_design_floating_action_menu_item1:

                openFragment(MapFragment.newInstance());
                materialDesignFAM.close(true);
//                main_container.setBackgroundColor(getResources().getColor(R.color.gray));


                break;


            case R.id.material_design_floating_action_menu_item2:


                openFragment(StartFragment.newInstance());
                materialDesignFAM.close(true);

                break;

            case R.id.material_design_floating_action_menu_item3:


                openFragment(NewsFragment.newInstance(listBundle));
                materialDesignFAM.close(true);


                break;


            case R.id.material_design_floating_action_menu_item4:


                openFragment(GalleryFragment.newInstance(listBundle));
                materialDesignFAM.close(true);


                break;


        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        }
    }


     public void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        }
    }


    private void hideTaskAndActrionBar() {

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        getSupportActionBar().hide();
    }


    public void getImagesFromFirebase() {
        FirebaseDatabase fDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = fDatabase.getReference();
        databaseReference.child("wallpapers").addValueEventListener(new com.google.firebase.database.ValueEventListener() {

            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                imageList.clear();
                for (com.google.firebase.database.DataSnapshot child : dataSnapshot.getChildren()) {
                    Image image = child.getValue(Image.class);
                    imageList.add(image);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                showSnackbar("Error - check internet connection") ;

            }
        });

        databaseReference.child("news").addValueEventListener(new com.google.firebase.database.ValueEventListener() {

            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                staffList.clear();
                for (com.google.firebase.database.DataSnapshot child : dataSnapshot.getChildren()) {
                    Staff staff = child.getValue(Staff.class);
                    staffList.add(staff);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

                showSnackbar("Error - check internet connection") ;


            }


        });

    }

    public void showSnackbar(String text){

        Snackbar.make(coordinatorLayout, text, Snackbar.LENGTH_LONG).show();
    }

}



