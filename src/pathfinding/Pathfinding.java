package pathfinding;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Pathfinding {

	Grid nodeGrid;
	
	public void getGrid(Grid _grid) {
		nodeGrid = _grid;
	}
	
	public void findPath(int startX, int startY, int targetX, int targetY) {
		Node startNode = nodeGrid.grid[startX][startY];
		Node targetNode = nodeGrid.grid[targetX][targetY];
		
		ArrayList<Node> openSet = new ArrayList<Node>();
		ArrayList<Node> closedSet = new ArrayList<Node>();
		openSet.add(startNode);
		System.out.println("start node " + startNode.nodeX + " : " + startNode.nodeY);
		
		while(openSet.size() > 0) {
			//get the node in the openSet with the lowest fCost (or if tie, lowest Hcost)
			Node currentNode = openSet.get(0);
			for(int i = 1; i < openSet.size(); i++) {
				if(openSet.get(i).fCost() < currentNode.fCost() ||(openSet.get(i).fCost() == currentNode.fCost() && openSet.get(i).hCost < currentNode.hCost)) {
					currentNode = openSet.get(i);
				}
			}
			
			currentNode.changeColor(Color.RED);
			openSet.remove(currentNode);
			closedSet.add(currentNode);
			
			if(currentNode == targetNode) {
				System.out.println("1");
				retracePath(startNode, targetNode);
				return;
			}
			
			ArrayList<Node> neighbourArray = nodeGrid.getNeighbours(currentNode);
			
			for(int i = 0; i < neighbourArray.size(); i++) {
				Node neighbour = neighbourArray.get(i);
				
				if(neighbour.walkable && !closedSet.contains(neighbour)) {
					int newMovementCostToNeighbour = currentNode.gCost + getDistance(currentNode, neighbour);
					if(newMovementCostToNeighbour < neighbour.gCost || !openSet.contains(neighbour)) {
						neighbour.gCost = newMovementCostToNeighbour;
						neighbour.hCost = getDistance(neighbour, targetNode);
						neighbour.parent = currentNode;
						
						if(!openSet.contains(neighbour)) {
							neighbour.changeColor(Color.ORANGE);
							openSet.add(neighbour);
						}
					}
				}
			}
		}
	}
	
	void retracePath(Node startNode, Node endNode) {
		ArrayList<Node> path = new ArrayList<Node>();
		Node currentNode = endNode;
		System.out.println("pathfinding");
		while(currentNode != startNode) {
			currentNode.changeColor(Color.GREEN);
			path.add(currentNode);
			currentNode = currentNode.parent;
		}
		startNode.changeColor(Color.BLUE);
		endNode.changeColor(Color.BLUE);
	}
	
	int getDistance(Node nodeA, Node nodeB) {
		int dstX = Math.abs(nodeA.x - nodeB.x);
		int dstY = Math.abs(nodeA.y - nodeB.y);
		
		if(dstX > dstY) 
			return 14*dstY + 10*(dstX - dstY);
		
		return 14*dstX + 10*(dstY - dstX);
	}
	
}
