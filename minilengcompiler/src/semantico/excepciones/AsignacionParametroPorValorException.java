package semantico.excepciones;

public class AsignacionParametroPorValorException extends SemanticException {

	private static final long serialVersionUID = 5138556554658280503L;

	public AsignacionParametroPorValorException(int fila, int columna, String token) {
		super(fila, columna, token);
	}
	
	public String toString() {
		return super.toString() + "No se le puede asignar ningún valor al parámetro " + _token
								+ " pasado por valor";
	}
}