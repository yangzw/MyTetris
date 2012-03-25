package dt.Tetris.view;

import java.awt.CardLayout;
import java.io.IOException;
import java.net.URL;

import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.swing.JPanel;

import dt.Tetris.controller.Controller;
import dt.Tetris.entities.Ground;
import dt.Tetris.entities.ShapeFactory;
import dt.Tetris.listener.FrameReSizeInterface;
import dt.Tetris.listener.PanelChangeListener;

public class TotalPanel extends JPanel implements PanelChangeListener{
	private CardLayout cardLayout;
	private WelcomePanel welcomePanel;
	private LevelPanel levelPanel;
	private HandicapPanel handicapPanel;
	private MusicPanel musicPanel;
	private GamePanel gamePanel;
	private int panelStatus = 1;
	private ShapeFactory shapeFactory;
	private Ground ground;
	private Controller controller;
	private OptionPanel optionPanel;
	private FrameReSizeInterface reSizeListener;
	private GameMenu gameMenu;
	private GameCommandPanel gameCommandPanel;
	Player player;
	URL nextSoundURL;
	
	public TotalPanel(FrameReSizeInterface reSizeListener){
		/*
		this.welcomePanel = welcomePanel;
		this.levelPanel = levelPanel;
		this.handicapPanel = handicapPanel;
		this.musicPanel = musicPanel;
		this.gamePanel = gamePanel;
		*/
		nextSoundURL = TotalPanel.class.getResource("/selection.mp3");
		gameCommandPanel = new GameCommandPanel();
		gamePanel = gameCommandPanel.getGamePanel();
		this.reSizeListener = reSizeListener;
		System.out.println("gamePanel size" + gamePanel.getSize().width + "  " + gamePanel.getSize().height);
		welcomePanel = new WelcomePanel(this);
		//levelPanel = new LevelPanel(this);
		//handicapPanel = new HandicapPanel(this);
		//musicPanel = new MusicPanel(this);
		optionPanel = new OptionPanel(this);
		cardLayout = new CardLayout();
		shapeFactory = new ShapeFactory();
		ground = new Ground();
		controller = new Controller(shapeFactory, ground, gamePanel, optionPanel);
		controller.setGameCommandPanel(gameCommandPanel);
		gameCommandPanel.setGameListener(controller);
		//this.gameMenu.addGameListener(controller);
		this.setLayout(cardLayout);
		this.add(welcomePanel, "welcomePanel");
		//this.add(levelPanel, "2");
		//this.add(handicapPanel, "3");
		this.add(optionPanel, "optionPanel");
		//this.add(musicPanel, "3");
		this.add(gameCommandPanel, "gameCommandPanel");
		//this.setFocusable(false);	
		
	}
	
	//will not ues  this method
	@Override
	public void completeSelect() {
		// TODO Auto-generated method stub
		System.out.println("will go into next Panel");
		this.panelStatus++;
		if(this.panelStatus > 3){
			this.panelStatus = 3;
		}
		if(panelStatus != 3){
			reSizeListener.FrameResize(500, 500);
		}
		else{
			System.out.println("abcdefg");
			//System.out.println("gamePanel size" + gamePanel.getSize().width + "  " + gamePanel.getSize().height);
			gamePanel.setSize(400, 400);
			reSizeListener.FrameResize(gamePanel.getSize().width + 10, gamePanel.getSize().height + 35);	
		}
		cardLayout.show(this, this.panelStatus + "");
		if(this.panelStatus == 3){
			
			controller.setHandicapLevel(optionPanel.getHandicapLevel());
			controller.setSpeedLevel(optionPanel.getSpeedLevel());
			controller.newGame();
		}
	}
	
	public GamePanel getGamePanel(){
		return this.gamePanel;
	}
	
	public Controller getController(){
		return this.controller;
	}
	@Override
	public void changePanel(String destination) {
		// TODO Auto-generated method stub
		System.out.println("will go to next Panel");
		
		try {
			player = Manager.createPlayer(nextSoundURL);
			player.prefetch();
			player.start();
		} catch (NoPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(destination.equals("optionPanel")){
			//player.stop();
			welcomePanel.player.stop();
			reSizeListener.FrameResize(500, 500);
		}
		else if(destination.equals("gameCommandPanel")){
			//gamePanel.setSize(420, 400);
			reSizeListener.FrameResize(420, 600);
			
			//reSizeListener.FrameResize(gamePanel.getSize().width + 18, gamePanel.getSize().height + 64);
		}
		cardLayout.show(this, destination);
		if(destination.equals("gameCommandPanel")){
			
			controller.refresh();
			controller.setHandicapLevel(optionPanel.getHandicapLevel());
			controller.setSpeedLevel(optionPanel.getSpeedLevel());
			gameMenu.setItemEnable();
			controller.newGame();
		}
	}
	
	public void setGameMenu(GameMenu g){
		this.gameMenu = g;
		this.gameMenu.addGameListener(controller);
	}

	@Override
	public void pauseGame() {
		// TODO Auto-generated method stub
		if(controller.getIsStart()){
			controller.pause();
		}
	}

	@Override
	public void stopGame() {
		// TODO Auto-generated method stub
		if(controller.getIsStart()){
			controller.stop();
		}
	}

	@Override
	public void continueGame() {
		// TODO Auto-generated method stub
		if(!controller.getIsStart()){
			controller.continueGame();
		}
	}
}