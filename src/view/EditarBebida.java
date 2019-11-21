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
public class EditarBebida extends JFrame {

	private JPanel contentPane;
	private JTextField txtMarca;
	private JTextField txtValor;
	private String marcaSelecionada;
	private JCheckBox chckbxCerveja;
	private JCheckBox chckbxRefrigerante;
	private JComboBox<String> comboBox;
	private String qtdAtual;
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
		
		try {
			PreparedStatement prep = conn.prepareStatement(query);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("tipo").equalsIgnoreCase("CERVEJA")) {
					
					chckbxCerveja.setSelected(true);
				} else if (rs.getString("tipo") ==  "REFRIGERANTE") {
					chckbxRefrigerante.setSelected(true);
					JOptionPane.showMessageDialog(null, "ok");
				}
				
				txtMarca.setText(marcaSelecionada);
				
				qtdAtual = df.format(rs.getDouble("quantidade"));
				
				txtValor.setText(rs.getDouble("valor")+ "");
				
				JLabel lblNewLabel = new JLabel("Atual: " + qtdAtual + " L");
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblNewLabel.setForeground(new Color(0, 128, 128));
				lblNewLabel.setBounds(268, 162, 77, 14);
				contentPane.add(lblNewLabel);
				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			// TODO: handle exception
		}
	}
	
	public void mostrarComboBox() {

		comboBox.removeAllItems();
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 422, 428);
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
		
		txtMarca = new JTextField();
		txtMarca.setBounds(171, 108, 174, 30);
		contentPane.add(txtMarca);
		txtMarca.setColumns(10);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(171, 154, 91, 30);
		contentPane.add(comboBox);
		
		JLabel lblValorPorLitro = new JLabel("Valor por litro:");
		lblValorPorLitro.setBounds(76, 211, 86, 14);
		contentPane.add(lblValorPorLitro);
		
		txtValor = new JTextField();
		txtValor.setColumns(10);
		txtValor.setBounds(171, 205, 174, 30);
		contentPane.add(txtValor);
		
		JButton btnConcludo = new JButton("CONFIRMAR");
		btnConcludo.setForeground(new Color(0, 0, 0));
		btnConcludo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConcludo.setBackground(new Color(0, 255, 153));
		btnConcludo.setBounds(127, 280, 174, 68);
		contentPane.add(btnConcludo);
		
	
		
		JLabel lblAdicionar = new JLabel("Adicionar");
		lblAdicionar.setBounds(76, 154, 85, 14);
		contentPane.add(lblAdicionar);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
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
