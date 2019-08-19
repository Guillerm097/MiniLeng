package semantico.excepciones;

public class SemanticException extends Exception {

	private static final long serialVersionUID = -1342112789308266856L;

	protected int _fila, _columna;
	protected String _token;
	
	public SemanticException() {}

	public SemanticException(int fila, int columna, String token) {
		_fila = fila;
		_columna = columna;
		_token = token;
	}

	public String toString() {
		return "ERROR SEMÁNTICO (" + _fila + "," + _columna + "): ";
	}
}
