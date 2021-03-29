

import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.vecmath.Color3f;
import com.sun.j3d.utils.geometry.Box;
import java.awt.Color;

public class Window {
    public static Box getWindow(float xdim, float ydim, float zdim) {
        return new Box(xdim, ydim, zdim, getWindowAppearence());
    }

    private static Appearance getWindowAppearence() {
        Appearance ap = new Appearance();
        Color3f emissive = new Color3f(Color.decode("#BDFDF8"));
        Color3f ambient = new Color3f(Color.blue);
        Color3f diffuse = new Color3f(Color.blue);
        Color3f specular = new Color3f(Color.blue);
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
        return ap;
    }
}