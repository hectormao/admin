package com.data3000.admin.cnt;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.North;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Window;

import com.data3000.admin.cmp.TablaDatos;
import com.data3000.admin.ngc.PlataformaNgc;
import com.data3000.admin.utl.CampoTabla;
import com.data3000.admin.utl.ConstantesAdmin;
import com.data3000.admin.utl.WindowComposer;


public class TablaDatosCnt extends WindowComposer {
	
	private Toolbar herramientas;
	private Div dvTabla;
	private North norte;
	
	private TablaDatos tablaDatos;
	
	private PlataformaNgc plataformaNgc;
	
	private Class clase;
	
	@Override
	public void doAfterCompose(Window win) throws Exception{
		super.doAfterCompose(win);
		
		

		Class clase = (Class) argumentos.get(ConstantesAdmin.ARG_CLASE);
		List<CampoTabla> listaCampos = (List<CampoTabla>) argumentos.get(ConstantesAdmin.ARG_CAMPOS_TABLA);

		
		tablaDatos = new TablaDatos(clase, listaCampos);
		dvTabla.appendChild(tablaDatos);
		tablaDatos.setWidth("100%");
		tablaDatos.setHeight("100%");
		tablaDatos.setMold("paging");
		tablaDatos.setPagingPosition("bottom");
		tablaDatos.setPageSize(10);
		
		
		
		
		
		if(herramientas.getChildren().size() <= 0){
			norte.setVisible(false);
		}
		
		win.addEventListener(Events.ON_USER, new EventListener<Event>() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				
				Map<String,Object> datos = (Map<String,Object>) arg0.getData();
				
				String accion = datos.get(ConstantesAdmin.ACCION).toString(); 
				
				if(accion.equals(ConstantesAdmin.EVENTO_REFRESCAR)){
					
					Object padre = datos.get(ConstantesAdmin.OBJETO_PADRE);
					String nombreAtributo = (String) datos.get(ConstantesAdmin.NOMBRE_ATRIBUTO_PADRE);
					
					refrescarTabla(padre, nombreAtributo);
				}
				
				
			}
		});
	}
	
	private void refrescarTabla(Object padre, String nombreAtributo) throws Exception{
		
		List<Object> datos = null;
		
		if(padre == null){
			datos = plataformaNgc.getDatos(clase);
		} else {
			if(nombreAtributo == null){				
				for(Field atributo : clase.getDeclaredFields()){
					if(atributo.getType().equals(padre.getClass())){
						nombreAtributo = atributo.getName();
						break;
					}
				}
			}
			
			String condicion = plataformaNgc.getCondicionPadre(padre);
			
			datos = plataformaNgc.getDatos(clase, condicion);
		}
		
		tablaDatos.setDatos(datos);
	}
	
	public PlataformaNgc getPlataformaNgc() {
		return plataformaNgc;
	}

	public void setPlataformaNgc(PlataformaNgc plataformaNgc) {
		this.plataformaNgc = plataformaNgc;
	}

	
	
	
	
}
