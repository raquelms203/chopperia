
package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.security.auth.callback.TextOutputCallback;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.Document;
import javax.swing.text.MaskFormatter;

import model.Conexao;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFormattedTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VendedorView extends JFrame {
	private JTextField textNome;
	private JTextField textDataNascimento;
	private JTextField textCPF;
	private JTextField textCartao;
	private JTextField textSaldo;
	private JLabel lupa1;
	private JLabel lupa2;
	private int txtSize = 0;
	private JLabel lblCpf;
	private JLabel lblDataDeNascimento;
	private JLabel lblNome;
	private JLabel lblSaldo;
	private JLabel lblCarregando1;
	private JPanel panelCliente;
	private JLabel lblNewLabel;
	private JButton btnBuscar;
	private JButton btnCadastrar;
	private JButton btnConcluido;
	private JButton btnExcluir;
	private boolean cadastro;

	public static void main(String[] args) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
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
					VendedorView frame = new VendedorView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void  insereCartao() {
		String query = "INSERT INTO cartoes ( nome, \"cpfCartao\", saldo, status, \"dataNasc\") VALUES"
				+ "(?, ?, ?, ?, ?)";
		
		String query2 = "SELECT \"nrCartao\" FROM cartoes WHERE \"cpfCartao\" = ?";
		
		Conexao con = new Conexao();
		
		Connection conn = con.getConnection();
		
		PreparedStatement prep, prep2;
		ResultSet rs;
		
		try {
			prep = conn.prepareStatement(query);
			prep.setString(1,  textNome.getText());
			prep.setInt(2, Integer.parseInt(textCPF.getText()));
			prep.setDouble(3, Double.parseDouble(textSaldo.getText()));
			prep.setBoolean(4, true);
			prep.setDate(5, stringParaDate(textDataNascimento.getText()));

			prep.executeUpdate();
			prep.close();
			
			prep2 = conn.prepareStatement(query2);
			prep2.setInt(1, Integer.parseInt(textCPF.getText()));
			rs = prep2.executeQuery();
			
			System.out.println(query2);
			
			
			while(rs.next()) {
				System.out.println(rs.toString());
				JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!\nCartao: " +
				rs.getInt("nrCartao"));
			}
			
			prep2.close();
			rs.close();
			conn.close();
					
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
	}
	
	public void buscaCartaoCpf () {
		
		String query = "SELECT * FROM cartoes WHERE \"cpfCartao\" = ?";
		
		Conexao con = new Conexao();
		
		Connection conn = con.getConnection();
		
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(query);
			prep.setInt(1,  Integer.parseInt(textCPF.getText()));
			
			ResultSet res = prep.executeQuery();
			
			while(res.next()) {
				textNome.setText(res.getString("nome"));
				textSaldo.setText(""+ res.getDouble("saldo"));
				textCartao.setText(""+ res.getInt("nrCartao"));
				textDataNascimento.setText(res.getString("dataNasc"));				
				
			}
			
			res.close();
			prep.close();
			conn.close();
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
	}
	
	public void atualizaCartao() {
		
	}
	
	
	public Date stringParaDate(String txtdata) {
		
		String format = "" + txtdata.charAt(6) + txtdata.charAt(7) +txtdata.charAt(8) +txtdata.charAt(9) 
		+ '-' + txtdata.charAt(3) +txtdata.charAt(4) + '-' + txtdata.charAt(0) +txtdata.charAt(1) ;
		
		Date date = Date.valueOf(format);
		
		return date;
	}
	
	public String dateParaString (Date date) {
		String string = date.toString();
		
		String format = "" + string.charAt(8) + string.charAt(9) + '/' + string.charAt(5) + string.charAt(6) +
				'/' + string.charAt(0) + string.charAt(1) +string.charAt(2) +string.charAt(3);
		
		return format;
	}
	
	public void limparCampos() {
		textCartao.setText("");
		textCPF.setText("");
		textDataNascimento.setText("");
		textNome.setText("");
		textSaldo.setText("");
		
	}
	

	public VendedorView() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 849, 483);

		panelCliente = new JPanel();
		panelCliente.setBackground(new Color(255, 255, 255));
		panelCliente.setBounds(311, 41, 492, 375);
		getContentPane().add(panelCliente);
		panelCliente.setLayout(null);

		lblNome = new JLabel("Nome");
		lblNome.setBounds(67, 63, 46, 14);
		panelCliente.add(lblNome);

		textNome = new JTextField();
		textNome.setBounds(67, 78, 402, 40);
		panelCliente.add(textNome);
		textNome.setColumns(10);

		lblCpf = new JLabel("CPF");
		lblCpf.setBounds(67, 129, 164, 14);
		panelCliente.add(lblCpf);
	
		textDataNascimento = new JTextField();
		textDataNascimento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				if(textDataNascimento.getText().length() == 2 ||
						textDataNascimento.getText().length() == 5) {
					textDataNascimento.setText(textDataNascimento.getText() + '/');
					
				}
				
				if(textDataNascimento.getText().length() == 10) {
					e.consume();
					return;
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
					
					if(textDataNascimento.getText().charAt(textDataNascimento.getText().length() - 1) == '/') {
						textDataNascimento.setText(textDataNascimento.getText().
								substring(0, textDataNascimento.getText().length() - 1));
						return;
					}
					
					if(textDataNascimento.getText().length() == 0) {
						e.consume();
						return;
					}
				}
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println(dateParaString(stringParaDate(textDataNascimento.getText())));
				}
			}
		});
		textDataNascimento.setBounds(285, 142, 181, 40);
		panelCliente.add(textDataNascimento);
		textDataNascimento.setColumns(10);

		lblDataDeNascimento = new JLabel("Data de Nascimento");
		lblDataDeNascimento.setBounds(287, 129, 110, 14);
		panelCliente.add(lblDataDeNascimento);

		textCPF = new JTextField();
		textCPF.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					
					btnExcluir.setVisible(true);
					textDataNascimento.setVisible(true);
					textNome.setVisible(true);
					textSaldo.setVisible(true);
					lblDataDeNascimento.setVisible(true);
					lblNome.setVisible(true);
					lblSaldo.setVisible(true);
					btnConcluido.setVisible(true);
					btnBuscar.setVisible(false);
					
		            buscaCartaoCpf();
		        }
			}
		});
		textCPF.setBounds(67, 142, 181, 40);
		panelCliente.add(textCPF);
		textCPF.setColumns(10);

		JLabel lblCarto = new JLabel("Cart\u00E3o");
		lblCarto.setBounds(67, 209, 46, 14);
		panelCliente.add(lblCarto);

		lblSaldo = new JLabel("Saldo");
		lblSaldo.setBounds(285, 209, 46, 14);
		panelCliente.add(lblSaldo);

		textCartao = new JTextField();
		textCartao.setBounds(67, 222, 181, 40);
		panelCliente.add(textCartao);
		textCartao.setColumns(10);

		textSaldo = new JTextField();
		textSaldo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(cadastro) {
						lblCarregando1.setVisible(true);
						insereCartao();
					}
					
					else {
						
					}
				}
			}
		});
		textSaldo.setBounds(285, 222, 181, 40);
		panelCliente.add(textSaldo);
		textSaldo.setColumns(10);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBackground(new Color(255, 255, 255));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExcluir.setBounds(67, 300, 181, 40);
		panelCliente.add(btnExcluir);

		btnConcluido = new JButton("Concluido");
		btnConcluido.setBackground(new Color(255, 255, 255));
		btnConcluido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				panelCliente.setVisible(false);
			}
		});
		btnConcluido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnConcluido.setBounds(287, 300, 179, 40);
		panelCliente.add(btnConcluido);
		Image imgSearch = new ImageIcon(this.getClass().getResource("/search.png")).getImage();

		JPanel panelAlterar = new JPanel();
		panelAlterar.setBackground(new Color(255, 255, 255));
		panelAlterar.setBounds(0, 0, 10, 10);
		getContentPane().add(panelAlterar);
		panelAlterar.setLayout(null);

		lblNewLabel = new JLabel("Painel do Vendedor");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(38, 76, 203, 14);
		panelAlterar.add(lblNewLabel);

		JButton btnCadastrarCliente = new JButton("Cadastrar Cliente");
		btnCadastrarCliente.setBackground(new Color(255, 255, 255));
		panelAlterar.add(btnCadastrarCliente);
		btnCadastrarCliente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastro = true;
				limparCampos();
				lblCarto.setVisible(false);
				textCartao.setVisible(false);
				panelCliente.setVisible(true);
				textDataNascimento.setVisible(true);
				textNome.setVisible(true);
				textSaldo.setVisible(true);
				lupa1.setVisible(false);
				lupa2.setVisible(false);
				lblDataDeNascimento.setVisible(true);
				lblNome.setVisible(true);
				lblSaldo.setVisible(true);
				btnBuscar.setVisible(false);
				btnExcluir.setVisible(false);
				btnCadastrar.setVisible(true);
				btnConcluido.setVisible(false);
			}
		});
		btnCadastrarCliente.setBounds(38, 120, 180, 114);

		JButton btnAlterarCadastro = new JButton("Manipular Cliente");
		btnAlterarCadastro.setBackground(new Color(255, 255, 255));
		panelAlterar.add(btnAlterarCadastro);
		btnAlterarCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cadastro = false;
				limparCampos();

				if (panelCliente.isVisible() == true) {

					textDataNascimento.setVisible(false);
					textNome.setVisible(false);
					textSaldo.setVisible(false);
					lupa1.setVisible(true);
					lupa2.setVisible(true);
					lblCarto.setVisible(true);
					textCartao.setVisible(true);

					if (lblSaldo.isVisible() == true) {
						lblSaldo.setVisible(false);
					}
					if (lblDataDeNascimento.isVisible() == true) {
						lblDataDeNascimento.setVisible(false);

					}
					if (lblNome.isVisible() == true) {
						lblNome.setVisible(false);
					}
					btnCadastrar.setVisible(false);
					btnBuscar.setVisible(true);
					btnExcluir.setVisible(false);
					btnConcluido.setVisible(false);
				} else {

					panelCliente.setVisible(true);
					textDataNascimento.setVisible(false);
					textNome.setVisible(false);
					textSaldo.setVisible(false);

					if (lblSaldo.isVisible() == true) {
						lblSaldo.setVisible(false);
					}
					if (lblDataDeNascimento.isVisible() == true) {
						lblDataDeNascimento.setVisible(false);

					}
					if (lblNome.isVisible() == true) {
						lblNome.setVisible(false);
					}

					lupa1.setVisible(true);
					lupa2.setVisible(true);
					btnCadastrar.setVisible(false);
					btnBuscar.setVisible(true);
					btnExcluir.setVisible(false);
					btnConcluido.setVisible(false);
				}
				
			}
		});
		btnAlterarCadastro.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAlterarCadastro.setBounds(38, 245, 180, 114);

		panelCliente.setVisible(false);

		lupa1 = new JLabel("");
		lupa1.setBounds(41, 142, 19, 19);
		lupa1.setIcon(new ImageIcon(imgSearch));

		panelCliente.add(lupa1);

		lupa2 = new JLabel("");
		lupa2.setBounds(41, 222, 19, 19);
		lupa2.setIcon(new ImageIcon(imgSearch));

		panelCliente.add(lupa2);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(new Color(255, 255, 255));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnExcluir.setVisible(true);
				textDataNascimento.setVisible(true);
				textNome.setVisible(true);
				textSaldo.setVisible(true);
				lblDataDeNascimento.setVisible(true);
				lblNome.setVisible(true);
				lblSaldo.setVisible(true);
				btnConcluido.setVisible(true);
				btnBuscar.setVisible(false);
				
				buscaCartaoCpf();
			}
		});
		btnBuscar.setBounds(285, 154, 181, 82);
		panelCliente.add(btnBuscar);

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblCarregando1.setVisible(true);
				insereCartao();
				
			}
		});
		btnCadastrar.setBackground(new Color(255, 255, 255));
		btnCadastrar.setBounds(183, 273, 148, 67);
		panelCliente.add(btnCadastrar);
		
		lblCarregando1 = new JLabel("Carregando...");
		lblCarregando1.setBounds(229, 351, 81, 14);
		panelCliente.add(lblCarregando1);
		
		lblCarregando1.setVisible(false);
		btnBuscar.setVisible(false);
		lupa1.setVisible(false);
		lupa2.setVisible(false);
		lblDataDeNascimento.setVisible(false);
		lblNome.setVisible(false);
		lblSaldo.setVisible(false);
		btnConcluido.setVisible(false);
		

	}
	
	

	public JPanel getPanelCliente() {
		return panelCliente;
	}

	public void setPanelCliente(JPanel panelCliente) {
		this.panelCliente = panelCliente;
	}

	public JTextField getTextNome() {
		return textNome;
	}

	public void setTextNome(JTextField textNome) {
		this.textNome = textNome;
	}

	public JTextField getTextDataNascimento() {
		return textDataNascimento;
	}

	public void setTextDataNascimento(JFormattedTextField textDataNascimento) {
		this.textDataNascimento = textDataNascimento;
	}

	public JTextField getTextCPF() {
		return textCPF;
	}

	public void setTextCPF(JTextField textCPF) {
		this.textCPF = textCPF;
	}

	public JTextField getTextCartao() {
		return textCartao;
	}

	public void setTextCartao(JTextField textCartao) {
		this.textCartao = textCartao;
	}

	public JTextField getTextSaldo() {
		return textSaldo;
	}

	public void setTextSaldo(JTextField textSaldo) {
		this.textSaldo = textSaldo;
	}

	public JLabel getLblCpf() {
		return lblCpf;
	}

	public void setLblCpf(JLabel lblCpf) {
		this.lblCpf = lblCpf;
	}

	public JLabel getLblDataDeNascimento() {
		return lblDataDeNascimento;
	}

	public void setLblDataDeNascimento(JLabel lblDataDeNascimento) {
		this.lblDataDeNascimento = lblDataDeNascimento;
	}

	public JLabel getLblNome() {
		return lblNome;
	}

	public void setLblNome(JLabel lblNome) {
		this.lblNome = lblNome;
	}

	public JLabel getLupa1() {
		return lupa1;
	}

	public void setLupa1(JLabel lupa1) {
		this.lupa1 = lupa1;
	}

	public JLabel getLupa2() {
		return lupa2;
	}

	public void setLupa2(JLabel lupa2) {
		this.lupa2 = lupa2;
	}

	public JLabel getLblSaldo() {
		return lblSaldo;
	}

	public void setLblSaldo(JLabel lblSaldo) {
		this.lblSaldo = lblSaldo;
	}

	public boolean isCadastro() {
		return cadastro;
	}

	public void setCadastro(boolean cadastro) {
		this.cadastro = cadastro;
	}

	
}
