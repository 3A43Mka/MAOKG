import java.awt.Color;
import java.util.Hashtable;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.JFrame;
import javax.vecmath.*;

public class BlackWidow extends JFrame{

    private Canvas3D canvas;
    private Hashtable roachNamedObjects;

    private BlackWidow(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());

        SimpleUniverse simpUniv = new SimpleUniverse(canvas);

        simpUniv.getViewingPlatform().setNominalViewingTransform();

        createSceneGraph(simpUniv);

        addLight(simpUniv);

        OrbitBehavior ob = new OrbitBehavior(canvas);
        ob.setSchedulingBounds(new BoundingSphere(new Point3d(0.0,0.0,0.0),Double.MAX_VALUE));
        simpUniv.getViewingPlatform().setViewPlatformBehavior(ob);

        setTitle("BlackWidow");
        setSize(1080,800);
        getContentPane().add("Center", canvas);
        setVisible(true);
    }

    private void createSceneGraph(SimpleUniverse su){
        ObjectFile f = new ObjectFile(ObjectFile.RESIZE);
        Scene spiderScene = null;
        try {
            spiderScene = f.load("C:\\Users\\3a43M\\Downloads\\black_widow.obj");
        }
        catch (Exception e){
            System.out.println("File loading failed:" + e);
        }

        Transform3D scaling = new Transform3D();
        scaling.setScale(0.1);

        Transform3D trans = new Transform3D();
        trans.rotY(-2.5 * Math.PI / 2);
        
        Transform3D tfSpider = new Transform3D();
        tfSpider.rotX(-1 * Math.PI / 2);
        tfSpider.mul(trans);
        tfSpider.mul(scaling);
        tfSpider.setTranslation(new Vector3d(-0.4f,0.1f,0.0f));

        TransformGroup tgSpider = new TransformGroup(tfSpider);

        TransformGroup sceneGroup = new TransformGroup();

        roachNamedObjects = spiderScene.getNamedObjects();

        BranchGroup scene = new BranchGroup();

        TransformGroup tgBody = new TransformGroup();
        Shape3D blkwBody = (Shape3D) roachNamedObjects.get("blkw_body");
        Appearance app = new Appearance();
        Color3f black = new Color3f(Color.black);

        app.setMaterial(new Material(black, black, black, black, 70f));
        blkwBody.setAppearance(app);
        tgBody.addChild(blkwBody.cloneTree());


        int noRotTime = 1000;
        int rotTime = 300;

        BoundingSphere bs = new BoundingSphere(new Point3d(0.0,0.0,0.0),Double.MAX_VALUE);

        Transform3D legRotAxis = new Transform3D();
        legRotAxis.rotZ(Math.PI/2);
        Transform3D leg2RotAxis = new Transform3D();

        sceneGroup.addChild(getAnimatedLeg("leg1", noRotTime, rotTime, legRotAxis, bs, (float) Math.PI/8, 100));
        sceneGroup.addChild(getAnimatedLeg("leg2", noRotTime, rotTime, legRotAxis, bs, (float) Math.PI/8, 200));
        sceneGroup.addChild(getAnimatedLeg("leg3", noRotTime, rotTime, legRotAxis, bs, (float) Math.PI/8, 300));
        sceneGroup.addChild(getAnimatedLeg("leg4", noRotTime, rotTime, legRotAxis, bs, (float) Math.PI/8, 300));
        ;
        sceneGroup.addChild(getAnimatedLeg("leg5", noRotTime, rotTime, leg2RotAxis, bs, -(float) Math.PI/8, 100));
        sceneGroup.addChild(getAnimatedLeg("leg6", noRotTime, rotTime, leg2RotAxis, bs, -(float) Math.PI/8, 200));
        sceneGroup.addChild(getAnimatedLeg("leg7", noRotTime, rotTime, leg2RotAxis, bs, -(float) Math.PI/8, 300));
        sceneGroup.addChild(getAnimatedLeg("leg8", noRotTime, rotTime, leg2RotAxis, bs, -(float) Math.PI/8, 200));

        sceneGroup.addChild(tgBody.cloneTree());

        Transform3D tCrawl = new Transform3D();
        tCrawl.rotY(-Math.PI/2);

        long crawlTime = 20000;
        Alpha crawlAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0, crawlTime,0,0,0,0,0);

        float crawlDistance = 20.0f;
        PositionInterpolator posICrawl = new PositionInterpolator(crawlAlpha, sceneGroup,tCrawl, -3.0f, crawlDistance);

        posICrawl.setSchedulingBounds(bs);
        sceneGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        sceneGroup.addChild(posICrawl);

        tgSpider.addChild(sceneGroup);
        scene.addChild(tgSpider);

        addImageBackground("C:\\Users\\3a43M\\Downloads\\web.jpg", scene);
        scene.compile();

        su.addBranchGraph(scene);
    }

    private void addImageBackground(String imagePath, BranchGroup root) {
        TextureLoader t = new TextureLoader(imagePath, canvas);
        Background background = new Background(t.getImage());
        background.setImageScaleMode(Background.SCALE_FIT_ALL);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        background.setApplicationBounds(bounds);
        root.addChild(background);
    }

    private TransformGroup getAnimatedLeg(String elementName, int noRotTime, long rotTime, Transform3D legRotAxis, Bounds bs, float v, int l){
        Alpha legRotAlpha = new Alpha(noRotTime, Alpha.INCREASING_ENABLE, l,0, rotTime,
                0,0,0,0,0);

        Shape3D leg = (Shape3D) roachNamedObjects.get(elementName);
        TransformGroup tgLeg = new TransformGroup();
        tgLeg.addChild(leg.cloneTree());

        RotationInterpolator legRotation = new RotationInterpolator(legRotAlpha , tgLeg, legRotAxis, v,0.0f);
        legRotation.setSchedulingBounds(bs);
        tgLeg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tgLeg.addChild(legRotation);
        return tgLeg;
    }

    private void addLight(SimpleUniverse su){
        BranchGroup bgLight = new BranchGroup();
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
        Color3f lightColour = new Color3f(1.0f,1.0f,1.0f);
        Vector3f lightDir = new Vector3f(-1.0f,0.0f,-0.5f);
        DirectionalLight light = new DirectionalLight(lightColour, lightDir);
        light.setInfluencingBounds(bounds);
        bgLight.addChild(light);
        su.addBranchGraph(bgLight);
    }

    public static void main(String[] args){
        new BlackWidow();
    }
}