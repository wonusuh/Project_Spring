<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
int num1 = Integer.parseInt(request.getParameter("n1"));
int num2 = Integer.parseInt(request.getParameter("n2"));

long result = 0;

switch (request.getParameter("op")) {
case "+":
	result = num1 + num2;
	break;
case "-":
	result = num1 - num2;
	break;
case "*":
	result = num1 * num2;
	break;
case "/":
	result = num1 / num2;
	break;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>계산 결과</h2>
	<hr>
	결과 :
	<%=result%>
</body>
</html>
