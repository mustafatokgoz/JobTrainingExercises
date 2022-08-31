package com.mustafa.sessions;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mustafa.executer.CommandExecuter;
import com.mustafa.trial.bags.XBag;
import com.mustafa.trial.entity.Commands;

public class BeforeRun {

	
	
	public void beforeRun() {
		 SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			addOperations(session);
			
			
			session.getTransaction().commit();
			session.close();
	}
	
	
	public void execute(String op,XBag bag) {
		 SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			CommandExecuter exec = new CommandExecuter();
			exec.execute(op, session, bag);
			
			session.getTransaction().commit();
			session.close();
	}
	
	private void addOperations(Session session) {
		
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
	}
	
}
