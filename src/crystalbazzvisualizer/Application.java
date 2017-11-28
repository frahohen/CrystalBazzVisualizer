package crystalbazzvisualizer;

import crystalbazzvisualizer.object.WaveBox;
import crystalbazzvisualizer.definition.Definition;
import crystalbazzvisualizer.service.WaveBoxService;
import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * test
 * @author normenhansen
 */
public class Application extends SimpleApplication {

    private ArrayList<WaveBox> waveformMix, waveformLeft,waveformRight, frequencyLeft, FrequencyRight;
    private int waveformSize;
    private static MinimProcessing minimProcess;
    private static Application app;
    
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

    /*
    public float cC(int value)
    {
        float fvalue;
        fvalue = (2.0f/255.0f)*(float)value; 
        return fvalue;
    }
    */
    
    @Override
    public void simpleInitApp() {
        // Init Camera
        /*
            cam.setLocation(new Vector3f(-3.4978552f ,2.5523603f ,4.9107704f));
            cam.setRotation(new Quaternion(0.04502283f ,0.8943176f ,-0.09246529f, 0.43545276f));
            flyCam.setEnabled(false);
        */
        //Direction:0.65877444 Y:-0.22367756 Z:-0.7183205
        
        //Init Processing & Waveform & Frequency
        minimProcess = new MinimProcessing();
        minimProcess.init();//main("cbscene/MProcessing");
        
        // Init Values
        boxService = new WaveBoxService(Definition.BOXSERVICE);
        // WERTE MÜSSEN ÜBEREINSTIMMEN !!!!!!!!!!!!!!!!!!!!!!!!!
        //boxService.addBoxList(CBDefinition.MIX_CHANNEL, 800, app);
        boxService.addBoxList(Definition.FREQ_CHANNEL, 100, app);
        rootNode.attachChild(boxService);
        /*
        waveformMix = new ArrayList<CBBox>();
        waveformLeft = new ArrayList<CBBox>();
        waveformRight = new ArrayList<CBBox>();
        frequencyLeft = new ArrayList<CBBox>();
        FrequencyRight = new ArrayList<CBBox>();
        waveformSize = 800;
        */
        
        // Init Light 
        DirectionalLight sun = new DirectionalLight();
        sun.setColor(ColorRGBA.White);
        sun.setDirection(new Vector3f(-.5f,-.5f,-.5f).normalizeLocal());
        rootNode.addLight(sun);
        
        viewPort.setBackgroundColor(ColorRGBA.White);
        
        /*
        // Init Waveform 
        for(int i = 0; i < waveformSize; i+=2)
        {
            CBBox wrect = new CBBox(this);
            wrect.setXYZ(0.01f, 1f, 0.05f);
            wrect.setName("Box_"+i);
            wrect.setColor(new ColorRGBA(0, cC(50), cC(160), 1.0f));
            wrect.simpleInit();
            wrect.getGeom().setLocalTranslation(-(float)i*0.01f+((waveformSize*0.0205f)/2), 0f, 0f);
            waveformMix.add(wrect);
        }
        for(int i = 0; i < waveformSize; i+=2)
        {
            CBBox wrect = new CBBox(this);
            wrect.setXYZ(0.01f, 1f, 0.05f);
            wrect.setName("Box_"+(i+waveformSize));
            wrect.setColor(new ColorRGBA(0, cC(65),cC(255),1.0f));
            wrect.simpleInit();
            wrect.getGeom().setLocalTranslation(-(float)i*0.01f+((waveformSize*0.0205f)/2), 0f, -0.1f);
            waveformLeft.add(wrect);
        }
        for(int i = 0; i < waveformSize; i+=2)
        {
            CBBox wrect = new CBBox(this);
            wrect.setXYZ(0.01f, 1f, 0.05f);
            wrect.setName("Box_"+(i+waveformSize*2));
            wrect.setColor(new ColorRGBA(0, cC(50), cC(160), 1.0f));
            wrect.simpleInit();
            wrect.getGeom().setLocalTranslation(-(float)i*0.01f+((waveformSize*0.0205f)/2), 0f, -0.2f);
            waveformRight.add(wrect);
        }
        
        for(int i = 0; i < 40; i++)
        {
            CBBox wrect = new CBBox(this);
            wrect.setXYZ(0.05f, 0.8f, 0.15f);
            wrect.setName("Box_"+(i+waveformSize*3));
            wrect.setColor(new ColorRGBA(0, cC(50), cC(160), 1.0f));
            wrect.simpleInit();
            wrect.getGeom().setLocalTranslation(-(float)i*0.15f, 3f, -0.1f);
            frequencyLeft.add(wrect);
        }
        for(int i = 0; i < 40; i++)
        {
            CBBox wrect = new CBBox(this);
            wrect.setXYZ(0.05f, 0.8f, 0.15f);
            wrect.setName("Box_"+(i+waveformSize*4));
            wrect.setColor(new ColorRGBA(0, cC(50), cC(160), 1.0f));
            wrect.simpleInit();
            wrect.getGeom().setLocalTranslation((float)i*0.15f+0.15f, 3f, -0.1f);
            FrequencyRight.add(wrect);
        }
        */
        
        
    }

    @Override
    public void simpleUpdate(float tpf) {
        
        /*
        ((CBBoxService)rootNode.getChild(CBDefinition.BOXSERVICE)).update(
                CBDefinition.MIX_CHANNEL, 
                mprocess.getChannelService().getChannel(CBDefinition.MIX_CHANNEL)
        );
        */
        
        
        
        ((WaveBoxService)rootNode.getChild(Definition.BOXSERVICE)).update(Definition.FREQ_CHANNEL, 
                minimProcess.getChannelService().getChannel(Definition.FREQ_CHANNEL)
        );
        
        
        /*
        for(int i = 0; i < waveformMix.size(); i++)
        {
            waveformMix.get(i).getGeom().setLocalScale(1, mprocess.getMixChannel().get(i), 1);
            waveformLeft.get(i).getGeom().setLocalScale(1, mprocess.getLeftChannel().get(i), 1);
            waveformRight.get(i).getGeom().setLocalScale(1, mprocess.getRightChannel().get(i), 1);
            
            if(waveformMix.get(i).getGeom().getLocalScale().y <= 0.01f)
            {
                waveformMix.get(i).getGeom().setLocalScale(1, 0.01f, 1);
            }
            
            if(waveformLeft.get(i).getGeom().getLocalScale().y <= 0.01f)
            {
                waveformLeft.get(i).getGeom().setLocalScale(1, 0.01f, 1);
            }
            
            if(waveformRight.get(i).getGeom().getLocalScale().y <= 0.01f)
            {
                waveformRight.get(i).getGeom().setLocalScale(1, 0.01f, 1);
            }
        }
        
        for(int i = 0; i < frequencyLeft.size(); i++)
        {
            frequencyLeft.get(i).getGeom().setLocalScale(1, mprocess.getCurrentFreq().get(i), 1);
            
            if(frequencyLeft.get(i).getGeom().getLocalScale().y <= 0.01f)
            {
                frequencyLeft.get(i).getGeom().setLocalScale(1, 0.01f, 1);
            }
        }
        for(int i = 0; i < FrequencyRight.size(); i++)
        {
            FrequencyRight.get(i).getGeom().setLocalScale(1, mprocess.getCurrentFreq().get(i), 1);
            
            if(FrequencyRight.get(i).getGeom().getLocalScale().y <= 0.01f)
            {
                FrequencyRight.get(i).getGeom().setLocalScale(1, 0.01f, 1);
            }
        }
        */
        
        /*
        System.out.println("Rotation: X:"+cam.getRotation().getX()+" Y:"+cam.getRotation().getY()+" Z:"+cam.getRotation().getZ()+" W:"+
                cam.getRotation().getW()+
                "|Location: X:"+cam.getLocation().x+" Y:"+cam.getLocation().y+" Z:"+cam.getLocation().z+
                "|Direction:"+cam.getDirection().x+" Y:"+cam.getDirection().y+" Z:"+cam.getDirection().z);
        */
        
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
