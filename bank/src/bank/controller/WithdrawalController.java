package bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.service.BankService;

public class WithdrawalController implements Controller{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int money = Integer.parseInt(request.getParameter("money"));
		
		String id = (String)request.getSession().getAttribute("id");
		
		int tmoney = BankService.getInstance().withdrawal(id,money);		
		
		if(tmoney<0)
			request.setAttribute("result", " Money is not enough");
		
		request.setAttribute("tmoney",tmoney);
		request.setAttribute("money",money);
	
		HttpUtil.forward(request, response, "/result/transferResult.jsp");
	} 

}
