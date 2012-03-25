package dt.Tetris.view;

import javax.swing.JPanel;

import dt.Tetris.listener.GameListener;

public class GameCommandPanel extends JPanel{
	private GamePanel gamePanel;
	private ControlPanel controlPanel;
	private int level, handicap;
	//GameListener gameListener;
	
	public GameCommandPanel(){
		gamePanel = new GamePanel();
		controlPanel = new ControlPanel();
		this.setLayout(null);
		this.add(gamePanel);
		this.add(controlPanel);
		//�趨Ϊ420 * 600
		gamePanel.setBounds(0, 0, 420, 400);
		//gamePanel.setLocation(0, 0);
		controlPanel.setBounds(0, 400, 420, 135);
		//controlPanel.setLocation(0, 400);
		//gamePanel.setSize(420, 400);
		//controlPanel.setSize(420, 135);
	}
	
	public GamePanel getGamePanel(){
		return this.gamePanel;
	}
	
	public ControlPanel getControlPanel(){
		return this.controlPanel;
	}
	
	public void setLevelAndHnadicap(int level, int handicap){
		this.level = level;
		this.handicap = handicap;
		controlPanel.level = level;
		controlPanel.handicap = handicap;
	}
	
	public void setGameListener(GameListener gameListener){
		this.controlPanel.gameListener = gameListener;
	}
	
	public void setNewScore(String newScore) {
		this.controlPanel.setScore(newScore);
	}
	
	public void setButtonEnabled(boolean status) {
		this.controlPanel.setButtonEnabled(status);
	}
}
