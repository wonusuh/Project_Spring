<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="/WEB-INF/views/includes/header.jsp"%>

<div class="row justify-content-center">
  <div class="col-lg-12">
    <div class="card shadow mb-4">
      <div class="card-header py-3">
        <h6 class="m-0 fw-bold text-primary">Board Modify</h6>
      </div>
      <div class="card-body">
        <form id="actionForm" action="/board/modify" method="post"></form>
        <div class="float-end">
          <button type="button" class="btn btn-info btnList">LIST</button>
          <button type="button" class="btn btn-warning btnModify">
            MODIFY
          </button>
          <button type="button" class="btn btn-danger btnRemove">REMOVE</button>
        </div>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
  const formObj = document.querySelector("#actionForm");

  /**
   * "Modify" 버튼 클릭 이벤트
   */
  document.querySelector(".btnModify").addEventListener(
    "click",
    () => {
      formObj.action = "/board/modify";
      formObj.method = "post";
      formObj.submit();
    },
    false
  );

  /**
   * "LIST" 버튼 클릭 이벤트
   */
  document.querySelector(".btnList").addEventListener(
    "click",
    () => {
      formObj.action = "/board/list";
      formObj.method = "get";
      formObj.submit();
    },
    false
  );
</script>

<%@ include file="/WEB-INF/views/includes/footer.jsp"%>
