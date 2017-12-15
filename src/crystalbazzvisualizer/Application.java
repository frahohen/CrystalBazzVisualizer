/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalbazzvisualizer;

import crystalbazzvisualizer.definition.Definition;
import crystalbazzvisualizer.service.WaveBoxService;
import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import crystalbazzvisualizer.definition.WaveBoxColorDefinition;
import crystalbazzvisualizer.definition.WaveBoxCountDefinition;
import crystalbazzvisualizer.definition.WaveBoxMassDefinition;
import crystalbazzvisualizer.list.FloatList;

/**
 * 
 * @author frahohen
 */
public class Application extends SimpleApplication {

    private static MinimProcessing minimProcess;
    private static Application app;
    private static WaveBoxCountDefinition waveBoxCountDefinition;
    private static WaveBoxMassDefinition waveBoxMassDefinition;
    private static WaveBoxMassDefinition waveBoxMassDefinitionRight;
    private static WaveBoxMassDefinition waveBoxMassDefinitionLeft;
    private static WaveBoxColorDefinition waveBoxColorDefinition;
    
    private WaveBoxService boxService;
    
    @Override
    public void destroy(){
        super.destroy();
        minimProcess.stop();
        minimProcess.destroy();
    }
    
    public static void main(String[] args) {
        // Init Scene
        app = new Application();
        app.start();
    }
    
    @Override
    public void simpleInitApp() {
        // Init Camera
        flyCam.setMoveSpeed(60);
        setDisplayFps(false);
        setDisplayStatView(false);
        
        // Init how many Boxes should be displayed
        waveBoxCountDefinition = new WaveBoxCountDefinition();
        waveBoxCountDefinition.setFrequencyChannel(400);
        waveBoxCountDefinition.setMixChannel(400);
        waveBoxCountDefinition.setLeftChannel(400);
        waveBoxCountDefinition.setRightChannel(400);
        // Init the Measure of the Boxes
        waveBoxMassDefinition = new WaveBoxMassDefinition(
                0.25f,   // Length
                2.0f,   // Height
                0.4f,   // Thickness
                0.1f    // Gap
        );
        waveBoxMassDefinitionRight = new WaveBoxMassDefinition(
                0.25f,   // Length
                2.5f,   // Height
                0.4f,   // Thickness
                0.1f    // Gap
        );
        waveBoxMassDefinitionLeft = new WaveBoxMassDefinition(
                0.25f,   // Length
                0.5f,   // Height
                0.4f,   // Thickness
                0.1f    // Gap
        );
        
        waveBoxColorDefinition = new WaveBoxColorDefinition(0, 50, 160);
        
        // Init Processing & Waveform & Frequency
        minimProcess = new MinimProcessing(waveBoxCountDefinition);
        minimProcess.init();//main("cbscene/MProcessing");
        
        // Init Values
        boxService = new WaveBoxService(Definition.BOXSERVICE);
        
        boxService.addBoxList(Definition.MIX_CHANNEL, waveBoxCountDefinition.getMixChannel(),waveBoxMassDefinition, waveBoxColorDefinition, app);
        boxService.addBoxList(Definition.RIGHT_CHANNEL, waveBoxCountDefinition.getRightChannel(),waveBoxMassDefinitionRight, waveBoxColorDefinition, app);
        boxService.addBoxList(Definition.LEFT_CHANNEL, waveBoxCountDefinition.getLeftChannel(),waveBoxMassDefinitionLeft, waveBoxColorDefinition, app);
        
        boxService.addBoxList(Definition.FREQUENCY_CHANNEL, waveBoxCountDefinition.getFrequencyChannel(), waveBoxMassDefinition, waveBoxColorDefinition, app);
        boxService.addBoxList(Definition.FREQUENCY_CHANNEL_REVERSE, waveBoxCountDefinition.getFrequencyChannel(), waveBoxMassDefinition, waveBoxColorDefinition, app);
        
        // Rotate Mix BoxList
        boxService.rotateBoxList(Definition.MIX_CHANNEL, (90+75)*FastMath.DEG_TO_RAD, 90*FastMath.DEG_TO_RAD, 0);
        boxService.moveBoxList(Definition.MIX_CHANNEL, -10f, -5f, 1.0f);
        
        // Rotate Right BoxList
        boxService.rotateBoxList(Definition.RIGHT_CHANNEL, (90+75)*FastMath.DEG_TO_RAD, 90*FastMath.DEG_TO_RAD, 0);
        boxService.moveBoxList(Definition.RIGHT_CHANNEL, -10f-0.8f, -5f-0.21f, 1.0f);
        
        // Rotate Left BoxList
        boxService.rotateBoxList(Definition.LEFT_CHANNEL, (90+75)*FastMath.DEG_TO_RAD, 90*FastMath.DEG_TO_RAD, 0);
        boxService.moveBoxList(Definition.LEFT_CHANNEL, -10f+0.8f, -5f+0.21f, 1.0f);
        
        // Rotate Frequency BoxList
        boxService.rotateBoxList(Definition.FREQUENCY_CHANNEL, (90-75)*FastMath.DEG_TO_RAD, 90*FastMath.DEG_TO_RAD, 0);
        boxService.moveBoxList(Definition.FREQUENCY_CHANNEL, -15f, 6f, -20f);
        
        // Rotate Reverse Frequency BoxList
        boxService.rotateBoxList(Definition.FREQUENCY_CHANNEL_REVERSE, (90+75)*FastMath.DEG_TO_RAD, -90*FastMath.DEG_TO_RAD, 0);
        boxService.moveBoxList(Definition.FREQUENCY_CHANNEL_REVERSE, -15f, 6f, -20f+0.65f);
        
        rootNode.attachChild(boxService);
        
        // Init Light 
        DirectionalLight sun = new DirectionalLight();
        sun.setColor(ColorRGBA.White);
        sun.setDirection(new Vector3f(-1.0f,-1.0f,-1.0f).normalizeLocal());
        rootNode.addLight(sun);
        
        viewPort.setBackgroundColor(ColorRGBA.White);
    }

    @Override
    public void simpleUpdate(float tpf) {

        FloatList mixFloatList = minimProcess.getChannelService().getChannel(Definition.MIX_CHANNEL);
        FloatList rightFloatList = minimProcess.getChannelService().getChannel(Definition.RIGHT_CHANNEL);
        FloatList leftFloatList = minimProcess.getChannelService().getChannel(Definition.LEFT_CHANNEL);
        FloatList frequencyFloatList = minimProcess.getChannelService().getChannel(Definition.FREQUENCY_CHANNEL);
        
        ((WaveBoxService)rootNode.getChild(Definition.BOXSERVICE))
                .update(
                    Definition.MIX_CHANNEL, 
                    mixFloatList
                );
        
        ((WaveBoxService)rootNode.getChild(Definition.BOXSERVICE))
                .update(
                    Definition.RIGHT_CHANNEL, 
                    rightFloatList
                );
        
        ((WaveBoxService)rootNode.getChild(Definition.BOXSERVICE))
                .update(
                    Definition.LEFT_CHANNEL, 
                    leftFloatList
                );

        ((WaveBoxService)rootNode.getChild(Definition.BOXSERVICE))
                .update(Definition.FREQUENCY_CHANNEL, 
                        frequencyFloatList
                );
        
        ((WaveBoxService)rootNode.getChild(Definition.BOXSERVICE))
                .update(Definition.FREQUENCY_CHANNEL_REVERSE, 
                        frequencyFloatList
                );
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
