package dt.Tetris.entities;

import java.awt.GridLayout;
import java.util.Iterator;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyDialog extends JDialog{
	JPanel aPanel;
	public MyDialog(List<ScoreListItem> scoreList){
		super(new JFrame(), "Hi Score", true);
		aPanel = new JPanel();
		aPanel.setSize(750, 300);
		this.setSize(aPanel.getWidth() + 20, aPanel.getHeight() + 20);
		String noString = "No.";
		String nameString = "Player";
		String scoreString = "Score";
		String timeString = "Time";
		JLabel numLabel = new JLabel(noString);
		JLabel nameLabel = new JLabel(nameString);
		JLabel scoreLabel = new JLabel(scoreString);
		JLabel timeLabel = new JLabel(timeString);
		aPanel.setLayout(new GridLayout(6, 4));
		aPanel.add(numLabel);
		aPanel.add(nameLabel);
		aPanel.add(scoreLabel);
		aPanel.add(timeLabel);
		
		int no = 1;
		Iterator<ScoreListItem> iterator = scoreList.iterator();
		while(iterator.hasNext()){
			ScoreListItem temp = iterator.next();
			aPanel.add(new JLabel(no + ""));
			aPanel.add(new JLabel(temp.getName()));
			aPanel.add(new JLabel(temp.getScore() + ""));
			aPanel.add(new JLabel(temp.getTime()));
			no++;
		}
		no--;
		for(int i = 0; i < 20 - no * 4; i++){
			aPanel.add(new JLabel("--------"));
		}
		
		this.add(aPanel);
	}
	
	public void showMyDialog(){
		this.setVisible(true);
		System.out.println("sssssssssssssssssssssssssssssssssssssssssssssss");
	}
}
