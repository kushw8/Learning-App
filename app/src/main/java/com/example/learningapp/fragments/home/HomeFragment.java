package com.example.learningapp.fragments.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.learningapp.R;
import com.example.learningapp.chapters.TopicActivity;


public class HomeFragment extends Fragment implements View.OnClickListener {

    CardView heading1,heading2,heading3,heading4;
    ImageView imageView1,imageView2,imageView3,imageView4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        heading1 = view.findViewById(R.id.home_heading1);
        heading2 = view.findViewById(R.id.home_heading2);
        heading3 = view.findViewById(R.id.home_heading3);
        heading4 = view.findViewById(R.id.home_heading4);

        imageView1 = view.findViewById(R.id.homee_image_java);
        imageView2 = view.findViewById(R.id.homee_image_python);
        imageView3 = view.findViewById(R.id.homee_image_swift);
        imageView4 = view.findViewById(R.id.homee_image_c);

        loadImage();


        heading1.setOnClickListener(this);
        heading2.setOnClickListener(this);
        heading3.setOnClickListener(this);
        heading4.setOnClickListener(this);


        return view;
    }

    private void loadImage() {
        Glide.with(getContext())
                .load("https://firebasestorage.googleapis.com/v0/b/learning-app-d2132.appspot.com/o/java.png?alt=media&token=55075353-ffc0-41a1-9203-007c68a107c2")
                .into(imageView1);

        Glide.with(getContext())
                .load("https://firebasestorage.googleapis.com/v0/b/learning-app-d2132.appspot.com/o/python.png?alt=media&token=fb45d194-7b01-432d-a95b-52591320dda3")
                .into(imageView2);

        Glide.with(getContext())
                .load("https://firebasestorage.googleapis.com/v0/b/learning-app-d2132.appspot.com/o/swift.png?alt=media&token=d3d1d2c5-2cfe-4c9b-b89c-eb930ca77301")
                .into(imageView3);

        Glide.with(getContext())
                .load("https://firebasestorage.googleapis.com/v0/b/learning-app-d2132.appspot.com/o/c.png?alt=media&token=461d54e4-9f0c-44db-b87d-26db5445740d")
                .into(imageView4);
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getContext(), TopicActivity.class);

        switch (v.getId()){
            case R.id.home_heading1:
                intent.putExtra("chapterName","heading1");
                startActivity(intent);
            break;
            case R.id.home_heading2:
                intent.putExtra("chapterName","heading2");
                startActivity(intent);
                break;
            case R.id.home_heading3:
                intent.putExtra("chapterName","heading3");
                startActivity(intent);
                break;
            case R.id.home_heading4:
                intent.putExtra("chapterName","heading4");
                startActivity(intent);
                break;
        }
    }
}