package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.event.ChangeListener;

import model.Conexao;
import system.JtextFieldSomenteNumeros;

import javax.swing.event.ChangeEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.awt.event.ItemEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class ManipularMaquina extends JFrame {

	private JPanel contentPane;
	private JTextField textMarcaBebida;
	private JCheckBox chckbxRefrigerante;
	private JCheckBox chckbxCerveja;
	private JPanel panelAdicionarBebida;
	private JPanel panelEstoque;
	private JPanel panelHistorico;
	private String marca = "";

	private JList<String> listBebidas;
	private JList<String> listVendas;
	private JLabel labelArrecadado;
	private JLabel labelvendas;
	JComboBox<String> comboBox;

	private JTextField textValor;

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
					ManipularMaquina frame = new ManipularMaquina();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void inserirBebida() {
		String tipo;
		String query = "INSERT INTO opcoes (tipo, marca, quantidade, valor)" + "VALUES (?, ?, ?, ?)";
		double qtd = 0.0;

		if (chckbxRefrigerante.isSelected()) {
			tipo = "REFRIGERANTE";
		} else {
			tipo = "CERVEJA";
		}

		switch (comboBox.getSelectedIndex()) {
		case 0:
			qtd = 30;
			break;
		case 1:
			qtd = 40;
			break;
		case 2:
			qtd = 50;
			break;
		case 3:
			qtd = 100;
			break;
		}

		Conexao con = new Conexao();
		Connection conn = con.getConnection();

		try {
			PreparedStatement prep = conn.prepareStatement(query);
			prep.setString(1, tipo);
			prep.setString(2, textMarcaBebida.getText().toUpperCase());
			prep.setDouble(3, qtd);
			prep.setDouble(4, Double.parseDouble(textValor.getText()));

			prep.execute();

			prep.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
			return;
		}

		JOptionPane.showMessageDialog(null, "Bebida registrada com sucesso!");

	}

	public void mostrarListaEstoque() {

		String query = "SELECT tipo, marca, quantidade FROM opcoes ORDER BY tipo ASC";
		Conexao con = new Conexao();
		Connection conn = con.getConnection();
		DefaultListModel<String> ls = new DefaultListModel<String>();
		listBebidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		try {
			PreparedStatement prep = conn.prepareStatement(query);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				ls.addElement(
				rs.getString("tipo") + "  " + rs.getString("marca") + " - " + rs.getDouble("quantidade") + " L");
			}

			rs.close();
			prep.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
			return;
		}

		listBebidas.setModel(ls);

	}

	public void deletarBebida() {
		String query = "DELETE FROM opcoes WHERE marca = '" + marca + "'";

		Conexao con = new Conexao();
		Connection conn = con.getConnection();

		try {
			PreparedStatement prep = conn.prepareStatement(query);
			prep.execute();

			prep.close();
			conn.close();
			JOptionPane.showMessageDialog(null, "Bebida apagada com sucesso!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
			return;
		}

	}

	public void historico() {

		String query = "SELECT * FROM pedidos";
		Conexao con = new Conexao();
		Connection conn = con.getConnection();
		DefaultListModel<String> ls = new DefaultListModel<String>();
		listVendas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		try {
			PreparedStatement prep = conn.prepareStatement(query);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				ls.addElement(
						"Numero do Cartão: " + rs.getInt("nrCartao") + "  Número do pedido: " + rs.getInt("idPedido")
								+ "  Opção: " + rs.getInt("idOpcao") + "  Data: " + rs.getDate("dataPedido")
								+ "  Produto: " + rs.getString("compra") + "  Valor: " + rs.getDouble("valor"));
			}

			rs.close();
			prep.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
			return;
		}

		listVendas.setModel(ls);

	}

	public void valorArrecadado() {

		String query = "SELECT valor FROM pedidos";

		Conexao con = new Conexao();
		Connection conn = con.getConnection();

		try {
			PreparedStatement prep = conn.prepareStatement(query);
			ResultSet rs = prep.executeQuery();
			DecimalFormat df = new DecimalFormat();

			double soma = 0;
			df.format(soma);
			int vendas = 0;
			
			while (rs.next()) {

				soma = soma + rs.getDouble("valor");
				df.format(soma);
				vendas++;
			}
			
			labelArrecadado.setText("" + soma);
			labelvendas.setText(""+vendas);
			rs.close();
			prep.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
			return;
		}

	}

	/**
	 * Create the frame.
	 */
	public ManipularMaquina() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1163, 748);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAdicionarBebida = new JButton("Adicionar Bebida");
		btnAdicionarBebida.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAdicionarBebida.setBackground(new Color(192, 192, 192));
		btnAdicionarBebida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAdicionarBebida.setVisible(true);
				panelEstoque.setVisible(false);
				panelHistorico.setVisible(false);

				MostrarComboBox();
			}
		});
		btnAdicionarBebida.setBounds(37, 199, 189, 78);
		contentPane.add(btnAdicionarBebida);

		JButton btnVerBebidas = new JButton("Visualizar Bebidas");
		btnVerBebidas.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVerBebidas.setBackground(new Color(192, 192, 192));
		btnVerBebidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelEstoque.setVisible(true);
				panelAdicionarBebida.setVisible(false);
				panelHistorico.setVisible(false);

				mostrarListaEstoque();
			}
		});
		btnVerBebidas.setBounds(37, 117, 189, 71);
		contentPane.add(btnVerBebidas);

		JLabel lblControleDaMquina = new JLabel("Controle da M\u00E1quina");
		lblControleDaMquina.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblControleDaMquina.setBounds(362, 11, 199, 28);
		contentPane.add(lblControleDaMquina);

		panelAdicionarBebida = new JPanel();
		panelAdicionarBebida.setBackground(new Color(255, 255, 255));
		panelAdicionarBebida.setBounds(260, 61, 358, 334);
		contentPane.add(panelAdicionarBebida);
		panelAdicionarBebida.setLayout(null);

		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(32, 117, 60, 14);
		panelAdicionarBebida.add(lblMarca);

		textMarcaBebida = new JTextField();
		textMarcaBebida.setBounds(127, 106, 193, 29);
		panelAdicionarBebida.add(textMarcaBebida);
		textMarcaBebida.setColumns(10);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxRefrigerante.isSelected() == true) {

					inserirBebida();
					limparCampos();
				} else {

					if (chckbxCerveja.isSelected() == true) {

						inserirBebida();
						limparCampos();

					} else {

						JOptionPane.showMessageDialog(null, "Selecione o tipo da bebida!");
					}
				}
			}
		});
		btnAdicionar.setBackground(new Color(0, 250, 154));
		btnAdicionar.setBounds(211, 224, 109, 29);
		panelAdicionarBebida.add(btnAdicionar);

		JLabel lblModelo = new JLabel("Tipo:");
		lblModelo.setBounds(32, 78, 68, 14);
		panelAdicionarBebida.add(lblModelo);

		chckbxCerveja = new JCheckBox("Cerveja");
		chckbxCerveja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxCerveja.isSelected()) {
					chckbxRefrigerante.setSelected(false);
					;
				}
			}
		});

		chckbxCerveja.setBackground(new Color(255, 255, 255));
		chckbxCerveja.setBounds(127, 75, 97, 23);
		panelAdicionarBebida.add(chckbxCerveja);

		chckbxRefrigerante = new JCheckBox("Refrigerante");
		chckbxRefrigerante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxRefrigerante.isSelected()) {
					chckbxCerveja.setSelected(false);
					;
				}
			}
		});
		chckbxRefrigerante.setBackground(new Color(255, 255, 255));
		chckbxRefrigerante.setBounds(223, 75, 97, 23);
		panelAdicionarBebida.add(chckbxRefrigerante);

		JLabel lblNovaBebida = new JLabel("Nova - Bebida");
		lblNovaBebida.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNovaBebida.setBounds(107, 34, 148, 14);
		panelAdicionarBebida.add(lblNovaBebida);

		JLabel lblValor = new JLabel("Valor por litro:");
		lblValor.setBounds(32, 195, 85, 14);
		panelAdicionarBebida.add(lblValor);

		textValor = new JtextFieldSomenteNumeros(6);
		textValor.setBounds(127, 184, 193, 29);
		panelAdicionarBebida.add(textValor);
		textValor.setColumns(10);

		JLabel lblTamanho = new JLabel("Quantidade:");
		lblTamanho.setBounds(32, 158, 68, 14);
		panelAdicionarBebida.add(lblTamanho);

		comboBox = new JComboBox<String>();
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setBounds(127, 146, 193, 31);
		panelAdicionarBebida.add(comboBox);

		panelEstoque = new JPanel();
		panelEstoque.setBounds(260, 61, 539, 483);
		contentPane.add(panelEstoque);
		panelEstoque.setBackground(new Color(255, 255, 255));
		panelEstoque.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 60, 317, 373);
		panelEstoque.add(scrollPane);

		listBebidas = new JList<String>();

		listBebidas.setSelectedIndex(0);
		listBebidas.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (listBebidas.getSelectedValue() != null) {
					String[] format = listBebidas.getSelectedValue().split("  ");
					String[] result = {};
					result = format[1].split(" -");
					marca = result[0];
				}

			}
		});
		scrollPane.setViewportView(listBebidas);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setBackground(new Color(255, 255, 102));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (marca != "") {
					EditarBebida eb = new EditarBebida(marca);

					eb.setVisible(true);
				}
			}
		});
		btnEditar.setBounds(371, 58, 132, 33);
		panelEstoque.add(btnEditar);

		JButton btnRemover = new JButton("Remover");
		btnRemover.setBackground(new Color(255, 0, 0));
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (marca != "") {
					deletarBebida();
				}
			}
		});
		btnRemover.setBounds(371, 96, 132, 33);
		panelEstoque.add(btnRemover);

		JLabel lblEstoqueDeBebidas = new JLabel("Estoque de Bebidas");
		lblEstoqueDeBebidas.setBounds(121, 11, 153, 28);
		panelEstoque.add(lblEstoqueDeBebidas);
		lblEstoqueDeBebidas.setFont(new Font("Tahoma", Font.BOLD, 12));

		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnFinalizar.setBackground(new Color(255, 0, 0));
		btnFinalizar.setForeground(new Color(0, 0, 0));
		btnFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnFinalizar.setBounds(37, 13, 189, 28);
		contentPane.add(btnFinalizar);

		JButton btnHistorico = new JButton("Historico");
		btnHistorico.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnHistorico.setBackground(new Color(192, 192, 192));
		btnHistorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				historico();
				panelAdicionarBebida.setVisible(false);
				panelEstoque.setVisible(false);
				panelHistorico.setVisible(true);
				valorArrecadado();
			}
		});
		btnHistorico.setBounds(37, 287, 189, 78);
		contentPane.add(btnHistorico);

		panelHistorico = new JPanel();
		panelHistorico.setBackground(new Color(255, 255, 255));
		panelHistorico.setBounds(236, 36, 901, 577);
		contentPane.add(panelHistorico);
		panelHistorico.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(26, 71, 638, 495);
		panelHistorico.add(scrollPane_1);

		listVendas = new JList<String>();
		scrollPane_1.setViewportView(listVendas);

		JLabel lblVendasRealizadas = new JLabel("Hist\u00F3rico de Vendas  Realizadas");
		lblVendasRealizadas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVendasRealizadas.setBounds(281, 28, 325, 24);
		panelHistorico.add(lblVendasRealizadas);

		JLabel lblValorArrecadado = new JLabel("Valor Arrecadado:");
		lblValorArrecadado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValorArrecadado.setBounds(686, 129, 144, 24);
		panelHistorico.add(lblValorArrecadado);

		JLabel lblR = new JLabel("R$");
		lblR.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblR.setBounds(686, 164, 48, 14);
		panelHistorico.add(lblR);

		labelArrecadado = new JLabel("----");
		labelArrecadado.setForeground(new Color(255, 0, 0));
		labelArrecadado.setBackground(new Color(255, 255, 255));
		labelArrecadado.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelArrecadado.setBounds(729, 164, 74, 14);
		panelHistorico.add(labelArrecadado);
		
		JLabel lblQuantidadeDeVendas = new JLabel("Quantidade  de Vendas:");
		lblQuantidadeDeVendas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuantidadeDeVendas.setBounds(686, 75, 191, 27);
		panelHistorico.add(lblQuantidadeDeVendas);
		
		 labelvendas = new JLabel("----");
		labelvendas.setForeground(new Color(0, 0, 255));
		labelvendas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelvendas.setBounds(729, 102, 74, 24);
		panelHistorico.add(labelvendas);
		panelEstoque.setVisible(false);

		panelAdicionarBebida.setVisible(false);
		panelHistorico.setVisible(false);
	}

	public void limparCampos() {
		textMarcaBebida.setText("");
		textValor.setText("");
		chckbxCerveja.setSelected(false);
		chckbxRefrigerante.setSelected(false);

	}

	public void MostrarComboBox() {

		comboBox.removeAllItems();
		comboBox.addItem("30 Litros");
		comboBox.addItem("40 Litros");
		comboBox.addItem("50 Litros");
		comboBox.addItem("100 Litros");
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
}
