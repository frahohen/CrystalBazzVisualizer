/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalbazzvisualizer.object;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 *
 * @author frahohen
 */
public class WaveBox extends Geometry {

    private SimpleApplication simpleApplication;
    private Box box;
    private Material mat;
    
    public WaveBox(Vector3f vector, ColorRGBA diffuseColor, SimpleApplication simpleApplication) {
        this.simpleApplication = simpleApplication;
        init(vector, diffuseColor);
    }
    
    private void init(Vector3f vector, ColorRGBA diffuseColor) {
        box = new Box(vector.x, vector.y, vector.z);
        this.setMesh(box);
        
        mat = new Material(simpleApplication.getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
        mat.setColor("Ambient", ColorRGBA.White);
        mat.setColor("Diffuse", diffuseColor);
        mat.setColor("Specular", ColorRGBA.White);
        mat.setBoolean("UseMaterialColors", true);
        mat.setFloat("Shininess", 1);
        
        this.setMaterial(mat);
    }
}
