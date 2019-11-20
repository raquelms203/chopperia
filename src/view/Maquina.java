package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import model.Conexao;

import javax.swing.JComboBox;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class Maquina extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JLabel labelSaldoDoCliente;
	private JLabel labelNumeroCliente;

	private JList<String> listCerveja;
	private JList<String> listRefrigerante;

	JComboBox<String> comboBoxCerveja;
	JComboBox<String> comboBoxRefrigerante;

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
					Maquina frame = new Maquina();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void mostrarListaCerveja() {

		String query = "SELECT (marca) FROM opcoes WHERE tipo = 'CERVEJA'";
		Conexao con = new Conexao();
		Connection conn = con.getConnection();
		DefaultListModel<String> ls = new DefaultListModel<String>();
		listCerveja.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		try {
			PreparedStatement prep = conn.prepareStatement(query);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				ls.addElement(rs.getString("marca"));
			}

			rs.close();
			prep.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
			return;
		}

		listCerveja.setModel(ls);

	}

	public void mostrarListaRefrigerante() {

		String query = "SELECT (marca) FROM opcoes WHERE tipo = 'REFRIGERANTE'";
		Conexao con = new Conexao();
		Connection conn = con.getConnection();
		DefaultListModel<String> ls = new DefaultListModel<String>();
		listCerveja.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		try {
			PreparedStatement prep = conn.prepareStatement(query);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				ls.addElement(rs.getString("marca"));
			}

			rs.close();
			prep.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
			return;
		}

		listRefrigerante.setModel(ls);

	}

	public double mostrarPrecoBebida(String marca) {

		String query = "SELECT (valor) FROM opcoes WHERE marca = ?";
		Conexao con = new Conexao();
		Connection conn = con.getConnection();
		double preco = 0.0;

		try {
			PreparedStatement prep = conn.prepareStatement(query);
			prep.setString(1, marca);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				preco = rs.getDouble("valor");
			}

			rs.close();
			prep.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
			return 0.0;
		}

		return preco;

	}
	
	public void MostrarComboBoxRefrigerante(double preco) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		comboBoxRefrigerante.removeAllItems();
		comboBoxRefrigerante.addItem("300 ML " + formatter.format(preco * 0.3));
		comboBoxRefrigerante.addItem("500 ML " + formatter.format(preco * 0.5));
		comboBoxRefrigerante.addItem("700 ML " + formatter.format(preco * 0.7));
		comboBoxRefrigerante.addItem("1 Litro " + formatter.format(preco));
	}

	public void MostrarComboBoxCerveja(double preco) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		comboBoxCerveja.removeAllItems();
		comboBoxCerveja.addItem("300 ML " + formatter.format(preco * 0.3));
		comboBoxCerveja.addItem("500 ML " + formatter.format(preco * 0.5));
		comboBoxCerveja.addItem("700 ML " + formatter.format(preco * 0.7));
		comboBoxCerveja.addItem("1 Litro " + formatter.format(preco));
	}
	
	
	public Maquina() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 868, 684);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMaquina = new JLabel("M\u00E1quina de Bebidas");
		lblMaquina.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaquina.setBounds(255, 3, 184, 50);
		contentPane.add(lblMaquina);

		JLabel lblCartao = new JLabel("Cart\u00E3o:");
		lblCartao.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCartao.setBounds(22, 58, 72, 14);
		contentPane.add(lblCartao);

		JLabel lblSaldo = new JLabel("Saldo:");
		lblSaldo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSaldo.setBounds(22, 83, 72, 14);
		contentPane.add(lblSaldo);

		labelSaldoDoCliente = new JLabel("----");
		labelSaldoDoCliente.setForeground(Color.RED);
		labelSaldoDoCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelSaldoDoCliente.setBounds(104, 80, 115, 17);
		contentPane.add(labelSaldoDoCliente);

		labelNumeroCliente = new JLabel("----");
		labelNumeroCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelNumeroCliente.setBounds(104, 58, 115, 14);
		contentPane.add(labelNumeroCliente);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 202, 223, 357);
		contentPane.add(scrollPane);

		listCerveja = new JList<String>();
		listCerveja.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {

				MostrarComboBoxCerveja(mostrarPrecoBebida(listCerveja.getSelectedValue()));
			}
		});
		scrollPane.setViewportView(listCerveja);

		JButton btnPersonalizarBebida = new JButton("Personalizar Bebida");
		btnPersonalizarBebida.setBackground(Color.LIGHT_GRAY);
		btnPersonalizarBebida.setForeground(Color.DARK_GRAY);
		btnPersonalizarBebida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPersonalizarBebida.setBounds(670, 309, 172, 61);
		contentPane.add(btnPersonalizarBebida);

		JLabel lblCervejas = new JLabel("Cervejas");
		lblCervejas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCervejas.setBounds(99, 166, 115, 25);
		contentPane.add(lblCervejas);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(437, 202, 223, 357);
		contentPane.add(scrollPane_1);

		listRefrigerante = new JList<String>();
		listRefrigerante.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				MostrarComboBoxRefrigerante(mostrarPrecoBebida(listRefrigerante.getSelectedValue()));
			}
		});
		scrollPane_1.setViewportView(listRefrigerante);

		JLabel lblNewLabel_1 = new JLabel("Refrigerante");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(497, 166, 115, 27);
		contentPane.add(lblNewLabel_1);

		JButton btnComprarRefrigerante = new JButton("Comprar");
		btnComprarRefrigerante.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnComprarRefrigerante.setBackground(new Color(0, 153, 204));
		btnComprarRefrigerante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnComprarRefrigerante.setBounds(670, 237, 172, 61);
		contentPane.add(btnComprarRefrigerante);

		JButton btnComprarCerveja = new JButton("Comprar");
		btnComprarCerveja.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnComprarCerveja.setBounds(255, 236, 172, 63);
		contentPane.add(btnComprarCerveja);
		btnComprarCerveja.setBackground(new Color(0, 153, 204));

		JLabel lblBebidasDisponiveis = new JLabel("BEBIDAS DISPON\u00CDVEIS");
		lblBebidasDisponiveis.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBebidasDisponiveis.setBounds(239, 120, 258, 27);
		contentPane.add(lblBebidasDisponiveis);

		JButton btnFinalizar = new JButton("Finalizar Acesso!");
		btnFinalizar.setForeground(Color.WHITE);
		btnFinalizar.setBackground(new Color(0, 255, 102));
		btnFinalizar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
				// implementar o fechamento ao acesso do BD
			}
		});
		btnFinalizar.setBounds(22, 573, 820, 61);
		contentPane.add(btnFinalizar);

		comboBoxCerveja = new JComboBox<String>();
		comboBoxCerveja.setBackground(new Color(255, 255, 255));
		comboBoxCerveja.setBounds(255, 200, 172, 27);
		contentPane.add(comboBoxCerveja);

		comboBoxRefrigerante = new JComboBox<String>();
		comboBoxRefrigerante.setBackground(new Color(255, 255, 255));
		comboBoxRefrigerante.setBounds(670, 200, 172, 25);
		contentPane.add(comboBoxRefrigerante);

		JLabel lblNewLabel = new JLabel("Selecione a quantidade");
		lblNewLabel.setBounds(283, 173, 158, 14);
		contentPane.add(lblNewLabel);

		JLabel label = new JLabel("Selecione a quantidade");
		label.setBounds(695, 173, 147, 14);
		contentPane.add(label);
		
		JButton btnVisualizarCartao = new JButton("Visualizar Cart\u00E3o");
		btnVisualizarCartao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ClienteView cartaoCliente = new ClienteView();
				cartaoCliente.setVisible(true);
				cartaoCliente.setLocationRelativeTo(null);
			}
		});
		btnVisualizarCartao.setBounds(670, 11, 172, 33);
		contentPane.add(btnVisualizarCartao);
		
		JButton button = new JButton("Personalizar Bebida");
		button.setBackground(Color.LIGHT_GRAY);
		button.setForeground(Color.DARK_GRAY);
		button.setBounds(255, 309, 172, 61);
		contentPane.add(button);
		btnComprarCerveja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		mostrarListaCerveja();
		mostrarListaRefrigerante();
	}



	public JLabel getLabelSaldoDoCliente() {
		return labelSaldoDoCliente;
	}

	public void setLabelSaldoDoCliente(JLabel labelSaldoDoCliente) {
		this.labelSaldoDoCliente = labelSaldoDoCliente;
	}

	public JLabel getLabelNumeroCliente() {
		return labelNumeroCliente;
	}

	public void setLabelNumeroCliente(JLabel labelNumeroCliente) {
		this.labelNumeroCliente = labelNumeroCliente;
	}

	public JList<String> getListCerveja() {
		return listCerveja;
	}

	public void setListCerveja(JList<String> listCerveja) {
		this.listCerveja = listCerveja;
	}

	public JList getListRefrigerante() {
		return listRefrigerante;
	}

	public void setListRefrigerante(JList<String> listRefrigerante) {
		this.listRefrigerante = listRefrigerante;
	}
}
