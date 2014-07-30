package com.data3000.admin.cnt;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.data3000.admin.bd.PltUsuario;
import com.data3000.admin.ngc.UsuarioNgc;
import com.data3000.admin.utl.WindowComposer;
import com.data3000.admin.vo.Formulario;

public class UsuarioCnt extends WindowComposer {
	
	/**
	 * Negocio Usuario
	 */
	private UsuarioNgc usuarioNgc;
	
	/**
	 * Log (log4j)
	 */
	private Logger logger = Logger.getLogger(this.getClass());
	
	private Window winUsuario;
	private Textbox txtNombreCompleto;
	private Textbox txtCorreo;
	private Textbox txtLogin;
	private Textbox txtClave;
	private Button btnAceptar;
	private Button btnCancelar;
	
	/**
	 * Objeto a insertar
	 */
	private PltUsuario usuario;
	
	public void doAfteCompose(Window winUsuario) throws Exception{
		
		super.doAfterCompose(winUsuario);
		logger = Logger.getLogger(this.getClass());
		btnAceptar.setAutodisable("self");
		txtClave.setType("password");
		
	}
	
	public void onCreate$winUsuario(Event event) throws InterruptedException {
		
	}

}
