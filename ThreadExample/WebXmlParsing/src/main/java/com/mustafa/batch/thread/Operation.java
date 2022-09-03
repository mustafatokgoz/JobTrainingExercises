package com.mustafa.batch.thread;

import java.util.List;

import org.hibernate.Session;

import com.mustafa.sessions.BeforeRun;
import com.mustafa.trial.entity.Account;
import com.mustafa.trial.entity.BatchData;

public class Operation implements Runnable{
	private int startnumber;
	private int endnumber;
	
	private List<Object> accounts;
	
	private List<Object> batch_datas;
	
	private Session session;
	
	
	public Operation(int startnumber, int endnumber, List<Object> accounts, List<Object> batch_datas,Session session) {
		super();
		this.startnumber = startnumber;
		this.endnumber = endnumber;
		this.accounts = accounts;
		this.batch_datas = batch_datas;
		this.session = session;

	}


	@Override
	public void run() {
		
		for(int i = startnumber; i < endnumber; i++) {
			for(Object o : accounts) {
				Account acc = (Account) o;
				BatchData data = (BatchData) batch_datas.get(i);
				
				if(acc.getAccount_number().equals(String.valueOf(data.getAccountno()))) {
					if(data.getTransactiontype().equals("A")) {
						acc.setAmount(acc.getAmount() + data.getAmount());
						session.update(acc);
					}
					
					if(data.getTransactiontype().equals("B")) {
						acc.setAmount(acc.getAmount() - data.getAmount());
						session.update(acc);
					}
				}
			}
		}
		
	}
	
	
	
}
