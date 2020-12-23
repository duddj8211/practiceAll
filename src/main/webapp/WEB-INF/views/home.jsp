<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="utf-8" %>
<html>
<head>
	<title>Home</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
	<table>
		<tr>
			<th>아이디:</th>
			<td><input type='text' name='userId' id='userId' /></td>
		</tr>
		<tr>
			<th>비밀번호:</th>
			<td><input type='password' name='userPwd' id='userPwd' /></td>
		</tr>	
		<tr>
			<td colspan="2"><button id="submitBtn">전송</button></td>
		</tr>
	</table>
	
	<!-- otp 등록창 -->
	<div id="otpEnroll" style="display: none;">
		<p>Secret key : <span style="color: red;"></span></p>
		<p><img src="" /></p>
		<button id="qrEnroll">등록완료</button>
	</div>
	
	<!-- otp 인증창 -->
	<div id="otpCheck" style="display: block;">
		<div>
			<p>OTP 번호</p>
			<input type="text" id="otpNum" />
			<button id="qrCheck">확인</button>
		</div>
	</div>
	
	<script>
		$("#submitBtn").click(function(){
			if($("#userId").val() == '' || $("#userId").val() == null){
				return alert('아이디는 입력 바람');
			}
			if($("#userPwd").val() == '' || $("#userPwd").val() == null){
				return alert('비밀번호 입력 바람');
			}
			var param = {};
			param.id = $("#userId").val();
			param.pw = $("#userPwd").val();
			
			$.ajax({
				url: "confirmUser",
				data: JSON.stringify(param),
				type: "POST",
				dataType: 'json',
				contentType:"application/json; charset=utf-8",
				success: function(result){
					if(result > 0) {
						// otp 인증 모달
						$.ajax({
							url: "checkOTP",
							type: "post",
							dataType: "json",
							data: JSON.stringify(param),
							contentType: "application/json; charset=utf-8",
							success: function(result){
								if(result > 0 ){
									// 발급한 otp가 있는 경우
									// 깃 연결 테스트 푸시
								} else {
									// 발급한 otp가 없는 경우
									
								}
							},
							error: function(result){
								alert("otp check 통신 에러");
							}
						})
					} else {
						alert("User 정보 불일치");
					}
				},
				error:function(request,status,error){
					alert("User check 통신 에러");
				}
				
			})
		});
	</script>
</body>
</html>
