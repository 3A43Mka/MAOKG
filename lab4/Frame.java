import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.vecmath.Color3f;
import com.sun.j3d.utils.geometry.Box;
import java.awt.Color;

public class Frame {
    public static Box getFrame(float xdim, float ydim, float zdim) {
        return new Box(xdim, ydim, zdim, getFrameAppearence());
    }

    private static Appearance getFrameAppearence() {
        Appearance ap = new Appearance();
        Color3f emissive = new Color3f(Color.decode("#210F07"));
        Color3f ambient = new Color3f(Color.decode("#210F07"));
        Color3f diffuse = new Color3f(Color.decode("#210F07"));
        Color3f specular = new Color3f(Color.decode("#3A1B0D"));
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
        return ap;
    }
}