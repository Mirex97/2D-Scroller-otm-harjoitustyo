/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VR.events;


public class CutsceneHandler {
    private NewGameCutscene newGame;
    private EndCutscene endGame;
    
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
    
}
