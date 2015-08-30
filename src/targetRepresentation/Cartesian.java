package targetRepresentation;

import java.util.ArrayList;
import java.util.List;

public class Cartesian {

	List<Point> board;

	public void init() {
		board = new ArrayList<Point>();
		board.add(new Point(0, 8));
		board.add(new Point(1, 5));
		board.add(new Point(2, 3));
		board.add(new Point(3, 7));
		board.add(new Point(4, 10));
	}

	public List<Point> getBoard() {
		return board;
	}

	public void setBoard(List<Point> board) {
		this.board = board;
	}


}
