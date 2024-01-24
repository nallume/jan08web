<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Freelancer - Start Bootstrap Theme</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body id="page-top">
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg bg-secondary text-uppercase fixed-top" id="mainNav">
            <div class="container">
                <a class="navbar-brand" href="#page-top">😒부트스트랩</a>
                <button class="navbar-toggler text-uppercase font-weight-bold bg-primary text-white rounded" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="fas fa-bars"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="./board">보드</a></li>
                        <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="./qna">문의게시판</a></li>
                        <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="./notice">공지사항</a></li>
                        <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="./login">로그인</a></li>
                    </ul>
                </div>
            </div>
        </nav>
      	<section>
      		<div class="mt-5">123
      		</div>
      		<div class="m-5 p-5 border-top border-bottom">
      		<table class="table table-hover">
      			<thead>
      				<tr class="row">
      					<th class="col-1">번호</th>
      					<th class="col-6">제목</th>
      					<th class="col-2">글쓴이</th>
      					<th class="col-2">날짜</th>
      					<th class="col-1">조회수</th>
      				</tr>
      			</thead>
      			<tbody>
      				<c:forEach items="${list }" var="row">
      				<tr class="row">
      					<td class="col-1">${row.no }</td>
      					<td class="col-6">${row.title }</td>
      					<td class="col-2">${row.write }</td>
      					<td class="col-2">${row.date }</td>
      					<td class="col-1">${row.count }</td>
      				</tr>
      				</c:forEach>
      			</tbody>
      		</table>
      		<button type="button" class="btn btn-outline-primary">글쓰기</button>
      		</div>
      		
      		<div class="container bg-secondary text-white">
      			구역
      			<div class="m-5 mb-0 bg-warning">구역2</div>
      			<div class="mx-2 bg-primary">구역3</div>
      			<div class="my-2 bg-primary w-25">구역4</div>
      			<div class="my-2 bg-primary w-50">구역5</div>
      			<div class="my-2 bg-primary w-75">구역6</div>
      		</div>
      		
      	</section>
        <footer>
  
        </footer>
  

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <!-- * *                               SB Forms JS                               * *-->
        <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>
</html>
    