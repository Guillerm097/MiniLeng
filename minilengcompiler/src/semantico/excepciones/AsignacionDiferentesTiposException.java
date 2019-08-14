package semantico.excepciones;

import semantico.Simbolo.Tipo_variable;

public class AsignacionDiferentesTiposException extends SemanticException {
	
	private static final long serialVersionUID = -4933718926605108589L;

	private String _tipoVariableIzq, _tipoVariableDer;
	
	public AsignacionDiferentesTiposException(int fila, int columna, String token,
			Tipo_variable tipoVariableIzq, Tipo_variable tipoVariableDer) {
		super(fila, columna, token);

		switch(tipoVariableIzq) {
		case DESCONOCIDO:
			_tipoVariableIzq = "desconocido";
			break;
		case ENTERO:
			_tipoVariableIzq = "entero";
			break;
		case BOOLEANO:
			_tipoVariableIzq = "booleano";
			break;
		case CHAR:
			_tipoVariableIzq = "char";
			break;
		case CADENA:
			_tipoVariableIzq = "cadena";
		}
		
		switch(tipoVariableDer) {
		case DESCONOCIDO:
			_tipoVariableDer = "desconocido";
			break;
		case ENTERO:
			_tipoVariableDer = "entero";
			break;
		case BOOLEANO:
			_tipoVariableDer = "booleano";
			break;
		case CHAR:
			_tipoVariableDer = "char";
			break;
		case CADENA:
			_tipoVariableDer = "cadena";
		}
	}
	
	public String toString() {
		return super.toString() + "El valor asignado a la variable " + _token +
				" debe de ser de tipo " + _tipoVariableIzq + " en vez de " + _tipoVariableDer;
	}
}