package dt.Tetris.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dt.Tetris.listener.PanelChangeListener;

public class OptionPanel extends JPanel{
	PanelChangeListener listener;
	JLabel jlabel = new JLabel("This is OptionPanel");
	JButton jbutton = new JButton("next");
	LevelPanel levelPanel;
	HandicapPanel handicapPanel;
	MusicPanel musicPanel;
	
	public OptionPanel(PanelChangeListener l){
		this.listener = l;
		//this.add(jlabel);
		//this.add(jbutton);
		levelPanel = new LevelPanel();
		handicapPanel = new HandicapPanel();
		musicPanel = new MusicPanel();
		jbutton.setFocusable(false);
		jbutton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				listener.changePanel("gameCommandPanel");
			}
			
		});
		
		Box b = Box.createVerticalBox();
		b.add(levelPanel);
		b.add(handicapPanel);
		b.add(musicPanel);
		b.add(jbutton);
		this.add(b);
		levelPanel.setSize(this.getSize().width / 2 - 50, this.getSize().height / 2 - 50);
	}
	
	public int getSpeedLevel(){
		return this.levelPanel.speedLevel;
	}
	
	public int getHandicapLevel(){
		return this.handicapPanel.handicapLevel;
	}

}
