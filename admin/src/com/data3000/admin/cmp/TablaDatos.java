package com.data3000.admin.cmp;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.zkoss.util.resource.Labels;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import com.data3000.admin.utl.CampoTabla;

public class TablaDatos extends Listbox implements ListitemRenderer<Object> {
	
	private Class clase;
	
	private ListModelList<Object> modelo;
	
	private List<CampoTabla> listaCampos;
	
	private Logger logger;
	
	
	
	public TablaDatos(Class clase) throws NoSuchFieldException, SecurityException{
		this(clase,null);
	}
	
	public TablaDatos(Class clase, List<CampoTabla> listaCampos) throws NoSuchFieldException, SecurityException{
		super();
		
		logger = Logger.getLogger(this.getClass());
		
		modelo = new ListModelList<Object>();
		this.setModel(modelo);
		this.setItemRenderer(this);
		
		this.clase = clase;
		
		Listhead cabeceraTabla = new Listhead();
		cabeceraTabla.setSizable(true);
		
		if(listaCampos != null){
			this.listaCampos = listaCampos;
			
			//mapaCampos = new HashMap<String, Boolean>();
			for(CampoTabla campo : listaCampos){
				//mapaCampos.put(nombreCampo, Boolean.TRUE);
				
				String nombre = campo.getNombre();
				
				if(! campo.isSiAccion()){
					Field atributo = clase.getDeclaredField(nombre);
					if(atributo != null){
						pintarColumnaTabla(cabeceraTabla, atributo);
						campo.setAtributo(atributo);
					}
				} else {
					pintarColumnaTabla(cabeceraTabla, nombre);
				}
				
			}
		} else {
			this.listaCampos = new ArrayList<CampoTabla>();
			for(Field atributo : clase.getDeclaredFields()){
				CampoTabla campo = new CampoTabla(atributo.getName());
				this.listaCampos.add(campo);
				pintarColumnaTabla(cabeceraTabla, atributo);
				campo.setAtributo(atributo);
			}
		}
		
		this.appendChild(cabeceraTabla);
	}
	
	private void pintarColumnaTabla(Listhead cabeceraTabla, Field campo){
		pintarColumnaTabla(cabeceraTabla, campo.getName());
	}
	
	private void pintarColumnaTabla(Listhead cabeceraTabla, String nombreCampo){
			Listheader columna = new Listheader();
			String leyenda = Labels.getLabel(nombreCampo);
			if(leyenda == null || (leyenda != null && leyenda.length() <= 0)){
				leyenda = nombreCampo;
			}
			columna.setLabel(leyenda);
			cabeceraTabla.appendChild(columna);		
	}
	
	
	public void setDatos(List<Object> datos){
		modelo.clear();
		modelo.addAll(datos);		
	}
	

	@Override
	public void render(Listitem item, Object data, int index) throws Exception {
		
		item.setValue(data);
		for(CampoTabla campo : listaCampos){
			
			if(! campo.isSiAccion()){
				//es un campo del objeto
				
				Field atributo = campo.getAtributo();
				
				String nombreCampo = atributo.getName();
				
				Class tipo = atributo.getType();
				
				String nombreMetodo = new StringBuilder(tipo.equals(Boolean.class) || tipo.equals(boolean.class) ? "is" : "get").append(nombreCampo.substring(0, 1).toUpperCase()).append(nombreCampo.substring(1)).toString();
				
				try{
					
					Method metodo = clase.getMethod(nombreMetodo);				
					Object objeto = metodo.invoke(data);
					
					Listcell celda = new Listcell(objeto != null ? objeto.toString() : "");
					item.appendChild(celda);
					
					
				} catch(NoSuchMethodError ex){
					logger.error(new StringBuilder(ex.getClass().getName()).append(": ").append(ex.getMessage()),ex);
				}
			} else {
				//TODO pintar boton accion 
			}
			
			
		} 
		
		
	}
	

}
