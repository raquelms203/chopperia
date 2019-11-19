package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JComboBox;

public class ManipularMaquina extends JFrame {

	private JPanel contentPane;
	private JTextField textNomeBebida;
	private JTextField textMarcaBebida;

	
	private JPanel panelAdicionarBebida;
	private JPanel panelEstoque;
	
	JComboBox<String> comboBox;
	
	private JTextField textValor;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManipularMaquina frame = new ManipularMaquina();
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
	public ManipularMaquina() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 939, 629);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAdicionarBebida = new JButton("Adicionar Bebida");
		btnAdicionarBebida.setBackground(new Color(255, 255, 255));
		btnAdicionarBebida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAdicionarBebida.setVisible(true);
				panelEstoque.setVisible(false);
				MostrarComboBox();
			}
		});
		btnAdicionarBebida.setBounds(37, 199, 189, 78);
		contentPane.add(btnAdicionarBebida);
		
		JButton btnVerBebidas = new JButton("Visualizar Bebidas");
		btnVerBebidas.setBackground(new Color(255, 255, 255));
		btnVerBebidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelEstoque.setVisible(true);
				panelAdicionarBebida.setVisible(false);
			}
		});
		btnVerBebidas.setBounds(37, 117, 189, 71);
		contentPane.add(btnVerBebidas);
		
		JLabel lblControleDaMquina = new JLabel("Controle da M\u00E1quina");
		lblControleDaMquina.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblControleDaMquina.setBounds(396, 11, 199, 28);
		contentPane.add(lblControleDaMquina);
		
		 panelAdicionarBebida = new JPanel();
		 panelAdicionarBebida.setBackground(new Color(255, 255, 255));
		panelAdicionarBebida.setBounds(260, 61, 358, 334);
		contentPane.add(panelAdicionarBebida);
		panelAdicionarBebida.setLayout(null);
		
		JLabel lblNomeBebida = new JLabel("Nome:");
		lblNomeBebida.setBounds(32, 60, 60, 14);
		panelAdicionarBebida.add(lblNomeBebida);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(32, 137, 60, 14);
		panelAdicionarBebida.add(lblMarca);
		
		textNomeBebida = new JTextField();
		textNomeBebida.setBounds(127, 58, 193, 20);
		panelAdicionarBebida.add(textNomeBebida);
		textNomeBebida.setColumns(10);
		
		textMarcaBebida = new JTextField();
		textMarcaBebida.setBounds(127, 135, 193, 20);
		panelAdicionarBebida.add(textMarcaBebida);
		textMarcaBebida.setColumns(10);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAdicionar.setBackground(new Color(255, 255, 255));
		btnAdicionar.setBounds(231, 244, 89, 23);
		panelAdicionarBebida.add(btnAdicionar);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(32, 98, 68, 14);
		panelAdicionarBebida.add(lblModelo);
		
		JCheckBox chckbxCerveja = new JCheckBox("Cerveja");
		chckbxCerveja.setBackground(new Color(255, 255, 255));
		chckbxCerveja.setBounds(127, 95, 97, 23);
		panelAdicionarBebida.add(chckbxCerveja);
		
		JCheckBox chckbxRefrigerante = new JCheckBox("Refrigerante");
		chckbxRefrigerante.setBackground(new Color(255, 255, 255));
		chckbxRefrigerante.setBounds(223, 95, 97, 23);
		panelAdicionarBebida.add(chckbxRefrigerante);
		
		JLabel lblNovaBebida = new JLabel("Nova - Bebida");
		lblNovaBebida.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNovaBebida.setBounds(134, 22, 97, 14);
		panelAdicionarBebida.add(lblNovaBebida);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(32, 215, 48, 14);
		panelAdicionarBebida.add(lblValor);
		
		textValor = new JTextField();
		textValor.setBounds(127, 213, 193, 20);
		panelAdicionarBebida.add(textValor);
		textValor.setColumns(10);
		
		JLabel lblTamanho = new JLabel("Quantidade:");
		lblTamanho.setBounds(32, 178, 68, 14);
		panelAdicionarBebida.add(lblTamanho);
		
		 comboBox = new JComboBox<String>();
		 comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setBounds(127, 175, 193, 22);
		panelAdicionarBebida.add(comboBox);
		
		 panelEstoque = new JPanel();
		 panelEstoque.setBounds(260, 61, 539, 483);
		 contentPane.add(panelEstoque);
		 panelEstoque.setBackground(new Color(255, 255, 255));
		 panelEstoque.setLayout(null);
		 
		 JScrollPane scrollPane = new JScrollPane();
		 scrollPane.setBounds(44, 60, 317, 373);
		 panelEstoque.add(scrollPane);
		 
		 JList list = new JList();
		 scrollPane.setViewportView(list);
		 
		 JButton btnEditar = new JButton("Editar");
		 btnEditar.setBackground(new Color(255, 255, 255));
		 btnEditar.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 	}
		 });
		 btnEditar.setBounds(371, 58, 132, 23);
		 panelEstoque.add(btnEditar);
		 
		 JButton btnRemover = new JButton("Remover");
		 btnRemover.setBackground(new Color(255, 255, 255));
		 btnRemover.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 	}
		 });
		 btnRemover.setBounds(371, 92, 132, 23);
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
		 btnFinalizar.setBackground(new Color(255, 255, 255));
		 btnFinalizar.setForeground(new Color(255, 0, 0));
		 btnFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 btnFinalizar.setBounds(37, 16, 114, 23);
		 contentPane.add(btnFinalizar);
		 
		 JButton btnHistorico = new JButton("Historico");
		 btnHistorico.setBackground(new Color(255, 255, 255));
		 btnHistorico.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 	}
		 });
		 btnHistorico.setBounds(37, 287, 189, 78);
		 contentPane.add(btnHistorico);
		panelEstoque.setVisible(false);
		
		panelAdicionarBebida.setVisible(false);
	}
	
	public void MostrarComboBox() {
		
		comboBox.removeAllItems();
		comboBox.addItem("30 Litros");
		comboBox.addItem("40 Litros");
		comboBox.addItem("50 Litros");
		comboBox.addItem("100 Litros");		
	}
	
		
	
}
