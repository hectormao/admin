package com.data3000.admin.ngc;

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
}
