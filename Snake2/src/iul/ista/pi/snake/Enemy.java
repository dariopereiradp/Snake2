package iul.ista.pi.snake;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Enemy {

	private static final int DESLOCAMENTO = 20;
	private static final int NUM_MOVIMENTOS = 4;

	private Image img = new ImageIcon("resources/enemy.png").getImage();

	private int x;
	private int y;
	private boolean move_x_cima = false;
	private boolean move_y_esquerda = false;
	private boolean move_x_baixo = true;
	private boolean move_y_direita = true;
	private int contador_direcoes;
	private int contador_movimentos;

	public Enemy(Comida c) {
		this.x = c.getX() - DESLOCAMENTO;
		this.y = c.getY() - DESLOCAMENTO;
	}

	public void move() {
		if (contador_movimentos == 4) {
			contador_movimentos=0;
			if ((contador_direcoes + 1) == 4)
				contador_direcoes = 0;
			else
				contador_direcoes++;
		}
		contador_movimentos++;
		Direcao direcao = Direcao.values()[contador_direcoes];
		switch (direcao) {
		case BAIXO:
			this.x+=10;
			break;
		case DIREITA:
			this.y+=10;
			break;
		case CIMA:
			this.x-=10;
			break;
		default:
			this.y-=10;
			break;
		}
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

}
