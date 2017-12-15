/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalbazzvisualizer.list;

import java.util.ArrayList;

/**
 *
 * @author frahohen
 */
public class FloatList {

    private ArrayList<Float> floatList;
    private int boxCount;
    
    private float min;
    private float max;

    public FloatList(int boxCount) {
        this.boxCount = boxCount;
        this.floatList = new ArrayList<Float>();
        init();
    }

    private void init() {
        for (int i = 0; i < boxCount; i++) {
            floatList.add(i, 0f);
        }
        
        min = 0;
        max = 0;
    }

    public int getBoxCount() {
        return boxCount;
    }

    public ArrayList<Float> getFloatList() {
        return floatList;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }
    
    
}
