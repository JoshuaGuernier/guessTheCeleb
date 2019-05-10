package au.edu.jcu.cp3406.guesstheceleb;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import au.edu.jcu.cp3406.guesstheceleb.game.Game;
import au.edu.jcu.cp3406.guesstheceleb.game.QuestionBuilder;


public class MainActivity extends AppCompatActivity implements StateListener {

    private static final String TAG = "MainActivity";

    // Sets large screen to inches.

    private ViewPager viewPager;
    private GameFragment gameFragment;
    private StatusFragment statusFragment;
    private QuestionFragment questionFragment;
    static boolean isLargeScreen = false;
    double diagonalInches;
    double largeScreen = 6.5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started. ");
        // Sets up Celebrity Manager.
        viewPager = findViewById(R.id.MainContainer);
        SectionsStatePageAdapter sectionsStatePageAdapter = new SectionsStatePageAdapter(getSupportFragmentManager());
        calculateScreenSize();
        if (diagonalInches < largeScreen) {
            setViewPager(viewPager);
        } else {
//         Sets up fragment grouping.
            FragmentManager fragmentManager = getSupportFragmentManager();
            gameFragment = (GameFragment) fragmentManager.findFragmentById(R.id.gameFragment);
            statusFragment = (StatusFragment) fragmentManager.findFragmentById(R.id.statusFragment);
            questionFragment = (QuestionFragment) fragmentManager.findFragmentById(R.id.questionFragment);
            isLargeScreen = statusFragment != null;
            // Finds inches of height and width of display and calculates if the screen is larger than 7.
        }
    }

    private void calculateScreenSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float yInches = displayMetrics.heightPixels / displayMetrics.ydpi;
        float xInches = displayMetrics.widthPixels / displayMetrics.xdpi;
        diagonalInches = Math.sqrt(Math.pow(xInches, 2) + Math.pow(yInches, 2));
    }
    // Adds fragments to Sections State Page Adapter.
    private void setViewPager(ViewPager viewPager) {
        SectionsStatePageAdapter adapter = new SectionsStatePageAdapter(getSupportFragmentManager());
        adapter.addFragment(new GameFragment(), GameFragment.TAG);
        adapter.addFragment(new QuestionFragment(), QuestionFragment.TAG);
        adapter.addFragment(new StatusFragment(), StatusFragment.TAG);
        viewPager.setAdapter(adapter);
    }


    public void setNewPager(int fragmentNumber) {
        viewPager.setCurrentItem(fragmentNumber);
    }

    @Override
    public void onUpdate(State state) {
        Difficulty level = gameFragment.getLevel();
        new CelebrityManager(this.getAssets(), "celebs");
        Log.d(TAG, "State: " + state);
        if (isLargeScreen) {
            switch (state) {
                case START_GAME:
                    Game game = QuestionBuilder.create(level);
                    questionFragment.setGame(game);
                    break;
                case CONTINUE_GAME:
                    statusFragment.setScoreTextView(Game.getScore());
                    break;
                case GAME_OVER:
                    statusFragment.setScoreTextView(Game.getScore());
                    startActivity(new Intent(this, PopWindow.class));
                    break;
            }
        } else {
            // Something happens here.
        }

    }
    public void questionMaker(){
        new CelebrityManager(this.getAssets(), "celebs");
    }
}
