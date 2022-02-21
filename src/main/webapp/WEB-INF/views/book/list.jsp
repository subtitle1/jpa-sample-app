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
			<p>도서 목록을 확인하세요</p>
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
					<c:forEach var="book" items="${books }" varStatus="loop">
						<tr>
							<td>${loop.count }</td>
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
</div>
</body>
</html>