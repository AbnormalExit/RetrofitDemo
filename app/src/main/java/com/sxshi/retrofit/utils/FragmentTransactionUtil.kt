package com.sxshi.retrofit.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentTransactionUtil private constructor() {
    companion object {
        @JvmStatic
        fun addFragment(fragmentManager: FragmentManager,
                        fragment: Fragment,
                        @IdRes fragmentContainerId: Int,
                        addToBackStack: Boolean) {
            val transaction = fragmentManager.beginTransaction()
            transaction.add(fragmentContainerId, fragment)
            if (addToBackStack) {
                transaction.addToBackStack(fragment.javaClass.simpleName)
            }
            transaction.commit()
        }

        fun replaceFragment(fragmentManager: FragmentManager,
                            fragment: Fragment,
                            @IdRes fragmentContainerId: Int,
                            addToBackStack: Boolean) {
            val transaction = fragmentManager.beginTransaction()
            //    transaction.setCustomAnimations(
//        R.anim.slide_in_bottom, R.anim.zoom_out,
//        R.anim.zoom_out, R.anim.slide_out_bottom);
            transaction.replace(fragmentContainerId, fragment)
            if (addToBackStack) {
                transaction.addToBackStack(fragment.javaClass.simpleName)
            }
            transaction.commit()
        }
    }

    init {
        throw AssertionError()
    }
}