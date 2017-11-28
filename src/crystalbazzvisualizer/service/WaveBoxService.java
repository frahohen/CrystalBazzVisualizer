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

/**
 *
 * @author -QUESTION-
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
    
    public void addBoxList(String name, int boxCount, SimpleApplication simpleApplication){
        this.attachChild(new WaveBoxList(name, boxCount, simpleApplication));
    }
    
    public void update(String name, FloatList floatList){
        ((WaveBoxList)this.getChild(name)).updateLevel(floatList);
        /*
        for(int i = 0; i < this.getChildren().size(); i++){
            CBBoxList boxList = (CBBoxList) this.getChild(i);
            // TODO: Jede Boxliste muss einen äquivalenten Channel haben
            // TODO: nicht GEOMETRY und NODE extenden !!!!!!!!!!!!!!!!!!!
            boxList.updateLevel(list);
        }
        */
    }
    // This can only be attached if all lists are added
    //simpleApplication.getRootNode().attachChild(node);
    
}
