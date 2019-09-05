package generacioncodigo;

import java.io.FileWriter;
import java.io.IOException;

public class Salida {

	private static String fichero_destino, codigo;
	private static int etiqueta;
	public static boolean volcar_salida;

	public Salida(String _fichero_destino) {
		etiqueta = 0;
		fichero_destino = _fichero_destino;
		volcar_salida = true;
		codigo = new String();
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
	
	public static void borrarUltimaLineaASM() {
		int idx = codigo.lastIndexOf("DRF");
		codigo = codigo.substring(0, idx-1);
	}
	
	public static void escribirEtiquetaASM() {
		codigo += "L" + etiqueta + ":\n";
	}

	public static void escribirEtiquetaASM(int etiqueta) {
		codigo += "L" + etiqueta + ":\n";
	}

	public static void escribirASM(String sentencia) {
		codigo += "\t" + sentencia + '\n';
	}
	
	public static void escribirComentarioASM(String sentencia) {
		codigo += sentencia + '\n';
	}
	
	private static void eliminarReferenciasInutiles() {
		int l = 0;
		int idx = codigo.indexOf("L" + l + ":");
		while(idx != -1) {
			int idx_invocacion = codigo.indexOf("L" + l + "\n");
			if (idx_invocacion == -1) {
				codigo = codigo.replaceAll("L" + l + ":" , "");
			}
			l++;
			idx = codigo.indexOf("L" + l + ":");
		}
	}

	public static void escribirFicheroFinalASM() throws IOException {
		if (volcar_salida) {
			eliminarReferenciasInutiles();
			FileWriter fw = new FileWriter(fichero_destino);
			fw.write(codigo + '\n');
			fw.close();
			System.out.println("Compilación finalizada. Se ha generado el fichero " +
								fichero_destino);
		}
	}
}
