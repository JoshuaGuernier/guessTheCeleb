package au.edu.jcu.cp3406.guesstheceleb;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    CelebrityManager celebrityManager;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started. ");
        // Sets up Celebrity Manager.
        celebrityManager = new CelebrityManager(this.getAssets(), "celebs");
        viewPager = findViewById(R.id.MainContainer);
        SectionsStatePageAdapter sectionsStatePageAdapter = new SectionsStatePageAdapter(getSupportFragmentManager());
        setViewPager(viewPager);

    }
    // Adds fragments to Sections State Page Adapter.
    private void setViewPager(ViewPager viewPager){
        SectionsStatePageAdapter adapter = new SectionsStatePageAdapter(getSupportFragmentManager());
        adapter.addFragment(new GameFragment(), GameFragment.TAG);
        adapter.addFragment(new QuestionFragment(), QuestionFragment.TAG);
        adapter.addFragment(new StatusFragment(), StatusFragment.TAG);
        viewPager.setAdapter(adapter);
    }

    public void setNewPager (int fragmentNumber){
        viewPager.setCurrentItem(fragmentNumber);
    }

    public void errorMessage(){

    }
}
