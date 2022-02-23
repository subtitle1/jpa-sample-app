<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="tags.jsp" %>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<div class="container">
		<ul class="navbar-nav me-auto">
			<li class="nav-item"><a class="nav-link ${menu eq 'home' ? 'active' : '' }" href="/">홈</a></li>
			<li class="nav-item"><a class="nav-link ${menu eq 'book' ? 'active' : '' }" href="/book/list">책</a></li>
			<li class="nav-item"><a class="nav-link ${menu eq 'cart' ? 'active' : '' }" href="/cart/list">장바구니</a></li>
		</ul>
		<sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal" var="user"/>
			<span class="navbar-text"><strong class="text-white">${user.name }</strong>님 환영합니다.</span>
		</sec:authorize>
		<ul class="navbar-nav">
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