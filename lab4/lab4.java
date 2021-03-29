
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.Timer;
import javax.vecmath.*;

public class lab4 implements ActionListener {
	private float angle = 0;
	private TransformGroup houseTransformGroup;
	private Transform3D trans1 = new Transform3D();
	private Transform3D trans2 = new Transform3D();
	private Timer timer;

	public static void main(String[] args) {
		new lab4();
	}

	public lab4() {
		timer = new Timer(50, this);
		timer.start();
		BranchGroup scene = createSceneGraph();
		SimpleUniverse u = new SimpleUniverse();
		u.getViewingPlatform().setNominalViewingTransform();
		u.addBranchGraph(scene);
	}

	public BranchGroup createSceneGraph() {
		BranchGroup myGroup = new BranchGroup();
		houseTransformGroup = new TransformGroup();
		houseTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		makeHouse();
		myGroup.addChild(houseTransformGroup);

		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
		Color3f light1Color = new Color3f(1.0f, 1.0f, 1.0f);
		Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
		DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
		light1.setInfluencingBounds(bounds);
		myGroup.addChild(light1);

		Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
		AmbientLight ambientLightNode = new AmbientLight(ambientColor);
		ambientLightNode.setInfluencingBounds(bounds);
		myGroup.addChild(ambientLightNode);
		return myGroup;
	}

	private void makeHouse() {

		// Main box
		TransformGroup tgBox = new TransformGroup();
		Transform3D transformBox = new Transform3D();
		Box mainBox = MainBox.getBox(0.4f, 0.4f, 0.4f);
		Vector3f vectorBox = new Vector3f(.0f, -0.20f, .0f);
		transformBox.setTranslation(vectorBox);
		tgBox.setTransform(transformBox);
		tgBox.addChild(mainBox);
		houseTransformGroup.addChild(tgBox);

		// Roof
		TransformGroup tgRoof = new TransformGroup();
		Transform3D transformRoof = new Transform3D();
		Cone mainRoof = Roof.getRoof();
		Vector3f vectorRoof = new Vector3f(.0f, 0.40f, .0f);
		transformRoof.setTranslation(vectorRoof);
		tgRoof.setTransform(transformRoof);
		tgRoof.addChild(mainRoof);
		houseTransformGroup.addChild(tgRoof);

		// Chimney
		TransformGroup tgChimney = new TransformGroup();
		Transform3D transformChimney = new Transform3D();
		Cylinder mainChimney = Chimney.getChimney(0.05f, 0.4f);
		Vector3f vectorChimney = new Vector3f(-0.1f, 0.4f, -0.3f);
		transformChimney.setTranslation(vectorChimney);
		tgChimney.setTransform(transformChimney);
		tgChimney.addChild(mainChimney);
		houseTransformGroup.addChild(tgChimney);

		// Door
		TransformGroup tgDoor = new TransformGroup();
		Transform3D transformDoor = new Transform3D();
		Box mainDoor = Door.getDoor(0.2f, 0.29f, 0.2f);
		Vector3f vectorDoor = new Vector3f(.0f, -0.30f, .22f);
		transformDoor.setTranslation(vectorDoor);
		tgDoor.setTransform(transformDoor);
		tgDoor.addChild(mainDoor);
		houseTransformGroup.addChild(tgDoor);

		// Handle
		TransformGroup tgHandle = new TransformGroup();
		Transform3D transformHandle = new Transform3D();
		Sphere mainHandle = Handle.getHandle(0.03f);
		Vector3f vectorHandle = new Vector3f(.1f, -0.30f, .42f);
		transformHandle.setTranslation(vectorHandle);
		tgHandle.setTransform(transformHandle);
		tgHandle.addChild(mainHandle);
		houseTransformGroup.addChild(tgHandle);

		// Window
		TransformGroup tgWindow = new TransformGroup();
		Transform3D transformWindow = new Transform3D();
		Box mainWindow = Window.getWindow(0.2f, 0.2f, 0.2f);
		Vector3f vectorWindow = new Vector3f(.0f, -0.25f, -0.21f);
		transformWindow.setTranslation(vectorWindow);
		tgWindow.setTransform(transformWindow);
		tgWindow.addChild(mainWindow);
		houseTransformGroup.addChild(tgWindow);

		// Frame
		createFrame(0.21f, 0.02f, 0.02f, .0f, -0.25f, -0.40f);
		createFrame(0.21f, 0.02f, 0.02f, .0f, -0.45f, -0.40f);
		createFrame(0.21f, 0.02f, 0.02f, .0f, -0.05f, -0.40f);
		createFrame(0.02f, 0.21f, 0.02f, .0f, -0.25f, -0.40f);
		createFrame(0.02f, 0.21f, 0.02f, .19f, -0.25f, -0.40f);
		createFrame(0.02f, 0.21f, 0.02f, -0.19f, -0.25f, -0.40f);

	}

	private void createFrame(float xdim, float ydim, float zdim, float xVector, float yVector, float zVector) {
		TransformGroup newFrame = new TransformGroup();
		Transform3D transformFrame = new Transform3D();
		Box frame = Frame.getFrame(xdim, ydim, zdim);
		Vector3f vectorFrame = new Vector3f(xVector, yVector, zVector);
		transformFrame.setTranslation(vectorFrame);
		newFrame.setTransform(transformFrame);
		newFrame.addChild(frame);
		houseTransformGroup.addChild(newFrame);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		trans1.rotY(angle);
		trans2.rotX(0.3f);
		trans1.mul(trans2);
		houseTransformGroup.setTransform(trans1);
		angle += 0.05;
	}
}