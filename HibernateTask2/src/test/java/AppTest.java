
 
import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mustafa.trial.entity.Account;
import com.mustafa.trial.entity.Address;
import com.mustafa.trial.entity.Commands;
import com.mustafa.trial.entity.Customer;
import com.mustafa.trial.entity.Phone;
 

public class AppTest extends TestCase {
 
	@SuppressWarnings("deprecation")
	public void testApp() {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Commands command1 = new Commands("customer->insert","com.mustafa.trial.entity.Customer","insert");
		Commands command2 = new Commands("customer->delete","com.mustafa.trial.entity.Customer","delete");
		Commands command3 = new Commands("customer->update","com.mustafa.trial.entity.Customer","update");
		Commands command4 = new Commands("customer->list","com.mustafa.trial.entity.Customer","list");
		
		Commands command5 = new Commands("address->insert","com.mustafa.trial.entity.Address","insert");
		Commands command6 = new Commands("address->delete","com.mustafa.trial.entity.Address","delete");
		Commands command7 = new Commands("address->update","com.mustafa.trial.entity.Address","update");
		Commands command8 = new Commands("address->list","com.mustafa.trial.entity.Address","list");
		
		
		Commands command9 = new Commands("phone->insert","com.mustafa.trial.entity.Phone","insert");
		Commands command10 = new Commands("phone->delete","com.mustafa.trial.entity.Phone","delete");
		Commands command11 = new Commands("phone->update","com.mustafa.trial.entity.Phone","update");
		Commands command12 = new Commands("phone->list","com.mustafa.trial.entity.Phone","list");
		
		Commands command13 = new Commands("account->insert","com.mustafa.trial.entity.Account","insert");
		Commands command14 = new Commands("account->delete","com.mustafa.trial.entity.Account","delete");
		Commands command15 = new Commands("account->update","com.mustafa.trial.entity.Account","update");
		Commands command16 = new Commands("account->list","com.mustafa.trial.entity.Account","list");
		
		
		
		session.save(command1);
		session.save(command2);
		session.save(command3);
		session.save(command4);
		
		session.save(command5);
		session.save(command6);
		session.save(command7);
		session.save(command8);
		
		session.save(command9);
		session.save(command10);
		session.save(command11);
		session.save(command12);
		
		session.save(command13);
		session.save(command14);
		session.save(command15);
		session.save(command16);
		
		
		Customer cost = new Customer("mustafa11111","tokgoz","ddd@gg.com");
		Customer cost2 = new Customer("mustafa2222","tokgoz22","ddd@2222gg.com");
		Customer cost3 = new Customer("mustafa3333","tokgoz33","ddd33@gg.com");
		
		
		CommandExecuter exec = new CommandExecuter();
		
		exec.execute("customer->insert", session, cost);
		exec.execute("customer->insert", session, cost2);
		exec.execute("customer->insert", session, cost3);
		
		
		Customer updated1 = (Customer) session.load(Customer.class, cost.getId());
		updated1.setName("updated");
		updated1.setSurname("updated");
		
		exec.execute("customer->update", session, updated1);
		
		
		exec.execute("customer->delete", session, cost3);
		
		exec.execute("customer->list", session, updated1);
		
		Address addr = new Address("osmangazii",cost);
		exec.execute("address->insert", session, addr);
		
		Address address_update = (Address) session.load(Address.class, addr.getId());
		address_update.setAddress("çamlıca");
		address_update.setCust(cost2);
		
		exec.execute("address->update", session, address_update);
		
		
		Phone obj_phone= new Phone("00002221111",cost2);
        exec.execute("phone->insert", session, obj_phone);
        
        
        Account obj_account = new Account("33223",10000,cost);
        
        exec.execute("account->insert", session, obj_account);
        
        exec.execute("account->list", session, obj_account);
		
 
		/*
		
		
		//add
		Customer cost = new Customer("mustafa11","tokgoz","ddd@gg.com");
		session.save(cost);
		Customer cost2 = new Customer("mustafa22","tokgoz","ddd@gg.com");
		cost2.insert(session);
		//session.save(cost2);
		Customer cost3 = new Customer("mustafa33","tokgoz","ddd@gg.com");
		session.save(cost3);
		Address addr = new Address("osmangazii",cost);
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
        
        */
        
		session.getTransaction().commit();
		session.close();
	}
	
	
	
	
}