package iul.ista.pi.snake;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;

public class Snake extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4304994289171574723L;
	private static Snake INSTANCE;
	private Board board;
	private JLabel pontos, temperatura;

	private Snake() {

		initUI();
	}

	private void initUI() {

		board = new Board();
		board.addObserver(this);

		getContentPane().setLayout(new BorderLayout(0, 0));

		getContentPane().add(board.getPanel());

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

		temperatura = new JLabel("00");
		panel_temperatura.add(temperatura);

		JLabel lblc = new JLabel("\u00BAC");
		panel_temperatura.add(lblc);

		setResizable(false);
		pack();

		setTitle("Snake");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);

		JMenuItem mntmOpes = new JMenuItem("Op\u00E7\u00F5es");
		mnMenu.add(mntmOpes);

		JMenuItem mntmReiniciar = new JMenuItem("Reiniciar");
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
		}

	}
}