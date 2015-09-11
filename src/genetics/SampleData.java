package genetics;

import java.util.ArrayList;
import java.util.List;

public class SampleData {
	public static final int[] sampleX = { -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	public static final int[] sampleY = { 100, 81, 64, 49, 36, 25, 16, 9, 4, 1, 0, 1, 4, 9, 16, 25, 36, 49, 64, 81,
			100 };

	public static ArrayList<Integer> dataX;
	public static List<Integer> dataY;
	public static List<Double> dataF;

	// Sigmoidal growth curves from Ratkowsky (1983).
	// public static final double[] sampleX = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
	// 11, 12, 13, 14, 15 };

	// public static final double[] sampleY = { 16.08, 33.83, 65.8, 97.2,
	// 191.55, 326.20, 386.87, 520.53, 590.03, 651.92,
	// 724.93, 699.56, 689.96, 637.56, 717.41 };

	public SampleData() {
		dataX = new ArrayList<>();
		dataY = new ArrayList<>();
		dataF = new ArrayList<>();

		for(int i = -10; i < 11; i++) {
			dataX.add(i);
			dataY.add(i * i);
		}
	}
}
