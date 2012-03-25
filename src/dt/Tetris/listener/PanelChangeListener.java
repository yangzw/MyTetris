package dt.Tetris.listener;

public interface PanelChangeListener {
	public void completeSelect();
	public void changePanel(String destination);
	public void stopGame();
	public void pauseGame();
	public void continueGame();
}
