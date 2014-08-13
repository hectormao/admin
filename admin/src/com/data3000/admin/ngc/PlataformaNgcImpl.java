package com.data3000.admin.ngc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.data3000.admin.bd.PltFormulario;
import com.data3000.admin.bd.PltMenu;
import com.data3000.admin.bd.PltRol;
import com.data3000.admin.bd.PltUsuario;
import com.data3000.admin.dao.PlataformaDAO;
import com.data3000.admin.vo.EstructuraMenu;
import com.data3000.admin.vo.Formulario;
import com.data3000.admin.vo.Usuario;

public class PlataformaNgcImpl implements PlataformaNgc {

	
	private PlataformaDAO plataformaDAO;
	
	@Override
	public Map<String, Formulario> getFuncionalidadesUsuario(Usuario usuario)  throws Exception {
		
		Map<String, Formulario> mapaFuncionalidades = new HashMap<String, Formulario>();
		
		List<Formulario> formularios = plataformaDAO.getFormulariosUsuario((PltUsuario) usuario); 
		
		for(Formulario formulario : formularios){
			mapaFuncionalidades.put(formulario.getNombre(), formulario);
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
	public void crearRol(PltRol pltRol) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarRol(PltRol pltRol) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarRol(PltRol pltRol) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	
}
