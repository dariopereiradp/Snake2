package iul.ista.pi.snake;

import java.text.DecimalFormat;
import java.util.Random;

public class Temperatura {
	private static final int media = 25;
	private static final int sigma = 5;
	private double valor;

	public Temperatura() {
		Random r = new Random();
	    this.valor = r.nextGaussian()*sigma + media;
		
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
