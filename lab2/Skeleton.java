package lab2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;

public class Skeleton extends JPanel implements ActionListener {
	private static int width = 500;
	private static int height = 800;

	private Graphics2D g2d;
	private Timer timer;
	private double[][] myPolygonPoints = { { 0, 85 }, { 75, 75 }, { 100, 10 }, { 125, 75 }, { 200, 85 }, { 150, 125 },
			{ 160, 190 }, { 100, 150 }, { 40, 190 }, { 50, 125 }, { 0, 85 } };
	private GeneralPath myPolygon;
	private int frameCenterX = 240;
	private int frameCenterY = 560;
	private double angle = 0;
	private double rotateX = 0;
	private double rotateY = 0;

	public Skeleton() {
		myPolygon = polygonFromPointArray(myPolygonPoints);
		timer = new Timer(10, this);
		timer.start();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Lab2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(new Skeleton());
		frame.setVisible(true);

		Dimension size = frame.getSize();
		Insets insets = frame.getInsets();
		width = size.width - insets.left - insets.right - 10;
		height = size.height - insets.top - insets.bottom - 1;
	}

	public void paint(Graphics g) {
		g2d = (Graphics2D) g;
		setRenderHints();
		g2d.setBackground(new Color(218, 112, 214));
		g2d.clearRect(0, 0, width, height);
		drawOriginalPicture();
		drawFrame();
		drawAnimated();
	}

	private void drawFrame() {
		BasicStroke stroke = new BasicStroke(28, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
		g2d.setStroke(stroke);
		g2d.setColor(Color.BLACK);
		g2d.drawRect(50, 390, 370, 340);
	}

	private void drawAnimated() {
		g2d.translate(frameCenterX - 100, frameCenterY - 100);
		g2d.setColor(Color.CYAN);
		g2d.rotate(angle, 100, 110);
		g2d.fill(myPolygon);
		g2d.rotate(-angle, 100, 110);
		g2d.translate(rotateX, rotateY);
		g2d.setColor(Color.GREEN);
		g2d.fill(myPolygon);
	}

	public void actionPerformed(ActionEvent e) {
		angle += 0.04;
		double x = -130;
		double cos = Math.cos(-angle);
		double sin = Math.sin(-angle);
		rotateX = x * cos;
		rotateY = x * sin;
		repaint();
	}

	private void setRenderHints() {
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);
	}

	private GeneralPath polygonFromPointArray(double[][] points) {
		GeneralPath path = new GeneralPath();
		path.moveTo(points[0][0], points[0][1]);
		for (int i = 1; i < points.length; i++)
			path.lineTo(points[i][0], points[i][1]);
		path.closePath();
		return path;
	}

	private void drawOriginalPicture() {
		g2d.setColor(new Color(0, 128, 0));
		g2d.fillRect(240, 20, 170, 90);
		g2d.setColor(new Color(255, 255, 0));
		g2d.fillOval(310, 20, 100, 340);

		double[][] rectPoints = { { 70, 270 }, { 410, 270 }, { 410, 360 }, { 70, 360 }, };
		GeneralPath redRect = polygonFromPointArray(rectPoints);
		g2d.setColor(new Color(255, 0, 0));
		g2d.fill(redRect);

		g2d.setColor(new Color(255, 0, 0));
		GradientPaint gp = new GradientPaint(5, 25, Color.RED, 20, 2, Color.BLUE, true);
		g2d.setPaint(gp);
		g2d.fillOval(70, 20, 100, 340);

		g2d.setColor(new Color(0, 128, 0));
		g2d.fillRect(70, 20, 170, 90);
	}
}