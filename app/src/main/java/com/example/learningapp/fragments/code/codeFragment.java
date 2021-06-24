package com.example.learningapp.fragments.code;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.learningapp.R;


public class codeFragment extends Fragment implements View.OnClickListener{

    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_code, container, false);

        imageView1 = view.findViewById(R.id.circleImage_ch1);
        imageView2 = view.findViewById(R.id.circleImage_ch2);
        imageView3 = view.findViewById(R.id.circleImage_ch3);
        imageView4 = view.findViewById(R.id.circleImage_ch4);
        imageView5 = view.findViewById(R.id.circleImage_ch5);
        imageView6 = view.findViewById(R.id.circleImage_ch6);
       // imageView7 = view.findViewById(R.id.circleImage_ch7);
       //  imageView8 = view.findViewById(R.id.circleImage_ch8);

        loadImage();

        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        imageView4.setOnClickListener(this);
        imageView5.setOnClickListener(this);
        imageView6.setOnClickListener(this);
//        imageView7.setOnClickListener(this);
//        imageView8.setOnClickListener(this);




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
        Glide.with(getContext())
                .load("https://firebasestorage.googleapis.com/v0/b/learning-app-d2132.appspot.com/o/java.png?alt=media&token=55075353-ffc0-41a1-9203-007c68a107c2")
                .into(imageView5);

        Glide.with(getContext())
                .load("https://firebasestorage.googleapis.com/v0/b/learning-app-d2132.appspot.com/o/python.png?alt=media&token=fb45d194-7b01-432d-a95b-52591320dda3")
                .into(imageView6);

//        Glide.with(getContext())
//                .load("https://firebasestorage.googleapis.com/v0/b/learning-app-d2132.appspot.com/o/swift.png?alt=media&token=d3d1d2c5-2cfe-4c9b-b89c-eb930ca77301")
//                .into(imageView7);
//
//        Glide.with(getContext())
//                .load("https://firebasestorage.googleapis.com/v0/b/learning-app-d2132.appspot.com/o/c.png?alt=media&token=461d54e4-9f0c-44db-b87d-26db5445740d")
//                .into(imageView8);

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getContext(),CodeCatagoryActivity.class);

        switch (v.getId()){

            case R.id.circleImage_ch1:
                intent.putExtra("codeCatagory","chapter1");
                startActivity(intent);
                break;
            case R.id.circleImage_ch2:
                intent.putExtra("codeCatagory","chapter2");
                startActivity(intent);
                break;
            case R.id.circleImage_ch3:
                intent.putExtra("codeCatagory","chapter3");
                startActivity(intent);
                break;
            case R.id.circleImage_ch4:
                intent.putExtra("codeCatagory","chapter4");
                startActivity(intent);
                break;
            case R.id.circleImage_ch5:
                intent.putExtra("codeCatagory","chapter5");
                startActivity(intent);
                break;
            case R.id.circleImage_ch6:
                intent.putExtra("codeCatagory","chapter6");
                startActivity(intent);
                break;


        }

    }
}