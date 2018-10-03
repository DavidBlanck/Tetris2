package edu.truman.blanck.tetris;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class UnitSquare {
	
	int col;
	int row;
	
	boolean graphicsReady;
	
	Rectangle2D.Double graphicSquare;
	Color color;

	public UnitSquare(int col, int row, Color color) {
		this.col = col;
		this.row = row;
		this.color = color;
		
		graphicSquare = new Rectangle2D.Double(col * Grid.UNIT_SIZE, 
				row * Grid.UNIT_SIZE, Grid.UNIT_SIZE, Grid.UNIT_SIZE);
		graphicsReady = true;
	}
	
	public void fall() {
		row += 1;
		graphicsReady = false;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void draw(Graphics2D g2) {
		if (!graphicsReady)
		{
			graphicSquare = new Rectangle2D.Double(col * Grid.UNIT_SIZE, 
					row * Grid.UNIT_SIZE, Grid.UNIT_SIZE, Grid.UNIT_SIZE);
			graphicsReady = true;
		}
		g2.setColor(color);
		g2.fill(graphicSquare);
		g2.setColor(Color.black);
		g2.draw(graphicSquare);;
	}

	public Color getColor() {
		return color;
	}
	

}
