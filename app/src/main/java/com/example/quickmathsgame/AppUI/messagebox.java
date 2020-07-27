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

public class messagebox extends Fragment {

    private MessageBoxViewModel mViewModel;

    TextView mTextViewMode, mTextViewScore, mTextViewMessage;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popup_messagebox, container, false);


        //ImageButton imgrestart = (ImageButton) view.findViewById(R.id.imgBtn_msgBox_restart);
        ImageButton imghome = view.findViewById(R.id.imgBtn_msgBox_home);
        ImageButton imgback = view.findViewById(R.id.imgBtn_msgBox_back);
        mTextViewMode = view.findViewById(R.id.mode);
        mTextViewScore = view.findViewById(R.id.score);
        mTextViewMessage = view.findViewById(R.id.message);

        String mode = getArguments().getString("mode");

        mTextViewMode.setText(mode);

        DatabaseReference player_id = FirebaseDatabase.getInstance().getReference().child("Difficulty").child(mode).child("Players");

        player_id.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    LeaderboardModel mLeaderboardModel = ds.getValue(LeaderboardModel.class);
                    String name = mLeaderboardModel.getUsername();
                    Integer score = mLeaderboardModel.getScore();
                    mTextViewMessage.setText("Good Job " + name + "!");
                    mTextViewScore.setText(String.valueOf(score));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
