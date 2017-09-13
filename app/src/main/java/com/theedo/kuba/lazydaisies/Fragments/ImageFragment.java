package com.theedo.kuba.lazydaisies.Fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.theedo.kuba.lazydaisies.MainActivity;
import com.theedo.kuba.lazydaisies.Model.Image;
import com.theedo.kuba.lazydaisies.R;
import com.theedo.kuba.lazydaisies.Utils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ImageFragment extends Fragment  {

    @BindView(R.id.big_image)
    ImageView bigImage;

    @BindView(R.id.save_btn)
    AppCompatButton saveBtn;

    @BindView(R.id.share_btn)
    AppCompatButton shareBtn;

    @BindView(R.id.set_btn)
    AppCompatButton setBtn;

    @BindView(R.id.loaderImage)
    ProgressBar loaderImage;


    Utils utils;
    FragmentUtils mainUtils;

    Image image;
    Bitmap thisBitmap;

    String imageName;


    public ImageFragment() {
        // Required empty public constructor
    }

    public static ImageFragment newInstance(Bundle bundle) {
        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(bundle);


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MainActivity) getActivity()).getSupportActionBar().hide();

        mainUtils = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        ButterKnife.bind(this, view);

        image = getArguments().getParcelable("Image");


        loaderImage.setVisibility(View.VISIBLE);
        bigImage.setVisibility(View.VISIBLE);
        final ProgressBar progressView = loaderImage;


        Picasso.with(getContext()).load(image.getUrl())

                .fit()
                .centerCrop()
                .into(bigImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        thisBitmap = ((BitmapDrawable) bigImage.getDrawable()).getBitmap();

                        progressView.setVisibility(View.GONE);

                    }

                    @Override
                    public void onError() {
                        mainUtils.showSnackbar("Error - check internet connection") ;                   }
                });


        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        utils = new Utils(getActivity());
        String[] urlParts = image.getUrl().split("-");
        imageName = urlParts[urlParts.length - 1];


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (thisBitmap == null) {
                    mainUtils.showSnackbar("Error - image not loaded") ;

                } else {
                    utils.saveImageToSDCard(thisBitmap, imageName);
                }


            }
        });

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (thisBitmap == null) {
                    mainUtils.showSnackbar("Error - image not loaded") ;
                } else {
                    utils.shareWallpaperUrl(thisBitmap, imageName);
                }

            }
        });

        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (thisBitmap == null) {
                    mainUtils.showSnackbar("Error - image not loaded") ;
                } else {
                    utils.setAsWallpaper(thisBitmap, imageName);
                }
            }
        });

    }

    @Override
    public void onDetach() {
        super.onDetach();

        image = null;
        thisBitmap = null;
        imageName = null;
    }


}