package au.edu.jcu.cp3406.guesstheceleb;

import org.junit.Test;

import au.edu.jcu.cp3406.guesstheceleb.game.Game;
import au.edu.jcu.cp3406.guesstheceleb.game.Question;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class gameUnitTest {
    @Test
    public void testGame(){
        Question[] questions = new Question[3];
        String[] answers = new String[]{"bob","jane","harry"};
        for (int i = 0; i < 3 ; ++i){
            questions[i] = new Question(answers[i], null, answers);
        }
        Game game = new Game(questions) ;

        while (!game.isGameOver()) {
            Question question = game.next();
            Game.updateScore(question.check("bob"));
        }
        assertEquals("Score: 1/3", Game.getScore());
    }
}