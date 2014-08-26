package com.data3000.admin.ngc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.apache.log4j.Logger;


import org.hibernate.sql.ForUpdateFragment;

import com.data3000.admin.bd.PltEnv;
import com.data3000.admin.bd.PltFormAtri;

import com.data3000.admin.bd.PltFormulario;
import com.data3000.admin.bd.PltMenu;
import com.data3000.admin.bd.PltRelaForm;
import com.data3000.admin.bd.PltRol;
import com.data3000.admin.bd.PltUsuario;
import com.data3000.admin.dao.PlataformaDAO;
import com.data3000.admin.dao.PltDAO;
import com.data3000.admin.exc.PltException;
import com.data3000.admin.utl.ConstantesAdmin;
import com.data3000.admin.vo.EstructuraMenu;
import com.data3000.admin.vo.Formulario;
import com.data3000.admin.vo.FormularioHijo;
import com.data3000.admin.vo.Usuario;

public class PlataformaNgcImpl implements PlataformaNgc {

	
	private PlataformaDAO plataformaDAO;
	
	/**
	 * Log (log4j).
	 */
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public Map<String, Formulario> getFuncionalidadesUsuario(Usuario usuario)  throws Exception {
		
		Map<Long, Formulario> mapaAux = new HashMap<Long, Formulario>();
		
		Map<String, Formulario> mapaFuncionalidades = new HashMap<String, Formulario>();
		
		List<Formulario> formularios = plataformaDAO.getFormulariosUsuario((PltUsuario) usuario); 
		
		for(Formulario formulario : formularios){
			mapaFuncionalidades.put(formulario.getNombre(), formulario);
			mapaAux.put(formulario.getId(), formulario);
			List<FormularioHijo> hijos = formulario.getHijos();
			if(hijos != null && ! hijos.isEmpty()){
				hijos.clear();
			}
			
			Map<String,String> mapaAtributos = formulario.getAtributos();
			if(mapaAtributos != null){
				mapaAtributos.clear();
			}
			
			
			Set<PltFormAtri> atributos = ((PltFormulario) formulario).getPltFormAtris();
			for(PltFormAtri atributo : atributos){
				formulario.addAtributo(atributo.getFormAtriNombre(), atributo.getFormAtriValor());
			}
			
			
		}
		
		for(Formulario formulario : formularios){
			Set<PltRelaForm> hijos = ((PltFormulario)formulario).getPltRelaFormsForFormPadre();
			for(PltRelaForm relaForm : hijos){
				Formulario hijo = mapaAux.get(relaForm.getPltFormularioByFormHijo().getFormIdn());
				if(hijo != null){
					
					FormularioHijo formularioHijo = new FormularioHijo();
					formularioHijo.setHijo(hijo);
					formularioHijo.setOrden(relaForm.getRelaFormOrden());
					formularioHijo.setTipo(relaForm.getRelaFormTipo());
					
					formulario.addHijo(formularioHijo);
				}
			}
			
		}
		
		return mapaFuncionalidades;
	}

	@Override
	public List<EstructuraMenu> getMenu(Usuario usuario, Map<String, Formulario> mapaFormularios) throws Exception {
		List<PltMenu> menus = plataformaDAO.getMenu();
		
		List<EstructuraMenu> estructuras = new ArrayList<EstructuraMenu>();
		Map<Long,EstructuraMenu> mapaEstructura = new HashMap<Long, EstructuraMenu>();
		for(PltMenu menu : menus){
			
			
			boolean pintar = true;
			Formulario formularioMenu = menu.getPltFormulario();
			if(formularioMenu != null){
				Formulario formulario = mapaFormularios.get(formularioMenu.getNombre());
				if(formulario != null){
					menu.setPltFormulario((PltFormulario) formulario);
				} else {
					pintar = false;
				}
			}
			
			
			
			
			if(pintar){
				//verifico si ya hay un elemento en el mapa con el mismo id para colocarle el menu
				EstructuraMenu estructuraMenu = mapaEstructura.get(menu.getMenuIdn());
				if(estructuraMenu == null){
					estructuraMenu = new EstructuraMenu();
					estructuraMenu.setMenu(menu);
					mapaEstructura.put(menu.getMenuIdn(), estructuraMenu);
				} else {
					estructuraMenu.setMenu(menu);
				}
				
				
				
				
				PltMenu padre = menu.getPltMenu();
				if(padre == null){
					estructuras.add(estructuraMenu);				
				} else {
					EstructuraMenu estructuraPadre = mapaEstructura.get(padre.getMenuIdn()); 
					if(estructuraPadre == null){
						estructuraPadre = new EstructuraMenu();
						estructuraPadre.addMenuHijo(estructuraMenu);
						mapaEstructura.put(padre.getMenuIdn(), estructuraPadre);
					} else {
						estructuraPadre.addMenuHijo(estructuraMenu);
					}
					
				}
			}
			
		}
		
		
		return estructuras;
	}
	
	@Override
	public List<Object> getDatos(Class clase) throws Exception {		
		return plataformaDAO.getDatos(clase, null);
	}
	
	@Override
	public List<Object> getDatos(Class clase, String condicion) throws Exception {
		
		return plataformaDAO.getDatos(clase, condicion);
	}

	public PlataformaDAO getPlataformaDAO() {
		return plataformaDAO;
	}

	public void setPlataformaDAO(PlataformaDAO plataformaDAO) {
		this.plataformaDAO = plataformaDAO;
	}

	@Override
	public String getCondicionPadre(Object padre) {
		
		String condicion = plataformaDAO.getCondicionId(padre);
		
		return condicion;
	}

	@Override
	public String getEnv(String propiedad) {
		
		PltEnv env = plataformaDAO.getEnv(propiedad);
		
		return env != null ? env.getEnvValor() : null;
	}
	
	public void crearRol(PltRol pltRol) throws Exception {
		if(logger.isDebugEnabled()) logger.debug(new StringBuilder("Creando Rol = ").append(pltRol.getRolNombre()));{
//			Se veririfica si ya existe un login igual creado
			if(plataformaDAO.getRolPorNombre(pltRol.getRolNombre()) != null){
				throw new PltException(ConstantesAdmin.ERR0008);
			}			
			//Crear Rol			
			plataformaDAO.crearRol(pltRol);
		}
		
	}

	@Override
	public void modificarRol(PltRol pltRol) throws Exception {
		if(logger.isDebugEnabled()) logger.debug(new StringBuilder("Modificando Rol = ").append(pltRol.getRolNombre()));{
			//Modificar Rol
			plataformaDAO.modificarRol(pltRol);
		}
		
	}

	@Override
	public void eliminarRol(PltRol pltRol) throws Exception {
		if(logger.isDebugEnabled()) logger.debug(new StringBuilder("Eliminando Rol = ").append(pltRol.getRolNombre()));{
			//Eliminar Rol
			plataformaDAO.eliminarRol(pltRol);
		}
		
	}

	
	
}
