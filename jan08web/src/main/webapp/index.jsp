<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
<link href="./css/index.css" rel="stylesheet" />
<link href="./css/menu.css" rel="stylesheet" />
<script type="text/javascript" src="./js/menu.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>
<body>
	<div class="container">
		<header>
			<%@ include file="menu.jsp"%>
		</header>
		<div class="main">
			<div class="mainStyle">
				<article>
					<h1>Home</h1>
				</article>
				<article>
					<!-- 변수 값 대입 -->
					<c:set var="number" value="105" />
					<c:out value="${number }" />
					<br>

					<c:forEach begin="1" end="9" var="row" step="2">
						2 x ${row } = ${row*2 }<br>
					</c:forEach>

					<!-- 조건문 -->
					<c:if test="${number eq 105 }">
						number는 105 입니다.<br>
						
						조건문에 쓸 수 있는 것들<br>
						eq  11 == 11<br>
					    ne  10 != 11<br>
						lt   5 〈 10<br>
						le   5 〈=10<br>
						gt  11 〉 10<br>
						ge  11 〉=10<br>
						&&<br>
						||<br>
						empty<br>
						not empty
					</c:if>
				</article>
					<hr>
					1~45까지 짝수만 출력하세요 <br>
				<article>
					<c:forEach begin="1" end="45" var="row">
						<c:if test="${row % 2 eq 0 }">
							${row }
						</c:if>
					</c:forEach>
				</article>
				<hr>
				<article>
					<br>
					<c:forEach begin="1" end="10" var="row" varStatus="s" >
						${s.index }
						${s.first } <!-- 첫번째 값이냐 (참 거짓)-->
						${s.last } <!-- 마지막 값이냐 -->
						${s.begin } <!-- 시작 값 -->
						${s.end } <!-- 마지막 값 --> / <br>
					</c:forEach>
					
				</article>
			</div>
		</div>
		<footer>
			<c:import url="footer.jsp"/>
		</footer>


	</div>

</body>
</html>