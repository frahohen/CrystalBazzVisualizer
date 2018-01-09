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
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
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
    private static WaveBoxMassDefinition waveBoxMassDefinitionMix;
    private static WaveBoxMassDefinition waveBoxMassDefinitionRight;
    private static WaveBoxMassDefinition waveBoxMassDefinitionLeft;
    private static WaveBoxMassDefinition waveBoxMassDefinitionFrequencyBlack;
    private static WaveBoxMassDefinition waveBoxMassDefinitionFrequencyBlue;
    private static WaveBoxMassDefinition waveBoxMassDefinitionFrequencyWhite;
    private static WaveBoxColorDefinition waveBoxColorDefinitionBlue;
    private static WaveBoxColorDefinition waveBoxColorDefinitionBlack;
    private static WaveBoxColorDefinition waveBoxColorDefinitionWhite;
    
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
        flyCam.setEnabled(false);
        
        cam.setRotation(new Quaternion(-0.0028423883f ,0.9880241f ,-0.018335091f, -0.15317991f));
        cam.setLocation(new Vector3f(8.383016f,0.71727115f,5.742541f));
        
        setDisplayFps(false);
        setDisplayStatView(false);
        
        // Init how many Boxes should be displayed
        waveBoxCountDefinition = new WaveBoxCountDefinition();
        waveBoxCountDefinition.setFrequencyChannel(800);
        waveBoxCountDefinition.setMixChannel(800);
        waveBoxCountDefinition.setLeftChannel(800);
        waveBoxCountDefinition.setRightChannel(800);
        // Init the Measure of the Boxes
        waveBoxMassDefinitionMix = new WaveBoxMassDefinition(
                0.25f,   // Length
                3.0f,   // Height
                0.2f,   // Thickness
                0.5f    // Gap
        );
        waveBoxMassDefinitionRight = new WaveBoxMassDefinition(
                0.25f,   // Length
                3.5f,   // Height
                0.2f,   // Thickness
                0.5f    // Gap
        );
        waveBoxMassDefinitionLeft = new WaveBoxMassDefinition(
                0.25f,   // Length
                1.0f,   // Height
                0.2f,   // Thickness
                0.5f    // Gap
        );
        
        waveBoxMassDefinitionFrequencyBlack = new WaveBoxMassDefinition(
                0.25f,   // Length
                3.0f,   // Height
                0.2f,   // Thickness
                0.1f    // Gap
        );
        waveBoxMassDefinitionFrequencyBlue = new WaveBoxMassDefinition(
                0.25f,   // Length
                2.0f,   // Height
                0.2f,   // Thickness
                0.1f    // Gap
        );
        waveBoxMassDefinitionFrequencyWhite = new WaveBoxMassDefinition(
                0.25f,   // Length
                1.0f,   // Height
                0.2f,   // Thickness
                0.1f    // Gap
        );
        
        waveBoxColorDefinitionBlue = new WaveBoxColorDefinition(93, 118, 147);
        waveBoxColorDefinitionBlack = new WaveBoxColorDefinition(33, 33, 33);
        waveBoxColorDefinitionWhite = new WaveBoxColorDefinition(255, 255, 255);
        
        // Init Processing & Waveform & Frequency
        minimProcess = new MinimProcessing(waveBoxCountDefinition);
        minimProcess.init();//main("cbscene/MProcessing");
        
        // Init Values
        boxService = new WaveBoxService(Definition.BOXSERVICE);
        
        boxService.addBoxList(Definition.MIX_CHANNEL, waveBoxCountDefinition.getMixChannel(),waveBoxMassDefinitionMix, waveBoxColorDefinitionBlue, app);
        boxService.addBoxList(Definition.RIGHT_CHANNEL, waveBoxCountDefinition.getRightChannel(),waveBoxMassDefinitionRight, waveBoxColorDefinitionBlack, app);
        boxService.addBoxList(Definition.LEFT_CHANNEL, waveBoxCountDefinition.getLeftChannel(),waveBoxMassDefinitionLeft, waveBoxColorDefinitionWhite, app);
        
        boxService.addBoxList(Definition.FREQUENCY_CHANNEL+"_BLUE", waveBoxCountDefinition.getFrequencyChannel(), waveBoxMassDefinitionFrequencyBlue, waveBoxColorDefinitionBlue, app);
        boxService.addBoxList(Definition.FREQUENCY_CHANNEL_REVERSE+"_BLUE", waveBoxCountDefinition.getFrequencyChannel(), waveBoxMassDefinitionFrequencyBlue, waveBoxColorDefinitionBlue, app);
        
        boxService.addBoxList(Definition.FREQUENCY_CHANNEL+"_BLACK", waveBoxCountDefinition.getFrequencyChannel(), waveBoxMassDefinitionFrequencyBlack, waveBoxColorDefinitionBlack, app);
        boxService.addBoxList(Definition.FREQUENCY_CHANNEL_REVERSE+"_BLACK", waveBoxCountDefinition.getFrequencyChannel(), waveBoxMassDefinitionFrequencyBlack, waveBoxColorDefinitionBlack, app);
        
        boxService.addBoxList(Definition.FREQUENCY_CHANNEL+"_WHITE", waveBoxCountDefinition.getFrequencyChannel(), waveBoxMassDefinitionFrequencyWhite, waveBoxColorDefinitionWhite, app);
        boxService.addBoxList(Definition.FREQUENCY_CHANNEL_REVERSE+"_WHITE", waveBoxCountDefinition.getFrequencyChannel(), waveBoxMassDefinitionFrequencyWhite, waveBoxColorDefinitionWhite, app);
        
        // Rotate Mix BoxList
        boxService.rotateBoxList(Definition.MIX_CHANNEL, (90+75)*FastMath.DEG_TO_RAD, 90*FastMath.DEG_TO_RAD, 0);
        boxService.moveBoxList(Definition.MIX_CHANNEL, -10f, -5f, -7.0f);
        
        // Rotate Right BoxList
        boxService.rotateBoxList(Definition.RIGHT_CHANNEL, (90+75)*FastMath.DEG_TO_RAD, 90*FastMath.DEG_TO_RAD, 0);
        boxService.moveBoxList(Definition.RIGHT_CHANNEL, -10f-0.4f, -5f-0.21f, -7.0f);
        
        // Rotate Left BoxList
        boxService.rotateBoxList(Definition.LEFT_CHANNEL, (90+75)*FastMath.DEG_TO_RAD, 90*FastMath.DEG_TO_RAD, 0);
        boxService.moveBoxList(Definition.LEFT_CHANNEL, -10f+0.4f, -5f+0.21f, -7.0f);
        
        // Rotate Frequency BoxList
        boxService.rotateBoxList(Definition.FREQUENCY_CHANNEL+"_BLUE", (90-75)*FastMath.DEG_TO_RAD, 90*FastMath.DEG_TO_RAD, 0);
        boxService.moveBoxList(Definition.FREQUENCY_CHANNEL+"_BLUE", -15f, 6f, -20f);
        // Rotate Reverse Frequency BoxList
        boxService.rotateBoxList(Definition.FREQUENCY_CHANNEL_REVERSE+"_BLUE", (90+75)*FastMath.DEG_TO_RAD, -90*FastMath.DEG_TO_RAD, 0);
        boxService.moveBoxList(Definition.FREQUENCY_CHANNEL_REVERSE+"_BLUE", -15f, 6f, -20f+0.65f);
        
        // Rotate Frequency BoxList
        boxService.rotateBoxList(Definition.FREQUENCY_CHANNEL+"_BLACK", (90-75)*FastMath.DEG_TO_RAD, 90*FastMath.DEG_TO_RAD, 0);
        boxService.moveBoxList(Definition.FREQUENCY_CHANNEL+"_BLACK", -15f-0.4f, 6f+0.21f, -20f);
        // Rotate Reverse Frequency BoxList
        boxService.rotateBoxList(Definition.FREQUENCY_CHANNEL_REVERSE+"_BLACK", (90+75)*FastMath.DEG_TO_RAD, -90*FastMath.DEG_TO_RAD, 0);
        boxService.moveBoxList(Definition.FREQUENCY_CHANNEL_REVERSE+"_BLACK", -15f-0.4f, 6f+0.21f, -20f+0.65f);
        
         // Rotate Frequency BoxList
        boxService.rotateBoxList(Definition.FREQUENCY_CHANNEL+"_WHITE", (90-75)*FastMath.DEG_TO_RAD, 90*FastMath.DEG_TO_RAD, 0);
        boxService.moveBoxList(Definition.FREQUENCY_CHANNEL+"_WHITE", -15f+0.4f, 6f-0.21f, -20f);
        // Rotate Reverse Frequency BoxList
        boxService.rotateBoxList(Definition.FREQUENCY_CHANNEL_REVERSE+"_WHITE", (90+75)*FastMath.DEG_TO_RAD, -90*FastMath.DEG_TO_RAD, 0);
        boxService.moveBoxList(Definition.FREQUENCY_CHANNEL_REVERSE+"_WHITE", -15f+0.4f, 6f-0.21f, -20f+0.65f);
        
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
                .update(Definition.FREQUENCY_CHANNEL+"_BLUE", 
                        frequencyFloatList
                );
        ((WaveBoxService)rootNode.getChild(Definition.BOXSERVICE))
                .update(Definition.FREQUENCY_CHANNEL_REVERSE+"_BLUE", 
                        frequencyFloatList
                );
        
        ((WaveBoxService)rootNode.getChild(Definition.BOXSERVICE))
                .update(Definition.FREQUENCY_CHANNEL+"_BLACK", 
                        frequencyFloatList
                );
        ((WaveBoxService)rootNode.getChild(Definition.BOXSERVICE))
                .update(Definition.FREQUENCY_CHANNEL_REVERSE+"_BLACK", 
                        frequencyFloatList
                );
        
        ((WaveBoxService)rootNode.getChild(Definition.BOXSERVICE))
                .update(Definition.FREQUENCY_CHANNEL+"_WHITE", 
                        frequencyFloatList
                );
        ((WaveBoxService)rootNode.getChild(Definition.BOXSERVICE))
                .update(Definition.FREQUENCY_CHANNEL_REVERSE+"_WHITE", 
                        frequencyFloatList
                );
        
        //System.out.println("Rotation: X: " + cam.getRotation().getX() + " Y:" + cam.getRotation().getY() + " Z:" + cam.getRotation().getZ() + " W:" + cam.getRotation().getW());
        //System.out.println("Direction: X: " + cam.getLocation().x + " Y:" + cam.getLocation().y + " Z:" + cam.getLocation().z);
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
