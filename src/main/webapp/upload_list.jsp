<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="dbproject.subject.*"%>
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
			<div id="top_header">제출목록</div>
		</div>

		<div id="subject-container">
			<div id="subject-header">
				<table border="1px" cellpadding="0" cellspacing="0" align="center">
					<tr height="30">
						<!-- BOARD LIST -->
						<td align="center" width="100">번호</td>
						<td align="center" width="300">파일 이름</td>
						<td align="center" width="150">작성자</td>
						<td align="center" width="150">작성시간</td>
						<td align="center" width="300">과제 이름</td>
						<td align="center" width="100">비고</td>
					</tr>
		<c:choose>
			<c:when test="${not empty files }">
				<c:forEach var="list" items="${files }" varStatus="status">
							<tr height="50">
								<td align="center">${status.count }</td>
								<td>
								<a href="/files/download?fileName=${list.fileName }"> 
								${list.fileName}
								</a>
								</td>
								<td align="center">${list.author}</td>
								<td align="center"><fmt:formatDate value="${list.uploadTime}" pattern="yyyy-MM-dd"/></td>
								<td align="center">${list.subjectName}</td>
								<td align="center"><a href="/files/delete?fileName=${list.fileName}">제출취소</a></td>
							</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
					<tr height="100">
						<td colspan="5" align="center">제출한 파일이 없습니다.</td>
					</tr>
			</c:otherwise>
		</c:choose>
				</table>
			</div>
		</div>

		<div id="subject-footer">
			<button id="subject-button" onclick="location.href='/subjects/subjectList'">과제목록</button>
		
		</div>
	</div>
</body>
</html>