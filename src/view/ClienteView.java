package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class ClienteView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| javax.swing.UnsupportedLookAndFeelException ex) {
			System.err.println(ex);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteView frame = new ClienteView();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public ClienteView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 502, 221);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(38, 76, 48, 14);
		contentPane.add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(38, 101, 48, 14);
		contentPane.add(lblCpf);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setBounds(38, 126, 122, 14);
		contentPane.add(lblDataDeNascimento);
		
		JLabel lblSaldo = new JLabel("Saldo:");
		lblSaldo.setBounds(38, 151, 48, 14);
		contentPane.add(lblSaldo);
		
		JLabel lbNomeCliente = new JLabel("----");
		lbNomeCliente.setBounds(96, 76, 48, 14);
		contentPane.add(lbNomeCliente);
		
		JLabel lbCPFCliente = new JLabel("----");
		lbCPFCliente.setBounds(96, 101, 48, 14);
		contentPane.add(lbCPFCliente);
		
		JLabel lbDataNascClient = new JLabel("----");
		lbDataNascClient.setBounds(170, 126, 48, 14);
		contentPane.add(lbDataNascClient);
		
		JLabel lbSaldoClient = new JLabel("----");
		lbSaldoClient.setBounds(96, 151, 48, 14);
		contentPane.add(lbSaldoClient);
		
		JLabel lblNumeroDoCarto = new JLabel("Numero do Cart\u00E3o:");
		lblNumeroDoCarto.setBounds(38, 51, 131, 14);
		contentPane.add(lblNumeroDoCarto);
		
		JLabel lbCartaoCliente = new JLabel("----");
		lbCartaoCliente.setBounds(173, 51, 48, 14);
		contentPane.add(lbCartaoCliente);
		
		JLabel lblCarto = new JLabel("Cart\u00E3o ");
		lblCarto.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCarto.setBounds(209, 11, 164, 23);
		contentPane.add(lblCarto);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setForeground(Color.WHITE);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setBackground(Color.RED);
		btnSair.setBounds(387, 147, 89, 23);
		contentPane.add(btnSair);
	}
}
