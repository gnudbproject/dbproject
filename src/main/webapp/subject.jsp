<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="dbproject.subject.*"%>

<%
	List list=(List)request.getAttribute("list");
	int count=((Integer)request.getAttribute("count")).intValue();
	int nowpage=((Integer)request.getAttribute("page")).intValue();
	int maxpage=((Integer)request.getAttribute("maxpage")).intValue();
	int startpage=((Integer)request.getAttribute("startpage")).intValue();
	int endpage=((Integer)request.getAttribute("endpage")).intValue();
%>

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
			<div id="top_header">과제목록</div>
		</div>

		<div id="subject-container">
			<div id="subject-header">
				<table border="1px" cellpadding="0" cellspacing="0" align="center">
					<tr height="30">
						<!-- BOARD LIST -->
						<td align="center" width="100">NUM</td>
						<td align="center" width="600">SUBJECT</td>
						<td align="center" width="150">USER</td>
						<td align="center" width="150">DATE</td>
						<td align="center" width="100">READCOUNT</td>
					</tr>

		<%
			if(list.size() > 0){
				for(int i=0 ; i < list.size(); i++){
					Subject subject = (Subject)list.get(i); // LIST를 BoradBean 타입으로 변환
		%>

					<tr height="50">
						<td align="center"><%=i+1 %></td>
						<td>
							<%if(subject.getRe_lev()!=0){ %> 
							<%for(int a=0;a<=subject.getRe_lev()*2;a++){ %>
							&nbsp; <%} %> 
						
							<%}else{ %>
							<%} %> 
						<a href="/subjects/viewSubject?num=<%=subject.getSubjectNum()%>&index=<%=i+1 %>&subject_userId=<%=subject.getUserId()%>"> 
						<%=subject.getSubjectName()%>
						</a>
						</td>
						<td align="center"><%=subject.getUserId() %></td>
						<td align="center"><%=subject.getSubjectDate().toString() %></td>
						<td align="center"><%=subject.getSubjectReadCnt() %></td>
					</tr>
			<%	
				}
			}else{	
			%>
					<tr height="100">
						<td colspan="5" align="center">NO DATA.</td>
					</tr>
					<%
			}
			%>
					<tr height="70">
						<!-- BOARD PAGING -->
						<td colspan="7" align="center">
							<%if(nowpage<=1){ %> [PREV]&nbsp; 
							
							<%}else{ %> 
							<a href="/subjects/subjectList?page=<%=nowpage-1 %>">[PREV]</a>&nbsp; 
							<%} %>

						<%for(int a=startpage;a<=endpage;a++){
							if(a==nowpage){%> [<%=a %>] 
						
							<%}else{ %> 
							<a href="/subjects/subjectList?page=<%=a %>">[<%=a%>]
							</a>&nbsp; <%} %> <%} %> <%if(nowpage>=maxpage){ %> [NEXT] <%}
							else{ %> 
							<a	href="/subjects/subjectList?page=<%=nowpage+1 %>">[NEXT]</a> 
							<%}%>
							</td>
				</table>
			</div>
		</div>

		<div id="subject-footer">
			<c:if test="${isMaster }">
				<button id="subject-button" onclick="location.href='/subjects/createSubjectForm'">과제생성</button>
			</c:if>
		</div>
	</div>
</body>
</html>