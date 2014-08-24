package com.data3000.admin.cnt;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
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
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import com.data3000.admin.bd.PltFormulario;
import com.data3000.admin.cmp.TablaDatos;
import com.data3000.admin.ngc.PlataformaNgc;
import com.data3000.admin.utl.CampoTabla;
import com.data3000.admin.utl.ConstantesAdmin;
import com.data3000.admin.utl.WindowComposer;
import com.data3000.admin.vo.Formulario;
import com.data3000.admin.vo.FormularioHijo;

public class TablaDatosCnt extends WindowComposer {

	private Toolbar herramientas;
	private Div dvTabla;
	private North norte;

	private TablaDatos tablaDatos;

	private PlataformaNgc plataformaNgc;

	private Class clase;

	@Override
	public void doAfterCompose(Window win) throws Exception {
		super.doAfterCompose(win);

		List<FormularioHijo> hijos = formulario.getHijos();
		if(hijos != null){
			for (FormularioHijo hijo : hijos) {
				switch (hijo.getTipo()) {
				case ConstantesAdmin.HIJO_BOTON:
					//crearBoton
					crearBotonHerramienta(hijo.getHijo());
					break;
				case ConstantesAdmin.HIJO_CELDA:
					//TODO crear boton como celda de la 
					break;
	
				case ConstantesAdmin.HIJO_DETALLE:
					//TODO crear detalle
					break;
	
				default:
					break;
				}
			}
		}
		clase = (Class) argumentos.get(ConstantesAdmin.ARG_CLASE);
		List<CampoTabla> listaCampos = (List<CampoTabla>) argumentos
				.get(ConstantesAdmin.ARG_CAMPOS_TABLA);

		tablaDatos = new TablaDatos(clase, listaCampos);
		dvTabla.appendChild(tablaDatos);
		tablaDatos.setWidth("100%");
		tablaDatos.setHeight("100%");
		tablaDatos.setMold("paging");
		tablaDatos.setPagingPosition("bottom");
		tablaDatos.setPageSize(10);

		if (herramientas.getChildren().size() <= 0) {
			norte.setVisible(false);
		}

		win.addEventListener(Events.ON_USER, new EventListener<Event>() {

			@Override
			public void onEvent(Event arg0) throws Exception {

				Map<String, Object> datos = (Map<String, Object>) arg0.getData();
				argumentos.putAll(datos);
				
				String accion = datos.get(ConstantesAdmin.ACCION).toString();
				
				
				if (accion.equals(ConstantesAdmin.EVENTO_REFRESCAR)) {

					Object padre = datos.get(ConstantesAdmin.OBJETO_PADRE);
					String nombreAtributo = (String) datos.get(ConstantesAdmin.NOMBRE_ATRIBUTO_PADRE);
					
					
					
					refrescarTabla(padre, nombreAtributo);
				}

			}
		});
	}

	private void crearBotonHerramienta(final Formulario hijo) {
		Toolbarbutton boton = new Toolbarbutton();
		boton.setImage(hijo.getUrlIcono());
		
		String tooltip = Labels.getLabel(hijo.getTooltip());
		if(tooltip == null){
			tooltip = hijo.getTooltip();
		}
		
		boton.setTooltiptext(tooltip);
		
		final Window estaVentana = this.self;
		
		boton.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				Map<String,Object> argumentosHijo = new HashMap<String, Object>();
				argumentosHijo.putAll(argumentos);
				Listitem li = tablaDatos.getSelectedItem();
				Object valor = null;
				if(li != null){
					valor = li.getValue();
				}
				argumentosHijo.put(ConstantesAdmin.ARG_SELECCION, valor);
				
				
				java.io.InputStream zulInput = this.getClass().getClassLoader().getResourceAsStream(hijo.getUrl()) ;
				java.io.Reader zulReader = new java.io.InputStreamReader(zulInput) ;
				
				
				Window winCargar = (Window) Executions.createComponentsDirectly(zulReader,"zul",estaVentana,argumentosHijo) ;
				
				String nombre = hijo.getNombre();
				
				String titulo = Labels.getLabel(nombre);
				if(titulo == null){
					titulo = nombre;
				}
				
				winCargar.setTitle(titulo);
				winCargar.doModal();
				
				
				
			}
		});
		
		herramientas.appendChild(boton);
		
	}

	private void refrescarTabla(Object padre, String nombreAtributo)
			throws Exception {

		List<Object> datos = null;

		if (padre == null) {
			datos = plataformaNgc.getDatos(clase);
		} else {
			if (nombreAtributo == null) {
				for (Field atributo : clase.getDeclaredFields()) {
					if (atributo.getType().equals(padre.getClass())) {
						nombreAtributo = atributo.getName();
						break;
					}
				}
			}
			StringBuilder where = new StringBuilder(nombreAtributo);
			where.append(".");
			String condicion = plataformaNgc.getCondicionPadre(padre);
			where.append(condicion);
			datos = plataformaNgc.getDatos(clase, where.toString());
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
