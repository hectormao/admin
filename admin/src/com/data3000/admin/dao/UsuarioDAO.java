package com.data3000.admin.dao;


import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.criterion.Restrictions;

import com.data3000.admin.bd.PltUsuario;

public class UsuarioDAO extends PltDAO {
	
	/**
	 * Log (log4j).
	 */
	private Logger logger = Logger.getLogger(this.getClass());
	
	public PltUsuario getUsuarioPorLogin(String login) throws Exception{		
		Session sesion = sessionFactory.getCurrentSession();		
		Transaction tx = sesion.getTransaction();
		try{
			if(! tx.isActive()){
				tx.begin();
			}			
			Criteria criterio = sesion.createCriteria(PltUsuario.class);
			criterio.add(Restrictions.eq("usuaLogin", login));
			return (PltUsuario) criterio.uniqueResult();
		} catch(Exception ex){
			sesion.close();
			throw ex;
		}
	}
	
	
	/**
	 * Método para crear un nuevo usuario
	 * @param pltUsuario
	 * @throws Exception
	 */
	public void crearUsuario(PltUsuario pltUsuario) throws Exception {
		if(logger.isDebugEnabled()) logger.debug(new StringBuilder("Creando Usuario = ").append(pltUsuario.getUsuaLogin()));{
			super.save(pltUsuario);
		}
	}
	
	/**
	 * Método para modificar un usuario
	 * @param pltUsuario
	 * @throws Exception
	 */
	public void modificarUsuario(PltUsuario pltUsuario)throws Exception{
		if(logger.isDebugEnabled()) logger.debug(new StringBuilder("Modificando Usuario = ").append(pltUsuario.getUsuaLogin()));{
			super.update(pltUsuario);
		}
		
	}
	
	/**
	 * Metodo para eliminar un usuario
	 * @param pltUsuario
	 * @throws Exception
	 */
	public void EliminarUsuario(PltUsuario pltUsuario)throws Exception{
		if(logger.isDebugEnabled()) logger.debug(new StringBuilder("Modificando Usuario = ").append(pltUsuario.getUsuaLogin()));{
			super.delete(pltUsuario);
		}
		
	}
	
}
