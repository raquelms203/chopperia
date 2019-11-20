package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Conexao;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JTextField;
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

public class GerenteView extends JFrame {


	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JPanel panelVerVendedores;
	private JPanel panelCadastrarVendedor;
	private JTextField textNovoNomeVendedor;
	private JTextField textNovoCPFvendedor;
	private JTextField textCargoVendedor;
	private JTextField textUserVendedor;
	private JPasswordField passwordVendedor;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenteView frame = new GerenteView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public void  insereFuncionario() {
		String query = "INSERT INTO funcionarios ( nome, \"cpfFuncionario\", cargo, \"user\",senha ) VALUES"
				+ " (?, ?, ?, ?, ?)";
		
		
		Conexao con = new Conexao();
		
		Connection conn = con.getConnection();
		
		PreparedStatement prep;
		
		try {
			prep = conn.prepareStatement(query);
			prep.setString(1,  textNovoNomeVendedor.getText());
			prep.setInt(2, Integer.parseInt(textNovoCPFvendedor.getText()));
			prep.setString(3, textCargoVendedor.getText());
			prep.setString(4, textUserVendedor.getText());
			prep.setString(5, passwordVendedor.getPassword().toString());
			JOptionPane.showMessageDialog(null, query);

			prep.execute();
			prep.close();
			
			
			
					
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
			return;
		}
		
		JOptionPane.showMessageDialog(null, "OK");
		
	}
	public GerenteView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1003, 660);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPainelDoGerente = new JLabel("Painel do Gerente");
		lblPainelDoGerente.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPainelDoGerente.setBounds(54, 38, 190, 14);
		contentPane.add(lblPainelDoGerente);

		JButton btnManipularMaquina = new JButton("Manipular Maquina");
		btnManipularMaquina.setBackground(new Color(255, 255, 255));
		btnManipularMaquina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ManipulaMaquina();
				
			}
		});
		btnManipularMaquina.setBounds(31, 328, 213, 86);
		contentPane.add(btnManipularMaquina);

		panelVerVendedores = new JPanel();
		panelVerVendedores.setBackground(new Color(255, 255, 255));
		panelVerVendedores.setBounds(349, 30, 311, 568);
		contentPane.add(panelVerVendedores);
		panelVerVendedores.setLayout(null);

		JLabel lblListaDeVendedores = new JLabel("Lista de Vendedores");
		lblListaDeVendedores.setBounds(97, 117, 147, 27);
		panelVerVendedores.add(lblListaDeVendedores);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 143, 253, 380);
		panelVerVendedores.add(scrollPane);

		JList listVendedores = new JList();
		scrollPane.setViewportView(listVendedores);
		panelVerVendedores.setVisible(false);

		JButton btnCadastrarVendedor = new JButton("Cadastrar Vendedor");
		btnCadastrarVendedor.setBackground(new Color(255, 255, 255));
		btnCadastrarVendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCadastrarVendedor.setVisible(true);
				panelVerVendedores.setVisible(false);
			}
		});
		btnCadastrarVendedor.setBounds(31, 231, 213, 86);
		contentPane.add(btnCadastrarVendedor);

		JButton btnVerVendedores = new JButton("Ver Vendedores");
		btnVerVendedores.setBackground(new Color(255, 255, 255));
		btnVerVendedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panelCadastrarVendedor.setVisible(false);
				panelVerVendedores.setVisible(true);
			}
		});
		btnVerVendedores.setBounds(31, 141, 213, 79);
		contentPane.add(btnVerVendedores);
		
		panelCadastrarVendedor = new JPanel();
		panelCadastrarVendedor.setBounds(394, 123, 490, 253);
		contentPane.add(panelCadastrarVendedor);
		panelCadastrarVendedor.setBackground(new Color(255, 255, 255));
		panelCadastrarVendedor.setLayout(null);
		
		JButton btnCadastrarNovoVendedor = new JButton("Cadastrar");
		btnCadastrarNovoVendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insereFuncionario();

			}
		});
		btnCadastrarNovoVendedor.setBounds(243, 219, 109, 23);
		panelCadastrarVendedor.add(btnCadastrarNovoVendedor);
		
		textNovoNomeVendedor = new JTextField();
		textNovoNomeVendedor.setBounds(110, 62, 242, 20);
		panelCadastrarVendedor.add(textNovoNomeVendedor);
		textNovoNomeVendedor.setColumns(10);
		
		textNovoCPFvendedor = new JTextField();
		textNovoCPFvendedor.setBounds(110, 93, 242, 20);
		panelCadastrarVendedor.add(textNovoCPFvendedor);
		textNovoCPFvendedor.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(39, 65, 62, 14);
		panelCadastrarVendedor.add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(39, 93, 77, 14);
		panelCadastrarVendedor.add(lblCpf);
		
		JLabel lblCadastrarVendedor = new JLabel("Cadastrar  -  Vendedor");
		lblCadastrarVendedor.setBounds(154, 37, 150, 14);
		panelCadastrarVendedor.add(lblCadastrarVendedor);
		
		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setBounds(39, 126, 46, 14);
		panelCadastrarVendedor.add(lblCargo);
		
		textCargoVendedor = new JTextField();
		textCargoVendedor.setBounds(109, 123, 243, 20);
		panelCadastrarVendedor.add(textCargoVendedor);
		textCargoVendedor.setColumns(10);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setBounds(39, 157, 46, 14);
		panelCadastrarVendedor.add(lblUser);
		
		textUserVendedor = new JTextField();
		textUserVendedor.setBounds(110, 154, 242, 20);
		panelCadastrarVendedor.add(textUserVendedor);
		textUserVendedor.setColumns(10);
		
		JLabel lblPassaword = new JLabel("Passaword:");
		lblPassaword.setBounds(39, 188, 62, 14);
		panelCadastrarVendedor.add(lblPassaword);
		
		passwordVendedor = new JPasswordField();
		passwordVendedor.setBounds(110, 185, 242, 20);
		panelCadastrarVendedor.add(passwordVendedor);
		panelCadastrarVendedor.setVisible(false);

		panelVerVendedores.setVisible(false);
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
}
