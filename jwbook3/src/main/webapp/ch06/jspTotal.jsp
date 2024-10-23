<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Total Practice</title>
</head>
<body>
	<h2>JSP 종합 예제</h2>
	<hr>
	<%!String[] members = { "김길동", "홍길동", "김사랑", "박사랑" };
	int num1 = 10;

	protected int calc(int num2) {
		return num1 + num2;
	}%>
	<h3>
		1. JSP 주석
		<!-- HTML 주석 : 화면에는 보이지 않지만 소스보기에서는 보입니다. -->
		<%-- JSP 주석 : 화면에서도 소스보기에서도 보이지 않는 주석입니다. --%>
	</h3>
	<h3>
		2. calc(10) 메서드 실행 결과 :
		<%=calc(10)%>
	</h3>
	<hr>
	<h3>3. include : hello.jsp</h3>
	<%@ include file="../hello.jsp"%>
	<hr>
	<h3>4. 스크립트 (배열 데이터 출력)</h3>
	<ul>
		<%
		for (String name : members) {
		%>
		<li><%=name%></li>
		<%
		}
		%>
	</ul>
</body>
</html>
