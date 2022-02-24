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
<c:set var="menu" value="cart" />
<%@ include file="../common/nav.jsp" %>
<div class="container my-3">
    <div class="row mb-3">
        <div class="col">
            <h1>주문하기</h1>
        </div>
    </div>
    <div class="row mb-2">
    	<div class="col-12">
			<p>주문할 상품을 확인하세요</p>
    	</div>
		<div class="col-9">
			<form id="form-cart-item-list" method="get" action="order">
				<table class="table" id="table-cart-items">
					<colgroup>
						<col width="5%">
						<col width="*">
						<col width="12%">
						<col width="10%">
						<col width="10%">
						<col width="23%">
					</colgroup>
					<thead>
						<tr>
							<th>순번</th>
							<th>제목</th>
							<th>가격</th>
							<th>판매가격</th>
							<th>수량</th>
							<th>구매가격</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="orderItem" items="${orderItems }" varStatus="loop">
							<tr>
								<td>${loop.count }</td>
								<td><a href="/book/detail?id=${orderItem.book.id }">${orderItem.book.title }</a></td>
								<td><fmt:formatNumber value="${orderItem.book.price }" /> 원</td>
								<td><strong class="text-danger"><fmt:formatNumber value="${orderItem.price }" /></strong> 원</td>
								<td><fmt:formatNumber value="${orderItem.quantity }" /></td>
								<td>
									<strong class="text-danger"><fmt:formatNumber value="${orderItem.quantity * orderItem.price }" /></strong> 원
									<small>(<fmt:formatNumber value="${orderItem.itemDiscountPrice }"/> 원 할인)</small>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
		</div>
		<div class="col-3">
			<div class="card">
				<div class="card-body">
					<p>결재금액을 확인하세요</p>
					<div class="d-flex justify-content-between mb-2"><strong>총 상품금액</strong> <span><strong><fmt:formatNumber value="${totalPrice }"/></strong> 원</span></div>
					<div class="d-flex justify-content-between mb-3"><strong>총 할인금액</strong> <span><strong><fmt:formatNumber value="${totalDiscountPrice }"/></strong> 원</span></div>
					<hr/>
					<div class="d-flex justify-content-between"><strong>총 결재금액</strong> <span><strong class="text-danger"><fmt:formatNumber value="${totalPaymentPrice }"/></strong> 원</span></div>
				</div>
				<div class="card-footer">
					<button class="btn btn-primary w-100" type="button">결재하기</button>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
</script>
</body>
</html>