
package calculadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;


public class Calculadora extends JFrame {

    
	private static final long serialVersionUID = 1583724102189855698L;
	JTextField pantalla;
        JLabel conver;
	double resultado;
        double resultado2;
	String operacion;
	JPanel panelNumeros, panelOperaciones;
	boolean nuevaOperacion = true;
        
        
	public Calculadora() {
		super();
		setSize(500, 600);
		setTitle("Convertidor de Pesos a Dolares");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);

		JPanel panel = (JPanel) this.getContentPane();
		panel.setLayout(new BorderLayout());

                conver = new JLabel();
                conver.setBounds(300, 400, 200, 200);
                conver.setBorder(new EmptyBorder(1,1,1,1));
                conver.setBackground(Color.orange);
                conver.setText("Convertidor");
                panel.add("North", conver);
                
		pantalla = new JTextField("0", 40);
		pantalla.setBorder(new EmptyBorder(4, 4, 4, 4));
		pantalla.setFont(new Font("Arial", Font.ITALIC, 25));
		pantalla.setHorizontalAlignment(JTextField.RIGHT);
		pantalla.setEditable(false);
		pantalla.setBackground(Color.RED);
		panel.add("North", pantalla);
                
		panelNumeros = new JPanel();
		panelNumeros.setLayout(new GridLayout(4, 3));
		panelNumeros.setBorder(new EmptyBorder(4, 4, 4, 4));
                
		for (int n = 9; n >= 0; n--) {
			nuevoBotonNumerico("" + n);
		}
		nuevoBotonNumerico(".");
                
		panel.add("Center", panelNumeros);
                
		panelOperaciones = new JPanel();
		panelOperaciones.setLayout(new GridLayout(6, 1));
		panelOperaciones.setBorder(new EmptyBorder(4, 4, 4, 4));
                
		nuevoBotonOperacion("Convertir");
                nuevoBotonOperacion("=");
		nuevoBotonOperacion("Limpiar");

		panel.add("East", panelOperaciones);

		validate();
	}

	private void nuevoBotonNumerico(String digito) {
		JButton btn = new JButton();
		btn.setText(digito);
		btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent evt) {
				JButton btn = (JButton) evt.getSource();
				numeroPulsado(btn.getText());
			}
		});

		panelNumeros.add(btn);
	}
        
	private void nuevoBotonOperacion(String operacion) {
		JButton btn = new JButton(operacion);
		btn.setForeground(Color.BLUE);

		btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent evt) {
				JButton btn = (JButton) evt.getSource();
				operacionPulsado(btn.getText());
			}
		});

		panelOperaciones.add(btn);
	}


	private void numeroPulsado(String digito) {
		if (pantalla.getText().equals("0") || nuevaOperacion) {
			pantalla.setText(digito);
		} else {
			pantalla.setText(pantalla.getText() + digito);
		}
		nuevaOperacion = false;
	}
        
        
	private void operacionPulsado(String tecla) {
		if (tecla.equals("=")) {
			calcularResultado();
		} else if (tecla.equals("Limpiar")) {
			resultado = 0;
			pantalla.setText("");
			nuevaOperacion = true;
		} else {
			operacion = tecla;
			if ((resultado > 0) && !nuevaOperacion) {
				calcularResultado();
			} else {
				resultado = new Double(pantalla.getText());
			}
		}

		nuevaOperacion = true;
	}
        
	private void calcularResultado() {
		if (operacion.equals("Convertir")) {
			resultado = new Double(pantalla.getText());
                        resultado = resultado/17;
		} 
		pantalla.setText("" + resultado);
		operacion = "";
	}
}
