<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String result = (String)request.getAttribute("result");
		if(result == null)
		{
	%>
			${id}->${rid} : ${money }->/${tmoney }
	<% 
		}else{
			out.print(result);
		}
	%>
	<p>
	<%@ include file = "homeMenu.jsp"%>
</body>
</html>