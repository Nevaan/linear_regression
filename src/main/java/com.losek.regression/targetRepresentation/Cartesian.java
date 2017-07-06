package com.losek.regression.targetRepresentation;

import java.util.ArrayList;
import java.util.List;

public class Cartesian {

	private List<Point> board;

	public Cartesian() {
		init();
	}

	public void init() {
		board = new ArrayList<Point>();
		
		// Function: 6x^3 + 2x^2 - 9x-7
		board.add(new Point(-5.0, -662.0));
		board.add(new Point(-3.0, -124.0));
		board.add(new Point(-1.0, -2.0));
		board.add(new Point(0.0, 7.0));
		board.add(new Point(1.0, 8.0));
		board.add(new Point(3.0, 146.0));
		board.add(new Point(5.0, 146.0));
		
		
	}

	public List<Point> getBoard() {
		return board;
	}

	public void setBoard(List<Point> board) {
		this.board = board;
	}

}
