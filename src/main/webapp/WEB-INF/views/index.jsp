<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 페이지</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript">
	$(function() {
		$("#login-button").click(function() {
			login();
		})
	})

	function login() {
		$.ajax({
			url : "/securelogin",
			type : "POST",
			dataType : "json",
			data : {
				memberId : $("#member-id").val(),
				memberPassword : $("#member-password").val()
			},
			beforeSend : function(xhr) {
				var $token = $("#token");
				xhr.setRequestHeader($token.data("token-name"), $token.val());
			},
			success : function(response) {
				if (response.code == "200") {
					window.location = response.item.url;
				} else {
					alert(response.message);
				}
			},
			error : function(a, b, c) {
				console.log(a, b, c);
			}

		})

	}
</script>
</head>
<body>
	<div>
		<div>
			<div>
				<div>
					<div>
						<input type="text" id="member-id" autofocus="autofocus"
							placeholder="ID">
					</div>
					<div>
						<input type="password" id="member-password" placeholder="Password">
					</div>
					<div>
						<input type="hidden" id="token"
							data-token-name="${_csrf.headerName}" placeholder="Password"
							value="${_csrf.token}">
					</div>
					<div>
						<div>
							<div>
								<button id="login-button">로그인</button>
							</div>
						</div>
					</div>
					<!-- 로그아웃은 로그인 후 이동되는 곳에 추가해줘야함.
					<form action="/logout" method="POST">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<button type="submit">LOGOUT</button>
					</form>
					-->
				</div>
			</div>
		</div>
	</div>
	<a
		href="https://kauth.kakao.com/oauth/authorize?client_id=07d1e4164004cd996e1ce6d978a81577&redirect_uri=http://localhost:8080/login&response_type=code">
		<img src="/img/kakao_login_large_narrow.png">
	</a>
</body>
</html>
