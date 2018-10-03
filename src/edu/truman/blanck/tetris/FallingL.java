package edu.truman.blanck.tetris;

import java.awt.Color;
import java.util.ArrayList;

public class FallingL extends FallingShape {
	
	private ArrayList<UnitSquare> squareList;
	
	private Color color;

	public FallingL() {
		super();
		
		color = Color.magenta;
		
		squareList = new ArrayList<>();
		
		squareList.add(new UnitSquare(col, row - 1, color));
		squareList.add(new UnitSquare(col, row, color));
		squareList.add(new UnitSquare(col, row + 1, color));
		squareList.add(new UnitSquare(col, row + 2, color));
	}


	protected ArrayList<UnitSquare> getSquareList() {
		return squareList;
	}
	
	protected Color getColor() {
		return Color.MAGENTA;
	}

	protected void updateSquares() {
		if (super.orientation == 1 || super.orientation == 3) {
			squareList.set(0,  new UnitSquare(col, row - 1, color));
			squareList.set(1,  new UnitSquare(col, row, color));
			squareList.set(2,  new UnitSquare(col, row + 1, color));
			squareList.set(3,  new UnitSquare(col, row + 2, color));
		}
		
		if (super.orientation == 2 || super.orientation == 4) {
			squareList.set(0,  new UnitSquare(col - 1, row, color));
			squareList.set(1,  new UnitSquare(col, row, color));
			squareList.set(2,  new UnitSquare(col + 1, row, color));
			squareList.set(3,  new UnitSquare(col + 2, row, color));
		}

	}

}
