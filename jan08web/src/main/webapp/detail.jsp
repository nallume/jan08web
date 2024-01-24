<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>톺아보기</title>
<link href="./css/index.css" rel="stylesheet" />
<link href="./css/menu.css" rel="stylesheet" />
<link href="./css/detail.css" rel="stylesheet" />
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script type="text/javascript" src="./js/menu.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/javascript">
$(document).ready(function(){
	//01-24 댓글 쓰기 버튼을 누르면 댓글창 나타나게 하기
	$(".comment-write").hide(); //ready 바로 아래 두는게 좋당
	//$(".comment-write").show(5000); // 5초동안 서서히 나타나기
	
	//버튼 누르면 나타나기
	$(".xi-comment-o").click(function(){	
		$(".xi-comment-o").hide(); // 버튼은 사라지고 
		$(".comment-write").show();	// 댓글창은 나타나고
		//$(".comment-write").slideToggle('slow'); // ..스르륵 등장
	});
	
	

	
	
	
	
	//댓글 수정하기
	$(".commentEdit").click(function(){
		if(confirm('수정하시겠습니까?')){
			//필요한 값 cno잡기 , 수정한 내용 잡기 + 로그인(세션) -> 서블릿에서 정리 
			let cno = $(this).siblings('.cno').val();
//			let cmm = $(this).parents('.comment').children('.ccomment').text(); 얘도 가넝
			let cmm = $(this).parents('.chead').next(); //변경
			//alert(cno + " : " + cmm);
			$(this).hide();
			$(this).next().hide();
			
			cmm.css('height', '110');
			cmm.css('padding-top', '10px');
			cmm.css('backgroundColor', 'beige');
			
			let commentChange = cmm.html().replaceAll("<br>", "\r\n");
			//alert(commentChange);
			
			let recommentBox = '<div class="recommentBox">';
			recommentBox += '<form action="./cedit" method="post">';
			recommentBox += '<textarea class="commentcontent" name="comment">' + commentChange + '</textarea>';
			recommentBox += '<input type="hidden" name="cno" value="' + cno + '">';
			recommentBox += '<button class="comment-btn" type="submit">댓글수정</button>';
			recommentBox += '</form></div>';
			
			cmm.html(recommentBox);
		}
		
	});
	

	
	
	
	//댓글 삭제 버튼을 누른 경우
	$(".commentDelete").click(function(){
		//alert("댓삭...?");
		//부모 객체 찾아가기(삭제할 번호?) = this : 나, 클릭한 주체
		//$(this).parent(".cname").css('color', 'green');
		//$(this).parent(".cname").text("변경가능");
		//let text = $(this).parent(".cname").text();
		//부모 요소 아래 자식요소 찾기 children()
		//let cno = $(this).parent().children(".cno").val();
		//형제요소 찾기 .siblings("클래스이름") .prev() 바로이전 형제 .next() 바로 다음형제
		//let cno = $(this).prev().val();
		//alert(cno);
		
		if(confirm("댓삭?")){
			let cno = $(this).siblings(".cno").val();
			//ajax
			let point = $(this).closest(".comment");
			$.ajax({
				url : './commentDel',  // 보낼 주소
				type : 'post',		//get이냐 post냐 
				dataType : 'text', 	//수신타입. result를 받는 아이. int여도 되고, 나중에는 json으로 쓸것임
				data : {'cno':cno},  //보낼 값
				success : function(result){ // 0, 1
					alert("서버에서 온 값 : " + result);
					if(result == 1){
						//정상 삭제된 경우 : this의 부모(.comment)를 찾아서 remove
						//즉 화면에서 없애는 동작이 이것
						//$(this).closest.(".comment").remove();
						point.remove();
					} else {
						alert("삭제할 수 없습니다. 관리자에게 문의하세요.");
					}
				},
				error : function(request, status, error){ //통신오류
					alert("문제 발생 으앙");
				}
			});//ajax end
			
		}
		
		
	});
	
	

	
	
	
	
	//alert("준비 완");
	$("#comment-btn").click(function() {
		//댓글창에 입력한 내용 잡기
		let content = $("#commentcontent").val();
		//글 번호 잡기
		let bno = ${detail.no };
		//alert("content : " + content + " no : " + no);
		//세션은 서블릿에서 잡아주면 ok
		
		// 가상 form 만들기 - 실제 html에 포함되지 않은 가짜 태그에 폼을 담아서 보내기?
		// 동적 생성
		// 전송할때 체크 사항 - 길이가 일정 길이 이상인지 등 체크
		if(content.length < 5){
			alert("댓글은 5글자 이상 작성해주세요.");
			//content는 $("#commentcontent").val()이니까.. 포커스는 칸에 하는거라서 직접 적기
			$("#commentcontent").focus();
			//return false(); 어차피 멈추니까 안 적어줘도됨
		} else {
			//가상 form 생성
			//jquery
			let form = $('<form></form>');
			form.attr('name', 'form');
			form.attr('method', 'post');
			form.attr('action', './comment');
			
			form.append($('<input/>', {type:'hidden', name:'commentcontent', value:content})); // json형식. map과 유사
			form.append($('<input/>', {type:'hidden', name:'bno', value:bno}));

			form.appendTo("body");
			form.submit();
			//json - 이종시스템간의 데이터 교환 시 json과 xml진자 많이 쓸 것.
			

			//자바스크립트
/* 			let form = document.createElement('form');
			form.name='form';
			form.method='post';
			form.action='./comment';
			//붙이기
			let text = document.createElement('input');
			text.setAttribute("type","hidden");
			text.setAttribute("name","commentcontent");
			text.setAttribute("value", content);
			//text.setAttribute("value", $("#commentcontent").val());
			//붙이기
			let no = document.createElement('input');
			no.setAttribute("type","hidden");
			no.setAttribute("name","bno");
			no.setAttribute("value", ${detail.no });			
			//no.setAttribute("value", bno);			
			//form에다가 붙이기
			form.appendChild(text);
			form.appendChild(no);
			//전송하기
			document.body.appendChild(form);
			form.submit(); */
		}
		
	});
	
	//댓글쓰기 창에 쓸 수 있는 글자 표시해주고 넘어가면 입력 불가로 바꾸기
	//id="commentcontent"
	//id="comment-btn"
	$("#commentcontent").keyup(function(){
        let text = $(this).val();
        if(text.length > 100){
           alert("100자 넘었어요.");
           $(this).val(  text.substr(0, 100)   );   
        }
        $("#comment-btn").text("글쓰기 " + text.length +  "/100");
     });
	
	
	
	
});

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
					<div class="detailDIV">
						<div class="detailTITLE">
							${detail.title }
						</div>
						<div class="detailWRITECOUNT">
							<div class="detailWRITE">
								${detail.write }
								<c:if test="${sessionScope.mname ne null && detail.mid eq sessionScope.mid}">
									<img alt="수정" src="./img/edit.png" onclick="update()"> 
									<img alt="삭제" src="./img/delete.png" onclick="del()"> 
								</c:if>
							</div>
							<div class="detailCOUNT"> ${detail.ip } / ${detail.count }</div>							
						</div>
						<div class="detailCONTENT">
							${detail.content }
						</div>
					</div>
					<!-- 24 - 1 - 22 댓글창 만들기 -->
					<!-- 댓글 쓰는 창 -->
					<!-- 그동안은 내용을 form태그로 묶어서 전송 -->
					<!-- 이번에는 가상(?)으로 전송시키겠돠 -->
					<!-- 댓글 내용, 쓴 사람(세션), 어느 글에(detail=no) 썼는지 세가지 값이 필요 -->
					<!-- 로그인 한 사람한테만 보이기 -->
					<c:if test="${sessionScope.mid ne null }">
					<button class="xi-comment-o">댓글쓰기</button>
					<div class="comment-write">
						<div class="comment-form">
							<textarea id="commentcontent" name="commentcontent"></textarea>
							<!-- id를 이용해서 내용을 잡아서 제이쿼리로 / name, class는 여러곳에 쓰여서 배열 형식으로 들어오기 때문에 id추천 -->
							<button id="comment-btn">등록하기</button>
							<!-- onclick="commentwrite()" -->
							<!-- onclick : 자바스크립트가 인식하는 명령. 뒤에 ()로 함수표시를 추가하면 제이쿼리도 인식할수있음 -->
						</div>
					</div>
					</c:if>
					<!-- 댓글 출력창 -->
					<div class="comments">
						<c:forEach items="${commentList }" var="co">
							<div class="comment">
								<div class="chead">
									<div class="cname">${co.mname }님
									<c:if test="${sessionScope.mname ne null && co.mid eq sessionScope.mid}">
										<input type="hidden" class="cno" value="${co.cno }"> <!-- cno값 불러오기.. 화면엔 안보이지만 소스코드 상으로 존재 -->
										<img alt="수정" src="./img/edit.png" class="commentEdit"> 
										<img alt="삭제" src="./img/delete.png" class="commentDelete"> 
									</c:if>
									</div>
									<div class="cdate">${co.cip} / ${co.cdate }</div>
								</div>
								<div class="ccomment">${co.ccomment }</div>
							</div>
						</c:forEach>
					</div>
					<button onclick="url('./board?page=${param.page}')">게시판으로</button>
					<!-- menu.js에서 url로 줄여부르기 설정했음 -->
				</article>
				<article>
					footer?
				</article>
			</div>
		</div>
		<footer>
		<c:import url="footer.jsp"/>
		</footer>
	</div>

	<script type="text/javascript">
		function del() {
			var ch = confirm("삭제하시겠습니까?");
			//사용자가 예를 누르면 true, 아니오를 누르면 false값을 반환
			if(ch){
				location.href="./delete?no=${detail.no }";
				/* 주소가 스트링으로 처리 => 아이콘 누르면 페이지 넘버가 숫자로 변환됨
				why? 29일꺼 확인, jstl
				*/
			}
		}
		
		function update() {
			//위 두줄 합친 버전
			if(confirm("수정?")){
				location.href="./update?no=${detail.no }";
			}
		}
		
/* 		function commentDel(cno){
			if(confirm("댓글을 삭제하시겠습니까?")){
				location.href='./commentDel?no=${detail.no}&cno='+cno;
			}
		} */
		


	</script>
</body>
</html>