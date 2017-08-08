package com.theedo.kuba.lazydaisies.Fragments;

import android.support.v4.app.Fragment;

/**
 * Created by Kuba-10 on 14.07.2017.
 */
public interface FragmentUtils {

    void openFragment(Fragment fragment);

    void requestPermission();

    void showSnackbar(String text);

    void onBackPressed();




}
