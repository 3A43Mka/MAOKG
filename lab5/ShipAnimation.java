import java.awt.Container;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Material;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;
import javax.swing.*;
import javax.vecmath.Vector3d;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class ShipAnimation implements ActionListener, KeyListener {
    private Button go;
    private TransformGroup wholeShip;
    private Transform3D translateTransform;
    private Transform3D rotateTransformX;
    private Transform3D rotateTransformY;
    private Transform3D rotateTransformZ;

    private JFrame mainFrame;

    private float sign = 1.0f;
    private float zoom = 1.0f;
    private float xloc = 0.0f;
    private float yloc = -1.3f;
    private float zloc = 0.0f;
    private Timer timer;

    public ShipAnimation(TransformGroup wholeShip, Transform3D trans, JFrame frame) {
        go = new Button("Start");
        this.wholeShip = wholeShip;
        this.translateTransform = trans;
        this.mainFrame = frame;

        rotateTransformX = new Transform3D();
        rotateTransformY = new Transform3D();
        rotateTransformZ = new Transform3D();

        Ship.canvas.addKeyListener(this);
        timer = new Timer(100, this);

        Panel p = new Panel();
        p.add(go);
        mainFrame.add("North", p);
        go.addActionListener(this);
        go.addKeyListener(this);
    }

    private void initialBoteState() {
        xloc = 0.0f;
        yloc = 0.0f;
        zloc = 0.0f;
        zoom = 1.0f;
        sign = 1.0f;
        if (timer.isRunning()) {
            timer.stop();
        }
        go.setLabel("Go");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == go) {
            if (!timer.isRunning()) {
                timer.start();
                go.setLabel("Stop");
            } else {
                timer.stop();
                go.setLabel("Start");
            }
        } else {
            Move();
            translateTransform.setScale(new Vector3d(zoom, zoom, zoom));
            translateTransform.setTranslation(new Vector3f(xloc, yloc, zloc));
            wholeShip.setTransform(translateTransform);
        }
    }

    private void Move() {
        xloc += 0.05 * sign;
        if (Math.abs(xloc * 2) >= 2) {
            sign = -1.0f * sign;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Invoked when a key has been typed.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            rotateTransformX.rotX(Math.PI / 9);
            translateTransform.mul(rotateTransformX);
        }
        if (e.getKeyChar() == 'a') {
            rotateTransformY.rotY(Math.PI / 9);
            translateTransform.mul(rotateTransformY);
        }
        if (e.getKeyChar() == 'd') {
            rotateTransformZ.rotZ(Math.PI / 9);
            translateTransform.mul(rotateTransformZ);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Invoked when a key has been released.
    }
}