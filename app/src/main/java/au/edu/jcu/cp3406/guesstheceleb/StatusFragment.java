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
    TextView score;
    static CountDownTimer countDownTimer;

    public StatusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View statusFragmentView = inflater.inflate(R.layout.fragment_status, container, false);
        timeRemaining = statusFragmentView.findViewById(R.id.timeRemaining);
        score = statusFragmentView.findViewById(R.id.score);
        countDownTimer();
        return statusFragmentView;
    }

    public void updateScore() {
        score.setText(Game.getScore());
    }

    // Creates timer and adds result to string.
    public void countDownTimer() {
        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int mathOut = (int) (millisUntilFinished / 1000);
                timeRemaining.setText(String.format(Locale.getDefault(), "%s%s%s%s%s",
                        getText(R.string.time_left), " ", String.valueOf(mathOut), " ", getText(R.string.seconds)));
                updateScore();
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(getActivity(), Pop.class));
            }
        }.start();
    }

    // calls for countdown timer to reset.
    public static void restartCountdownTimer() {
        countDownTimer.cancel();
        countDownTimer.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
