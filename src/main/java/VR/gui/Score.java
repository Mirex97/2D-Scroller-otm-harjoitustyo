
package VR.gui;

//This class contains score which is taken from loaded save or initiated as new!
//Score is the same thing as the players money!
public class Score {
    private int score; //actual score!
    private int currentScore; //currentScore for animation purposes!
    
    public Score() {
        score = 0;
        currentScore = 0;
    }
    
    public void setScore(int amount) {
        this.score = amount;
    }
    
    //negative is accepted!
    public void addScore(int amount) {
        score += amount;
    }
    
    //This is the real method to use for example at the end of the map!
    public int getActualScore() {
        return score;
    }
    
    //Do not use this when saving!
    public int getScore() {
        return currentScore;
    }
    
    //for animation purposes!
    public void updateScore() {
        if (currentScore > score) {
            currentScore--;
        }
        if (currentScore < score) {
            currentScore++;
        }
    }
}
