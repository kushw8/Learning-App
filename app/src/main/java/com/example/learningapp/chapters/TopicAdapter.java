package com.example.learningapp.chapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.learningapp.R;

public class TopicAdapter extends BaseAdapter {

    String[] topicName;
    Context context;

    public TopicAdapter(String[] topicName, Context context) {
        this.topicName = topicName;
        this.context = context;
    }

    @Override
    public int getCount() {
        return topicName.length;
    }

    @Override
    public Object getItem(int position) {
        return topicName[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View gridView = convertView;
        LayoutInflater inflater;
        if (convertView == null){
            inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            gridView =inflater.inflate(R.layout.custom_topic_layout,null);

        }
        TextView textView = gridView.findViewById(R.id.topic_text);
        textView.setText(topicName[position]);

        return gridView;
    }
}
