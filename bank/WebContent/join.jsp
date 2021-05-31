<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${error}
	
	<form action="join.do" method="post">
		ID : <input type="text" name="id"/><br>
		PASSWORD : <input type="password" name="pwd"/><br>
		<input type="submit" value="join"/>
	</form>
	<a href="/bank/index.jsp">HOME</a>

</body>
</html>