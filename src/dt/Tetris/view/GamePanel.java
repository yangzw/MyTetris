package dt.Tetris.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import dt.Tetris.entities.Ground;
import dt.Tetris.entities.Shape;
import dt.Tetris.util.Global;

public class GamePanel extends JPanel{
	
	private Ground ground;
	private Shape shape;
	
	public void display(Ground ground, Shape shape){
		System.out.println("GamePanel' s display");
		this.ground = ground;
		this.shape = shape;
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		//g.setColor(new Color(0xcfcfcf));
		g.setColor(Color.black);
		//g.fillRect(0, 0, Global.CELL_SIZE * Global.WIDTH, Global.CELL_SIZE * Global.HEIGHT);
		System.out.println("width = " + this.getWidth() + "height = " + this.getHeight());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		/*
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		if(shape != null && ground != null){
			shape.drawMe(g);
			ground.drawMe(g);
		}
	}
	
	public GamePanel(){
		//this.setSize(Global.CELL_SIZE * Global.WIDTH, Global.CELL_SIZE * Global.HEIGHT);
		this.setBackground(Color.black);
	}
}
