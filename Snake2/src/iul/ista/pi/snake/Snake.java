package iul.ista.pi.snake;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Snake extends JFrame implements Observer {

	private static final long serialVersionUID = 4304994289171574723L;
	private static Snake INSTANCE;
	private Board board;
	private JLabel pontos, temperatura;
	private Temperatura temp;

	private Snake() {
		initUI();
	}

	private void initUI() {

		board = new Board();
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
				if(board.isInGame() && !board.isPause()) {
					temp = new Temperatura();
					temperatura.setText(temp.toString());
					board.setTemperatura(temp.getValor());
				}
			}};
			
		Timer timer= new Timer(10000,taskPerformer);
		timer.setInitialDelay(0);
		timer.restart();
		JLabel lblc = new JLabel("\u00BAC");
		panel_temperatura.add(lblc);

		setTitle("Snake");
//		setLocationRelativeTo(null);
		setLocation(300, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);

		JMenuItem mntmOpes = new JMenuItem("Op\u00E7\u00F5es");
		mnMenu.add(mntmOpes);

		JMenuItem mntmReiniciar = new JMenuItem("Reiniciar");
		mntmReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				board.restart();
			}
		});
		mnMenu.add(mntmReiniciar);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mnMenu.add(mntmSair);

		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);

		JMenuItem m_regras = new JMenuItem("Regras");
		mnAjuda.add(m_regras);

		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				board.getTimer().stop();
				new Sobre(Snake.this).open();
				board.getTimer().start();
			}
		});
		mnAjuda.add(mntmSobre);
		setResizable(false);
		pack();
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

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
			JFrame ex = new Snake();
			ex.setVisible(true);
		});
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof Integer) {
			pontos.setText(String.valueOf(arg1));
		} else if (arg1 instanceof Double) {
			temperatura.setText(String.valueOf(arg1));
		}

	}
}