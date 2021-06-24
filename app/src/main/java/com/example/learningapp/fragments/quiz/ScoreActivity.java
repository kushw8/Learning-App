package com.example.learningapp.fragments.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learningapp.R;
import com.example.learningapp.leaderBoard.LeaderBoardActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ScoreActivity extends AppCompatActivity {

    TextView scoreText, totalText,checkOutLeader;
    int score,total;
    FirebaseUser user;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        score = getIntent().getIntExtra("score",0);
        total = getIntent().getIntExtra("total",0);

        scoreText = findViewById(R.id.score_id);
        totalText = findViewById(R.id.total_id);
        checkOutLeader = findViewById(R.id.score_leader_box);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        scoreText.setText(String.valueOf(score));
        totalText.setText(String.valueOf(total));

        checkOutLeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScoreActivity.this, LeaderBoardActivity.class));
                finish();
            }
        });

        databaseReference.child("Score").child(user.getUid()).child("score").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                    score = score + Integer.parseInt(snapshot.getValue().toString());

                
                snapshot.getRef().setValue(score);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}