<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/views/includes/header.jsp"%>

<div class="row justify-content-center">
	<div class="col-lg-12">
		<div class="card shadow mb-4">
			<div class="card-header py-3">
				<h6 class="m-0 font-weight-bold text-primary">Board Register</h6>
			</div>
			<div class="card-body">
				<!-- class="p-3" -->
				<form action="/board/register" method="post">

					<!-- Submit Button -->
					<div class="d-flex justify-content-end">
						<button class="btn btn-primary btn-lg" type="submit">Submit</button>
					</div>
					<!-- // Submit Button -->
				</form>
			</div>
		</div>
	</div>
</div>

<%@include file="/WEB-INF/views/includes/footer.jsp"%>
