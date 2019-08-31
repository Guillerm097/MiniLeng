package semantico.excepciones;

public class EntacarOverflowException extends SemanticException {
	
	private static final long serialVersionUID = 1234347817568593351L;
	
	int _argumentoEntacar;
	
	public EntacarOverflowException(int fila, int columna, int argumentoEntacar) {
		super(fila, columna, null);
		_argumentoEntacar = argumentoEntacar;
	}

	public String toString() {
		return super.toString() + "El rango de valores para convertir un número a carácter " +
					" tiene que estar comprendido entre 0 y 255, el valor actual es " + _argumentoEntacar;
	}
}
