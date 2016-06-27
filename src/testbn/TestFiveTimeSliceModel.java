package testbn;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import norsys.netica.Environ;
import norsys.netica.Net;
import norsys.netica.NeticaException;
import norsys.netica.Node;
import norsys.netica.Streamer;

public class TestFiveTimeSliceModel {
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

		int[][] confMat_w3 = new int[][] {
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

		int[][] confMat_w4 = new int[][] {
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

		double[] rainfallRange_w0 = { 0.0, 0.122649, 3.321, 14.333, 34.1386,
				156.7519884 };
		double[] rainfallRange_wm1 = { 0.0, 0.122649, 3.321, 14.333, 34.1386,
				156.7519884 };
		double[] rainfallRange_wm2 = { 0.0, 0.122649, 3.321, 14.333, 34.1386,
				156.7519884 };

		double[] lstRange_w0 = { 0.0, 24.6, 26.6, 28.3, 31.43, 39 };

		double[] slopeRange = { 0.0, 9.189, 13.58, 16.96, 25.2 };
		double[] borderRange = { 0.0, 3858.78, 7828.425, 12692, 21000 };
		double[] streamDistRange = { 0.0, 300.842, 589.1185, 1464.6,
				2061.863700 };
		double[] streamDensityRange = { 0.0, 0.22, 0.28, 0.38, 0.59 };
		double[] ndviRange_w0 = { 0.38, 0.62, 0.71, 0.77, 0.83,
				0.9300000000000001 };

		double[] malaria_w0 = { 0, 0.5, 1.5, 2.5, 5.5, 6.5, 7.5, 8.5, 9.5,
				10.5, 11.5, 13, 16.5, 82.09999999999999 };
		double[] malaria_w1 = { 0.0, 0.98, 1.5, 2.5, 5.5, 6.5, 7.5, 8.5, 9.5,
				10.5, 11.5, 13, 16.5, 30, 82.09999999999999 };
		double[] malaria_w2 = { 0.0, 0.98, 1.5, 2.5, 5.5, 6.5, 7.5, 8.5, 9.5,
				10.5, 11.5, 13, 16.5, 30, 82.09999999999999 };
		double[] malaria_w3 = { 0.0, 0.98, 1.5, 2.5, 5.5, 6.5, 7.5, 8.5, 9.5,
				10.5, 11.5, 13, 16.5, 30, 82.09999999999999 };
		double[] malaria_w4 = { 0.0, 0.98, 1.5, 2.5, 5.5, 6.5, 7.5, 8.5, 9.5,
				10.5, 11.5, 13, 16.5, 30, 82.09999999999999 };

		double absoluteError_w1 = 0.0;
		double absoluteError_w2 = 0.0;
		double absoluteError_w3 = 0.0;
		double absoluteError_w4 = 0.0;

		try {
			Environ environ = new Environ("+HaddawyP/AIT/120,310-5-A/11987");
			Net net = new Net(new Streamer(modelDir));

			// Nodes that are required to set data
			Node Month_w0 = net.getNode("Month_w0");
			Node Month_w1 = net.getNode("Month_w1");
			Node Month_w2 = net.getNode("Month_w2");
			Node Month_w3 = net.getNode("Month_w3");
			Node Month_w4 = net.getNode("Month_w4");

			Node LST_w0 = net.getNode("LST_w0");
			Node LST_w1 = net.getNode("LST_w1");
			Node LST_w2 = net.getNode("LST_w2");
			Node LST_w3 = net.getNode("LST_w3");
			Node LST_w4 = net.getNode("LST_w4");

			Node NDVI_w0 = net.getNode("NDVI_w0");
			Node NDVI_w1 = net.getNode("NDVI_w1");
			Node NDVI_w2 = net.getNode("NDVI_w2");
			Node NDVI_w3 = net.getNode("NDVI_w3");
			Node NDVI_w4 = net.getNode("NDVI_w4");

			Node rainfall_w0 = net.getNode("Rainfall_w0");
			Node rainfall_w1 = net.getNode("Rainfall_w1");
			Node rainfall_w2 = net.getNode("Rainfall_w2");
			Node rainfall_w3 = net.getNode("Rainfall_w3");
			Node rainfall_wm1 = net.getNode("Rainfall_wm1");
			Node rainfall_wm2 = net.getNode("Rainfall_wm2");

			Node Malaria_w0 = net.getNode("incidents_w0");
			Node Malaria_w1 = net.getNode("incidents_w1");
			Node Malaria_w2 = net.getNode("incidents_w2");
			Node Malaria_w3 = net.getNode("incidents_w3");
			Node Malaria_w4 = net.getNode("incidents_w4");

			Node slope = net.getNode("Slope");
			Node dist_to_stream = net.getNode("STRM_DIS");
			Node dist_to_border = net.getNode("BOR_DIS");
			Node strm_density = net.getNode("StreamDensity");

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
								.valueOf(dataWithNoSeparator[0]) - 1;
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
								Double.valueOf(dataWithNoSeparator[5]),
								streamDensityRange);
						int rainfall_0_state = getGlobalState(
								Double.valueOf(dataWithNoSeparator[6]),
								rainfallRange_w0);
						int ndvi_0_state = getGlobalState(
								Double.valueOf(dataWithNoSeparator[7]),
								ndviRange_w0);
						int malaria_0_state = getGlobalState(
								Double.valueOf(dataWithNoSeparator[8]),
								malaria_w0);
						int lst_0_state = getGlobalState(
								Double.valueOf(dataWithNoSeparator[9]),
								lstRange_w0);
						int month_1_state = Integer
								.valueOf(dataWithNoSeparator[10]) - 1;
						int month_2_state = Integer
								.valueOf(dataWithNoSeparator[15]) - 1;
						int month_3_state = Integer
								.valueOf(dataWithNoSeparator[20]) - 1;
						int month_4_state = Integer
								.valueOf(dataWithNoSeparator[25]) - 1;

						int rainfallwm1_state = getGlobalState(
								Double.valueOf(dataWithNoSeparator[29]),
								rainfallRange_wm1);
						int rainfallwm2_state = getGlobalState(
								Double.valueOf(dataWithNoSeparator[30]),
								rainfallRange_wm2);
						// Malaria Actual
						double malaria_1_actual = Double
								.valueOf(dataWithNoSeparator[14]);
						double malaria_2_actual = Double
								.valueOf(dataWithNoSeparator[19]);
						double malaria_3_actual = Double
								.valueOf(dataWithNoSeparator[24]);
						double malaria_4_actual = Double
								.valueOf(dataWithNoSeparator[31]);

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
						Month_w3.finding().enterState(month_3_state);
						Month_w4.finding().enterState(month_4_state);
						rainfall_wm1.finding().enterState(rainfallwm1_state);
						rainfall_wm2.finding().enterState(rainfallwm2_state);

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
						// System.out.println(expected_w1);

						double expected_w2 = 0.0;
						expected_w2 = getExpectedValue(Malaria_w2, malaria_w2);
						absoluteError_w2 = absoluteError_w2
								+ Math.abs(expected_w2 - malaria_2_actual);
						int predictedState_w2 = getGlobalState(expected_w2,
								malaria_w2);
						int actualState_w2 = getGlobalState(malaria_2_actual,
								malaria_w2);
						confMat_w2[actualState_w2][predictedState_w2] = confMat_w2[actualState_w2][predictedState_w2] + 1;

						double expected_w3 = 0.0;
						expected_w3 = getExpectedValue(Malaria_w3, malaria_w3);
						absoluteError_w3 = absoluteError_w3
								+ Math.abs(expected_w3 - malaria_3_actual);
						int predictedState_w3 = getGlobalState(expected_w3,
								malaria_w3);
						int actualState_w3 = getGlobalState(malaria_3_actual,
								malaria_w3);
						confMat_w3[actualState_w3][predictedState_w3] = confMat_w3[actualState_w3][predictedState_w3] + 1;

						double expected_w4 = 0.0;
						expected_w4 = getExpectedValue(Malaria_w4, malaria_w4);
						absoluteError_w4 = absoluteError_w4
								+ Math.abs(expected_w4 - malaria_4_actual);
						int predictedState_w4 = getGlobalState(expected_w4,
								malaria_w4);
						int actualState_w4 = getGlobalState(malaria_4_actual,
								malaria_w4);
						confMat_w4[actualState_w4][predictedState_w4] = confMat_w4[actualState_w4][predictedState_w4] + 1;

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

				printTestReport(Malaria_w1, confMat_w1, absoluteError_w1, i);
				printTestReport(Malaria_w2, confMat_w2, absoluteError_w2, i);
				printTestReport(Malaria_w3, confMat_w3, absoluteError_w3, i);
				printTestReport(Malaria_w4, confMat_w4, absoluteError_w4, i);

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
			} else {
				if (data >= dataRange[i] && data <= dataRange[i]) {
					state = i - 1;
				}
			}
		}
		return state;
	}

	public static void printTestReport(Node n1, int[][] confusionMatrix,
			double absoluteError, int i) throws NeticaException {
		System.out.println("Test Report for " + n1.getName());
		// printConfusionMatrix(confusionMatrix, n1);
		System.out.println("Absolute Error: " + absoluteError);
		System.out.println("Mean Absolute Error: " + (absoluteError / (i - 1)));
		System.out.println("Error Rate: "
				+ getErrorRate(confusionMatrix, i - 1));
		System.out.println("\n");
	}
}
