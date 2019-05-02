package au.edu.jcu.cp3406.guesstheceleb;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.View;

import au.edu.jcu.cp3406.guesstheceleb.game.QuestionBuilder;

public class Pop extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_window);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.8));
    }

    public void restartGame(View view) {
        new QuestionBuilder(GameFragment.numberOfCelebrities);
        StatusFragment.restartCountdownTimer();
    }

    public void quitGame(View view) {
        moveTaskToBack(true);
        finish();
    }
}
