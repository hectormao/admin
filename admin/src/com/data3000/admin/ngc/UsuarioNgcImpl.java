package com.data3000.admin.ngc;

import java.util.Map;

import org.zkoss.zhtml.Tbody;
import org.zkoss.zk.ui.Executions;

import com.data3000.admin.bd.PltUsuario;
import com.data3000.admin.dao.UsuarioDAO;
import com.data3000.admin.exc.PltException;
import com.data3000.admin.utl.ConstantesAdmin;
import com.data3000.admin.utl.EncriptarClave;
import com.data3000.admin.vo.Formulario;
import com.data3000.admin.vo.Usuario;

public class UsuarioNgcImpl implements UsuarioNgc {
	
	
	private UsuarioDAO usuarioDAO;
	
	private PlataformaNgc plataformaNgc;
	

	@Override
	public Usuario validarAcceso(String login, String clave) throws Exception {
		
		PltUsuario usuario = usuarioDAO.getUsuarioPorLogin(login);
		
		if(usuario == null){
			throw new PltException(ConstantesAdmin.ERR0003);
		}
		
		if(usuario.getUsuaEstado().equals(ConstantesAdmin.ESTADO_INACTIVO)){
			throw new PltException(ConstantesAdmin.ERR0005);
		}
		
		String claveEncriptada = EncriptarClave.encryptPassword(clave);
		
		if(! claveEncriptada.equals(usuario.getUsuaClave())){
			throw new PltException(ConstantesAdmin.ERR0004);
		}
		
		Map<String,Formulario> mapaFormularios = plataformaNgc.getFuncionalidadesUsuario(usuario);
		
		Executions.getCurrent().getSession().setAttribute(ConstantesAdmin.SESION_MAPA_FORM,mapaFormularios);
		Executions.getCurrent().getSession().setAttribute(ConstantesAdmin.SESION_USUARIO,usuario);
		return usuario;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public PlataformaNgc getPlataformaNgc() {
		return plataformaNgc;
	}

	public void setPlataformaNgc(PlataformaNgc plataformaNgc) {
		this.plataformaNgc = plataformaNgc;
	}
	
	
	
}
