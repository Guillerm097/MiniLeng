package semantico.excepciones;

public class SimboloNoEncontradoException extends SemanticException {

	private static final long serialVersionUID = 8922484408021048158L;

	public SimboloNoEncontradoException(String nombre) {
		 _token = nombre;
	}
	
	public void restoSimboloNoEncontrado(int fila, int columna) {
		_fila = fila;
		_columna = columna;
	}
	
	public String toString() {
		return super.toString() + "No se ha encontrado el símbolo " + _token;
	}
}