/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VR.mapitems;

import VR.Main;
import VR.entities.Player;
import VR.gui.Gui;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Properties;

public class Cutscene {

    private String type;
    private String label;
    private ArrayList<String> keyset;
    private Properties properties;
    private Rectangle collision;
    private boolean finished;
    private int currentProp;
    private int helpfulPointer;
    private int helpfulCounter;
    private ArrayList<String> characters;
    private ArrayList<String> text;
    private boolean written = false;
    private boolean now = false;
    private boolean once = false;

    public Cutscene(String type, String label, ArrayList<String> keyset, Properties properties, Rectangle collision) {
        characters = new ArrayList<>();
        text = new ArrayList<>();
        finished = false;
        helpfulPointer = 0;
        currentProp = 0;
        this.type = type;
        this.keyset = keyset;
        this.properties = properties;
        this.collision = collision;
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }

    public Rectangle getCollision() {
        return collision;
    }

    public boolean getFinished() {
        return finished;
    }

    public void play(Player player, Gui gui) {
        if (currentProp < keyset.size()) {
            if (now) {
                if (!once) {
                    gui.write(new Text("Cutscene", characters, text, null, "nothing"));
                    once = true;
                }
                if (gui.getText() != null) {
                    gui.drawText();
                } else {
                    once = false;
                    now = false;
                    written = false;
                    characters = new ArrayList<>();
                    text = new ArrayList<>();
                }
            }
            String[] split = keyset.get(currentProp).split(":");
            if (split[1].equals("Player")) {
                characters.add("Player");
                text.add(properties.getProperty(keyset.get(currentProp)));
                written = true;
                currentProp++;
            }
            if (split[1].equals("Buzz")) {
                characters.add("Intercom");
                written = true;
                text.add(properties.getProperty(keyset.get(currentProp)));
                currentProp++;
            }
            if (split[1].equals("Move")) {
                if (written) {
                    now = true;
                } else {

                    String[] splitted = properties.getProperty(keyset.get(currentProp)).split(";");
                    if (splitted[helpfulPointer].equals("FINISH:0")) {
                        helpfulPointer = 0;
                        helpfulCounter = 0;
                        currentProp++;
                    } else {
                        String[] splitty = splitted[helpfulPointer].split(":");
                        if (splitty[0].equals("LEFT")) {
                            if (helpfulCounter < Integer.parseInt(splitty[1])) {
                                player.moveCUT(Player.Dir.LEFT);
                                helpfulCounter++;
                            } else {
                                helpfulCounter = 0;
                                helpfulPointer++;
                            }
                        }
                        if (splitty[0].equals("RIGHT") || splitty[0].equals("RIGTH")) {
                            if (helpfulCounter < Integer.parseInt(splitty[1])) {
                                player.moveCUT(Player.Dir.RIGHT);
                                helpfulCounter++;
                            } else {
                                helpfulCounter = 0;
                                helpfulPointer++;
                            }
                        }
                        if (splitty[0].equals("STILL")) {
                            if (helpfulCounter < Integer.parseInt(splitty[1])) {
                                player.moveCUT(Player.Dir.DOWN);
                                helpfulCounter++;
                            } else {
                                helpfulCounter = 0;
                                helpfulPointer++;
                            }
                        }

                    }
                }
            }
            if (split[1].equals("Smurf")) {
                characters.add("Smurfradar");
                written = true;
                text.add(properties.getProperty(keyset.get(currentProp)));
                currentProp++;

            }

        } else {
            if (written) {
                if (!once) {
                    gui.write(new Text("Cutscene", characters, text, null, "nothing"));
                    once = true;
                }
                if (gui.getText() != null) {
                    gui.drawText();
                } else {
                    once = false;
                    now = false;
                    written = false;
                    characters = new ArrayList<>();
                    text = new ArrayList<>();
                }
            } else {
                finished = true;
            }
        }

    }

}
