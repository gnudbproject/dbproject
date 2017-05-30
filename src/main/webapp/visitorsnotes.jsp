<%@ page import="dbproject.visitors.PageInfo"%>
<%@ page import="dbproject.visitors.VisitorsNote"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<VisitorsNote> vnList = (ArrayList<VisitorsNote>) request.getAttribute("visitorsNotesList");
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
	int listCount = pageInfo.getListCount();
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/stylesheets/visitorsnotes.css" rel="stylesheet"
	type="text/css">
<%@ include file="./commons/top.jspf"%>
<title>Visitor's Book Page</title>
</head>
<body>
	<div id="nav">
		<%@ include file="./commons/left_sidemenu.jspf"%>
	</div>
	<div id=input-section>
		<form id="form-visitorsnote" action="/visitorsnotes" method="post">
			<table>
				<tr>
					<td>${sessionScope.userId}</td>
				</tr>
				<tr>
					<td><textarea name="notetext" cols="40" rows="5"></textarea></td>
				</tr>
				<tr>
					<td><input type="submit"></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="list-section">
		<table>
		<%
			if (vnList != null && listCount > 0) {
				for (int i = 0; i < vnList.size(); i++) {
		%>
				<tr>
					<td><%=vnList.get(i).getName()%></td>
					<td><%=vnList.get(i).getVbDateToStr()%></td>
				</tr>
				<tr>
					<td><%=vnList.get(i).getVbtext()%></td>
				</tr>
		
				<tr>
					<td>
		<%  		
					boolean yn=true;
					if(request.getSession().getAttribute("isMaster")==null)
						yn=false;
					if(request.getSession().getAttribute("userId").equals(vnList.get(i).getUserid()) || yn) {%>
						<input type="button" name="DEL" value="DEL" onclick="location.href='/rmvnote?page=<%=nowPage%>&code=<%=vnList.get(i).getVbcode()%>'" />
		<%			} %>
					</td>
				</tr>
		<%
				}
		%>
		</table>
	</div>
	<div id="pagelist-sesction">
		<%
				if (nowPage <= 1) {
		%>
					[PREV]&nbsp;
		<%
				} else {
		%>
					<a href="/visitorsnotes?page=<%=nowPage - 1%>">[PREV]</a>&nbsp;
		<%
				}
		%>
		
		<%
				for (int a = startPage; a <= endPage; a++) {
					if (a == nowPage) {
		%>
						[<%=a%>]
		<%
					} else {
		%>
						<a href="/visitorsnotes?page=<%=a%>">[<%=a%>]</a>&nbsp;
		<%
					}
				}
		%>
		
		<%
				if (nowPage >= maxPage) {
		%>
					[NEXT]
		<%
				} else {
		%>
					<a href="/visitorsnotes?page=<%=nowPage + 1%>">[NEXT]</a>
		<%
				}
		%>
	</div>
		<%
			} else {
		%>
	<div id="empty-section">등록된 글이 없습니다</div>
		<%
			}
		%>
</body>
</html>