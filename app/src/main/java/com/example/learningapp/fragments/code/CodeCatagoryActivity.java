package com.example.learningapp.fragments.code;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.learningapp.R;

public class CodeCatagoryActivity extends AppCompatActivity {

    ListView listView;
    String[] list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_catagory);

        loadCatagory();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = findViewById(R.id.catagory_listView);
        customAdapter customAdapte = new customAdapter();
        listView.setAdapter(customAdapte);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =  new Intent(CodeCatagoryActivity.this,codeActivity.class);
                intent.putExtra("position",list[position]);
                startActivity(intent);
            }
        });

    }

    private void loadCatagory() {

        final String[] chapter1 = {"Java Program to Add two Numbers","Java Program to Check Even or Odd Number",
                "Java Program to add two binary numbers","Java Program to add two complex numbers",
                "Java Program to Multiply two Numbers","Java Program to check Leap Year",
                "Java Program to check whether input character is vowel or consonant","Java Program to calculate compound interest",
                "Java Program to calculate simple interest","Java Program to Multiply two Numbers","Java Program to check Leap Year",
                "Java Program to check whether input character is vowel or consonant","Java Program to calculate compound interest",
                "Java Program to calculate simple interest",};

        final String[] chapter2 = {"Program 2","Program 2","Program 3","Program 4","Program 5","Program 6","Program 7","Program 8","Program 9","Program 1",
                "Program 1","Program 1","Program 1","Program 1","Program 1","Program 1"};
        final String[] chapter3 = {"Program 3","Program 2","Program 3","Program 4","Program 5","Program 6","Program 7","Program 8","Program 9","Program 1",
                "Program 1","Program 1","Program 1","Program 1","Program 1","Program 1"};
        final String[] chapter4 = {"Program 4","Program 2","Program 3","Program 4","Program 5","Program 6","Program 7","Program 8","Program 9","Program 1",
                "Program 1","Program 1","Program 1","Program 1","Program 1","Program 1"};
        final String[] chapter5 = {"Program 5","Program 2","Program 3","Program 4","Program 5","Program 6","Program 7","Program 8","Program 9","Program 1",
                "Program 1","Program 1","Program 1","Program 1","Program 1","Program 1"};
        final String[] chapter6 = {"Program 6","Program 2","Program 3","Program 4","Program 5","Program 6","Program 7","Program 8","Program 9","Program 1",
                "Program 1","Program 1","Program 1","Program 1","Program 1","Program 1"};

        String catagory = getIntent().getStringExtra("codeCatagory");

        switch (catagory){

            case "chapter1":
                list = chapter1;
                break;
            case "chapter2":
                list = chapter2;
                break;
            case "chapter3":
                list = chapter3;
                break;
            case "chapter4":
                list = chapter4;
                break;
            case "chapter5":
                list = chapter5;
                break;
            case "chapter6":
                list = chapter6;
                break;
        }

    }

    class customAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.code_catgory_item_layout,null);
            TextView textView = convertView.findViewById(R.id.cod_title_item);
            textView.setText(list[position]);
            return convertView;
        }
    }
}