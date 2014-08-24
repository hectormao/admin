package com.data3000.admin.vo;

import java.util.ArrayList;
import java.util.List;

public abstract class Formulario {
	
	private List<FormularioHijo> hijos;
	
	public abstract Long   getId();
	public abstract String getNombre();
	public abstract String getTooltip();
	public abstract String getUrl();
	public abstract String getTipo();
	public abstract String getUrlIcono();
	
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
