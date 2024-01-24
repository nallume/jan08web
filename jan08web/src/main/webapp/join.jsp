<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="./css/index.css" rel="stylesheet" />
<link href="./css/menu.css" rel="stylesheet" />
<script type="text/javascript" src="./js/menu.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.7.1.min.js"></script>
<style type="text/css">
.id-alert, .name-alert, .pw-alert{
}
.alert{
	color: red;
}
</style>
<script type="text/javascript">
	//$(선택자).할일();
	//$("#id").val();
	//id가 "id"인 아이를 찾아내서 값을 뽑아조라
	
	// 글로벌 변수 - 가입버튼을 누를때 아이디 검사여부를 다시 한번 체크하도록?
	let idCheckBool = false;
	
	
//  제이쿼리 시작문 - 제이쿼리 시작합니다.
//	$(document).ready(function (){
//		$('.id-alert').hide(); //해당 클래스 명을 가진 개체 숨겨라		
//	});
	
	
// 더 간단 버전
	$(function(){
		$('.id-alert, .name-alert, .pw-alert').hide();
	/* 	$("#id").change(function(){
			alert("아이디입력창 값이 변경되었습니다.");
		}); */
		
		//onchange() : input창에 입력된 값이 변경 될 때 마다 실행
		//on = 변화를 감지
		$('#id').on("change keyup paste", function(){
			//alert("아이디입력창 값이 변경되었습니다.");			
			$('.id-alert').show();
			$('.id-alert').html('<p class="alert">당신이 입력한 ID : ' + $('#id').val() + '</p>');
			//$('.id-alert').text("당신이 입력한 ID : " + $('#id').val());
			if($('#id').val().length > 4){
				idCheck();
			} //id입력창에 5글자 이상 적으면 자동으로 id검사 해줌
		});				
	});
	
		function check() {
			let id = $("#id").val();
			//alert(id + " : " + id.length);
			if(id.length < 5 || id == null){
				//alert("아이디는 5글자 이상이어야 합니다.");
				$('.id-alert').show();
				$("#id").focus();
				return false;
			} else { 
				$('.id-alert').hide();
				}
 	 		if(!idCheckBool){
				alert("ID 검사를 먼저 실행해주세요.");
				return false;
			} 
			
			let name = $('#name').val();
			if(name.length < 3){
				alert("이름은 3글자 이상이어야 합니다.");
				$('.name-alert').show();
				$('#name').focus();
				return false;
			}
			$('.name-alert').hide();
			
			let pw1 = $("#pw1").val();
			let pw2 = $("#pw2").val();
			if(pw1.length < 8){
				$('.pw-alert').show();
				alert("암호는 8글자 이상이어야 합니다.");
				$('#pw1').focus();
				return false;
			}
			if(pw1 != pw2){
				alert("비밀번호가 일치하지 않습니다.");
				$('#pw2').val("");
				//""안의 값으로 바꿔줌 -> 비워놓으면 지운 것 처럼 보임
				$('#pw2').focus();				
				return false;	
			}
			$('.pw-alert').hide();
		}
		
		function idCheck(){
			//alert('id검사를 눌렀습니다.');
			let id = $('#id').val();
			//const regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"\sㄱ-ㅎㅏ-ㅣ가-힣]/g;   //한글+공백
			//alert(regExp.test(id)); //한글 포함여부 검사하는 정규식 - 한글이 있으면 true
			//나중에는 영어 소문자, 숫자만 들어오게 해주는 것에 도전해보자
			const regExp = /^[a-z0-9]{5,15}$/; //영어, 숫자 5글자 ~ 15글자
			//const : 블록 범위의 상수. 아래에서 값 변경, 재선언이 불가
			if(id.length < 4 || !regExp.test(id)){
				//alert("아이디는 영문자 5글자 이상이고 특수문자가 없어야 합니다.");
				$('.id-alert').html('<p class="alert">아이디는 영문자와 숫자 5 ~ 15글자이고 특수문자가 없어야 합니다.</p>');
				$('#id').focus();
			} else {
				//alert(id);
				//id가 5글자 이상이면, 이제 ajax 필요
				$.ajax({
					url : './idCheck', //이동할 url주소
					type : 'post',		//post / get
					dataType : 'text', //오는 데이터 타입(수신타입)
					data : {'id' : id}, //앞 - 보낼 이름(변수명) / 뒤 - 보낼 값  (요게 json모양)
					success : function(result){ //성공시
						//alert("통신에 성공했습니다.");
						if(result == 1){
						//alert("이미 가입되어있습니다.");
						$('.id-alert').append('<p class="alert">이미 가입되어있습니다.</p>');
						
						//가입할수 있는 아이디 입력해서 비활성화 해제한 뒤에 가입할 수 없는 아이디를 넣는 경우 방지
						idCheckBool = false;
						$("#joinBtn").attr("disabled", "disabled");//비활성화 시키기 disabled="disabled"랑 같아
						//attr은 속성이라는 뜻
						$('#id').focus();
						
						} else {							
						//alert("가입할 수 있습니다. 다음을 계속 진행하세요.");
						$('.id-alert').append('<p class="alert">가입할 수 있습니다.</p>');
						$('.id-alert .alert').css("color", "green");
						$("#joinBtn").removeAttr("disabled"); // 비활성화 제거하기 = 활성화 시키기
						//$('#name').focus();
						idCheckBool = true;
						}
					},
					error : function(request, statue, error){ //접속불가, 통신 장애 등
						alert("문제가 발생했습니다.");						
					}
				});
			}
			return false;
		}
</script>
</head>
<body>
	<div class="container1">
		<header>
			<%@ include file="menu.jsp"%>
		</header>
		<div class="main">
			<div class="mainStyle">
				<article>
					<div class="join-form">
						<h1>회원가입</h1>
						<div class="mx-3 p-3 border">
							<form action="./join" method="post" onsubmit="return check()">
								<div class="input-group mb-2">
									<label>아이디
									<input type="text" id="id" name="id" class="form-control" placeholder="아이디를 입력하세요">
									<!-- <button class="btn border" onclick="return idCheck()">ID검사</button> -->
									</label>
								</div>
								<div class="input-group mb-2 id-alert">
									<p class="alert">올바른 아이디를 입력하세요</p>
								</div>
								<div class="input-group mb-2">
									<label>이름
									<input type="text" id="name" name="name" class="form-control" placeholder="이름을 입력하세요"></label>
								</div>
								<div class="input-group mb-2 name-alert">
									<p class="alert">올바른 이름을 입력하세요</p>
								</div>
								<div class="input-group mb-2">
									<label>암호
									<input type="password" id="pw1" name="pw1" class="form-control" placeholder="암호를 입력하세요"></label>
								</div>
								<div class="input-group mb-2">
									<label>암호 재입력
									<input type="password" id="pw2" name="pw2" class="form-control" placeholder="한번 더 입력하세요"></label>
								</div>
								<div class="input-group mb-2 pw-alert">
									<p class="alert">올바른 암호를 입력하세요</p>
								</div>
								<div class="input-group mt-1">
									<button type="reset" class="btn border">&ensp;초기화&ensp;</button>
									<button id="joinBtn" type="submit" disabled="disabled" class="btn border">가입하기</button>
								</div>
							</form>
						</div>
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