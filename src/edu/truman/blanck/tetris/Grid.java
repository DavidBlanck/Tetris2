package edu.truman.blanck.tetris;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.JComponent;
public class Grid extends JComponent {

	private static final long serialVersionUID = 1L;
	
	public static final int UNIT_SIZE = 35;
	public static final int NO_COL = 10; //number of columns
	public static final int NO_ROWS = 16; //number of rows
	public static final int GRID_WIDTH = UNIT_SIZE * NO_COL;
	public static final int GRID_HEIGHT = UNIT_SIZE * NO_ROWS;
	
	private ArrayList<Line2D.Double> verticalLineList;
	private ArrayList<Line2D.Double> horizontalLineList;
	
	private Random rng;
	
	private FallingShape currentShape;
	
	private ArrayList<UnitSquare> stackList;

	public Grid() {
		setPreferredSize(new Dimension(GRID_WIDTH, GRID_HEIGHT));
		
		horizontalLineList = new ArrayList<>();
		for (int i = 0; i <= NO_ROWS; i++) {
			horizontalLineList.add(new Line2D.Double(0, i*UNIT_SIZE, GRID_WIDTH, i*UNIT_SIZE));
		}
		
		verticalLineList = new ArrayList<>();
		for (int i = 0; i <= NO_COL; i++) {
			verticalLineList.add(new Line2D.Double((i*UNIT_SIZE), 0, i*UNIT_SIZE, GRID_HEIGHT));
		}
		
		rng = new Random();
		
		stackList = new ArrayList<>();
		
		addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_SPACE) rotateShape();
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) shiftShapeR();
				if (e.getKeyCode() == KeyEvent.VK_LEFT) shiftShapeL();
				if (e.getKeyCode() == KeyEvent.VK_DOWN) fallShape();
			}
		});
		
		setFocusable(true);
	}
	
	public void deployShape() {
		int n = rng.nextInt(5);
		
		if (n == 0) currentShape = new FallingT();
		if (n == 1) currentShape = new FallingL();
		if (n == 2) currentShape = new FallingSquare();
		if (n == 3) currentShape = new FallingS();
		if (n == 4) currentShape = new FallingZ();
	}
	
	public void rotateShape() {
		currentShape.rotate();
		
		if(currentShape.checkBorder() || currentShape.checkOccupied(stackList)) {
			currentShape.rotateBack();
		}

	}
	
	public void shiftShapeR() {
		currentShape.shiftRight();
		
		if(currentShape.checkBorder() || currentShape.checkOccupied(stackList)) {
			shiftShapeL();
		}
	}
	
	public void shiftShapeL() {
		currentShape.shiftLeft();
		
		if(currentShape.checkBorder() || currentShape.checkOccupied(stackList)) {
			shiftShapeR();
		}
	}
	
	public void fallShape() {
		currentShape.fall();
		if(currentShape.checkBorder() || currentShape.checkOccupied(stackList)) {
			currentShape.shiftUp();
			currentShape.setOnFloor();
		}
	}
	
	public void addShapeToStack() {
		currentShape.addToStackList(stackList);
	}
	
	public void removeCompletedRows() {
		sortStackList();
		
		for (int row = 0; row < Grid.NO_ROWS; row++) {
			
			if (rowComplete(row)) {
				deleteRow(row);
			}
		}
	}
	
	private void deleteRow(int row) {
		int index = 0;
		
		// Advance index to deletion row
		while ((stackList.get(index).getRow()) > row) {
			index++;
		}
		
		// Delete row
		for (int i = 0; i < NO_COL; i++) {
			stackList.remove(index);
		}
		
		// Fall squares above deletion row
		for (int i = index; i < stackList.size(); i++) {
			stackList.get(i).fall();
		}
	}
	
	private boolean rowComplete(int row) {
		int index = 0;
		
		// check to see if row is empty
		if (stackList.get(stackList.size() - 1).getRow() > row) return false;
		
		// advance index to row
		while (stackList.get(index).getRow() > row) {
			index++;
		}
		
		// check if at least NO_COL - 1 more elements exist
		if (stackList.size() <= index + NO_COL - 1) {
			return false;
		}
		
		// compare with the row of the element NO-COL - 1 elements later
		else if ((int) stackList.get(index + NO_COL - 1).getRow() == row) {
			return true;
		}
		
		else return false;
	}
	
	/** Sort stackList in ascending order by row then column.
	 */
	public void sortStackList() {
		for (int i = 0; i < stackList.size() - 1; i++) {
			for (int j = i + 1; j < stackList.size(); j++) {
				if (stackList.get(i).getRow() < stackList.get(j).getRow()) {
					Collections.swap(stackList, i, j);
				}
				else if ((stackList.get(i).getRow() == (int) stackList.get(j).getRow())
						&& (stackList.get(i).getCol() > stackList.get(j).getCol())) {
					Collections.swap(stackList, i, j);
				}
			}
		}
	}
	
	
	public boolean shapeIsOnFloor() {
		return currentShape.isOnFloor();
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.LIGHT_GRAY);
		
		for (int i = 0; i <= NO_COL; i++) {
			g2.draw(verticalLineList.get(i));
		}
		for (int i = 0; i <= NO_ROWS; i++) {
			g2.draw(horizontalLineList.get(i));
		}
		
		currentShape.draw(g2);
		
		for(int i = 0; i < stackList.size(); i++) {
			g2.setColor(stackList.get(i).getColor());
			stackList.get(i).draw(g2);
		}
	}

}
