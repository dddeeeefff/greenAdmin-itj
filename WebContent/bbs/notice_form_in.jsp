<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_inc/inc_head.jsp" %>
<style>
#page-contents {
	width:1000px;	margin:50px 0 50px 500px;
}
.frm_frame{ border:1px solid black; padding:15px; }
.addTitle{ width:600px; height:30px; padding:5px; }
.addFile{ cursor:pointer; }
.writeContent{ width:100%; height:400px; vertical-align:top; padding:5px; }
h2{ text-align:center; font-size:30px; }
.btn_box{ display:flex; justify-content: flex-end; }
.cancel, .regist { width:80px; height:30px; cursor:pointer; }
.cancel{ background:white; }
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
	
	function notice_submit(){
		var frm = document.frm;
		var title = frm.addTitle.value;
		var file = frm.addFile.value;
		
		content = $("#writeContent").val();
		frm.addContent.value = content;	

		if (title == "" || file == "" || content == ""){
			alert("모든 항목을 입력해 주세요.")
			return;
		}
		if (confirm("공지사항을 등록하시겠습니까?")) {
			frm.action = "notice_proc_in";
			frm.method = "post";
			frm.submit();
		}
	}
</script>
<div id="page-contents">
	<h2>공지사항 등록</h2>
	<br /><br />
	<div class="frm_frame">
		<form name="frm" enctype="multipart/form-data">
			<div><input type="text" name="addTitle" class="addTitle" id = "addTitle" placeholder="제목을 입력해주세요." /></div><br />
			<div><input type="file" name="addFile" class="addFile" id = "addFile" accept="notice_img/*" onchange="validation(this);" /></div><br />
			<div>
				<input type="hidden" name="addContent" id="addContent" />
				<textarea name="writeContent" class='writeContent' id="writeContent" placeholder="내용을 입력해주세요."></textarea>
			</div>
			<br />
			<div class="btn_box">
				<input class="cancel" type="button" value="취소" onclick="location.href='notice_list';" />&nbsp;
				<input class="regist" type="button" onclick="notice_submit();" value="등록" />
			</div>
		</form>
	</div>
</div>
</body>
</html>