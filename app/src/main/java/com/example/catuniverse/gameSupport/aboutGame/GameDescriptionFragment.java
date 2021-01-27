package com.example.catuniverse.gameSupport.aboutGame;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.catuniverse.R;
import com.example.catuniverse.gameSupport.databaseHelpers.Cat;

import java.util.ArrayList;

import static com.example.catuniverse.MainActivity.listOfCats;

public class GameDescriptionFragment extends Fragment {

    private TextView title, description;
    private ImageView imageView;
    private RecyclerView recyclerView;
    private Button rooms, cats, time, strategy, maths, credits;
    private ArrayList<Cat>nothing;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.description_fragment, container, false);

        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);
        imageView = view.findViewById(R.id.imageDescription);
        rooms = view.findViewById(R.id.roomButton);
        cats = view.findViewById(R.id.catsButton);
        time = view.findViewById(R.id.timeButton);
        strategy = view.findViewById(R.id.strategyButton);
        maths = view.findViewById(R.id.mathsButton);
        credits = view.findViewById(R.id.creditsButton);
        recyclerView = view.findViewById(R.id.recycle);



        final Context context = getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);

        nothing = new ArrayList<>();

        rooms.setOnClickListener((vie) -> {
                recyclerView.setAdapter(new CatAdapter(nothing, context));
                title.setText(rooms.getText());
                description.setText(R.string.rooms_description);
                imageView.setImageResource(R.drawable.roommoon);
        });

        cats.setOnClickListener((vie) -> {
                title.setText(cats.getText());
                description.setText(R.string.cats_description); //"There you can see cats you already have." +
                recyclerView.setAdapter(new CatAdapter(listOfCats, context));
                imageView.setImageResource(R.drawable.room);
        });


        time.setOnClickListener((vie) -> {
                recyclerView.setAdapter(new CatAdapter(nothing, context));
                title.setText(time.getText());
                description.setText(R.string.time_description);
                imageView.setImageResource(R.drawable.time);
        });

        strategy.setOnClickListener((vie) -> {
                recyclerView.setAdapter(new CatAdapter(nothing, context));
                title.setText(strategy.getText());
                description.setText(R.string.strategy_description);
                imageView.setImageResource(R.drawable.strategy);

            }
        );

        maths.setOnClickListener((vie) -> {
                recyclerView.setAdapter(new CatAdapter(nothing, context));
                title.setText(maths.getText());
                description.setText(R.string.maths_description);
                imageView.setImageResource(R.drawable.maths);
            }
        );

        credits.setOnClickListener((vie) -> {
                recyclerView.setAdapter(new CatAdapter(nothing, context));
                title.setText(credits.getText());
                description.setText("Game Developer : Yana Gladkikh \n" + "Supported by Samsung IT School \n");
                imageView.setImageResource(R.drawable.samsungitschool);
        });

        return view;
    }
}

