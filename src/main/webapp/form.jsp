<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Runtime</title>

<link href="stylesheets/form.css" rel="stylesheet" type="text/css">
</head>

<script>
	function idchk() {
		var id = document.joinform.userId.value;
		
		var idCheck = /^[A-Za-z0-9_-]*$/;
		
		if (id == null||id.length<1) {
			alert("아이디를 입력하십시오.");
		} else if(idCheck.test(id))
		 {
			if(id.length > 12|| id.length <4){
				alert("아이디는 최소 4자 최대 12자 입니다.");
			}else {
				var url = "/users/check?id=" + id;
				window.open(url, "get", "height = 220px, width = 300px");
			}
		 }else{
			alert("아이디는 영어와 숫자로만 작성해주세요.");
		}
		return false;
	}
</script>


<body>

	<div class="signup-container">
		<div class="signup-header">

			<c:set var="pageName" value="회원가입" />
			<c:if test="${isUpdate}">
				<!-- userId 유무로써 판단했던 것을 서블릿 정보로써 판단  -->
				<c:set var="pageName" value="개인정보수정" />
			</c:if>
			<h1>${pageName}</h1>
		</div>

		<c:set var="actionUrl" value="/users/create" />
		<c:if test="${isUpdate}">
			<c:set var="actionUrl" value="/users/update" />
		</c:if>

		<form id="form-sign" action="${actionUrl}" method="post"
			name="joinform">

			<c:if test="${not empty errorMessage}">
				<div class="Validator">
					<label class="error">
						<h4>${errorMessage}</h4>
					</label>
				</div>
			</c:if>

			<div class="first">
				<label class="" for="name"> 이름 </label>

				<c:choose>
					<c:when test="${isUpdate}">
						<input type="hidden" name="name" value="${user.name}" />
				${user.name}
			</c:when>

					<c:otherwise>
						<input type="text" name="name" value="${user.name}" />
					</c:otherwise>
				</c:choose>
			</div>

			<div>
				<label class="" for="age">나이</label>
				<c:choose>
					<c:when test="${isUpdate}">
						<input type="hidden" name="age" value="${user.age}" />
				${user.age}	
			</c:when>

					<c:otherwise>
						<input type="text" name="age" value="${user.age}" />
					</c:otherwise>
				</c:choose>

				<div>
					<label class="" for="gender">성별</label>
					<c:choose>
						<c:when test="${isUpdate}">
							<input type="hidden" name="gender" value="${user.gender}" />
				${user.gender}
			</c:when>

						<c:otherwise>
							<input type="checkbox" name="gender" value="Man" />남
				<input type="checkbox" name="gender" value="Women" />여
			</c:otherwise>
					</c:choose>
				</div>

				<div>
					<label class="" for="userId">Id</label>

					<c:choose>
						<c:when test="${isUpdate}">
							<input type="hidden" name="userId" value="${user.userId}" />
				${user.userId}
			</c:when>

						<c:otherwise>
							<input type="text" name="userId" value="${user.userId}" />
							<input type="button" name="id_check" onClick="idchk()"
								value="중복확인">
						</c:otherwise>
					</c:choose>
				</div>

				<div>
					<label class="" for="password">Password</label> <input
						type="password" name="password" value="${user.password}" />
				</div>

				<div>
					<label class="" for="password2">Password-check</label> <input
						type="password" name="password2" value="${user.password}" />
				</div>

				<div>
					<label class="" for="email">이메일</label> <input type="text"
						name="email" value="${user.email}" />
				</div>

				<div class="signup-footer">
					<button type="submit" class="sign_up_button">

						<c:set var="buttonName" value="가입하기" />
						<c:if test="${isUpdate}">
							<c:set var="buttonName" value="수정하기" />
						</c:if>

						${ buttonName }
					</button>
				</div>
		</form>
	</div>

</body>
</html>