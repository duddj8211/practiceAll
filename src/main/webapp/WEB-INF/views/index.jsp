<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<style>
	.commonClass {
		color: blue;
		cursor: pointer;
	}
</style>
</head>
<body>
<!-- member 추가 테스트 푸시 -->
	<script>
		function page(num) {
			switch(num) {
				case 1 : 
					if($("#otpArea").css('display') == 'none'){
						$("#otpArea").css('display', 'block');
					} else {
						$("#otpArea").css('display', 'none');
					}
				break;
			}
		}
	</script>
	<!-- OTP -->
	<h2><a class="commonClass" onClick="page(1)">1. OTP 기능</a></h2>
	<div id="otpArea" style="display: none">
		<jsp:include page="otp.jsp"></jsp:include>
	</div>
</body>
</html>