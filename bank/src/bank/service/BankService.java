package bank.service;

import bank.*;
import bank.dao.BankDAO;
import bank.vo.Account;

public class BankService {
	private static BankService service = new BankService();
	public BankDAO dao = BankDAO.getInstance();
	private BankService() {}

	public static BankService getInstance() 
	{
		return service;
	}

	public void join(Account account) 
	{
		dao.join(account);		
	}
	public boolean login(String id, String pwd) {
		// TODO Auto-generated method stub
		return dao.login(id, pwd);
	}

	public int transfer(String id, String rid, int money) {
		// TODO Auto-generated method stub
		return dao.transfer(id,rid,money);
	}
	
	public int deposit(String id, int money) {
		// TODO Auto-generated method stub
		return dao.deposit(id,money);
	}
	public int withdrawal(String id, int money) {
		// TODO Auto-generated method stub
		return dao.withdrawal(id,money);
	}
	public int query(String id) {
		// TODO Auto-generated method stub
		return dao.query(id);
	}

	public boolean search(String id) {
		// TODO Auto-generated method stub
		return dao.search(id);
	}

}
