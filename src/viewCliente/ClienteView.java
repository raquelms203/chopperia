package viewCliente;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ClienteView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JLabel lbNomeCliente;
	private JLabel lbCPFCliente;
	private JLabel lbDataNascClient;
	private JLabel lbSaldoClient;
	private JLabel lbCartaoCliente;

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
	
	
	
	public ClienteView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 502, 260);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(38, 90, 48, 14);
		contentPane.add(lblNome);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(38, 115, 48, 14);
		contentPane.add(lblCpf);

		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setBounds(38, 140, 122, 14);
		contentPane.add(lblDataDeNascimento);

		JLabel lblSaldo = new JLabel("Saldo:");
		lblSaldo.setBounds(38, 165, 48, 14);
		contentPane.add(lblSaldo);

		lbNomeCliente = new JLabel("----");
		lbNomeCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbNomeCliente.setBounds(96, 90, 215, 14);
		contentPane.add(lbNomeCliente);

		lbCPFCliente = new JLabel("----");
		lbCPFCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbCPFCliente.setBounds(96, 115, 215, 14);
		contentPane.add(lbCPFCliente);

		lbDataNascClient = new JLabel("----");
		lbDataNascClient.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbDataNascClient.setBounds(170, 140, 203, 14);
		contentPane.add(lbDataNascClient);

		lbSaldoClient = new JLabel("----");
		lbSaldoClient.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbSaldoClient.setBounds(96, 165, 277, 14);
		contentPane.add(lbSaldoClient);

		JLabel lblNumeroDoCarto = new JLabel("Numero do Cart\u00E3o:");
		lblNumeroDoCarto.setBounds(38, 65, 131, 14);
		contentPane.add(lblNumeroDoCarto);

		lbCartaoCliente = new JLabel("----");
		lbCartaoCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbCartaoCliente.setBounds(173, 65, 138, 14);
		contentPane.add(lbCartaoCliente);

		JLabel lblCarto = new JLabel("Visualiza\u00E7\u00E3o do Cart\u00E3o ");
		lblCarto.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCarto.setBounds(136, 11, 257, 23);
		contentPane.add(lblCarto);

		JButton btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSair.setForeground(new Color(0, 0, 0));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setBackground(new Color(255, 0, 0));
		btnSair.setBounds(338, 176, 138, 34);
		contentPane.add(btnSair);
		

	}

	public JLabel getLbNomeCliente() {
		return lbNomeCliente;
	}

	public void setLbNomeCliente(JLabel lbNomeCliente) {
		this.lbNomeCliente = lbNomeCliente;
	}

	public JLabel getLbCPFCliente() {
		return lbCPFCliente;
	}

	public void setLbCPFCliente(JLabel lbCPFCliente) {
		this.lbCPFCliente = lbCPFCliente;
	}

	public JLabel getLbDataNascClient() {
		return lbDataNascClient;
	}

	public void setLbDataNascClient(JLabel lbDataNascClient) {
		this.lbDataNascClient = lbDataNascClient;
	}

	public JLabel getLbSaldoClient() {
		return lbSaldoClient;
	}

	public void setLbSaldoClient(JLabel lbSaldoClient) {
		this.lbSaldoClient = lbSaldoClient;
	}

	public JLabel getLbCartaoCliente() {
		return lbCartaoCliente;
	}

	public void setLbCartaoCliente(JLabel lbCartaoCliente) {
		this.lbCartaoCliente = lbCartaoCliente;
	}
	
	
}

