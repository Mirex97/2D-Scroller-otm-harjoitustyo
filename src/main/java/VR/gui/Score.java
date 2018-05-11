
package VR.gui;


public class Score {
    private int score; 
    private int currentScore; 
    
    public Score() {
        score = 0;
        currentScore = 0;
    }
    
    public void setScore(int amount) {
        this.score = amount;
    }
    
    public void addScore(int amount) {
        score += amount;
    }
    
    public int getActualScore() {
        return score;
    }
    
    public int getScore() {
        return currentScore;
    }
    
    public void updateScore() {
        if (currentScore > score) {
            currentScore--;
        }
        if (currentScore < score) {
            currentScore++;
        }
    }
}
