package com.data3000.admin.ngc;

import java.util.List;
import java.util.Map;

import com.data3000.admin.bd.PltFormulario;
import com.data3000.admin.bd.PltRol;
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
	
	/**
	 * Obtiene los datos ejecutando la consulta hql
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	public List<Object> getDatos(Class clase, String condicion) throws Exception;


	public String getCondicionPadre(Object padre);
	
	/**Crea un Rol, de no existir el mismo rol
	 * @param pltRol
	 * @throws Exception
	 */
	public void crearRol(PltRol pltRol) throws Exception;
	
	/**Modifica un Rol
	 * @param pltRol
	 * @throws Exception
	 */
	public void modificarRol(PltRol pltRol) throws Exception;
	
	/**Elimina un Rol
	 * @param pltRol
	 * @throws Exception
	 */
	public void eliminarRol(PltRol pltRol) throws Exception;
	
	/**
	 * Obtiene todos los roles ordenados por su nombre
	 * @return
	 * @throws Exception
	 */
	public List<PltRol> getRoles() throws Exception;
	
	
	
	/**
	 * Obtiene el valor de una variable del entorno
	 * @param propiedad
	 * @return
	 */
	public String getEnv(String propiedad);
	
	/**
	 * Obtiene lista de todos formularios/funcionalidades del sistema
	 * @return
	 * @throws Exception
	 */
	public List<PltFormulario> getFormularios() throws Exception;

}
