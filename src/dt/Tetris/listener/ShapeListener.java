package dt.Tetris.listener;

import dt.Tetris.entities.Shape;

public interface ShapeListener {
	public void shapeMoveDown(Shape shape);
	public boolean isShapeMoveDownable(Shape shape);
}
