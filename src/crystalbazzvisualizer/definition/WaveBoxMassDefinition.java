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
public class WaveBoxMassDefinition {
    private float waveBoxLength;
    private float waveBoxHeight;
    private float waveBoxThickness; 
    private float waveBoxGap;

    public WaveBoxMassDefinition(float waveBoxLength, float waveBoxHeight, float waveBoxThickness, float waveBoxGap) {
        this.waveBoxLength = waveBoxLength;
        this.waveBoxHeight = waveBoxHeight;
        this.waveBoxThickness = waveBoxThickness;
        this.waveBoxGap = waveBoxGap;
    }

    public float getWaveBoxLength() {
        return waveBoxLength;
    }

    public void setWaveBoxLength(float waveBoxLength) {
        this.waveBoxLength = waveBoxLength;
    }

    public float getWaveBoxHeight() {
        return waveBoxHeight;
    }

    public void setWaveBoxHeight(float waveBoxHeight) {
        this.waveBoxHeight = waveBoxHeight;
    }

    public float getWaveBoxThickness() {
        return waveBoxThickness;
    }

    public void setWaveBoxThickness(float waveBoxThickness) {
        this.waveBoxThickness = waveBoxThickness;
    }

    public float getWaveBoxGap() {
        return waveBoxGap;
    }

    public void setWaveBoxGap(float waveBoxGap) {
        this.waveBoxGap = waveBoxGap;
    }
}
