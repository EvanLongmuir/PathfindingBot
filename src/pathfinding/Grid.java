package pathfinding;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Grid {
	//size of the area that the grids cover
	float worldSizeX, worldSizeY;
	float nodeRadius;
	Node[][] grid;
	
	//number of nodes in each dimension
	int nodeNumberX, nodeNumberY;
	
	GraphicsContext gc;
    @FXML
    Canvas appCanvas;
	
    
	
	public Grid(float worldSizeX, float worldSizeY, float nodeRadius, GraphicsContext gc, Canvas appCanvas) {
		super();
		this.worldSizeX = worldSizeX;
		this.worldSizeY = worldSizeY;
		this.nodeRadius = nodeRadius;
		this.gc = gc;
		this.appCanvas = appCanvas;
	}

	public void makeGrid() {
		nodeNumberX = (int)(worldSizeX/(nodeRadius*2));
		nodeNumberY = (int)(worldSizeY/(nodeRadius*2));
		
		grid = new Node[nodeNumberX][nodeNumberY];
		
		for(int x = 0; x < nodeNumberX; x++) {
			int nodeX = (int)(nodeRadius + x*2*nodeRadius);
			for(int y = 0; y < nodeNumberY; y++) {
				int nodeY = (int)(nodeRadius + y*2*nodeRadius);
				
				grid[x][y] = new Node(true, nodeX, nodeY, x, y, nodeRadius, gc, appCanvas);
			}
		}
		
	}
	
	public ArrayList<Node> getNeighbours(Node node){
		
		ArrayList<Node> neighbour = new ArrayList<Node>();
		
		for(int y = -1; y <= 1; y++) {
			int targetY = node.nodeY + y;
			
			for(int x = -1; x <= 1; x++) {
				if(x == 0 && y == 0) 
					continue;
				
				int targetX = node.nodeX + x;
				
				
				if(targetX < nodeNumberX && targetX >= 0 && targetY < nodeNumberY && targetY >= 0) {
					neighbour.add(grid[targetX][targetY]);
				}
			}
		}
		
		System.out.println("NodeX " + node.nodeX + " NodeY " + node.nodeY);
		
		return neighbour;
	}
	
	public void resetColor() {
		for(int x = 0; x < nodeNumberX; x++) {
			for(int y = 0; y < nodeNumberY; y++) {
				Node currentNode = grid[x][y];
				if(currentNode.isStart || currentNode.isEnd) {
					currentNode.changeColor(Color.BLUE);
				}
				else if(!currentNode.walkable) {
					currentNode.changeColor(Color.BLACK);
				}
				else {
					currentNode.changeColor(Color.GREY);
				}
			}
		}
	}
	
}
