package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class GerenteView extends JFrame {


	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JPanel panelVerVendedores;
	private JPanel panelCadastrarVendedor;
	private JTextField textNovoNomeVendedor;
	private JTextField textNovoCPFvendedor;

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
		listVendedores.setSelectedIndex(0);
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
		panelCadastrarVendedor.setBounds(349, 162, 490, 188);
		contentPane.add(panelCadastrarVendedor);
		panelCadastrarVendedor.setBackground(new Color(255, 255, 255));
		panelCadastrarVendedor.setLayout(null);
		
		JButton btnCadastrarNovoVendedor = new JButton("Cadastrar");
		btnCadastrarNovoVendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCadastrarNovoVendedor.setBounds(243, 124, 109, 23);
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
		lblNome.setBounds(52, 65, 62, 14);
		panelCadastrarVendedor.add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(52, 96, 77, 14);
		panelCadastrarVendedor.add(lblCpf);
		
		JLabel lblCadastrarVendedor = new JLabel("Cadastrar  -  Vendedor");
		lblCadastrarVendedor.setBounds(154, 37, 150, 14);
		panelCadastrarVendedor.add(lblCadastrarVendedor);
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
