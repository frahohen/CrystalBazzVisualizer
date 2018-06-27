/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalbazzvisualizer.helper;

/**
 *
 * @author frahohen
 */
public class TimeCounter {
    private long fadeTimeStart;
    private float fadeTimeEnd;

    public TimeCounter(float fadeTimeEnd) {
        this.fadeTimeStart = System.currentTimeMillis();
        this.fadeTimeEnd = fadeTimeEnd;
    }

    public void setTimeStart(long fadeTimeStart) {
        this.fadeTimeStart = fadeTimeStart;
    }
    
    public float getTimeLeft() {
        return (System.currentTimeMillis() - fadeTimeStart)/1000;
    }
    
    public float getTimeEnd() {
        return fadeTimeEnd;
    }
    
}
