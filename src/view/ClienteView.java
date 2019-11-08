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

public class ClienteView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteView frame = new ClienteView();
					frame.setVisible(true);
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
		setBounds(100, 100, 502, 215);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(38, 61, 48, 14);
		contentPane.add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(38, 86, 48, 14);
		contentPane.add(lblCpf);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setBounds(38, 111, 122, 14);
		contentPane.add(lblDataDeNascimento);
		
		JLabel lblSaldo = new JLabel("Saldo:");
		lblSaldo.setBounds(38, 136, 48, 14);
		contentPane.add(lblSaldo);
		
		JLabel lbNomeCliente = new JLabel("----");
		lbNomeCliente.setBounds(96, 61, 48, 14);
		contentPane.add(lbNomeCliente);
		
		JLabel lbCPFCliente = new JLabel("----");
		lbCPFCliente.setBounds(96, 86, 48, 14);
		contentPane.add(lbCPFCliente);
		
		JLabel lbDataNascClient = new JLabel("----");
		lbDataNascClient.setBounds(170, 111, 48, 14);
		contentPane.add(lbDataNascClient);
		
		JLabel lbSaldoClient = new JLabel("----");
		lbSaldoClient.setBounds(96, 136, 48, 14);
		contentPane.add(lbSaldoClient);
		
		JLabel lblNumeroDoCarto = new JLabel("Numero do Cart\u00E3o:");
		lblNumeroDoCarto.setBounds(38, 36, 131, 14);
		contentPane.add(lblNumeroDoCarto);
		
		JLabel lbCartaoCliente = new JLabel("----");
		lbCartaoCliente.setBounds(173, 36, 48, 14);
		contentPane.add(lbCartaoCliente);
		
		JLabel lblCarto = new JLabel("Cart\u00E3o ");
		lblCarto.setBounds(209, 11, 131, 14);
		contentPane.add(lblCarto);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setBackground(new Color(255, 255, 255));
		btnSair.setBounds(387, 136, 89, 23);
		contentPane.add(btnSair);
	}
}
