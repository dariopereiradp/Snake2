package iul.ista.pi.snake;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MenuEvent;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;

public class Snake extends JFrame implements Observer {

	private static final long serialVersionUID = 4304994289171574723L;
	private static final int DELAY_TIME = 5000;
	private static Snake INSTANCE;
	private Board board;
	private JLabel pontos, temperatura;
	private Temperatura temp;

	private Snake() {
		initUI();
	}

	private void initUI() {
		
		setTitle("Snake");
		setLocation(300, 100);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				dispose();
				StartMenu.getInstance().open();
			}
		});

		board = Board.getINSTANCE();
		board.addObserver(this);
		temp = new Temperatura();

		getContentPane().setLayout(new BorderLayout(0, 0));

		getContentPane().add(board.getPanel(), BorderLayout.CENTER);

		JPanel panel_info = new JPanel();
		getContentPane().add(panel_info, BorderLayout.NORTH);
		panel_info.setLayout(new BorderLayout(0, 0));

		JPanel panel_pontuacao = new JPanel();
		panel_info.add(panel_pontuacao, BorderLayout.WEST);

		JLabel lblPontuao = new JLabel("Pontua\u00E7\u00E3o: ");
		panel_pontuacao.add(lblPontuao);

		pontos = new JLabel("0         ");
		panel_pontuacao.add(pontos);

		JPanel panel_temperatura = new JPanel();
		panel_info.add(panel_temperatura, BorderLayout.EAST);

		JLabel lblTemperatura = new JLabel("Temperatura:");
		panel_temperatura.add(lblTemperatura);

		temperatura = new JLabel();
		panel_temperatura.add(temperatura);

		ActionListener taskPerformer = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (board.isInGame() && !board.isPause()) {
					temp = new Temperatura();
					temperatura.setText(temp.toString());
					board.setTemperatura(temp.getValor());
				}
			}
		};

		JLabel lblc = new JLabel("\u00BAC");
		panel_temperatura.add(lblc);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);

		JMenuItem mntmReiniciar = new JMenuItem("Reiniciar");
		mntmReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				board.restart();
			}
		});
		mnMenu.add(mntmReiniciar);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				StartMenu.getInstance().open();
			}
		});
		mnMenu.add(mntmSair);

		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);

		JMenuItem m_regras = new JMenuItem("Regras");
		m_regras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board.getTimer().stop();
				JOptionPane.showMessageDialog(Snake.this, "                ---------------Snake---------------                \n"
						+ "Usa as teclas de direção para mover a serpente pelo cenário.\n"
						+ "Atenção: não podes bater nas rochas! Cuidado também para não bateres a cabeça\n"
						+ "no corpo da cobra. A cada 3 vezes que comes, a probabilidade de aparecer um inimigo\n"
						+ "aumenta! Cuidado para a cabeça da cobra não embater no inimigo, que anda à volta da\n"
						+ "comida! A temperatura altera a cada 5 segundos. Quanto mais quente, mais depressa a\n"
						+ "cobra move-se. Tem atenção a isso! A temperatura pode subir ou descer!\n"
						+ "Podem aparecer diferentes tipos de comida, cada uma com uma pontuação diferente.\n"
						+ "Quanto maior a pontuação da comida, menor probabilidade de ela aparecer!\n"
						+ "-> Pera: 1 ponto;\n"
						+ "-> Maçã: 3 pontos;\n"
						+ "-> Cereja: 6 pontos;\n"
						+ "-> Ruby: 10 pontos;\n"
						+ "\nDiverte-te e tem um bom jogo!");
				board.getTimer().start();
			}
		});
		mnAjuda.add(m_regras);

		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				board.getTimer().stop();
				new Sobre(Snake.this).open();
				board.getTimer().start();
			}
		});

		JMenuItem mHistogram = new JMenuItem("Testes - Histogramas");
		mHistogram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				board.pausar();
				HistogramTests frame = new HistogramTests();
				frame.setVisible(true);
			}
		});
		mnAjuda.add(mHistogram);
		mnAjuda.add(mntmSobre);
		setResizable(false);
		pack();

		Timer timer = new Timer(DELAY_TIME, taskPerformer);
		timer.setInitialDelay(0);
		timer.restart();

		mnMenu.addMenuListener(new MenuListenerTimer());
		mnAjuda.addMenuListener(new MenuListenerTimer());
	}

	public JLabel getPontos() {
		return pontos;
	}

	public JLabel getTemperatura() {
		return temperatura;
	}

	public static Snake getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Snake();
		}
		return INSTANCE;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof Integer) {
			pontos.setText(String.valueOf(arg1));
		} else if (arg1 instanceof Double) {
			temperatura.setText(String.valueOf(arg1));
		}
	}

	private class MenuListenerTimer implements javax.swing.event.MenuListener {

		@Override
		public void menuCanceled(MenuEvent e) {

		}

		@Override
		public void menuDeselected(MenuEvent e) {
			Board.getINSTANCE().getTimer().start();

		}

		@Override
		public void menuSelected(MenuEvent e) {
			Board.getINSTANCE().getTimer().stop();

		}
	}
	
	public void open(){
		JOptionPane.showMessageDialog(this, "Pressiona 'OK' para iniciar!");
		setVisible(true);
	}
}