package com.example.quickmathsgame.AppUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.quickmathsgame.AppUI.EModeActivity;
import com.example.quickmathsgame.AppUI.GameActivity;
import com.example.quickmathsgame.AppUI.MainActivity;
import com.example.quickmathsgame.Model.MessageBoxViewModel;
import com.example.quickmathsgame.R;

public class messagebox extends Fragment {

    private MessageBoxViewModel mViewModel;

    TextView mTextViewFragmentSCORE;
    String mScore;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popup_messagebox, container, false);

        ImageButton imgrestart = (ImageButton) view.findViewById(R.id.imgBtn_msgBox_restart);
        ImageButton imghome = (ImageButton) view.findViewById(R.id.imgBtn_msgBox_home);
        ImageButton imgback = (ImageButton) view.findViewById(R.id.imgBtn_msgBox_back);


        mTextViewFragmentSCORE = view.findViewById(R.id.user_score);
//        String s = getActivity().getIntent().getStringExtra("score");


//        Bundle bundle = getArguments();
//        String score = bundle.getString("score");
//        mTextViewFragmentSCORE.setText(score);


        //restart button
        imgrestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartgame();
            }
        });
        //home button
        imghome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home();
            }
        });
        //back button
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        return view;
    }

    private void restartgame() {
        Intent intent = new Intent(getActivity(), EModeActivity.class);
        startActivity(intent);
    }

    private void home() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

    private void back() {
        Intent intent = new Intent(getActivity(), GameActivity.class);
        startActivity(intent);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MessageBoxViewModel.class);
        // TODO: Use the ViewModel
    }

}
