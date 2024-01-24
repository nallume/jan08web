<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link href="./css/index.css" rel="stylesheet" />
<link href="./css/menu.css" rel="stylesheet" />
<script type="text/javascript" src="./js/menu.js"></script>
<style type="text/css">
h1 {
color: gray;
}
.login {
	margin: 0 auto;
	width: 300px;
	min-heigth: 300px;
	padding: 10px;
	border: 1px solid #c6c6c6;
	box-sizing: border-box;
	text-align: center;
}
.login input{
	width: 100%;
	height: 30px;
	text-align: center;
	margin-bottom: 10px;
	box-sizing: border-box;
}

.loginButton{
	width: 45%;
	height: 30px;
	color: white;
	font-size: large;
}
</style>

<script type="text/javascript">
function err(){
	let errBox = document.querySelector("#errorMSG"); /* #id = errorMSG를 찾아서 errBox에 대입 */
	errBox.innerHTML = "<marquee>~ 올바른 id와 pw를 입력하세요 ~</marquee>" /* 내부 텍스트를 이걸로 바꿔줘라 */
	errBox.style.color = 'green';
	errBox.style.fontSize = "10pt";
}
</script>
</head>
<body>
	<div class="container">
		<header>
			<%@ include file="menu.jsp"%>
		</header>
		<div class="main">
			<div class="mainStyle">
				<article>
					<h1>login</h1>
					<c:if test="${param.error ne null}"> <!-- 에러에 값이 찍힌다면 = 에러라면! -->
						<script type="text/javascript">
							alert("올바른 암호와 아이디를 입력하세요.");
						</script>
					</c:if>
					
					<div class="login">
						<form action="./login" method="post">
							<img alt="login" src="./img/cute.gif"" width="100%;">
							<input type="text" name="id" placeholder="아이디를 입력하세요">
							<input type="password" name="pw" placeholder="암호를 입력하세요">
							<button type="reset">지우기</button>
							<button type="submit">로그인</button>		
							<div id="errorMSG"></div>
						</form>
						<a href="./join">회원가입</a>
					</div>
				</article>
			</div>
		</div>
		<footer>
			<c:import url="footer.jsp"/>
		</footer>


	</div>
	<c:if test="${param.error ne null }">
		<script type="text/javascript">
			err(); /* 저 위에 function err() 호출 */
		</script>
	</c:if>
	<!-- 위 내용이 다 실행된 뒤에 떠야해서 이 아래에 적는대 -->
</body>
</html>