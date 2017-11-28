/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalbazzvisualizer.list;

import crystalbazzvisualizer.object.WaveBox;
import com.jme3.app.SimpleApplication;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/**
 *
 * @author -QUESTION-
 */
public class WaveBoxList extends Node {
    
    //private ArrayList<CBBox> boxList;
    private int boxCount;

    public WaveBoxList(String name, int boxCount, SimpleApplication simpleApplication) {
        this.name = name;
        this.boxCount = boxCount;
        init(simpleApplication);
    }
    
    private void init(SimpleApplication simpleApplication){
        for(int i = 0; i < boxCount; i++)
        {
            WaveBox box = new WaveBox(
                    new Vector3f(1f, 1f, 1f),
                    new ColorRGBA(0,RGBtoFloat(50), RGBtoFloat(160), 1.0f),
                    simpleApplication
                    
            );
            // Hier muss der wert immer das doppelte sein und geht man eins drüber, dann hat man einen abstand
            box.setLocalTranslation(2.1f*i, 0f, 0f);
            this.attachChild(box);
        }
    }
    
    public void updateLevel(FloatList list){
        for(int i = 0; i < this.getChildren().size(); i++)
        {
            this.getChild(i).setLocalScale(1f, list.getFloatList().get(i), 1f);
        }
    }
    
     private float RGBtoFloat(int value)
    {
        float fvalue;
        fvalue = (2.0f/255.0f)*(float)value; 
        return fvalue;
    }
    
}
