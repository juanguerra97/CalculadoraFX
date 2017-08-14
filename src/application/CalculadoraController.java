package application;

import java.math.BigDecimal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

// Clase controladora de eventos
public class CalculadoraController
{
	
	private Calculadora calculadora;
	private boolean reset;
	
	@FXML
	private TextField pantalla;
	
	@FXML// Método llamado cuando se presiona un botón numérico
	private void onNumericButtonPressed(ActionEvent event) {
		if(reset) {
			pantalla.setText("");
			reset = false;
		}
		Button boton = (Button) event.getSource();
		pantalla.setText(pantalla.getText() + boton.getText());
	}
	
	@FXML// método que se llama al presionar el botón de alguno de los operadores matemáticos
	private void onOperatorButtonPressed(ActionEvent event) {
		try {
			Button boton = (Button) event.getSource();
			BigDecimal num = new BigDecimal(pantalla.getText());
			calculadora.addOperando(num);
			calculadora.addOperador(boton.getText());
			pantalla.setText("0");
			reset = true;
		}catch(NumberFormatException e) {}
	}
	
	@FXML// método llamado al presionar el botón del punto
	private void onDotButtonPressed(ActionEvent event) {
		String textoPantalla = pantalla.getText();
		boolean contienePunto = false;
		for(int i = 0; i < textoPantalla.length(); ++i) {
			if(textoPantalla.charAt(i) == '.') {
				contienePunto = true;
				break;
			}
		}
		if(!contienePunto) {
			pantalla.setText(textoPantalla + ".");
			if(reset)
				reset = false;
		}
	}
	
	@FXML// método llamado al presionar el botón de el último resultado
	private void onANSButtonPressed(ActionEvent event) {
		if(reset) {
			pantalla.setText("");
			reset = false;
		}
		String t = pantalla.getText();
		if(t.equals("") || t.equals("0") || t.equals("0.0")) 
			pantalla.setText("" + calculadora.getANS().doubleValue());
		
	}
	
	@FXML// método para el botón de resetear
	private void onACButtonPressed(ActionEvent event) {
		pantalla.setText("0");
		reset = true;
		calculadora.limpiarPila();
	}
	
	@FXML// método para borrar el último dígito ingresado
	private void onDELButtonPressed(ActionEvent e) {
		String texP = pantalla.getText();
		if(!reset) {
			if(texP.length() <= 1) {
				reset = true;
				pantalla.setText("0");
			}else {
				pantalla.setText(texP.substring(0, texP.length() - 1));
			}
		}
	}
	
	@FXML	//Evento del boton igual
	private void onEqualButtonClicked(ActionEvent e) {
		BigDecimal num = new BigDecimal(pantalla.getText());
		calculadora.addOperando(num);
		BigDecimal resultado = calculadora.getResultado();
		pantalla.setText("" + resultado);
	}
	
	@FXML
	public void initialize() {
		reset = true;
		calculadora = new Calculadora();	
		pantalla.setText("0");
	}

}
