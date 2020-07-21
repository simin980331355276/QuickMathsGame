package com.example.quickmathsgame.AppUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.quickmathsgame.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    EditText username;
    ImageButton btnBack;
    Button btnEasy,btnNormal,btnHard;
    FirebaseDatabase mFirebaseDatabase;
    //FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mFirebaseDatabase=FirebaseDatabase.getInstance();
        username=findViewById(R.id.username);
        btnEasy=findViewById(R.id.easy);
        btnNormal=findViewById(R.id.normal);
        btnHard=findViewById(R.id.hard);
        btnBack=findViewById(R.id.btnBack);

        btnEasy.setOnClickListener(this);
        btnNormal.setOnClickListener(this);
        btnHard.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        /*btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=username.getText().toString();
                if(name.isEmpty()){
                    username.setError("Please enter player's name.");
                }
                else if(!name.isEmpty()){
                    DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Difficulty").child(btnEasy.getText().toString()).child("PlayerName");
                    Map newPost = new HashMap();
                    newPost.put("username",name);
                    current_user_db.setValue(newPost);
                    startActivity(new Intent(GameActivity.this,EModeActivity.class));
                }
            }
        });*/
    }

    @Override
    public void onClick(View v) {
        String mode;
        String name=username.getText().toString();
        switch(v.getId()){
            case R.id.btnBack:
                startActivity(new Intent(GameActivity.this,MainActivity.class));
                break;
            case R.id.easy:
                mode="Easy";
                InsertName(name,mode);
                break;
            case R.id.normal:
                mode="Normal";
                InsertName(name,mode);
                break;
            case R.id.hard:
                mode="Hard";
                InsertName(name,mode);
                break;
        }
    }

    public void InsertName(String n, String m){
        if(n.isEmpty()){
            username.setError("Please enter player's name.");
        }
        else if(!n.isEmpty()){
            //String uid = mFirebaseAuth.getInstance().getCurrentUser().getUid();
            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Difficulty").child(m).child("Players").push();
            String pushkey = current_user_db.getKey();


            Map newPost = new HashMap();
            newPost.put("username",n);
            newPost.put("score",0);
            current_user_db.setValue(newPost);
            Intent iToEgame = new Intent(GameActivity.this,EModeActivity.class);
            //iToEgame.putExtra("uid",uid);
            iToEgame.putExtra("pushkey", pushkey);
            iToEgame.putExtra("username",n);
            iToEgame.putExtra("mode",m);
            startActivity(iToEgame);
        }
    }


}
