package com.example.quickmathsgame.AppUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.media.MediaPlayer;
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
import com.google.firebase.database.Query;

import org.w3c.dom.Text;

import java.util.ArrayList;
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
    String mMode, mName, mPushkey;
    FirebaseDatabase mFirebaseDatabase;

    Button mBtn1, mBtn2, mBtn3, mBtn4, mBtn5, mBtn6, mBtn7, mBtn8, mBtn9, mBtn10,
            mBtn11, mBtn12, mBtn13, mBtn14, mBtn15, mBtn16, mBtn17, mBtn18, mBtn19, mBtn20,
            mBtn21, mBtn22, mBtn23, mBtn24, mBtn25, mBtn26, mBtn27, mBtn28, mBtn29, mBtn30;

    TextView mTextView, mTextView2, mTextViewMode, mTextViewScore;
    Random random = new Random();

    MediaPlayer player, player_wrong, player_correct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emode);

        player = MediaPlayer.create(this, R.raw.gamemode);
        player_wrong = MediaPlayer.create(this, R.raw.wrong);
        player_correct = MediaPlayer.create(this, R.raw.correct);
        player.start();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        String uid = mPushkey = getIntent().getStringExtra("pushkey");
        String gamemode = mMode = getIntent().getStringExtra("mode");
        mTextViewMode = findViewById(R.id.mode);
        mTextViewScore = findViewById(R.id.score);

//      mTextView = findViewById(R.id.tv1);
//      mTextView2 = findViewById(R.id.tv2);
        mTextViewMode.setText("Mode: " + gamemode);
        mTextViewCountdown = findViewById(R.id.text_view_countdown);

        mBtn1 = findViewById(R.id.no1);
        mBtn2 = findViewById(R.id.no2);
        mBtn3 = findViewById(R.id.no3);
        mBtn4 = findViewById(R.id.no4);
        mBtn5 = findViewById(R.id.no5);

        mBtn6 = findViewById(R.id.no6);
        mBtn7 = findViewById(R.id.no7);
        mBtn8 = findViewById(R.id.no8);
        mBtn9 = findViewById(R.id.no9);
        mBtn10 = findViewById(R.id.no10);

        mBtn11 = findViewById(R.id.no11);
        mBtn12 = findViewById(R.id.no12);
        mBtn13 = findViewById(R.id.no13);
        mBtn14 = findViewById(R.id.no14);
        mBtn15 = findViewById(R.id.no15);

        mBtn16 = findViewById(R.id.no16);
        mBtn17 = findViewById(R.id.no17);
        mBtn18 = findViewById(R.id.no18);
        mBtn19 = findViewById(R.id.no19);
        mBtn20 = findViewById(R.id.no20);

        mBtn21 = findViewById(R.id.no21);
        mBtn22 = findViewById(R.id.no22);
        mBtn23 = findViewById(R.id.no23);
        mBtn24 = findViewById(R.id.no24);
        mBtn25 = findViewById(R.id.no25);

        mBtn26 = findViewById(R.id.no26);
        mBtn27 = findViewById(R.id.no27);
        mBtn28 = findViewById(R.id.no28);
        mBtn29 = findViewById(R.id.no29);
        mBtn30 = findViewById(R.id.no30);


        int score = 0;

        startnew(score);
        CountdownTime(uid, gamemode);


        ImageButton setting = (ImageButton) findViewById(R.id.n1);
           setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // load First Fragment
                startActivity(new Intent(EModeActivity.this,GameActivity.class));
                player.stop();
                mCountDownTimer.cancel();
                mTimeLeftInMillis = COUNTDOWN_IN_MILLIS;
                mTimeLeftInMillis= 0;
            }
        });


    }

    private void startnew(int score) {

        String pushid = mPushkey = getIntent().getStringExtra("pushkey");
        String mode = mMode = getIntent().getStringExtra("mode");
        String name = mName = getIntent().getStringExtra("username");

        mBtn1.setVisibility(View.INVISIBLE);
        mBtn2.setVisibility(View.INVISIBLE);
        mBtn3.setVisibility(View.INVISIBLE);
        mBtn4.setVisibility(View.INVISIBLE);
        mBtn5.setVisibility(View.INVISIBLE);

        mBtn6.setVisibility(View.INVISIBLE);
        mBtn7.setVisibility(View.INVISIBLE);
        mBtn8.setVisibility(View.INVISIBLE);
        mBtn9.setVisibility(View.INVISIBLE);
        mBtn10.setVisibility(View.INVISIBLE);

        mBtn11.setVisibility(View.INVISIBLE);
        mBtn12.setVisibility(View.INVISIBLE);
        mBtn13.setVisibility(View.INVISIBLE);
        mBtn14.setVisibility(View.INVISIBLE);
        mBtn15.setVisibility(View.INVISIBLE);

        mBtn16.setVisibility(View.INVISIBLE);
        mBtn17.setVisibility(View.INVISIBLE);
        mBtn18.setVisibility(View.INVISIBLE);
        mBtn19.setVisibility(View.INVISIBLE);
        mBtn20.setVisibility(View.INVISIBLE);

        mBtn21.setVisibility(View.INVISIBLE);
        mBtn22.setVisibility(View.INVISIBLE);
        mBtn23.setVisibility(View.INVISIBLE);
        mBtn24.setVisibility(View.INVISIBLE);
        mBtn25.setVisibility(View.INVISIBLE);

        mBtn26.setVisibility(View.INVISIBLE);
        mBtn27.setVisibility(View.INVISIBLE);
        mBtn28.setVisibility(View.INVISIBLE);
        mBtn29.setVisibility(View.INVISIBLE);
        mBtn30.setVisibility(View.INVISIBLE);

        switch (mode) {
            case "Easy":
            case "Normal":
            case "Hard":
                Button[] btns = new Button[30];
                btns[0] = mBtn1;btns[1] = mBtn2;btns[2] = mBtn3;btns[3] = mBtn4;btns[4] = mBtn5; btns[5] = mBtn6;  btns[6] = mBtn7;btns[7] = mBtn8;btns[8] = mBtn9;btns[9] = mBtn10;
                btns[10] = mBtn11;btns[11] = mBtn12;btns[12] = mBtn13;btns[13] = mBtn14;btns[14] = mBtn15; btns[15] = mBtn16;btns[16] = mBtn17;btns[17] = mBtn18;btns[18] = mBtn19;btns[19] = mBtn20;
                btns[20] = mBtn21;btns[21] = mBtn22;btns[22] = mBtn23;btns[23] = mBtn24;btns[24] = mBtn25; btns[25] = mBtn26;btns[26] = mBtn27;btns[27] = mBtn28;btns[28] = mBtn29;btns[29] = mBtn30;
                swaparray(btns, score, mode, pushid, name);
                break;
        }
    }


    private void swaparray(Button[] btns, Integer score, String mode, String pushid, String name) {
        for (int x = 0; x < btns.length; x++) {
            int randomIndexToSwap = random.nextInt(btns.length);
            Button temp = btns[randomIndexToSwap];
            btns[randomIndexToSwap] = btns[x];
            btns[x] = temp;
        }
        switch (mode) {
            case "Easy":
                for (int y = 0; y < 3; y++) {
                    btns[y].setVisibility(View.VISIBLE);
                }
                setnumber(btns, score, mode, pushid, name);
                break;
            case "Normal":
                for (int y = 0; y < 5; y++) {
                    btns[y].setVisibility(View.VISIBLE);
                }
                setnumber(btns, score, mode, pushid, name);
                break;
            case "Hard":
                for (int y = 0; y < 7; y++) {
                    btns[y].setVisibility(View.VISIBLE);
                }
                setnumber(btns, score, mode, pushid, name);
                break;
        }
    }

    private void setnumber(Button[] btns, int score, String mode, String pushid, String name) {
        int nm = random.nextInt(100);
        switch (mode) {
            case "Easy":
                int ezsn = nm - 1;int ezln = nm + 1;
                btns[0].setText(String.valueOf(ezsn)); //Small
                btns[1].setText(String.valueOf(nm)); //Middle //Correct
                btns[2].setText(String.valueOf(ezln)); //Big
                checkAnswer(btns, score, mode, pushid, name);
                break;
            case "Normal":
                int nossn = nm - 2;int nosn = nm - 1;int noln = nm + 1;int nolln = nm + 2;
                btns[0].setText(String.valueOf(nossn)); //Smallest
                btns[1].setText(String.valueOf(nosn)); //Small
                btns[2].setText(String.valueOf(nm)); //Middle //Correct
                btns[3].setText(String.valueOf(noln)); //Big
                btns[4].setText(String.valueOf(nolln)); //Biggest
                checkAnswer(btns, score, mode, pushid, name);
                break;
            case "Hard":
                int hsssn = nm - 3;int hssn = nm - 2;int hsn = nm - 1;int hln = nm + 1;int hlln = nm + 2;int hllln = nm + 3;
                btns[0].setText(String.valueOf(hsssn)); //Smallest
                btns[1].setText(String.valueOf(hssn)); //Smaller
                btns[2].setText(String.valueOf(hsn)); //Small
                btns[3].setText(String.valueOf(nm)); //Middle //Correct
                btns[4].setText(String.valueOf(hln)); //Big
                btns[5].setText(String.valueOf(hlln)); //Bigger
                btns[6].setText(String.valueOf(hllln)); //Biggest
                checkAnswer(btns, score, mode, pushid, name);
                break;
        }
    }


    private void checkAnswer(Button[] btns, final int score, final String mode, final String pushid, final String name) {
        switch (mode) {
            case "Easy":
                btns[0].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        generateLoseFragment(score,pushid,mode);
                    }
                });

                btns[1].setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        scoreplus(score, mode, pushid, name);
                    }
                });

                btns[2].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        generateLoseFragment(score,pushid,mode);
                    }
                });
                break;

            case "Normal":
                btns[0].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        generateLoseFragment(score,pushid,mode);
                    }
                });

                btns[1].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        generateLoseFragment(score,pushid,mode);
                    }
                });

                btns[2].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        scoreplus(score, mode, pushid, name);
                    }
                });

                btns[3].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        generateLoseFragment(score,pushid,mode);
                    }
                });

                btns[4].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        generateLoseFragment(score,pushid,mode);
                    }
                });
                break;

            case "Hard":
                btns[0].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        generateLoseFragment(score,pushid,mode);
                    }
                });

                btns[1].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        generateLoseFragment(score,pushid,mode);
                    }
                });

                btns[2].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        generateLoseFragment(score,pushid,mode);
                    }
                });

                btns[3].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        scoreplus(score, mode, pushid, name);
                    }
                });

                btns[4].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        generateLoseFragment(score,pushid,mode);
                    }
                });

                btns[5].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        generateLoseFragment(score,pushid,mode);
                    }
                });

                btns[6].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        generateLoseFragment(score,pushid,mode);
                    }
                });
                break;
        }
    }

    private void generateLoseFragment(int score, String uid, String mode){
        mCountDownTimer.cancel();
        player_wrong.start();
        player.stop();
        mTimeLeftInMillis = COUNTDOWN_IN_MILLIS;
        mTimeLeftInMillis= 0;

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("mode", mode);
        bundle.putString("score",String.valueOf(score));
        LoseFragment fragInfo = new LoseFragment();
        fragInfo.setArguments(bundle);
        transaction.replace(R.id.frameLayout, fragInfo);
        transaction.commit();
    }

    private void scoreplus(int score, String m, String pushid, String name) {
        player_correct.start();
        score++;
        startnew(score);
        DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Difficulty").child(m).child("Players").child(pushid);
        Map newPost = new HashMap();
        newPost.put("username", name);
        newPost.put("score", score);
        current_user_db.setValue(newPost);
        mTextViewScore.setText(String.valueOf(score));
    }

    private void CountdownTime(String uid, String mode) {
        mTimeLeftInMillis = COUNTDOWN_IN_MILLIS;
        startTimer(uid, mode);
    }

    private void startTimer(final String uid, final String mode) {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;    //start countdown and update the textview
                updateCountDownText();
            }

            @Override
            public void onFinish() {    //when timer countdown finish, show the message box fragment
                mTimeLeftInMillis = 0;
                updateCountDownText();
                //loadFragment(new messagebox());
                generateFragment(uid,mode);
                player.stop();
            }
        }.start();
    }
    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mTextViewCountdown.setText(timeLeftFormatted);
    }

    private void generateFragment(String uid, String mode){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("pushkey", uid);
        bundle.putString("mode", mode);
        messagebox fragInfo = new messagebox();
        fragInfo.setArguments(bundle);
        transaction.replace(R.id.frameLayout, fragInfo);
        transaction.commit();
    }




    private void loadFragment(Fragment fragment) {
        // create a FragmentManager
        FragmentManager fm = getSupportFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();


        Bundle bundle = new Bundle();
        String myMessage = "Stack Overflow is cool!";
        bundle.putString("message", myMessage);
        messagebox fragInfo = new messagebox();
        fragInfo.setArguments(bundle);


        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit(); // save the changes
    }

}
