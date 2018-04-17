package VR.handlers;

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class Keylistener implements Keyhandler {

    private ArrayList<String> input = new ArrayList<>();

    public Keylistener(Scene scene) {

        scene.setOnKeyPressed((KeyEvent e) -> {
            String code = e.getCode().toString();
            if (!input.contains(code)) {
                input.add(code);
            }
        });

        scene.setOnKeyReleased((KeyEvent e) -> {
            String code = e.getCode().toString();
            input.remove(code);
        });
    }

    @Override
    public ArrayList<String> getInput() {
        return input;
    }
}
