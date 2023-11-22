package com.demo.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.demo.entities.Account;
import com.demo.entities.Accountdetails;

public class AccountDetailsModel {
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	public Accountdetails findAccountByAccountID(int accountID) {
		Accountdetails account = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			account = (Accountdetails) session.createQuery("from Accountdetails where accountid = :accountid").setParameter("accountid", accountID).getSingleResult();
		
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			account = null;
		
			if(transaction != null) {
				transaction.rollback();
			}
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
		return account;
	}
}
