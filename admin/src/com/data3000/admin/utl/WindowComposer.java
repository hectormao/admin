package com.data3000.admin.utl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Window;

import com.data3000.admin.vo.Formulario;
import com.data3000.admin.vo.Usuario;

public class WindowComposer extends GenericForwardComposer<Window>{

	protected Formulario formulario;
	
	protected Usuario usuario;
	
	protected Map<String,Object> argumentos;
	
	private Logger logger;
	
	@Override
	public void doAfterCompose(Window win) throws Exception{
		
		super.doAfterCompose(win);
		
		logger = Logger.getLogger(this.getClass());
		
		argumentos = (Map<String, Object>) Executions.getCurrent().getArg();
		
		usuario = (Usuario) argumentos.get(ConstantesAdmin.ARG_USUARIO);
		
		if(usuario == null){
			throw new Exception("No hay usuario registrado");
		}
		
		formulario = (Formulario) argumentos.get(ConstantesAdmin.ARG_FORMULARIO);
		
		if(formulario == null){
			throw new Exception("No hay formulario registrado");
		}
		
		if(logger.isDebugEnabled()) logger.debug(new StringBuilder("Usuario: ").append(usuario.getLogin()).append(" Abriendo formulario: ").append(formulario.getNombre()).toString());
	}
	
	
	
}
