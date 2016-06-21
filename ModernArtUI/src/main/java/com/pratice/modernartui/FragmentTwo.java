package com.pratice.modernartui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentTwo extends Fragment
{
    private TextView textView=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.frgament_two, container, false);
        textView=(TextView) view.findViewById(R.id.textViewTwo);
        textView.setBackgroundResource(R.color.grey);
        return view;
    }

    public void setTextViewColor(int progress)
    {
        if(progress>=0 && progress<=10)
        {
            textView.setBackgroundResource(R.color.violet);
        }
        else if(progress>10 && progress<=20)
        {
            textView.setBackgroundResource(R.color.grey);
        }
        else if(progress>20 && progress<=30)
        {
            textView.setBackgroundResource(R.color.red);
        }
        else if(progress>30 && progress<=40)
        {
            textView.setBackgroundResource(R.color.pink);
        }
        else if(progress>40 && progress<=50)
        {
            textView.setBackgroundResource(R.color.orange);
        }
        else if(progress>50 && progress<=60)
        {
            textView.setBackgroundResource(R.color.pink);
        }
        else if(progress>60 && progress<=70)
        {
            textView.setBackgroundResource(R.color.grey);
        }
        else if(progress>70 && progress<=80)
        {
            textView.setBackgroundResource(R.color.yellow);
        }
        else if(progress>80 && progress<=90)
        {
            textView.setBackgroundResource(R.color.violet);
        }
        else if(progress>90 && progress<=100)
        {
            textView.setBackgroundResource(R.color.skyblue);
        }
    }
}
