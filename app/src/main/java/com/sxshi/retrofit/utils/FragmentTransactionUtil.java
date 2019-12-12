package com.sxshi.retrofit.utils;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public final class FragmentTransactionUtil {

    private FragmentTransactionUtil() {
        throw new AssertionError();
    }

    public static void addFragment(FragmentManager fragmentManager,
                                   @NonNull Fragment fragment,
                                   @IdRes int fragmentContainerId,
                                   boolean addToBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(fragmentContainerId, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        transaction.commit();
    }

    public static void replaceFragment(FragmentManager fragmentManager,
                                       @NonNull Fragment fragment,
                                       @IdRes int fragmentContainerId,
                                       boolean addToBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
//    transaction.setCustomAnimations(
//        R.anim.slide_in_bottom, R.anim.zoom_out,
//        R.anim.zoom_out, R.anim.slide_out_bottom);
        transaction.replace(fragmentContainerId, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        transaction.commit();
    }
}