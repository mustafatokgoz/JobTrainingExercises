package com.mustafa.trial.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
public class BatchData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int sirano;
	
	private int status;
	
	private int accountno;
	
	private int amount;
	
	private String transactiontype;

	public BatchData() {
		
	}
	
	
	public BatchData(int sirano, int status, int accountno, int amount, String transactiontype) {
		super();
		this.sirano = sirano;
		this.status = status;
		this.accountno = accountno;
		this.amount = amount;
		this.transactiontype = transactiontype;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getSirano() {
		return sirano;
	}


	public void setSirano(int sirano) {
		this.sirano = sirano;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public int getAccountno() {
		return accountno;
	}


	public void setAccountno(int accountno) {
		this.accountno = accountno;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public String getTransactiontype() {
		return transactiontype;
	}


	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}
	
	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		for(int i = 0;i < 100; i++) {
			
			if(i%2 == 0) {
				BatchData temp = new BatchData(i,i%2,i+10,(i+1)*20,"A");
				session.save(temp);
			}
			else {
				BatchData temp = new BatchData(i,i%2,i+10,(i+1)*20,"B");
				session.save(temp);
			}
			
		}
		session.getTransaction().commit();
		session.close();
	}
	
}
