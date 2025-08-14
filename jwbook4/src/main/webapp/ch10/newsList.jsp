<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<!-- 부트스트랩 자바스크립트 라이브러리 -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
<title>뉴스 관리 앱</title>
</head>
<body>
	<div class="container w-75 mt-5 mx-auto">
		<h2>뉴스 목록</h2>
		<hr>
		<ul class="list-group">
			<core:forEach var="news" items="${newslist}" varStatus="status">
				<li
					class="list-group-item list-group-item-action d-flex justify-content-none">
					<a href="news.nhn?action=getNews&aid=${news.aid}"
					class="text-decoration-none">[${status.count}] ${news.title},
						${news.date}</a> <a href="news.nhn?action=deleteNews&aid=${news.aid}"><span
						class="badge bg-secondary"> &times;</span></a>
				</li>
			</core:forEach>
		</ul>
		<hr>

		<!-- error 출력부 -->
		<core:if test="${error != null}">
			<div class="alert alert-danger alert-dismissible fade show mt-3">
				에러 발생 : ${error}
				<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
			</div>
		</core:if>

		<button class="btn btn-outline-info mb-3" type="button"
			data-bs-toggle="collapse" data-bs-target="#addForm"
			aria-expanded="false" aria-controls="addForm">뉴스 등록</button>

		<div class="collapse" id="addForm">
			<form method="post" action="/jwbook4/news.nhn?action=addNews"
				enctype="multipart/form-data">
				<label class="form-label">제목</label> <input type="text" name="title"
					class="form-control" /> <label class="form-label">이미지</label> <input
					type="file" name="file" class="form-control" /> <label
					class="form-label">기사내용</label>
				<textarea rows="5" cols="50" name="content" class="form-control"></textarea>
				<button type="submit" class="btn btn-success mt-3">저장</button>
			</form>
		</div>
	</div>
</body>
</html>