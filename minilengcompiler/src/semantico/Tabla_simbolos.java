package semantico;

import java.util.*;

import semantico.Simbolo.Tipo_variable;

public class Tabla_simbolos {
		
	public class nodoTabla {
		public Simbolo getSim() {
			return s;
		}
		public void setSim(Simbolo s) {
			this.s = s;
		}		
		
		Simbolo s;
		LinkedList<Simbolo> lista_colisiones = new LinkedList<Simbolo>();
	}
	
	int nivel; // Nivel actual
	int tam; // Tamaño de la tabla
	nodoTabla[] tabla;
	static Integer[] TP; // Tabla de permutaciones
	
	public Tabla_simbolos() {
		tam = 256; // TODO: Ajustar valor
		tabla = new nodoTabla[tam];
	}
	
	public Tabla_simbolos(int tamanyo) {
		tam = tamanyo;
		tabla = new nodoTabla[tam];
	}
	
	public void inicializar_tabla() {
		nivel = 0;
		vectorPermutaciones();
	}
	
	// Buscar símbolo de mayor nivel
	public Simbolo buscar_simbolo(String nombre) throws SimboloNoEncontradoException{
		int pos = hash(nombre);
		Simbolo s = new Simbolo();
		
		if (tabla[pos].getSim().getNombre().equals(nombre)) {
			s = tabla[pos].getSim();
		}
		else {
			ListIterator<Simbolo> listIter = tabla[pos].lista_colisiones.listIterator(1);
			boolean seguir = true;
			
			while(listIter.hasNext() && seguir) {
				if (listIter.next().getNombre().equals(nombre)) {
					s = listIter.previous();
					seguir = false;
				}
			}
		}
		
		if(!s.getNombre().isEmpty()) { // Hay simbolo
			return s;
		}
		else {
			throw new SimboloNoEncontradoException(nombre);
		}
	}
	
	public Simbolo introducir_programa(String nombre, int dir) {
		Simbolo s = new Simbolo();
		s.introducir_programa(nombre, dir);
		int pos = hash(nombre);
		if(tabla[pos] == null) {
			tabla[pos] = new nodoTabla();
		}
		else if(!(tabla[pos].getSim().equals(s)) && (!tabla[pos].lista_colisiones.contains(s))){
			tabla[pos].lista_colisiones.addFirst(s);
		}
		else {
			s = null;
		}
		
		return s;
	}
	
	public Simbolo introducir_variable(String nombre, Simbolo.Tipo_variable variable, int nivel, int dir) {
		Simbolo s = new Simbolo();
		s.introducir_variable(nombre, variable, nivel, dir);
		int pos = hash(nombre);
		if(tabla[pos] == null) {
			tabla[pos] = new nodoTabla();
			tabla[pos].setSim(s);
		}
		else if(!(tabla[pos].getSim().equals(s)) && (!tabla[pos].lista_colisiones.contains(s))){
			tabla[pos].lista_colisiones.addFirst(s);
		}
		else {
			s = null;
		}
		
		return s;
	}
	
	public Simbolo introducir_accion(String nombre, int nivel, int dir) {
		Simbolo s = new Simbolo();
		s.introducir_accion(nombre, nivel, dir);
		int pos = hash(nombre);
		if(tabla[pos] == null) {
			tabla[pos] = new nodoTabla();
			tabla[pos].setSim(s);
		}
		else if(!(tabla[pos].getSim().equals(s)) && (!tabla[pos].lista_colisiones.contains(s))){
			tabla[pos].lista_colisiones.addFirst(s);
		}
		else {
			s = null;
		}
		
		return s;
	}
	
	public Simbolo introducir_parametro(String nombre, Simbolo.Tipo_variable variable, 
										Simbolo.Clase_parametro parametro, int nivel, int dir) {
		Simbolo s = new Simbolo();
		s.introducir_parametro(nombre, variable, parametro, nivel, dir);		
		int pos = hash(nombre);
		if(tabla[pos] == null) {
			tabla[pos] = new nodoTabla();
			tabla[pos].setSim(s);
		}
		else if(!(tabla[pos].getSim().equals(s)) && (!tabla[pos].lista_colisiones.contains(s))){
			tabla[pos].lista_colisiones.addFirst(s);
		}
		else {
			s = null;
		}
		
		return s;
	}
	
	public void eliminar_programa() {
		boolean seguir = true;
		for(nodoTabla nt : tabla) {
			if((nt.getSim().es_programa()) && (nt.getSim().getNivel() == 0)){
				nt.s = null;
			}
			
			// Iterar sobre la lista de simbolos
			ListIterator<Simbolo> it = nt.lista_colisiones.listIterator();
			

			while(it.hasNext() && seguir) {
				if((it.next().es_programa())) {
					it.remove();
					seguir = false;
				}
			}
		}
	}

	public void eliminar_variables(int nivel) {
		for(nodoTabla nt : tabla) {
			if((nt.getSim().es_variable()) && (nt.getSim().getNivel() == nivel)){
				nt.s = null;
			}
			
			// Iterar sobre la lista de simbolos
			Iterator<Simbolo> it = nt.lista_colisiones.iterator();
			while(it.hasNext()) {
				if((it.next().es_variable()) && (nt.getSim().getNivel() == nivel)) {
					it.remove();
				}
			}
		}
	}
	
	public void ocultar_parametros(int nivel) {
		
	}
	
	public void eliminar_parametros_ocultos(int nivel) {
		
	}

	public void eliminar_acciones(int nivel) {
		
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
	
	public static int hash(String nombre) { // Pearson
		int h = 0;
		while (!nombre.isEmpty()) {
			int c = Character.getNumericValue(nombre.charAt(0));
			h = TP[h^c];
			nombre = nombre.substring(1);
		}
		
		return h;
	}
	
	public static void main(String[] args){
		Simbolo s;
		if (s != null) {
			System.out.println("kk");
		}
		
		Tabla_simbolos ts = new Tabla_simbolos();
		ts.inicializar_tabla();
		ts.introducir_variable("prueba", Tipo_variable.CADENA, 0, 0);
		ts.introducir_variable("prueba", Tipo_variable.CADENA, 1, 1);
		ts.introducir_variable("prueba", Tipo_variable.CADENA, 1, 1);
	}
}
