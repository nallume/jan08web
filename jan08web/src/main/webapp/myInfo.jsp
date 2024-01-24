<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보 보기</title>
<link href="./css/index.css" rel="stylesheet" />
<link href="./css/menu.css" rel="stylesheet" />
<script type="text/javascript" src="./js/menu.js"></script>
<style type="text/css">
table {
	width: 900px;
	height: 400px;
	border-collapse: collapse;
}
.w1{
	width: 10%;
}
.w2{
	width: 20%;
}
.w5{
	width: 50%;
}
</style>
</head>
<body>
<%
//로그인 여부 검사하기
if (session.getAttribute("mid") == null){
	response.sendRedirect("./login");
}
%>
	<div class="container">
		<header>
			<%@ include file="menu.jsp"%>
		</header>
		<div class="main">
			<div class="mainStyle">
				<article>
					<h1>내 정보 보기</h1>
				</article>
				<article>
					${myInfo.mname } / ${myInfo.mid } 					
					<div>
						<form action="./myInfo" method="post" onsubmit="return check()">
						<!-- check()메소드를 호출해 실행 → 그 결과가 true면 아래 코드 계속 진행 -->
						<br><input type="password" name="newPW" id="newPW" placeholder="~변경할 암호를 입력하세요~"> 
						<button type="submit">수정하기</button>
						</form>
					</div>
				</article>
				<article>
					<h2>방문 흔적 찾아가기</h2>
					
					<table>
						<thead>
							<tr>
								<td class="w5">글 제목</td>
								<td class="w2">읽은 날짜</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${readData }" var="d">
							<tr>
								<td class="w5" onclick="location.href='./detail?no=${d.board_no }'">${d.board_title }</td>
								<td class="w2">
								<fmt:parseDate value="${d.vdate }" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
                       			<!-- ${d.vdate}를 date로 변환시켜조 (DAO에서 스트링타입으로 받아와서 한번 변환이 필요) -->
                       			<fmt:formatDate value="${date }" pattern="yyyy년 MM월 dd일 HH시 mm분 ss초"/>
                       			<!-- date를 이 패턴으로 보여줘 -->
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</article>
			</div>
		</div>
		<footer>
			<c:import url="footer.jsp"/>
		</footer>
	</div>
	<script type="text/javascript">
		function check() {
			//let reg = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/
			var pw = document.querySelector("#newPW");
			if(pw.value.length < 5){
				alert("암호는 5글자 이상이어야 합니다.");
				return false;
			}
		}
	</script>
</body>
</html>