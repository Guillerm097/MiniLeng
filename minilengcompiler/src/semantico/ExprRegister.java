package semantico;

import semantico.Simbolo.Tipo_variable;

//Registro que se utilizá para propagar el valor de una expresión
public class ExprRegister {
	public boolean val_booleano;
	public int val_entero;
	public char val_char;
	public Tipo_variable tipo;
	public boolean constante;
	// Necesario para exhaustividad de las excepciones
	public int fila, columna;
}