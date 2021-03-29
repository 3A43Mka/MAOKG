import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;

import java.awt.Color;
import java.awt.Container;

public class Door {
    public static Box getDoor(float xdim, float ydim, float zdim) {
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
        return new Box(xdim, ydim, zdim, primflags, getBoxAppearence());
    }

    private static Appearance getBoxAppearence() {
    	
    	TextureLoader loader = new TextureLoader(
				"C:\\Users\\3a43M\\Downloads\\Lab_4_maokg\\Example\\RedCube\\resources\\woodTexture.jpg", "LUMINANCE",
				new Container());
		Texture texture = loader.getTexture();
		texture.setBoundaryModeS(Texture.WRAP);
		texture.setBoundaryModeT(Texture.WRAP);
		texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 1.0f, 0.0f));
		TextureAttributes texAttr = new TextureAttributes();
		texAttr.setTextureMode(TextureAttributes.MODULATE);
    	
        Appearance ap = new Appearance();
        Color3f emissive = new Color3f(Color.decode("#C28F60"));
        Color3f ambient = new Color3f(Color.decode("#3A1B0D"));
        Color3f diffuse = new Color3f(Color.decode("#3A1B0D"));
        Color3f specular = new Color3f(Color.decode("#3A1B0D"));
		ap.setTexture(texture);
		ap.setTextureAttributes(texAttr);
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
        return ap;
    }
}