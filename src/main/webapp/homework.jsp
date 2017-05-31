<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="dbproject.homework.*"%>

<% 
		//과목 정보 조회를 위한 초기화
		int allSubjectCount=((Integer)request.getAttribute("allSubjectCount")).intValue();
		String[] allSubjectName = new String[allSubjectCount];
		allSubjectName = (String[])request.getAttribute("allSubjectName");
	
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board</title>
<link href="/stylesheets/board.css?v=1" rel="stylesheet" type="text/css">
<%@ include file="./commons/top.jspf"%>

<script type="text/javascript">

	function change() {;
	var frm = document.frm;
	
	var subjectNameList = frm.subjectNameList;
	var txt = frm.combo_nm;
	
	var subjectNameListVal = subjectNameList.value;
	
		for( var i=0; i<subjectNameList.options.length; i++ ) {
			if( subjectNameList.options[i].value == subjectNameListVal ) {
				subjectNameList.options[i].checked = true;
			txt.value= subjectNameListVal;
			}
		}

	}
</script>


</head>
<body>
		<%@ include file="./commons/left_sidemenu.jspf"%>
	<div id="container">
		<div id="top">
		
				<form name="frm" action="/homeworks/homeworkList" method="get">
					과목 선택 : 
					<SELECT name="subjectNameList" onChange="change();">
					<OPTION value="-1" name="none">조회할 과목을 선택하세요.</OPTION>
					<%
						for(int i = 0; i < allSubjectCount; i++) { 
						%>
							<OPTION value="<%=allSubjectName[i] %>"><%=allSubjectName[i] %></OPTION>
							<% 
						}
					%>
					</SELECT>
				
					<input type="submit" value="조회">
				</form>
		
			<div id="top_header">과제목록</div>
		</div>
		
		<%
	if(request.getAttribute("list")!=null){
		
		List list=(List)request.getAttribute("list");
		String subjectName=(String)request.getAttribute("subjectName");
		int count=((Integer)request.getAttribute("count")).intValue();
		int nowpage=((Integer)request.getAttribute("page")).intValue();
		int maxpage=((Integer)request.getAttribute("maxpage")).intValue();
		int startpage=((Integer)request.getAttribute("startpage")).intValue();
		int endpage=((Integer)request.getAttribute("endpage")).intValue();
	
%>
		
		<div id="homework-container">
			<div id="homework-header">
				<table border="1px" cellpadding="0" cellspacing="0" align="center">
					<tr height="30">
						<!-- BOARD LIST -->
						<td align="center" width="100">NUM</td>
						<td align="center" width="600">과제명</td>
						<td align="center" width="150">작성자</td>
						<td align="center" width="150">등록날짜</td>
						<td align="center" width="100">조회수</td>
					</tr>

		<%
			if(list.size() > 0){
				for(int i=0 ; i < list.size(); i++){
					Homework homework = (Homework)list.get(i); // LIST를 BoradBean 타입으로 변환
		%>

					<tr height="50">
						<td align="center"><%=(10*(nowpage-1))+(i+1) %></td>
						<td>
						<a href="/homeworks/viewHomework?num=<%=homework.getHomeworkNum()%>&index=<%=i+1 %>&homework_userId=<%=homework.getUserId()%>"> 
						<%=homework.getHomeworkName()%>
						</a>
						</td>
						<td align="center"><%=homework.getUserId() %></td>
						<td align="center"><%=homework.getHomeworkDate().toString() %></td>
						<td align="center"><%=homework.getHomeworkReadCnt() %></td>
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
							<a href="/homeworks/homeworkList?page=<%=nowpage-1 %>">[PREV]</a>&nbsp; 
							<%} %>

						<%for(int a=startpage;a<=endpage;a++){
							if(a==nowpage){%> [<%=a %>] 
						
							<%}else{ %> 
							<a href="/homeworks/homeworkList?page=<%=a %>">[<%=a%>]
							</a>&nbsp; <%} %> <%} %> <%if(nowpage>=maxpage){ %> [NEXT] <%}
							else{ %> 
							<a	href="/homeworks/homeworkList?page=<%=nowpage+1 %>">[NEXT]</a> 
							<%}%>
							</td>
				</table>
			</div>
		</div>

		<div id="homework-footer">
			<c:if test="${isMaster }">
				<button id="homework-button" onclick="location.href='/homeworks/createHomeworkForm?subjectName=${subjectName}'">과제생성</button>
			</c:if>
		</div>
		<%} %>
	</div>
	
</body>
</html>