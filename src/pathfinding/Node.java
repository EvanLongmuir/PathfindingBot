package pathfinding;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Node {
	boolean walkable;
	int x, y;
	float radius;
	
	int nodeX, nodeY;
	
	boolean isStart = false;
	boolean isEnd = false;
	
	boolean isEditable = true;
	
	GraphicsContext gc;
    @FXML
    Canvas gameCanvas;
    
    static float nodeGap = 10;
    
    int gCost;
    int hCost;
    
    public Node parent;
	
	public Node(boolean walkable, int x, int y, int nodeX, int nodeY, float radius, GraphicsContext gc, Canvas gameCanvas) {
		this.walkable = walkable;
		this.x = x;
		this.y = y;
		this.nodeX = nodeX;
		this.nodeY = nodeY;
		this.radius = radius;
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		
		changeColor(Color.GREY);
	}
	
	public int fCost(){
		return gCost + hCost;
	}
	
	public void changeColor(Paint color) {
		this.gc.setFill(color);
		drawNode();
	}
	
	public void drawNode() {
		this.gc.fillRect(x-radius + (nodeGap/2), y-radius + (nodeGap/2), (radius * 2) - (nodeGap), (radius * 2) - (nodeGap));
	}
	
}
