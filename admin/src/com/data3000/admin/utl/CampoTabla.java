package com.data3000.admin.utl;

import java.lang.reflect.Field;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Image;

public class CampoTabla {	
	private String nombre;
	private boolean siAccion;
	private EventListener<Event> accion;
	private Image icono;
	private Field atributo;
	
	
	
	
	
	public CampoTabla(String nombre) {
		super();
		this.nombre = nombre;
		this.siAccion = false;
	}
	
	
	
	public CampoTabla(String nombre, EventListener<Event> accion, Image icono) {
		super();
		this.nombre = nombre;
		this.accion = accion;
		this.icono = icono;
		this.siAccion = true;
	}



	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isSiAccion() {
		return siAccion;
	}
	public void setSiAccion(boolean siAccion) {
		this.siAccion = siAccion;
	}
	public EventListener<Event> getAccion() {
		return accion;
	}
	public void setAccion(EventListener<Event> accion) {
		this.accion = accion;
	}
	public Image getIcono() {
		return icono;
	}
	public void setIcono(Image icono) {
		this.icono = icono;
	}



	public Field getAtributo() {
		return atributo;
	}



	public void setAtributo(Field atributo) {
		this.atributo = atributo;
	}
	
	
	

}
