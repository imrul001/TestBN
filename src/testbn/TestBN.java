package testbn;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import norsys.netica.Environ;
import norsys.netica.Net;
import norsys.netica.NeticaException;
import norsys.netica.Node;
import norsys.netica.Streamer;

public class TestBN {

	public static void main(String[] args) {
		System.out.println("Bayesian Network Testing");
		String modelDir = args[0];
		String testDir = args[1];
		int[][] confMat_w1 = new int[][] {
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }

		};

		int[][] confMat_w2 = new int[][] {
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
		int[][] confMat_w3 = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

		// double[] monthRange = {0.0, 5.0, 8.0, 12.0};
		// double[] rainfallRange_w0 = {0.0, 9.810000000000001, 48.77, 89.98,
		// 161.17, 300};
		// double[] rainfallRange_w1 = {0.0, 9.810000000000001, 48.77, 89.98,
		// 161.17, 300};
		//
		// double[] lstRange_w0 = {17.4, 24.6, 26.6, 28.3, 31, 39};
		// double[] lstRange_w1 = {17.4, 24.6, 26.6, 28.3, 31, 39};
		// double[] lstRange_w2 = {17.4, 24.6, 26.6, 28.3, 31, 39};
		//
		// double[] slopeRange = {0, 4.19, 6.45, 12.001, 20.832};
		// double[] borderRange = {0, 666.697, 1394.72, 4407.6, 21000};
		// double[] streamDistRange = {0, 240, 500, 900, 1900, 3700};
		// double[] streamDensityRange = {0.07000000000000001, 0.21, 0.26, 0.32,
		// 0.4, 0.59};
		//
		// double[] ndviRange_w0 = {0.38, 0.62, 0.71, 0.77, 0.83,
		// 0.9300000000000001};
		// double[] ndviRange_w1 = {0.38, 0.62, 0.71, 0.77, 0.83,
		// 0.9300000000000001};
		// double[] ndviRange_w2 = {0.38, 0.62, 0.71, 0.77, 0.83,
		// 0.9300000000000001};
		//
		//
		// // double[] malaria_w0 = {0.0, 0, 1.5, 2.5, 3.5, 4.5, 5.5, 7.5, 8.5,
		// 9.5, 13.5, 18.5, 25.5, 30.5, 45, 297.1};
		// double[] malaria_w0 = {0, 6.5, 9.5, 12.5, 15.5, 18.5, 21.5, 24.5, 30,
		// 36.5, 46.5, 59.5, 97, 297};
		// double[] malaria_w1 = {0, 6.5, 9.5, 12.5, 15.5, 18.5, 21.5, 24.5, 30,
		// 36.5, 46.5, 59.5, 97, 150, 297};
		// //double[] malaria_w2 = {0.0, 0.98, 1.5, 2.5, 3.5, 4.5, 5.5, 7.5,
		// 9.5, 13.5, 18.5, 25.5, 297};
		//
		double[] monthRange = { 0.0, 5.0, 8.0, 12.0 };
		double[] rainfallRange_w0 = { 0.0, 0.122649, 3.321, 14.333, 34.1386,
				156.7519884 };
		double[] rainfallRange_wm1 = { 0.0, 0.122649, 3.321, 14.333, 34.1386,
				156.7519884 };
		double[] rainfallRange_wm2 = { 0.0, 0.122649, 3.321, 14.333, 34.1386,
				156.7519884 };

		double[] lstRange_w0 = { 0.0, 24.6, 26.6, 28.3, 31.43, 39 };
		double[] lstRange_w1 = { 0.0, 0.122649, 3.321, 14.333, 34.1386, 39 };
		double[] lstRange_w2 = { 0.0, 0.122649, 3.321, 14.333, 34.1386, 39 };

		// double[] slopeRange = {2, 7, 11, 16, 17.3, 25.2};
		// double[] borderRange = {300, 2500, 5800, 11000, 14000, 21000};
		// double[] streamDistRange = {0, 240, 500, 900, 1900, 3700};
		// double[] streamDensityRange = {0.07000000000000001, 0.21, 0.26, 0.32,
		// 0.4, 0.59};
		double[] slopeRange = { 0.0, 9.189, 13.58, 16.96, 25.2 };
		double[] borderRange = { 0.0, 3858.78, 7828.425, 12692, 21000 };
		double[] streamDistRange = { 0.0, 300.842, 589.1185, 1464.6,
				2061.863700 };
		double[] streamDensityRange = { 0.0, 0.22, 0.28, 0.38, 0.59 };

		double[] ndviRange_w0 = { 0.38, 0.62, 0.71, 0.77, 0.83,
				0.9300000000000001 };
		double[] ndviRange_w1 = { 0.38, 0.62, 0.71, 0.77, 0.83,
				0.9300000000000001 };
		double[] ndviRange_w2 = { 0.38, 0.62, 0.71, 0.77, 0.83,
				0.9300000000000001 };

		double[] arima_w1_range = { 0.0, 14.1661, 28.33, 42.49, 200 };

		double[] malaria_w0 = { 0, 0.5, 1.5, 2.5, 5.5, 6.5, 7.5, 8.5, 9.5,
				10.5, 11.5, 13, 16.5, 82.09999999999999 };
		double[] malaria_w1 = { 0.0, 0.98, 1.5, 2.5, 5.5, 6.5, 7.5, 8.5, 9.5,
				10.5, 11.5, 13, 16.5, 30, 82.09999999999999 };
		double[] malaria_w2 = { 0.0, 0.98, 1.5, 2.5, 5.5, 6.5, 7.5, 8.5, 9.5,
				10.5, 11.5, 13, 16.5, 30, 82.09999999999999 };
		// double[] malaria_w3 = {0.0,0.5,1.5,2.5,5.5,82};

		double absoluteError_w1 = 0.0;
		double absoluteError_w2 = 0.0;
		// double absoluteError_w3 = 0.0;

		try {
			Environ environ = new Environ("+HaddawyP/AIT/120,310-5-A/11987");
			Net net = new Net(new Streamer(modelDir));

			// Nodes that are required to set data
			Node Month_w0 = net.getNode("Month_w0");
			Node Month_w1 = net.getNode("Month_w1");
			Node Month_w2 = net.getNode("Month_w2");
			// Node Month_w3 = net.getNode("Month_m3");
			Node LST_w0 = net.getNode("LST_w0");
			Node LST_w1 = net.getNode("LST_w1");
			Node LST_w2 = net.getNode("LST_w2");
			// Node LST_w3 = net.getNode("LST_w3");
			Node NDVI_w0 = net.getNode("NDVI_w0");
			Node NDVI_w1 = net.getNode("NDVI_w1");
			Node NDVI_w2 = net.getNode("NDVI_w2");
			// Node NDVI_w3 = net.getNode("NDVI_w3");
			Node rainfall_w0 = net.getNode("Rainfall_w0");
			Node rainfall_w1 = net.getNode("Rainfall_w1");
			Node rainfall_w2 = net.getNode("Rainfall_w2");
			Node rainfall_w3 = net.getNode("Rainfall_w3");
			Node rainfall_wm1 = net.getNode("Rainfall_wm1");
			Node rainfall_wm2 = net.getNode("Rainfall_wm2");

			Node Malaria_w0 = net.getNode("incidents_w0");
			Node Malaria_w1 = net.getNode("incidents_w1");
			Node Malaria_w2 = net.getNode("incidents_w2");
			Node incidence_wm1 = net.getNode("incidents_wm1");
			// Node Malaria_w3 = net.getNode("Malaria_w3");
			Node slope = net.getNode("Slope");
			Node dist_to_stream = net.getNode("STRM_DIS");
			Node dist_to_border = net.getNode("BOR_DIS");
			Node strm_density = net.getNode("StreamDensity");
			Node arima_w1 = net.getNode("arima_w1");

			net.compile();

			// Reading the Test Case File
			String csvFile = testDir;
			BufferedReader br = null;
			String line = "";
			String csvSplitBy = ",";
			net.setAutoUpdate(0);

			int i = 0;
			try {
				br = new BufferedReader(new FileReader(csvFile));
				while ((line = br.readLine()) != null) {
					if (i != 0) {
						String[] dataWithNoSeparator = line.split(csvSplitBy);

						int month_0_state = Integer
								.valueOf(dataWithNoSeparator[1]) - 1;
						int dist_to_strm_state = getGlobalState(
								Double.valueOf(dataWithNoSeparator[2]),
								streamDistRange);
						int dist_to_brdr_state = getGlobalState(
								Double.valueOf(dataWithNoSeparator[3]),
								borderRange);
						int slope_state = getGlobalState(
								Double.valueOf(dataWithNoSeparator[4]),
								slopeRange);
						int strmDensty_state = getGlobalState(
								Double.valueOf(dataWithNoSeparator[6]),
								streamDensityRange);
						int rainfall_0_state = getGlobalState(
								Double.valueOf(dataWithNoSeparator[7]),
								rainfallRange_w0);
						int ndvi_0_state = getGlobalState(
								Double.valueOf(dataWithNoSeparator[8]),
								ndviRange_w0);
						int malaria_0_state = getGlobalState(
								Double.valueOf(dataWithNoSeparator[9]),
								malaria_w0);
						int lst_0_state = getGlobalState(
								Double.valueOf(dataWithNoSeparator[10]),
								lstRange_w0);
						int month_1_state = Integer
								.valueOf(dataWithNoSeparator[11]) - 1;
						int month_2_state = Integer
								.valueOf(dataWithNoSeparator[16]) - 1;
						int rainfallwm1_state = getGlobalState(
								Double.valueOf(dataWithNoSeparator[21]),
								rainfallRange_wm1);
						int rainfallwm2_state = getGlobalState(
								Double.valueOf(dataWithNoSeparator[22]),
								rainfallRange_wm2);
						// int arima_w1_state =
						// getGlobalState(Double.valueOf(dataWithNoSeparator[21]),
						// arima_w1_range);

						// Malaria Actual
						double malaria_1_actual = Double
								.valueOf(dataWithNoSeparator[15]);
						double malaria_2_actual = Double
								.valueOf(dataWithNoSeparator[20]);

						// Entering the evidence
						Month_w0.finding().enterState(month_0_state);
						dist_to_stream.finding().enterState(dist_to_strm_state);
						dist_to_border.finding().enterState(dist_to_brdr_state);
						slope.finding().enterState(slope_state);
						strm_density.finding().enterState(strmDensty_state);
						rainfall_w0.finding().enterState(rainfall_0_state);
						NDVI_w0.finding().enterState(ndvi_0_state);
						Malaria_w0.finding().enterState(malaria_0_state);
						LST_w0.finding().enterState(lst_0_state);
						Month_w1.finding().enterState(month_1_state);
						Month_w2.finding().enterState(month_2_state);
						rainfall_wm1.finding().enterState(rainfallwm1_state);
						rainfall_wm2.finding().enterState(rainfallwm2_state);
						// arima_w1.finding().enterState(arima_w1_state);

						// Getting the belief and printing them
						// System.out.println(Malaria_w1.getBeliefs()[0]+";"+Malaria_w1.getBeliefs()[1]+";"+Malaria_w1.getBeliefs()[2]+";"+Malaria_w1.getBeliefs()[3]+";"+Malaria_w1.getBeliefs()[4]+","+
						// Malaria_w2.getBeliefs()[0]+";"+Malaria_w2.getBeliefs()[1]+";"+Malaria_w2.getBeliefs()[2]+";"+Malaria_w2.getBeliefs()[3]+";"+Malaria_w2.getBeliefs()[4]+","+
						// Malaria_w3.getBeliefs()[0]+";"+Malaria_w3.getBeliefs()[1]+";"+Malaria_w3.getBeliefs()[2]+";"+Malaria_w3.getBeliefs()[3]+";"+Malaria_w3.getBeliefs()[4]);
						// Confusion Matrix and MAE calculation
						double expected_w1 = 0.0;
						expected_w1 = getExpectedValue(Malaria_w1, malaria_w1);
						absoluteError_w1 = absoluteError_w1
								+ Math.abs(expected_w1 - malaria_1_actual);
						int predictedState_w1 = getGlobalState(expected_w1,
								malaria_w1);
						int actualState_w1 = getGlobalState(malaria_1_actual,
								malaria_w1);
						confMat_w1[actualState_w1][predictedState_w1] = confMat_w1[actualState_w1][predictedState_w1] + 1;

						double expected_w2 = 0.0;
						expected_w2 = getExpectedValue(Malaria_w2, malaria_w2);
						absoluteError_w2 = absoluteError_w2
								+ Math.abs(expected_w2 - malaria_2_actual);
						int predictedState_w2 = getGlobalState(expected_w2,
								malaria_w2);
						int actualState_w2 = getGlobalState(malaria_2_actual,
								malaria_w2);
						confMat_w2[actualState_w2][predictedState_w2] = confMat_w2[actualState_w2][predictedState_w2] + 1;
						System.out.println(expected_w1);

						for (Object o : net.getNodes()) {
							Node n = (Node) o;
							n.finding().clear();
						}
						// all the nodes should be clear
					}
					i++;
				}
				net.setAutoUpdate(1);
				// print Confusion Matrix Here
				// bw.close();
				printConfusionMatrix(confMat_w1, Malaria_w1);
				System.out.println("Absolute Error: " + absoluteError_w1);
				System.out.println("Mean Absolute Error: "
						+ (absoluteError_w1 / (i - 1)));
				System.out.println("Error Rate: "
						+ getErrorRate(confMat_w1, i - 1));

				printConfusionMatrix(confMat_w2, Malaria_w2);
				System.out.println("Absolute Error: " + absoluteError_w2);
				System.out.println("Mean Absolute Error: "
						+ (absoluteError_w2 / (i - 1)));
				System.out.println("Error Rate: "
						+ getErrorRate(confMat_w2, i - 1));

			} catch (FileNotFoundException exception) {
				exception.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * two ways calculating confusion matrix 1. Midpoint-to-Midpoint 2.
	 * Expected-Value
	 * 
	 * We will be using Expected-Value Method to Calculate the Expected Value
	 */
	public static double getExpectedValue(Node malaria, double[] binRanges)
			throws NeticaException {
		double expected = 0.0;
		double[] midPoints = getMidpoints(binRanges);
		int numberOfStates = malaria.getNumStates();
		for (int i = 0; i < numberOfStates; i++) {
			expected = expected + (malaria.getBeliefs()[i] * midPoints[i]);
		}
		return expected;
	}

	// Method to get the mid point(Currently not using it)
	public static double[] getMidpoints(double[] binRanges) {
		// double[] midPoints = {0, 1.33, 3.71, 6.53, 22.92, 67};
		double[] midPoints = { 0.0, 1.0, 2.0, 3.79, 6.0, 7.0, 8.0, 9.0, 10.0,
				11.0, 12.32, 14.880, 22, 43.57 };
		// for(int i = 0; i < binRanges.length-1;i++){
		// midPoints[i] = (binRanges[i] + binRanges[i+1])/2;
		// }
		return midPoints;
	}

	// Method to calculate and print Confusion Matrix
	public static void printConfusionMatrix(int[][] confMat, Node malaria)
			throws NeticaException {
		System.out.println("Confusion Matrix for " + malaria.getName()
				+ " Node");
		System.out.println("\t\tPredicted");
		// System.out.println("\ts0\ts1\ts2\ts3\ts4");
		int numberStates = malaria.getNumStates();
		for (int j = 0; j < numberStates; j++) {
			System.out.print("\ts" + j);
		}
		System.out.println("\tActual");
		for (int i = 0; i < numberStates; i++) {
			for (int j = 0; j < numberStates; j++) {
				System.out.print("\t" + confMat[i][j]);
			}
			System.out.println("\ts" + i);
		}
		System.out.println("\n");
	}

	// Method to Calculate calculate Error Rate
	public static double getErrorRate(int[][] confmat, int totalCase) {
		double successRate = 0.0;
		for (int i = 0; i < confmat.length; i++) {
			successRate = successRate + confmat[i][i];
		}
		return (100 - ((successRate / totalCase) * 100));
	}

	// Method to get the states(in int format) by the ranges
	public static int getGlobalState(double data, double[] dataRange) {
		int state = 0;
		for (int i = 0; i < dataRange.length; i++) {
			if (i != dataRange.length - 1) {
				if (data >= dataRange[i] && data < dataRange[i + 1]) {
					state = i;
				}
			} else if (data >= dataRange[i] && data <= dataRange[i]) {
				state = i - 1;
			}
		}
		return state;
	}

}
