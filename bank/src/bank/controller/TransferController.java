package bank.controller;

import bank.controller.Controller;
import java.io.IOException;
import java.security.Provider.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.controller.HttpUtil;
import bank.service.BankService;



public class TransferController implements Controller{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String rid = request.getParameter("rid");
		int money = Integer.parseInt(request.getParameter("money"));
		
		String id = (String)request.getSession().getAttribute("id");
		
		int tmoney = BankService.getInstance().transfer(id,rid,money);		
		
		if(tmoney<0)
			request.setAttribute("result", "Not enough Money");
		
		request.setAttribute("tmoney",tmoney);
		request.setAttribute("money",money);
		request.setAttribute("rid",rid);
		
		HttpUtil.forward(request, response, "/result/transferResult.jsp");
	} 
	

}
