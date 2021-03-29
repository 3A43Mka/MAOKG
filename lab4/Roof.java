
import java.awt.*;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;

import javax.media.j3d.*;
import javax.vecmath.*;

import java.awt.Container;
import javax.vecmath.Color3f;
import java.awt.Color;
import com.sun.j3d.utils.geometry.*;
import javax.vecmath.*; // for Vector3f

public class Roof {
	public static Cone getRoof() {
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		return new Cone(0.55f, 0.4f, primflags, getRoofAppearence());
	}

	private static Appearance getRoofAppearence() {

		TextureLoader loader = new TextureLoader(
				"C:\\Users\\3a43M\\Downloads\\Lab_4_maokg\\Example\\RedCube\\resources\\woodTexture.jpg", "LUMINANCE",
				new Container());
		Texture texture = loader.getTexture();
		texture.setBoundaryModeS(Texture.WRAP);
		texture.setBoundaryModeT(Texture.WRAP);
		texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 1.0f, 0.0f));
		TextureAttributes texAttr = new TextureAttributes();
		texAttr.setTextureMode(TextureAttributes.MODULATE);

		Appearance appearance = new Appearance();
		Color3f color = new Color3f(Color.decode("#FF7300"));

		Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
		Color3f white = new Color3f(.0f, 1.0f, .0f);
		Material mat = new Material(color, black, color, white, 70f);
		appearance.setTexture(texture);
		appearance.setTextureAttributes(texAttr);
		appearance.setMaterial(mat);
		return appearance;
	}
}