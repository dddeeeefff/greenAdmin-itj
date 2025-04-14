<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_inc/inc_head.jsp" %>
<%
request.setCharacterEncoding("utf-8");
LocalDate today = LocalDate.now();	// 오늘 날짜


%>
<style>
#page-contents {
	width:1000px;	margin:50px 0 50px 500px;
}
.frm_frame{ width:800px; border:1px solid black; padding:15px; }
#be_title { width:600px; height:30px; padding:5px; }
#be_img { cursor:pointer; }
#sdate, #edate { width:100px; height:30px; padding:5px; }
#be_content { width:100%; height:400px; vertical-align:top; padding:5px; }
#btn_box { display:flex; justify-content: flex-end; }
#cancel { background:white; }
#cancel, #btnsch { width:80px; height:30px; cursor:pointer; }
</style>
<script>
function validation(file) {
	var file_path = file.value;
	var reg = /(.*?)\.(jpg|bmp|jpeg|png)$/;
	
	// 허용되지 않은 확장자일 경우
	if (file_path != "" && (file_path.match(reg) == null || reg.test(file_path) == false)) {
		if ($.browser.msie) {		// ie 일때 
			$(this).replaceWith($(this).clone(true));
		} else {
			file.value = "";
		}		
		alert("이미지 파일만 업로드 가능합니다.");
		return;
	}
	
	// 파일의 크기가 5MB 이상일 경우
	var maxSize = 5 * 1024 * 1024;
	var fileSize = file.files[0].size;

	if(fileSize > maxSize){
		alert("첨부파일 사이즈는 5MB 이내로 등록 가능합니다.");
		file.value = "";
		return;
	}
}

function eventProcIn() {
	var frm = document.frmEvent;
	var betitle = frm.be_title.value;
	var beimg = frm.be_img.value;
	var becontent = frm.be_content.value;
	
	var sdate = frm.sdate.value;
	var edate = frm.edate.value;
	
	if (sdate > edate) {
		alert("시작일이 종료일보다 늦습니다.");	return;
	}
	
	if (betitle == "" || beimg == "" || becontent == "") {
		alert("모든 항목을 선택하여 주세요.");
		return;
	}
	
	frm.action = "event_proc_in";
	frm.method = "post";
	frm.submit();
}
</script>
<div id="page-contents">
	<h2>이벤트 등록</h2>
	<br />
	<div class="frm_frame">
		<form name="frmEvent" enctype="multipart/form-data">
			<input type="hidden" name="aiidx" value="" />
			<div><input type="text" name="be_title" id="be_title" placeholder="제목을 입력해 주세요." /></div><br />
			<div><input type="file" name="be_img" id="be_img" accept="image/*" onchange="validation(this);" /></div><br />
			<div>
				이벤트 기간 : 시작일&nbsp;&nbsp;
				<input type="date" name="sdate" id="sdate" min="<%=today %>" value="" />
				&nbsp;&nbsp;~&nbsp;
				종료일&nbsp;&nbsp;
				<input type="date" name="edate" id="edate" min="<%=today %>" value="" />
			</div><br/>
			<textarea name="be_content" id="be_content" placeholder="내용을 입력해주세요."></textarea><br/><br/>
			<div id="btn_box">
				<input type="button" id="cancel" value="취소" onclick="location.href='event_list';" />&nbsp;
				<input type="button" id="btnsch" value="등록" onclick="eventProcIn();" />
			</div>
		</form>
	</div>
</div>
</body>
</html>
