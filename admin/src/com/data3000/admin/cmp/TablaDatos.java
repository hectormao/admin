package com.data3000.admin.cmp;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.zkoss.util.resource.Labels;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

public class TablaDatos extends Listbox implements ListitemRenderer<Object> {
	
	private Class clase;
	
	private ListModelList<Object> modelo;
	
	private List<Field> campos;
	
	private Logger logger;
	
	private Map<String,Boolean> mapaCampos;
	
	public TablaDatos(Class clase){
		this(clase,null);
	}
	
	public TablaDatos(Class clase, List<String> listaCampos){
		super();
		
		logger = Logger.getLogger(this.getClass());
		
		modelo = new ListModelList<Object>();
		this.setModel(modelo);
		this.setItemRenderer(this);
		
		this.clase = clase;
		
		if(listaCampos != null){
			mapaCampos = new HashMap<String, Boolean>();
			for(String nombreCampo : listaCampos){
				mapaCampos.put(nombreCampo, Boolean.TRUE);
			}
		}
		
		
		campos = new ArrayList<Field>();
		Field[] camposAux = clase.getDeclaredFields();
		
		Listhead cabeceraTabla = new Listhead();
		cabeceraTabla.setSizable(true);
		for(Field campo : camposAux){
			
			String campoNombre = campo.getName();
			
			Boolean siPintar = null;
			if(mapaCampos == null){
				siPintar = Boolean.TRUE;
			} else {
				siPintar = mapaCampos.get(campoNombre);
				if(siPintar == null){
					siPintar = Boolean.FALSE;
				}
				
			}
			
			if(siPintar){
				Listheader columna = new Listheader();
				String leyenda = Labels.getLabel(campo.getName());
				if(leyenda == null || (leyenda != null && leyenda.length() <= 0)){
					leyenda = campo.getName();
				}
				columna.setLabel(leyenda);
				cabeceraTabla.appendChild(columna);
				campos.add(campo);
			}
		}
		
		this.appendChild(cabeceraTabla);
		
	}
	
	
	
	
	public void setDatos(List<Object> datos){
		modelo.clear();
		modelo.addAll(datos);		
	}
	

	@Override
	public void render(Listitem item, Object data, int index) throws Exception {
		
		item.setValue(data);
		for(Field campo : campos){
			
			String nombreCampo = campo.getName();
			
			String nombreMetodo = new StringBuilder(campo.getType().equals(Boolean.class) || campo.getType().equals(boolean.class) ? "is" : "get").append(nombreCampo.substring(0, 1).toUpperCase()).append(nombreCampo.substring(1)).toString();
			
			try{
				
				Method metodo = clase.getMethod(nombreMetodo);				
				Object objeto = metodo.invoke(data);
				
				Listcell celda = new Listcell(objeto != null ? objeto.toString() : "");
				item.appendChild(celda);
				
				
			} catch(NoSuchMethodError ex){
				logger.error(new StringBuilder(ex.getClass().getName()).append(": ").append(ex.getMessage()),ex);
			}
			
			
		}
		
		
	}
	

}
