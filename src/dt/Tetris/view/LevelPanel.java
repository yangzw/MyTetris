package dt.Tetris.view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dt.Tetris.listener.PanelChangeListener;

public class LevelPanel extends JPanel{
	JLabel tempLabel = new JLabel("this is LevelPanel");
	PanelChangeListener listener;
	JButton jbutton;
	Border aBorder;
	Border aTitleBorder;
	JSlider slider;
	JLabel levelLabel;
	int speedLevel = 50;
	
	public LevelPanel() {
		// TODO Auto-generated constructor stub
		aBorder = BorderFactory.createEtchedBorder();
		aTitleBorder = BorderFactory.createTitledBorder(aBorder, "set speed Level");
		this.setBorder(aTitleBorder);
		slider = new JSlider(0, 100, 50);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(10);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setFocusable(false);
		this.add(slider);
		levelLabel = new JLabel();
		levelLabel.setFocusable(false);
		this.add(levelLabel);
		slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				levelLabel.setText(slider.getValue() + "");
				speedLevel = slider.getValue();
			}
		});
	}
}
