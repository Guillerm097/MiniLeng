package semantico.excepciones;

public class SemanticException extends Exception {
	
	private static final long serialVersionUID = -1342112789308266856L;
	
	int fila, columna;

	public SemanticException(int fila, int columna) {
		fila = fila;
		columna = columna;
	}
	
	public String toString() {
		return "Error semántico (" + fila + "," + columna + "): ";
	}
}
