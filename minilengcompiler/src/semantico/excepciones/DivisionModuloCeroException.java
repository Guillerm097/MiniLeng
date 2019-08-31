package semantico.excepciones;

public class DivisionModuloCeroException extends SemanticException {

	private static final long serialVersionUID = -5934860070731271910L;

	private String _operacion;
	
	public DivisionModuloCeroException(int fila, int columna, String token, String operacion) {
		super(fila, columna, token);
		_operacion = operacion;
	}

	
	public String toString() {
		return super.toString() + "\"" + _token + "\" No se permite la operación " + _operacion +
							" siendo el miembro derecho de la operación igual a cero";
	}
}