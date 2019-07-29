package semantico;

import semantico.Simbolo.Tipo_variable;

public class SemanticoUtils {
	
	// ENUMS
	public enum Tipo_operador {
		SUMA,
		RESTA,
		OR
	}
	
	// Mostrar error semántico sálida estándar y error
	public static void error_semantico(String error) {
		System.out.println("Error semantico: " + error);
		System.err.println("Error semantico: " + error);
	}
	
	// Registro que se utilizá para propagar el valor de una expresión
	public class ExprRegister {
		public boolean val_booleano;
		public int val_entero;
		public char val_char;
		public Tipo_variable tipo;
	}
}