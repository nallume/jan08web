<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link href="../css/admin.css" rel="stylesheet"/>
<link href="../css/members.css" rel="stylesheet"/>
<script type="text/javascript" src="../js/menu.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script type="text/javascript">
//$(document).ready(function(){
$(function(){
//	alert("!");
	$('select[name=grade]').on("change", function(){
		let val = $(this).val(); // 변경시킬 등급
		//alert(val);	
		//변경한 회원의 번호 가져오기
		let no = $(this).parent().siblings('#mno').text(); //번호 칸에 id부여 한 경우
		//alert(no);		
		//let ano = $(this).closest("tr").children().first().text(); // 그냥 하는 경우
		//alert(ano);
		
		let currentgrade = '${param.grade}';
		
		// 두 값을 잡았으면 가상 form에 담아서 전송
		let form = $('<form></form>');
		form.attr('method', 'post');
		form.attr('action', './members');
		form.append($('<input/>', {type: 'hidden', name: 'mno', value: no}));
		form.append($('<input/>', {type: 'hidden', name: 'grade', value: val}));
		form.append($('<input/>', {type: 'hidden', name: 'currentgrade', value: currentgrade}));
		form.appendTo('body');
		form.submit();
		//요즘 쓰는 방식
		
		//ajax 버전도 만들어보기...
		
	});
	
});
</script>
</head>
<body>
	<div class="wrap">
		<div class="menu">
			<nav>
				<ul>
					<li onclick="url('./members')"><i class="xi-users-o"></i> 회원 관리</li>
					<li onclick="url('./board')"><i class="xi-document"></i> 게시글 관리</li>
					<li onclick="url('./comments')"><i class="xi-comment-o"></i> 댓글 관리</li>
					<li onclick="url('./info')"><i class="xi-lock-o"></i> farmer님</li>
					<li></li>
				</ul>
			</nav>
		</div>		
		<!-- 본문내용 -->
		<div class="main">	
		<article>
			<h2>회원관리</h2>
			<div class="member-group">
				<ul>
					<li onclick="url('members?grade=0')"><i class="xi-ban"></i>강퇴</li>
					<li onclick="url('members?grade=1')"><i class="xi-close-circle-o"></i>탈퇴</li>
					<li onclick="url('members?grade=2')"><i class="xi-minus-circle-o"></i>정지</li>
					<li onclick="url('members?grade=5')"><i class="xi-check-circle-o"></i>정상</li>
					<li onclick="url('members?grade=9')"><i class="xi-wrench"></i>관리자</li>
				</ul>
			</div>

			<%-- ${memberList } --%>
			<table>
				<thead>
				<tr>
					<td class="w1">번호</td>
					<td class="w2">아이디</td>
					<td class="w2">이름</td>
					<td class="w4">가입일</td>
					<td class="w1">등급</td>
				</tr>
				</thead>
				<tbody>
					<c:forEach items = "${list }" var="row">
					<tr class="row${row.mgrade}">
						<td class="w1" id="mno">${row.mno }</td>
						<td class="w2">${row.mid }</td>
						<td class="w2">${row.mname }</td>
						<td class="w4">${row.mdate }</td>
						<td class="w1">
							<select name="grade">
								<optgroup label="정지">
									<option <c:if test="${row.mgrade eq 0 }">selected="selected"</c:if> value="0">0 강퇴</option>
									<option <c:if test="${row.mgrade eq 1 }">selected="selected"</c:if> value="1">1 탈퇴</option>
									<option <c:if test="${row.mgrade eq 2 }">selected="selected"</c:if> value="2">2 정지</option>
								</optgroup>
								<optgroup label="정상">
									<option <c:if test="${row.mgrade eq 5 }">selected="selected"</c:if> value="5">5 평민</option>
								</optgroup>
								<optgroup label="관리자">
									<option <c:if test="${row.mgrade eq 9 }">selected="selected"</c:if> value="9">9 관리자</option>
								</optgroup>
							</select>
							${row.mgrade }
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>	

		</article>
		
		</div>	
	</div>
</body>
</html>