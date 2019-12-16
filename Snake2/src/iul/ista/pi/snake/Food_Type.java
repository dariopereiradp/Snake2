package iul.ista.pi.snake;

import java.awt.Image;

import javax.swing.ImageIcon;

public enum Food_Type {

	PEAR(1, 0.45, "pear.png"),
	RED_APPLE(3, 0.3, "apple-r.png"),
	CHERRY(6, 0.2, "cherry.png"),
	RUBY(10, 0.05, "ruby.png");

	/**
	 * Número de pontos que cada fruta vai dar, se for comida
	 */
	private final int pontos;
	private final double probabilidade;
	private final String image;

	private Food_Type(int pontos, double probabilidade, String image) {
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
		return new ImageIcon(getClass().getClassLoader().getResource(image)).getImage();
	}

}
