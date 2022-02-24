<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/tags.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>애플리케이션</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<c:set var="menu" value="book" />
<%@ include file="../common/nav.jsp" %>
<div class="container my-3">
    <div class="row mb-3">
        <div class="col">
            <h1>도서 목록</h1>
        </div>
    </div>
    <div class="row mb-3">
		<div class="col">
			<div class="d-flex justify-content-between">
				<p>도서 목록을 확인하세요</p>
				<form id="form-paging" method="get" action="/book/list">
					<input type="hidden" name="page" value='<c:out value="${param.page }" default="1"/>' />
					<select class="form-select form-control-sm" name="sort">
						<option value="DESC:id" ${empty param.sort or param.sort eq 'DESC:id' ? 'selected' : '' }> 신상품순 </option>
						<option value="ASC:title" ${param.sort eq 'ASC:title' ? 'selected' : '' }> 제목순 </option>
						<option value="ASC:price" ${param.sort eq 'ASC:price' ? 'selected' : '' }> 최저가순 </option>
						<option value="DESC:price" ${param.sort eq 'DESC:price' ? 'selected' : '' }> 최고가순 </option>
					</select>
				</form>
			</div>
			<table class="table">
				<thead>
					<tr>
						<th style="width: 10%;">순번</th>
						<th style="width: 40%;">제목</th>
						<th style="width: 20%;">저자</th>
						<th style="width: 15%;">출판사</th>
						<th style="width: 15%;">가격</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${books }">
						<tr>
							<td>${book.id }</td>
							<td><a href="detail?id=${book.id }">${book.title }</a></td>
							<td>${book.author }</td>
							<td>${book.publisher }</td>
							<td><fmt:formatNumber value="${book.price}"/> 원</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<c:if test="${not empty books }">
		<div class="row mb-3">
			<div class="col">
				<nav aria-label="Page navigation">
					<ul class="pagination justify-content-center">
						<li class="page-item ${pagination.firstBlock ? 'disabled' : '' }">
							<a class="page-link" href="/book/list?page=${pagination.previousBlockPage }" data-page="${pagination.previousBlockPage }">이전</a>
						</li>
	
						<c:forEach var="num" begin="${pagination.beginPage }" end="${pagination.endPage }">
							<li class="page-item ${pagination.pageNo eq num ? 'active' : '' }">
								<a class="page-link" href="/book/list?page=${num }" data-page="${num }">${num }</a>
							</li>
						</c:forEach>
	
						<li class="page-item ${pagination.lastBlock ? 'disabled' : '' }">
							<a class="page-link" href="/book/list?page=${pagination.nextBlockPage }" data-page="${pagination.nextBlockPage }">다음</a>
						</li>
					</ul>
				</nav>
			</div>
		</div>
	</c:if>
</div>
<script type="text/javascript">
$(function() {
	// 페이지번호를 클릭할 때 실행되는 요청핸들러 메소드를 등록한다.
	$('.page-link').click(function(event) {
		event.preventDefault();
		// 클릭한 <a> 링크의 data-page 속성을 조회한다.
		let pageNo = $(this).attr("data-page");
		// <form> 태그의 입력요소중에서 name이 page이 입력필드의 값을 조회한 페이지번호로 설정한다.
		$(":input[name=page]").val(pageNo);
		
		// 폼에서 submit 이벤트를 강제발생시켜서 폼의 값을 서버로 제출시킨다.
		$("#form-paging").trigger("submit");
	});
	
	// 정렬기준을 변경할 때 실행되는 요청핸들러 메소드를 등록한다.
	$('select[name=sort]').change(function() {
		$(":input[name=page]").val(1);
		$("#form-paging").trigger("submit");
	});
})
</script>
</body>
</html>