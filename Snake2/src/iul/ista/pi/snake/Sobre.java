package iul.ista.pi.snake;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.Desktop;

import javax.swing.JTextPane;

public class Sobre extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7715987917518715359L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public Sobre(JFrame frame) {
		super(frame, ModalityType.DOCUMENT_MODAL);
		setBounds(100, 100, 350, 300);
		setLocationRelativeTo(frame);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JLabel titulo = new JLabel("Snake v2.0");
		titulo.setFont(new Font("Dialog", Font.PLAIN, 15));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(titulo, BorderLayout.NORTH);

		JTextPane sobre = new JTextPane();
		sobre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sobre.setEditable(false);
		sobre.setContentType("text/html");
		sobre.setText(
				"Projeto de <a href=\"https://fenix.iscte-iul.pt/disciplinas/pi-ii-1/2019-2020/1-semestre\"> Processamento da Informa\u00E7\u00E3o</a>\r\n<br>1\u00BA Semestre de 2019/2020\r\n<br>3\u00BA ano do <a href=\"https://www.iscte-iul.pt/curso/3/licenciatura-engenharia-informatica\"> curso de Licenciatura em Engenharia Inform\u00E1tica</a> no <a href=\"https://www.iscte-iul.pt/\"> ISCTE-IUL</a>\r\n<br><br>\r\nFeito por:<br>D\u00E1rio Pereira - n\u00BA 82745<br>Jo\u00E3o Mendes - n\u00BA 82222<br>\r\nRodrigo Alves - n\u00BA 82270<br>\u00C9ric Sancha - n\u00BA 83404<br>\r\n<br>Turma: EIC1<br>\r\n<br>C\u00F3digo Fonte (GitHub): <a href=\"https://github.com/dariopereiradp/Snake2\">Snake2</a><br><br>Compat\u00EDvel com <a href=\"https://www.java.com/download\">Java 8</a><br>");
		

		sobre.addHyperlinkListener(new HyperlinkListener() {
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					if (Desktop.isDesktopSupported()) {
						try {
							Desktop.getDesktop().browse(e.getURL().toURI());
						} catch (IOException | URISyntaxException e1) {
							e1.printStackTrace();
						} catch (NullPointerException e2) {

						}
					}
				}
			}
		});

		JScrollPane jsp = new JScrollPane(sobre);
		contentPanel.add(jsp, BorderLayout.CENTER);
		// jsp.setViewportView(sobre);

		JLabel lblVerso = new JLabel("Vers\u00E3o 1.0 - 12 de Dezembro de 2019");
		lblVerso.setHorizontalAlignment(SwingConstants.CENTER);
		lblVerso.setFont(new Font("Dialog", Font.PLAIN, 13));
		contentPanel.add(lblVerso, BorderLayout.SOUTH);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				// okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	public void open(){
		setVisible(true);
	}
}
