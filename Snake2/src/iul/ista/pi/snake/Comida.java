package iul.ista.pi.snake;

public class Comida {

	private Food_Type type;

	private int x;
	private int y;

	public Comida() {
		genaratePosition();
		generateType();
	}

	private void generateType() {
		type = Food_Type.CHERRY;
		// TODO Auto-generated method stub

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
