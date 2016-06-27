package testbn;

import java.util.ArrayList;

import norsys.netica.Environ;
import norsys.netica.Net;
import norsys.netica.Node;
import norsys.netica.NodeList;
import norsys.netica.Sensitivity;
import norsys.netica.Streamer;

public class Entropy {

	public static void main(String[] args) {
		String modelDir = args[0];
		try {
			Environ environ = new Environ("+HaddawyP/AIT/120,310-5-A/11987");
			Net net = new Net(new Streamer(modelDir));
			Node Month_w0 = net.getNode("Month_w0");
			Node Month_w1 = net.getNode("Month_w1");
			Node Month_w2 = net.getNode("Month_w2");
			Node LST_w0 = net.getNode("LST_w0");
			Node LST_w1 = net.getNode("LST_w1");
			Node LST_w2 = net.getNode("LST_w2");
			Node NDVI_w0 = net.getNode("NDVI_w0");
			Node NDVI_w1 = net.getNode("NDVI_w1");
			Node NDVI_w2 = net.getNode("NDVI_w2");
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
			Node slope = net.getNode("Slope");
			Node dist_to_stream = net.getNode("STRM_DIS");
			Node dist_to_border = net.getNode("BOR_DIS");
			Node strm_density = net.getNode("StreamDensity");
			Node arima_w1 = net.getNode("arima_w1");
			Node arima_w2 = net.getNode("arima_w2");
			Node incidents_rate_w1 = net.getNode("incidents_rate_w1");
			Node rainfall_effect_w1 = net.getNode("Rainfall_Effect_w1");
			Node rainfall_effect_w2 = net.getNode("Rainfall_Effect_w2");
			Node incidence_w1 = net.getNode("incidenets_w1");
			Node mosq_pop_density_w1 = net.getNode("Mosquito_pop_density_w1");
			Node mosq_pop_density_w2 = net.getNode("Mosquito_pop_density_w2");
			Node stream_effect = net.getNode("Stream_Effect");
			net.compile();

			NodeList nodeList = new NodeList(net);
			// for (Object o : net.getNodes()) {
			// Node n = (Node) o;
			// nodeList.add(n);
			// }
			// nodeList.add(dist_to_border);
			// nodeList.add(LST_w1);
			// nodeList.add(mosq_pop_density_w1);
			// nodeList.add(NDVI_w1);
			// nodeList.add(stream_effect);
			nodeList.add(rainfall_effect_w1);
			Sensitivity sensitivity_entr = new Sensitivity(Malaria_w1,
					nodeList, Sensitivity.ENTROPY_SENSV);
			Sensitivity sensitivity_variance = new Sensitivity(Malaria_w1,
					nodeList, Sensitivity.VARIANCE_OF_REAL_SENSV);
			double[] malaria_w0 = { 0, 0.5, 1.5, 2.5, 5.5, 6.5, 7.5, 8.5, 9.5,
					10.5, 11.5, 13, 16.5, 82.09999999999999 };
			double midPoints[] = getMidpoints(malaria_w0);
			System.out.println("Node Name" + " , " + "State" + " , "
					+ "W0_midpoint" + " , " + "Reduced Entropy");
			for (int i = 0; i < Malaria_w0.getNumStates(); i++) {
				Malaria_w0.finding().setState(i);
				ArrayList<Node> varyingNodeList = new ArrayList<Node>();
				varyingNodeList.addAll(nodeList);

				System.out.println(nodeList.getNode(0).getName() + " , " + "s"
						+ i + " , " + midPoints[i] + " , "
						+ sensitivity_entr.getMutualInfo(nodeList.getNode(0)));

			}

			// for (Node n : varyingNodeList) {
			// System.out.println(n.getName() + " : "
			// + sensitivity_entr.getMutualInfo(n) + ","
			// + sensitivity_variance.getVarianceOfReal(n));
			// }
			sensitivity_entr.finalize();
			sensitivity_variance.finalize();
			net.finalize();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static double[] getMidpoints(double[] binRanges) {
		double[] midPoints = new double[binRanges.length];
		for (int i = 0; i < binRanges.length - 1; i++) {
			midPoints[i] = (binRanges[i] + binRanges[i + 1]) / 2;
		}
		return midPoints;
	}
}
