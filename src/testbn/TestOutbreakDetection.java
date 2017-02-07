package testbn;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;

import norsys.netica.Environ;
import norsys.netica.Net;
import norsys.netica.Node;
import norsys.netica.Streamer;

public class TestOutbreakDetection {
	public static void main(String[] args) {
		System.out.println("Bayesian Network Testing");
		String modelDir = args[0];
		String testDir = args[1];
		double[] lstRange_w0 = { 0.0, 24.7, 26.7, 28.58, 31.58, 39 };
		double[] slopeRange = { 0.0, 9.189, 13.58, 16.96, 25.2 };
		// double[] borderRange = { 0.0, 3858.78, 7828.425, 12692, 21000 };
		double[] borderRange = { 0.0, 3858.78, 7828.425, 12692 };
		double[] streamDistRange = { 0.0, 300.842, 589.1185, 1464.6,
				2061.863700 };
		double[] streamDensityRange = { 0.0, 0.22, 0.28, 0.38, 0.59 };
		try {
			Environ environ = new Environ("+HaddawyP/AIT/120,310-5-A/11987");
			Net net = new Net(new Streamer(modelDir));

			// Nodes that are required to set data
			Node LST_wm5 = net.getNode("LST_wm5");
			Node LST_wm4 = net.getNode("LST_wm4");
			Node LST_wm3 = net.getNode("LST_wm3");
			Node LST_wm2 = net.getNode("LST_wm2");
			Node LST_wm1 = net.getNode("LST_wm1");
			Node LST_w0 = net.getNode("LST_w0");

			Node Malaria_w0 = net.getNode("outbreak_w0");
			Node Malaria_w1 = net.getNode("outbreak_w1");
			Node Malaria_w2 = net.getNode("outbreak_w2");
			Node Malaria_w3 = net.getNode("outbreak_w3");
			Node Malaria_w4 = net.getNode("outbreak_w4");
			// Node Malaria_w5 = net.getNode("outbreak_w5");
			// Node Malaria_w6 = net.getNode("outbreak_w6");

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

						int dist_to_strm_state = getGlobalState(
								Double.valueOf(dataWithNoSeparator[1]),
								streamDistRange);
						int dist_to_brdr_state = getGlobalState(
								Double.valueOf(dataWithNoSeparator[2]),
								borderRange);
						int slope_state = getGlobalState(
								Double.valueOf(dataWithNoSeparator[3]),
								slopeRange);
						int strmDensty_state = getGlobalState(
								Double.valueOf(dataWithNoSeparator[4]),
								streamDensityRange);

						String malaria_0_state = dataWithNoSeparator[5];

						int lst_wm5_state = getGlobalState(
								Double.valueOf(dataWithNoSeparator[6]),
								lstRange_w0);

						int lst_wm4_state = getGlobalState(
								Double.valueOf(dataWithNoSeparator[8]),
								lstRange_w0);

						int lst_wm3_state = getGlobalState(
								Double.valueOf(dataWithNoSeparator[10]),
								lstRange_w0);

						int lst_wm2_state = getGlobalState(
								Double.valueOf(dataWithNoSeparator[12]),
								lstRange_w0);

						// int lst_wm1_state = getGlobalState(
						// 		Double.valueOf(dataWithNoSeparator[14]),
						// 		lstRange_w0);

						// int lst_w0_state = getGlobalState(
						// 		Double.valueOf(dataWithNoSeparator[16]),
						// 		lstRange_w0);

						// Malaria Actual
						String malaria_1_actual = dataWithNoSeparator[7];
						String malaria_2_actual = dataWithNoSeparator[9];
						String malaria_3_actual = dataWithNoSeparator[11];
						String malaria_4_actual = dataWithNoSeparator[13];
						// String malaria_5_actual = dataWithNoSeparator[15];
						// String malaria_6_actual = dataWithNoSeparator[17];

						// Entering the evidence
						dist_to_stream.finding().enterState(dist_to_strm_state);
						dist_to_border.finding().enterState(dist_to_brdr_state);
						slope.finding().enterState(slope_state);
						strm_density.finding().enterState(strmDensty_state);

						LST_wm5.finding().enterState(lst_wm5_state);
						LST_wm4.finding().enterState(lst_wm4_state);
						LST_wm3.finding().enterState(lst_wm3_state);
						LST_wm2.finding().enterState(lst_wm2_state);
						LST_wm1.finding().enterState(lst_wm1_state);
						LST_w0.finding().enterState(lst_w0_state);
						Malaria_w0.finding().enterState(malaria_0_state);

						System.out.println(Malaria_w1.getBelief("yes") + ","
								+ malaria_1_actual + ","
								+ Malaria_w2.getBelief("yes") + ","
								+ malaria_2_actual + ","
								+ Malaria_w3.getBelief("yes") + ","
								+ malaria_3_actual + ","
								+ Malaria_w4.getBelief("yes") + ","
								+ malaria_4_actual);

						// System.out.println(Malaria_w1.getBelief("yes") + ","
						// 		+ malaria_1_actual + ","
						// 		+ Malaria_w2.getBelief("yes") + ","
						// 		+ malaria_2_actual + ","
						// 		+ Malaria_w3.getBelief("yes") + ","
						// 		+ malaria_3_actual + ","
						// 		+ Malaria_w4.getBelief("yes") + ","
						// 		+ malaria_4_actual + ","
						// 		+ Malaria_w5.getBelief("yes") + ","
						// 		+ malaria_5_actual + ","
						// 		+ Malaria_w6.getBelief("yes") + ","
						// 		+ malaria_6_actual);

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

			} catch (FileNotFoundException exception) {
				exception.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

	public static double formatDouble(double expected) {
		return Double.parseDouble(new DecimalFormat("##.##########")
				.format(expected));
	}

}
