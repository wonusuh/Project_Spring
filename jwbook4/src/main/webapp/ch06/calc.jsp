<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
int num1 = Integer.parseInt(request.getParameter("num1"));
int num2 = Integer.parseInt(request.getParameter("num2"));
String operator = request.getParameter("operator");

long result = 0;

switch (operator) {
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
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>계산기 JSP</title>
</head>
<body>
	<h2>계산 결과-JSP</h2>
	<hr />
	결과 :
	<%=result%>
</body>
</html>
