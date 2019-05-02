package au.edu.jcu.cp3406.guesstheceleb;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Objects;

import au.edu.jcu.cp3406.guesstheceleb.game.QuestionBuilder;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {
    public static final String TAG = "GameFragment";
    static int numberOfCelebrities = 3; // Implies start on Easy.
    Spinner spinnerDifficulty;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View gameFragmentView = inflater.inflate(R.layout.fragment_game, container, false);
        Button playGameButton = gameFragmentView.findViewById(R.id.gamePlayButton);
        Log.d(TAG, "onCreateView: Started");


        spinnerDifficulty = gameFragmentView.findViewById(R.id.difficultySpinner);
        playGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Run game Builder, Associate loaded images and names.
                // Transfer to fragment Question.
                new QuestionBuilder(getDifficultyNumber());
                StatusFragment.restartCountdownTimer();
                ((MainActivity) Objects.requireNonNull(getActivity())).setNewPager(1);

            }
        });
        return gameFragmentView;
    }

    // Gets number
    public int getDifficultyNumber() {
        String difficulty = spinnerDifficulty.getSelectedItem().toString();
        System.out.println(difficulty);
        switch (difficulty) {
            case "Easy":
                numberOfCelebrities = 3;
                break;
            case "Medium":
                numberOfCelebrities = 6;
                break;
            case "Hard":
                numberOfCelebrities = 12;
                break;
            case "Expert":
                numberOfCelebrities = 24;
                break;
        }
        return numberOfCelebrities;
    }
}
