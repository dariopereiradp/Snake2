package iul.ista.pi.snake;

public enum Food_Type {
	
	GREEN_APPLE (1, 0.45), RED_APPLE (3, 0.3), CHERRY (6, 0.2), RUBY (15, 0.05);
	
	/**
	 * Número de pontos que cada fruta vai dar, se for comida
	 */
	private final int pontos;
	private final double probabilidade;
	
	private Food_Type (int pontos, double probabilidade){
		this.pontos = pontos;
		this.probabilidade = probabilidade;
	}
	
	public int getPontos() {
		return pontos;
	}
	
	public double getProbabilidade() {
		return probabilidade;
	}

}
