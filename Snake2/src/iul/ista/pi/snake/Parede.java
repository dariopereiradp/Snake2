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
		double u = Math.random();
		double k = Math.random();
//		int r = (int) (Math.random() * Board.RAND_POS);
//		x = ((r * Board.DOT_SIZE));
		if (u <= 0.4)
			x = round20( 0 + (int) (Math.random() * ((50 - 0) + 1)));
		else {
			if (u <= 0.8)
				x = round20(350 + (int) (Math.random() * ((400 - 350) + 1)));
			else
				x = round20(50 + (int) (Math.random() * (350 - 50) + 1));
		}

		if (k <= 0.4)
			y = round20(0 + (int) (Math.random() * ((50 - 0) + 1)));
		else {
			if (k <= 0.8)
				y = round20(350 + (int) (Math.random() * ((400 - 350) + 1)));
			else
				y = round20(50 + (int) (Math.random() * (350 - 50) + 1));
		}
		

//		if(k < 0.4)
//			y = 0 + (int) (Math.random() * ((50 - 0) + 1));
//			
//		
//		double a = 0.4 * (0 + (int) (Math.random() * ((50 - 0) + 1)))
//				+ 0.2 * 1 / 1500 * (50 + (int) (Math.random() * ((350 - 50) + 1)))
//				+ 0.4 * (350 + (int) (Math.random() * ((400 - 350) + 1)));
//		
//		x = (int) (a+0.5);
//		//1/125 1/1500 1/125      1/125 * (u(x) - u(x-50)) + 1/1500 * (u(x-50)-u(x-350)) + 1/125 * (u(x-350) - u(x-400))
//		a = 0.4 * (0 + (int) (Math.random() * ((50 - 0) + 1)))
//				+ 0.2 * 1 / 1500 * (50 + (int) (Math.random() * ((350 - 50) + 1)))
//				+ 0.4 * (350 + (int) (Math.random() * ((400 - 350) + 1)));
//		
//		y = (int) (a+0.5);

//		r = (int) (Math.random() * Board.RAND_POS);
//		y = ((r * Board.DOT_SIZE));

	}

	public static Integer round20(Integer b){
        return b - (b%Board.DOT_SIZE);
    }
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
