package generacioncodigo;

import java.io.FileWriter;
import java.io.IOException;

public class Salida {

	private static String fichero_destino, codigo;
	private static int etiqueta;
	private static boolean volcar_salida;

	public Salida(String _fichero_destino) {
		etiqueta = 0;
		fichero_destino = _fichero_destino;
		volcar_salida = true;
	}

	public static void noCodificarASM() {
		volcar_salida = false;
	}

	public static int nuevaEtiqueta() {
		etiqueta++;
		return etiqueta;
	}

	public static int getEtiqueta() {
		return etiqueta;
	}
	
	public static void escribirEtiquetaASM() {
		codigo += "L " + etiqueta + ":";
	}
	public static void escribirEtiquetaASM(int etiqueta) {
		codigo += "L " + etiqueta + ":";
	}

	public static void escribirASM(String sentencia) {
		codigo += '\t' + sentencia + '\n';
	}

	public static void escribirFicheroFinalASM() throws IOException {
		if (volcar_salida) {
			FileWriter fw = new FileWriter(fichero_destino);
			fw.write(codigo + '\n');
			fw.close();
		}
	}
}
