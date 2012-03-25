package dt.Tetris.entities;

import dt.Tetris.listener.ShapeListener;
import dt.Tetris.util.Global;

public class ShapeFactory {
	public Shape getShape(ShapeListener listener, int speedLevel, int shapeType){
		System.out.println("ShapeFactory' s getShape");
		Shape shape = new Shape(speedLevel, listener);
		System.out.println("i have got a reference of shape");
		//shape.addListener(listener);
		//changeShapeType();
		//int type = new Random().nextInt(shapes.length);
		System.out.println("in factory shape type-------> " + shapeType);
		shape.setBody(Global.shapes[shapeType]);
		//shapeType = new Random().nextInt(shapes.length);
		
		shape.setStatus(0);
		System.out.println("will return shape ---------shapeFactory");
		return shape;
	}
	
	public int getShapesLength(){
		return Global.shapes.length;
	}
}
