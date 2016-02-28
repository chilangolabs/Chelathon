package com.chilangolabs.modelonow.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chilangolabs.modelonow.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserInfoFragment extends Fragment {

    LinearLayout addCardLayout;

    public UserInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_user_info, container, false);

        addCardLayout = (LinearLayout) rootView.findViewById(R.id.linearAddCard);


        addCardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return rootView;
    }

}
