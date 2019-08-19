package semantico.excepciones;

import semantico.Simbolo.Tipo_variable;

public class TipoMiembroExpresionInvalidoException extends SemanticException {

	private static final long serialVersionUID = -771632238802709928L;

	private String _tipoExprInvalida;
	private String _tipoExprValidos;
	private String _miembro;
	
	public TipoMiembroExpresionInvalidoException(int fila, int columna,
			Tipo_variable tipoExprInvalida, String tipoExprValidos,
			String miembro) {
		
		super(fila, columna, null);

		_tipoExprValidos = tipoExprValidos;
		_miembro = miembro;
		
		switch(tipoExprInvalida) {
		case DESCONOCIDO:
			_tipoExprInvalida = "desconocido";
			break;
		case ENTERO:
			_tipoExprInvalida = "entero";
			break;
		case BOOLEANO:
			_tipoExprInvalida = "booleano";
			break;
		case CHAR:
			_tipoExprInvalida = "char";
			break;
		case CADENA:
			_tipoExprInvalida = "cadena";
		}
	}
	
	public String toString() {
		return super.toString() + "El miembro " + _miembro + " de la expresión es de tipo "
				+ _tipoExprInvalida  + " cuando debería de ser de uno de los siguientes tipos : "
				+ _tipoExprValidos;
	}
}