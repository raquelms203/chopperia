package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Conexao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarBebida extends JFrame {

	private JPanel contentPane;
	private JTextField txtMarca;
	private JTextField txtValor;
	private String marcaSelecionada;
	private JCheckBox chckbxCerveja;
	private JCheckBox chckbxRefrigerante;
	private JComboBox<String> comboBox;
	private String qtdAtual;

	private double valorAtual;

	/**
	 * Launch the application.
	 */
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
					EditarBebida frame = new EditarBebida("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void preencherCampos() {
		String query = "SELECT * FROM opcoes WHERE marca = '" + marcaSelecionada + "'";
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(1);

		Conexao con = new Conexao();
		Connection conn = con.getConnection();

		NumberFormat formatter = NumberFormat.getCurrencyInstance();

		try {
			PreparedStatement prep = conn.prepareStatement(query);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				if (rs.getString("tipo").equalsIgnoreCase("CERVEJA")) {

					chckbxCerveja.setSelected(true);
				} else if (rs.getString("tipo").equalsIgnoreCase("REFRIGERANTE")) {
					chckbxRefrigerante.setSelected(true);
				}

				txtMarca.setText(marcaSelecionada);

				valorAtual = rs.getDouble("quantidade");
				qtdAtual = df.format(rs.getDouble("quantidade"));

				txtValor.setText(formatter.format(rs.getDouble("valor")));

				JLabel lblNewLabel = new JLabel("Atual: " + qtdAtual + " L");
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblNewLabel.setForeground(new Color(0, 128, 128));
				lblNewLabel.setBounds(268, 162, 77, 14);
				contentPane.add(lblNewLabel);

			}

			prep.close();
			rs.close();
			conn.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			// TODO: handle exception
		}
	}

	public void atualizarBebida() {

		double qtdSelecionada = 0.0;

		switch (comboBox.getSelectedIndex()) {

		case 0:
			qtdSelecionada = 0;
			break;

		case 1:
			qtdSelecionada = 30;

			break;
		case 2:
			qtdSelecionada = 40;

			break;
		case 3:
			qtdSelecionada = 50;

			break;
		case 4:
			qtdSelecionada = 100;

			break;
		default:
			break;
		}

		String query = "UPDATE opcoes SET " + "tipo = ?, marca = ?, valor = ?, quantidade = ? WHERE marca = '"
				+ marcaSelecionada + "'";

		String tipo = "";

		if (chckbxCerveja.isSelected())
			tipo = "CERVEJA";
		else if (chckbxRefrigerante.isSelected())
			tipo = "REFRIGERANTE";

		Conexao con = new Conexao();
		Connection conn = con.getConnection();

		try {
			PreparedStatement prep = conn.prepareStatement(query);
			prep.setString(1, tipo);
			prep.setString(2, txtMarca.getText());
			prep.setDouble(3, Double.parseDouble(moneyToDouble(txtValor.getText())));
			prep.setDouble(4, qtdSelecionada + valorAtual);

			prep.execute();

			prep.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, (e));
		}

		JOptionPane.showMessageDialog(null, "Bebida editada com sucesso!");
	}

	public String moneyToDouble(String money) {
		String format = money.substring(3, money.length());
		format = format.replace(',', '.');
		return format;
	}

	public void mostrarComboBox() {

		comboBox.removeAllItems();
		comboBox.addItem("0 Litros");
		comboBox.addItem("30 Litros");
		comboBox.addItem("40 Litros");
		comboBox.addItem("50 Litros");
		comboBox.addItem("100 Litros");
	}


	/**
	 * Create the frame.
	 */
	

	public EditarBebida(String marcaSelecionada) {
		this.marcaSelecionada = marcaSelecionada;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 422, 359);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(76, 72, 59, 14);
		contentPane.add(lblTipo);
		
		JLabel lblEditarBebida = new JLabel("Editar Bebida");
		lblEditarBebida.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEditarBebida.setBounds(164, 11, 110, 14);
		contentPane.add(lblEditarBebida);
		
		chckbxCerveja = new JCheckBox("Cerveja");
		chckbxCerveja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxCerveja.isSelected())

					chckbxRefrigerante.setSelected(false);
			}
		});
		chckbxCerveja.setBounds(171, 72, 86, 23);
		contentPane.add(chckbxCerveja);
		
		chckbxRefrigerante = new JCheckBox("Refrigerante");
		chckbxRefrigerante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxRefrigerante.isSelected())

					chckbxRefrigerante.setSelected(false);
			}
		});
		chckbxRefrigerante.setBounds(256, 72, 97, 23);
		contentPane.add(chckbxRefrigerante);


		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(76, 107, 46, 14);
		contentPane.add(lblMarca);


		comboBox = new JComboBox<String>();
		comboBox.setBounds(171, 154, 91, 30);
		contentPane.add(comboBox);
		
		JLabel lblValorPorLitro = new JLabel("Valor por litro:");
		lblValorPorLitro.setBounds(76, 211, 86, 14);
		contentPane.add(lblValorPorLitro);
		
		comboBox = new JComboBox<String>();
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setBounds(171, 154, 174, 30);
		contentPane.add(comboBox);

		lblValorPorLitro = new JLabel("Valor por litro:");
		lblValorPorLitro.setBounds(76, 211, 86, 14);
		contentPane.add(lblValorPorLitro);


		
		JButton btnConcludo = new JButton("CONFIRMAR");
		btnConcludo.setForeground(new Color(0, 0, 0));
		btnConcludo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConcludo.setBackground(new Color(0, 255, 153));
		btnConcludo.setBounds(127, 280, 174, 68);
		contentPane.add(btnConcludo);
		
		JLabel lblNewLabel = new JLabel("Atual: " + qtdAtual + " L");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(new Color(0, 128, 128));
		lblNewLabel.setBounds(268, 162, 77, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblAdicionar = new JLabel("Adicionar");
		lblAdicionar.setBounds(76, 154, 85, 14);
		contentPane.add(lblAdicionar);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(76, 170, 85, 14);
		contentPane.add(lblQuantidade);
		
		preencherCampos();
		mostrarComboBox();

		btnConcludo = new JButton("CONFIRMAR");
		btnConcludo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				atualizarBebida();
				dispose();
			}
		});
		btnConcludo.setForeground(new Color(0, 0, 0));
		btnConcludo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConcludo.setBackground(new Color(0, 250, 154));
		btnConcludo.setBounds(76, 252, 269, 30);
		contentPane.add(btnConcludo);

		lblAdicionar = new JLabel("Adicionar");
		lblAdicionar.setBounds(76, 154, 85, 14);
		contentPane.add(lblAdicionar);

		lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(76, 170, 85, 14);
		contentPane.add(lblQuantidade);

		preencherCampos();
		mostrarComboBox();

	}

	public String getMarcaSelecionada() {
		return marcaSelecionada;
	}

	public void setMarcaSelecionada(String marcaSelecionada) {
		this.marcaSelecionada = marcaSelecionada;
	}
}
