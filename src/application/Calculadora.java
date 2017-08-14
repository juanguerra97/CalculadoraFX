package application;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Calculadora {
	
	private BigDecimal ans;
	private ArrayList<BigDecimal> operandos;
	private ArrayList<String> operadores;
	private BigDecimal resultado;
	
	// constructor
	public Calculadora() {
		ans = new BigDecimal(0);
		resultado = new BigDecimal(0);
		operandos = new ArrayList<>();
		operadores = new ArrayList<>();
	}
	
	public BigDecimal getANS() {return ans;}
	
	// método que agrega un nuevo operando a la pila
	public void addOperando(BigDecimal operando) throws IllegalArgumentException {
		if(operando != null) {
			if(operandos.size() == 0)
				resultado = operando;
			operandos.add(operando);
			if(operandos.size() == 2) {
				resultado = operar();
				if(resultado != null) {
					operandos.clear();
					operadores.clear();
					operandos.add(resultado);
				}
			}
		}else throw new IllegalArgumentException("NO es posible operar con argumentos nulos");
	}
	
	// método para agregar un nuevo operador
	public void addOperador(String operador) throws IllegalArgumentException {
		switch(operador) {
		case "+":
		case "-":
		case "*":
		case "/":
			operadores.add(operador);
			break;
		default:
			throw new IllegalArgumentException("Operador inválido");
		}
	}
	
	// método que devuelve el resultado de las operaciones
	public BigDecimal getResultado() {
		operandos.clear();
		operadores.clear();
		ans = resultado;
		return resultado;
	}
	
	// método que hace la operacion
	private BigDecimal operar() {
		BigDecimal resultado = null;
		switch(operadores.get(0)) {
		case "+":
			resultado = operandos.get(0).add(operandos.get(1));
			break;
		case "-":
			resultado = operandos.get(0).subtract(operandos.get(1));
			break;
		case "*":
			resultado = operandos.get(0).multiply(operandos.get(1));
			break;
		case "/":
			resultado = BigDecimal.valueOf(operandos.get(0).doubleValue() / operandos.get(1).doubleValue());
			break;
		default:
			break;
		}
		return resultado;
	}
	
	// método que limpia la pila
	public void limpiarPila() {
		operandos.clear();
		operadores.clear();
		resultado = BigDecimal.valueOf(0);
	}

}
