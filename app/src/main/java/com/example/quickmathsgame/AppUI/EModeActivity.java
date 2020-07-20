package com.example.quickmathsgame.AppUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quickmathsgame.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class EModeActivity extends AppCompatActivity {

    //timer
    private static final long COUNTDOWN_IN_MILLIS = 30000;
    private TextView mTextViewCountdown;
    CountDownTimer mCountDownTimer;
    private long mTimeLeftInMillis;
    String mMode, mName;
    FirebaseDatabase mFirebaseDatabase;

    Button mButton, mBtn1, mBtn2, mBtn3, mBtn4, mBtn5;
    TextView mTextView, mTextView2;
    Random random = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emode);

        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mName = getIntent().getStringExtra("username");
        //mMode = getIntent().getStringExtra("mode");

        mTextViewCountdown = findViewById(R.id.text_view_countdown);

        mButton = findViewById(R.id.btn1);
        mBtn1 = findViewById(R.id.no1);
        mBtn2 = findViewById(R.id.no2);
        mBtn3 = findViewById(R.id.no3);
        mBtn4 = findViewById(R.id.no4);
        mBtn5 = findViewById(R.id.no5);


        mBtn1.setVisibility(View.INVISIBLE);
        mBtn2.setVisibility(View.INVISIBLE);
        mBtn3.setVisibility(View.INVISIBLE);
        mBtn4.setVisibility(View.INVISIBLE);
        mBtn5.setVisibility(View.INVISIBLE);


        mTextView = findViewById(R.id.tv1);
        mTextView2 = findViewById(R.id.tv2);
        int score = 0;

        startnew(score);
        CountdownTime();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EModeActivity.this, "FDSFDSFSDFSDFSDFSDFD", Toast.LENGTH_SHORT).show();

            }
        });


//        ImageButton setting = (ImageButton) findViewById(R.id.n1);
//        setting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // load First Fragment
//                loadFragment(new messagebox());
//            }
//        });
//        showNextQuestion();

    }

    private void startnew(int score) {


        String mode = mMode = getIntent().getStringExtra("mode");
        mBtn1.setVisibility(View.INVISIBLE);
        mBtn2.setVisibility(View.INVISIBLE);
        mBtn3.setVisibility(View.INVISIBLE);
        mBtn4.setVisibility(View.INVISIBLE);
        mBtn5.setVisibility(View.INVISIBLE);


        Button btns[] = new Button[5];
        btns[0] = mBtn1;
        btns[1] = mBtn2;
        btns[2] = mBtn3;
        btns[3] = mBtn4;
        btns[4] = mBtn5;

        for (int x = 0; x < btns.length; x++) {
            int randomIndexToSwap = random.nextInt(btns.length);
            Button temp = btns[randomIndexToSwap];
            btns[randomIndexToSwap] = btns[x];
            btns[x] = temp;
        }

        for (int x = 0; x < 3; x++) {
            btns[x].setVisibility(View.VISIBLE);
        }
        setnumber(btns, score, mode);
    }

    private void setnumber(Button[] btns, int score, String mode) {
        int nm = random.nextInt(100);
        int ns = nm - 1;
        int nl = nm + 1;

        //mTextView2.setText(ns + "," + nm + "," + nl);
        btns[0].setText(String.valueOf(ns));
        btns[1].setText(String.valueOf(nm));
        btns[2].setText(String.valueOf(nl));
        checkAnswer(btns, score, mode);
    }


    private void checkAnswer(Button[] btns, final int score, final String mode) {
        btns[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        btns[1].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                scoreplus(score, mode);
            }
        });

        btns[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    private void scoreplus(int score, String m) {
        score++;
        startnew(score);
        DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Difficulty").child(m).child("Players").push();
        Map newPost = new HashMap();
        newPost.put("score",score);
        current_user_db.setValue(newPost);
        mTextView.setText(String.valueOf(score));
    }

    private void CountdownTime() {
        mTimeLeftInMillis = COUNTDOWN_IN_MILLIS;
        startTimer();
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimeLeftInMillis = 0;
                updateCountDownText();
                loadFragment(new messagebox());
            }
        }.start();
    }


    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mTextViewCountdown.setText(timeLeftFormatted);
    }


    private void loadFragment(Fragment fragment) {
        // create a FragmentManager
        FragmentManager fm = getSupportFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit(); // save the changes
    }

}
