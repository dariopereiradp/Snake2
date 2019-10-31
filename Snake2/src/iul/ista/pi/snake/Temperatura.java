package iul.ista.pi.snake;

import java.text.DecimalFormat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.*;

public class Temperatura {
	private static final int media = 25;
	private static final int sigma = 5;
	private double valor;

	public Temperatura() {
		this.valor = generateTemp();
	}

	public double generateTemp() {
		double u = Math.random();
		if (u < 0.025)
			return 15;
		else if (u > (1 - 0.025))
			return 35;
		else
			return generateTemp1();
	}

	public double generateTemp1() {
		double value = 0.0;
		do {
			double p1, p2, p;
			do {
				p1 = -1 + (1 + 1) * Math.random();
				p2 = -1 + (1 + 1) * Math.random();
				p = p1 * p1 + p2 * p2;
			} while (p >= 1);

			value = media + sigma * p1 * Math.sqrt(-2 * Math.log(p) / p);
		} while (value <= 15 || value >= 35);
		return value;

	}

	// public double generateTemp(){
	// double x = 0;
	// try {
	// String s = null;
	//// Process p = Runtime.getRuntime().exec("python3 tnormal.py 25 5 15 35");
	// Process p = Runtime.getRuntime().exec("python /c start python tnormal.py
	// 25 5 15 35");
	//
	// BufferedReader in = new BufferedReader(new
	// InputStreamReader(p.getInputStream()));
	// x = Double.parseDouble(in.readLine());
	// }
	// catch(IOException ie) {
	// ie.printStackTrace();
	// }
	// return x;
	// }

	public double getValor() {
		return valor;
	}

	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(valor);
	}

}
