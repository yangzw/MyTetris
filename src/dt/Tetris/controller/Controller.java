package dt.Tetris.controller;

import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dt.Tetris.entities.Ground;
import dt.Tetris.entities.MyDialog;
import dt.Tetris.entities.ScoreListItem;
import dt.Tetris.entities.Shape;
import dt.Tetris.entities.ShapeFactory;
import dt.Tetris.listener.GameListener;
import dt.Tetris.listener.ShapeListener;
import dt.Tetris.view.GameCommandPanel;
import dt.Tetris.view.GamePanel;
import dt.Tetris.view.OptionPanel;

public class Controller extends KeyAdapter implements ShapeListener,
		GameListener {

	private Shape shape;
	private ShapeFactory shapeFactory;
	private Ground ground;
	private GamePanel gamePanel;
	private OptionPanel optionPanel;
	private int speedLevel;
	private int handicapLevel = 0;
	private boolean isStart = false;
	private int nextType;
	private GameCommandPanel gameCommandPanel;
	private Player player;
	private URL failMusicURL;

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			if (ground.isMoveable(shape, Shape.ROTATE)) {
				shape.rotate();
			}
			break;
		case KeyEvent.VK_DOWN:
			/*
			 * if(ground.isMoveable(shape, Shape.DOWN)){ shape.moveDown(); }
			 */
			if (isShapeMoveDownable(shape)) {
				shape.moveDown();
			}
			break;
		case KeyEvent.VK_LEFT:
			if (ground.isMoveable(shape, Shape.LEFT)) {
				shape.moveLeft();
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (ground.isMoveable(shape, Shape.RIGHT)) {
				shape.moveRight();
			}
			break;
		}
		gamePanel.display(ground, shape);
	}

	@Override
	public void shapeMoveDown(Shape shape) {
		// TODO Auto-generated method stub
		gamePanel.display(ground, shape);
	}

	public void newGame() {
		if (this.isStart && shape != null) {
			shape.setStop();
		}
		ground.refreshStack();
		int length = shapeFactory.getShapesLength();
		this.nextType = new Random().nextInt(length);
		shape = shapeFactory.getShape(this, speedLevel, nextType);
		this.nextType = new Random().nextInt(length);
		this.gameCommandPanel.getControlPanel().setNextType(nextType);
		isStart = true;
	}

	public Controller(ShapeFactory shapeFactory, Ground ground,
			GamePanel gamePanel, OptionPanel optionPanel) {
		super();
		this.shapeFactory = shapeFactory;
		this.ground = ground;
		ground.setListener(this);
		this.gamePanel = gamePanel;
		this.optionPanel = optionPanel;
		// this.speedLevel = optionPanel.getSpeedLevel();
		// System.out.println("speedLevel" + this.speedLevel);
	}

	@Override
	public synchronized boolean isShapeMoveDownable(Shape shape) {
		// TODO Auto-generated method stub
		// boolean result = ground.isMoveable(shape, Shape.DOWN);
		// 用于检测shape是否为null
		// 注意由于新启动的线程可能先于主线程运行,这是一个bug
		if (this.shape == null) {
			System.out.println("this.shape is null");
		}
		if (shape == null) {
			System.out.println("shape is null");
		}

		if (this.shape == null) {
			return true;
		}

		if (this.shape != shape) {
			System.out.println("isShapeMoveDownable return false");
			return false;
		}
		if (ground.isMoveable(shape, Shape.DOWN)) {
			System.out.println("isShapeMoveDownable return true");
			return true;
		}
		ground.accept(shape);
		if (!ground.isFull()) {
			this.shape = shapeFactory.getShape(this, speedLevel, nextType);
			System.out.println("nextType--------------------> " + nextType);
			this.nextType = new Random()
					.nextInt(shapeFactory.getShapesLength());
			// System.out.println("");
			this.gameCommandPanel.getControlPanel().setNextType(nextType);
			System.out.println("nextType--------------------> " + nextType);
			return true;
		}
		failMusicURL = Controller.class.getResource("/fail.mp3");

		try {
			player = Manager.createPlayer(failMusicURL);
			player.prefetch();
			player.start();
		} catch (NoPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String name = JOptionPane
				.showInputDialog("GameOver, Please type in your name:");
		displayPlayerList(name, ground.getSocre());
		System.out.println("isShapeMoveDownable return true1 && Our Space is Full");
		// return true;
		return false;
	}

	public void setSpeedLevel(int speedLevel) {
		this.speedLevel = speedLevel;
		if (this.shape != null) {
			this.shape.setSpeedLevel(speedLevel);
		}
	}

	public void setHandicapLevel(int handicapLevel) {
		this.handicapLevel = handicapLevel;
		this.ground.generateHandicap(handicapLevel);
	}

	@Override
	public void pause() {
		this.isStart = false;
		shape.setPasue();
		this.gameCommandPanel.setButtonEnabled(true);
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		this.isStart = false;
		shape.setStop();
	}

	public boolean getIsStart() {
		return this.isStart;
	}

	public void refresh() {
		this.ground.refresh();
		//this.shape = null;
	}

	@Override
	public void continueGame() {
		// TODO Auto-generated method stub
		this.isStart = true;
		shape.setContinue();
	}

	@Override
	public void speedUp() {
		// TODO Auto-generated method stub
		if (shape != null) {
			this.speedLevel += 10;
			if (this.speedLevel > 100) {
				this.speedLevel = 90;
			}
			this.shape.setSpeedLevel(speedLevel);
		}
	}

	@Override
	public void speedDown() {
		// TODO Auto-generated method stub
		if (shape != null) {
			this.speedLevel -= 10;
			if (this.speedLevel < 1) {
				this.speedLevel = 1;
			}
			this.shape.setSpeedLevel(speedLevel);
		}
	}

	@Override
	public int getSocre() {
		// TODO Auto-generated method stub
		return ground.getSocre();
	}

	public void setGameCommandPanel(GameCommandPanel g) {
		this.gameCommandPanel = g;
	}

	private void displayPlayerList(String name, int score) {
		String noString = "No.";
		String nameString = "Player";
		String scoreString = "Score";
		String timeString = "Time";
		JPanel aPanel = new JPanel();
		JLabel numLabel = new JLabel(noString);
		JLabel nameLabel = new JLabel(nameString);
		JLabel scoreLabel = new JLabel(scoreString);
		JLabel timeLabel = new JLabel(timeString);
		aPanel.setLayout(new GridLayout(6, 4));
		// aPanel.setSize(500, 500);
		aPanel.add(numLabel);
		aPanel.add(nameLabel);
		aPanel.add(scoreLabel);
		aPanel.add(timeLabel);
		for (int i = 0; i < 22; i++) {
			aPanel.add(new JLabel("this is " + i));
		}
		// aPanel.add(numLabel);
		// aPanel.add(nameLabel);
		// aPanel.add(scoreLabel);
		// aPanel.add(timeLabel);
		Date date = new Date();
		String time = date.toString();
		System.out.println(time);
		/**
		 * 从文件读取前五名，与当前的玩家成绩进行比较，输出全新的前五名 将结果输出回文件
		 */
		File testFile = new File("save.file");
		List<ScoreListItem> scoreList = new ArrayList<ScoreListItem>();
		scoreList.add(new ScoreListItem(score, name, time));
		if (testFile.exists()) {
			try {
				System.out.println(testFile.getAbsolutePath());
				DataInputStream in = new DataInputStream(new FileInputStream(
						"save.file"));
				while (in.available() != 0) {
					System.out.println("in.avaialable: " + in.available());
					int readScore = in.readInt();
					String readName = in.readUTF();
					String readTime = in.readUTF();
					scoreList.add(new ScoreListItem(readScore, readName,
							readTime));
				}
				sortScoreList(scoreList);
				// after sort
				outputToSave(scoreList);
				displayList(scoreList);
				MyDialog myDialog = new MyDialog(scoreList);
				myDialog.showMyDialog();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			outputToSave(scoreList);
			MyDialog myDialog = new MyDialog(scoreList);
			myDialog.showMyDialog();
			displayList(scoreList);
		}
		//JOptionPane.showMessageDialog(null, aPanel, "HiScore",
		//		JOptionPane.PLAIN_MESSAGE);
	}

	private void displayList(List<ScoreListItem> scoreList) {
		Iterator<ScoreListItem> iterator = scoreList.iterator();
		while (iterator.hasNext()) {
			ScoreListItem tempItem = iterator.next();
			System.out.println("name: " + tempItem.getName() + " score: "
					+ tempItem.getScore() + " time: " + tempItem.getTime());
		}
	}

	private void outputToSave(List<ScoreListItem> scoreList) {
		Iterator<ScoreListItem> iterator = scoreList.iterator();
		DataOutputStream out;
		try {
			out = new DataOutputStream(new FileOutputStream("save.file"));
			while (iterator.hasNext()) {
				// score name time
				ScoreListItem temp = iterator.next();
				out.writeInt(temp.getScore());
				out.writeUTF(temp.getName());
				out.writeUTF(temp.getTime());
				System.out.println("write a item");
				out.flush();
			}
			out.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void sortScoreList(List<ScoreListItem> scoreList) {
		int length = scoreList.size();
		for (int i = 0; i < length; i++) {
			// ScoreListItem temp = scoreList.get(i);
			int index = i;
			for (int j = i + 1; j < length; j++) {
				if (scoreList.get(j).getScore() > scoreList.get(index)
						.getScore()) {
					System.out.println("change index");
					System.out.println("index:" + index);
					System.out.println("j:" + j);
					index = j;
				}
			}
			
			System.out.println("---1--------------------");
			for (int k = 0; k < scoreList.size(); k++) {
				System.out.println(scoreList.get(k).getScore());
			}
			
			ScoreListItem temp = scoreList.get(index);
			scoreList.remove(index);
			
			System.out.println("---2--------------------");
			for (int k = 0; k < scoreList.size(); k++) {
				System.out.println(scoreList.get(k).getScore());
			}
			
			scoreList.add(i, temp);
			
			System.out.println("---3--------------------");
			for (int k = 0; k < scoreList.size(); k++) {
				System.out.println(scoreList.get(k).getScore());
			}

		}
		System.out.println("-----------------------");
		for (int i = 0; i < scoreList.size(); i++) {
			System.out.println(scoreList.get(i).getScore());
		}
		if (scoreList.size() == 6) {
			scoreList.remove(scoreList.size() - 1);
		}
	}

	@Override
	public int setNewScore(String newScore) {
		// TODO Auto-generated method stub
		this.gameCommandPanel.setNewScore(newScore);
		return 0;
	}

	@Override
	public void statusRollBack() {
		// TODO Auto-generated method stub
		this.ground.statusRollBack();
		gamePanel.display(ground, shape);
		
	}
}