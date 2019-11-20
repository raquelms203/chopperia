package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;

public class EditarBebida extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

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
					EditarBebida frame = new EditarBebida();
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
	public EditarBebida() {
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
		
		JCheckBox chckbxCerveja = new JCheckBox("Cerveja");
		chckbxCerveja.setBounds(167, 72, 86, 23);
		contentPane.add(chckbxCerveja);
		
		JCheckBox chckbxRefrigerante = new JCheckBox("Refrigerante");
		chckbxRefrigerante.setBounds(256, 72, 97, 23);
		contentPane.add(chckbxRefrigerante);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(76, 107, 46, 14);
		contentPane.add(lblMarca);
		
		textField = new JTextField();
		textField.setBounds(171, 108, 174, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(76, 157, 86, 14);
		contentPane.add(lblQuantidade);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(170, 154, 174, 30);
		contentPane.add(comboBox);
		
		JLabel lblValorPorLitro = new JLabel("Valor por litro:");
		lblValorPorLitro.setBounds(76, 201, 86, 14);
		contentPane.add(lblValorPorLitro);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(171, 195, 174, 30);
		contentPane.add(textField_1);
		
		JButton btnConcludo = new JButton("CONFIRMAR");
		btnConcludo.setForeground(new Color(0, 0, 0));
		btnConcludo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConcludo.setBackground(new Color(0, 255, 153));
		btnConcludo.setBounds(127, 280, 174, 68);
		contentPane.add(btnConcludo);
	}
}
