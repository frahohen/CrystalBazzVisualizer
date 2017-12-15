/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalbazzvisualizer.service;

import crystalbazzvisualizer.list.WaveBoxList;
import crystalbazzvisualizer.list.FloatList;
import com.jme3.app.SimpleApplication;
import com.jme3.scene.Node;
import crystalbazzvisualizer.definition.WaveBoxColorDefinition;
import crystalbazzvisualizer.definition.WaveBoxMassDefinition;

/**
 *
 * @author frahohen
 */
public class WaveBoxService extends Node {
    
    /*
        TODO: This service should handle the CBoxList scaling in x y and z.
              The Developer should just give in the parameters:
                - x and y of each box
                - gap between boxes
                - max z of each box
              
              In the CBChannelService the scaling of the normalization by multiplying the normalize function
              should also be handled by this service. 
              The "max z of each box" should be here the reference for multiplication.
    */
    
    public WaveBoxService(String name) {
        this.name = name;
    }
    
    public void addBoxList(String name, int boxCount, WaveBoxMassDefinition waveBoxMassDefinition, WaveBoxColorDefinition waveBoxColorDefinition, SimpleApplication simpleApplication){
        this.attachChild(new WaveBoxList(name, boxCount, waveBoxMassDefinition, waveBoxColorDefinition, simpleApplication));
    }
    
    public void update(String name, FloatList floatList){
        ((WaveBoxList)this.getChild(name)).updateLevel(floatList);
    }
    
    public void rotateBoxList(String name, float x, float y, float z){
        ((WaveBoxList)this.getChild(name)).rotate(x, y, z);
    }
    
    public void moveBoxList(String name, float x, float y, float z){
        ((WaveBoxList)this.getChild(name)).move(x, y, z);
    }
}
