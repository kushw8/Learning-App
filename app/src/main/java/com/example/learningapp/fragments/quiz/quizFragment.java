package com.example.learningapp.fragments.quiz;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.learningapp.R;
import com.example.learningapp.auth.LoginnActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class quizFragment extends Fragment {

    Button button;
    FirebaseUser user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        button = view.findViewById(R.id.quiz_start_btn);
        user = FirebaseAuth.getInstance().getCurrentUser();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user != null){
                    startActivity(new Intent(getContext(),StartQuizActivity.class));
                }else {
                    startActivity(new Intent(getContext(), LoginnActivity.class));
                }
            }
        });

        return view;
    }
}