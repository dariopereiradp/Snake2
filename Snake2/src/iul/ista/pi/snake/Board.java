package iul.ista.pi.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends Observable implements ActionListener {

	public static final int B_WIDTH = 400;
	public static final int B_HEIGHT = 400;
	public static final int DOT_SIZE = 20;
	public static final int ALL_DOTS = 1600;
	public static final int RAND_POS = 19;
	private boolean pause = false;
	private int DELAY = 100;

	private final int x[] = new int[ALL_DOTS];
	private final int y[] = new int[ALL_DOTS];
	private ArrayList<Parede> paredes;

	private Enemy inimigo;
	private int nParedes;
	private JPanel panel;
	private int dots;
	private int pontos;
	private double temperatura;
	private int contador_num_comidas;

	private boolean leftDirection = false;
	private boolean rightDirection = true;
	private boolean upDirection = false;
	private boolean downDirection = false;
	private boolean inGame = true;
	private boolean move_keys = true;

	private Timer timer;
	private Image ball;
	private Comida food;
	private Image head;

	private static Board INSTANCE;

	private Board() {
		INSTANCE = this;
		panel = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -4753510838643245080L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				doDrawing(g);
			}
		};
		panel.addKeyListener(new TAdapter());
		panel.setBackground(Color.BLACK);
		panel.setFocusable(true);

		panel.setMinimumSize(new Dimension(B_WIDTH, B_HEIGHT));
		panel.setMaximumSize(new Dimension(B_WIDTH, B_HEIGHT));
		panel.setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		restart();
	}

	public void restart() {
		DELAY = 100;
		pontos = 0;
		contador_num_comidas = 0;
		Enemy.reset();
		inimigo = null;
		pause = false;
		setChanged();
		notifyObservers(pontos);
		paredes = new ArrayList<>();
		nParedes = Parede.generateNParedes();
		for (int i = 0; i < nParedes; i++) {
			paredes.add(new Parede());
		}
		food = new Comida();
		do {
			food.genaratePosition();
		} while (hasParede(food.getX(), food.getY()));
		initBoard();
		inGame = true;
	}

	private void initBoard() {

		loadImages();
		initGame();
	}

	private void loadImages() {

		ImageIcon iid =  new ImageIcon(getClass().getClassLoader().getResource("dot.png"));
		ball = iid.getImage();

		ImageIcon iih = new ImageIcon(getClass().getClassLoader().getResource("head.png"));
		head = iih.getImage();
	}

	private void initGame() {

		leftDirection = false;
		rightDirection = true;
		upDirection = false;
		downDirection = false;

		dots = 3;

		for (int z = 0; z < dots; z++) {
			x[z] = 60 - z * 20;
			y[z] = 60;
		}

		if (Enemy.geraInimigo()) {
			inimigo = new Enemy(food);
		}

		if (timer != null)
			timer.stop();
		timer = new Timer(DELAY, this);
		timer.start();
	}

	private void doDrawing(Graphics g) {

		if (inGame) {

			g.drawImage(food.getType().getImage(), food.getX(), food.getY(), panel);

			if (nParedes != 0) {
				for (int i = 0; i < nParedes; i++) {
					Parede parede = paredes.get(i);
					g.drawImage(parede.getImg(), parede.getX(), parede.getY(), panel);

				}
			}

			if (inimigo != null) {
				inimigo.move();
				g.drawImage(inimigo.getImg(), inimigo.getX(), inimigo.getY(), panel);
			}

			for (int z = 0; z < dots; z++) {
				if (z == 0) {
					g.drawImage(head, x[z], y[z], panel);
				} else {
					g.drawImage(ball, x[z], y[z], panel);
				}
			}

			Toolkit.getDefaultToolkit().sync();

		} else {

			gameOver(g);
		}
	}

	private void gameOver(Graphics g) {
		String msg = "Game Over";
		String msgPontos = "Pontuacao: " + pontos;
		String msgRestart = "Press 'r' to restart";
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metr = panel.getFontMetrics(small);

		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2 - 20);
		g.drawString(msgPontos, (B_WIDTH - metr.stringWidth(msgPontos)) / 2, B_HEIGHT / 2);
		g.drawString(msgRestart, (B_WIDTH - metr.stringWidth(msgRestart)) / 2, B_HEIGHT / 2 + 20);
		timer.stop();
	}

	private void checkApple() {

		if ((x[0] == food.getX()) && (y[0] == food.getY())) {
			contador_num_comidas++;
			System.out.println("Numero de comidas: " + contador_num_comidas);
			if (contador_num_comidas % 3 == 0) {
				Enemy.change_probabilidades();
			}
			dots++;
			pontos += food.getType().getPontos();
			setChanged();
			notifyObservers(new Integer(pontos));
			food.generateType();
			do {
				food.genaratePosition();
			} while (hasParede(food.getX(), food.getY()));

			if (Enemy.geraInimigo()) {
				inimigo = new Enemy(food);
			} else {
				inimigo = null;
			}
		}
	}

	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}

	public boolean isInGame() {
		return inGame;
	}

	private void move() {
		timer.stop();
		DELAY = 200 - (int) (temperatura * 4);
		timer = new Timer(DELAY, this);
		timer.start();

		for (int z = dots; z > 0; z--) {
			x[z] = x[(z - 1)];
			y[z] = y[(z - 1)];
		}

		if (leftDirection) {
			x[0] -= DOT_SIZE;
		}

		if (rightDirection) {
			x[0] += DOT_SIZE;
		}

		if (upDirection) {
			y[0] -= DOT_SIZE;
		}

		if (downDirection) {
			y[0] += DOT_SIZE;
		}

		if (!move_keys) {
			move_keys = true;
		}

	}

	private void checkCollision() {

		for (int z = dots; z > 0; z--) {

			if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
				inGame = false;
			}
		}

		if (inimigo != null && (x[0] == inimigo.getX() && y[0] == inimigo.getY()))
			inGame = false;

		for (int i = 0; i < nParedes; i++) {
			Parede parede = paredes.get(i);
			if (x[0] == parede.getX() && y[0] == parede.getY())
				inGame = false;
		}

		if (y[0] > (B_HEIGHT - 20)) {
			y[0] = 0;
			move_keys = false;
		}

		if (y[0] < 0) {
			y[0] = B_HEIGHT - 20;
			move_keys = false;
		}

		if (x[0] > (B_WIDTH - 20)) {
			x[0] = 0;
			move_keys = false;
		}

		if (x[0] < 0) {
			x[0] = B_WIDTH - 20;
			move_keys = false;
		}

		if (!inGame) {
			timer.stop();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (inGame) {

			checkApple();
			checkCollision();
			move();
		}

		panel.repaint();
	}

	private class TAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (move_keys) {
				if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
					leftDirection = true;
					upDirection = false;
					downDirection = false;
				}

				if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
					rightDirection = true;
					upDirection = false;
					downDirection = false;
				}

				if ((key == KeyEvent.VK_UP) && (!downDirection)) {
					upDirection = true;
					rightDirection = false;
					leftDirection = false;
				}

				if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
					downDirection = true;
					rightDirection = false;
					leftDirection = false;
				}
			}

			if (key == KeyEvent.VK_R)
				restart();

			if (key == KeyEvent.VK_P)
				pauseAndPlay();
		}
	}

	public Timer getTimer() {
		return timer;
	}

	public void pauseAndPlay() {
		System.out.println(pause);
		if (!pause) {
			getTimer().stop();
			pause = true;
		} else {
			getTimer().start();
			pause = false;
		}

	}

	public JPanel getPanel() {
		return panel;
	}

	public boolean isPause() {
		return pause;
	}

	public boolean hasParede(int x, int y) {
		for (int i = 0; i < nParedes; i++) {
			if (paredes.get(i).getX() == x && paredes.get(i).getY() == y)
				return true;
		}
		return false;
	}

	public static Board getINSTANCE() {
		if (INSTANCE == null)
			new Board();
		return INSTANCE;
	}
	
	public int getContador_num_comidas() {
		return contador_num_comidas;
	}
}