<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 관리</title>
<link href="../css/admin.css?ver=0.13" rel="stylesheet"/>
<link href="../css/members.css" rel="stylesheet"/>
<script type="text/javascript" src="../js/menu.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<style type="text/css">
.bRow0{
	background-color: gray;
}
</style>
</head>
<body>
	<div class="wrap">
		<!-- 메뉴 -->
		<%@ include file="menu.jsp" %>	
		<!-- 본문내용 -->
		<div class="main">	
		<article>
			<h2>게시글 관리</h2>
			<table>
				<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>이름</th>
					<th>날짜</th>
					<th>조회수</th>
					<th>ip</th>
				</tr>
				</thead>
				<tbody>
					<c:forEach items = "${list }" var="row">
					<tr class="bRow${row.del }">
						<td>${row.no }</td>
						<td>${row.title }
						<c:if test="${row.comment ne 0}">
						<span>[${row.comment }]</span></c:if>
						</td>
						<td>${row.write }</td>
						<td>${row.date }</td>
						<td>${row.count }</td>
						<td>${row.ip }</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>	

		</article>
		
		</div>	
	</div>
</body>
</html>