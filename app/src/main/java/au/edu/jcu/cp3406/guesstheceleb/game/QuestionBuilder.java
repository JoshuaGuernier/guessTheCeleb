package au.edu.jcu.cp3406.guesstheceleb.game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Random;

import au.edu.jcu.cp3406.guesstheceleb.CelebrityManager;

public class QuestionBuilder extends AppCompatActivity {
    public static Question question;
    public static String[] questionsOut;
    int randomCeleb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Filler
    }

    public QuestionBuilder(int difficultyNumber) {
        // Gets random celeb.
        Random random = new Random();
        randomCeleb = random.nextInt(CelebrityManager.count());

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
