package iul.ista.pi.snake;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Parede {

	private Image img;

	private int x;
	private int y;

	public Parede() {
		generatePosition();
		img = new ImageIcon(getClass().getClassLoader().getResource("wall.png")).getImage();
	}

	public Image getImg() {
		return img;
	}

	public static int generateNParedes() {
		int nParedes = 0 + (int) (Math.random() * ((9 - 0) + 1));
		return nParedes;
	}

	public void generatePosition() {
		x = round20((int) generateRandomPosition());
		y = round20((int) generateRandomPosition());
	}

	public static double generateRandomPosition() {
		double u = Math.random();
		if (u <= 0.4)
			return 0 + (Math.random() * ((50 - 0) + 1));
		else {
			if (u <= 0.8)
				return 350 + (Math.random() * ((400 - 350) + 1));
			else
				return 50 + (Math.random() * (350 - 50) + 1);
		}
	}

	public static Integer round20(Integer b) {
		return b - (b % Board.DOT_SIZE);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public static double[] getHistogramDataPos() {
		double[] data = new double[10000000];
		for (int i = 0; i < 10000000; i++) {
			data[i] = generateRandomPosition();
		}
		return data;
	}

	public static double[] getHistogramDataNumber() {
		double[] data = new double[1000000];
		for (int i = 0; i < 1000000; i++) {
			data[i] = (double) generateNParedes();
		}
		return data;
	}

}
