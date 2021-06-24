package com.example.learningapp.chapters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.learningapp.R;
import com.example.learningapp.chapterName.c.c;
import com.example.learningapp.chapterName.java.java;
import com.example.learningapp.chapterName.python.python;
import com.example.learningapp.chapterName.swift.swift;


public class TopicActivity extends AppCompatActivity {

    Toolbar toolbar;
     ExpandabHightGridView gridView;
     String[] topicName1= {"Introduction Java","Chapter1","Chapter2","Chapter3","Chapter4"};
     String[] topicName2= {"Introduction Python","Chapter1"};
     String[] topicName3= {"Introduction Swift","Chapter1","Chapter2","Chapter3","Chapter4"};
     String[] topicName4= {"Introduction C","Chapter1","Chapter2"};
     Context context;
     TopicAdapter topicAdapter;
     String chapterName;
    String[] array = null;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        toolbar = findViewById(R.id.toolbar_home_fragment);
        gridView = findViewById(R.id.topic_name);
        gridView.setExpanded(true);

        
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        chapterName = getIntent().getStringExtra("chapterName");
        imageView = findViewById(R.id.topic_image);
        
        compareAndOpen();



    }

    private void compareAndOpen() {

        if (chapterName.equals("heading1")) {
            array = topicName1;
          //  imageView.setImageResource(R.drawable.java);
            Glide.with(TopicActivity.this)
                    .load("https://firebasestorage.googleapis.com/v0/b/learning-app-d2132.appspot.com/o/java.png?alt=media&token=55075353-ffc0-41a1-9203-007c68a107c2")
                    .into(imageView);
            getSupportActionBar().setTitle("Java");
        }
        else if(chapterName.equals("heading2")){
            array =topicName2;
         //   imageView.setImageResource(R.drawable.python);
            Glide.with(TopicActivity.this)
                    .load("https://firebasestorage.googleapis.com/v0/b/learning-app-d2132.appspot.com/o/python.png?alt=media&token=fb45d194-7b01-432d-a95b-52591320dda3")
                    .into(imageView);
            getSupportActionBar().setTitle("Python");
        }
        else if(chapterName.equals("heading3")){
            array =topicName3;
         //   imageView.setImageResource(R.drawable.swift);
            Glide.with(TopicActivity.this)
                    .load("https://firebasestorage.googleapis.com/v0/b/learning-app-d2132.appspot.com/o/swift.png?alt=media&token=d3d1d2c5-2cfe-4c9b-b89c-eb930ca77301")
                    .into(imageView);
            getSupportActionBar().setTitle("Swift");
        }
        else if(chapterName.equals("heading4")) {
            array = topicName4;
          //  imageView.setImageResource(R.drawable.c);
            Glide.with(TopicActivity.this)
                    .load("https://firebasestorage.googleapis.com/v0/b/learning-app-d2132.appspot.com/o/c.png?alt=media&token=461d54e4-9f0c-44db-b87d-26db5445740d")
                    .into(imageView);
            getSupportActionBar().setTitle("C Programming");
        }
        else {
            array = null;
        }
        topicAdapter = new TopicAdapter(array,TopicActivity.this);
        gridView.setAdapter(topicAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OpenActivity(array[position]);
            }
        });
    }

    private void OpenActivity(String s) {

        switch (s){

            case "Introduction Java" :
                startActivity(new Intent(TopicActivity.this, java.class));
                break;
            case "Introduction Python":
                startActivity(new Intent(TopicActivity.this, python.class));
                break;
            case "Introduction Swift":
                startActivity(new Intent(TopicActivity.this, swift.class));
                break;
            case "Introduction C":
                startActivity(new Intent(TopicActivity.this, c.class));
                break;

        }
    }


}