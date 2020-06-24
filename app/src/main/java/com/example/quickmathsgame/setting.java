package com.example.quickmathsgame;

import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.quickmathsgame.AppUI.EModeActivity;
import com.example.quickmathsgame.AppUI.GameActivity;

public class setting extends Fragment {

    private SettingViewModel mViewModel;
    private int buttonState = 1;


    public static setting newInstance() {
        return new setting();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.setting_fragment, container, false);

        //image button
        ImageButton imgclose = (ImageButton)view.findViewById(R.id.imgBtn_close);
        ImageButton imgrestart = (ImageButton)view.findViewById(R.id.imgBtn_restart);
        ImageButton imgresume = (ImageButton)view.findViewById(R.id.imgBtn_resume);
        final ImageButton imgsound = (ImageButton)view.findViewById(R.id.imgBtn_sound);
        ImageButton imgmusic = (ImageButton)view.findViewById(R.id.imgBtn_music);

        //close button
        imgclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closefragment();

            }
        });

        //restart button
        imgrestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartgame();
            }
        });

        //resume button
        imgresume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closefragment();
            }
        });

        //sound button
        imgsound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonState % 2 == 1){
                    //imgsound.setBackgroundColor(getResources().getColor(R.color.colorWrong));
                    imgsound.setImageResource(R.drawable.button_style_content);
                }
                else{
                    //imgsound.setBackgroundColor(getResources().getColor(R.color.colorPurple));
                    imgsound.setImageResource(R.drawable.button_style_setting);
                }
                buttonState++;
            }
        });


        return view;
    }

    private void restartgame(){
        Intent intent = new Intent(getActivity(), GameActivity.class);
        startActivity(intent);
    }

    private void closefragment() {
        Intent intent = new Intent(getActivity(), EModeActivity.class);
        startActivity(intent);
        ((Activity) getActivity()).overridePendingTransition(0, 0);

    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SettingViewModel.class);
        // TODO: Use the ViewModel
    }

}
