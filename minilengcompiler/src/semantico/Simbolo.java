package semantico;

import java.util.*;

public class Simbolo {

	public enum Tipo_simbolo {
		PROGRAMA,
		VARIABLE,
		ACCION,
		PARAMETRO;
	}

	public enum Tipo_variable {
		DESCONOCIDO,
		ENTERO,
		BOOLEANO,
		CHAR,
		CADENA;
	}

	public enum Clase_parametro {
		VAL,
		REF;
	}

	// Lista de atributos que definen al símbolo
	String etiqueta;
	String nombre;
	int nivel;
	int dir;
	Tipo_simbolo tipo;
	Tipo_variable variable;
	Clase_parametro parametro;
	boolean visible;
	LinkedList<Simbolo> lista_parametros = new LinkedList<Simbolo>();

	public void introducir_programa(String nombre, int dir) {
		this.nombre = nombre;
		this.dir = dir;
		this.tipo = Tipo_simbolo.PROGRAMA;
	}

	public void introducir_variable(String nombre, Tipo_variable variable, int nivel, int dir) {
		this.nombre = nombre;
		this.variable = variable;
		this.nivel = nivel;
		this.dir = dir;
		this.tipo = Tipo_simbolo.VARIABLE;
	}

	public void introducir_accion(String nombre, int nivel, int dir) {
		this.nombre = nombre;
		this.nivel = nivel;
		this.dir = dir;
		this.tipo = Tipo_simbolo.ACCION;
	}

	public void introducir_parametro(String nombre, Tipo_variable variable,
							Clase_parametro parametro, int nivel, int dir) {
		this.nombre = nombre;
		this.variable = variable;
		this.parametro = parametro;
		this.nivel = nivel;
		this.dir = dir;
		this.tipo = Tipo_simbolo.PARAMETRO;
	}

	public boolean es_programa() {
		return tipo == Tipo_simbolo.PROGRAMA;
	}

	public boolean es_variable() {
		return tipo == Tipo_simbolo.VARIABLE;
	}

	public boolean es_accion() {
		return tipo == Tipo_simbolo.ACCION;
	}

	public boolean es_parametro() {
		return tipo == Tipo_simbolo.PARAMETRO;
	}
	
	public boolean es_valor() {
		return (tipo == Tipo_simbolo.PARAMETRO) && (parametro == Clase_parametro.VAL);
	}

	public boolean es_referencia() {
		return (tipo == Tipo_simbolo.PARAMETRO) && (parametro == Clase_parametro.REF);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LinkedList<Simbolo> getLista_parametros() {
		return lista_parametros;
	}

	public void setLista_parametros(LinkedList<Simbolo> lista_parametros) {
		this.lista_parametros = lista_parametros;
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

	public boolean getVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Tipo_variable getVariable() {
		return variable;
	}

	public void setVariable(Tipo_variable tipo) {
		this.variable = tipo;
	}
	
	public String getEtiqueta() {
		return this.etiqueta;
	}
	
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	
	

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Simbolo)) {
			return false;
		}

		Simbolo s = (Simbolo) o;

		return (nombre.equals(s.nombre)) && (nivel == s.nivel);
	}

	@Override
	public int hashCode() {
		int hash = 1;
		hash = hash * 31 + nombre.hashCode() + ((Integer)nivel).hashCode();

		return hash;
	}
}
