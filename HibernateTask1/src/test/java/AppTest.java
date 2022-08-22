
 
import junit.framework.TestCase;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mustafa.trial.entity.Account;
import com.mustafa.trial.entity.Address;
import com.mustafa.trial.entity.Customer;
import com.mustafa.trial.entity.Phone;
 

public class AppTest extends TestCase {
 
	@SuppressWarnings("deprecation")
	public void testApp() {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
 
		//add
		Customer cost = new Customer("mustafa1","tokgoz","ddd@gg.com");
		session.save(cost);
		Customer cost2 = new Customer("mustafa2","tokgoz","ddd@gg.com");
		session.save(cost2);
		Customer cost3 = new Customer("mustafa3","tokgoz","ddd@gg.com");
		session.save(cost3);
		Address addr = new Address("osmangazi",cost);
		session.save(addr);
		
		
		// update customer
		Customer updated1 = (Customer) session.load(Customer.class, cost.getId());
		updated1.setName("updated");
		updated1.setSurname("updated");
		session.update(updated1);
		
		//update Adress
		Address address_update = (Address) session.load(Address.class, addr.getId());
		address_update.setAddress("çamlıca");
		address_update.setCust(cost2);
		session.update(address_update);
		
		
		//delete
		//session.delete(address_update);
        session.delete(cost3);
        System.out.println("Customer 2 is removed");
        
        
        //list customers
        List<?> customers = session.createQuery("from Customer").list();
        for(Object acustumer: customers) {
        	Customer acust = (Customer) acustumer;
        	System.out.println(String.format("Customer Name: %s , Customer Surname: %s, Customer Email : %s",acust.getName(),acust.getSurname(),
        			acust.getEmail()));
        			
        }
        
        Phone obj_phone= new Phone("00002221111",cost2);
        session.save(obj_phone);
        
        
        Account obj_account = new Account("33223",10000,cost);
        session.save(obj_account);
        
        //list accounts
        List<?> accounts = session.createQuery("from Account").list();
        for(Object acct: accounts) {
        	Account acct2 = (Account) acct;
        	System.out.println(String.format("Account Number: %s, Account Amount: %d, %s",acct2.getAccount_number(),acct2.getAmount(),
        			acct2.getCust()));
        			
        }
		session.getTransaction().commit();
		session.close();
	}
	
	
	
	
}