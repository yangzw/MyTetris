package dt.Tetris.listener;

public interface GameListener {
	public void pause();
	public void stop();
	public void continueGame();
	public void speedUp();
	public void speedDown();
	public int getSocre();
	public int setNewScore(String newScore);
	public void refresh();
	public void statusRollBack();
}
