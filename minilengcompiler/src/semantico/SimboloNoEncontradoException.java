package semantico;

public class SimboloNoEncontradoException extends Exception {

	private static final long serialVersionUID = 8922484408021048158L;
	
	private String simbolo;
	
	public SimboloNoEncontradoException(String simbolo) {
		this.simbolo = simbolo;
	}
	
	public String toString() {
		return "No se ha encontrado el símbolo " + this.simbolo;
	}
}
