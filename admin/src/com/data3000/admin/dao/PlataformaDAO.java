package com.data3000.admin.dao;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.zkoss.zhtml.Tbody;

import com.data3000.admin.bd.PltFormulario;
import com.data3000.admin.bd.PltMenu;
import com.data3000.admin.bd.PltUsuario;
import com.data3000.admin.vo.Formulario;

public class PlataformaDAO extends PltDAO {
	
	/**
	 * Retorna un listado de funcionalidades a las que tiene permisos un usuario
	 * @param usuario
	 * @return
	 * @throws Exception
	 */
	public List<Formulario> getFormulariosUsuario(PltUsuario usuario) throws Exception{
		Session sesion = sessionFactory.getCurrentSession();
		Transaction tx = sesion.getTransaction();
		try{
			
			if(! tx.isActive()){
				tx.begin();
			}
			
			String hql = "select distinct perm.pltFormulario from PltPermiso perm where perm.pltRol in (select distinct usuarol.pltRol from PltUsuaRol usuarol where usuarol.pltUsuario = :usuario )";
			
			Query query = sesion.createQuery(hql);
			query.setEntity("usuario", usuario);
			
			return query.list();
		} catch(Exception ex){
			sesion.close();
			throw ex;
		}
	}
	
	
	public List<PltMenu> getMenu() throws Exception{
		Session sesion = sessionFactory.getCurrentSession();
		Transaction tx = sesion.getTransaction();
		try{
			
			if(! tx.isActive()){
				tx.begin();
			}		
			
			Criteria criterio = sesion.createCriteria(PltMenu.class);
			criterio.addOrder(Order.asc("pltMenu.menuIdn"));
			criterio.addOrder(Order.asc("menuOrden"));
			
			return criterio.list();
		} catch(Exception ex){
			sesion.close();
			throw ex;
		}
	}
	
	public List<Object> getDatos(Class clase, String condicion) throws Exception{
		
		Session sesion = sessionFactory.getCurrentSession();
		Transaction tx = sesion.getTransaction();
		
		try{
			
			if(! tx.isActive()){
				tx.begin();
			}
		
			StringBuilder hql = new StringBuilder("from ");
			hql.append(clase.getName());
			if(condicion != null && condicion.trim().length() > 0){
				hql.append(" where ");
				hql.append(condicion);
			}
			
			Query query = sesion.createQuery(hql.toString());
			List<Object> resultado = query.list();
			return resultado;			
		} catch(Exception ex){
			sesion.close();
			throw ex;
		}
		
	}
	
}
