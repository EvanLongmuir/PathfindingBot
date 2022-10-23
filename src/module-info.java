module Pathfinding {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens pathfinding to javafx.graphics, javafx.fxml;
}
