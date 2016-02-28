package com.chilangolabs.modelonow.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chilangolabs.modelonow.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPromos extends Fragment {


    public FragmentPromos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragment_promos, container, false);
        return rootView;
    }

}
