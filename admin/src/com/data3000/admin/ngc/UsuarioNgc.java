package com.data3000.admin.ngc;

import com.data3000.admin.bd.PltUsuario;
import com.data3000.admin.vo.Usuario;

/**
 * determina el comportamiento del usuario en la plataforma 
 * @author hectormao
 *
 */
public interface UsuarioNgc {
	/**
	 * Valida la autorización de acceso de un usuario a la plataforma por medio de login y contraseña
	 * @param usuario
	 * @param clave
	 * @return
	 */
	public Usuario validarAcceso(String login, String clave) throws Exception;
	
	/**
	 * Crea un usuario, de no existir el mismo login
	 * @param pltUsuario
	 * @param loginUsuario
	 * @throws Exception
	 */
	public void crearUsuario(PltUsuario pltUsuario, String loginUsuario) throws Exception;
	
	/**
	 * Actualiza un usuario
	 * @param pltUsuario
	 * @throws Exception
	 */
	public void modificarUsuario(PltUsuario pltUsuario) throws Exception;
}
