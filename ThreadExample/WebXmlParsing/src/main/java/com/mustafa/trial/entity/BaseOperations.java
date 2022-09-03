package com.mustafa.trial.entity;

import org.hibernate.Session;

public abstract class BaseOperations {
	public void insert(Session session) {
		session.save(this);
	}
	
	public void update(Session session) {
		session.update(this);
	}
	
	public void delete(Session session) {
		session.delete(this);
	}
	
	public abstract void list(Session session);
}
