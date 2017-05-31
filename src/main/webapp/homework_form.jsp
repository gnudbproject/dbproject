<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="/stylesheets/form2.css" rel="stylesheet" type="text/css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Runtime</title>
</head>

<body>

	<%@ include file="./commons/top.jspf"%>
	<div id="cover_container">
		<%@ include file="./commons/left_sidemenu.jspf"%>


		<div class="wrap ac">
			<div id="container_wrap">
				<!-- actionUrl 설정 필요 -->
				<c:set var="actionUrl" value="/homeworks/createHomework" />
				<c:if test="${isView}">
					<c:set var="actionUrl" value="/homeworks/updateHomework" />
				</c:if>

				<form id="homework-field" action="${actionUrl}" method="post">

					<table width="400" border="1" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td align="center" colspan="2"><c:set var="TitleName"
									value="과제생성" /> <c:if test="${isView}">
									<c:set var="TitleName" value="과제확인" />
								</c:if>
								<h2>${TitleName}</h2></td>
						</tr>
						<tr>
							<td width="70" align="center">NUM</td>
							<td width="330"><input type="hidden" name="num"
								value="${homework.homeworkNum}" />${param.index }</td>
						</tr>
						<tr>
							<td width="70" align="center">작성자</td>
							<td width="330"><c:if test="${isCreate}">
									<input type="hidden" name="userId" value="${userId}" /> ${userId}</td>
							</c:if>
							<input type="hidden" name="userId" value="${homework.userId}" />
							${homework.userId}
							</td>
						</tr>
						<tr>
							<td width="70" align="center">과제명</td>
							<td width="330"><c:if test="${isCreate}">
									<input type="text" size="40" maxlength="50" name="homeworkName"
										value="${homework.homeworkName}"></td>
							</c:if>
							<input type="hidden" size="40" maxlength="50" name="homeworkName"
								value="${homework.homeworkName}">${homework.homeworkName}</td>
						</tr>

						<tr>
							<td width="70" align="center">내용</td>
							<td width="330"><c:choose>
									<c:when test="${isCreate==true||isUser==true||isMaster==true}">
										<textarea name="content" rows="13" cols="40">${homework.homeworkContent}</textarea></td>
							</c:when>

							<c:otherwise>
								<textarea readonly name="content" rows="16" cols="50"
									style="resize: none;">${homework.homeworkContent}</textarea>
							</c:otherwise>
							</c:choose>
						</tr>
						<tr>
							<td colspan="2" align="center"><c:choose>

									<c:when test="${isView}">
										<c:set var="sNum" value="${homework.homeworkNum}" />
										<c:if test="${empty isMaster}">
											<a href="/upload.jsp?homeworkNum=<c:out value="${sNum }"/>">과제
												제출하기</a>
										</c:if>
										<a
											href="/files/uploadlist?homeworkNum=<c:out value="${sNum }"/>">제출확인하기</a>

										<c:if test="${isMaster==true}">
											<!-- master아이디만 과제게시물 수정삭제 가능 -->
											<input type="button" name="delete" value="Delete"
												onclick="location.href='/homeworks/removeHomework?homeworkNum=${homework.homeworkNum}'" />
											<input type="submit" name="modify" value="Modify" />
										</c:if>
									</c:when>

									<c:otherwise>
										<input type="submit" value="Submit" />
										<input type="reset" value="Reset" />
									</c:otherwise>
								</c:choose> <input type="button" value="List"
								onclick="location.href='/homeworks/homeworkList'" /></td>
						</tr>
					</table>
					<input type="hidden" name="subjectName"
						value="${homework.subjectName }" />
			</div>
		</div>
	</div>

	</form>
</body>
</html>