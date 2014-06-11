package com.data3000.admin.ngc;

import java.util.List;
import java.util.Map;
import com.data3000.admin.vo.EstructuraMenu;
import com.data3000.admin.vo.Formulario;
import com.data3000.admin.vo.Menu;
import com.data3000.admin.vo.Usuario;

public interface PlataformaNgc {
	/**
	 * Obtiene un mapa con las funcionalidades a las que tiene acceso un usuario
	 * @param usuario
	 * @return
	 */
	public Map<String,Formulario> getFuncionalidadesUsuario(Usuario usuario) throws Exception;
	
	
	/**
	 * Obtiene el menu a pintar para un usuario
	 * @param usuario
	 * @param mapaFormularios 
	 * @return
	 */
	public List<EstructuraMenu> getMenu(Usuario usuario, Map<String, Formulario> mapaFormularios) throws Exception;
	
	/**
	 * Obtiene los datos para una tabla de datos
	 * @param tablaDatos
	 * @return
	 */
	public List<Object> getDatos(Class clase) throws Exception;

}
