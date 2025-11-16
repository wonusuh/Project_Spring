<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>제품 상세 정보</title>
</head>
<body>
	<h2>제품 상세 정보</h2>
	<hr>
	<ul>
		<li>상품코드 : ${p.id}</li>
		<li>상품명 : ${p.name}</li>
		<li>제조사 : ${p.maker}</li>
		<li>가격 : ${p.price}</li>
		<li>등록일 : ${p.date}</li>
	</ul>
</body>
</html>
