<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>coffee</title>
<link href="./css/index.css" rel="stylesheet" />
<link href="./css/menu.css" rel="stylesheet" />
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
    $('#checkName').on('click', function() {
        var inputName = $('#name').val();
        let nameList = "${classMate}"; 

        if (nameList.indexOf(inputName) != -1) {
            alert('입력한 이름은 리스트에 있습니다.');
            alert("안녕 " + inputName +"! 메뉴를 골라");
            $('#finishBtn').prop('disabled', false);
        } else {
            alert("우리반에 그런 사람은 업어용...");
        }
    });
    
    
    $('#finishBtn').on('click', function(){
    	if(confirm("결정 완?")){
    		let setname = $('#name').val();
    		let setmenu = $('input[name="menu"]:checked').val();
    		let setice = $('input[name="ice"]:checked').val();
    		
    		alert("이름 : " + setname + "메뉴" + setmenu + setice);
    		
    		$.ajax({
    			url: './coffee',
    			type: 'post',
    			dataType: 'text',
    			data: {'name':setname, 'menu':setmenu, 'ice':setice},
    			success : function(result){
    				if(result == 1) {
    					alert("반영 완");
   					} else {
   						alert("에러");
   					}
   				},
    			error : function (request, status, error){
    				alert("통신 오류");
   				}  				
  			});
    	}
    	 	    	
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
					<h1>뽑기</h1>
					<div class="select">
						<div class="selectName">이름<br>
							<input type="text" id="name" name="name" placeholder="이름을 정확히 입력하세요">
							<button id="checkName">이름 체크</button>
						</div><br>
						<div class="selectMenue">메뉴 선택<br>
							<input type="radio" class="coffee" name="menu" value="1" checked/>커피
							<input type="radio" class="coffee" name="menu" value="2" checked/>차
						</div><br>
						<div class="selectIce" >아이스 / 핫 <br>
							<input type="radio" name="ice" class="ice" value="1" checked/>아이스
							<input type="radio" name="ice" class="ice" value="0" checked/>핫
						</div><br>
						<div class="btn">
							<button id="finishBtn" disabled="disabled">&ensp;결정&ensp;</button>				
						</div>
					</div>
				</article>
				
				<article>
					<h1>총계</h1>
						<div class="total">
						
							<table>
								<tr>
									<th class="w1">뜨거운 커피</th>
									<th class="w1">아이스 커피</th>
									<th class="w1">뜨거운 티</th>
									<th class="w1">아이스 티</th>
									<th class="w1">결정 못함</th>
								</tr>
								<tr>
									<td class="w1">${total.get("coffeehot") }</td>
									<td class="w1">${total.get("coffeeice") }</td>
									<td class="w1">${total.get("teahot") }</td>
									<td class="w1">${total.get("teaice") }</td>
									<td class="w1">${total.get("none") }</td>
								</tr>
							</table>
						</div>
						
						<div>
							
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
