package semantico.excepciones;

public class ArgumentoParametroPorValorException extends SemanticException {

	private static final long serialVersionUID = 6202447076955437823L;
	
	private int _numParametro;
	
	public ArgumentoParametroPorValorException(int fila, int columna, int numParametro) {
		super(fila, columna, null);
		_numParametro = numParametro;
	}
	
	public String toString() {
		return super.toString() + "No se puede pasar por valor a una nueva función, el " +
					 _numParametro + " parámetro pasado previamente por referencia";
	}
}