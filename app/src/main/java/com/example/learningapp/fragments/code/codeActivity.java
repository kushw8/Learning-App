package com.example.learningapp.fragments.code;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.learningapp.R;

import thereisnospon.codeview.CodeView;
import thereisnospon.codeview.CodeViewTheme;

public class codeActivity extends AppCompatActivity {

    CodeView codeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        codeView = findViewById(R.id.code_view_code_activity);
        codeView.setTheme(CodeViewTheme.ANDROIDSTUDIO);
        loadPrograms();
    }

    private void loadPrograms() {

        String code=null;

        switch (getIntent().getStringExtra("position")){

            case "Program 1":
                code = programsExamples.ex1;
                break;
            case "Program 2":
                code = programsExamples.ex2;
                break;
            case "Program 3":
                code = programsExamples.ex3;
                break;
            case "Program 4":
                code = programsExamples.ex4;
                break;
            case "Program 5":
                code = programsExamples.ex1;
                break;
            case "Java Program to Add two Numbers":
                code = programsExamples.ex6;
                break;
            case "Java Program to Check Even or Odd Number":
                code = programsExamples.ex5;
                break;
            case "Java Program to add two binary numbers":
                code = programsExamples.ex6;
                break;
            case "Java Program to add two complex numbers":
                code = programsExamples.ex4;
                break;
            case "Java Program to check whether input character is vowel or consonant":
                code = programsExamples.ex4;
                break;


        }
        codeView.showCode(code);
    }
}