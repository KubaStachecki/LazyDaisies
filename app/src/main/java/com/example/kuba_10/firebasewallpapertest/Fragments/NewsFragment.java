package com.example.kuba_10.firebasewallpapertest.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.kuba_10.firebasewallpapertest.Adapters.ExpandableRecyclerAdapter;
import com.example.kuba_10.firebasewallpapertest.MainActivity;
import com.example.kuba_10.firebasewallpapertest.Model.Staff;
import com.example.kuba_10.firebasewallpapertest.R;

import java.util.ArrayList;

public class NewsFragment extends Fragment {

    ArrayList<Staff> staffList;
    private RecyclerView recyclerView;
    private ImageView errorImage;

    FragmentUtils mainActivity;



    public NewsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(Bundle bundle) {
        NewsFragment fragment = new NewsFragment();

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

        View view =  inflater.inflate(R.layout.fragment_news, container, false);

        errorImage = (ImageView) view.findViewById(R.id.error_image_news);


        staffList = getArguments().getParcelableArrayList("newslist");

        recyclerView = (RecyclerView) view.findViewById(R.id.news_recycle);
        errorImage.setVisibility(View.GONE);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();



        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ExpandableRecyclerAdapter(staffList));



        if (staffList.size() == 0){


            errorImage.setVisibility(View.VISIBLE);

        }
    }
}
