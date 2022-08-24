package com.mustafa.trial.entity;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.Session;

@Entity
public class Address extends BaseOperations {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	private String address;
	
	
	
	@OneToOne
	@JoinColumn(name = "cust_id", referencedColumnName = "id")
	private Customer cust_id;
	
	public Address() {

	}

	public Address(String address,Customer cust) {
		
		this.address = address;
		this.cust_id = cust;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	@Override
	public String toString() {
		return "Address [id=" + id + ", address=" + address + ", cust_id=" + cust_id + "]";
	}

	public void list(Session session) {
		 List<?> addresses = session.createQuery("from Address").list();
	        for(Object aaddress: addresses) {
	        	Address aaddr = (Address) aaddress;
	        	System.out.println(aaddr.toString());
	        			
	        }
	}

}
