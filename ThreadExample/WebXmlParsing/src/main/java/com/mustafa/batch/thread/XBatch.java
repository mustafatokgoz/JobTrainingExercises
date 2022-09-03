package com.mustafa.batch.thread;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mustafa.sessions.BeforeRun;
import com.mustafa.trial.bags.XBag;
import com.mustafa.trial.entity.Account;
import com.mustafa.trial.entity.BatchData;
import com.mustafa.trial.entity.Customer;

public class XBatch {

	
	public static void execute(int threadcount, int commitcount) {
		BeforeRun b = new BeforeRun();
		
		
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		
	 	List<Object> batchdata = session.createQuery("from BatchData").list();
	 	
	 	/*
	 	Customer cost = new Customer("mm","ss","aaa");
	 	session.save(cost);
	 	
        Account obj_account = new Account("10",1000,cost);
        Account obj_account2 = new Account("11",100,cost);
        Account obj_account3 = new Account("12",10000,cost);
        Account obj_account4 = new Account("13",100,cost);
        Account obj_account5 = new Account("14",2000,cost);
        
        session.save(obj_account);
        session.save(obj_account2);
        session.save(obj_account3);
        session.save(obj_account4);
        session.save(obj_account5);
        
        session.getTransaction().commit();
        
        */
        
        List<Object> alist = session.createQuery("from Account").list();
        
		int k = 0;
		ExecutorService executor = Executors.newFixedThreadPool(threadcount);
        for (int i = 0; i < threadcount; i++) {
        	
            Runnable worker = new Operation(k,k+2,alist,batchdata,session);
            executor.execute(worker);
            k = 2;
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
        
        session.getTransaction().commit();
		session.close();
	}
	
	public static void main(String[] args) {
		execute(2,2);
	}
}
