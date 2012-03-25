package dt.Tetris.entities;

import java.awt.Color;
import java.awt.Graphics;

import dt.Tetris.util.Global;

public class ShapeForPredict {
	
	private int[][] body;
	private int top, left;
	
	public ShapeForPredict(int top, int left){
		this.top = top;
		this.left = left;
	}
	
	public void drawMe(Graphics g){
		System.out.println("PredictShape' s drawMe");
		g.setColor(Color.blue);
		System.out.println(g.getColor().toString());
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (getFlagByPoint(x, y)) {
					System.out.println("i am drawing");
					g.setColor(Color.yellow);
					System.out.println(g.getColor().toString());
					g.fill3DRect((left + x) * Global.CELL_SIZE, (y + top)
							* Global.CELL_SIZE, Global.CELL_SIZE,
							Global.CELL_SIZE, true);
				}
			}
		}
	}
	
	
	
	public void setBody(int body[][]){
		this.body = body;
	}
	
	private boolean getFlagByPoint(int x, int y) {
		return body[0][y * 4 + x] == 1;
	}
}
