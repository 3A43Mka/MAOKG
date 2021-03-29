import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;

import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;

import java.awt.Color;
import java.awt.Container;

public class Chimney {
    public static Cylinder getChimney(float var1, float var2) {
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
        return new Cylinder(var1, var2, primflags, getChimneyAppearence());
    }

    private static Appearance getChimneyAppearence() {
    	
		TextureLoader loader = new TextureLoader(
				"C:\\Users\\3a43M\\Downloads\\Lab_4_maokg\\Example\\RedCube\\resources\\brick.jpeg", "LUMINANCE",
				new Container());
		Texture texture = loader.getTexture();
		texture.setBoundaryModeS(Texture.WRAP);
		texture.setBoundaryModeT(Texture.WRAP);
		texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 1.0f, 0.0f));
		TextureAttributes texAttr = new TextureAttributes();
		texAttr.setTextureMode(TextureAttributes.MODULATE);
    	
        Appearance ap = new Appearance();
        Color3f emissive = new Color3f(Color.decode("#210F07"));
        Color3f ambient = new Color3f(Color.decode("#210F07"));
        Color3f diffuse = new Color3f(Color.decode("#210F07"));
        Color3f specular = new Color3f(Color.decode("#210F07"));
		ap.setTexture(texture);
		ap.setTextureAttributes(texAttr);
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
        return ap;
    }
}