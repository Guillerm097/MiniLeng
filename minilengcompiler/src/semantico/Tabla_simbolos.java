package semantico;

import java.util.*;
import semantico.Simbolo.Tipo_variable;
import semantico.excepciones.SimboloNoEncontradoException;
import semantico.Simbolo.Clase_parametro;

public class Tabla_simbolos {

	int nivel; // Nivel actual
	int tam; // Tamaño de la tabla
	int dir; // Direccion actual (puntero dentro del bloque de activación)
	ArrayList<LinkedList<Simbolo>> tabla;
	static Integer[] TP; // Tabla de permutaciones

	public Tabla_simbolos() {
		nivel = 0;
		dir = 3;
		tam = 256; // TODO: Ajustar valor
		tabla = new ArrayList<LinkedList<Simbolo>>(tam);
		for (int i = 0; i < tam; i++) {
			tabla.add(new LinkedList<Simbolo>());
		}
	}

	public Tabla_simbolos(int tamanyo) {
		nivel = 0;
		dir = 3;
		tam = tamanyo;
		tabla = new ArrayList<LinkedList<Simbolo>>(tam);
		for (int i = 0; i < tam; i++) {
			tabla.add(new LinkedList<Simbolo>());
		}
	}

	public void inicializar_tabla() {
		vectorPermutaciones();
	}

	// Buscar símbolo de mayor nivel (En teoría el de mayor nivel siempre más a la izq en la lista)
	public Simbolo buscar_simbolo(String nombre) throws SimboloNoEncontradoException{
		int pos = hash(nombre);
		
		// Buscar en la lista de colisiones
		LinkedList<Simbolo> ls = tabla.get(pos);
		Simbolo s = new Simbolo();
		boolean encontrado = false;
		ListIterator<Simbolo> it = ls.listIterator();
		while (it.hasNext() && (!encontrado)) {
			s = it.next();
			if (s.getNombre().equals(nombre))
				encontrado = true;
		}
		
		if (encontrado)
			return s;
		else
			throw new SimboloNoEncontradoException(nombre);
	}

	public Simbolo introducir_programa(String nombre, int dir) {
		Simbolo s = new Simbolo();
		s.introducir_programa(nombre, dir);
		int pos = hash(nombre);
		LinkedList<Simbolo> ls = tabla.get(pos);
		
		if (ls.contains(s)) {
			return null;
		}
		else {
			ls.addFirst(s);
			// TODO? tabla.set(pos, ls);
			
			return s;
		}
	}

	public Simbolo introducir_variable(String nombre, Tipo_variable variable, int nivel, int dir) {
		Simbolo s = new Simbolo();
		s.introducir_variable(nombre, variable, nivel, dir);
		int pos = hash(nombre);
		
		LinkedList<Simbolo> ls = tabla.get(pos);
		
		if (ls.contains(s)) {
			return null;
		}
		else {
			ls.addFirst(s);
			// TODO? tabla.set(pos, ls);
			
			return s;
		}
	}

	public Simbolo introducir_accion(String nombre, int nivel, int dir) {
		Simbolo s = new Simbolo();
		s.introducir_accion(nombre, nivel, dir);
		int pos = hash(nombre);
		
		LinkedList<Simbolo> ls = tabla.get(pos);
		
		if (ls.contains(s)) {
			return null;
		}
		else {
			ls.addFirst(s);
			//TODO ? tabla.set(pos, ls);
			
			return s;
		}
	}

	public Simbolo introducir_parametro(String nombre, Simbolo.Tipo_variable variable,
										Clase_parametro parametro, int nivel, int dir) {
		Simbolo s = new Simbolo();
		s.introducir_parametro(nombre, variable, parametro, nivel, dir);
		int pos = hash(nombre);

		LinkedList<Simbolo> ls = tabla.get(pos);
		
		if (ls.contains(s)) {
			return null;
		}
		else {
			ls.addFirst(s);
			// TODO? tabla.set(pos, ls);
			
			return s;
		}
	}

	public void eliminar_programa() {
		for (int i = 0; i < tam; i++) {
			LinkedList<Simbolo> ls = tabla.get(i);
			ListIterator<Simbolo> it = ls.listIterator();			
			
			while(it.hasNext()) {
				if((it.next().es_programa())) {
					it.remove();
				}
			}
		}
	}

	public void eliminar_variables(int nivel) {
		for (int i = 0; i < tam; i++) {
			LinkedList<Simbolo> ls = tabla.get(i);
			ListIterator<Simbolo> it = ls.listIterator();			
			
			while(it.hasNext()) {
				Simbolo s = it.next();
				if((s.es_variable()) && (s.getNivel() == nivel)) {
					it.remove();
				}
			}
		}
	}

	public void ocultar_parametros(int nivel) {
		for (int i = 0; i < tam; i++) {
			LinkedList<Simbolo> ls = tabla.get(i);
			ListIterator<Simbolo> it = ls.listIterator();			
			
			while(it.hasNext()) {
				Simbolo s = it.next();
				if((s.es_variable()) && (s.getNivel() == nivel)) {
					s.setVisible(false);
				}
			}
		}
		
	}
	
	public void eliminar_parametros_ocultos(int nivel) {
		for (int i = 0; i < tam; i++) {
			LinkedList<Simbolo> ls = tabla.get(i);
			ListIterator<Simbolo> it = ls.listIterator();			
			
			while(it.hasNext()) {
				Simbolo s = it.next();
				if((s.es_parametro()) && (s.getNivel() == nivel) && (s.getVisible() == false)) {
					it.remove();
				}
			}
		}
	}

	public void eliminar_acciones_parametros_ocultos(Simbolo parametro) {
		for (int i = 0; i < tam; i++) {
			LinkedList<Simbolo> ls = tabla.get(i);
			ListIterator<Simbolo> it = ls.listIterator();			
			
			while(it.hasNext()) {
				Simbolo s = it.next();
				if((s.es_variable()) && (s.getNivel() == nivel) && (s.getLista_parametros().contains(parametro))) {
					it.remove();
				}
			}
		}
	}

	public void eliminar_acciones(int nivel) {
		for (int i = 0; i < tam; i++) {
			LinkedList<Simbolo> ls = tabla.get(i);
			ListIterator<Simbolo> it = ls.listIterator();			
			
			while(it.hasNext()) {
				Simbolo s = it.next();
				if((s.es_accion()) && (s.getNivel() == nivel)) {
					it.remove();
					eliminar_parametros_acciones(s.getLista_parametros());
				}
			}
		}
	}

	public void eliminar_parametros_acciones(LinkedList<Simbolo> ls_parametros) {
		for (int i = 0; i < tam; i++) {
			LinkedList<Simbolo> ls_tabla_global = tabla.get(i);
			ListIterator<Simbolo> it = ls_parametros.listIterator();
			
			while(it.hasNext()) {
				Simbolo s = it.next();
				// Eliminar aquellos parámetros que se encuentren en la lista de la acción
				if((ls_tabla_global.contains(s)) && (ls_parametros.contains(s))) {
					it.remove();
				}
			}
		}
		
	}

	public void vectorPermutaciones() {
		Integer T[] = new Integer[tam];
		for(int i = 0; i < tam; i++) {
			T[i] = i;
		}

		List<Integer> list = Arrays.asList(T);
		Collections.shuffle(list);
		TP = list.stream().toArray(Integer[]::new);
	}

	public int hash(String nombre) { // Pearson
		int h = 0;
		while (!nombre.isEmpty()) {
			int c = nombre.charAt(0);
			h = TP[h^c % tam];
			nombre = nombre.substring(1);
		}

		return h;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public static void main(String[] args) throws SimboloNoEncontradoException{
		// TEST
		Tabla_simbolos ts = new Tabla_simbolos();
		ts.inicializar_tabla();
		ts.introducir_variable("prueba", Tipo_variable.CADENA, 0, 0);
		ts.eliminar_variables(0);
		ts.introducir_accion("cerda", 0, 0);
		ts.introducir_accion("cerda", 0, 0);
		System.out.println("null:" + ts.introducir_accion("cerda", 0, 0));
		try {
		ts.buscar_simbolo("prueba");
		}
		catch(SimboloNoEncontradoException e) {
			System.out.println("OK");
		}
	}
}
