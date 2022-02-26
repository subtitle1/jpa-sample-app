<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="tags.jsp" %>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<div class="container">
		<ul class="navbar-nav me-auto">
			<li class="nav-item"><a class="nav-link ${menu eq 'home' ? 'active' : '' }" href="/">북스토어</a></li>
			<li class="nav-item"><a class="nav-link ${menu eq 'book' ? 'active' : '' }" href="/book/list">도서목록</a></li>
		</ul>
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link ${menu eq 'cart' ? 'active' : '' }" href="/cart/list">카트</a></li>
			<li class="nav-item"><a class="nav-link ${menu eq 'order' ? 'active' : '' }" href="/member/order/list">주문내역</a></li>
			<sec:authorize access="!isAuthenticated()">
				<li class="nav-item"><a class="nav-link ${menu eq 'login' ? 'active' : '' }" href="/login">로그인</a></li>
				<li class="nav-item"><a class="nav-link ${menu eq 'register' ? 'active' : '' }" href="/register">회원가입</a></li>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<form id="form-logout" method="post" action="/logout">
					<sec:csrfInput/>
				</form>
				<li class="nav-item"><a class="nav-link" href="/logout" id="link-logout">로그아웃</a></li>
			</sec:authorize>
		</ul>
	</div>
</nav>
<script>
$(function() {
	$("#link-logout").click(function(e) {
		e.preventDefault();
		document.getElementById("form-logout").submit();
	})
})
</script>