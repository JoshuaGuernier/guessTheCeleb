package au.edu.jcu.cp3406.guesstheceleb.game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Random;
import au.edu.jcu.cp3406.guesstheceleb.CelebrityManager;
import au.edu.jcu.cp3406.guesstheceleb.Difficulty;

public class QuestionBuilder extends AppCompatActivity {
    public static final String TAG = "QuestionBuilder";
    public static Question question;
    public static String[] questionsOut;
    int randomCeleb;

    public static Game create(Difficulty level) {
        Random random = new Random();
        int randomCeleb = random.nextInt(CelebrityManager.count());

        question = new Question(CelebrityManager.getName(randomCeleb),
                CelebrityManager.get(randomCeleb), CelebrityManager.getImageNamesReformatted());
        // Creates and initializes string array.
        questionsOut = new String[level.getNumberOfCelebrities()];
        String[] possibleNames = question.getPossibleNames();
        // Randomly inputs celebrity names into questions out array.
        for (int i = 0; i < level.getNumberOfCelebrities(); i++) {
            questionsOut[i] = possibleNames[random.nextInt(CelebrityManager.count())];
        }
        // Randomly inserts chosen celebrity name to questions out array.
        questionsOut[random.nextInt(questionsOut.length)] = CelebrityManager.getName(randomCeleb);
        return null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Filler
    }

    public QuestionBuilder(int difficultyNumber) {
        // Gets random celeb.
        Random random = new Random();
        randomCeleb = random.nextInt(CelebrityManager.count());
        Log.d(TAG, "Started random Celeb " + randomCeleb);
        question = new Question(CelebrityManager.getName(randomCeleb),
                CelebrityManager.get(randomCeleb), CelebrityManager.getImageNamesReformatted());
        // Creates and initializes string array.
        questionsOut = new String[difficultyNumber];
        String[] possibleNames = question.getPossibleNames();
        // Randomly inputs celebrity names into questions out array.
        for (int i = 0; i < difficultyNumber; i++) {
            questionsOut[i] = possibleNames[random.nextInt(CelebrityManager.count())];
        }
        // Randomly inserts chosen celebrity name to questions out array.
        questionsOut[random.nextInt(questionsOut.length)] = CelebrityManager.getName(randomCeleb);
    }

}
