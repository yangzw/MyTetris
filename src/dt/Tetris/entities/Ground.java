package dt.Tetris.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.Stack;

import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;

import dt.Tetris.listener.GameListener;
import dt.Tetris.util.Global;

public class Ground {
	
	private int[][] obstacles = new int[Global.WIDTH][Global.HEIGHT];
	private int score = 0;
	private Player player;
	private URL soundURL;
	private GameListener listener;
	private Stack<int[][]> statusStack;
	
	public Ground() {
		statusStack = new Stack<int[][]>();
	}
	
	public void accept(Shape shape){
		System.out.println("Ground' s accept");
		soundURL = Ground.class.getResource("/accept.mp3");
		
		try {
			player = Manager.createPlayer(soundURL);
			player.prefetch();
			player.start();
		} catch (NoPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int x = 0; x < 4; x++){
			for(int y = 0; y < 4; y++){
				if(shape.isMember(x, y, false)){
					obstacles[shape.getLeft() + x][shape.getTop() + y] = 1;
				}
			}
		}
		deleteFullLine();
		saveStatus();
	}
	
	private void saveStatus() {
		int [][] tempStatus = new int[Global.WIDTH][Global.HEIGHT];
		for(int x = 0; x < Global.WIDTH; x++) {
			for(int y = 0; y < Global.HEIGHT; y++) {
				tempStatus[x][y] = obstacles[x][y];
			}
		}
		statusStack.push(tempStatus);
	}
	
	public void statusRollBack() {
		if(!statusStack.empty()) {
			obstacles = statusStack.pop();
		}
	}
	
	public void drawMe(Graphics g){
		g.setColor(new Color(0x660066));
		for(int x = 0; x < Global.WIDTH; x++){
			for(int y = 0; y < Global.HEIGHT; y++){
				if(obstacles[x][y] == 1){
					g.fill3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
				}
			}
		}
	}
	
	private void deleteFullLine(){
		for(int y = Global.HEIGHT - 1; y > 0; y--){
			boolean full = true;
			for(int x = 0; x < Global.WIDTH; x++){
				if(obstacles[x][y] == 0){
					full = false;
				}
			}
			if(full){
				deleteLine(y);
				score++;
				listener.setNewScore(score + "");
			}
		}
	}
	
	private void deleteLine(int lineNum){
		for(int y = lineNum; y > 0; y--){
			for(int x = 0; x < Global.WIDTH; x++){
				obstacles[x][y] = obstacles[x][y - 1];
			}
		}
	}
	
	/*
	for(int x = 0; x < Global.WIDTH; x++){
		obstacles[x][0] = 0;
	}
	*/
	
	public boolean isMoveable(Shape shape, int action){
		int left = shape.getLeft();
		int top = shape.getTop();
		switch(action){
		case Shape.LEFT:
			left--;
			break;
		case Shape.RIGHT:
			left++;
			break;
		case Shape.DOWN:
			top++;
			break;
		}
		for(int x = 0; x < 4; x++){
			for(int y = 0; y < 4; y++){
				if(shape.isMember(x, y, action == Shape.ROTATE)){
					if(top + y >= Global.HEIGHT || 
						left + x < 0 || 
						left + x >= Global.WIDTH || 
						obstacles[left + x][top + y] == 1){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean isFull(){
		for(int x = 0; x < Global.WIDTH; x++){
			if(obstacles[x][0] == 1){
				System.out.println("is Full SB------------------");
				return true;
			}
		}
		System.out.println("Not Full");
		return false;
	}
	
	public void generateHandicap(int level){
		for(int i = Global.HEIGHT - 1; i >= Global.HEIGHT - 1 - level; i--){
			Random random = new Random();
			int numOfHandicap = random.nextInt(Global.WIDTH) + 1;
			if(numOfHandicap == Global.WIDTH){
				numOfHandicap--;
			}
			//int tempArray[] = new int[numOfHandicap];
			for(int j = 0; j < numOfHandicap; j++){
				int tempInt = random.nextInt(Global.WIDTH);
				//tempArray[i][tempInt] = 1;
				this.obstacles[tempInt][i] = 1;
			}
		}
	}
	
	public void refresh(){
		for(int i = 0; i < Global.WIDTH; i++){
			for(int j = 0; j < Global.HEIGHT; j++){
				this.obstacles[i][j] = 0;
			}
		}
		score = 0;
		this.statusStack = new Stack<int[][]>();
	}
	
	public void refreshStack() {
		this.statusStack = new Stack<int[][]>();
	}
	
	public void setSocre(int  newScore){
		score = newScore;
	}
	
	public int getSocre(){
		return score;
	}
	
	public void setListener(GameListener listener) {
		this.listener = listener;
	}
}
