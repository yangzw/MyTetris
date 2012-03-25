package dt.Tetris.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;

import dt.Tetris.entities.ShapeForPredict;
import dt.Tetris.listener.ControlPanelListener;
import dt.Tetris.listener.GameListener;
import dt.Tetris.util.Global;

public class ControlPanel extends JPanel implements ControlPanelListener{
	private JPanel setPanel;
	private JPanel scorePanel;
	private PredictPanel predictPanel;
	//private JLabel temp3;
	private JLabel message;
	private int nextType;
	int level, handicap;
	GameListener gameListener;
	JButton speedUpButton;
	JButton speedDownButton;
	JButton tempButton;
	JButton rollBackButton;
	JLabel temp3 = new JLabel("this is predictPanel");
	
	public ControlPanel(){
		setPanel = new JPanel();
		scorePanel = new JPanel();
		predictPanel = new PredictPanel();
		
		//setPanel.add(temp1);
		//predictPanel.add(temp3);
		this.setLayout(new GridLayout(1, 3));
		this.add(setPanel);
		this.add(scorePanel);
		this.add(predictPanel);
		
		//Set Panel
		//Border aBorder = BorderFactory.createEtchedBorder();
		//Border aTitledBorder = BorderFactory.createTitledBorder(aBorder, "set speed");
		setPanel.setLayout(new FlowLayout());
		speedUpButton = new JButton(">>");
		speedDownButton = new JButton("<<");
		speedUpButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				gameListener.speedUp();
			}
		});
		
		speedDownButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gameListener.speedDown();
			}
		});
		
		rollBackButton = new JButton("roll back");
		
		rollBackButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				gameListener.statusRollBack();
			}
		});
		
		speedDownButton.setFocusable(false);
		speedUpButton.setFocusable(false);
		rollBackButton.setFocusable(false);
		setPanel.add(speedDownButton);
		setPanel.add(speedUpButton);
		setPanel.add(rollBackButton);
		setButtonEnabled(false);
		
		//score panel
		//add a set score method
		message = new JLabel();
		tempButton = new JButton("refresh");
		tempButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//message.setText("Score: " + gameListener.getSocre());
				gameListener.refresh();
			}
		});
		tempButton.setFocusable(false);
		scorePanel.setLayout(new BorderLayout());
		scorePanel.add(message, BorderLayout.CENTER);
		scorePanel.add(tempButton, BorderLayout.SOUTH);
		
		//predict panel
		
		
	}
	
	public void setNextType(int nextType){
		this.nextType = nextType;
		//temp3.setText("nextType " + nextType);
		predictPanel.repaint();
		predictPanel.setBackground(Color.green);
		System.out.println("nextType1------------------> " + this.nextType);
	}
	
	class PredictPanel extends JPanel{
		int body[][];
		
		public PredictPanel() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			//super.paintComponent(g);
			//g.drawOval(0, 0, this.getWidth(), this.getHeight());
			int top = this.getLocation().y;
			int left = this.getLocation().x;
			//ShapeForPredict nextShape = new ShapeForPredict(top, left);
			//nextShape.setBody(Global.shapes[nextType]);
			System.out.println("nextType1------------------> " + nextType);
			//nextShape.drawMe(g);
			System.out.println("@@@@@@@@@@@@@@@@@@draw");
			body = Global.shapes[nextType];
			g.setColor(new Color(238, 238, 238));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			for (int x = 0; x < 4; x++) {
				for (int y = 0; y < 4; y++) {
					if (getFlagByPoint(x, y)) {
						System.out.println("i am drawing");
						g.setColor(Color.yellow);
						System.out.println(g.getColor().toString());
						System.out.println("x = " + x + "  y = " + y);
						g.fill3DRect((x + 2) * Global.CELL_SIZE, (y + 2) * Global.CELL_SIZE, Global.CELL_SIZE,
								Global.CELL_SIZE, true);
					}
				}
			}
			
		}
		
		private boolean getFlagByPoint(int x, int y) {
			return body[0][y * 4 + x] == 1;
		}
		
	}

	public void setButtonEnabled(boolean status) {
		speedDownButton.setEnabled(status);
		speedUpButton.setEnabled(status);
		rollBackButton.setEnabled(status);
	}
	
	@Override
	public void setScore(String score) {
		// TODO Auto-generated method stub
		this.message.setText("Score: " + score);
	}	
}

