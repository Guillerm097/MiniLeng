package semantico.excepciones;

public class AsignacionParametroPorValorException extends SemanticException {

	private static final long serialVersionUID = -6590078836085412098L;

	public AsignacionParametroPorValorException(int fila, int columna, String token) {
		super(fila, columna, token);
	}
	
	public String toString() {
		return super.toString() + "No está permitido pasar por referencia " +
					" a una nueva función ni asignarle un valor a el parámetro " + _token +
					" pasado previamente por valor";
	}
}