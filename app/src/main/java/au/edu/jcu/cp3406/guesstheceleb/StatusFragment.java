package au.edu.jcu.cp3406.guesstheceleb;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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
    TextView scoreTextView;
    View statusFragmentView;
    static CountDownTimer countDownTimer;
    static boolean isTimerRunning;

    public StatusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        statusFragmentView = inflater.inflate(R.layout.fragment_status, container, false);
        timeRemaining = statusFragmentView.findViewById(R.id.timeRemaining);
        scoreTextView = statusFragmentView.findViewById(R.id.scoreTextView);
        countDownTimer();
        return statusFragmentView;
    }

    public void updateScore() {
        scoreTextView.setText(Game.getScore());
    }

    // Creates timer and adds result to string.
    public void countDownTimer() {
        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                isTimerRunning = true;
                timeRemaining.setText(String.format(Locale.getDefault(), "%s%s%s",
                        "Time Remaining: ",
                        String.valueOf((int) (millisUntilFinished / 1000))
                        , " Seconds."));
                updateScore();
            }

            // Once timer is finished calls pop up window.
            @Override
            public void onFinish() {
                isTimerRunning = false;
                startActivity(new Intent(getContext(), PopWindow.class));
            }
        }.start();
    }

    // calls for countdown timer to reset.
    public static void restartCountdownTimer() {
        if (isTimerRunning = true) {
            countDownTimer.cancel();
            countDownTimer.start();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void setScoreTextView(String score) {

    }
}
