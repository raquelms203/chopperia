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

import javax.swing.JLabel;
import javax.swing.JTextField;

public class VendedorView extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
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
		
		textField = new JTextField();
		textField.setBounds(64, 53, 402, 40);
		panelCliente.add(textField);
		textField.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(67, 129, 164, 14);
		panelCliente.add(lblCpf);
		
		textField_1 = new JTextField();
		textField_1.setBounds(285, 142, 181, 40);
		panelCliente.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento");
		lblDataDeNascimento.setBounds(287, 129, 110, 14);
		panelCliente.add(lblDataDeNascimento);
		
		textField_2 = new JTextField();
		textField_2.setBounds(67, 142, 181, 40);
		panelCliente.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblCarto = new JLabel("Cart\u00E3o");
		lblCarto.setBounds(67, 209, 46, 14);
		panelCliente.add(lblCarto);
		
		JLabel lblSaldo = new JLabel("Saldo");
		lblSaldo.setBounds(285, 209, 46, 14);
		panelCliente.add(lblSaldo);
		
		textField_3 = new JTextField();
		textField_3.setBounds(67, 222, 181, 40);
		panelCliente.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(285, 222, 181, 40);
		panelCliente.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExcluir.setBounds(67, 300, 181, 40);
		panelCliente.add(btnExcluir);
		
		JButton btnConcluido = new JButton("Concluido");
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
