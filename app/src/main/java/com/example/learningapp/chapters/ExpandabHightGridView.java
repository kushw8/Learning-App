package com.example.learningapp.chapters;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.GridView;

public class ExpandabHightGridView  extends GridView {

    boolean expanded = false;

    public ExpandabHightGridView(Context context) {
        super(context);
    }

    public ExpandabHightGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandabHightGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean isExapnded(){
        return expanded;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isExapnded()){
            int expandspec = MeasureSpec.makeMeasureSpec(MEASURED_SIZE_MASK,MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec,expandspec);
            ViewGroup.LayoutParams params = getLayoutParams();
            params.height = getMeasuredHeight();
        }else {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    }
    public void setExpanded(boolean expanded){
        this.expanded = expanded;
    }
}
