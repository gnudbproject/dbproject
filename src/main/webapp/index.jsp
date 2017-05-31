<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="./stylesheets/index.css?" rel="stylesheet" type="text/css">
<head>
<title>Insert title here</title>
</head>
<body>

	<%@ include file="./commons/top.jspf"%>
	<div id="cover_container">
		<%@ include file="./commons/left_sidemenu.jspf"%>


		<div class="wrap ac">
			<div id="container_wrap">
				<div id="container">
				
					<div id="title">
						열심히 공부합시다
						<p>"DB Project"</p>
					</div>
					<div id="content">
						<%-- 			<form id="form-sign" action="/users/login" method="post">
				<c:if test="${not empty errorMessage }">
					<div class="control-group">
						<label class="error">${errorMessage}</label>
					</div>
				</c:if>
				<div class="control-group">
					<label class="control-label" for="userId">사용자 아이디</label> <input
						type="text" name="userId" value="" />
				</div>
				<div class="control-group">
					<label class="control-label" for="password">비밀번호</label> <input
						type="password" name="password" value="" />
				</div>
				<div id="button">
					<div class="controls">
						<button type="button" onclick="location.href='/users/createForm' "
							class="btn btn-primary">Sign up</button>
						<button type="submit" class="btn btn-primary">Sign in</button>
					</div>
				</div>
				<a href="#"> forget your ID/password ? </a>
			</form> --%>
						<form id="form-sign" action="/users/login" method="post"
							class="form-inline">
							<c:if test="${not empty errorMessage }">
								<div class="control-group">
									<label class="error">${errorMessage}</label>
								</div>
							</c:if>

							<div
								style="width: 300px; margin: 0 auto; text-align: right; margin-bottom: 20px;">
								<div class="input-group">
									<span class="input-group-addon" id="basic-addon1"
										style="width: 83px"> ID </span> <input type="text"
										class="form-control" aria-describedby="basic-addon1"
										name="userId" value="" placeholder="Enter Id" />
								</div>
								<div class="input-group">
									<span class="input-group-addon">password</span> <input
										type="password" class="form-control" name="password" value=""
										placeholder="Enter Password" />
								</div>
							</div>

							<div id="button">
								<div class="controls">
									<!-- 					<button type="button" onclick="location.href='/users/createForm'" class="btn btn-primary">Sign up</button> -->
									<!-- <button type="button" onclick="popupOpen();" class="btn btn-danger">Sign up</button> -->
									<button type="button" class="btn btn-default"
										data-toggle="modal" data-target="#myLoginModal">Sign
										up</button>
									<button type="submit" class="btn btn-default">Sign in</button>
									<!-- 환용 : 가입버튼에 모달 적용 -->
								</div>
							</div>
							<a href="#"> forget your ID/password ? </a>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>