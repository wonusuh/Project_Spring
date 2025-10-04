<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@include
file="/WEB-INF/views/includes/header.jsp"%>

<div class="row justify-content-center">
  <div class="col-lg-12">
    <div class="card shadow mb-4">
      <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Board List</h6>
      </div>

      <div class="card-body">
        <table class="table table-bordered" id="dataTable">
          <thead>
            <tr>
              <th>Bno</th>
              <th>Title</th>
              <th>Writer</th>
              <th>RegDate</th>
            </tr>
          </thead>
          <tbody class="tbody">
            <c:forEach var="board" items="${dto.boardDTOList}">
              <tr data-bno="${board.bno}">
                <td>
                  <a href="/board/read/${board.bno}">
                    <c:out value="${board.bno}" />
                  </a>
                </td>
                <td><c:out value="${board.title}" /></td>
                <td><c:out value="${board.writer}" /></td>
                <td><c:out value="${board.createdDate}" /></td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
        <div class="d-flex justify-content-center">
          <ul class="pagination">
            <c:if test="${dto.prev}">
              <li class="page-item">
                <a class="page-link" href="${ dto.start - 1 }" tabindex="-1">
                  Previous
                </a>
              </li>
            </c:if>

            <c:forEach var="num" items="${dto.pageNums}">
              <li class="page-item ${dto.page == num ? 'active':'' }">
                <a class="page-link" href="${num}"> ${num} </a>
              </li>
            </c:forEach>

            <c:if test="${dto.next}">
              <li class="page-item">
                <a class="page-link" href="${ dto.end + 1 }">Next</a>
              </li>
            </c:if>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- 모달 -->
<div
  class="modal fade"
  id="myModal"
  tabindex="-1"
  aria-labelledby="exampleModalLabel"
  aria-hidden="true"
>
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button
          type="button"
          class="btn-close"
          data-bs-dismiss="modal"
          aria-label="Close"
        ></button>
      </div>
      <div class="modal-body">Modal body text goes here.</div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">Save changes</button>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
          Close
        </button>
      </div>
    </div>
  </div>
</div>
<!-- // 모달 -->

<script type="text/javascript" defer="defer">
  // 모달
  const result = "${result}";
  const myModal = new bootstrap.Modal(document.getElementById("myModal"));
  console.log(myModal);
  if (result) {
    myModal.show();
  }

  // 페이징
  const pagingDiv = document.querySelector(".pagination");
  pagingDiv.addEventListener("click", (e) => {
    e.preventDefault();
    e.stopPropagation();
    const target = e.target;
    // console.log(target);
    const targetPage = target.getAttribute("href");
    const mySize = "${dto.size}" || 10;
    const params = new URLSearchParams({
      page: targetPage,
      size: mySize,
    });
    self.location = `/board/list?\${params.toString()}`;
  });
</script>

<%@include file="/WEB-INF/views/includes/footer.jsp"%>
