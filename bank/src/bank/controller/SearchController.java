package bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.*;

import bank.controller.Controller;
import bank.service.BankService;

public class SearchController implements Controller{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		
		boolean result = BankService.getInstance().search(id);
		String msg = null;
		if(result == false)
			msg = "false";
		else
			msg = "true";
		
		request.setAttribute("msg",msg);
		HttpUtil.forward(request, response, "/transefer.jsp");
		
		
	}
	

}
