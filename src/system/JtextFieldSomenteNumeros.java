package system;

import java.awt.event.InputEvent;
import javax.swing.JTextField;

public final class JtextFieldSomenteNumeros extends JTextField {

	private static final long serialVersionUID = 1L;
	private int maximoCaracteres = -1;

	public JtextFieldSomenteNumeros() {
		super();
		addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent evt) {
				jTextFieldKeyTyped(evt);
			}
		});
	}

	public JtextFieldSomenteNumeros(int maximo) {
		super();
		setMaximoCaracteres(maximo);

		addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent evt) {
				jTextFieldKeyTyped(evt);
			}
		});
	}

	private void jTextFieldKeyTyped(java.awt.event.KeyEvent evt) {

		String caracteres = "0987654321.";
		if (!caracteres.contains(((java.awt.event.KeyEvent) evt).getKeyChar() + "")) {
			((InputEvent) evt).consume();
		}
		if ((getText().length() >= getMaximoCaracteres()) && (getMaximoCaracteres() != -1)) {

			((InputEvent) evt).consume();
			setText(getText().substring(0, getMaximoCaracteres()));
		}

	}

	public int getMaximoCaracteres() {
		return maximoCaracteres;
	}

	public void setMaximoCaracteres(int maximoCaracteres) {
		this.maximoCaracteres = maximoCaracteres;
	}
}