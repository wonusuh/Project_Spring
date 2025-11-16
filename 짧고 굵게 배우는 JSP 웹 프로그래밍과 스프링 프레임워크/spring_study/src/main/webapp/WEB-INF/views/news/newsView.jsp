<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<h2>${news.title}</h2>
		<hr />
		<div class="card w-75 mx-auto">
		<img class="card-img-top"  src="${news.img}" />
			<div class="card-body">
				<h4 class="card-title">Date : ${news.date}</h4>
				<p class="card-text">Content : ${news.content}</p>
			</div>
		</div>
		<hr />
		<a href="javascript:history.back()" class="btn btn-primary"> << Back</a>
	</div>
</body>
</html>
