package au.edu.jcu.cp3406.guesstheceleb;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

import au.edu.jcu.cp3406.guesstheceleb.game.Game;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatusFragment extends Fragment {

    public static final String TAG = "StatusFragment";
    TextView timeRemaining;
    TextView score;
    private boolean isRunning;
    private int speed = 1000;
    private static int secondsLeft = 60;

    Handler handler = new Handler();

    public StatusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View statusFragmentView = inflater.inflate(R.layout.fragment_status, container, false);
        timeRemaining = statusFragmentView.findViewById(R.id.timeRemaining);
        score = statusFragmentView.findViewById(R.id.score);
        // Inflate the layout for this fragment
        countDownTimer();
        return statusFragmentView;
    }

    public void updateScore(){
        score.setText(Game.getScore());
    }

    public void disableWatch() {
        isRunning = false;
        handler.removeMessages(0);
    }

    public void countDownTimer(){
        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int mathOut = (int) (millisUntilFinished / 1000);
                timeRemaining.setText(String.format(Locale.getDefault(),"%s%s%s",
                        getText(R.string.time_left), String.valueOf(mathOut), getText(R.string.seconds)));
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(getActivity(), Pop.class));
            }
        }.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }

    public void tick(){
        secondsLeft--;
    }


}
