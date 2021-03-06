<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="dbproject.board.*"%>


<%  // 추가항목sh
	List list=(List)request.getAttribute("list");
	boolean  confirm = (boolean)request.getAttribute("isCreate");	

%>

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
			<c:set var = "actionUrl" value = "/board/createBoard" />
			<c:if test="${isView}">
			<c:set var = "actionUrl" value = "/board/updateBoard" />
			</c:if>
			
	<form id="board-field" action="${actionUrl}" method="post">
		
		<table id = "board_table" width="400" border="1" cellspacing="0" cellpadding="0" align="center">
			<tr>
			<td align="center" colspan="2"> 
				
				<c:set var = "TitleName" value = "CREATE_JSP_BOARD" />
				<c:if test = "${isView}" >
				<c:set var = "TitleName" value = "VIEW_BOARD" />
				</c:if>
					<h2>${TitleName}</h2></td>
			</tr>
		<tr>
				<td width="70" align="center">NUMBER</td>
				<td width="330">
				<input type="hidden" name="num" value="${board.num}" /><%= request.getParameter("index") %></td>
			</tr>
			<tr>
				<td width="70" align="center">USER</td>
				<td width="330">
				<c:if test="${isCreate}">
					<input type="hidden" name="userId" value="${userId}" /> ${userId}</td>
				</c:if>
				<input type="hidden" name="userId" value="${board.userId}" /> ${board.userId}</td>
			</tr>
			<tr>
				<td width="70" align="center">SUBJECT</td>
				<td width="330">
				<input type="text" size="40" maxlength="50" name="subject" value="${board.subject}"></td>
			</tr>
			<tr>
				<td width="70" align="center">E-mail</td>
				<td width="330">
				<input type="hidden" name="email" value="${user.email}" /> ${user.email}</td>
			</tr>
		<tr>
				<td width="70" align="center">CONTENT</td>
				<td width="330">
				<c:choose>
				<c:when test = "${isCreate==true||isUser==true||isMaster==true}" >
					<textarea name="content"  rows = "13" cols="40">${board.content}</textarea></td>
				</c:when>
				
				<c:otherwise>
					<textarea readonly name="content" rows="16" cols="50" style = "resize:none;">${board.content}</textarea>
				</c:otherwise>
				
				</c:choose>
			</tr>
			<tr>
				<td colspan="2" align="center">
			<c:choose>
			
			<c:when test="${isView}">
				<c:if test = "${isNotUser}" >
				<a></a>
				</c:if>
			
				<c:if test = "${isUser==true||isMaster==true}">  <!-- master아이디는 모든 게시물 수정삭제 가능 -->
				<input style = "margin-top: 3px;background-color: #337ab7;color: #fff;" type="button" name="delete"  value="Delete" onclick="location.href='/board/removeBoard?num=${board.num}'" />
				<input style = "margin-top: 3px;background-color: #337ab7;color: #fff;"class = "bnt-custom" type="submit" name="modify" value="Modify"/>
				</c:if>
			</c:when>
			
			<c:otherwise>
				<input style = "margin-top: 3px;background-color: #337ab7;color: #fff;" type="submit" value="Submit" /> 
				<input style = "margin-top: 3px;background-color: #337ab7;color: #fff;" type="reset" value="Reset" />
			</c:otherwise>
			</c:choose>
				<input style = "margin-top: 3px;background-color: #337ab7; color: #fff;"type="button" value="List" onclick="location.href='/board/Boardlist'" />
				</td>
			</tr>
	</table>
	</form>
	
	
	<% //추가sh
	if(confirm != true){ %>
		<div class="wrap">
		
				<form action="/replyReply" method="post">
					<input type="hidden" name="num" value="${board.num}" />
					<input type="text" size="60" maxlength="200" name="reContent" value="">
					<input type="submit" name="reWrite" value="댓글입력" />
				</form>
	    	
	    
	    
	<table border="1px" cellpadding="0" cellspacing="0" align="center">
		<tr height="30">
									<td align="center" width="50">아이디</td>
									<td align="center" width="700">내용</td>
									<td align="center" width="10">삭제</td>
								</tr>
	<%		
		for(int i=0 ; i < list.size(); i++){
			reBoardDTO reBoard = (reBoardDTO)list.get(i); 			
	%>
	<tr height="30">
			<td align="center">
			<%=reBoard.getUserId() %>
			</td>
			<td align="center">
				<%=reBoard.getContent() %>	
				</td>
				<td>
			<%
			boolean yn=true;
			if(request.getSession().getAttribute("isMaster")==null)
				yn=false;
			if(request.getSession().getAttribute("userId").equals(reBoard.getUserId()) || yn==true){%>  <!-- 마스터 아이디는 모든댓글 삭제 가능 -->
				<input class="x" type="button" name="X" value="X" onclick="location.href='/board/removeReBoard?reNum=<%=reBoard.getNum() %>&bNum=<%=reBoard.getBoardNum() %>'" />	
	<%          }%>
			</td>
				
			
	<%
		}%>
	</tr>
	<%
	}			
	%>  
	</table>
	</div>
	</div>
	</div>
	</div>
</body>
</html>