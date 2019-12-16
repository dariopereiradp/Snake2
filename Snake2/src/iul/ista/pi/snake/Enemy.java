package iul.ista.pi.snake;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Enemy {

	private static final int DESLOCAMENTO = 20;
	private static final int NUM_MOVIMENTOS = 4;
	private static double probabilidade_sim = 0.1;

	private Image img;
	private int x;
	private int y;
	private int contador_direcoes;
	private int contador_movimentos;

	public Enemy(Comida c) {
		this.x = c.getX() - DESLOCAMENTO;
		this.y = c.getY() - DESLOCAMENTO;
		img = new ImageIcon(getClass().getClassLoader().getResource("enemy.png")).getImage();

	}

	public void move() {
		if (contador_movimentos == NUM_MOVIMENTOS) {
			contador_movimentos = 0;
			if ((contador_direcoes + 1) == NUM_MOVIMENTOS)
				contador_direcoes = 0;
			else
				contador_direcoes++;
		}
		contador_movimentos++;
		Direcao direcao = Direcao.values()[contador_direcoes];
		switch (direcao) {
		case BAIXO:
			this.x += 10;
			break;
		case DIREITA:
			this.y += 10;
			break;
		case CIMA:
			this.x -= 10;
			break;
		default:
			this.y -= 10;
			break;
		}
	}

	public static void change_probabilidades() {
		if (probabilidade_sim < 1)
			probabilidade_sim += 0.1;
		System.out.println("Probabilidade Inimigo: " + probabilidade_sim);
	}
	
	public static double getProbabilidade_sim() {
		return probabilidade_sim;
	}
	
	public static boolean geraInimigo() {
		double u = Math.random();
		if(u<=probabilidade_sim)
			return true;
		else
			return false;
	}
	
	public static void reset(){
		probabilidade_sim=0.1;
	}

	public Image getImg() {
		return img;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public static double[] getHistogramData() {
		double [] data = new double[1000000];
		for (int i = 0; i < 1000000; i++) {
			if(geraInimigo())
				data[i] = 1;
			else
				data[i] = 0;
		}
		return data;
	}

}
