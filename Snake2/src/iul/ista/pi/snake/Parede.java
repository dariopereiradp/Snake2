package iul.ista.pi.snake;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Parede {

	private Image img = new ImageIcon("resources/wall.png").getImage();

	private int x;
	private int y;

	public Parede() {
		generatePosition();
	}

	public Image getImg() {
		return img;
	}

	public static int generateNParedes() {
		int nParedes = 0 + (int) (Math.random() * ((9 - 0) + 1));
		return nParedes;
	}

	public void generatePosition() {
		int r = (int) (Math.random() * Board.RAND_POS);
		x = ((r * Board.DOT_SIZE));

		r = (int) (Math.random() * Board.RAND_POS);
		y = ((r * Board.DOT_SIZE));

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
