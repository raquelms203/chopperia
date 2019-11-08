package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GerenteView extends JFrame {

	private JPanel contentPane;
	
	private JPanel panelVerVendedores;
	private JPanel panelCadastrarVendedor;
	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public GerenteView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 985, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JLabel lblPainelDoGerente = new JLabel("Painel do Gerente");
		lblPainelDoGerente.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPainelDoGerente.setBounds(54, 38, 190, 14);
		contentPane.add(lblPainelDoGerente);
		
		JButton btnManipularMaquina = new JButton("Manipular Maquina");
		btnManipularMaquina.setBounds(10, 336, 213, 86);
		contentPane.add(btnManipularMaquina);
		
		JPanel panelVerVendedores = new JPanel();
		panelVerVendedores.setBounds(278, 22, 410, 568);
		contentPane.add(panelVerVendedores);
		panelVerVendedores.setLayout(null);
		
		JLabel lblListaDeVendedores = new JLabel("Lista de Vendedores");
		lblListaDeVendedores.setBounds(67, 93, 180, 14);
		panelVerVendedores.add(lblListaDeVendedores);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(67, 141, 253, 380);
		panelVerVendedores.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		panelVerVendedores.setVisible(false);
		
		JButton btnCadastrarVendedor = new JButton("Cadastrar Vendedor");
		btnCadastrarVendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				panelVerVendedores.setVisible(false);
				panelCadastrarVendedor_1.setVisible(true);
				panelCadastrarVendedor.setVisible(true);
			}
		});
		btnCadastrarVendedor.setBounds(10, 239, 213, 86);
		contentPane.add(btnCadastrarVendedor);
		
		JButton btnVerVendedores = new JButton("Ver Vendedores");
		btnVerVendedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panelCadastrarVendedor_1.setVisible(false);
				panelVerVendedores.setVisible(true);
			}
		});
		btnVerVendedores.setBounds(10, 149, 213, 79);
		contentPane.add(btnVerVendedores);
		
		panelVerVendedores.setVisible(false);
	}

	public JPanel getPanelVerVendedores() {
		return panelVerVendedores;
	}

	public void setPanelVerVendedores(JPanel panelVerVendedores) {
		this.panelVerVendedores = panelVerVendedores;
	}

	public JPanel getPanelCadastrarVendedor() {
		return panelCadastrarVendedor;
	}

	public void setPanelCadastrarVendedor(JPanel panelCadastrarVendedor) {
		this.panelCadastrarVendedor = panelCadastrarVendedor;
	}
	public JPanel getPanelCadastrarVendedor_1() {
		return panelCadastrarVendedor_1;
	}
}
