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
