package edu.truman.blanck.tetris;

import java.awt.Color;
import java.util.ArrayList;

public class FallingSquare extends FallingShape {
	
	private ArrayList<UnitSquare> squareList; 
	
	private Color color;

	public FallingSquare() {
		super();
		
		color = Color.GRAY;
		
		squareList = new ArrayList<>();
		
		squareList.add(new UnitSquare(col, row - 1, color));
		squareList.add(new UnitSquare(col + 1, row - 1, color));
		squareList.add(new UnitSquare(col, row, color));
		squareList.add(new UnitSquare(col + 1, row, color));
	}

	@Override
	protected ArrayList<UnitSquare> getSquareList() {
		return squareList;
	}

	protected Color getColor() {
		return Color.BLUE;
	}

	@Override
	protected void updateSquares() {
		squareList.set(0, new UnitSquare(col, row - 1, color));
		squareList.set(1, new UnitSquare(col + 1, row - 1, color));
		squareList.set(2, new UnitSquare(col, row, color));
		squareList.set(3, new UnitSquare(col + 1, row, color));
	}

}
