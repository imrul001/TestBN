package testbn;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import norsys.netica.Environ;
import norsys.netica.Net;
import norsys.netica.NeticaException;
import norsys.netica.Node;
import norsys.netica.Streamer;

public class SensitivityAnalysis {

	public static void main(String[] args) {
		// System.out.println("Sensitivity Analysis of Bayesian Network");
		String modelDir = args[0];
		try {
			Environ environ = new Environ("+HaddawyP/AIT/120,310-5-A/11987");
			Net net = new Net(new Streamer(modelDir));
			Node LST_wm5 = net.getNode("LST_wm5");
			Node LST_wm4 = net.getNode("LST_wm4");
			Node LST_wm3 = net.getNode("LST_wm3");
			Node LST_wm2 = net.getNode("LST_wm2");
			Node LST_wm1 = net.getNode("LST_wm1");
			Node LST_w0 = net.getNode("LST_w0");
			Node slope = net.getNode("Slope");
			Node dist_to_stream = net.getNode("STRM_DIS");
			Node dist_to_border = net.getNode("BOR_DIS");
			Node strm_density = net.getNode("StreamDensity");
			Node Malaria_w0 = net.getNode("incidents_w0");
			Node Malaria_w1 = net.getNode("incidents_w1");
			Node Malaria_w2 = net.getNode("incidents_w2");
			Node Malaria_w3 = net.getNode("incidents_w3");
			Node Malaria_w4 = net.getNode("incidents_w4");
			Node Malaria_w5 = net.getNode("incidents_w5");
			Node Malaria_w6 = net.getNode("incidents_w6");
			Node mosq_pop_density_w1 = net.getNode("Mosquito_pop_density_w1");
			Node mosq_pop_density_w2 = net.getNode("Mosquito_pop_density_w2");
			Node mosq_pop_density_w3 = net.getNode("Mosquito_pop_density_w3");
			Node mosq_pop_density_w4 = net.getNode("Mosquito_pop_density_w4");
			Node mosq_pop_density_w5 = net.getNode("Mosquito_pop_density_w5");
			Node mosq_pop_density_w6 = net.getNode("Mosquito_pop_density_w6");
			Node stream_effect = net.getNode("Stream_Effect");
			net.compile();

			ArrayList<Node> nodeList = new ArrayList<Node>();
			// nodeList.add(rainfall_wm2);
			nodeList.add(slope);
			// nodeList.add(dist_to_border);
			// nodeList.add(Month_w1);
			// nodeList.add(dist_to_stream);
			// nodeList.add(strm_density);
			// nodeList.add(LST_w1);
			// nodeList.add(stream_effect);
			// nodeList.add(NDVI_w1);
			// nodeList.add(NDVI_w0);
			// nodeList.add(LST_wm5);
			// nodeList.add(LST_w0);
			// nodeList.add(mosq_pop_density_w1);
			// nodeList.add(rainfall_w0);
			// nodeList.add(rainfall_effect_w1);
			nodeList.add(Malaria_w0);

			SensitivityAnalysis.runSensitivityAnalysis(nodeList, Malaria_w6,
					net);
			// SensitivityAnalysis.runAnalysisOnHidden(nodeList, stream_effect,
			// net);
			// System.out.println("Inside from Try block");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// rainfall_effect_w1
	// s0 = medium
	// s1 = high
	// s2 = low

	// mos_pop_density
	// s0 = medium
	// s1 = low
	// s2 = high

	public static void runSensitivityAnalysis(ArrayList nodeList,
			Node targetVariable, Net net) throws NeticaException {
		ArrayList<String> states = new ArrayList<String>();
		double[] malaria_w1 = { 0.0, 0.98, 1.5, 2.5, 5.5, 6.5, 7.5, 8.5, 9.5,
				10.5, 11.5, 13, 16.5, 30, 82.09999999999999 };
		int[] index = new int[nodeList.size()];
		int i = 0;
		int prev = 0;
		for (Object o : nodeList) {
			Node n = (Node) o;
			double[] levels = n.getLevels();
			int numberOfStates = 0;
			if (n.getType() == 101) {
				numberOfStates = levels.length - 1;
				index[i] = prev + levels.length - 1;
			}
			if (n.getType() == 102) {
				numberOfStates = n.getBeliefs().length;
				index[i] = prev + numberOfStates;
			}
			String[] strList = assignNumberToEachState(i + 1, numberOfStates);
			states = assignArrayToList(states, strList);
			prev = index[i];
			i++;
		}
		List<List<String>> combinations = new ArrayList<List<String>>();
		if (nodeList.size() == 1) {
			combinations = SensitivityAnalysis.cartesianProduct(Arrays
					.asList(states.subList(0, index[0])));
		}
		if (nodeList.size() == 2) {
			combinations = SensitivityAnalysis.cartesianProduct(Arrays.asList(
					states.subList(0, index[0]),
					states.subList(index[0], index[1])));
		}
		if (nodeList.size() == 3) {
			combinations = SensitivityAnalysis.cartesianProduct(Arrays.asList(
					states.subList(0, index[0]),
					states.subList(index[0], index[1]),
					states.subList(index[1], index[2])));
		}
		if (nodeList.size() == 4) {
			combinations = SensitivityAnalysis.cartesianProduct(Arrays.asList(
					states.subList(0, index[0]),
					states.subList(index[0], index[1]),
					states.subList(index[1], index[2]),
					states.subList(index[2], index[3])));
		}
		double commonExpectedValue = getExpectedValue(targetVariable,
				malaria_w1);

		for (Object o : nodeList) {
			Node n = (Node) o;
			System.out.print(n.getName() + ",");
		}
		System.out.print("Default" + ",");
		System.out.println("Expected");

		int count = 0;
		for (List<String> strList : combinations) {
			for (String str : strList) {
				int nodeNumber = Integer.valueOf(str.substring(1, 2)) - 1;
				int stateNumber = Integer.valueOf(str.substring(3));
				Object obj = nodeList.get(nodeNumber);
				Node node = (Node) obj;
				node.finding().enterState(stateNumber);
				System.out.print("s" + stateNumber + ",");
			}
			net.compile();
			double updatedExpectedValue = getExpectedValue(targetVariable,
					malaria_w1);
			System.out.print(Double.parseDouble(new DecimalFormat("##.####")
					.format(commonExpectedValue)) + ",");
			System.out.println(Double.parseDouble(new DecimalFormat("##.####")
					.format(updatedExpectedValue)));
			for (Object n : net.getNodes()) {
				Node node = (Node) n;
				node.finding().clear();
			}
			count++;
		}
		System.out.println("Total Combination Count: " + count);
		System.out.println("Number of combinations :" + combinations.size());
	}

	public static void runAnalysisOnHidden(ArrayList nodeList,
			Node targetVariable, Net net) throws NeticaException {
		ArrayList<String> states = new ArrayList<String>();
		int[] index = new int[nodeList.size()];
		int i = 0;
		int prev = 0;
		for (Object o : nodeList) {
			Node n = (Node) o;
			double[] levels = n.getLevels();
			int numberOfStates = 0;
			if (n.getType() == 101) {
				numberOfStates = levels.length - 1;
				index[i] = prev + levels.length - 1;
			}
			if (n.getType() == 102) {
				numberOfStates = n.getBeliefs().length;
				index[i] = prev + numberOfStates;
			}
			String[] strList = assignNumberToEachState(i + 1, numberOfStates);
			states = assignArrayToList(states, strList);
			prev = index[i];
			i++;
		}
		List<List<String>> combinations = new ArrayList<List<String>>();
		if (nodeList.size() == 1) {
			combinations = SensitivityAnalysis.cartesianProduct(Arrays
					.asList(states.subList(0, index[0])));
		}
		if (nodeList.size() == 2) {
			combinations = SensitivityAnalysis.cartesianProduct(Arrays.asList(
					states.subList(0, index[0]),
					states.subList(index[0], index[1])));
		}
		if (nodeList.size() == 3) {
			combinations = SensitivityAnalysis.cartesianProduct(Arrays.asList(
					states.subList(0, index[0]),
					states.subList(index[0], index[1]),
					states.subList(index[1], index[2])));
		}
		if (nodeList.size() == 4) {
			combinations = SensitivityAnalysis.cartesianProduct(Arrays.asList(
					states.subList(0, index[0]),
					states.subList(index[0], index[1]),
					states.subList(index[1], index[2]),
					states.subList(index[2], index[3])));
		}
		float[] commonExpectedValue = targetVariable.getBeliefs();

		for (Object o : nodeList) {
			Node n = (Node) o;
			System.out.print(n.getName() + ",");
		}
		System.out.print("low, medium, high,");
		System.out.println("low, medium, high");

		int count = 0;
		for (List<String> strList : combinations) {
			for (String str : strList) {
				int nodeNumber = Integer.valueOf(str.substring(1, 2)) - 1;
				int stateNumber = Integer.valueOf(str.substring(3));
				Object obj = nodeList.get(nodeNumber);
				Node node = (Node) obj;
				node.finding().enterState(stateNumber);
				System.out.print("s" + stateNumber + ",");
			}
			net.compile();
			float[] updatedExpectedValue = targetVariable.getBeliefs();
			printArrayByComma(commonExpectedValue);
			System.out.print(",");
			printArrayByComma(updatedExpectedValue);
			System.out.println(" ");
			for (Object n : net.getNodes()) {
				Node node = (Node) n;
				node.finding().clear();
			}
			count++;
		}
		System.out.println("Total Combination Count: " + count);

	}

	public static String[] assignNumberToEachState(int nodeNumber,
			int numberOfState) {
		String[] str = new String[numberOfState];
		for (int i = 0; i <= numberOfState - 1; i++) {
			str[i] = "n" + nodeNumber + "s" + i;
		}
		return str;
	}

	public static ArrayList<String> assignArrayToList(ArrayList<String> states,
			String[] strList) {
		for (String str : strList) {
			states.add(str);
		}
		return states;
	}

	protected static <T> List<List<T>> cartesianProduct(List<List<T>> lists) {
		List<List<T>> resultLists = new ArrayList<List<T>>();
		if (lists.size() == 0) {
			resultLists.add(new ArrayList<T>());
			return resultLists;
		} else {
			List<T> firstList = lists.get(0);
			List<List<T>> remainingLists = cartesianProduct(lists.subList(1,
					lists.size()));
			for (T condition : firstList) {
				for (List<T> remainingList : remainingLists) {
					ArrayList<T> resultList = new ArrayList<T>();
					resultList.add(condition);
					resultList.addAll(remainingList);
					resultLists.add(resultList);
				}
			}
		}
		return resultLists;
	}

	public static void printListElement(List<String> strList) {
		int i = 1;
		for (String str : strList) {
			System.out.println("element :" + i + " " + str);
			i++;
		}
	}

	public static void printArrayByComma(float[] array) {
		int i = 0;
		for (float ar : array) {
			if (i == array.length - 1) {
				System.out.print((ar * 100));
			} else {
				System.out.print((ar * 100) + ",");
			}
			i++;
		}
	}

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

}
