package pathfinding;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InputDetection {

	@FXML
	Canvas applicationCanvas;
	
	Scene gameScene;
	
	GraphicsContext gc;
	
	double clickX;
	double clickY;
	
	public void getApplicationInformation(Stage primaryStage, Canvas _applicationCanvas, GraphicsContext _gc) {
		gameScene = primaryStage.getScene();
		applicationCanvas = _applicationCanvas;
		gc = _gc;
	}
	
	public void inputListener(ApplicationController controller) {
		gameScene.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				clickX = e.getSceneX();
				clickY = e.getSceneY();
				
				controller.onMouseClick(clickX, clickY);
			}
		});
	}
	
}
