package com.data3000.admin.cnt;

import java.util.List;
import java.util.Map;

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
import com.data3000.admin.utl.ConstantesAdmin;
import com.data3000.admin.utl.WindowComposer;


public class TablaDatosCnt extends WindowComposer {
	
	private Toolbar herramientas;
	private Div dvTabla;
	private North norte;
	
	private TablaDatos tablaDatos;
	
	private PlataformaNgc plataformaNgc;
	
	@Override
	public void doAfterCompose(Window win) throws Exception{
		super.doAfterCompose(win);
		
		
		Class clase = (Class) argumentos.get(ConstantesAdmin.ARG_CLASE);
		
		tablaDatos = new TablaDatos(clase);
		dvTabla.appendChild(tablaDatos);
		tablaDatos.setWidth("100%");
		tablaDatos.setHeight("100%");
		tablaDatos.setMold("paging");
		tablaDatos.setPagingPosition("bottom");
		tablaDatos.setPageSize(10);
		
		
		List<Object> datos = plataformaNgc.getDatos(clase);		
		tablaDatos.setDatos(datos);
		
		
		if(herramientas.getChildren().size() <= 0){
			norte.setVisible(false);
		}
	}

	public PlataformaNgc getPlataformaNgc() {
		return plataformaNgc;
	}

	public void setPlataformaNgc(PlataformaNgc plataformaNgc) {
		this.plataformaNgc = plataformaNgc;
	}

	
	
	
	
}
