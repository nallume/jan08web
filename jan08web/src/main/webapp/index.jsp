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
					<h1>바질...</h1>
				</article>
				<article>
					<div>
						<img alt="basil" src="./img/basil.jpg">
					</div>
					<div>
                            			<p class="saying">
						<h3>"언제 키워서 잡아먹냐."</h3>
						</p>
                      				<p class="people">
						- mom</p>					
					</div>
					<hr>
					<div>
                            			<p class="saying">
						<h3>"듣지마 넌 최고의 바질이야."</h3>
						</p>
                      				<p class="people">
						- farmer</p>					
					</div>
					<hr>
					<div>
                            			<p class="saying">
						<h3>"......."</h3>
						</p>
                      				<p class="people">
						- basil</p>					
					</div>
				</article>
				

			</div>
		</div>
		<footer>
			<c:import url="footer.jsp"/>
		</footer>


	</div>

</body>
</html>
