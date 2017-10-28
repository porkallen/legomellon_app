package com.example.allen.hp_app;

/**
 * Created by Nanica on 10/27/17.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class subPage2_Chapters extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.subpage1_chats, container, false);

        return rootView;
    }
}
