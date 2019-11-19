package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

public class Maquina extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JLabel labelSaldoDoCliente;
	private JLabel labelNumeroCliente;

	private JList listCerveja;
	private JList listRefrigerante;
	
	JComboBox<String> comboBoxCerveja;
	JComboBox<String> comboBoxRefrigerante;

	public static void main(String[] args) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Metal".equals(info.getName())) {
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Maquina() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1058, 727);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMaquina = new JLabel("M\u00E1quina");
		lblMaquina.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaquina.setBounds(496, 11, 184, 50);
		contentPane.add(lblMaquina);

		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCliente.setBounds(24, 91, 72, 14);
		contentPane.add(lblCliente);

		JLabel lblSaldo = new JLabel("Saldo:");
		lblSaldo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSaldo.setBounds(24, 116, 72, 14);
		contentPane.add(lblSaldo);

		labelSaldoDoCliente = new JLabel("----");
		labelSaldoDoCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelSaldoDoCliente.setBounds(118, 118, 115, 14);
		contentPane.add(labelSaldoDoCliente);

		labelNumeroCliente = new JLabel("----");
		labelNumeroCliente.setFont(new Font("Tahoma", Font.ITALIC, 14));
		labelNumeroCliente.setBounds(118, 93, 115, 14);
		contentPane.add(labelNumeroCliente);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 262, 223, 357);
		contentPane.add(scrollPane);

		listCerveja = new JList();
		scrollPane.setViewportView(listCerveja);

		JButton btnPersonalizarBebida = new JButton("Personalizar Bebida");
		btnPersonalizarBebida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPersonalizarBebida.setBounds(676, 85, 335, 84);
		contentPane.add(btnPersonalizarBebida);

		JLabel lblCervejas = new JLabel("Cervejas");
		lblCervejas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCervejas.setBounds(105, 226, 115, 25);
		contentPane.add(lblCervejas);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(443, 262, 223, 357);
		contentPane.add(scrollPane_1);

		listRefrigerante = new JList();
		scrollPane_1.setViewportView(listRefrigerante);

		JLabel lblNewLabel_1 = new JLabel("Refrigerante");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(503, 226, 115, 27);
		contentPane.add(lblNewLabel_1);

		JButton btnComprarRefrigerante = new JButton("Comprar");
		btnComprarRefrigerante.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnComprarRefrigerante.setBackground(new Color(255, 255, 255));
		btnComprarRefrigerante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnComprarRefrigerante.setBounds(676, 297, 172, 61);
		contentPane.add(btnComprarRefrigerante);

		JButton btnComprarCerveja = new JButton("Comprar");
		btnComprarCerveja.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnComprarCerveja.setBounds(261, 296, 172, 63);
		contentPane.add(btnComprarCerveja);
		btnComprarCerveja.setBackground(new Color(255, 255, 255));

		JLabel lblBebidasDisponiveis = new JLabel("BEBIDAS DISPON\u00CDVEIS");
		lblBebidasDisponiveis.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblBebidasDisponiveis.setBounds(256, 145, 258, 27);
		contentPane.add(lblBebidasDisponiveis);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setForeground(new Color(255, 0, 0));
		btnFinalizar.setBackground(new Color(255, 255, 255));
		btnFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				//implementar o fechamento ao acesso do  BD
			}
		});
		btnFinalizar.setBounds(24, 11, 129, 33);
		contentPane.add(btnFinalizar);
		
		comboBoxCerveja = new JComboBox<String>();
		comboBoxCerveja.setBackground(new Color(255, 255, 255));
		comboBoxCerveja.setBounds(261, 260, 172, 25);
		contentPane.add(comboBoxCerveja);
		
		comboBoxRefrigerante = new JComboBox<String>();
		comboBoxRefrigerante.setBackground(new Color(255, 255, 255));
		comboBoxRefrigerante.setBounds(676, 260, 172, 25);
		contentPane.add(comboBoxRefrigerante);
		
		JLabel lblNewLabel = new JLabel("Selecione a quantidade");
		lblNewLabel.setBounds(286, 233, 129, 14);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("Selecione a quantidade");
		label.setBounds(697, 233, 129, 14);
		contentPane.add(label);
		btnComprarCerveja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			
			}
		});
		
		MostrarComboBox();
	}
	
	
	public void MostrarComboBox() {
		
		comboBoxRefrigerante.removeAllItems();
		comboBoxRefrigerante.addItem("300 ML");
		comboBoxRefrigerante.addItem("500 ML");
		comboBoxRefrigerante.addItem("700 ML");
		comboBoxRefrigerante.addItem("1 Litro");	
		
		comboBoxCerveja.removeAllItems();
		comboBoxCerveja.addItem("300 ML");
		comboBoxCerveja.addItem("500 ML");
		comboBoxCerveja.addItem("700 ML");
		comboBoxCerveja.addItem("1 Litro");
		
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

	public JList getListCerveja() {
		return listCerveja;
	}

	public void setListCerveja(JList listCerveja) {
		this.listCerveja = listCerveja;
	}

	public JList getListRefrigerante() {
		return listRefrigerante;
	}

	public void setListRefrigerante(JList listRefrigerante) {
		this.listRefrigerante = listRefrigerante;
	}
	
}
