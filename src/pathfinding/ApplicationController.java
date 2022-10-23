package pathfinding;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class ApplicationController {
	
	@FXML
	Canvas applicationCanvas;
	
	Scene gameScene;
	
	GraphicsContext gc;
	
	Grid nodeGrid;
	
	int inputNumber = 0;
	
	int startNodeX;
	int startNodeY;
	int endNodeX;
	int endNodeY;
	
	//Button properties
	int buttonX = 0;
	int buttonY = 0;
	int buttonSize = 15;
	Paint buttonColor = Color.RED;
	boolean buttonDrawn = false;
	
	int nodeRadius = 25;
	
	public void onStart(FXMLLoader loader, Stage primaryStage, ApplicationController controller){
		//set up the input detection
		InputDetection detector = new InputDetection();
		gc = applicationCanvas.getGraphicsContext2D();
		detector.getApplicationInformation(primaryStage, applicationCanvas, gc);
		detector.inputListener(controller);
		
		//make the Grid
		System.out.println(primaryStage.getHeight());
		nodeGrid = new Grid((int)primaryStage.getWidth(), (int)primaryStage.getHeight(), nodeRadius, gc, applicationCanvas);
		nodeGrid.makeGrid();
	}
	
	public void onMouseClick(double xPos, double yPos) {
		System.out.println("mouse input at (" + xPos + "," + yPos + ")");
		int xNode = (int) (xPos/(nodeGrid.nodeRadius*2));
		int yNode = (int) (yPos/(nodeGrid.nodeRadius*2));
		
		if(xPos > buttonX && xPos < buttonX + buttonSize && yPos > buttonY && yPos < buttonY + buttonSize && buttonDrawn) {
			buttonPressed();
			return;
		}
		else {
			nodeGrid.resetColor();
		}
		
		if(nodeGrid.grid[xNode][yNode].isEditable == false) return;
		
		if(inputNumber == 0) {
			nodeGrid.grid[xNode][yNode].changeColor(Color.BLUE);
			nodeGrid.grid[xNode][yNode].isStart = true;
			nodeGrid.grid[xNode][yNode].isEditable = false;
			startNodeX = xNode;
			startNodeY = yNode;
			inputNumber = 1;
		}
		else if(inputNumber == 1) {
			nodeGrid.grid[xNode][yNode].changeColor(Color.BLUE);
			nodeGrid.grid[xNode][yNode].isEnd = true;
			nodeGrid.grid[xNode][yNode].isEditable = false;
			endNodeX = xNode;
			endNodeY = yNode;
			inputNumber = 2;
			
			buttonDrawn = true;
			this.gc.setFill(buttonColor);
			gc.fillRect(buttonX, buttonY, buttonSize, buttonSize);
		}
		else if(inputNumber == 2) {
			if (nodeGrid.grid[xNode][yNode].walkable) {
				nodeGrid.grid[xNode][yNode].changeColor(Color.BLACK);
				nodeGrid.grid[xNode][yNode].walkable = false;
			}
			else {
				nodeGrid.grid[xNode][yNode].changeColor(Color.GREY);
				nodeGrid.grid[xNode][yNode].walkable = true;
			}
			
			this.gc.setFill(buttonColor);
			gc.fillRect(buttonX, buttonY, buttonSize, buttonSize);
		}
		
	}
	
	public void buttonPressed() {
		System.out.println("buttonPressed");
		Pathfinding pathfindingController = new Pathfinding();
		pathfindingController.getGrid(nodeGrid);
		System.out.println("startNodeX " + startNodeX);
		pathfindingController.findPath(startNodeX, startNodeY, endNodeX, endNodeY);
	}
	
}

