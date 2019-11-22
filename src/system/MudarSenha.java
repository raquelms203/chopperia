package system;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Conexao;
import systemLog.LoginFuncionarios;
import view.GerenteView;
import view.VendedorView;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class MudarSenha extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldAtual;
	private JPasswordField passwordField;
	
	private int cpf;
	private JLabel lblMudarSenha;

	public String conversordeSenha() {

		char[] st = passwordField.getPassword();
		String senha = "";
		for (int i = 0; i < st.length; i++) {

			senha = senha + st[i];
		}

		return senha;
	}

	public void mudarSenha(int cpf) {

		String query = "SELECT senha FROM funcionarios WHERE cpffuncionario = ?";
		Conexao con = new Conexao();
		Connection conn = con.getConnection();

		try {

			String senhaAtual = null;
			PreparedStatement prep = conn.prepareStatement(query);
			prep.setInt(1, cpf);

			ResultSet rs = prep.executeQuery();
			
			while(rs.next()){
				
				senhaAtual = rs.getString("senha");

			}
			
			prep.close();
			

			if (senhaAtual.contentEquals(textFieldAtual.getText())) {
				

				query = "UPDATE funcionarios SET senha = ? WHERE \"cpffuncionario\" = ?";
				
				prep = conn.prepareStatement(query);
				prep.setString(1, conversordeSenha());
				prep.setInt(2, cpf);
				
				prep.executeUpdate();

				rs.close();
				prep.close();
				conn.close();
				
				JOptionPane.showMessageDialog(null, "Senha alterada com sucesso!");
				dispose();
				
			} else {

				JOptionPane.showMessageDialog(null, "Senha Atual incorreta!");
				conn.close();
				rs.close();
				prep.close();
			}

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);
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
					MudarSenha frame = new MudarSenha();
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
	public MudarSenha() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 452, 243);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSenhaAtual = new JLabel("Senha  Atual:");
		lblSenhaAtual.setBounds(40, 59, 82, 24);
		contentPane.add(lblSenhaAtual);

		JLabel lblNovaSenha = new JLabel("Nova Senha:");
		lblNovaSenha.setBounds(40, 94, 82, 30);
		contentPane.add(lblNovaSenha);

		textFieldAtual = new JTextField();
		textFieldAtual.setBounds(127, 59, 255, 24);
		contentPane.add(textFieldAtual);
		textFieldAtual.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(127, 94, 255, 25);
		contentPane.add(passwordField);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBackground(new Color(0, 250, 154));
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mudarSenha(getCpf());
			}
		});
		btnAlterar.setBounds(40, 130, 342, 30);
		contentPane.add(btnAlterar);
		
		lblMudarSenha = new JLabel("Mudar Senha");
		lblMudarSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMudarSenha.setBounds(164, 11, 148, 31);
		contentPane.add(lblMudarSenha);
	}

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
}
