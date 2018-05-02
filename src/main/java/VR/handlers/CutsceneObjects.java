/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VR.handlers;

import VR.mapitems.Cutscene;
import VR.mapitems.Text;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import tiled.core.Map;
import tiled.core.MapLayer;
import tiled.core.MapObject;
import tiled.core.ObjectGroup;

/**
 *
 * @author MK
 */
public class CutsceneObjects {
    
    private ObjectGroup group;
    private ArrayList<Cutscene> cutscenes;

    public CutsceneObjects(Map map) {
        this.group = findCuts(map);
        this.cutscenes = new ArrayList<>();
        findInstructionsFromGroup();
    }
    
    public ObjectGroup findCuts(Map map) {
        for (MapLayer layer : map.getLayers()) {
            if (layer.getName().equals("Cutscenes")) {
                return (ObjectGroup) layer;
            }
        }
        System.out.println("Cutscenes not found!");
        return null;
    }
    
    public ArrayList<Cutscene> getCuts() {
        return cutscenes;
    }
    
    public void removeCut(Cutscene cut) {
        this.cutscenes.remove(cut);
    }

    private void findInstructionsFromGroup() {
        Iterator<MapObject> meh = group.getObjects();
        int scale = 2;
        while (meh.hasNext()) {
            MapObject object = meh.next();
            Rectangle collision = new Rectangle((int) (object.getX() * scale),
                    (int) (object.getY() * scale),
                    (int) (object.getWidth() * scale),
                    (int) (object.getHeight() * scale));
            String label = object.getName();
            Set<Object> wat = object.getProperties().keySet();
            ArrayList<String> keyset = new ArrayList<>();
            for (Object hah : wat) {
                keyset.add((String) hah);
            }
            Collections.sort(keyset);

            Cutscene cut = new Cutscene(object.getType(), label, keyset, object.getProperties(), collision);
            cutscenes.add(cut);
        }
    }
    
}
