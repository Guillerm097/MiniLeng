package semantico.excepciones;

public class IdentificadorDuplicadoException extends SemanticException {

	private static final long serialVersionUID = -6590078836085412098L;

	public IdentificadorDuplicadoException(int fila, int columna, String token) {
		super(fila, columna, token);
	}
	
	public String toString() {
		return super.toString() + "Ya existe un identificador idéntico a " + _token +
				" en el mismo nivel del programa.";
	}
}