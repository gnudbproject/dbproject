<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="dbproject.board.*"%>
<%@ page import="dbproject.user.LoginServlet" %>
<%@ page import= "dbproject.support.SessionUtils" %>
<%@ page import= "javax.servlet.http.HttpSession" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="/stylesheets/form2.css" rel="stylesheet" type="text/css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Runtime</title>
</head>

<body>
	<!-- actionUrl 설정 필요 -->
			<c:set var = "actionUrl" value = "/subjects/createSubject" />
			<c:if test="${isView}">
			<c:set var = "actionUrl" value = "/subjects/updateSubject" />
			</c:if>
			
		<form id="board-field" action="${actionUrl}" method="post">
		
		<table width="400" border="1" cellspacing="0" cellpadding="0" align="center">
			<tr>
			<td align="center" colspan="2"> 
				
				<c:set var = "TitleName" value = "과제생성" />
				<c:if test = "${isView}" >
				<c:set var = "TitleName" value = "과제확인" />
				</c:if>
					<h2>${TitleName}</h2></td>
			</tr>
		<tr>
				<td width="70" align="center">NUMBER</td>
				<td width="330">
				<input type="hidden" name="num" value="${subject.subjectNum}" />${param.index }</td>
			</tr>
			<tr>
				<td width="70" align="center">USER</td>
				<td width="330">
				<c:if test="${isCreate}">
					<input type="hidden" name="userId" value="${userId}" /> ${userId}</td>
				</c:if>
				<input type="hidden" name="userId" value="${subject.userId}" /> ${subject.userId}</td>
			</tr>
			<tr>
				<td width="70" align="center">SUBJECT</td>
				<td width="330">
				<c:if test="${isCreate}">
				<input type="text" size="40" maxlength="50" name="subject" value="${subject.subjectName}"></td>
				</c:if>
				<input type="hidden" size="40" maxlength="50" name="subject" value="${subject.subjectName}">${subject.subjectName}</td>
				</tr>

			<tr>
				<td width="70" align="center">CONTENT</td>
				<td width="330">
				<c:choose>
				<c:when test = "${isCreate==true||isUser==true||isMaster==true}" >
				<textarea name="content"  rows = "13" cols="40">${subject.subjectContent}</textarea></td>
				</c:when>
				
				<c:otherwise>
				<textarea readonly name="content" rows="16" cols="50" style = "resize:none;">${subject.subjectContent}</textarea>
				</c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<td colspan="2" align="center">
			<c:choose>
			
			<c:when test="${isView}">
			    <c:set var="sNum" value="${subject.subjectNum}"/>
				<c:if test = "${empty isMaster}" >
					<a href="/upload.jsp?subjectNum=<c:out value="${sNum }"/>">과제 제출하기</a>
				</c:if>
				<a href="/files/uploadlist?subjectNum=<c:out value="${sNum }"/>">제출확인하기</a>
				
				<c:if test = "${isMaster==true}">  <!-- master아이디만 과제게시물 수정삭제 가능 -->
				<input type="button" name="delete"  value="Delete" onclick="location.href='/subjects/removeSubject?num=${subject.subjectNum}'" />
				<input type="submit" name="modify" value="Modify"/>
				</c:if>
			</c:when>
			
			<c:otherwise>
				<input type="submit" value="Submit" /> 
				<input type="reset" value="Reset" />
			</c:otherwise>
			</c:choose>
				<input type="button" value="List" onclick="location.href='/subjects/subjectList'" />
				</td>
			</tr>
	</table>
	
	
</form>
</body>
</html>