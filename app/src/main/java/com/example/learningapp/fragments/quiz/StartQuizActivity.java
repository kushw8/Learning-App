package com.example.learningapp.fragments.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learningapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StartQuizActivity extends AppCompatActivity {

    private TextView questionText,indicator;
    LinearLayout container;
    private Button nextBtn,shareBtn;
    private int score = 0;
    private int position = 0;
    private int count = 0;
    DatabaseReference reference;
    private List<QestionData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);

        questionText = findViewById(R.id.start_quiz_question);
        indicator = findViewById(R.id.start_quiz_indicator);
        container = findViewById(R.id.linearLayout_container);
        nextBtn = findViewById(R.id.start_quiz_next);
        shareBtn = findViewById(R.id.start_quiz_share);

        list = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        reference.child("Questions").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 :snapshot.getChildren()){
                    String question = snapshot1.child("Question").getValue().toString();
                    String option1 = snapshot1.child("Option1").getValue().toString();
                    String option2 = snapshot1.child("Option2").getValue().toString();
                    String option3 = snapshot1.child("Option3").getValue().toString();
                    String option4 = snapshot1.child("Option4").getValue().toString();
                    String answer = snapshot1.child("Answer").getValue().toString();

                    list.add(new QestionData(option1,option2,option3,option4,question,answer));
                }
                if (list.size() > 0){

                    loadQuestion(questionText,0,list.get(position).getQuestion());

                    for (int j=0;j<4;j++){
                      container.getChildAt(j).setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              checkAnswer((Button)v);
                          }
                      });
                    }

                    nextBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            nextBtn.setEnabled(false);
                            nextBtn.setAlpha(0.7f);
                            enable(true);
                            position++;

                            if (position == list.size()){
                                Intent intent = new Intent(StartQuizActivity.this,ScoreActivity.class);
                                intent.putExtra("score",score);
                                intent.putExtra("total",list.size());
                                startActivity(intent);
                                finish();
                                return;

                            }

                            count=0;
                            loadQuestion(questionText,0,list.get(position).getQuestion());
                        }
                    });

                    shareBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                         String body = "*" + list.get(position).getQuestion() + "*\n" +
                                 "(a)" + list.get(position).getOption1() + "*\n" +
                                 "(b)" + list.get(position).getOption2() + "*\n" +
                                 "(c)" + list.get(position).getOption3() + "*\n" +
                                 "(d)" + list.get(position).getOption4();

                         Intent intent = new Intent(Intent.ACTION_SEND);
                         intent.setType("Text/Plain");
                         intent.putExtra(Intent.EXTRA_SUBJECT,"Learning App..");
                         intent.putExtra(Intent.EXTRA_TEXT,body);
                         startActivity(Intent.createChooser(intent,"Share via"));


                        }
                    });


                }else {
                    Toast.makeText(StartQuizActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(StartQuizActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void checkAnswer(Button selectedOption) {

        enable(false);
        nextBtn.setEnabled(true);
        nextBtn.setAlpha(1);
        if (selectedOption.getText().toString().equals(list.get(position).getAnswer())){
            score++;
            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4caf50")));
        }else{
            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
            Button correctAnswer = container.findViewWithTag(list.get(position).getAnswer());
            correctAnswer.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4caf50")));
        }
    }
    private void enable(Boolean enable){
        for (int i=0;i<4;i++){
            container.getChildAt(i).setEnabled(enable);

            if (enable){
                container.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#989898")));
            }
        }
    }

    private void loadQuestion(View questionText, int value, String question) {

        for (int i=0;i<4;i++){

            container.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#989898")));
            questionText.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                    .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                   if (value == 0 && count < 4){
                       String option = "";
                       if (count == 0)
                           option = list.get(position).getOption1();
                       if (count==1)
                           option = list.get(position).getOption2();
                       if (count==2)
                           option = list.get(position).getOption3();
                       if (count==3)
                           option = list.get(position).getOption4();

                  loadQuestion(container.getChildAt(count),0,option);
                  count++;


                   }
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (value == 0){
                        try {
                            ((TextView) questionText).setText(question);
                            indicator.setText(position + "/" + list.size());
                        }catch (ClassCastException exception){
                            ((Button)questionText).setText(question);
                        }
                        questionText.setTag(question);
                        loadQuestion(questionText,1,question);
                    }

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });

        }


    }
}