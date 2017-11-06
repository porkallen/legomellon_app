package com.app.hp_app;

/**
 * Created by Nanica on 10/27/17.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.hp_app.hp_app.R;


public class SubPage2_Chapters extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_subpage1_chats, container, false);

        return rootView;
    }
}
