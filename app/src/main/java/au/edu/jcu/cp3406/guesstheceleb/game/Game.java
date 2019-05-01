package au.edu.jcu.cp3406.guesstheceleb.game;

import android.util.Log;

public class Game {

    public static final String TAG = "Game";
    private int questionNumber;
    private static int questionsTotal;
    private static int score;
    private Question[] questions;

    public Game(Question[] questions) {
        this.questions = questions;
        questionNumber = 0;
        questionsTotal = questions.length;
    }

    public boolean isGameOver() {
        questionNumber ++;
        return questionNumber == questionsTotal;
    }

    public Question next() {
        return questions[questionNumber];
    }

    public static void updateScore(boolean correctCheck) {
        if (correctCheck) {
            score++;
        }
        questionsTotal ++;
        System.out.println(score + " / " +  questionsTotal);
    }

    public static String getScore() {
        return ("Score: " + score + " / " + questionsTotal);
    }
}
