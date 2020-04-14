

/*
 * Originally written by Jason Hunter, http://www.servlets.com.
 */

package num;

import java.io.Serializable;
import java.util.Random;

public class NumberGuessBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private int answer;
    private String hint;
    private int numGuesses;
    private boolean success;
    private final Random random = new Random();

    public NumberGuessBean() {
        reset();
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getHint() {
        return "" + hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public void setNumGuesses(int numGuesses) {
        this.numGuesses = numGuesses;
    }

    public int getNumGuesses() {
        return numGuesses;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setGuess(String guess) {
        numGuesses++;

        int g;
        try {
            g = Integer.parseInt(guess);
        } catch (NumberFormatException e) {
            g = -1;
        }

        if (g == answer) {
            success = true;
        } else if (g == -1) {
            hint = "a number next time";
        } else if (g < answer) {
            hint = "higher";
        } else if (g > answer) {
            hint = "lower";
        }
    }

    public void reset() {
        answer = Math.abs(random.nextInt() % 100) + 1;
        success = false;
        numGuesses = 0;
    }
}
