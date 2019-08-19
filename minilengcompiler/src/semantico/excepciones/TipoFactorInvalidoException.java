package semantico.excepciones;

import semantico.Simbolo.Tipo_variable;

public class TipoFactorInvalidoException extends SemanticException {

	private static final long serialVersionUID = -8465344218968754677L;
	
	private String _tipoFactInvalida;
	private String _tipoFactValidos;
	
	public TipoFactorInvalidoException(int fila, int columna,
			Tipo_variable tipoExprInvalida, String tipoExprValidos) {
		
		super(fila, columna, null);

		_tipoFactValidos = tipoExprValidos;
		
		switch(tipoExprInvalida) {
		case DESCONOCIDO:
			_tipoFactInvalida = "desconocido";
			break;
		case ENTERO:
			_tipoFactInvalida = "entero";
			break;
		case BOOLEANO:
			_tipoFactInvalida = "booleano";
			break;
		case CHAR:
			_tipoFactInvalida = "char";
			break;
		case CADENA:
			_tipoFactInvalida = "cadena";
		}
	}
	
	public String toString() {
		return super.toString() + "El factor " + " es de tipo "
				+ _tipoFactInvalida  + " cuando debería de ser de uno de los siguientes tipos : "
				+ _tipoFactValidos;
	}
}