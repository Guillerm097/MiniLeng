package semantico.excepciones;

public class LeerParametroPorValorException extends SemanticException {

	private static final long serialVersionUID = -636051507696853441L;

	public LeerParametroPorValorException(int fila, int columna, String token) {
		super(fila, columna, token);
	}
	
	public String toString() {
		return super.toString() + "No se puede leer el parámetro " + _token 
							+ " pasado por valor";
	}
}