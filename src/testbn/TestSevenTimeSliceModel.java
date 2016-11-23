package testbn;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;

import norsys.netica.Environ;
import norsys.netica.Net;
import norsys.netica.NeticaException;
import norsys.netica.Node;
import norsys.netica.Streamer;

public class TestSevenTimeSliceModel {
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

		int[][] confMat_w5 = new int[][] {
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

		int[][] confMat_w6 = new int[][] {
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

		double[] rainfallRange_w0 = { 0.0, 0.021765, 3.538734, 14.587237,
				34.59804, 156.7519884 };

		double[] lstRange_w0 = { 0.0, 24.7, 26.7, 28.58, 31.58, 39 };

		double[] slopeRange = { 0.0, 9.189, 13.58, 16.96, 25.2 };
		double[] borderRange = { 0.0, 3858.78, 7828.425, 12692, 21000 };
		double[] streamDistRange = { 0.0, 300.842, 589.1185, 1464.6,
				2061.863700 };
		double[] streamDensityRange = { 0.0, 0.22, 0.28, 0.38, 0.59 };

		double[] ndviRange_w0 = { 0.0, 0.61835, 0.6994, 0.764883, 0.826117,
				0.9300000000000001 };

		// double[] malaria_w0 = { 0, 0.5, 1.5, 2.5, 5.5, 6.5, 7.5, 8.5, 9.5,
		// 10.5, 11.5, 13, 16.5, 82.09999999999999 };

		// double[] malaria_w1 = { 0.0, 0.98, 1.5, 2.5, 5.5, 6.5, 7.5, 8.5, 9.5,
		// 10.5, 11.5, 13, 16.5, 30, 82.09999999999999 };
		//
		// double[] malaria_w2 = { 0.0, 0.98, 1.5, 2.5, 5.5, 6.5, 7.5, 8.5, 9.5,
		// 10.5, 11.5, 13, 16.5, 30, 82.09999999999999 };
		//
		// double[] malaria_w3 = { 0.0, 0.98, 1.5, 2.5, 5.5, 6.5, 7.5, 8.5, 9.5,
		// 10.5, 11.5, 13, 16.5, 30, 82.09999999999999 };
		//
		// double[] malaria_w4 = { 0.0, 0.98, 1.5, 2.5, 5.5, 6.5, 7.5, 8.5, 9.5,
		// 10.5, 11.5, 13, 16.5, 30, 82.09999999999999 };

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
		double[] malaria_w5 = { 0.0, 0.98, 1.5, 2.5, 5.5, 6.5, 7.5, 8.5, 9.5,
				10.5, 11.5, 13, 16.5, 30, 82.09999999999999 };
		double[] malaria_w6 = { 0.0, 0.98, 1.5, 2.5, 5.5, 6.5, 7.5, 8.5, 9.5,
				10.5, 11.5, 13, 16.5, 30, 82.09999999999999 };

		double absoluteError_w1 = 0.0;
		double absoluteError_w2 = 0.0;
		double absoluteError_w3 = 0.0;
		double absoluteError_w4 = 0.0;
		double absoluteError_w5 = 0.0;
		double absoluteError_w6 = 0.0;

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

			// Node NDVI_wm7 = net.getNode("NDVI_wm7");
			// Node NDVI_wm6 = net.getNode("NDVI_wm6");
			// Node NDVI_wm5 = net.getNode("NDVI_wm5");
			// Node NDVI_wm4 = net.getNode("NDVI_wm4");

			// Node NDVI_wm1 = net.getNode("NDVI_wm1");
			// Node NDVI_w0 = net.getNode("NDVI_w0");
			// Node NDVI_w1 = net.getNode("NDVI_w1");
			// Node NDVI_w2 = net.getNode("NDVI_w2");

			// Node rainfall_wm6 = net.getNode("Rainfall_wm6");
			// Node rainfall_wm5 = net.getNode("Rainfall_wm5");
			// Node rainfall_wm4 = net.getNode("Rainfall_wm4");
			// Node rainfall_wm3 = net.getNode("Rainfall_wm3");

			Node Malaria_w0 = net.getNode("incidents_w0");
			Node Malaria_w1 = net.getNode("incidents_w1");
			Node Malaria_w2 = net.getNode("incidents_w2");
			Node Malaria_w3 = net.getNode("incidents_w3");
			Node Malaria_w4 = net.getNode("incidents_w4");
			Node Malaria_w5 = net.getNode("incidents_w5");
			Node Malaria_w6 = net.getNode("incidents_w6");

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

						int malaria_0_state = getGlobalState(
								Double.valueOf(dataWithNoSeparator[5]),
								malaria_w0);

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

						int lst_wm1_state = getGlobalState(
								Double.valueOf(dataWithNoSeparator[14]),
								lstRange_w0);

						int lst_w0_state = getGlobalState(
								Double.valueOf(dataWithNoSeparator[16]),
								lstRange_w0);

						// Malaria Actual
						double malaria_1_actual = Double
								.valueOf(dataWithNoSeparator[7]);
						double malaria_2_actual = Double
								.valueOf(dataWithNoSeparator[9]);
						double malaria_3_actual = Double
								.valueOf(dataWithNoSeparator[11]);
						double malaria_4_actual = Double
								.valueOf(dataWithNoSeparator[13]);
						double malaria_5_actual = Double
								.valueOf(dataWithNoSeparator[15]);
						double malaria_6_actual = Double
								.valueOf(dataWithNoSeparator[17]);

						// Entering the evidence
						dist_to_stream.finding().enterState(dist_to_strm_state);
						dist_to_border.finding().enterState(dist_to_brdr_state);
						slope.finding().enterState(slope_state);
						strm_density.finding().enterState(strmDensty_state);
						// rainfall_wm6.finding().enterState(rainfall_wm6_state);
						// rainfall_wm5.finding().enterState(rainfall_wm5_state);
						// rainfall_wm4.finding().enterState(rainfall_wm4_state);
						// rainfall_wm3.finding().enterState(rainfall_wm3_state);
						//
						// NDVI_wm7.finding().enterState(ndvi_wm7_state);
						// NDVI_wm6.finding().enterState(ndvi_wm6_state);
						// NDVI_wm5.finding().enterState(ndvi_wm5_state);
						// NDVI_wm4.finding().enterState(ndvi_wm4_state);

						LST_wm5.finding().enterState(lst_wm5_state);
						LST_wm4.finding().enterState(lst_wm4_state);
						LST_wm3.finding().enterState(lst_wm3_state);
						LST_wm2.finding().enterState(lst_wm2_state);
						LST_wm1.finding().enterState(lst_wm1_state);
						LST_w0.finding().enterState(lst_w0_state);
						Malaria_w0.finding().enterState(malaria_0_state);

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

						double expected_w5 = 0.0;
						expected_w5 = getExpectedValue(Malaria_w5, malaria_w5);
						absoluteError_w5 = absoluteError_w5
								+ Math.abs(expected_w5 - malaria_5_actual);
						int predictedState_w5 = getGlobalState(expected_w5,
								malaria_w5);
						int actualState_w5 = getGlobalState(malaria_5_actual,
								malaria_w5);
						confMat_w5[actualState_w5][predictedState_w5] = confMat_w5[actualState_w5][predictedState_w5] + 1;

						double expected_w6 = 0.0;
						expected_w6 = getExpectedValue(Malaria_w6, malaria_w6);
						absoluteError_w6 = absoluteError_w6
								+ Math.abs(expected_w6 - malaria_6_actual);
						int predictedState_w6 = getGlobalState(expected_w6,
								malaria_w6);
						int actualState_w6 = getGlobalState(malaria_6_actual,
								malaria_w6);
						confMat_w6[actualState_w6][predictedState_w6] = confMat_w6[actualState_w6][predictedState_w6] + 1;

						System.out.println(formatDouble(expected_w1) + ","
								+ malaria_1_actual + ","
								+ formatDouble(expected_w2) + ","
								+ malaria_2_actual + ","
								+ formatDouble(expected_w3) + ","
								+ malaria_3_actual + ","
								+ formatDouble(expected_w4) + ","
								+ malaria_4_actual + ","
								+ formatDouble(expected_w5) + ","
								+ malaria_5_actual + ","
								+ formatDouble(expected_w6) + ","
								+ malaria_6_actual);

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
				printTestReport(Malaria_w5, confMat_w5, absoluteError_w5, i);
				printTestReport(Malaria_w6, confMat_w6, absoluteError_w6, i);

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
		// previous
		// double[] midPoints = { 0.0, 1.0, 2.0, 3.79, 6.0, 7.0, 8.0, 9.0, 10.0,
		// 11.0, 12.32, 14.880, 22, 43.57 };
		// lastest best
		double[] midPoints = { 0.0, 1.0, 2.0, 3.79, 6.0, 7.0, 8.0, 9.0, 10.0,
				11.0, 12.32, 14.48, 21.98, 42.70 };
		// double[] midPoints = { 0.0, 1.0, 2.0, 3.79, 6.83, 9, 11.296, 18.254,
		// 35.82, 60.69 };
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
		System.out.println("Total Test Cases: " + (i - 1));
		System.out.println("Mean Absolute Error: " + (absoluteError / (i - 1)));
		System.out.println("Error Rate: "
				+ getErrorRate(confusionMatrix, i - 1));
		System.out.println("\n");
	}

	public static double formatDouble(double expected) {
		return Double.parseDouble(new DecimalFormat("##.##########")
				.format(expected));
	}

}
