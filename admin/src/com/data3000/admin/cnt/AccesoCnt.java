package com.data3000.admin.cnt;

import org.apache.log4j.Logger;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.data3000.admin.exc.PltException;
import com.data3000.admin.ngc.UsuarioNgc;
import com.data3000.admin.utl.ConstantesAdmin;
import com.data3000.admin.vo.Usuario;

public class AccesoCnt extends GenericForwardComposer<Window> {
	
	
	private UsuarioNgc usuarioNgc;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private Window winAcceso;
	private Textbox txtUsuario;
	private Textbox txtClave;
	
	 
	
	@Override
	public void doAfterCompose(Window winIndex) throws Exception{
		
		super.doAfterCompose(winIndex);
		logger = Logger.getLogger(this.getClass());
		
		
	} 
	
	public void onClick$btnIngresar() throws Exception{
		
		if(validar()){
			
			String login = txtUsuario.getValue();
			String clave = txtClave.getValue();
			try{
				Usuario usuario = usuarioNgc.validarAcceso(login, clave);
				
				Events.sendEvent(Events.ON_CLOSE,winAcceso,usuario);
			} catch(PltException ex){
				Messagebox.show(Labels.getLabel(ex.getCodigo()), "Error", Messagebox.OK, Messagebox.ERROR);
				logger.error("Error de logueo",ex);
			} catch(Exception ex){
				Messagebox.show(ex.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
				logger.error("Error Desconocido de logueo",ex);
			}
						
		}
		
		
	}
	
	private boolean validar() throws Exception{
		
		boolean siError = false;
		
		String usuario = txtUsuario.getValue();
		
		if(usuario == null || (usuario != null && usuario.trim().length() <= 0)){
			txtUsuario.setErrorMessage(Labels.getLabel(ConstantesAdmin.ERR0002));
			siError = true;
		}
		
		String clave = txtClave.getValue();
		if(clave == null || (clave != null && clave.trim().length() <= 0)){
			txtClave.setErrorMessage(Labels.getLabel(ConstantesAdmin.ERR0002));
			siError = true;
		}
		
		return ! siError;
	}

	public UsuarioNgc getUsuarioNgc() {
		return usuarioNgc;
	}

	public void setUsuarioNgc(UsuarioNgc usuarioNgc) {
		this.usuarioNgc = usuarioNgc;
	}
	
	

}
