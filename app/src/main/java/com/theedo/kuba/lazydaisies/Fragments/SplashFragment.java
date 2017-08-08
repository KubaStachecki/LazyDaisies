package com.theedo.kuba.lazydaisies.Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.theedo.kuba.lazydaisies.R;


public class SplashFragment extends DialogFragment {


    private final int SPLASH_TIME_OUT = 6000;

    LottieAnimationView animationView;



    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setLayout(width, height);

    }

    public static SplashFragment newInstance() {

        SplashFragment fragment = new SplashFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // realnie pelen ekran ?

        //     setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);

    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_splash, container, false);


        animationView = (LottieAnimationView) view.findViewById(R.id.animation_view);


        playLogo();

        dialogDismiss();

        return view;

    }

    private void playLogo() {
        animationView.setAnimation("lazy_anim3.json");
        animationView.useExperimentalHardwareAcceleration(true);
        animationView.enableMergePathsForKitKatAndAbove(true);
        animationView.loop(false);
        animationView.playAnimation();
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
        this.setCancelable(false);
    }


    private void dialogDismiss() {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {

                dismiss();
            }
        };

        handler.postDelayed(runnable, SPLASH_TIME_OUT);
    }


}
