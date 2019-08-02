package semantico.excepciones;

public class SimboloNoEncontradoException extends SemanticException {

	private static final long serialVersionUID = 8922484408021048158L;
	
	private String simbolo;
	
	public SimboloNoEncontradoException(String simbolo) {
		this.simbolo = simbolo;
	}
	
	public String toString() {
		return super.toString() + "No se ha encontrado el símbolo " + this.simbolo;
	}
}