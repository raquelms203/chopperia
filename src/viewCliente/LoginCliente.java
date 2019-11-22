package viewCliente;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Conexao;
import system.JtextFieldSomenteNumeros;

public class LoginCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textCartao;
	
	static LoginCliente frame;
	
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
					frame = new LoginCliente();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public String dateParaString(Date date) {
		String string = date.toString();

		String format = "" + string.charAt(8) + string.charAt(9) + '/' + string.charAt(5) + string.charAt(6) + '/'
				+ string.charAt(0) + string.charAt(1) + string.charAt(2) + string.charAt(3);

		return format;
	}

	public boolean buscaCartao() {

		String query = "SELECT * FROM cartoes WHERE \"nrcartao\" = ?";

		Conexao con = new Conexao();

		Connection conn = con.getConnection();

		boolean result = false;

		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(query);
			prep.setInt(1, Integer.parseInt(textCartao.getText()));

			ResultSet res = prep.executeQuery();

			if (res.next()) {

				result = true;
			}

			res.close();
			prep.close();
			conn.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
			result = false;
		}

		return result;

	}

	public LoginCliente() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1089, 498);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textCartao = new JtextFieldSomenteNumeros(6);
		textCartao.setHorizontalAlignment(SwingConstants.CENTER);
		textCartao.setFont(new Font("Tahoma", Font.BOLD, 20));
		textCartao.setBounds(244, 171, 553, 73);
		contentPane.add(textCartao);
		textCartao.setColumns(10);

		JButton btnNewButton = new JButton("Acessar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textCartao.getText().equals("")) {

					
					JOptionPane.showMessageDialog(null, "Nenhum cartão foi inserido!","ERRO",2);

				} else {

					if (buscaCartao() == true) {

						Maquina maquina = new Maquina(Integer.parseInt(textCartao.getText()));
						maquina.setVisible(true);
						maquina.setLocationRelativeTo(null);
						frame.setVisible(false);
						textCartao.setText("");
						
					} else {

						textCartao.setText("");
						JOptionPane.showMessageDialog(null, "Cartão não encontrado!");
					}
				}

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBackground(new Color(0, 255, 153));
		btnNewButton.setBounds(391, 253, 262, 73);
		contentPane.add(btnNewButton);

		JLabel lblDigiteONumero = new JLabel("Digite o n\u00FAmero do cart\u00E3o");
		lblDigiteONumero.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblDigiteONumero.setBounds(357, 74, 379, 102);
		contentPane.add(lblDigiteONumero);
	}

	public JTextField getTextCartao() {
		return textCartao;
	}

	public void setTextCartao(JTextField textCartao) {
		this.textCartao = textCartao;
	}

}
