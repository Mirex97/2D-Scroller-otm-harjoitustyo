
package VR.profile;


public class Save {
    private int id;
    private String letter;
    private boolean tutorialDone;
    private boolean level1Done;
    private boolean level2Done;
    public Save(int id, String letter, boolean tutorialDone, 
            boolean level1Done, boolean level2Done) {
        this.id = id;
        this.letter = letter;
        this.tutorialDone = tutorialDone;
        this.level1Done = level1Done;
        this.level2Done = level2Done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public boolean isTutorialDone() {
        return tutorialDone;
    }

    public void setTutorialDone(boolean tutorialDone) {
        this.tutorialDone = tutorialDone;
    }

    public boolean isLevel1Done() {
        return level1Done;
    }

    public void setLevel1Done(boolean level1Done) {
        this.level1Done = level1Done;
    }

    public boolean isLevel2Done() {
        return level2Done;
    }

    public void setLevel2Done(boolean level2Done) {
        this.level2Done = level2Done;
    }
    
    
}
