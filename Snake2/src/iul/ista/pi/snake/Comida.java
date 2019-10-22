package iul.ista.pi.snake;

public class Comida {

	private Food_Type type;

	private int x;
	private int y;

	public Comida() {
		genaratePosition();
		generateType();
	}

	public void generateType() {
		double u = Math.random();
		if (u <= 0.05)
			type = Food_Type.RUBY;
		else if (u > 0.05 && u <= 0.25)
			type = Food_Type.CHERRY;
		else if (u > 0.25 && u <= 0.55)
			type = Food_Type.RED_APPLE;
		else if (u > 0.55 && u <= 1)
			type = Food_Type.PEAR;
	}

	public void genaratePosition() {
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

	public Food_Type getType() {
		return type;
	}
}
