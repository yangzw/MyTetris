package dt.Tetris.entities;

public class ScoreListItem {
	int score;
	String name;
	String time;
	public ScoreListItem(int score, String name, String time){
		this.score = score;
		this.name = name;
		this.time = time;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
