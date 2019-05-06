package au.edu.jcu.cp3406.guesstheceleb;

public enum Difficulty {
    EASY(3), MEDIUM(6), HARD(12), EXPERT(24);

    private int numberOfCelebrities;

    Difficulty(int numberOfCelebrities) {
        this.numberOfCelebrities = numberOfCelebrities;
    }

    public int getNumberOfCelebrities() {
        return numberOfCelebrities;
    }
}
