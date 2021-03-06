package au.edu.jcu.cp3406.guesstheceleb.game;


public class Game {

    public static final String TAG = "Game";
    private int questionNumber;
    private static int questionsTotal, score;
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
    }

    public static String getScore() {
        return ("Score: " + score + " / " + questionsTotal);
    }

}
