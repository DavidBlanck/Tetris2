import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import edu.truman.blanck.tetris.*;


public class BlanckTetris {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		final Grid grid = new Grid();
		
		frame.add(grid);
		
		grid.deployShape();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		
		
		Timer t1 = new Timer(1000, new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				grid.fallShape();
				
				if (grid.shapeIsOnFloor()) {
					grid.addShapeToStack();
					grid.removeCompletedRows();
					grid.deployShape();
				}
			}
		});
		
		Timer t2 = new Timer(100, new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				grid.repaint();
			}
		});
		
		t1.start();
		t2.start();
	}

}
