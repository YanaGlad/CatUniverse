package com.glad.catuniverse.gameSupport.aboutGame;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.glad.catuniverse.R;
import com.glad.catuniverse.gameSupport.BasicGameSupport;
import com.glad.catuniverse.gameSupport.databaseHelpers.Cat;
import com.glad.catuniverse.MainActivity;

import java.util.ArrayList;

public class GameDescriptionFragment extends Fragment {

    private AppCompatTextView title, description;
    private AppCompatImageView imageView;
    private RecyclerView recyclerView;
    private Button rooms, cats, time, strategy, maths, credits, sortAttack, sortDelay, sortPrice;
    private ArrayList<Cat> nothing;

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
        sortAttack = view.findViewById(R.id.sortAttack);
        sortDelay = view.findViewById(R.id.sortDelay);
        sortPrice = view.findViewById(R.id.sortPrice);

        sortAttack.setVisibility(View.GONE);
        sortDelay.setVisibility(View.GONE);
        sortPrice.setVisibility(View.GONE);

        final Context context = getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);

        nothing = new ArrayList<>();

        rooms.setOnClickListener((vie) -> {

            sortAttack.setVisibility(View.GONE);
            sortDelay.setVisibility(View.GONE);
            sortPrice.setVisibility(View.GONE);

            recyclerView.setAdapter(new CatAdapter(nothing, requireContext()));
            title.setText(rooms.getText());
            description.setText(R.string.rooms_description);
            imageView.setImageResource(R.drawable.roommoon);
        });

        cats.setOnClickListener((vie) -> {

            sortAttack.setVisibility(View.VISIBLE);
            sortDelay.setVisibility(View.VISIBLE);
            sortPrice.setVisibility(View.VISIBLE);

            title.setText(cats.getText());
            description.setText(R.string.cats_description); //"There you can see cats you already have." +
            recyclerView.setAdapter(new CatAdapter(MainActivity.listOfCats, requireContext()));

            imageView.setImageResource(R.drawable.room);

            sortPrice.setOnClickListener(view1 -> {
                recyclerView.setAdapter(new CatAdapter(sortCharacteristic(1), requireContext()));
            });

            sortAttack.setOnClickListener(view1 -> {
                recyclerView.setAdapter(new CatAdapter(sortCharacteristic(2), requireContext()));
            });

            sortDelay.setOnClickListener(view1 -> {
                recyclerView.setAdapter(new CatAdapter(sortCharacteristic(3), requireContext()));
            });
        });

        time.setOnClickListener((vie) -> {

            sortAttack.setVisibility(View.GONE);
            sortDelay.setVisibility(View.GONE);
            sortPrice.setVisibility(View.GONE);

            recyclerView.setAdapter(new CatAdapter(nothing, requireContext()));
            title.setText(time.getText());
            description.setText(R.string.time_description);
            imageView.setImageResource(R.drawable.time);
        });

        strategy.setOnClickListener((vie) -> {

                    sortAttack.setVisibility(View.GONE);
                    sortDelay.setVisibility(View.GONE);
                    sortPrice.setVisibility(View.GONE);

                    recyclerView.setAdapter(new CatAdapter(nothing, requireContext()));
                    title.setText(strategy.getText());
                    description.setText(R.string.strategy_description);
                    imageView.setImageResource(R.drawable.strategy);
                }
        );

        maths.setOnClickListener((vie) -> {

                    sortAttack.setVisibility(View.GONE);
                    sortDelay.setVisibility(View.GONE);
                    sortPrice.setVisibility(View.GONE);

                    recyclerView.setAdapter(new CatAdapter(nothing, requireContext()));
                    title.setText(maths.getText());
                    description.setText(R.string.maths_description);
                    imageView.setImageResource(R.drawable.maths);
                }
        );

        credits.setOnClickListener((vie) -> {

            sortAttack.setVisibility(View.GONE);
            sortDelay.setVisibility(View.GONE);
            sortPrice.setVisibility(View.GONE);

            recyclerView.setAdapter(new CatAdapter(nothing, requireContext()));
            title.setText(credits.getText());
            description.setText("Game Developer : Yana Gladkikh \n" + "Supported by Samsung IT School \n");
            imageView.setImageResource(R.drawable.samsungitschool);
        });

        return view;
    }

    public ArrayList<Cat> sortCharacteristic(int id) {
        int[] array = new int[MainActivity.listOfCats.size()];

        for (int i = 0; i < MainActivity.listOfCats.size(); i++) {
            array[i] = (int) MainActivity.listOfCats.get(i).getCharacteristics(id);
        }
        BasicGameSupport.quickSort(array, 0, MainActivity.listOfCats.size() - 1);

        ArrayList<Cat> cats = new ArrayList<>();

        for (int i = 0; i < MainActivity.listOfCats.size(); i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i] == MainActivity.listOfCats.get(j).getCharacteristics(id)) {
                    boolean no = false;
                    for (int k = 0; k < cats.size(); k++) {
                        if (cats.get(k) == MainActivity.listOfCats.get(j)) no = true;
                    }
                    if (!no)
                        cats.add(MainActivity.listOfCats.get(j));
                }
            }
        }
        return cats;
    }
}
