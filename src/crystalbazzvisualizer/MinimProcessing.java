/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalbazzvisualizer;

import crystalbazzvisualizer.service.ChannelService;
import crystalbazzvisualizer.definition.Definition;
import crystalbazzvisualizer.definition.WaveBoxCountDefinition;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import java.util.logging.Level;
import java.util.logging.Logger;
import processing.core.*;
/**
 *
 * @author frahohen
 */

public class MinimProcessing extends PApplet {
    
    private Minim minim;
    private AudioPlayer player;
    private FFT fft;
    private ChannelService channelService;
    private WaveBoxCountDefinition waveBoxCountDefinition;

    public MinimProcessing(WaveBoxCountDefinition boxCountDefinition) {
        this.waveBoxCountDefinition = boxCountDefinition;
    }
    
    public void setup() {
        channelService = new ChannelService();
        
        channelService.addChannel(Definition.MIX_CHANNEL, waveBoxCountDefinition.getMixChannel(), Definition.WAVEFORM);
        channelService.addChannel(Definition.LEFT_CHANNEL, waveBoxCountDefinition.getLeftChannel(), Definition.WAVEFORM);
        channelService.addChannel(Definition.RIGHT_CHANNEL, waveBoxCountDefinition.getRightChannel(), Definition.WAVEFORM);
        channelService.addChannel(Definition.FREQUENCY_CHANNEL, waveBoxCountDefinition.getFrequencyChannel(), Definition.FREQUENCY);
        
        // Init Minim
        minim = new Minim(this);
        player = minim.loadFile("Take_Whats_Mine_080.mp3");
        //player = minim.loadFile("Calibration_Value_001.mp3");
        fft = new FFT(player.bufferSize(), player.sampleRate());
        player.loop();
        
        /* This delay is needed otherwise the application will collapse 
           Hint: A one second delay is needed in the track before the track itself starts
        */
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MinimProcessing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void draw() {
        // Waveform Channel Level
        channelService.addLevel(Definition.MIX_CHANNEL,player.mix.level());
        channelService.addLevel(Definition.LEFT_CHANNEL,player.left.level());
        channelService.addLevel(Definition.RIGHT_CHANNEL,player.right.level());
        
        // Frequency Channel Level
        fft.forward(player.mix);
        channelService.setLevel(Definition.FREQUENCY_CHANNEL, fft);
    }
    
    public void stop() {
        player.close();
        minim.stop();
        super.stop();
    }

    public ChannelService getChannelService() {
        return channelService;
    }

    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
    }
}

