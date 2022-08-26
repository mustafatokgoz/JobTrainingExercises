package com.mustafa.trial.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.Session;

import com.mustafa.trial.bags.BagKey;
import com.mustafa.trial.bags.XBag;

@Entity
public class Account extends BaseOperations{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String account_number;
	
	private int amount;

	@ManyToOne
	@JoinColumn(name = "cust_id", referencedColumnName = "id")
	private Customer cust_id;
	
	public Account() {
		
	}

	public Account(String account_number, int amount, Customer cust_id) {
		super();
		this.account_number = account_number;
		this.amount = amount;
		this.cust_id = cust_id;
	}

	public Customer getCust() {
		return cust_id;
	}

	public void setCust(Customer cust) {
		this.cust_id = cust;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Customer getCust_id() {
		return cust_id;
	}


	public void setCust_id(Customer cust_id) {
		this.cust_id = cust_id;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", account_number=" + account_number + ", amount=" + amount + ", cust_id="
				+ cust_id + "]";
	}
	
	public void insert(Session session,XBag bag) {
		account_number = (String) bag.getValue(BagKey.ACCOUNT_NUMBER);
		amount = (Integer) bag.getValue(BagKey.ACCOUNT_AMOUNT);
		cust_id = getCustomer(session,bag);
		session.save(this);
	}
	
	public void update(Session session,XBag bag) {
		Account updated1 = this.get(session,bag);
		updated1.setAccount_number((String) bag.getValue(BagKey.ACCOUNT_NUMBER));
		updated1.setAmount((Integer) bag.getValue(BagKey.ACCOUNT_AMOUNT));
		updated1.setCust(getCustomer(session,bag));
		session.update(updated1);
	}
	
	public void delete(Session session,XBag bag) {
		Account deleted = this.get(session,bag);
		session.delete(deleted);
	}
	
	public Customer getCustomer(Session session, XBag bag) {
		if(bag.getValue(BagKey.CUSTOMER_ID) != null) {
			return (Customer) session.load(Customer.class, (Serializable) bag.getValue(BagKey.CUSTOMER_ID));
		}
		return null;
	}
	
	public Account get(Session session, XBag bag) {
		if(bag.getValue(BagKey.ACCOUNT_ID)!= null) {
			return (Account) session.load(Phone.class, (Serializable) bag.getValue(BagKey.ACCOUNT_ID));
		}
		return null;
	}
	
	
	
	public void list(Session session) {
		 List<?> accounts = session.createQuery("from Account").list();
	        for(Object acc: accounts) {
	        	Account acc1 = (Account) acc;
	        	System.out.println(acc1.toString());
	        			
	        }
	}
	

}
