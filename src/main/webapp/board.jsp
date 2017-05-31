<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="dbproject.board.*"%>

<%
	List list = (List) request.getAttribute("list");
	int count = ((Integer) request.getAttribute("count")).intValue();
	int nowpage = ((Integer) request.getAttribute("page")).intValue();
	int maxpage = ((Integer) request.getAttribute("maxpage")).intValue();
	int startpage = ((Integer) request.getAttribute("startpage")).intValue();
	int endpage = ((Integer) request.getAttribute("endpage")).intValue();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board</title>

</head>
<body>
	<%@ include file="./commons/top.jspf"%>
	<div id="cover_container">
		<%@ include file="./commons/left_sidemenu.jspf"%>


		<div class="wrap ac">
			<div id="container_wrap">
			
				<div id="container">
					<div id="top">
						<div id="top_header">JSP_BOARD_LIST</div>
					</div>

					<div id="board-container">
						<div id="board-header">
							<table id="board_table" border="1px" cellpadding="0"
								cellspacing="0" align="center">
								<tr height="30">
									<!-- BOARD LIST -->
									<td align="center" width="100">NUM</td>
									<td align="center" width="600">SUBJECT</td>
									<td align="center" width="150">USER</td>
									<td align="center" width="150">DATE</td>
									<td align="center" width="100">READCOUNT</td>
								</tr>

								<%
									if (list.size() > 0) {
										for (int i = 0; i < list.size(); i++) {
											Board board = (Board) list.get(i); // LIST를 BoradBean 타입으로 변환
								%>

								<tr height="50">
									<td align="center"><%=(10 * (nowpage - 1)) + (i + 1)%></td>
									<td><a id="a_table"
										href="/board/viewBoard?num=<%=board.getNum()%>&index=<%=i + 1%>&board_userId=<%=board.getUserId()%>">
											<%=board.getSubject()%>
									</a></td>
									<td align="center"><%=board.getUserId()%></td>
									<td align="center"><%=board.getDate().toString()%></td>
									<td align="center"><%=board.getReadcnt()%></td>
								</tr>
								<%
									}
									} else {
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
										<%
											if (nowpage <= 1) {
										%> [PREV]&nbsp; <%
 	} else {
 %> <a
										href="/board/Boardlist?page=<%=nowpage - 1%>">[PREV]</a>&nbsp;
										<%
											}
										%> <%
 	for (int a = startpage; a <= endpage; a++) {
 		if (a == nowpage) {
 %> [<%=a%>] <%
 	} else {
 %> <a
										href="/board/Boardlist?page=<%=a%>">[<%=a%>]
									</a>&nbsp; <%
 	}
 %> <%
 	}
 %> <%
 	if (nowpage >= maxpage) {
 %> [NEXT] <%
 	} else {
 %> <a href="/board/Boardlist?page=<%=nowpage + 1%>">[NEXT]</a>
										<%
											}
										%>
									</td>
							</table>
						</div>
					</div>

					<div id="board-footer">
						<button id="board-button"
							onclick="location.href='/board/createBoardForm'"
							class="btn-custom">글쓰기</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>