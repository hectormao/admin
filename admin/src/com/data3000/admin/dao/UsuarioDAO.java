package com.data3000.admin.dao;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.criterion.Restrictions;

import com.data3000.admin.bd.PltUsuario;

public class UsuarioDAO extends PltDAO {
	
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
	
}
