package com.data3000.admin.cnt;

import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.data3000.admin.bd.PltPermiso;
import com.data3000.admin.bd.PltRol;
import com.data3000.admin.ngc.PlataformaNgc;
import com.data3000.admin.utl.WindowComposer;

public class RolPermisosCnt extends WindowComposer {
	

	/**
	 * Atributos
	 */
	private Window winRolPermisos;
	private Textbox txtNombreRol;
	private Textbox txtDescripcionRol;
	private Div divArbolFormularios;
	private Button btnAceptar;
	private Button btnCancelar;
	
	
	/**
	 *Objetos a insertar 
	 */
	private PltRol pltRol;
	private PltPermiso pltPermiso;
	
	/**
	 * Negocio Plataforma
	 */
	private PlataformaNgc plataformaNgc;
	
	

}
