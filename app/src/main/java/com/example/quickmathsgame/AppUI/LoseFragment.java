//package com.example.quickmathsgame.AppUI;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageButton;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.ViewModelProviders;
//import androidx.room.Query;
//
//import com.example.quickmathsgame.AppUI.EModeActivity;
//import com.example.quickmathsgame.AppUI.GameActivity;
//import com.example.quickmathsgame.AppUI.MainActivity;
//import com.example.quickmathsgame.Model.LeaderboardModel;
//import com.example.quickmathsgame.Model.MessageBoxViewModel;
//import com.example.quickmathsgame.R;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//
//public class LoseFragment extends Fragment {
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_lose, container, false);
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_lose, container, false);
//        String mode = getArguments().getString("mode");
//        String score = getArguments().getString("score");
//
//        return view;
//    }
//}

package com.example.quickmathsgame.AppUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Query;

import com.example.quickmathsgame.AppUI.EModeActivity;
import com.example.quickmathsgame.AppUI.GameActivity;
import com.example.quickmathsgame.AppUI.MainActivity;
import com.example.quickmathsgame.Model.LeaderboardModel;
import com.example.quickmathsgame.Model.MessageBoxViewModel;
import com.example.quickmathsgame.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoseFragment extends Fragment {

    private MessageBoxViewModel mViewModel;

    TextView mTextViewMode, mTextViewScore, mTextViewMessage;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lose, container, false);


        //ImageButton imgrestart = (ImageButton) view.findViewById(R.id.imgBtn_msgBox_restart);
        ImageButton imghome = (ImageButton) view.findViewById(R.id.imgBtn_msgBox_home);
        ImageButton imgback = (ImageButton) view.findViewById(R.id.imgBtn_msgBox_back);
        mTextViewMode = view.findViewById(R.id.mode);
        mTextViewScore = view.findViewById(R.id.score);
        mTextViewMessage = view.findViewById(R.id.message);

        String mode = getArguments().getString("mode");
        String score = getArguments().getString("score");

        mTextViewMode.setText(mode);
        mTextViewScore.setText(score);

        //restart button
//        imgrestart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                restartgame();
//            }
//        });
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

//    private void restartgame() {
//        Intent intent = new Intent(getActivity(), EModeActivity.class);
//        startActivity(intent);
//    }

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
