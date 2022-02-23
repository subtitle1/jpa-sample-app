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
            <h1>도서 상세 정보</h1>
        </div>
    </div>
    <div class="row mb-3">
		<div class="col-10">
			<p>도서 정보를 확인하세요</p>
			<table class="table">
				<colgroup>
					<col width="15%">
					<col width="35%">
					<col width="15%">
					<col width="35%">
				</colgroup>
				<tbody>
					<tr>
						<th>제목</th>
						<td colspan="3">${book.title }</td>
					</tr>
					<tr>
						<th>등록일</th>
						<td><fmt:formatDate value="${book.createdDate }" pattern="yyyy년 M월 d일"/> </td>
						<th>최종수정일</th>
						<td><fmt:formatDate value="${book.lastModifiedDate }" pattern="yyyy년 M월 d일"/> </td>
					</tr>
					<tr>
						<th>할인가격</th>
						<td><strong class="text-danger"><fmt:formatNumber value="${book.discountPrice }" pattern="#,###"/></strong> 원</td>
						<th>가격</th>
						<td><fmt:formatNumber value="${book.price }" pattern="#,###"/> 원</td>
					</tr>
					<tr>
						<th>저자</th>
						<td>${book.author }</td>
						<th>출판사</th>
						<td>${book.publisher }</td>
					</tr>
					<tr>
						<th>판매여부</th>
						<td>${book.onSale ? '판매중' : '판매완료' }</td>
						<th>재고량</th>
						<td><fmt:formatNumber value="${book.stock }"/> 권</td>
					</tr>
					<tr>
						<th>설명</th>
						<td colspan="3">${book.description }</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="col-2 mt-5">
			<div class="row mb-3">
				<div class="col">
					<div class="card">
						<div class="card-body">
							<p>수량을 선택하세요</p>
							<form id="form-order" class="row" method="get" action="/order/form">
								<input type="hidden" name="id" value="${book.id }" />
								<div class="mb-3">
									<div class="input-group mb-3">
										<button class="btn btn-outline-secondary" id="btn-minus-quantity" type="button"><i class="bi bi-dash-lg"></i></button>
										<input type="text" class="form-control border-secondary" style="padding-left: 32px;" name="quantity" value="1" readonly="readonly">
										<button class="btn btn-outline-secondary" id="btn-plus-quantity" type="button"><i class="bi bi-plus-lg"></i></button>
									</div>
								</div>
								<div class="d-grid gap-2 mb-2">
									<button class="btn btn-primary" type="button" id="btn-add-cart">카트에 담기</button>
								</div>
								<div class="d-grid gap-2">
									<button class="btn btn-success" type="submit">바로구매</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="row mt-5 mb-3">
				<div class="col">
					<div class="d-grid gap-2">
						<a href="/book/list" class="btn btn-outline-primary">책 목록보기</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(function() {
	// 수량입력필드를 선택해서 변수에 미리 저장한다.
	var $quantityField = $("#form-order :input[name=quantity]");

	// 수량의 마이너스 버튼을 클릭했을 때 실행할 이벤트 핸들러 함수를 등록한다.
	$("#btn-minus-quantity").click(function() {
		var quantity = parseInt($quantityField.val());
	
		if (quantity <= 1) {
			$quantityField.val("1");
			return;
		}
		$quantityField.val(quantity - 1);
	});
	
	// 수량의 플러스 버튼을 클릭했을 때 실행할 이벤트 핸들러 함수를 등록한다.
	$("#btn-plus-quantity").click(function() {
		var quantity = parseInt($quantityField.val());
		
		if (quantity >= 9) {
			$quantityField.val("10");
			return;
		}
		$quantityField.val(quantity + 1);
	});
	
	// 카트에 담기 버튼을 클릭했을 때 실행할 이벤트 핸들러 함수를 등록한다.
	$("#btn-add-cart").click(function() {
		$("#form-order").attr("action", "/cart/add").trigger("submit");
	});
})
</script>
</body>
</html>