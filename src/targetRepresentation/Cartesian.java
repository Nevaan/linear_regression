package targetRepresentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cartesian {

	//public static List<Point> board;

	public Cartesian() {
		init();
	}

	public static void init() {
		GPParameters.board = new ArrayList<Point>();

		// Function: 6x^3 + 2x^2 - 9x-7
		/*board.add(new Point(-5.0, -662.0));
		board.add(new Point(-3.0, -124.0));
		board.add(new Point(-1.0, -2.0));
		board.add(new Point(0.0, 7.0));
		board.add(new Point(1.0, 8.0));
		board.add(new Point(3.0, 146.0));
		board.add(new Point(5.0, 146.0));*/

		GPParameters.board.add(new Point(0.0, 3.0));
		GPParameters.board.add(new Point(3.0, 8.0));
		GPParameters.board.add(new Point(4.0, 6.0));
		GPParameters.board.add(new Point(7.0, 12.0));
		GPParameters.board.add(new Point(9.0, 9.0));
		GPParameters.board.add(new Point(13.0, 10.0));
		GPParameters.board.add(new Point(14.0, 17.0));
		GPParameters.board.add(new Point(17.0, 16.0));
		GPParameters.board.add(new Point(18.0, 20.0));
		GPParameters.board.add(new Point(10.0, 7.0));
		GPParameters.board.add(new Point(19.0, 18.0));

		GPParameters.board.add(new Point(21.0, 24.0));
		GPParameters.board.add(new Point(23.0, 22.0));
		GPParameters.board.add(new Point(25.0, 27.0));
		GPParameters.board.add(new Point(27.0, 19.0));
		GPParameters.board.add(new Point(26.0, 14.0));
		GPParameters.board.add(new Point(31.0, 40.0));
		GPParameters.board.add(new Point(33.0, 30.0));
		GPParameters.board.add(new Point(38.0, 25.0));
		GPParameters.board.add(new Point(22.0, 29.0));
		GPParameters.board.add(new Point(40.0, 40.0));
		GPParameters.board.add(new Point(35.0, 19.0));

		GPParameters.board.add(new Point(39.0, 7.0));
		GPParameters.board.add(new Point(43.0, 15.0));
		GPParameters.board.add(new Point(45.0, 13.0));
		GPParameters.board.add(new Point(47.0, 19.0));
		GPParameters.board.add(new Point(48.0, 10.0));
		GPParameters.board.add(new Point(50.0, 20.0));
		GPParameters.board.add(new Point(53.0, 6.0));
		GPParameters.board.add(new Point(54.0, 12.0));
		GPParameters.board.add(new Point(55.0, 16.0));
		GPParameters.board.add(new Point(59.0, 21.0));
		GPParameters.board.add(new Point(60.0, 23.0));

		GPParameters.board.add(new Point(62.0, 40.0));
		GPParameters.board.add(new Point(63.0, 37.0));
		GPParameters.board.add(new Point(65.0, 42.0));
		GPParameters.board.add(new Point(66.0, 55.0));
		GPParameters.board.add(new Point(69.0, 45.0));
		GPParameters.board.add(new Point(71.0, 50.0));
		GPParameters.board.add(new Point(73.0, 52.0));
		GPParameters.board.add(new Point(74.0, 57.0));
		GPParameters.board.add(new Point(77.0, 49.0));
		GPParameters.board.add(new Point(78.0, 45.0));
		GPParameters.board.add(new Point(79.0, 43.0));

		GPParameters.board.add(new Point(81.0, 7.0));
		GPParameters.board.add(new Point(83.0, 15.0));
		GPParameters.board.add(new Point(86.0, 13.0));
		GPParameters.board.add(new Point(87.0, 15.0));
		GPParameters.board.add(new Point(91.0, 5.0));
		GPParameters.board.add(new Point(94.0, 12.0));
		GPParameters.board.add(new Point(95.0, 11.0));
		GPParameters.board.add(new Point(96.0, 10.0));
		GPParameters.board.add(new Point(98.0, 14.0));
		GPParameters.board.add(new Point(90.0, 20.0));
		GPParameters.board.add(new Point(100.0, 10.0));




	}

	private static void randomFill() {
		Random r1 = new Random();
		Random r2 = new Random();
		for(int i = 0; i < 100; i++) {
			GPParameters.board.add(new Point((r1.nextInt(60)), (r2.nextInt(60))));
		}
	}

}
