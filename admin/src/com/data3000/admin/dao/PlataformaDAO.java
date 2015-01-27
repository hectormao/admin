package com.data3000.admin.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.metadata.ClassMetadata;

import com.data3000.admin.bd.PltEnv;
import com.data3000.admin.bd.PltFormulario;
import com.data3000.admin.bd.PltMenu;
import com.data3000.admin.bd.PltRelaForm;
import com.data3000.admin.bd.PltRol;
import com.data3000.admin.bd.PltUsuario;
import com.data3000.admin.vo.Formulario;

public class PlataformaDAO extends PltDAO {
	
	/**
	 * Log (log4j).
	 */
	private Logger logger = Logger.getLogger(this.getClass());
	
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
			
			throw ex;
		}finally{
			if(sesion.isOpen()){
				sesion.close();
			}
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
			
			throw ex;
		}finally{
			if(sesion.isOpen()){
				sesion.close();
			}
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
			if(StringUtils.isNotBlank(condicion)){
				hql.append(" where ");
				hql.append(condicion);
			}
			
			Query query = sesion.createQuery(hql.toString());
			List<Object> resultado = query.list();
			return resultado;			
		} catch(Exception ex){
			
			throw ex;
		}finally{
			if(sesion.isOpen()){
				sesion.close();
			}
		}
		
	}


	public String getCondicionId(Object padre) {
		
		ClassMetadata meta = sessionFactory.getClassMetadata(padre.getClass());
		if(meta == null){
			meta = sessionFactory.getClassMetadata(padre.getClass().getGenericSuperclass().getClass());
		}
		
		StringBuilder condicion = new StringBuilder(meta.getIdentifierPropertyName());
		condicion.append(" = ");
		condicion.append(meta.getIdentifier(padre,(SessionImplementor)sessionFactory.getCurrentSession()));
		
		return condicion.toString();
	}


	public PltEnv getEnv(String propiedad) {
		Session sesion = sessionFactory.getCurrentSession();
		Transaction tx = sesion.getTransaction();
		
		try{
			
			if(! tx.isActive()){
				tx.begin();
			}
		
			Criteria criteria = sesion.createCriteria(PltEnv.class);
			criteria.add(Restrictions.eq("envPropiedad", propiedad));
			return (PltEnv) criteria.uniqueResult();			
		} catch(Exception ex){
			
			return null;
		}finally{
			if(sesion.isOpen()){
				sesion.close();
			}
		}
	}
	
	/**
	 * Metodo para crear un rol
	 * @param pltRol
	 * @throws Exception
	 */
	public void crearRol(PltRol pltRol) throws Exception {
		if(logger.isDebugEnabled()) logger.debug(new StringBuilder("Creando Rol = ").append(pltRol.getRolNombre()));
		super.save(pltRol);
	}
	
	/**
	 * Metodo para modificar un rol
	 * @param pltRol
	 * @throws Exception
	 */
	public void modificarRol(PltRol pltRol) throws Exception {
		if(logger.isDebugEnabled()) logger.debug(new StringBuilder("Modificando Rol = ").append(pltRol.getRolNombre()));
		super.update(pltRol);
	}
	
	
	/**
	 * Metodo para eliminar un rol
	 * @param pltRol
	 * @throws Exception
	 */
	public void eliminarRol(PltRol pltRol)throws Exception{ if(logger.isDebugEnabled()) logger.debug(new StringBuilder("Eliminando Rol = ").append(pltRol.getRolNombre()));
		super.delete(pltRol);
	}


	public List<PltRelaForm> getHijos(PltFormulario formulario) {
		Session sesion = sessionFactory.getCurrentSession();
		Transaction tx = sesion.getTransaction();
		
		try{
			
			if(! tx.isActive()){
				tx.begin();
			}
		
			Criteria criteria = sesion.createCriteria(PltRelaForm.class);
			criteria.add(Restrictions.eq("pltFormularioByFormPadre", formulario));
			criteria.addOrder(Order.asc("relaFormOrden"));
			return criteria.list();			
		} catch(Exception ex){
			
			return null;
		}finally{
			if(sesion.isOpen()){
				sesion.close();
			}
		}
	}
	
	/**
	 * Retorna rol por nombre
	 * @param nombreRol
	 * @return
	 * @throws Exception
	 */
	public PltRol getRolPorNombre(String nombreRol) throws Exception{	
		Session sesion = sessionFactory.getCurrentSession();		
		Transaction tx = sesion.getTransaction();
		try{
			if(! tx.isActive()){
				tx.begin();
			}			
			Criteria criteria = sesion.createCriteria(PltRol.class);
			criteria.add(Restrictions.eq("rolNombre", nombreRol));
			return (PltRol) criteria.uniqueResult();
		} catch(Exception ex){
			
			throw ex;
		}finally{
			if(sesion.isOpen()){
				sesion.close();
			}
		}
		
	}
	
	/**
	 * Retorna listado de formularios creados en el sistema
	 * @param usuario
	 * @return
	 * @throws Exception
	 */
	public List<PltFormulario> getFormularios() throws Exception{
		Session sesion = sessionFactory.getCurrentSession();
		Transaction tx = sesion.getTransaction();
		try{
			
			if(! tx.isActive()){
				tx.begin();
			}
			Criteria criteria = sesion.createCriteria(PltFormulario.class);
			criteria.addOrder(Order.asc("formModulo"));
			
			return criteria.list();
		} catch(Exception ex){
			
			throw ex;
		}finally{
			if(sesion.isOpen()){
				sesion.close();
			}
		}
	}


	public List<PltRol> getRolesOrdenadoNombre() {
		Session sesion = sessionFactory.getCurrentSession();
		Transaction tx = sesion.getTransaction();
		try{
			
			if(! tx.isActive()){
				tx.begin();
			}
			Criteria criteria = sesion.createCriteria(PltRol.class);			
			criteria.addOrder(Order.asc("rolNombre"));
			return criteria.list();
		} catch(Exception ex){
			
			throw ex;
		}finally{
			if(sesion.isOpen()){
				sesion.close();
			}
		}
	}
	
}
