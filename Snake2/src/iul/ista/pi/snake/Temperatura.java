package iul.ista.pi.snake;

import java.text.DecimalFormat;

public class Temperatura {
	private static final int media = 25;
	private static final int sigma = 5;
	private double valor;

	public Temperatura() {
		this.valor = generateTemp();
	}

	public static double generateTemp() {
		double u = Math.random();
		if (u < 0.025)
			return 15;
		else if (u > (1 - 0.025))
			return 35;
		else
			return generateTemp1();
	}

	public static double generateTemp1() {
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

	public static double[] getHistogramData() {
		double [] data = new double[1000000];
		for (int i = 0; i < 1000000; i++) {
			data[i] = generateTemp();
		}
		return data;
	}

	public double getValor() {
		return valor;
	}

	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(valor);
	}

}
