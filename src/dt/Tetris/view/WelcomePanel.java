package dt.Tetris.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dt.Tetris.listener.PanelChangeListener;

public class WelcomePanel extends JPanel{
	//JLabel tempLabel = new JLabel("this is WelcomePanel");
	PanelChangeListener listener;
	Image img;
	Player player;
	URL musicURL;
	
	public WelcomePanel(PanelChangeListener l) {
		// TODO Auto-generated constructor stub
		listener = l;
		//this.player = player;
		//this.add(tempLabel);
		img = this.getToolkit().getImage("C:" +File.separator + "Users" + File.separator + "Administrator" +File.separator + "Desktop" + File.separator + "Java_Source" + File.separator + "MyTetris" + File.separator + "src" + File.separator + "dt" + File.separator + "Tetris" + File.separator + "view" + File.separator + "welcome.jpg");
		this.repaint();
		musicURL = WelcomePanel.class.getResource("/start_music.mp3");
		//System.out.println("musicURL:" + musicURL.toString());
		playWelcomeMusic();
		//this.setBackground(Color.blue);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		//super.paintComponent(g);
		if(img == null){
			System.out.println("img is null");
		}
		else{
			System.out.println("img is not null");
			System.out.println(img.toString());
		}
		g.drawImage(img, 0, 0, this);
	}
	
	protected void playWelcomeMusic(){
		//URL imageURL = GameOptionPanel.class.getResource("/czbk.png");
		//String mp3Path = "C:" + File.separator + "Users" + File.separator + "Administrator" + File.separator + "Desktop" + File.separator + "Java_Source" + File.separator + "MyTetris" + File.separator + "src" + File.separator + "dt" + File.separator + "Tetris" + File.separator + "view"  + File.separator + "start_music.mp3"; 
		
		try {
			//player = Manager.createPlayer(new File(mp3Path).toURI().toURL());
			if(musicURL == null){
				System.out.println("musicURL is null");
			}
			else{
				System.out.println("musicURL is not null");
			}
			player = Manager.createPlayer(musicURL);
			player.prefetch();
			player.start();
		} catch (NoPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}