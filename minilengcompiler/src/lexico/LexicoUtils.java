package lexico;

import java.util.HashMap;

public class LexicoUtils {
	private static HashMap<String, Integer> tabla;
	
	public LexicoUtils() {
		tabla  = new HashMap<String, Integer>();
	}
	
	public static void anyadirToken(String token) {
		Integer apariciones = tabla.get(token);
		if (apariciones == null) {
			tabla.put(token, 1);
		 }
		else {
			apariciones++;
			tabla.put(token, apariciones);
		}
	}
	
	public static void mostrarTabla() {
		System.out.println();
		for (String key : tabla.keySet()) {
			System.out.println(key + "--->" + tabla.get(key));
		}
	}
	
	public static void mostrarErrorLexico(int filaError, int columnaError, char curChar) {
		System.out.println("ERROR LÉXICO (" + filaError + ","
				+ columnaError + "): símbolo no reconocido: " + curChar);
	}
}