/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VR.events;

import VR.entities.Player;


public class CutsceneHandler {
    private NewGameCutscene newGame;
    private EndCutscene endGame;
    private LoseScene loseGame;
    
    public CutsceneHandler() {
        
    }
    
    public NewGameCutscene getNew() {
        newGame = new NewGameCutscene();
        return newGame;
    }
    public EndCutscene getEnd() {
        endGame = new EndCutscene();
        return endGame;
    }
    
    public LoseScene getLose(String loseType) {
        loseGame = new LoseScene(loseType);
        return loseGame;
    }
    
}
