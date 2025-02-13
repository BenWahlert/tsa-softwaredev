package com.example.tsa_softwaredev;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class IntroFragment extends Fragment {

    private String text;

    public IntroFragment(String text) {
        this.text = text;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro_screen, container, false);

        TextView textView = view.findViewById(R.id.introText);
        textView.setText(text);

        return view;
    }
}
