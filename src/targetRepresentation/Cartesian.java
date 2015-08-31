package targetRepresentation;

import java.util.ArrayList;
import java.util.List;

public class Cartesian {

	private List<Point> board;

	public Cartesian(){
		init();
	}

	public void init() {
		board = new ArrayList<Point>();
		board.add(new Point(0.0, 8.0));
		board.add(new Point(1.0, 5.0));
		board.add(new Point(2.0, 3.0));
		board.add(new Point(3.0, 7.0));
		board.add(new Point(4.0, 10.0));
	}

	public List<Point> getBoard() {
		return board;
	}

	public void setBoard(List<Point> board) {
		this.board = board;
	}


}
