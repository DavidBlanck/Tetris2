package edu.truman.blanck.tetris;

import java.awt.Color;
import java.util.ArrayList;

public class FallingJ extends FallingShape {
	
	private ArrayList<UnitSquare> squareList;
	
	private Color color;

	public FallingJ() {
		super();
		
		color = Color.WHITE;
		
		squareList = new ArrayList<>();
		squareList.add(new UnitSquare(col, row + 2, color));
		squareList.add(new UnitSquare(col, row + 1, color));
		squareList.add(new UnitSquare(col, row, color));
		squareList.add(new UnitSquare(col - 1, row, color));
	}

	@Override
	protected ArrayList<UnitSquare> getSquareList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void updateSquares() {
		if (orientation == 1) {
			squareList.set(0, new UnitSquare(col, row - 2, color));
			squareList.set(1, new UnitSquare(col, row - 1, color));
			squareList.set(2, new UnitSquare(col, row, color));
			squareList.set(3, new UnitSquare(col - 1, row, color));
		}
		if (orientation == 2) {
			squareList.set(0, new UnitSquare(col, row - 1, color));
		}

	}

}
