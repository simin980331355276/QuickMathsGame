package com.example.quickmathsgame.AppUI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.quickmathsgame.Model.LeaderboardModel;
import com.example.quickmathsgame.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LeaderboardActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnEasy, btnNormal, btnHard;
    ListView mListView;
    FirebaseDatabase mFirebaseDatabase;
    ArrayList<String> mArrayList=new ArrayList<>();
    ArrayAdapter<String> mArrayAdapter;
    LeaderboardModel mLeaderboardModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        mLeaderboardModel = new LeaderboardModel();

        mListView = findViewById(R.id.list_view);
        btnEasy = findViewById(R.id.easyBtn);
        btnNormal = findViewById(R.id.normalBtn);
        btnHard = findViewById(R.id.hardBtn);

        btnEasy.setOnClickListener(this);
        btnNormal.setOnClickListener(this);
        btnHard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String mode;
        switch (v.getId()) {
            case R.id.easyBtn:
                mode = "Easy";
                 RetrieveDataLeaderboard(mode);
                break;
            case R.id.normalBtn:
                mode = "Normal";
                RetrieveDataLeaderboard(mode);
                break;
            case R.id.hardBtn:
                mode = "Hard";
                RetrieveDataLeaderboard(mode);
                break;
        }
    }
    public void RetrieveDataLeaderboard(String m) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Difficulty").child(m).child("Players");

        mArrayAdapter = new ArrayAdapter<String>(this, R.layout.leaderboard_data, R.id.ldrboardName, mArrayList);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    mLeaderboardModel = ds.getValue(LeaderboardModel.class);
                    mArrayList.add(mLeaderboardModel.getUsername().toString() + " " + mLeaderboardModel.getScore().toString());
                }
                mListView.setAdapter(mArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

