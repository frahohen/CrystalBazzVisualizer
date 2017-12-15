/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalbazzvisualizer.service;

import crystalbazzvisualizer.list.FloatList;
import crystalbazzvisualizer.list.FrequencyFloatList;
import crystalbazzvisualizer.definition.Definition;
import ddf.minim.analysis.FFT;
import java.util.HashMap;

/**
 *
 * @author frahohen
 */
public class ChannelService {

    private HashMap<String, FloatList> channelMap;

    public ChannelService() {
        channelMap = new HashMap<String, FloatList>();
    }

    public void addChannel(String name, int count, String type) {
        if (type.equals(Definition.WAVEFORM)) {
            channelMap.put(name, new FloatList(count));
        }
        if (type.equals(Definition.FREQUENCY)) {
            channelMap.put(name, new FrequencyFloatList(count));
        }
    }

    public FloatList getChannel(String name) {
        return channelMap.get(name);
    }

    public synchronized void addLevel(String name, Float value) {
        // The new element must be always on top of the list 
        // => index = 0
        
        // Multiply the normalized value and you get a scaled output
        value = normalize(name, value) * 2;
        
        // To get a good normalization i have to play the whole audio once and get the highest and lowest peaks
        // Or i can also create a Sound at the start that takes the whole frequnecies and the whole loudness
        //System.out.println("MAX: " + channelMap.get(name).getMax());
        //System.out.println("MIN: " + channelMap.get(name).getMin());
        
        channelMap.get(name).getFloatList().add(0, value);

        int boxCount = channelMap.get(name).getBoxCount();
        int listSize = channelMap.get(name).getFloatList().size();

        // If the list is greater than the BoxCountDefinition then delete the outdated values
        if (listSize > boxCount) {
            channelMap.get(name).getFloatList().subList(boxCount, listSize).clear();
        }
    }

    public synchronized void setLevel(String name, FFT fft) {
        // maxFrequency is the maximum frequency that the human ear can hear 
        // => 20 000 Hz
        int maxFrequency = ((FrequencyFloatList) channelMap.get(name)).getMaxFrequency();
        // scale defines the gap to the next freqnecy that should be displayed 
        int scale = ((FrequencyFloatList) channelMap.get(name)).getScale();
        // This is the index of the list that increases in the for-loop 
        // until it has reached the waveBoxCount
        int index = 0;

        for (int i = 0; i < maxFrequency; i += scale) {
            float value = fft.getFreq(i);
            // Multiply the normalized value and you get a scaled output
            value = normalize(name, value) * 2;
            
            channelMap.get(name).getFloatList().set(index, value);
            index++;
        }
    }

    private float normalize(String name, float x) {
        // (xi-min(x))/(max(x)-min(x))
        float min = channelMap.get(name).getMin();
        float max = channelMap.get(name).getMax();
        
        if(x < min){
            min = x;
            channelMap.get(name).setMin(min);
        }
        
        if(x > max){
            if(x > Definition.CALIBRATION_VALUE){
                max = x;
            }else{
                max = Definition.CALIBRATION_VALUE;
            }
            channelMap.get(name).setMax(max);
        }
        
        float normalizedValue = (x - min) / (max - min);

        return normalizedValue;
    }
}
