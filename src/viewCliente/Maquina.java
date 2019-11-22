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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Conexao;

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

	private int NUMEROCARTAOMANIPULADO;

	private ClienteView cartaoCliente;

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
					Maquina frame = new Maquina(-1);
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

	public Date stringParaDate(String txtdata) {

		String format = "" + txtdata.charAt(6) + txtdata.charAt(7) + txtdata.charAt(8) + txtdata.charAt(9) + '-'
				+ txtdata.charAt(3) + txtdata.charAt(4) + '-' + txtdata.charAt(0) + txtdata.charAt(1);

		Date date = Date.valueOf(format);

		return date;
	}

	public int calculaIdade(Date dataNasc) {

		Calendar dateOfBirth = new GregorianCalendar();

		dateOfBirth.setTime(dataNasc);

		// Cria um objeto calendar com a data atual

		Calendar today = Calendar.getInstance();

		// Obtém a idade baseado no ano

		int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

		dateOfBirth.add(Calendar.YEAR, age);

		// se a data de hoje é antes da data de Nascimento, então diminui 1(um)

		if (today.before(dateOfBirth)) {

			age--;

		}

		return age;

	}

	public void verificaIdade(String idade) {

		if (NUMEROCARTAOMANIPULADO < 0) {
			return;
		}
		int idadeAtual = calculaIdade(stringParaDate(idade));

		if (idadeAtual >= 18) {

			mostrarListaCerveja();
			mostrarListaRefrigerante();

		} else {

			mostrarListaRefrigerante();
		}

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
			JOptionPane.showMessageDialog(null, e);
			return;
		}

		listRefrigerante.setModel(ls);

	}

	public boolean acessoCartao() {

		String query = "SELECT * FROM cartoes WHERE \"nrcartao\" = ?";

		Conexao con = new Conexao();

		Connection conn = con.getConnection();

		boolean result = false;

		PreparedStatement prep;
		try {

			prep = conn.prepareStatement(query);
			prep.setInt(1, getNUMEROCARTAOMANIPULADO());

			ResultSet res = prep.executeQuery();

			if (res.next()) {

				DecimalFormat df = new DecimalFormat("0.00");

				cartaoCliente.getLbNomeCliente().setText("" + res.getString("nome"));
				cartaoCliente.getLbSaldoClient().setText("" + df.format(res.getDouble("saldo")));
				cartaoCliente.getLbCPFCliente().setText("" + res.getInt("cpfcartao"));
				cartaoCliente.getLbDataNascClient().setText(dateParaString(res.getDate("dataNasc")));
				cartaoCliente.getLbCartaoCliente().setText("" + NUMEROCARTAOMANIPULADO);

				result = true;
			}

			res.close();
			prep.close();
			conn.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}

		return result;

	}

	public void refresh() {

		if (NUMEROCARTAOMANIPULADO < 0) {

			return;
		}
		acessoCartao();
		labelNumeroCliente.setText(cartaoCliente.getLbCartaoCliente().getText());
		labelSaldoDoCliente.setText(cartaoCliente.getLbSaldoClient().getText());
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
			JOptionPane.showMessageDialog(null, e);
			return 0.0;
		}

		return preco;

	}

	public void comprarCerveja(String marca) {

		if (NUMEROCARTAOMANIPULADO < 0) {

			return;
		}

		Conexao con = new Conexao();
		Connection conn = con.getConnection();

		String query = "";
		try {

			query = "SELECT quantidade FROM opcoes WHERE marca = ?";

			PreparedStatement prep = conn.prepareStatement(query);
			prep.setString(1, marca);
			ResultSet rs = prep.executeQuery();

			double quantidade = 0;
			while (rs.next()) {
				quantidade = rs.getDouble("quantidade");

			}

			double selecao = 0;

			switch (comboBoxCerveja.getSelectedIndex()) {
			case 0:
				selecao = 0.3;
				break;
			case 1:
				selecao = 0.5;
				break;
			case 2:
				selecao = 0.7;
				break;
			case 3:
				selecao = 1;
				break;

			default:
				break;
			}

			if (quantidade <= selecao) {
				rs.close();
				prep.close();
				conn.close();

				JOptionPane.showMessageDialog(null, "Desculpe, o Estoque acabou!");
				return;
			} else {
				rs.close();
				prep.close();

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

		DecimalFormat df = new DecimalFormat("00.00");
		double valor = 0;
		query = "SELECT (valor) FROM opcoes WHERE marca = ?";

		try {

			PreparedStatement prep = conn.prepareStatement(query);
			prep.setString(1, marca);

			ResultSet rs = prep.executeQuery();

			while (rs.next()) {

				valor = rs.getDouble("valor");
			}

			rs.close();
			prep.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

		double saldo = 0;
		df.format(saldo);

		query = "SELECT (saldo) FROM cartoes WHERE \"nrcartao\" = ?";

		try {

			PreparedStatement prep = conn.prepareStatement(query);
			prep.setInt(1, getNUMEROCARTAOMANIPULADO());

			ResultSet rs = prep.executeQuery();

			while (rs.next()) {

				saldo = rs.getDouble("saldo");
			}

			rs.close();
			prep.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

		switch (comboBoxCerveja.getSelectedIndex()) {
		case 0:
			valor = valor * 0.3;
			df.format(valor);
			break;
		case 1:
			valor = valor * 0.5;
			df.format(valor);

			break;
		case 2:
			valor = valor * 0.7;
			df.format(valor);

			break;
		case 3:
			valor = valor * 1;
			df.format(valor);

			break;

		default:
			break;
		}

		if (saldo >= valor) {

			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			saldo = saldo - valor;

			formatter.format(saldo);

			query = "UPDATE cartoes SET saldo = ? WHERE \"nrcartao\" = ?";

			try {

				PreparedStatement prep = conn.prepareStatement(query);
				prep.setDouble(1, saldo);
				prep.setInt(2, getNUMEROCARTAOMANIPULADO());

				prep.executeUpdate();
				prep.close();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);

			}

			query = "SELECT quantidade FROM opcoes WHERE marca = ?";
			double tamanho = 1.00;
			double quantidade = 0;
			df.format(tamanho);
			df.format(quantidade);

			try {

				PreparedStatement prep = conn.prepareStatement(query);
				prep.setString(1, marca);

				ResultSet rs = prep.executeQuery();

				while (rs.next()) {

					quantidade = rs.getDouble("quantidade");
				}

				df.format(quantidade);
				rs.close();
				prep.close();

				switch (comboBoxCerveja.getSelectedIndex()) {

				case 0:
					tamanho = 0.3;
					df.format(tamanho);

					break;
				case 1:
					tamanho = 0.5;
					df.format(tamanho);

					break;
				case 2:
					tamanho = 0.7;
					df.format(tamanho);

					break;
				case 3:
					tamanho = tamanho / 1;
					df.format(tamanho);

					break;
				default:
					break;
				}

				quantidade = quantidade - tamanho;
				df.format(quantidade);

			} catch (Exception e) {

				JOptionPane.showMessageDialog(null, e);
			}

			try {

				query = "UPDATE opcoes SET quantidade = ? WHERE marca = ?";

				PreparedStatement prep = conn.prepareStatement(query);

				prep.setDouble(1, quantidade);
				prep.setString(2, marca);
				prep.executeUpdate();

				prep.close();
				conn.close();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "EROW" + e);
			}

		} else {

			JOptionPane.showMessageDialog(null, "Você não possui saldo suficiente");
		}

		refresh();

	}

	public void comprarRefrigerante(String marca) {

		if (NUMEROCARTAOMANIPULADO < 0) {

			return;
		}

		Conexao con = new Conexao();
		Connection conn = con.getConnection();

		String query = "";

		try {

			query = "SELECT (quantidade) FROM opcoes WHERE marca = ?";

			PreparedStatement prep = conn.prepareStatement(query);
			prep.setString(1, marca);

			ResultSet rs = prep.executeQuery();

			double quantidade = 0;
			while (rs.next()) {
				quantidade = rs.getDouble("quantidade");

			}

			double selecao = 0;

			switch (comboBoxRefrigerante.getSelectedIndex()) {
			case 0:
				selecao = 0.3;
				break;
			case 1:
				selecao = 0.5;
				break;
			case 2:
				selecao = 0.7;
				break;
			case 3:
				selecao = 1;
				break;

			default:
				break;
			}

			if (quantidade <= selecao) {
				rs.close();
				prep.close();
				conn.close();

				JOptionPane.showMessageDialog(null, "Desculpe, o Estoque acabou!");
				return;
			} else {
				rs.close();
				prep.close();

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

		DecimalFormat df = new DecimalFormat("00.00");

		double valor = 0;
		df.format(valor);
		query = "SELECT (valor) FROM opcoes WHERE marca = ?";

		try {

			PreparedStatement prep = conn.prepareStatement(query);
			prep.setString(1, marca);

			ResultSet rs = prep.executeQuery();

			while (rs.next()) {

				valor = rs.getDouble("valor");
			}
			df.format(valor);
			rs.close();
			prep.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

		double saldo = 0;
		df.format(saldo);

		query = "SELECT (saldo) FROM cartoes WHERE \"nrcartao\" = ?";

		try {

			PreparedStatement prep = conn.prepareStatement(query);
			prep.setInt(1, getNUMEROCARTAOMANIPULADO());

			ResultSet rs = prep.executeQuery();

			while (rs.next()) {

				saldo = rs.getDouble("saldo");
			}
			df.format(saldo);
			rs.close();
			prep.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

		switch (comboBoxRefrigerante.getSelectedIndex()) {
		case 0:
			valor = valor * 0.3;
			df.format(valor);
			break;
		case 1:
			valor = valor * 0.5;
			df.format(valor);

			break;
		case 2:
			valor = valor * 0.7;
			df.format(valor);

			break;
		case 3:
			valor = valor * 1;
			df.format(valor);

			break;

		default:
			break;
		}

		if (saldo >= valor) {

			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			saldo = saldo - valor;

			formatter.format(saldo);

			query = "UPDATE cartoes SET saldo = ? WHERE \"nrcartao\" = ?";

			try {

				PreparedStatement prep = conn.prepareStatement(query);
				prep.setDouble(1, saldo);
				prep.setInt(2, getNUMEROCARTAOMANIPULADO());

				prep.executeUpdate();
				prep.close();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);

			}

			double tamanho = 1.00;
			double quantidade = 0;
			df.format(tamanho);
			df.format(quantidade);

			try {

				query = "SELECT quantidade FROM opcoes WHERE marca = ?";
				PreparedStatement prep = conn.prepareStatement(query);
				prep.setString(1, marca);

				ResultSet rs = prep.executeQuery();

				while (rs.next()) {

					quantidade = rs.getDouble("quantidade");
					df.format(quantidade);

				}

				rs.close();
				prep.close();

				switch (comboBoxRefrigerante.getSelectedIndex()) {

				case 0:
					tamanho = 0.3;
					df.format(tamanho);

					break;
				case 1:
					tamanho = 0.5;
					df.format(tamanho);

					break;
				case 2:
					tamanho = 0.7;
					df.format(tamanho);

					break;
				case 3:
					tamanho = tamanho / 1;
					df.format(tamanho);

					break;
				default:
					break;
				}

				quantidade = quantidade - tamanho;
				df.format(quantidade);

			} catch (Exception e) {

				JOptionPane.showMessageDialog(null, e);
			}

			try {

				query = "UPDATE opcoes SET quantidade = ? WHERE marca = ?";

				PreparedStatement prep = conn.prepareStatement(query);

				prep.setDouble(1, quantidade);
				prep.setString(2, marca);
				prep.executeUpdate();

				prep.close();
				conn.close();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "EROW" + e);
			}

		} else {

			JOptionPane.showMessageDialog(null, "Você não possui saldo suficiente");
		}

		refresh();

	}

	public void fazerPedido() {
		String marca = "";
		int id = -1;
		java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
		
		if (!listCerveja.isSelectionEmpty())
			marca = listCerveja.getSelectedValue();

		else if (!listRefrigerante.isSelectionEmpty())
			marca = listRefrigerante.getSelectedValue();
		
		String query = "SELECT \"idOpcao\" FROM opcoes WHERE marca = '" + marca + "'";
		
		String query2 = "INSERT INTO pedidos (\"idOpcao\", \"nrCartao\", \"dataPedido\") "+ 
				"	VALUES ( ?, ?, ?); ";
		Conexao con = new Conexao();
		Connection conn = con.getConnection();
		
		try {
			PreparedStatement prep = conn.prepareStatement(query);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()) {
				id = rs.getInt("idOpcao");
			}
			
			prep.close();
			rs.close();
			
			PreparedStatement prep2 = conn.prepareStatement(query2);
			prep2.setInt(1, id);
			prep2.setInt(2, NUMEROCARTAOMANIPULADO);
			prep2.setDate(3, sqlDate);
			
			prep2.execute();
			
			prep2.close();
			conn.close();
			
			JOptionPane.showMessageDialog(null, "Pedido realizado com sucesso!");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
		
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

	public Maquina(int numeroCartao) {

		NUMEROCARTAOMANIPULADO = numeroCartao;
		cartaoCliente = new ClienteView();
		acessoCartao();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

		JLabel lblBebidasDisponiveis = new JLabel("BEBIDAS DISPON\u00CDVEIS");
		lblBebidasDisponiveis.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBebidasDisponiveis.setBounds(239, 120, 258, 27);
		contentPane.add(lblBebidasDisponiveis);

		JButton btnFinalizar = new JButton("Finalizar Acesso!");
		btnFinalizar.setForeground(new Color(0, 0, 0));
		btnFinalizar.setBackground(new Color(0, 250, 154));
		btnFinalizar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (NUMEROCARTAOMANIPULADO == -1) {

					dispose();
				} else {
					fazerPedido();
					LoginCliente.frame.setVisible(true);
					NUMEROCARTAOMANIPULADO = -1;
					dispose();
					// implementar o fechamento ao acesso do BD
				}
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

				cartaoCliente.setVisible(true);
				cartaoCliente.setLocationRelativeTo(null);

			}
		});
		btnVisualizarCartao.setBounds(670, 11, 172, 33);
		contentPane.add(btnVisualizarCartao);

		labelNumeroCliente.setText(cartaoCliente.getLbCartaoCliente().getText());
		labelSaldoDoCliente.setText("R$ " + cartaoCliente.getLbSaldoClient().getText());

		JButton btnRefresh = new JButton("()");
		btnRefresh.setBackground(new Color(255, 255, 255));
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				refresh();
			}
		});
		btnRefresh.setBounds(624, 11, 44, 33);
		contentPane.add(btnRefresh);

		verificaIdade(cartaoCliente.getLbDataNascClient().getText());
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

	public JList<String> getListRefrigerante() {
		return listRefrigerante;
	}

	public void setListRefrigerante(JList<String> listRefrigerante) {
		this.listRefrigerante = listRefrigerante;
	}

	public int getNUMEROCARTAOMANIPULADO() {
		return NUMEROCARTAOMANIPULADO;
	}

	public void setNUMEROCARTAOMANIPULADO(int nUMEROCARTAOMANIPULADO) {
		NUMEROCARTAOMANIPULADO = nUMEROCARTAOMANIPULADO;
	}

	public ClienteView getCartaoCliente() {
		return cartaoCliente;
	}

	public void setCartaoCliente(ClienteView cartaoCliente) {
		this.cartaoCliente = cartaoCliente;
	}
}
