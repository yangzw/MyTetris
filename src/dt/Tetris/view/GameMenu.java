package dt.Tetris.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import dt.Tetris.listener.GameListener;
import dt.Tetris.listener.PanelChangeListener;

public class GameMenu extends JMenuBar{
	
	private JMenu menuForGame;
	private JMenu menuForAbout;
	private JMenuItem newGameItem;
	private JMenuItem exitItem;
	private JMenuItem aboutItem; 
	//private JMenuItem setSpeedItem;
	//private JMenuItem setMusicItem;
	//private JMenuItem setHandicapItem;
	private JMenuItem pauseItem;
	private JMenuItem stopItem;
	private JMenuItem continueItem;
	private PanelChangeListener listener;
	private GameListener gameListener;
	
	public GameMenu(){
		menuForGame = new JMenu("Game");
		menuForAbout = new JMenu("About");
		newGameItem = new JMenuItem("New Game");
		exitItem = new JMenuItem("Exit");
		//setItem = new JMenuItem("Set");
		aboutItem = new JMenuItem("About");
		pauseItem = new JMenuItem("Pause");
		stopItem = new JMenuItem("Stop");
		continueItem = new JMenuItem("Continue");
		
		this.add(menuForGame);
		this.add(menuForAbout);
		menuForGame.add(newGameItem);
		menuForGame.add(pauseItem);
		pauseItem.setEnabled(false);
		menuForGame.add(continueItem);
		continueItem.setEnabled(false);
		menuForGame.add(stopItem);
		stopItem.setEnabled(false);
		menuForGame.addSeparator();
		menuForGame.add(exitItem);
		menuForAbout.add(aboutItem);
		//setMusicItem.setEnabled(false);		
		//add listemer
		exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		aboutItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//JOptionPane.showMessageDialog(null, "coded by bearzx\n bearzx@gmail.com");
				JOptionPane.showMessageDialog(null, "coded by bearzx\n bearzx@gmail.com", "About", JOptionPane.INFORMATION_MESSAGE, null);
			}
		});
		
		newGameItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//gameListener.stop();
				listener.stopGame();
				listener.changePanel("optionPanel");
			}
		});
		
		pauseItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				pauseItem.setEnabled(false);
				stopItem.setEnabled(true);
				continueItem.setEnabled(true);
				listener.pauseGame();
			}
		});
		
		stopItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				pauseItem.setEnabled(false);
				stopItem.setEnabled(false);
				continueItem.setEnabled(false);
				listener.stopGame();
			}
		});
		
		continueItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				continueItem.setEnabled(false);
				pauseItem.setEnabled(true);
				stopItem.setEnabled(true);
				listener.continueGame();
			}
		});
		
		
	}
	
	public void addPanelChangeListener(PanelChangeListener l){
		this.listener = l;
	}
	
	public void addGameListener(GameListener l){
		this.gameListener = l;
	}
	
	public void setItemEnable(){
		this.pauseItem.setEnabled(true);
		this.stopItem.setEnabled(true);
		this.continueItem.setEnabled(true);
	}
	
}
