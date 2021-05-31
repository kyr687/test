package bank.controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.service.BankService;
import bank.controller.HttpUtil;
import bank.vo.Account;

public class JoinController implements Controller {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		if(id.isEmpty() || pwd.isEmpty()) {
			request.setAttribute("error","모든 항목을 빠짐없이 제출해주세요.");
			HttpUtil.forward(request, response, "/join.jsp");
			return;
		}		
		Account account = new Account(id,pwd);
		
		BankService service = BankService.getInstance();
		service.join(account);
		
		HttpUtil.forward(request, response, "/index.jsp");
		
	}
}
