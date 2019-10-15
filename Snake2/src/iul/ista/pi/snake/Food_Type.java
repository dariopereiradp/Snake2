package iul.ista.pi.snake;

import java.awt.Image;

import javax.swing.ImageIcon;

public enum Food_Type {

	GREEN_APPLE(1, 0.45, new ImageIcon("resources/apple.png").getImage()), RED_APPLE(3, 0.3,
			new ImageIcon("resources/apple-r.png").getImage()), CHERRY(6, 0.2,
					new ImageIcon("resources/cherry.png").getImage()), RUBY(10, 0.05,
							new ImageIcon("resources/ruby.png").getImage());

	/**
	 * Número de pontos que cada fruta vai dar, se for comida
	 */
	private final int pontos;
	private final double probabilidade;
	private final Image image;

	private Food_Type(int pontos, double probabilidade, Image image) {
		this.pontos = pontos;
		this.probabilidade = probabilidade;
		this.image = image;
	}

	public int getPontos() {
		return pontos;
	}

	public double getProbabilidade() {
		return probabilidade;
	}
	
	public Image getImage() {
		return image;
	}

}
