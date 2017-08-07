package com.example.kuba_10.firebasewallpapertest.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.kuba_10.firebasewallpapertest.Adapters.WallAdapter;
import com.example.kuba_10.firebasewallpapertest.MainActivity;
import com.example.kuba_10.firebasewallpapertest.Model.Image;
import com.example.kuba_10.firebasewallpapertest.R;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class GalleryFragment extends Fragment {


    WallAdapter wallAdapter;
    private GridLayoutManager gridManager;
    private RecyclerView recyclerView;
    private FrameLayout frameLayout;
    private RelativeLayout imageContainer;


    ArrayList<Image> imageList;
    FragmentUtils mainActivity;


    public GalleryFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static GalleryFragment newInstance(Bundle bundle) {


        GalleryFragment fragment = new GalleryFragment();

        fragment.setArguments(bundle);


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         mainActivity = (MainActivity) getActivity();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_gallery, container, false);


        frameLayout = (FrameLayout) view.findViewById(R.id.container);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        gridManager = new GridLayoutManager(getActivity(), 2);


        recyclerView.setLayoutManager(gridManager);


        imageList = getArguments().getParcelableArrayList("imagelist");

        wallAdapter = new WallAdapter(getActivity(), imageList);
        recyclerView.setAdapter(wallAdapter);

        wallAdapter.notifyDataSetChanged();




        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


        mainActivity.requestPermission();


    }
}
