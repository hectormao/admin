package com.data3000.admin.cnt;

import java.util.Date;

import org.apache.log4j.Logger;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.data3000.admin.bd.PltUsuario;
import com.data3000.admin.exc.PltException;
import com.data3000.admin.ngc.UsuarioNgc;
import com.data3000.admin.utl.ConstantesAdmin;
import com.data3000.admin.utl.EncriptarClave;
import com.data3000.admin.utl.WindowComposer;
import com.data3000.admin.vo.Formulario;

public class UsuarioCnt extends WindowComposer {
	
	
	
//	ATRIBUTOS
	private Window winUsuario;
	private Textbox txtNombreCompleto;
	private Textbox txtCorreo;
	private Textbox txtLogin;
	private Textbox txtClave;
	private Textbox txtConfirmarClave;
	private Button btnAceptar;
	private Button btnCancelar;
	private Label lblConfirmarClave;
	
	/**
	 * Objeto a insertar
	 */
	private PltUsuario usu;
	
	/**
	 * Negocio Usuario
	 */
	private UsuarioNgc usuarioNgc;
	
	/**
	 * Log (log4j)
	 */
	private Logger logger = Logger.getLogger(this.getClass());
	
	
	public void doAfteCompose(Window winUsuario) throws Exception {

		super.doAfterCompose(winUsuario);
		logger = Logger.getLogger(this.getClass());
		btnAceptar.setAutodisable("self");
		
		

	}

	public void onCreate$winUsuario(Event event) {
		try {
			if (logger.isDebugEnabled())logger.debug(new StringBuilder("Formulario = ").append(formulario.getNombre()));
			if (formulario.getTipo().equalsIgnoreCase(ConstantesAdmin.FORMULARIO_TIPO_INSERTAR)) {
				// Se instancia nuevo objeto de usuario
				usu = new PltUsuario();

			} else if (formulario.getTipo().equalsIgnoreCase(ConstantesAdmin.FORMULARIO_TIPO_EDITAR)) {

				usu = (PltUsuario) argumentos.get(ConstantesAdmin.ARG_SELECCION);

				if (usu == null) {
					throw new PltException(ConstantesAdmin.ERR0007);
				}

				cargarDatosUsuario(usu);

			}else if(formulario.getTipo().equalsIgnoreCase(ConstantesAdmin.FORMULARIO_TIPO_BORRAR)){
				usu = (PltUsuario) argumentos.get(ConstantesAdmin.ARG_SELECCION);
				cargarDatosUsuario(usu);
				soloConsulta();
			}
		} catch (PltException e) {
			logger.error(new StringBuilder("Error metodo onCreate usuario").append(e.getClass().getName()).append(": ").append(e.getMessage()), e);
			Messagebox.show(e.getMessage(), Labels.getLabel("data3000.error"),Messagebox.OK, Messagebox.ERROR);
			self.detach();

		}

	}

	private void cargarDatosUsuario(PltUsuario usua) {
//		Cargar datos en el formulario
		txtNombreCompleto.setValue(usua.getUsuaNombre());
		txtCorreo.setValue(usua.getUsuaCorreo());
		txtLogin.setValue(usua.getUsuaLogin());
		txtLogin.setReadonly(true);
		txtLogin.setStyle("background-color:#D8D8D8");
		txtClave.setValue(usua.getUsuaClave());
		txtClave.setReadonly(true);
		txtClave.setStyle("background-color:#D8D8D8");
		txtConfirmarClave.setVisible(Boolean.FALSE);
		lblConfirmarClave.setVisible(Boolean.FALSE);
	}
	
	public void onClick$btnAceptar(Event evt) throws Exception{
		
		establecerDatos();
		
		if(formulario.getTipo().equals(ConstantesAdmin.FORMULARIO_TIPO_INSERTAR)){
			usuarioNgc.crearUsuario(usu, usu.getLogin());
		} else if (formulario.getTipo().equalsIgnoreCase(ConstantesAdmin.FORMULARIO_TIPO_EDITAR)) {
			usuarioNgc.modificarUsuario(usu);
		}
		
		
		Events.sendEvent(new Event(Events.ON_CLOSE,this.self,null));
	}
	
	public void onClick$btnCancelar(Event evt) throws Exception{
		Events.sendEvent(new Event(Events.ON_CLOSE,this.self,null));
		
	}

	private void establecerDatos() throws WrongValueException, Exception {
		EncriptarClave encriptarClave = new EncriptarClave();
		usu.setUsuaNombre(txtNombreCompleto.getValue());
		usu.setUsuaCorreo(txtCorreo.getValue());
		usu.setUsuaLogin(txtLogin.getValue());		
		usu.setUsuaClave(encriptarClave.encryptPassword(txtClave.getValue()));		
		usu.setUsuaEstado(ConstantesAdmin.ESTADO_ACTIVO);
		usu.setUsuaInteAute(new Short("0"));
		usu.setUsuaFechClav(new Date());
//		Auditoria
		usu.setAudiFechModi(new Date());
		usu.setAudiChecksum(null);
		usu.setAudiMotiAnul(null);
		usu.setAudiSiAnul(Boolean.FALSE);
		usu.setAudiUsuario(usuario.getLogin());		
		
	}

	public UsuarioNgc getUsuarioNgc() {
		return usuarioNgc;
	}

	public void setUsuarioNgc(UsuarioNgc usuarioNgc) {
		this.usuarioNgc = usuarioNgc;
	}

	
	
	
	
	

}
