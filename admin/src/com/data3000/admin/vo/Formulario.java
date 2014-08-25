package com.data3000.admin.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Formulario {
	
	private List<FormularioHijo> hijos;
	
	private Map<String,String> atributos;
	
	public abstract Long   getId();
	public abstract String getNombre();
	public abstract String getTooltip();
	public abstract String getUrl();
	public abstract String getTipo();
	public abstract String getUrlIcono();
	
	public Map<String,String> getAtributos(){
		return atributos;
	}
	
	public String getAtributo(String nombre){
		return atributos != null ? atributos.get(nombre) : null;
	}
	
	public void addAtributo(String nombre, String valor){
		if(atributos == null){
			atributos = new HashMap<String, String>();
		}		
		atributos.put(nombre,valor);
	}
	
	public void addHijo(FormularioHijo hijo){
		if(hijos == null){
			hijos = new ArrayList<FormularioHijo>();
		}
		
		hijos.add(hijo);
		
	}
	
	public List<FormularioHijo> getHijos(){
		return hijos;
	}
	

}
