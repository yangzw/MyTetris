package dt.Tetris.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import dt.Tetris.listener.ShapeListener;
import dt.Tetris.util.Global;

public class Shape {
	public static final int ROTATE = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;

	private int[][] body;
	private int status;
	private int left = 10;
	private int top = 0;
	private Color shapeColor;
	private int speedLevel;
	private Thread thread;
	private boolean isPause;
	private boolean isStop;

	private ShapeListener listener;

	public void moveLeft() {
		System.out.println("Shape' s moveLeft");
		left--;
	}

	public void moveRight() {
		System.out.println("Shape' s moveRight");
		left++;
	}

	public void moveDown() {
		System.out.println("Shape' s moveDown");
		top++;
	}

	public void rotate() {
		System.out.println("Shape' s rotate");
		status = (status + 1) % body.length;
	}

	public void drawMe(Graphics g) {
		System.out.println("Shape' s drawMe");
		g.setColor(shapeColor);
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (getFlagByPoint(x, y)) {
					g.fill3DRect((left + x) * Global.CELL_SIZE, (y + top)
							* Global.CELL_SIZE, Global.CELL_SIZE,
							Global.CELL_SIZE, true);
				}
			}
		}
	}

	private Color getRandomColor() {
		Random random = new Random();
		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);
		Color aColor = new Color(r, g, b);
		return aColor;
	}

	private boolean getFlagByPoint(int x, int y) {
		return body[status][y * 4 + x] == 1;
	}

	public boolean isMember(int x, int y, boolean rotate) {
		int tempStatus = status;
		if (rotate) {
			tempStatus = (status + 1) % body.length;
		}
		return body[tempStatus][y * 4 + x] == 1;
	}

	private class ShapeDriver implements Runnable {

		@Override
		public void run() {
			while (listener.isShapeMoveDownable(Shape.this) && !isStop) {
				if (!isPause) {
					moveDown();
					listener.shapeMoveDown(Shape.this);
					try {
						Thread.sleep(1000 - speedLevel * 10 + 1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public Shape(int speedLevel, ShapeListener l) {
		this.speedLevel = speedLevel;
		this.listener = l;
		isPause = false;
		isStop = false;
		shapeColor = getRandomColor();
		thread = new Thread(new ShapeDriver());
		thread.start();
	}

	public void addListener(ShapeListener l) {
		if (l != null) {
			this.listener = l;
		}
	}

	public void setBody(int body[][]) {
		this.body = body;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTop() {
		return top;
	}

	public int getLeft() {
		return left;
	}

	public Thread getThread() {
		return this.thread;
	}

	public void setPasue() {
		this.isPause = true;
	}
	
	public void setContinue(){
		this.isPause = false;
	}
	
	public void setStop(){
		this.isStop = true;
	}
	
	public void setSpeedLevel(int speedLevel){
		this.speedLevel = speedLevel;
	}
}
