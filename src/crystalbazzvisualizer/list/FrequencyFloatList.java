/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalbazzvisualizer.list;

/**
 *
 * @author -QUESTION-
 */
public class FrequencyFloatList extends FloatList {
    
    private int maxFrequency;
    private int scale;
    
    public FrequencyFloatList(int boxCount) {
        super(boxCount);
        init(boxCount);   
    }
    
    private void init(int boxCount){
        maxFrequency = 20000;
        scale = maxFrequency/boxCount;
    }

    public int getMaxFrequency() {
        return maxFrequency;
    }

    public int getScale() {
        return scale;
    }
}
