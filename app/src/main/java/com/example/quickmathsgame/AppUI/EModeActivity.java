package com.example.quickmathsgame.AppUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.quickmathsgame.R;
import com.example.quickmathsgame.emode_node;
import com.example.quickmathsgame.messagebox;

import java.util.Locale;

public class EModeActivity extends AppCompatActivity {

    //timer
    private static final long COUNTDOWN_IN_MILLIS = 30000;
    private TextView mTextViewCountdown;
    CountDownTimer mCountDownTimer;
    private long mTimeLeftInMillis;

    //node
    Button node1,node2,node3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emode);

        //node_fragment
        node1 = findViewById(R.id.n1);
        node2 = findViewById(R.id.n2);
        node3 = findViewById(R.id.n3);
        loadFragment_node(new emode_node());

        //timer
        mTextViewCountdown = findViewById(R.id.text_view_countdown);
        showNextQuestion();

        /*
        ImageButton imgsetting = (ImageButton)findViewById(R.id.imgBtn_setting);
        imgsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // load First Fragment
                loadFragment(new setting());
            }
        });

         */

        ImageButton setting = (ImageButton)findViewById(R.id.n1);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // load First Fragment
                loadFragment(new messagebox());
            }
        });

        showNextQuestion();
    }

    private void showNextQuestion(){
        mTimeLeftInMillis = COUNTDOWN_IN_MILLIS;
        startTimer();
    }

    private void startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis,1000){
            @Override
            public void onTick(long millisUntilFinished){
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimeLeftInMillis = 0;
                updateCountDownText();
            }
        }.start();
    }

    private void updateCountDownText(){
        int minutes = (int)(mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d",minutes,seconds);
        mTextViewCountdown.setText(timeLeftFormatted);
    }

    private void checkAnswer(){

    }

    private void loadFragment_node(Fragment fragment){
        FragmentManager fmn = getSupportFragmentManager();
        FragmentTransaction ftn = fmn.beginTransaction();
        ftn.replace(R.id.frameLayout, fragment);
        ftn.commit();
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
