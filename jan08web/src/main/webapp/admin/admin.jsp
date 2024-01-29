<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link href="../css/admin.css?ver=0.13" rel="stylesheet"/>
<!-- css파일 업데이트때마다 0.13 , 4, 5 바꿔주면 바로 변경됨 약간의 꼼수 -->
<script type="text/javascript" src="../js/menu.js"></script>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
</head>
<body>
	<!-- 틀 -->	
	<div class="wrap">
		<!-- 메뉴 -->
		<%@ include file="menu.jsp" %>		
		<!-- 본문내용 -->
		<div class="main">
			<!-- 이 페이지에 오는 모든 사람은 관리자인지 검사
			관리자인 경우 보여주고, 로그인하지 않았거나 일반 사용자는 로그인 페이지로 이동시키기 -->		
		<article>
			<div class="info">
				왼쪽 메뉴를 선택하세요
			</div>
		</article>
		
		</div>	
	</div>
</body>
</html>