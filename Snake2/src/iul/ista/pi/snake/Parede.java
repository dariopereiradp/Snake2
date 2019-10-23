package iul.ista.pi.snake;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Parede {

	private Image img = new ImageIcon("resources/wall.png").getImage();

	private int x;
	private int y;

	/*
	 * 1/125 *( 0 + (int) (Math.random() * ((50 - 0) + 1)) 1/1500 * ( 50 + (int)
	 * (Math.random() * ((350 - 50) + 1))) 1/125 * ( 350 + (int) (Math.random() *
	 * ((400 - 350) + 1)))
	 * 
	 * 
	 */

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
//		int r = (int) (Math.random() * Board.RAND_POS);
//		x = ((r * Board.DOT_SIZE));
		double a = 0.4 * (0 + (int) (Math.random() * ((50 - 0) + 1)))
				+ 0.2 * 1 / 1500 * (50 + (int) (Math.random() * ((350 - 50) + 1)))
				+ 0.4 * (350 + (int) (Math.random() * ((400 - 350) + 1)));
		
		x = (int) (a+0.5);
		//1/125 1/1500 1/125      1/125 * (u(x) - u(x-50)) + 1/1500 * (u(x-50)-u(x-350)) + 1/125 * (u(x-350) - u(x-400))
		a = 0.4 * (0 + (int) (Math.random() * ((50 - 0) + 1)))
				+ 0.2 * 1 / 1500 * (50 + (int) (Math.random() * ((350 - 50) + 1)))
				+ 0.4 * (350 + (int) (Math.random() * ((400 - 350) + 1)));
		
		y = (int) (a+0.5);
		

//		r = (int) (Math.random() * Board.RAND_POS);
//		y = ((r * Board.DOT_SIZE));

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
