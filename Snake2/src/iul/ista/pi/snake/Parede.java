package iul.ista.pi.snake;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Parede {
	
	private Image img = new ImageIcon("resources/wall.png").getImage();
	
	private int x;
	private int y;
	private int nParedes;
	
	public Parede() {
//		x = (int) Math.round();
//		y = (int) Math.round();
		nParedes =  -1 + (int)(Math.random() * ((9 + 1)  + 1));
		
	}
	
	public Image getImg() {
		return img;
	}	
	
	public int nParedes() {
		nParedes = 0 + (int)(Math.random() * ((9 - 0) + 1));
		return nParedes;
	}
	
	public int getX() {
//	para arredondar int a = (int) Math.round(doubleVar)
		return x;
	}
	
	public int getY() {
		return y;
	}


}
