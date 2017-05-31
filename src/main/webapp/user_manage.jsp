<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="dbproject.user.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board</title>
<link href="/stylesheets/board.css?v=1" rel="stylesheet" type="text/css">
<%@ include file="./commons/top.jspf"%>
</head>
<body>
		<%@ include file="./commons/left_sidemenu.jspf"%>
	<div id="container">
		<div id="top">
			<div id="top_header">유저목록</div>
		</div>

		<div id="userlist-container">
			<div id="userlist-header">
				<table border="1px" cellpadding="0" cellspacing="0" align="center">
					<tr height="30">
						<td align="center" width="100">구분</td>
						<td align="center" width="300">아이디</td>
						<td align="center" width="150">이름</td>
						<td align="center" width="100">나이</td>
						<td align="center" width="150">이메일</td>
						<td align="center" width="100">성별</td>
						<td align="center" width="100">비고</td>
					</tr>
					<c:choose>
						<c:when test="${not empty users }">
							<c:forEach var="list" items="${users }">
								<tr height="50">
									<td align="center">
										<c:if test="${list.power==1}">
										선생님
										</c:if>
										<c:if test="${list.power==0}">
										학생
										</c:if>
									</td>
									<td align="center">${list.userId }</td>
									<td align="center">${list.name}</td>
									<td align="center">${list.age}</td>
									<td align="center">${list.email}</td>
									<td align="center">${list.gender}</td>
									<td><a href="/users/dropuser?userId=${list.userId }">강퇴</a></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
								<tr height="100">
									<td colspan="5" align="center">유저가 없습니다.</td>
								</tr>
						</c:otherwise>
					</c:choose>
							</table>
			</div>
		</div>

		<div id="userlist-footer">
			<button id="userlist-button" onclick="location.href='/users/createForm'">교사 계정 생성</button>
		
		</div>
	</div>
</body>
</html>