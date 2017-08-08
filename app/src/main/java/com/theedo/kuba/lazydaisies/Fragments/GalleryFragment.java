package com.theedo.kuba.lazydaisies.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.theedo.kuba.lazydaisies.Adapters.WallAdapter;
import com.theedo.kuba.lazydaisies.MainActivity;
import com.theedo.kuba.lazydaisies.Model.Image;
import com.theedo.kuba.lazydaisies.R;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {


    WallAdapter wallAdapter;
    private GridLayoutManager gridManager;
    private RecyclerView recyclerView;
    private FrameLayout frameLayout;
    private RelativeLayout imageContainer;
    private ImageView errorImage;


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

        errorImage = (ImageView) view.findViewById(R.id.error_image_gallery);



        frameLayout = (FrameLayout) view.findViewById(R.id.container);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        gridManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridManager);


        imageList = getArguments().getParcelableArrayList("imagelist");

        wallAdapter = new WallAdapter(getActivity(), imageList);
        recyclerView.setAdapter(wallAdapter);

        wallAdapter.notifyDataSetChanged();
        errorImage.setVisibility(View.GONE);




        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


        mainActivity.requestPermission();

        if (imageList.size() == 0){


            errorImage.setVisibility(View.VISIBLE);


        }



    }
}
