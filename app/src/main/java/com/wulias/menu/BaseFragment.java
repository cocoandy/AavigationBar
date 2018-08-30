package com.wulias.menu;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 曹小贼 on 2018/8/30.
 */

public abstract class BaseFragment extends Fragment {

    public abstract String getName();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.e(getName(),"Fragment--onAttach()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(getName(),"Fragment--onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(getName(),"Fragment--onCreateView()");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(getName(),"Fragment--onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(getName(),"Fragment--onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(getName(),"Fragment--onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(getName(),"Fragment--onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(getName(),"Fragment--onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(getName(),"Fragment--onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(getName(),"Fragment--onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(getName(),"Fragment--onDetach()");
    }
}
