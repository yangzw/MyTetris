package dt.Tetris.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.Time;
import javax.media.*;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

import dt.Tetris.listener.PanelChangeListener;

public class MusicPanel extends JPanel {
	JLabel tempLabel = new JLabel("this is MusicPanel");
	PanelChangeListener listener;
	JButton jbutton;
	Border aBorder;
	Border aTitleBorder;
	ButtonGroup musicButtonGroup;
	JRadioButton music1, music2, music3, music4;
	Player player;
	URL musicURL;

	public MusicPanel() {
		// TODO Auto-generated constructor stub
		aBorder = BorderFactory.createEtchedBorder();
		aTitleBorder = BorderFactory.createTitledBorder(aBorder, "set Music");
		this.setBorder(aTitleBorder);
		musicButtonGroup = new ButtonGroup();
		music1 = new JRadioButton("brandisky", false);
		music2 = new JRadioButton("karinka", false);
		music3 = new JRadioButton("loginska", false);
		music4 = new JRadioButton("troika", false);
		musicButtonGroup.add(music1);
		musicButtonGroup.add(music2);
		musicButtonGroup.add(music3);
		musicButtonGroup.add(music4);
		music1.setFocusable(false);
		music2.setFocusable(false);
		music3.setFocusable(false);
		music4.setFocusable(false);
		this.setLayout(new GridLayout(4, 1));
		this.add(music1);
		this.add(music2);
		this.add(music3);
		this.add(music4);

		music1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (player == null) {
					playDirectly("/brandisky.mp3");
				} else {
					stopAndPlayNew("/brandisky.mp3");
				}
			}
		});

		music2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (player == null) {
					playDirectly("/karinka.mp3");
				} else {
					stopAndPlayNew("/karinka.mp3");
				}
			}
		});

		music3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (player == null) {
					playDirectly("/loginska.mp3");
				} else {
					stopAndPlayNew("/loginska.mp3");
				}
			}
		});

		music4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (player == null) {
					playDirectly("/troika.mp3");
				} else {
					stopAndPlayNew("/troika.mp3");
				}
			}
		});
	}

	private void playDirectly(String path) {
		musicURL = MusicPanel.class.getResource(path);
		try {
			player = Manager.createPlayer(musicURL);
		} catch (NoPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		player.prefetch();
		player.addControllerListener(new ControllerHand());
		player.start();
	}

	private void stopAndPlayNew(String path) {
		player.stop();
		playDirectly(path);
	}

	class ControllerHand implements ControllerListener {

		@Override
		public void controllerUpdate(ControllerEvent e) {
			// TODO Auto-generated method stub
			if (e instanceof EndOfMediaEvent) {
				player.setMediaTime(new Time(0));
				player.start();
			}
		}

	}

}