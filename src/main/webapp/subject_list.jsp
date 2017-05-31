<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="dbproject.homework.*"%>

<% 
	if((Integer)request.getAttribute("allSubjectCount")!=null){
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

	<%@ include file="./commons/top.jspf"%>
	<div id="cover_container">
		<%@ include file="./commons/left_sidemenu.jspf"%>


		<div class="wrap ac">
			<div id="container_wrap">
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
			<%} %>
		</div>
	</div>
	
			</div>
		</div>
	</div>
	
</body>
</html>