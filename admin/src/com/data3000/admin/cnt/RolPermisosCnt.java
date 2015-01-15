package com.data3000.admin.cnt;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Div;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Window;

import com.data3000.admin.bd.PltFormulario;
import com.data3000.admin.bd.PltPermiso;
import com.data3000.admin.bd.PltRol;
import com.data3000.admin.bd.PltUsuario;
import com.data3000.admin.exc.PltException;
import com.data3000.admin.ngc.PlataformaNgc;
import com.data3000.admin.utl.ConstantesAdmin;
import com.data3000.admin.utl.WindowComposer;

public class RolPermisosCnt extends WindowComposer {
	

	/**
	 * Atributos
	 */
	private Window winRolPermisos;
	private Textbox txtNombreRol;
	private Textbox txtDescripcionRol;
	private Button btnAceptar;
	private Button btnCancelar;
	private Tree treePermisoRol;
	private Treechildren tchPermisoRol;	
	
	/**
	 * Atributos del arbol
	 */
	
	
	/**
	 *Objetos a insertar 
	 */
	private PltRol pltRol;
	private PltPermiso pltPermiso;
	
	/**
	 * Negocio Plataforma
	 */
	private PlataformaNgc plataformaNgc;
	
	/**
	 * Log (log4j)
	 */
	private Logger logger = Logger.getLogger(this.getClass());
	
	private List<PltFormulario> listaFormularios;
	
	
	public void doAfteCompose(Window winRolPermisos) throws Exception {

		super.doAfterCompose(winRolPermisos);
		logger = Logger.getLogger(this.getClass());
		btnAceptar.setAutodisable("self");	
				
	}
	
	public void onCreate$winRolPermisos(Event event) throws Exception {
		try {
			if (logger.isDebugEnabled())logger.debug(new StringBuilder("Formulario = ").append(formulario.getNombre()));
			if (formulario.getTipo().equalsIgnoreCase(ConstantesAdmin.FORMULARIO_TIPO_INSERTAR)) {
				// Se instancia nuevo objeto de rol
				pltRol = new PltRol();
				
				//Arbol de Funcionalidades
				listaFormularios = plataformaNgc.getFormularios();
				Map<String,Object> mapaModuloFormulario = new HashMap<String, Object>();	
				
				Treechildren treechildrenAux = new Treechildren();
				
				
				
				for(PltFormulario formulario :listaFormularios){
					
//					if(mapaModuloFormulario.get(formulario.getFormModulo()) == null){
//						//Se instancian elementos del arbol
//						Treeitem treeitemPadre = new Treeitem();
//						Treechildren treechildren = new Treechildren();
//						Treeitem treeitem2 = new Treeitem();
//						Treerow treerow = new Treerow();
//						Treecell treecell = new Treecell();
//						
//						treeitemPadre.setLabel(formulario.getFormModulo());
//						treecell.setAttribute(ConstantesAdmin.ATRIBUTO_FORMULARIO, formulario);
//						treecell.setLabel(formulario.getFormNombre());
//						treerow.appendChild(treecell);
//						treeitem2.appendChild(treerow);
//						treechildren.appendChild(treeitem2);
//						treeitemPadre.appendChild(treechildren);					
//						tchPermisoRol.appendChild(treeitemPadre);
//						mapaModuloFormulario.put(formulario.getFormModulo(), formulario);
//						treechildrenAux = treechildren;
//					}else{
//						mapaModuloFormulario.put(formulario.getFormModulo(),formulario);
//						cargarHijosArbol(treechildrenAux, formulario);
//					}
					
					if(mapaModuloFormulario.get(formulario.getFormModulo()) == null){
						Treeitem treeitemPadre = new Treeitem(formulario.getFormModulo());
						Treechildren treechildren = new Treechildren();
						Treeitem treeitem2 = new Treeitem(formulario.getFormNombre(),formulario);
						
						treechildren.appendChild(treeitem2);
						treeitemPadre.appendChild(treechildren);					
						tchPermisoRol.appendChild(treeitemPadre);
						mapaModuloFormulario.put(formulario.getFormModulo(), formulario);
						treechildrenAux = treechildren;
					}else{
						mapaModuloFormulario.put(formulario.getFormModulo(),formulario);
						cargarHijosArbol(treechildrenAux, formulario);
					}
				}
				
			} else if (formulario.getTipo().equalsIgnoreCase(ConstantesAdmin.FORMULARIO_TIPO_EDITAR)) {

				pltRol = (PltRol) argumentos.get(ConstantesAdmin.ARG_SELECCION);

				if (pltRol == null) {
					throw new PltException(ConstantesAdmin.ERR0007);
				}

				cargarDatosRolPermisos(pltRol);

			}
		} catch (PltException e) {
			logger.error(new StringBuilder("Error metodo onCreate rolPermisos").append(e.getClass().getName()).append(": ").append(e.getMessage()), e);
			Messagebox.show(e.getMessage(), Labels.getLabel("data3000.error"),Messagebox.OK, Messagebox.ERROR);
			self.detach();

		}

	}
	
	private void cargarHijosArbol(Treechildren treechildren, PltFormulario formulario){
		Treeitem treeitem = new Treeitem(formulario.getNombre(),formulario);
		treechildren.appendChild(treeitem);
			
	}
	
	private void cargarDatosRolPermisos(PltRol rol){
//		Cargar datos en el formulario
		txtNombreRol.setValue(rol.getRolNombre());
		txtDescripcionRol.setValue(rol.getRolDescripcion());		
	}
	
	public void onClick$btnAceptar(Event evt) throws Exception{
		
		establecerDatosRolPermisos();
		
		if(formulario.getTipo().equals(ConstantesAdmin.FORMULARIO_TIPO_INSERTAR)){			
			plataformaNgc.crearRol(pltRol);
		} else if (formulario.getTipo().equalsIgnoreCase(ConstantesAdmin.FORMULARIO_TIPO_EDITAR)) {
			plataformaNgc.modificarRol(pltRol);
		}
		
		
		Events.sendEvent(new Event(Events.ON_CLOSE,this.self,null));
	}	

	public void onClick$btnCancelar(Event evt) throws Exception{
		Events.sendEvent(new Event(Events.ON_CLOSE,this.self,null));
		
	}
	
	private void establecerDatosRolPermisos() throws WrongValueException, Exception{
		pltRol.setRolNombre(txtNombreRol.getValue());
		pltRol.setRolDescripcion(txtDescripcionRol.getValue());
//		Auditoria
		pltRol.setAudiFechModi(new Date());
		pltRol.setAudiChecksum(null);
		pltRol.setAudiMotiAnul(null);
		pltRol.setAudiSiAnul(Boolean.FALSE);
		pltRol.setAudiUsuario(usuario.getLogin());
		
	}

	public PlataformaNgc getPlataformaNgc() {
		return plataformaNgc;
	}

	public void setPlataformaNgc(PlataformaNgc plataformaNgc) {
		this.plataformaNgc = plataformaNgc;
	}
	
	

}
