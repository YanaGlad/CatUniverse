package com.glad.catuniverse.gameSupport.aboutGame;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.glad.catuniverse.R;

import java.util.Objects;


public class AboutAchievementFragment extends Fragment {

    private String name, descriptionStr;
    private Bitmap bitmap;

    public AboutAchievementFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = requireActivity().getIntent().getExtras();
        name = Objects.requireNonNull(Objects.requireNonNull(arguments).get("name")).toString();
        descriptionStr = Objects.requireNonNull(arguments.get("description")).toString();
        bitmap = arguments.getParcelable("bitmap");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about_achievement, container, false);

        AppCompatImageView imageView = view.findViewById(R.id.image_achieve);
        AppCompatTextView title = view.findViewById(R.id.achieve_title);
        AppCompatTextView description = view.findViewById(R.id.acheve_description);

        title.setText(name);
        description.setText(descriptionStr);
        imageView.setImageBitmap(bitmap);

        return view;
    }
}