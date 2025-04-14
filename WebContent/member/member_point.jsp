<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.text.DecimalFormat"%>
<%
request.setCharacterEncoding("utf-8");
DecimalFormat formatter = new DecimalFormat("###,###");
int point = (int) request.getAttribute("point");
String miid = (String)request.getAttribute("miid");	
%>
<!DOCTYPE html>
<html>
<head>
<script src="/greenAdmin/src/js/jquery-3.6.1.js"></script>
<link rel='stylesheet'
	href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>


<style>
h3 {
	text-align: center;
}

.point {
	display: table;
	margin: 0 auto
}

.row {
	display: table-row;
	background: #fff;
}

.cell {
	padding: 10px 12px;
	display: table-cell;
	vertical-align: middle;
	text-align: Center;
	border-bottom: 1px solid #ddd;
	color: #666
}

.cell p {
	display: flex;
	gap: 10px
}

.cell button {
	flex: 1;
	background: #fff;
	padding: 5px 7px;
	border: 1px solid #ddd;
	position: relative
}
/* .cell button:after{position: absolute;top:0;left:0;content:''} */
.cell button:after {
	position: absolute;
	content: "";
	top: 0;
	left: 0;
}

.cell button:active {
	top: 2px;
}

.cell span {
	flex: 1
}

.cell .select {
	display: flex;
	flex-direction: column;
}

.cell input {
	color: #666;
	border: 1px solid #d4d4d4;
	background-color: #fff;
	padding: 11px 5px;
	box-sizing: border-box
}

.cell select {
	color: #666;
	border: 1px solid #d4d4d4;
	background-color: #fff;
	padding: 11px 5px;
	box-sizing: border-box;
	width: 100%;
	margin-bottom: 10px
}

.btn_wrap {
	margin-top: 30px;
	text-align: center;
}

.btn_wrap input {
	border: 1px solid #666;
	padding: 10px 20px;
	display: inline-block;
	background: #666;
}

.cancel input {
	margin-left: 10px;
	background: #fff;
	color: #666
}

.btn_wrap input {
	text-decoration: none;
	color: #000
}
</style>
<script>
var point = <%=point %>;
var su = 's';
function chgPoint(n) {
	if (n=='+') {
		var chgPoint = $('#chgPoint').val();
		point = point + Number(chgPoint);
		var strPoint = String(point);
		strPoint = strPoint.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,') + " P";
		$('#totalPoint').val(strPoint);
	} else {
		var chgPoint = $('#chgPoint').val();
		point = point - Number(chgPoint);
		if (point < 0) {
			alert("0미만으로는 불가능 합니다.");
			point = point + Number(chgPoint);
			
		} else {
			var strPoint = String(point);
			strPoint = strPoint.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,') + " P";
			$('#totalPoint').val(strPoint);
		}
	}
	if (point < <%=point %>) {
		su = 'u';
	}
} 

function onlyNum(obj) {
	if (isNaN(obj.value)) {
		obj.value = "";
	}
}

$(document).ready(function() {
	$("#reason").change(function() {
		if ($(this).val() == "") {
			$("#direct").val("");
		} else if($(this).val() == "direct") {
			$("#direct").val("");
			$("#direct").focus();
			$("#direct").attr('readonly', false);
		} else {
			$("#direct").val($(this).val());
			$("#direct").attr('readonly', true);
		}
	});
});

function submit() {
	var reason = $("#direct").val();
	var miid = $("#miid").val();
	if (reason == "") {
		alert("사유를 적어주세요.");
		return
	}
	if (<%=point %> == point) {
		alert("포인트 변동 없음");
		return
	}
	$.ajax({
		type : "POST",
		url : "/greenAdmin/member_point_up",
		data : {"reason" : reason, "point" : Math.abs(<%=point%> - point), "miid" : miid, "su" : su},
		success : function(chkRs) {
			if (chkRs == 2) {
				alert("포인트 처리 완료");
				opener.location.reload();
				window.close();
			} else {
				alert(chkRs);
				alert("포인트 처리에 실패 했습니다.");
			}
		}
	});
}
</script>

<body>

	<h3>회원 포인트 관리</h3>
	
	

	<div class="point">
		<div class="row">
			<div class="cell">보유 포인트</div>
			<div class="cell">
				<input type="hidden" id="miid" value="<%=miid %>" />
				<input type="text" value="<%=formatter.format(point)%> P" readonly />
			</div>
		</div>
		<div class="row">
			<div class="cell">포인트 증감 사유</div>
			<div class="cell">
				<div class="select">
					<select name="" id="reason">
						<option value="direct">직접 입력</option>
						<option>이벤트</option>
						
					</select>
					<input type="text" id="direct" placeholder="이유를 입력해 주세요."/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="cell">
				<p>
					<button onclick="chgPoint('+')">
						<i class="fa-regular fa-plus"></i>
					</button>
					<button onclick="chgPoint('-')">
						<i class="fa-solid fa-minus"></i>
					</button>
				</p>

			</div>
			<div class="cell">
				<input type="text" id="chgPoint" placeholder="숫자를 입력해 주세요." onkeyup="onlyNum(this);"/>
			</div>
		</div>
		<div class="row">
			<div class="cell">총 포인트</div>
			<div class="cell">
				<input type="hidden" id=totalHidden" />
				<input type="text" id="totalPoint" value="<%=formatter.format(point) %> P">
			</div>
		</div>
	</div>


	<div class="btn_wrap">
		<span class="submit">
			<input type="button" value="확인" onclick="submit()" />
		</span>
		<span class="cancel">
			<input type="button" value="취소" onclick="self.close()"/>
		</span>
	</div>
	

	<script src='https://kit.fontawesome.com/77ad8525ff.js'
		crossorigin="anonymous"></script>
</body>
</html>
