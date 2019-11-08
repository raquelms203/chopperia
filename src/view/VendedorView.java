package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;

public class VendedorView extends JFrame {
	private JTextField textNome;
	private JTextField textDataNascimento;
	private JTextField textCPF;
	private JTextField textCartao;
	private JTextField textSaldo;
	private JLabel lupa1;
	private JLabel lupa2;

	private JLabel lblCpf;
	private JLabel lblDataDeNascimento;
	private JLabel lblNome;
	private JLabel lblSaldo;

	private JPanel panelCliente;
	private JLabel lblNewLabel;
	private JButton btnBuscar;
	private JButton btnCadastrar;

	public static void main(String[] args) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
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
					VendedorView frame = new VendedorView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VendedorView() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 849, 483);

		panelCliente = new JPanel();
		panelCliente.setBackground(new Color(255, 255, 255));
		panelCliente.setBounds(311, 41, 492, 375);
		getContentPane().add(panelCliente);
		panelCliente.setLayout(null);

		lblNome = new JLabel("Nome");
		lblNome.setBounds(67, 63, 46, 14);
		panelCliente.add(lblNome);

		textNome = new JTextField();
		textNome.setBounds(67, 78, 402, 40);
		panelCliente.add(textNome);
		textNome.setColumns(10);

		lblCpf = new JLabel("CPF");
		lblCpf.setBounds(67, 129, 164, 14);
		panelCliente.add(lblCpf);

		textDataNascimento = new JTextField();
		textDataNascimento.setBounds(285, 142, 181, 40);
		panelCliente.add(textDataNascimento);
		textDataNascimento.setColumns(10);

		lblDataDeNascimento = new JLabel("Data de Nascimento");
		lblDataDeNascimento.setBounds(287, 129, 110, 14);
		panelCliente.add(lblDataDeNascimento);

		textCPF = new JTextField();
		textCPF.setBounds(67, 142, 181, 40);
		panelCliente.add(textCPF);
		textCPF.setColumns(10);

		JLabel lblCarto = new JLabel("Cart\u00E3o");
		lblCarto.setBounds(67, 209, 46, 14);
		panelCliente.add(lblCarto);

		lblSaldo = new JLabel("Saldo");
		lblSaldo.setBounds(285, 209, 46, 14);
		panelCliente.add(lblSaldo);

		textCartao = new JTextField();
		textCartao.setBounds(67, 222, 181, 40);
		panelCliente.add(textCartao);
		textCartao.setColumns(10);

		textSaldo = new JTextField();
		textSaldo.setBounds(285, 222, 181, 40);
		panelCliente.add(textSaldo);
		textSaldo.setColumns(10);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBackground(new Color(255, 255, 255));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExcluir.setBounds(67, 300, 181, 40);
		panelCliente.add(btnExcluir);

		JButton btnConcluido = new JButton("Concluido");
		btnConcluido.setBackground(new Color(255, 255, 255));
		btnConcluido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				panelCliente.setVisible(false);
			}
		});
		btnConcluido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnConcluido.setBounds(287, 300, 179, 40);
		panelCliente.add(btnConcluido);
		Image imgSearch = new ImageIcon(this.getClass().getResource("/search.png")).getImage();

		JPanel panelAlterar = new JPanel();
		panelAlterar.setBackground(new Color(255, 255, 255));
		panelAlterar.setBounds(0, 0, 10, 10);
		getContentPane().add(panelAlterar);
		panelAlterar.setLayout(null);

		lblNewLabel = new JLabel("Painel do Vendedor");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(38, 76, 203, 14);
		panelAlterar.add(lblNewLabel);

		JButton btnCadastrarCliente = new JButton("Cadastrar Cliente");
		btnCadastrarCliente.setBackground(new Color(255, 255, 255));
		panelAlterar.add(btnCadastrarCliente);
		btnCadastrarCliente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				panelCliente.setVisible(true);
				textDataNascimento.setVisible(true);
				textNome.setVisible(true);
				textSaldo.setVisible(true);
				lupa1.setVisible(false);
				lupa2.setVisible(false);
				lblDataDeNascimento.setVisible(true);
				lblNome.setVisible(true);
				lblSaldo.setVisible(true);
				btnBuscar.setVisible(false);
				btnExcluir.setVisible(false);
				btnCadastrar.setVisible(true);
				btnConcluido.setVisible(false);
			}
		});
		btnCadastrarCliente.setBounds(38, 120, 180, 114);

		JButton btnAlterarCadastro = new JButton("Manipular Cliente");
		btnAlterarCadastro.setBackground(new Color(255, 255, 255));
		panelAlterar.add(btnAlterarCadastro);
		btnAlterarCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (panelCliente.isVisible() == true) {

					textDataNascimento.setVisible(false);
					textNome.setVisible(false);
					textSaldo.setVisible(false);
					lupa1.setVisible(true);
					lupa2.setVisible(true);

					if (lblSaldo.isVisible() == true) {
						lblSaldo.setVisible(false);
					}
					if (lblDataDeNascimento.isVisible() == true) {
						lblDataDeNascimento.setVisible(false);

					}
					if (lblNome.isVisible() == true) {
						lblNome.setVisible(false);
					}
					btnCadastrar.setVisible(false);
					btnBuscar.setVisible(true);
					btnExcluir.setVisible(false);
					btnConcluido.setVisible(false);
				} else {

					panelCliente.setVisible(true);
					textDataNascimento.setVisible(false);
					textNome.setVisible(false);
					textSaldo.setVisible(false);

					if (lblSaldo.isVisible() == true) {
						lblSaldo.setVisible(false);
					}
					if (lblDataDeNascimento.isVisible() == true) {
						lblDataDeNascimento.setVisible(false);

					}
					if (lblNome.isVisible() == true) {
						lblNome.setVisible(false);
					}

					lupa1.setVisible(true);
					lupa2.setVisible(true);
					btnCadastrar.setVisible(false);
					btnBuscar.setVisible(true);
					btnExcluir.setVisible(false);
					btnConcluido.setVisible(false);
				}

			}
		});
		btnAlterarCadastro.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAlterarCadastro.setBounds(38, 245, 180, 114);

		panelCliente.setVisible(false);

		lupa1 = new JLabel("");
		lupa1.setBounds(41, 142, 19, 19);
		lupa1.setIcon(new ImageIcon(imgSearch));

		panelCliente.add(lupa1);

		lupa2 = new JLabel("");
		lupa2.setBounds(41, 222, 19, 19);
		lupa2.setIcon(new ImageIcon(imgSearch));

		panelCliente.add(lupa2);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(new Color(255, 255, 255));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnExcluir.setVisible(true);
				textDataNascimento.setVisible(true);
				textNome.setVisible(true);
				textSaldo.setVisible(true);
				lblDataDeNascimento.setVisible(true);
				lblNome.setVisible(true);
				lblSaldo.setVisible(true);
				btnConcluido.setVisible(true);
				btnBuscar.setVisible(false);
			}
		});
		btnBuscar.setBounds(285, 154, 181, 82);
		panelCliente.add(btnBuscar);

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBackground(new Color(255, 255, 255));
		btnCadastrar.setBounds(183, 273, 148, 67);
		panelCliente.add(btnCadastrar);

		btnBuscar.setVisible(false);
		lupa1.setVisible(false);
		lupa2.setVisible(false);
		lblDataDeNascimento.setVisible(false);
		lblNome.setVisible(false);
		lblSaldo.setVisible(false);
		btnConcluido.setVisible(false);

	}

	public JPanel getPanelCliente() {
		return panelCliente;
	}

	public void setPanelCliente(JPanel panelCliente) {
		this.panelCliente = panelCliente;
	}

	public JTextField getTextNome() {
		return textNome;
	}

	public void setTextNome(JTextField textNome) {
		this.textNome = textNome;
	}

	public JTextField getTextDataNascimento() {
		return textDataNascimento;
	}

	public void setTextDataNascimento(JTextField textDataNascimento) {
		this.textDataNascimento = textDataNascimento;
	}

	public JTextField getTextCPF() {
		return textCPF;
	}

	public void setTextCPF(JTextField textCPF) {
		this.textCPF = textCPF;
	}

	public JTextField getTextCartao() {
		return textCartao;
	}

	public void setTextCartao(JTextField textCartao) {
		this.textCartao = textCartao;
	}

	public JTextField getTextSaldo() {
		return textSaldo;
	}

	public void setTextSaldo(JTextField textSaldo) {
		this.textSaldo = textSaldo;
	}

	public JLabel getLblCpf() {
		return lblCpf;
	}

	public void setLblCpf(JLabel lblCpf) {
		this.lblCpf = lblCpf;
	}

	public JLabel getLblDataDeNascimento() {
		return lblDataDeNascimento;
	}

	public void setLblDataDeNascimento(JLabel lblDataDeNascimento) {
		this.lblDataDeNascimento = lblDataDeNascimento;
	}

	public JLabel getLblNome() {
		return lblNome;
	}

	public void setLblNome(JLabel lblNome) {
		this.lblNome = lblNome;
	}

	public JLabel getLupa1() {
		return lupa1;
	}

	public void setLupa1(JLabel lupa1) {
		this.lupa1 = lupa1;
	}

	public JLabel getLupa2() {
		return lupa2;
	}

	public void setLupa2(JLabel lupa2) {
		this.lupa2 = lupa2;
	}

	public JLabel getLblSaldo() {
		return lblSaldo;
	}

	public void setLblSaldo(JLabel lblSaldo) {
		this.lblSaldo = lblSaldo;
	}

}
