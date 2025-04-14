<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String miid = (String)request.getAttribute("miid");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴</title>
</head>
<style>
.main {
	width: 500px;
	display: flex;
	flex-direction: column;
	align-items: center;
	margin: 0 auto;
	font-size: 1.8em;
}

.text {
	width: 100%;
	font-size: 0.9em;
}

.btn {
	font-size: 0.9em;
	width: 100px;
	margin: 30px;
}
.reason {
	width: 100%;
	font-size:1em;
}
</style>
<script>

</script>

<body>
	<form name="outForm" action="member_status_up" method="post">
	<input type="hidden" name="miid" value="<%=miid %>" />
		<div class="main">
			<h2>정말 탈퇴 시키겠습니까 ?</h2>
			<input type="text" class="reason" name="reason" placeholder="탈퇴 사유를 입력해 주세요." />
			<div>
				<input type="submit" value="예" class="btn" />
				<input type="button" value="아니요" class="btn" onclick="self.close()"/>
			</div>
		</div>
	</form>
</body>
</html>