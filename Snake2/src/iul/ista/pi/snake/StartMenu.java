package iul.ista.pi.snake;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartMenu {
	
	private static StartMenu INSTANCE;
	private JFrame frame;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			EventQueue.invokeLater(() -> {
				new StartMenu();
			});
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	private StartMenu(){
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.getContentPane().setLayout(null);
		frame.setSize(300, 200);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel lblSnakeV = new JLabel("Snake - v2.0");
		lblSnakeV.setForeground(Color.WHITE);
		lblSnakeV.setFont(new Font("Agency FB", Font.BOLD, 16));
		lblSnakeV.setHorizontalAlignment(SwingConstants.CENTER);
		lblSnakeV.setBounds(110, 11, 80, 20);
		frame.getContentPane().add(lblSnakeV);
		
		JButton btnJogar = new JButton("JOGAR");
		btnJogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Snake ex = Snake.getInstance();
				ex.open();
				frame.dispose();
			}
		});
		btnJogar.setBounds(90, 60, 120, 30);
		frame.getContentPane().add(btnJogar);
		
		JButton bHistogramas = new JButton("HISTOGRAMAS");
		bHistogramas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HistogramTests ht = new HistogramTests();
				ht.setVisible(true);
				frame.dispose();
			}
		});
		bHistogramas.setBounds(90, 100, 120, 30);
		frame.getContentPane().add(bHistogramas);
		
		frame.setVisible(true);
	}
	
	public static StartMenu getInstance(){
		if(INSTANCE==null)
			INSTANCE = new StartMenu();
		return INSTANCE;
	}
	
	public void open(){
		frame.setVisible(true);
	}
}
