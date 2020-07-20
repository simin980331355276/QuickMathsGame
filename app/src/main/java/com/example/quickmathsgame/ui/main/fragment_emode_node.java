package com.example.quickmathsgame.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.quickmathsgame.R;

public class fragment_emode_node extends Fragment {

    private FragmentEmodeNodeViewModel mViewModel;

    public static fragment_emode_node newInstance() {
        return new fragment_emode_node();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_emode_node, container, false);


        Button node1 = (Button)view.findViewById(R.id.n1);
        Button node2 = (Button)view.findViewById(R.id.n2);
        Button node3 = (Button)view.findViewById(R.id.n3);

        node1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }

        });

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FragmentEmodeNodeViewModel.class);
        // TODO: Use the ViewModel
    }

}
