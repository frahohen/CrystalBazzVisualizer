/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalbazzvisualizer.definition;

/**
 *
 * @author frahohen
 */
public class WaveBoxCountDefinition {
    int mixChannel;
    int leftChannel;
    int rightChannel;
    int frequencyChannel;
    
    public int getMixChannel() {
        return mixChannel;
    }

    public void setMixChannel(int mixChannel) {
        this.mixChannel = mixChannel;
    }

    public int getLeftChannel() {
        return leftChannel;
    }

    public void setLeftChannel(int leftChannel) {
        this.leftChannel = leftChannel;
    }

    public int getRightChannel() {
        return rightChannel;
    }

    public void setRightChannel(int rightChannel) {
        this.rightChannel = rightChannel;
    }

    public int getFrequencyChannel() {
        return frequencyChannel;
    }

    public void setFrequencyChannel(int frequencyChannel) {
        this.frequencyChannel = frequencyChannel;
    }
}
