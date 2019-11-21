
package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Conexao;
import system.JtextFieldSomenteLetras;
import system.JtextFieldSomenteNumeros;

import javax.swing.SwingConstants;

public class VendedorView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private JButton btnConcluido;
	private JButton btnExcluir;
	private boolean cadastro;
	private JLabel lblCarto;
	private JLabel lblUtilitario;

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
					VendedorView frame = new VendedorView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void insereCartao() {
		String query = "INSERT INTO cartoes ( nome, \"cpfCartao\", saldo, status, \"dataNasc\") VALUES"
				+ "(?, ?, ?, ?, ?)";

		String query2 = "SELECT \"nrCartao\" FROM cartoes WHERE \"cpfCartao\" = ?";

		Conexao con = new Conexao();

		Connection conn = con.getConnection();

		PreparedStatement prep, prep2;
		ResultSet rs;

		try {
			
			double saldo =  Double.parseDouble(textSaldo.getText());
			DecimalFormat df = new DecimalFormat("0.00");
			df.format(saldo);

		
			prep = conn.prepareStatement(query);
			prep.setString(1, textNome.getText());
			prep.setInt(2, Integer.parseInt(textCPF.getText()));
			prep.setDouble(3, saldo);
			prep.setBoolean(4, true);
			prep.setDate(5, stringParaDate(textDataNascimento.getText()));

			prep.executeUpdate();
			prep.close();

			prep2 = conn.prepareStatement(query2);
			prep2.setInt(1, Integer.parseInt(textCPF.getText()));
			rs = prep2.executeQuery();

			while (rs.next()) {
				JOptionPane.showMessageDialog(null,
						"Cliente cadastrado com sucesso!\nCartao: " + rs.getInt("nrCartao"));
			}

			prep2.close();
			rs.close();
			conn.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}

	}

	public boolean buscaCpf() {

		String query = "SELECT * FROM cartoes WHERE \"cpfCartao\" = ?";

		Conexao con = new Conexao();

		Connection conn = con.getConnection();

		boolean result = false;

		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(query);
			prep.setInt(1, Integer.parseInt(textCPF.getText()));

			ResultSet res = prep.executeQuery();

			if (res.next()) {
				textNome.setText(res.getString("nome"));
				textSaldo.setText("" + res.getDouble("saldo"));
				textCartao.setText("" + res.getInt("nrCartao"));
				textDataNascimento.setText(res.getString("dataNasc"));

				result = true;

			}

			res.close();
			prep.close();
			conn.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}

		return result;

	}

	public boolean buscaCartao() {

		String query = "SELECT * FROM cartoes WHERE \"nrCartao\" = ?";

		Conexao con = new Conexao();

		Connection conn = con.getConnection();

		boolean result = false;

		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(query);
			prep.setInt(1, Integer.parseInt(textCartao.getText()));

			ResultSet res = prep.executeQuery();

			if (res.next()) {
				textNome.setText(res.getString("nome"));
				textSaldo.setText("" + res.getDouble("saldo"));
				textCPF.setText("" + res.getInt("cpfCartao"));
				textDataNascimento.setText(dateParaString(res.getDate("dataNasc")));
				result = true;
			}

			res.close();
			prep.close();
			conn.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}

		return result;

	}

	public void atualizaCartao() {
		String query = "UPDATE cartoes SET nome = ?, \"cpfCartao\" = ?, saldo = ?"
				+ ", status = ?, \"dataNasc\" = ? WHERE \"nrCartao\" = ?";

		Conexao con = new Conexao();

		Connection conn = con.getConnection();

		PreparedStatement prep;

		try {

			double saldo = Double.parseDouble(textSaldo.getText());

			DecimalFormat df = new DecimalFormat("0.00");
			df.format(saldo);

			prep = conn.prepareStatement(query);
			prep.setString(1, textNome.getText());
			prep.setInt(2, Integer.parseInt(textCPF.getText()));
			prep.setDouble(3, saldo);
			prep.setBoolean(4, true);
			prep.setDate(5, stringParaDate(textDataNascimento.getText()));
			prep.setInt(6, Integer.parseInt(textCartao.getText()));

			prep.executeUpdate();
			prep.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

		JOptionPane.showMessageDialog(null, "Cliente editado com sucesso!");

	}

	public void deletaCartao() {
		String query = "DELETE FROM cartoes WHERE \"nrCartao\" = ?";

		Conexao con = new Conexao();

		Connection conn = con.getConnection();

		PreparedStatement prep;

		try {
			prep = conn.prepareStatement(query);
			prep.setInt(1, Integer.parseInt(textCartao.getText()));

			prep.execute();
			prep.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

		JOptionPane.showMessageDialog(null, "Cliente apagado com sucessso!");

	}

	public Date stringParaDate(String txtdata) {

		String format = "" + txtdata.charAt(6) + txtdata.charAt(7) + txtdata.charAt(8) + txtdata.charAt(9) + '-'
				+ txtdata.charAt(3) + txtdata.charAt(4) + '-' + txtdata.charAt(0) + txtdata.charAt(1);

		Date date = Date.valueOf(format);

		return date;
	}

	public String dateParaString(Date date) {
		String string = date.toString();

		String format = "" + string.charAt(8) + string.charAt(9) + '/' + string.charAt(5) + string.charAt(6) + '/'
				+ string.charAt(0) + string.charAt(1) + string.charAt(2) + string.charAt(3);

		return format;
	}

	public boolean campoVazio() {
		if (textNome.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Campo 'Nome' vazio!");
			return true;
		}

		if (textDataNascimento.getText().length() != 10) {
			JOptionPane.showMessageDialog(null, "Campo 'Data de Nascimento' incompleto!");
			return true;
		}

		if (textCPF.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Campo 'CPF' vazio!");
			return true;
		}

		if (textSaldo.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Campo 'Saldo' vazio!");
			return true;
		}

		return false;
	}

	public void limparCampos() {
		textCartao.setText("");
		textCPF.setText("");
		textDataNascimento.setText("");
		textNome.setText("");
		textSaldo.setText("");

	}

	public void mostrarPainelManipular() {
		cadastro = false;
		limparCampos();

		if (panelCliente.isVisible() == true) {
			textCartao.setEditable(true);
			textDataNascimento.setVisible(false);
			textNome.setVisible(false);
			textSaldo.setVisible(false);
			lupa1.setVisible(true);
			lupa2.setVisible(true);
			lblCarto.setVisible(true);
			textCartao.setVisible(true);

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
			textCartao.setEditable(true);
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

		textNome = new JtextFieldSomenteLetras(50);
		textNome.setBounds(67, 78, 402, 40);
		panelCliente.add(textNome);
		textNome.setColumns(10);

		lblCpf = new JLabel("CPF");
		lblCpf.setBounds(67, 129, 164, 14);
		panelCliente.add(lblCpf);

		textDataNascimento = new JTextField();
		textDataNascimento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (textDataNascimento.getText().length() == 2 || textDataNascimento.getText().length() == 5) {
					textDataNascimento.setText(textDataNascimento.getText() + '/');

				}

				if (textDataNascimento.getText().length() == 10) {
					e.consume();
					return;
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {

				if (textDataNascimento.getText().length() == 0) {
					e.consume();
					return;
				}
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {

					if (textDataNascimento.getText().charAt(textDataNascimento.getText().length() - 1) == '/') {
						textDataNascimento.setText(
								textDataNascimento.getText().substring(0, textDataNascimento.getText().length() - 1));
						return;
					}

				}

			}
		});
		textDataNascimento.setBounds(285, 142, 181, 40);
		panelCliente.add(textDataNascimento);
		textDataNascimento.setColumns(10);

		lblDataDeNascimento = new JLabel("Data de Nascimento");
		lblDataDeNascimento.setBounds(287, 129, 110, 14);
		panelCliente.add(lblDataDeNascimento);

		textCPF = new JtextFieldSomenteNumeros(11);
		textCPF.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					if (!cadastro) {
						btnExcluir.setVisible(true);
						textDataNascimento.setVisible(true);
						textNome.setVisible(true);
						textSaldo.setVisible(true);
						lblDataDeNascimento.setVisible(true);
						lblNome.setVisible(true);
						lblSaldo.setVisible(true);
						btnConcluido.setVisible(true);
						btnBuscar.setVisible(false);

						buscaCpf();
					}
				}
			}
		});
		textCPF.setBounds(67, 142, 181, 40);
		panelCliente.add(textCPF);
		textCPF.setColumns(10);

		lblCarto = new JLabel("Cart\u00E3o");
		lblCarto.setBounds(67, 209, 46, 14);
		panelCliente.add(lblCarto);

		lblSaldo = new JLabel("Saldo");
		lblSaldo.setBounds(285, 209, 46, 14);
		panelCliente.add(lblSaldo);

		textCartao = new JtextFieldSomenteNumeros(6);
		textCartao.setBounds(67, 222, 181, 40);
		panelCliente.add(textCartao);
		textCartao.setColumns(10);

		textSaldo = new JtextFieldSomenteNumeros(5);
		textSaldo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (cadastro) {
						if (campoVazio())
							return;
						insereCartao();
						limparCampos();
					}

				}
			}
		});
		textSaldo.setBounds(285, 222, 181, 40);
		panelCliente.add(textSaldo);
		textSaldo.setColumns(10);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setBackground(new Color(255, 0, 0));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textCartao.setEditable(true);
				deletaCartao();
				mostrarPainelManipular();
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExcluir.setBounds(67, 300, 181, 40);
		panelCliente.add(btnExcluir);

		btnConcluido = new JButton("Concluido");
		btnConcluido.setBackground(new Color(0, 250, 154));
		btnConcluido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (campoVazio())
					return;
				atualizaCartao();
				mostrarPainelManipular();
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
		lblNewLabel.setBounds(38, 68, 203, 22);
		panelAlterar.add(lblNewLabel);

		JButton btnCadastrarCliente = new JButton("Cadastrar Cliente");
		btnCadastrarCliente.setBackground(new Color(0, 250, 154));
		panelAlterar.add(btnCadastrarCliente);
		btnCadastrarCliente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastro = true;
				limparCampos();
				lblCarto.setVisible(false);
				textCartao.setVisible(false);
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
				lblUtilitario.setText("CADASTRAR NOVO CLIENTE");
				lblUtilitario.setVisible(true);
			}
		});
		btnCadastrarCliente.setBounds(38, 120, 180, 114);

		JButton btnAlterarCadastro = new JButton("Manipular Cliente");
		btnAlterarCadastro.setBackground(new Color(255, 255, 0));
		panelAlterar.add(btnAlterarCadastro);
		btnAlterarCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastro = false;
				mostrarPainelManipular();
				lblUtilitario.setText("MANIPULAR CLIENTE EXISTENTE");
				lblUtilitario.setVisible(true);

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
		btnBuscar.setBackground(new Color(0, 191, 255));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textCPF.getText().length() == 0 && textCartao.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Campo vazio!");
					return;
				}

				if (textCPF.getText().length() != 0 && !buscaCpf()) {
					textCPF.setBackground(new Color(255, 153, 153));
					return;
				}

				if (textCartao.getText().length() != 0 && !buscaCartao()) {
					textCartao.setBackground(new Color(255, 153, 153));
					return;
				}

				textCartao.setBackground(new Color(255, 255, 255));
				textCPF.setBackground(new Color(255, 255, 255));
				textCartao.setEditable(false);
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
		btnCadastrar.setForeground(new Color(0, 0, 0));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (campoVazio())
					return;
				insereCartao();
				limparCampos();

			}
		});
		btnCadastrar.setBackground(new Color(0, 250, 154));
		btnCadastrar.setBounds(183, 273, 148, 67);
		panelCliente.add(btnCadastrar);

		lblUtilitario = new JLabel("----");
		lblUtilitario.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUtilitario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUtilitario.setBounds(95, 12, 345, 40);
		panelCliente.add(lblUtilitario);

		btnBuscar.setVisible(false);
		lupa1.setVisible(false);
		lupa2.setVisible(false);
		lblDataDeNascimento.setVisible(false);
		lblNome.setVisible(false);
		lblSaldo.setVisible(false);
		btnConcluido.setVisible(false);

		lblUtilitario.setVisible(false);
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

	public void setTextDataNascimento(JFormattedTextField textDataNascimento) {
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

	public boolean isCadastro() {
		return cadastro;
	}

	public void setCadastro(boolean cadastro) {
		this.cadastro = cadastro;
	}

}
