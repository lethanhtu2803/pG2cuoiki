package com.demo.models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

import com.demo.entities.Account;
import com.demo.entities.Accountdetails;

public class AccountModel {
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	public List<Account> findAll(){
		List<Account> accounts = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			accounts = session.createQuery("from Account").getResultList();
		
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			accounts = null;
		
			if(transaction != null) {
				transaction.rollback();
			}
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
		return accounts;
	}
	
	public Account findAccountByUsername(String username) {
		Account account = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			account = (Account) session.createQuery("from Account where username = :username")
					.setParameter("username", username)
					.getSingleResult();
		
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
	public static void main(String[] args) {
		System.out.println(BCrypt.checkpw("123", "$2a$10$EGz5UXiizIxOG0fvJz2mM.0u4rcfA6KVBZx897NkzfNrRB/p/fRLa"));
	}
	
	public boolean login(String username, String password) {
		Account account = findAccountByUsername(username);
		if(account != null) {
			if(account.isStatus() && account.isVerify()) {
				return BCrypt.checkpw(password, account.getPassword());
			}
			
		}
		return false;
	} 

	
	
}