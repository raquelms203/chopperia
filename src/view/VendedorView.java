package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Conexao;

public class VendedorView extends JFrame{
	private JTextField txtNome;
	private JTextField txtDataNasc;
	private JTextField txtCPF;
	private JTextField txtCartao;
	private JTextField txtSaldo;
	
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
		getContentPane().setLayout(null);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnCadastrar.setBounds(81, 73, 180, 114);
		getContentPane().add(btnCadastrar);
		
		JButton btnAlterarCadastro = new JButton("Alterar Cadastro");
		btnAlterarCadastro.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAlterarCadastro.setBounds(81, 258, 180, 114);
		getContentPane().add(btnAlterarCadastro);
		
		JPanel panelCliente = new JPanel();
		panelCliente.setBounds(308, 32, 492, 375);
		getContentPane().add(panelCliente);
		panelCliente.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(67, 39, 46, 14);
		panelCliente.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(64, 53, 402, 40);
		panelCliente.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(67, 129, 164, 14);
		panelCliente.add(lblCpf);
		
		txtDataNasc = new JTextField();
		txtDataNasc.setBounds(285, 142, 181, 40);
		panelCliente.add(txtDataNasc);
		txtDataNasc.setColumns(10);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento");
		lblDataDeNascimento.setBounds(287, 129, 110, 14);
		panelCliente.add(lblDataDeNascimento);
		
		txtCPF = new JTextField();
		txtCPF.setBounds(67, 142, 181, 40);
		panelCliente.add(txtCPF);
		txtCPF.setColumns(10);
		
		JLabel lblCarto = new JLabel("Cart\u00E3o");
		lblCarto.setBounds(67, 209, 46, 14);
		panelCliente.add(lblCarto);
		
		JLabel lblSaldo = new JLabel("Saldo");
		lblSaldo.setBounds(285, 209, 46, 14);
		panelCliente.add(lblSaldo);
		
		txtCartao = new JTextField();
		txtCartao.setBounds(67, 222, 181, 40);
		panelCliente.add(txtCartao);
		txtCartao.setColumns(10);
		
		txtSaldo = new JTextField();
		txtSaldo.setBounds(285, 222, 181, 40);
		panelCliente.add(txtSaldo);
		txtSaldo.setColumns(10);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExcluir.setBounds(67, 300, 181, 40);
		panelCliente.add(btnExcluir);
		
		JButton btnConcluido = new JButton("Concluido");
		btnConcluido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "INSERT INTO public.cartoes (\"cpfCartao\", \"dataNasc\", saldo, status) VALUES "
						+ txtCPF.getText() + ", '" + txtDataNasc.getText() + "', " + txtSaldo.getText() + ", " + true;
					 
				JOptionPane.showMessageDialog(null, query);
				Conexao con = new Conexao();
				boolean res = con.executeUpdate(query);
				
				if(res) 
					JOptionPane.showMessageDialog(null, "Sucesso");
				else 
					JOptionPane.showMessageDialog(null, "Erro");
				
			}
		});
		btnConcluido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnConcluido.setBounds(287, 300, 179, 40);
		panelCliente.add(btnConcluido);
		Image imgSearch = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(imgSearch));
		label.setBounds(41, 53, 19, 19);
		panelCliente.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(41, 142, 19, 19);
		label_1.setIcon(new ImageIcon(imgSearch));

		panelCliente.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(41, 222, 19, 19);
		label_2.setIcon(new ImageIcon(imgSearch));

		panelCliente.add(label_2);
		
		JPanel panelAlterar = new JPanel();
		panelAlterar.setBounds(0, 0, 10, 10);
		getContentPane().add(panelAlterar);
	}
}
