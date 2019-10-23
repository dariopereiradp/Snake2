package iul.ista.pi.snake;

import java.text.DecimalFormat;
import java.lang.Math;

public class Temperatura {
	private static final int media = 25;
	private static final int sigma = 5;
	private double valor;

	public Temperatura() {
		this.valor = generateTemp();
		}

	public double generateTemp(){
		double p1,p2,p;
		do{
			p1 = -1 + (1+1)* Math.random();
			p2 = -1 + (1+1)* Math.random();
			p = p1*p1 + p2*p2;
		}while(p >= 1);
		return media + sigma * p1 * Math.sqrt(-2 * Math.log(p) /p);
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
