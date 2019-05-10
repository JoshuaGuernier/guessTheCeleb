package au.edu.jcu.cp3406.guesstheceleb;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Objects;

import au.edu.jcu.cp3406.guesstheceleb.game.Game;
import au.edu.jcu.cp3406.guesstheceleb.game.QuestionBuilder;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {
    public static final String TAG = "GameFragment";
    ImageView celebrityImageView;
    View fragmentQuestionView;

    private StateListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (StateListener) context;
    }

    public QuestionFragment() {
        // Required empty public constructor
    }

    // Makes buttons dynamically and allocates to button holder view.
    public void makeButtons() {
        ((MainActivity) Objects.requireNonNull(getActivity())).questionMaker();
        Log.d(TAG, "makeButtons: Started");
        new QuestionBuilder(GameFragment.numberOfCelebrities);
        celebrityImageView.setImageBitmap(QuestionBuilder.question.getCelebrityImage());
        final LinearLayout linearLayout = fragmentQuestionView.findViewById(R.id.buttonHolder);
        for (int i = 0; i < GameFragment.numberOfCelebrities; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            // Add buttons to
            Button button = new Button(fragmentQuestionView.getContext());
            button.setId(i);
            final int id_ = button.getId();
            button.setText(QuestionBuilder.questionsOut[i]);
            button.setBackgroundColor(Color.GRAY);
            button.setLayoutParams(params);
            linearLayout.addView(button, params);
            button = fragmentQuestionView.findViewById(id_);
            button.setVisibility(View.VISIBLE);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "Button Clicked");
                    Button generatedButton = (Button) view;
                    boolean result = QuestionBuilder.question.check(generatedButton.getText().toString());
                    Game.updateScore(result);
                    if (result) {
                        resultToastOut("Correct!");
                    } else {
                        resultToastOut("Incorrect!");
                    }
                    linearLayout.removeAllViewsInLayout();
                    makeButtons();
                }
            });
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentQuestionView = inflater.inflate(R.layout.fragment_question, container, false);
        Log.d(TAG, "onCreateView: Started");
        celebrityImageView = fragmentQuestionView.findViewById(R.id.celebrityImage);
        makeButtons();
        return fragmentQuestionView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void resultToastOut(String messageOut) {
        Toast toast = Toast.makeText(getContext(), messageOut, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

    public void setGame(Game game) {
    }
}
