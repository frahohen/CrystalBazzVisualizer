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
import crystalbazzvisualizer.definition.WaveBoxColorDefinition;
import crystalbazzvisualizer.definition.WaveBoxMassDefinition;

/**
 *
 * @author frahohen
 */
public class WaveBoxList extends Node {
    private int waveBoxCount;
    private WaveBoxMassDefinition waveBoxMassDefinition;
    private WaveBoxColorDefinition waveBoxColorDefinition;

    public WaveBoxList(String name, int waveBoxCount, WaveBoxMassDefinition waveBoxMassDefinition, WaveBoxColorDefinition waveBoxColorDefinition, SimpleApplication simpleApplication) {
        this.name = name;
        this.waveBoxCount = waveBoxCount;
        this.waveBoxMassDefinition = waveBoxMassDefinition;
        this.waveBoxColorDefinition = waveBoxColorDefinition;
        init(simpleApplication);
    }
    
    private void init(SimpleApplication simpleApplication){
        for(int i = 0; i < waveBoxCount; i++)
        {
            WaveBox box = new WaveBox(
                    // x ... length
                    // y ... maximum height
                    // z ... thickness
                    new Vector3f(waveBoxMassDefinition.getWaveBoxLength(), waveBoxMassDefinition.getWaveBoxHeight(), waveBoxMassDefinition.getWaveBoxThickness()),
                    new ColorRGBA(RGBtoFloat(waveBoxColorDefinition.getR()),RGBtoFloat(waveBoxColorDefinition.getG()), RGBtoFloat(waveBoxColorDefinition.getB()), 1.0f),
                    simpleApplication
                    
            );
            // Hier muss der wert immer das doppelte sein und geht man eins drüber, dann hat man einen abstand
            box.setLocalTranslation(
                    (
                        (waveBoxMassDefinition.getWaveBoxLength() * 2) + 
                        waveBoxMassDefinition.getWaveBoxGap()
                    ) * i, 
                    0f, 
                    0f
            );
            this.attachChild(box);
        }
    }
    
    public void updateLevel(FloatList list){
        for(int i = 0; i < this.getChildren().size(); i++)
        {
            this.getChild(i).setLocalScale(1f, list.getFloatList().get(i), 1f);
        }
    }
    
    private float RGBtoFloat(int value){
        float fvalue;
        fvalue = (2.0f/255.0f)*(float)value; 
        return fvalue;
    }
    
}
