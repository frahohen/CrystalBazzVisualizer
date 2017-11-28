/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalbazzvisualizer;

import crystalbazzvisualizer.service.ChannelService;
import crystalbazzvisualizer.definition.Definition;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import java.util.ArrayList;
import processing.core.*;
/**
 *
 * @author -QUESTION-
 */

public class MinimProcessing extends PApplet {
    
    private Minim minim;
    private AudioPlayer player;
    private FFT fft;
    private ChannelService channelService;
    
    private ArrayList<Float> leftChannel, rightChannel, mixChannel;
    private ArrayList<Float> currentFreq;
    
    //private int rectSize = 1;
    private int waveRectCount = 800;

    private int freqMax = 20000;
    private int freqRectCount = 40;
    private int freqScale = freqMax/freqRectCount;
    
    public void setup() {
        channelService = new ChannelService();
        
        channelService.addChannel(Definition.MIX_CHANNEL, 800, Definition.WAVEFORM);
        channelService.addChannel(Definition.LEFT_CHANNEL, 800, Definition.WAVEFORM);
        channelService.addChannel(Definition.RIGHT_CHANNEL, 800, Definition.WAVEFORM);
        channelService.addChannel(Definition.FREQ_CHANNEL, 100, Definition.FREQUENCY);
        
        // Init ArrayList
        /*
        mixChannel = new ArrayList<Float>();
        leftChannel = new ArrayList<Float>();
        rightChannel = new ArrayList<Float>();
        currentFreq = new ArrayList<Float>();
        
       // Fill ArrayList with 0
        for(int i = 0; i < freqRectCount; i++)
        {
            currentFreq.add(0f);
        }
        
        // Fill Arraylist with 0
        for(int i = 0; i < waveRectCount; i++){
            mixChannel.add(i, 0f);
            leftChannel.add(i, 0f);
            rightChannel.add(i, 0f);
        }
        */
        
        // Init Minim
        minim = new Minim(this);
        player = minim.loadFile("crYstalBaZZ ft. Stehpanie Kay - Take Whats Mine (Preview) (002)Master.mp3");
        //player = minim.loadFile("cbscene/Calibration_Value_001.mp3");
        fft = new FFT(player.bufferSize(), player.sampleRate());
        player.loop();
    }
    
    public void draw() {
        // Waveform - Line
        /*
        for(int i = 0; i < player.bufferSize() - 1; i++)
        {
          float mix1 = player.mix.get(i) * 100;
          float mix2 = player.mix.get(i+1) * 100;
          
          float left1 = player.left.get(i) * 100;
          float left2 = player.left.get(i+1) * 100;
          
          float right1 = player.right.get(i) * 100;
          float right2 = player.right.get(i+1) * 100;
        }
        */
        
        channelService.addLevel(Definition.MIX_CHANNEL,player.mix.level());
        channelService.addLevel(Definition.LEFT_CHANNEL,player.left.level());
        channelService.addLevel(Definition.RIGHT_CHANNEL,player.right.level());
        
        // Waveform - Rect

         // Add Level to ArrayList
         /*
        mixChannel.add(0, player.mix.level());
        leftChannel.add(0, player.left.level());
        rightChannel.add(0, player.right.level());
        
        // Limit ArrayList at around waveRectCount
        if(mixChannel.size() > waveRectCount)
        {
            mixChannel.subList(waveRectCount, mixChannel.size()).clear();
        }
        if(leftChannel.size() > waveRectCount)
        {
            leftChannel.subList(waveRectCount, leftChannel.size()).clear();
        }
        if(rightChannel.size() > waveRectCount)
        {
            rightChannel.subList(waveRectCount, rightChannel.size()).clear();
        }
         */
        
        // Frequency - Rect
        fft.forward(player.mix);
       
        channelService.setLevel(Definition.FREQ_CHANNEL, fft);
        /*
        int n = 0;
        for(int i = 0; i < freqMax; i+=freqScale) 
        {
            currentFreq.set(n, fft.getFreq(i));
            n++;
        }   
        */
        /*
        System.out.println("Size: "+ currentFreq.size());
        for(int i = 0; i < currentFreq.size(); i++)
        {
            System.out.println(i+". Value: "+ currentFreq.get(i));
        }
        */
    }
    
    public void stop() {
        player.close();
        minim.stop();
        super.stop();
    }

    
    /*
    public int getWaveRectCount() {
        return waveRectCount;
    }
    public void setWaveRectCount(int waveRectCount) {
        this.waveRectCount = waveRectCount;
    }
    
    public ArrayList<Float> getCurrentFreq() {
        return currentFreq;
    }

    public void setCurrentFreq(ArrayList<Float> currentFreq) {
        this.currentFreq = currentFreq;
    }

    public ArrayList<Float> getLeftChannel() {
        return leftChannel;
    }

    public void setLeftChannel(ArrayList<Float> leftChannel) {
        this.leftChannel = leftChannel;
    }

    public ArrayList<Float> getMixChannel() {
        return mixChannel;
    }

    public void setMixChannel(ArrayList<Float> mixChannel) {
        this.mixChannel = mixChannel;
    }

    public ArrayList<Float> getRightChannel() {
        return rightChannel;
    }

    public void setRightChannel(ArrayList<Float> rightChannel) {
        this.rightChannel = rightChannel;
    }
    */

    public ChannelService getChannelService() {
        return channelService;
    }

    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
    }
}

