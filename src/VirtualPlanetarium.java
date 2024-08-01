import java.awt.*;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.behaviors.keyboard.*;

import com.sun.j3d.loaders.Scene;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.io.*;
import com.sun.j3d.loaders.objectfile.ObjectFile;

import java.awt.event.*;

public class VirtualPlanetarium extends Frame implements KeyListener {

	private SimpleUniverse universe = null;
	private Canvas3D canvas = null;
	private TransformGroup viewtrans = null;

	private TransformGroup tg_x = null;
	private Transform3D t3d_x = null;

	private TransformGroup tg_y = null;
	private Transform3D t3d_y = null;

	private Transform3D t3dstep = new Transform3D();
	private Matrix4d matrix = new Matrix4d();

	public VirtualPlanetarium() {
		setLayout(new BorderLayout());
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();

		canvas = new Canvas3D(config);
		add("Center", canvas);
		universe = new SimpleUniverse(canvas);

		BranchGroup scene = createSceneGraph();
		universe.getViewingPlatform().setNominalViewingTransform();

		universe.getViewer().getView().setBackClipDistance(10000.0);

		canvas.addKeyListener(this);

		universe.addBranchGraph(scene);
	}

	private BranchGroup createSceneGraph() {
		BranchGroup objRoot = new BranchGroup();

		BoundingSphere bounds = new BoundingSphere(new Point3d(), 10000.0);

		viewtrans = universe.getViewingPlatform().getViewPlatformTransform();

		KeyNavigatorBehavior virPlan = new KeyNavigatorBehavior(viewtrans);
		virPlan.setSchedulingBounds(bounds);

		BranchGroup bg = new BranchGroup();
		bg.addChild(virPlan);
		universe.addBranchGraph(bg);

		objRoot.addChild(createPlanetarium("/Users/maomao/virtualPlanetarium_2/Geep/model/CelestialSphere_nasa.obj"));

		return objRoot;
	}

	private BranchGroup createPlanetarium(String filename) {

		BranchGroup objRoot = new BranchGroup();

		tg_x = new TransformGroup();
		t3d_x = new Transform3D();
		tg_x.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		t3d_x.setTranslation(new Vector3d(0.0, 0.0, 0.0));
		t3d_x.setScale(250.0);
		tg_x.setTransform(t3d_x);

		tg_y = new TransformGroup();
		t3d_y = new Transform3D();
		tg_y.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		tg_y.addChild(createObjLoad(filename));

		tg_x.addChild(tg_y);
		objRoot.addChild(tg_x);

		objRoot.compile();

		return objRoot;

	}

	private BranchGroup createObjLoad(String filename) {

		BranchGroup objRoot = new BranchGroup();

		TransformGroup tg = new TransformGroup();
		Transform3D t3d = new Transform3D();
		t3d.setScale(1.0);
		tg.setTransform(t3d);

		ObjectFile loader = new ObjectFile();
		Scene s = null;

		File file = new java.io.File(filename);

		try {
			s = loader.load(file.toURI().toURL());
		} catch (Exception e) {
			System.err.println(e);
			System.exit(1);
		}

		tg.addChild(s.getSceneGroup());

		objRoot.addChild(tg);

		objRoot.compile();

		return objRoot;

	}

	public static void main(String args[]) {

		VirtualPlanetarium window = new VirtualPlanetarium();

		window.setSize(800, 600);

		window.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		window.setVisible(true);
	}

	public void keyTyped(KeyEvent e) {
		char key = e.getKeyChar();

		if (key == 'a') {

			t3dstep.rotY(-Math.PI / 32);
			tg_y.getTransform(t3d_y);
			t3d_y.get(matrix);
			t3d_y.setTranslation(new Vector3d(0.0, 0.0, 0.0));
			t3d_y.mul(t3dstep);
			t3d_y.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
			tg_y.setTransform(t3d_y);

		}

		if (key == 'd') {

			t3dstep.rotY(Math.PI / 32);
			tg_y.getTransform(t3d_y);
			t3d_y.get(matrix);
			t3d_y.setTranslation(new Vector3d(0.0, 0.0, 0.0));
			t3d_y.mul(t3dstep);
			t3d_y.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
			tg_y.setTransform(t3d_y);

		}

		if (key == 'w') {

			t3dstep.rotX(-Math.PI / 128);
			tg_x.getTransform(t3d_x);
			t3d_x.get(matrix);
			t3d_x.setTranslation(new Vector3d(0.0, 0.0, 0.0));
			t3d_x.mul(t3dstep);
			t3d_x.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
			tg_x.setTransform(t3d_x);

		}

		if (key == 's') {

			t3dstep.rotX(Math.PI / 128);
			tg_x.getTransform(t3d_x);
			t3d_x.get(matrix);
			t3d_x.setTranslation(new Vector3d(0.0, 0.0, 0.0));
			t3d_x.mul(t3dstep);
			t3d_x.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
			tg_x.setTransform(t3d_x);

		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

}