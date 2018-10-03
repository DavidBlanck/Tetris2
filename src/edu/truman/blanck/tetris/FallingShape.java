package edu.truman.blanck.tetris;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public abstract class FallingShape {
	
	protected int row;
	protected int col;
	protected int orientation;
	
	private final int NO_SQUARES = 4;
	private boolean onFloor;
	
	public FallingShape() {
		col = 4;
		row = 1;
		orientation = 1;
	}
	
	
	public void rotate() {
		if (orientation == 1) orientation = 4;
		else orientation--;
		
		updateSquares();
	}
	
	public void rotateBack() {
		if (orientation == 4) orientation = 1;
		else orientation++;
		
		updateSquares();
	}
	
	public void fall() {
		row += 1;
		
		updateSquares();
	}
	
	public void shiftUp() {
		row -= 1;
		
		updateSquares();
	}
	
	public void shiftRight() {
		col += 1;
		
		updateSquares();
	}
	
	public void shiftLeft() {
		col -= 1;
		
		updateSquares();
	}
	
	public void setOnFloor() {
		onFloor = true;
	}
	
	public boolean isOnFloor() {
		return onFloor;
	}
	
	public boolean checkOccupied(ArrayList<UnitSquare> stackList) {
		for (int i = 0; i < NO_SQUARES; i++){
			for (int j = 0; j < stackList.size(); j ++) {
				if (getSquareList().get(i).getCol() == stackList.get(j).getCol()
						&& getSquareList().get(i).getRow() == stackList.get(j).getRow())
					return true;
			}
		}
		return false;
	}
	
	public boolean checkBorder() {
		for (int i = 0; i < NO_SQUARES; i++) {
			if (getSquareList().get(i).getRow() == Grid.NO_ROWS) {
				return true;
			}
			if ((getSquareList().get(i).getCol() < 0) || 
					(getSquareList().get(i).getCol() == Grid.NO_COL)) {
				return true;
			}
		}
		return false;
	}
	
	public void addToStackList(ArrayList<UnitSquare> stackList){
		for (int i = 0; i < NO_SQUARES; i++) {
			stackList.add(getSquareList().get(i));
		}
	}
	
	public void draw(Graphics2D g2) {
		for (int i = 0; i < NO_SQUARES; i++) {
			g2.setColor(getColor());
			getSquareList().get(i).draw(g2);
		}
	}
	
	protected abstract ArrayList<UnitSquare> getSquareList();
	
	protected abstract Color getColor();
	
	protected abstract void updateSquares();
}
