package au.edu.jcu.cp3406.guesstheceleb;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Objects;

import au.edu.jcu.cp3406.guesstheceleb.game.QuestionBuilder;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {
    public static final String TAG = "GameFragment";
    static int numberOfCelebrities = 3; // Need to fix this.
    Spinner spinnerDifficulty;
    private StateListener listener;
    private Difficulty level;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (StateListener) context;
    }

    public Difficulty getLevel() {
        return level;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View gameFragmentView = inflater.inflate(R.layout.fragment_game, container, false);
        Button playGameButton = gameFragmentView.findViewById(R.id.gamePlayButton);
        Log.d(TAG, "onCreateView: Started.");
        StatusFragment.isTimerRunning = false;
        spinnerDifficulty = gameFragmentView.findViewById(R.id.difficultySpinner);
        // Sets contents of spinner from Difficulty enum array.
        spinnerDifficulty.setAdapter(new ArrayAdapter<Difficulty>(Objects.requireNonNull(getActivity()), android.R.layout.simple_spinner_item, Difficulty.values()));
        playGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Run game Builder, Associate loaded images and names.
                // Transfer to fragment Question.
                level = Difficulty.valueOf(spinnerDifficulty.getSelectedItem().toString());
                numberOfCelebrities = level.getNumberOfCelebrities();
                new QuestionBuilder(level.getNumberOfCelebrities());
                StatusFragment.restartCountdownTimer();
                if (!MainActivity.isLargeScreen) {
                    ((MainActivity) Objects.requireNonNull(getActivity())).setNewPager(1);
                }

            }
        });
        return gameFragmentView;
    }


}
