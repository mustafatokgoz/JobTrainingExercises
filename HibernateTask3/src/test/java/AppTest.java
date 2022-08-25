
 
import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mustafa.trial.bags.BagKey;
import com.mustafa.trial.bags.XBag;
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
		
		Commands command17 = new Commands("customer->get","com.mustafa.trial.entity.Customer","get");
		Commands command18 = new Commands("account->get","com.mustafa.trial.entity.Customer","get");
		Commands command19 = new Commands("phone->get","com.mustafa.trial.entity.Customer","get");
		Commands command20 = new Commands("address->get","com.mustafa.trial.entity.Customer","get");
		
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
		
		session.save(command17);
		session.save(command18);
		session.save(command19);
		session.save(command20);
		
		
		XBag bag = new XBag();
		bag.getMap().put(BagKey.CUSTOMER_NAME, "Mustafa");
		bag.getMap().put(BagKey.CUSTOMER_SURNAME, "TOKGOZ");
		bag.getMap().put(BagKey.CUSTOMER_EMAIL, "mustafa@tokgoz.com");
		
		CommandExecuter exec = new CommandExecuter();
		
		
		exec.execute("customer->insert", session,bag);
		
		bag.getMap().put(BagKey.CUSTOMER_ID, 1L);
		
		//exec.execute("customer->delete", session, bag);
		
		bag.getMap().put(BagKey.CUSTOMER_SURNAME, "TOKZ");
		
		exec.execute("customer->update", session, bag);
		
		exec.execute("customer->list", session, null);
		
		XBag bag2 = new XBag();
		
		bag2.getMap().put(BagKey.CUSTOMER_ID, 1L);
		Customer obj = (Customer) exec.execute("customer->get", session, bag2);
		
		System.out.println(obj);
		
		bag2.getMap().put(BagKey.PHONE_NUMBER, "Mustafa");
		
		
		exec.execute("phone->insert", session, bag2);
		
		bag2.getMap().put(BagKey.PHONE_NUMBER, "0xxxxxxxx");
		bag2.getMap().put(BagKey.PHONE_ID, 1L);
		
		
		exec.execute("phone->update", session, bag2);
		
	
		bag2.getMap().put(BagKey.ADDRESS_ADDRESS,"Orhanagazi");
		
		exec.execute("address->insert", session, bag2);
		
		bag2.getMap().put(BagKey.ACCOUNT_NUMBER,"44444");
		bag2.getMap().put(BagKey.ACCOUNT_AMOUNT,1000);
		
		exec.execute("account->insert", session, bag2);
		
		
		
		//exec.execute("phone->delete", session, bag2);
		
		
		/*
		
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
		*/
 
		
        
		session.getTransaction().commit();
		session.close();
	}
	
	
	
	
}