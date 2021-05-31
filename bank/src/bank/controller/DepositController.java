package bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.service.BankService;

public class DepositController implements Controller{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int money = Integer.parseInt(request.getParameter("money"));
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		BankService s = BankService.getInstance();
		int totalmoney = s.deposit(id,money);
		
		request.setAttribute("money", money);
		request.setAttribute("tmoney", totalmoney);
		
		HttpUtil.forward(request, response, "/result/depositResult.jsp");
		
	}
}
