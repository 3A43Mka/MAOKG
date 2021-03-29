import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.vecmath.Color3f;
import com.sun.j3d.utils.geometry.Sphere;
import java.awt.Color;

public class Handle {
    public static Sphere getHandle(float v) {
        return new Sphere(v, getHandleAppearence());
    }

    private static Appearance getHandleAppearence() {
        Appearance ap = new Appearance();
        Color3f emissive = new Color3f(Color.decode("#1A2828"));
        Color3f ambient = new Color3f(Color.decode("#3A1B0D"));
        Color3f diffuse = new Color3f(Color.decode("#3A1B0D"));
        Color3f specular = new Color3f(Color.decode("#3A1B0D"));
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
        return ap;
    }
}