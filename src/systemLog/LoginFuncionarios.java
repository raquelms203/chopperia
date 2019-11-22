package systemLog;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Conexao;
import view.EditarBebida;
import view.GerenteView;
import view.VendedorView;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFuncionarios extends JFrame {

	private JPanel contentPane;
	private JTextField textUSUARIO;
	private JPasswordField passwordFieldFuncionario;

	public String conversordeSenha() {

		char[] st = passwordFieldFuncionario.getPassword();
		String senha = "";
		for (int i = 0; i < st.length; i++) {

			senha = senha + st[i];
		}

		return senha;
	}

	public void verificaLogin() {
		
		if(textUSUARIO.getText().contentEquals("")) {
			JOptionPane.showMessageDialog(null, "Campos Vazios!");
			return;
		}
		String query = "SELECT nome, senha, cargo, cpffuncionario FROM funcionarios WHERE usuario = ?";
		Conexao con = new Conexao();
		Connection conn = con.getConnection();

		try {
			PreparedStatement prep = conn.prepareStatement(query);
			prep.setString(1, textUSUARIO.getText());
			ResultSet rs = prep.executeQuery();

			String senha = "";
			String cargo = "";
			String nome = "";
			int cpf = -1;

			while (rs.next()) {
				nome = rs.getString("nome");
				senha = rs.getString("senha");
				cargo = rs.getString("cargo");
				cpf = rs.getInt("cpffuncionario");
			}

			if (senha.equals(conversordeSenha()) == true) {

				if (cargo.equals("VENDEDOR")) {

					VendedorView vendedor = new VendedorView();
					vendedor.getLabelVendedor().setText(nome);
					vendedor.setCpf(cpf);
					vendedor.setVisible(true);
					dispose();

				} else {

					if (cargo.equals("GERENTE")) {
						GerenteView gerente = new GerenteView();
						gerente.getLabelGerente().setText(nome);
						gerente.setCpf(cpf);
						gerente.setVisible(true);
						dispose();
					} else {

					}

				}

			} else {
				JOptionPane.showMessageDialog(null, "Usuário ou Senha incorreta!", "OPS!", 2);
				rs.close();
				prep.close();
				conn.close();
				return;
			}

			JOptionPane.showMessageDialog(null, "Bem-Vindo!");

			rs.close();
			prep.close();
			conn.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			return;
		}

	}

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
					LoginFuncionarios frame = new LoginFuncionarios();
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
	public LoginFuncionarios() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 983, 395);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("USU\u00C1RIO:");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUsuario.setBounds(122, 124, 146, 33);
		contentPane.add(lblUsuario);

		JLabel lblSenha = new JLabel("SENHA:");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSenha.setBounds(122, 168, 164, 35);
		contentPane.add(lblSenha);

		textUSUARIO = new JTextField();
		textUSUARIO.setBounds(244, 118, 600, 39);
		contentPane.add(textUSUARIO);
		textUSUARIO.setColumns(10);

		JButton btnLogin = new JButton("Acessar");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verificaLogin();
			}
		});
		btnLogin.setBackground(new Color(0, 250, 154));
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogin.setBounds(122, 218, 722, 45);
		contentPane.add(btnLogin);

		JLabel lblLoginFuncionrio = new JLabel("LOGIN FUNCION\u00C1RIO");
		lblLoginFuncionrio.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLoginFuncionrio.setBounds(369, 60, 290, 33);
		contentPane.add(lblLoginFuncionrio);

		passwordFieldFuncionario = new JPasswordField();
		passwordFieldFuncionario.setBounds(244, 164, 600, 39);
		contentPane.add(passwordFieldFuncionario);
	}
}
