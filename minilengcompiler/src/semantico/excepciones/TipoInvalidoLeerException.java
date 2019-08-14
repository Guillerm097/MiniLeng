package semantico.excepciones;

import semantico.Simbolo.Tipo_variable;

public class TipoInvalidoLeerException extends SemanticException {

	private static final long serialVersionUID = -115260214497829205L;
	private String _tipoVariable;
	
	public TipoInvalidoLeerException(int fila, int columna, String token,
			Tipo_variable tipoVariable) {
			super(fila, columna, token);

			switch(tipoVariable) {
			case CHAR:
				_tipoVariable = "char";
				break;
			case CADENA:
				_tipoVariable = "cadena";
				break;
			default:
			}
	}
	
	public String toString() {
		return super.toString() + "El identificador " + _token + " de tipo " +
					_tipoVariable  + " no se puede leer";
	}
}
