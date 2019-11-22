package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Conexao;
import system.JtextFieldSomenteLetras;
import system.JtextFieldSomenteNumeros;
import system.MudarSenha;
import viewCliente.Maquina;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class GerenteView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JPanel panelVerVendedores;
	private JPanel panelCadastrarVendedor;
	private JTextField textNovoNomeVendedor;
	private JTextField textNovoCPFvendedor;
	private JTextField textUserVendedor;
	private JPasswordField passwordVendedor;

	private JList<String> listFuncionarios;

	private JCheckBox chckbxGerente;
	private JCheckBox chckbxVendedor;
	private JCheckBox chckbxFeminino;
	private JCheckBox chckbxMasculino;
	private JLabel labelGerente;
	
	private int cpf;

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
					GerenteView frame = new GerenteView();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public String conversordeSenha() {

		char[] st = passwordVendedor.getPassword();
		String senha = "";
		for (int i = 0; i < st.length; i++) {

			senha = senha + st[i];
		}

		return senha;
	}

	public void insereFuncionario() {

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

		String query = "INSERT INTO funcionarios (nome, \"cpffuncionario\", cargo, \"usuario\", senha, sexo) VALUES"
				+ " (?, ?, ?, ?, ?, ?)";

		Conexao con = new Conexao();

		Connection conn = con.getConnection();
		PreparedStatement prep;

		try {
			prep = conn.prepareStatement(query);
			prep.setString(1, textNovoNomeVendedor.getText().toUpperCase());
			prep.setInt(2, Integer.parseInt(textNovoCPFvendedor.getText()));
			prep.setString(3, cargo);
			prep.setString(4, textUserVendedor.getText());
			prep.setString(5, conversordeSenha());
			prep.setString(6, sexo);

			prep.execute();
			prep.close();

		} catch (SQLException e) {
			textUserVendedor.setText("");
			JOptionPane.showMessageDialog(null, "Usuario já existente" + e);
			return;
		}

		JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
		limpar();

	}

	public void deleteFuncionario(int CpfFuncionario) {

		String query = "DELETE FROM funcionarios WHERE cpffuncionario = ?";

		Conexao con = new Conexao();

		Connection conn = con.getConnection();
		PreparedStatement prep;

		try {
			prep = conn.prepareStatement(query);
			prep.setInt(1, CpfFuncionario);
			prep.execute();
			prep.close();

			JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
			mostrarListaFuncionarios();
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, "Não pode ser deletado!");
			return;
		}

	}

	public void mostrarListaFuncionarios() {

		String query = "SELECT nome, cargo, \"cpffuncionario\" FROM funcionarios";

		Conexao con = new Conexao();
		Connection conn = con.getConnection();

		DefaultListModel<String> ls = new DefaultListModel<String>();
		listFuncionarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		try {
			PreparedStatement prep = conn.prepareStatement(query);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				ls.addElement(
						rs.getInt("Cpffuncionario") + "    " + rs.getString("nome") + "    " + rs.getString("cargo"));
			}

			rs.close();
			prep.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
			return;
		}

		listFuncionarios.setModel(ls);

	}

	public GerenteView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 882, 632);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPainelDoGerente = new JLabel("Painel do Gerente");
		lblPainelDoGerente.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPainelDoGerente.setBounds(302, 6, 190, 38);
		contentPane.add(lblPainelDoGerente);

		JButton btnManipularMaquina = new JButton("Manipular Maquina");
		btnManipularMaquina.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnManipularMaquina.setBackground(new Color(255, 255, 255));
		btnManipularMaquina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ManipulaMaquina();

			}
		});
		btnManipularMaquina.setBounds(31, 328, 213, 86);
		contentPane.add(btnManipularMaquina);

		JButton btnCadastrarVendedor = new JButton("Cadastrar Funcion\u00E1rio");
		btnCadastrarVendedor.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCadastrarVendedor.setBackground(new Color(255, 255, 255));
		btnCadastrarVendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCadastrarVendedor.setVisible(true);
				panelVerVendedores.setVisible(false);
			}
		});
		btnCadastrarVendedor.setBounds(31, 231, 213, 86);
		contentPane.add(btnCadastrarVendedor);

		JButton btnVerVendedores = new JButton("Ver Funcion\u00E1rios");
		btnVerVendedores.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVerVendedores.setBackground(new Color(255, 255, 255));
		btnVerVendedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				panelCadastrarVendedor.setVisible(false);
				panelVerVendedores.setVisible(true);
				mostrarListaFuncionarios();

			}
		});
		btnVerVendedores.setBounds(31, 141, 213, 79);
		contentPane.add(btnVerVendedores);

		panelVerVendedores = new JPanel();
		panelVerVendedores.setBounds(276, 60, 401, 454);
		contentPane.add(panelVerVendedores);
		panelVerVendedores.setBackground(new Color(255, 255, 255));
		panelVerVendedores.setLayout(null);

		JLabel lblListaDeVendedores = new JLabel("Lista de Vendedores");
		lblListaDeVendedores.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblListaDeVendedores.setBounds(87, 11, 147, 27);
		panelVerVendedores.add(lblListaDeVendedores);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 49, 253, 380);
		panelVerVendedores.add(scrollPane);

		listFuncionarios = new JList<String>();
		scrollPane.setViewportView(listFuncionarios);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] format = listFuncionarios.getSelectedValue().split("    ");

				EditarFuncionario editarfuncionario =  new EditarFuncionario(Integer.parseInt(format[0]));
				editarfuncionario.setVisible(true);
			}
		});
		btnEditar.setBackground(new Color(255, 255, 0));
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEditar.setBounds(294, 47, 89, 23);
		panelVerVendedores.add(btnEditar);

		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String[] format = listFuncionarios.getSelectedValue().split("    ");

				deleteFuncionario(Integer.parseInt(format[0]));

			}
		});
		btnDeletar.setBackground(new Color(255, 0, 0));
		btnDeletar.setBounds(294, 76, 89, 23);
		panelVerVendedores.add(btnDeletar);
		
		JButton button = new JButton("()");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(31, 15, 46, 23);
		panelVerVendedores.add(button);

		panelCadastrarVendedor = new JPanel();
		panelCadastrarVendedor.setBounds(329, 118, 401, 338);
		contentPane.add(panelCadastrarVendedor);
		panelCadastrarVendedor.setBackground(new Color(255, 255, 255));
		panelCadastrarVendedor.setLayout(null);

		JButton btnCadastrarNovoVendedor = new JButton("Cadastrar");
		btnCadastrarNovoVendedor.setBackground(new Color(0, 250, 154));
		btnCadastrarNovoVendedor.setForeground(new Color(0, 0, 0));
		btnCadastrarNovoVendedor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCadastrarNovoVendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insereFuncionario();

			}
		});
		btnCadastrarNovoVendedor.setBounds(243, 263, 109, 23);
		panelCadastrarVendedor.add(btnCadastrarNovoVendedor);

		textNovoNomeVendedor = new JtextFieldSomenteLetras(50);
		textNovoNomeVendedor.setBounds(110, 59, 242, 23);
		panelCadastrarVendedor.add(textNovoNomeVendedor);
		textNovoNomeVendedor.setColumns(10);

		textNovoCPFvendedor = new JtextFieldSomenteNumeros(11);
		textNovoCPFvendedor.setBounds(110, 85, 242, 25);
		panelCadastrarVendedor.add(textNovoCPFvendedor);
		textNovoCPFvendedor.setColumns(10);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(39, 63, 62, 14);
		panelCadastrarVendedor.add(lblNome);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(39, 93, 77, 14);
		panelCadastrarVendedor.add(lblCpf);

		JLabel lblCadastrarFuncionario = new JLabel("Cadastrar  -  Funcion\u00E1rio");
		lblCadastrarFuncionario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCadastrarFuncionario.setBounds(110, 25, 215, 25);
		panelCadastrarVendedor.add(lblCadastrarFuncionario);

		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setBounds(39, 118, 46, 22);
		panelCadastrarVendedor.add(lblCargo);

		JLabel lblUser = new JLabel("User:");
		lblUser.setBounds(39, 198, 46, 14);
		panelCadastrarVendedor.add(lblUser);

		textUserVendedor = new JTextField();
		textUserVendedor.setBounds(110, 194, 242, 27);
		panelCadastrarVendedor.add(textUserVendedor);
		textUserVendedor.setColumns(10);

		JLabel lblPassaword = new JLabel("Passaword:");
		lblPassaword.setBounds(39, 231, 77, 14);
		panelCadastrarVendedor.add(lblPassaword);

		passwordVendedor = new JPasswordField();
		passwordVendedor.setBounds(110, 225, 242, 27);
		panelCadastrarVendedor.add(passwordVendedor);

		chckbxGerente = new JCheckBox("Gerente");
		chckbxGerente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxGerente.isSelected()) {
					chckbxVendedor.setSelected(false);

				}
			}
		});
		chckbxGerente.setBackground(new Color(255, 255, 255));
		chckbxGerente.setBounds(110, 117, 97, 23);
		panelCadastrarVendedor.add(chckbxGerente);

		chckbxVendedor = new JCheckBox("Vendedor");
		chckbxVendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxVendedor.isSelected()) {

					chckbxGerente.setSelected(false);

				}
			}
		});
		chckbxVendedor.setBackground(new Color(255, 255, 255));
		chckbxVendedor.setBounds(255, 117, 97, 23);
		panelCadastrarVendedor.add(chckbxVendedor);

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(39, 158, 48, 14);
		panelCadastrarVendedor.add(lblSexo);

		chckbxMasculino = new JCheckBox("Masculino");
		chckbxMasculino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (chckbxMasculino.isSelected()) {
					chckbxFeminino.setSelected(false);
				}
			}
		});
		chckbxMasculino.setBackground(new Color(255, 255, 255));
		chckbxMasculino.setBounds(110, 154, 97, 23);
		panelCadastrarVendedor.add(chckbxMasculino);

		chckbxFeminino = new JCheckBox("Feminino");
		chckbxFeminino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (chckbxFeminino.isSelected()) {
					chckbxMasculino.setSelected(false);
				}
			}
		});
		chckbxFeminino.setBackground(new Color(255, 255, 255));
		chckbxFeminino.setBounds(255, 154, 97, 23);
		panelCadastrarVendedor.add(chckbxFeminino);

		JButton btnNewButton = new JButton("Finalizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(31, 11, 117, 33);
		contentPane.add(btnNewButton);
		
		labelGerente = new JLabel("----");
		labelGerente.setForeground(new Color(128, 128, 128));
		labelGerente.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelGerente.setBounds(478, 4, 334, 44);
		contentPane.add(labelGerente);
		
		JButton btnNewButton_1 = new JButton("Alterar Senha");
		btnNewButton_1.setBackground(Color.YELLOW);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MudarSenha mudarSenha = new MudarSenha();
				mudarSenha.setCpf(getCpf());
				mudarSenha.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(155, 11, 117, 33);
		contentPane.add(btnNewButton_1);
		panelCadastrarVendedor.setVisible(false);
		panelVerVendedores.setVisible(false);

		panelVerVendedores.setVisible(false);

	}

	public void limpar() {
		textNovoCPFvendedor.setText("");
		textNovoNomeVendedor.setText("");
		textUserVendedor.setText("");
		chckbxGerente.setSelected(false);
		chckbxVendedor.setSelected(false);
		passwordVendedor.setText("");
	}
	
	public void  refresh() {
		mostrarListaFuncionarios();
	}
	public void ManipulaMaquina() {

		ManipularMaquina maquinaManipulada = new ManipularMaquina();
		maquinaManipulada.setVisible(true);

	}

	public JPanel getPanelVerVendedores() {
		return panelVerVendedores;
	}

	public void setPanelVerVendedores(JPanel panelVerVendedores) {
		this.panelVerVendedores = panelVerVendedores;
	}

	public JLabel getLabelGerente() {
		return labelGerente;
	}

	public void setLabelGerente(JLabel labelGerente) {
		this.labelGerente = labelGerente;
	}

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	
}