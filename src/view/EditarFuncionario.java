package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Conexao;
import system.JtextFieldSomenteLetras;
import system.JtextFieldSomenteNumeros;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class EditarFuncionario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textCPF;
	private int cpffuncionario;

	private JCheckBox chckbxMasculino;
	private JCheckBox chckbxFeminino;
	private JCheckBox chckbxGerente;
	private JCheckBox chckbxVendedor;

	public void mostrarFuncionario(int cpffuncionario) {
		
		String query = "SELECT nome, cargo, sexo FROM funcionarios WHERE cpffuncionario = ?"; 
		
		Conexao con = new Conexao();
		Connection conn = con.getConnection();

		try {
			PreparedStatement prep = conn.prepareStatement(query);
			prep.setInt(1, cpffuncionario);
			
			ResultSet rs = prep.executeQuery();
			
			while (rs.next()) {
			
				textNome.setText(rs.getString("nome"));
				textCPF.setText(""+cpffuncionario);
				
				if(rs.getString("sexo").equals("FEMININO")) {
					
					chckbxFeminino.setSelected(true);
				}else {
					
					chckbxMasculino.setSelected(true);
				}
				
				if(rs.getString("cargo").equals("VENDEDOR")) {
					
					chckbxVendedor.setSelected(true);
					
				}else {
					
					chckbxGerente.setSelected(true);
				}
				
			}
	
			prep.close();
			rs.close();
			conn.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}
	
	public void updateFuncionario(){

		String query = "UPDATE funcionarios SET nome =  ?, cpffuncionario = ?, sexo = ?, cargo = ? WHERE cpffuncionario = ?"; 
		
		Conexao con = new Conexao();
		Connection conn = con.getConnection();

		try {

			String cargo = null;
			String sexo = null;

			if (chckbxGerente.isSelected() == true) {
				cargo = "GERENTE";
			} else {
				if (chckbxVendedor.isSelected() == true) {
					cargo = "VENDEDOR";
				} else {

					JOptionPane.showMessageDialog(null, "Selecione uma opcão para tipo de funcionário!");
					return;
				}
			}

			if (chckbxFeminino.isSelected() == true) {

				sexo = "FEMININO";
			} else {
				if (chckbxMasculino.isSelected() == true) {
					sexo = "MASCULINO";
				} else {

					JOptionPane.showMessageDialog(null, "Marque uma opção de sexo!");
					return;
				}
			}
			
			PreparedStatement prep = conn.prepareStatement(query);
			prep.setString(1, textNome.getText());
			prep.setInt(2, Integer.parseInt(textCPF.getText()));
			prep.setString(3, sexo);
			prep.setString(4, cargo);
			prep.setInt(5, cpffuncionario);
			prep.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Sucesso!");
			prep.close();
			conn.close();
			
		} catch (SQLException e) {
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
					EditarFuncionario frame = new EditarFuncionario(-1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EditarFuncionario(int cpffuncionario) {
		this.cpffuncionario = cpffuncionario;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 438, 340);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(32, 76, 48, 14);
		contentPane.add(lblNome);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(32, 112, 48, 14);
		contentPane.add(lblCpf);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(32, 148, 48, 14);
		contentPane.add(lblTipo);

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(32, 180, 48, 14);
		contentPane.add(lblSexo);

		textNome = new JtextFieldSomenteLetras(50);
		textNome.setBounds(90, 66, 249, 27);
		contentPane.add(textNome);
		textNome.setColumns(10);

		textCPF = new JtextFieldSomenteNumeros(11);
		textCPF.setBounds(90, 102, 249, 27);
		contentPane.add(textCPF);
		textCPF.setColumns(10);

		chckbxGerente = new JCheckBox("Gerente");
		chckbxGerente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxGerente.isSelected()) {
					chckbxVendedor.setSelected(false);
				}
			}
		});
		chckbxGerente.setBackground(new Color(255, 255, 255));
		chckbxGerente.setBounds(86, 144, 97, 23);
		contentPane.add(chckbxGerente);

		chckbxVendedor = new JCheckBox("Vendedor");
		chckbxVendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxVendedor.isSelected()) {

					chckbxGerente.setSelected(false);
				}
			}
		});
		chckbxVendedor.setBackground(new Color(255, 255, 255));
		chckbxVendedor.setBounds(242, 144, 97, 23);
		contentPane.add(chckbxVendedor);

		chckbxMasculino = new JCheckBox("Masculino");
		chckbxMasculino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (chckbxFeminino.isSelected()) {

					chckbxFeminino.setSelected(false);
				}
			}
		});
		chckbxMasculino.setBackground(new Color(255, 255, 255));
		chckbxMasculino.setBounds(86, 176, 97, 23);
		contentPane.add(chckbxMasculino);

		chckbxFeminino = new JCheckBox("Feminino");
		chckbxFeminino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxMasculino.isSelected()) {
					chckbxMasculino.setSelected(false);
				}
			}
		});
		chckbxFeminino.setBackground(new Color(255, 255, 255));
		chckbxFeminino.setBounds(242, 176, 97, 23);
		contentPane.add(chckbxFeminino);

		JButton btnNewButton = new JButton("Concluido");
		btnNewButton.setBackground(new Color(0, 250, 154));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				updateFuncionario();
				dispose();
			}
		});
		btnNewButton.setBounds(90, 235, 249, 33);
		contentPane.add(btnNewButton);
		
		JLabel lblEditarFuncionrio = new JLabel("Editar Funcion\u00E1rio");
		lblEditarFuncionrio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEditarFuncionrio.setBounds(143, 11, 136, 44);
		contentPane.add(lblEditarFuncionrio);
		
		mostrarFuncionario(cpffuncionario);
	}

	public int getCpffuncionario() {
		return cpffuncionario;
	}

	public void setCpffuncionario(int cpffuncionario) {
		this.cpffuncionario = cpffuncionario;
	}
}
