package dt.Tetris.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import dt.Tetris.listener.PanelChangeListener;

public class HandicapPanel extends JPanel{
	JLabel tempLabel = new JLabel("this is HandicapPanel");
	PanelChangeListener listener;
	JButton jbutton;
	Border aBorder;
	Border aTitleBorder;
	ButtonGroup group;
	MyJRadioButton radioButton[];
	int handicapLevel = 0;
	JLabel label;
	
	public HandicapPanel() {
		// TODO Auto-generated constructor stub
		aBorder = BorderFactory.createEtchedBorder();
		aTitleBorder = BorderFactory.createTitledBorder(aBorder, "set Handicap");
		group = new ButtonGroup();
		radioButton = new MyJRadioButton[10];
		label = new JLabel();
		label.setFocusable(false);
		for(int i = 0; i < 10; i++){
			if(i == 0){
				radioButton[i] = new MyJRadioButton(i + "", true, i);
				group.add(radioButton[i]);
				this.add(radioButton[i]);
				radioButton[i].setFocusable(false);
				radioButton[i].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						handicapLevel = ((MyJRadioButton)arg0.getSource()).level;
						label.setText(handicapLevel + "");
					}
				});
			}
			else{
				radioButton[i] = new MyJRadioButton(i + "", false, i);
				group.add(radioButton[i]);
				this.add(radioButton[i]);
				radioButton[i].setFocusable(false);
				radioButton[i].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						handicapLevel = ((MyJRadioButton)arg0.getSource()).level;
						label.setText(handicapLevel + "");
					}
				});
			}
		}
		this.setBorder(aTitleBorder);
		this.add(label);
	}
}

class MyJRadioButton extends JRadioButton{
	int level;
	public MyJRadioButton(String msg, boolean selected, int level){
		super(msg, selected);
		this.level = level;
	}
}