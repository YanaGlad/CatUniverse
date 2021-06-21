package com.example.catuniverse.gameSupport.aboutGame;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.catuniverse.R;


public class AboutAchievementFragment extends Fragment {

    private AppCompatImageView imageView;
    private AppCompatTextView title, description;

    public AboutAchievementFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about_achievement, container, false);

        imageView = view.findViewById(R.id.image_achieve);
        title = view.findViewById(R.id.achieve_title);
        description = view.findViewById(R.id.acheve_description);

        return  view;
    }
}