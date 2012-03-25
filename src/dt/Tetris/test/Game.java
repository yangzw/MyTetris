package dt.Tetris.test;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.UIManager;

import dt.Tetris.controller.Controller;
import dt.Tetris.entities.Ground;
import dt.Tetris.entities.ShapeFactory;
import dt.Tetris.listener.FrameReSizeInterface;
import dt.Tetris.listener.PanelChangeListener;
import dt.Tetris.view.GameCommandPanel;
import dt.Tetris.view.GameMenu;
import dt.Tetris.view.GamePanel;
import dt.Tetris.view.HandicapPanel;
import dt.Tetris.view.LevelPanel;
import dt.Tetris.view.MusicPanel;
import dt.Tetris.view.TotalPanel;
import dt.Tetris.view.WelcomePanel;

public class Game {
	public static void main(String args[]){
		System.setProperty("Quaqua.tabLayoutPolicy", "wrap");
		// set the Quaqua Look and Feel in the UIManager
		try {
			UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager
					.getLookAndFeel());
			// set UI manager properties here that affect Quaqua

		} catch (Exception e) {
			// take an appropriate action here

		}
		GameFrame myGameFrame = new GameFrame();
	}
}

class GameFrame extends JFrame implements FrameReSizeInterface{
	private CardLayout cardLayout;
	private TotalPanel totalPanel;
	private ShapeFactory shapeFactory;
	private Ground ground;
	private GamePanel gamePanel;
	private GameCommandPanel gameCommandPanel;
	private Controller controller;
	private WelcomePanel welcomePanel;
	private LevelPanel levelPanel;
	private HandicapPanel handicapPanel;
	private MusicPanel musicPanel;
	private PanelChangeListener totalListener;
	private GameMenu gameMenu;
	GameFrame(){
		super("MyGame");
		
		cardLayout = new CardLayout();
		totalPanel = new TotalPanel(this);
		GameMenu gameMenu = new GameMenu();
		gameMenu.addPanelChangeListener(totalPanel);
		totalPanel.setGameMenu(gameMenu);
		//gameMenu.addGameListener(controller);
		//totalListener = new TotalPanel();
		//totalPanel.setLayout(cardLayout);
		this.add(totalPanel);
		this.setJMenuBar(gameMenu);
		//gamePanel = totalPanel.getGamePanel();
		
		//gamePanel = new GamePanel();
		
		
		//JButton btn1 = new JButton("btn1");
		//JButton btn2 = new JButton("btn2");
		//JPanel tempPanel = new JPanel();
		//tempPanel.setSize(Global.CELL_SIZE * Global.WIDTH, 30);
		
		//JFrame frame = new JFrame();
		//frame.setLayout(null);
		//frame.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setSize(Global.CELL_SIZE * Global.WIDTH, Global.CELL_SIZE * Global.HEIGHT);
		//this.setSize(gamePanel.getSize().width + 10, gamePanel.getSize().height + 100);
		this.setSize(578, 665);
		this.setResizable(true);
		//frame.add(gamePanel); 
		//frame.add(tempPanel);
		
		//Box b = Box.createHorizontalBox();
		//tempPanel.setBackground(Color.black);
		//tempPanel.setSize(gamePanel.getWidth(), frame.getHeight() - gamePanel.getHeight());
		//tempPanel.setLocation(0, gamePanel.getHeight());
		//tempPanel.add(b);
		//b.add(btn1);
		//b.add(Box.createRigidArea(new Dimension(120, 20)));
		//b.add(btn2);
		//frame.add(btn1, BorderLayout.NORTH);
		//frame.add(btn2, BorderLayout.SOUTH);
		/*
		JButton button = new JButton("sb");
		JButton button1 = new JButton("sb");
		frame.add(button, BorderLayout.NORTH);
		frame.add(button1, BorderLayout.WEST);
		*/
		//btn1.setFocusable(false);
		//btn2.setFocusable(false);
		//gamePanel.addKeyListener(controller);
		//gamePanel.requestFocus();
		//tempPanel.addKeyListener(controller);
		//b.addKeyListener(controller);
		controller = totalPanel.getController();
		this.addKeyListener(controller);
		//this.setFocusable(false);
		this.setUndecorated(true);
		this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		this.setVisible(true);
	}
	
	@Override
	public void FrameResize(int width, int height) {
		// TODO Auto-generated method stub
		System.out.println("will set to " + width + "  " + height);
		this.setSize(width, height);
	}
}
