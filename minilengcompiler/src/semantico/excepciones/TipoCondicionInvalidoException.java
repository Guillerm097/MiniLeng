package semantico.excepciones;

import semantico.Simbolo.Tipo_variable;

public class TipoCondicionInvalidoException extends SemanticException {

	private static final long serialVersionUID = -294989306362026419L;

	private String _tipoVariable;
	
	public TipoCondicionInvalidoException(int fila, int columna, Tipo_variable tipoVariable) {
			super(fila, columna, null);

			switch(tipoVariable) {
			case DESCONOCIDO:
				_tipoVariable = "desconocido";
				break;
			case ENTERO:
				_tipoVariable = "entero";
				break;
			case BOOLEANO:
				_tipoVariable = "booleano";
				break;
			case CHAR:
				_tipoVariable = "char";
				break;
			case CADENA:
				_tipoVariable = "cadena";
			default:
			}
	}
	
	public String toString() {
		return super.toString() + "La expresión de la condición es de tipo " +
					_tipoVariable  + " cuando debería de ser de tipo booleano";
	}
}
