package com.luv2code.springdemo.dao;

import java.util.List;

import com.luv2code.springdemo.entity.CrmUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<CrmUser> getCrmUsers() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query  ... sort by last name
		Query<CrmUser> theQuery = 
				currentSession.createQuery("From CrmUser order by username ",
											CrmUser.class);
		
		// execute query and get result list
		List<CrmUser> crmUsers = theQuery.getResultList();
				
		// return the results		
		return crmUsers;
	}

	@Override
	public void saveCrmUser(CrmUser thecrmUser) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save/upate the CrmUser ... finally LOL
		currentSession.saveOrUpdate(thecrmUser);
		
	}

	@Override
	public CrmUser getCrmUser(String theUsername) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		CrmUser thecrmUser = currentSession.get(CrmUser.class, theUsername);
		
		return thecrmUser;
	}

	@Override
	public void deleteCrmUser(String theUsername) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query theQuery = 
				currentSession.createQuery("delete from CrmUser where username=:crmUserId");
		theQuery.setParameter("crmUserId", theUsername);
		
		theQuery.executeUpdate();		
	}

}











