<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_inc/inc_head.jsp"%>
<%
request.setCharacterEncoding("utf-8");
ArrayList<MemberInfo> memberList = (ArrayList<MemberInfo>)request.getAttribute("memberList");
PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
int bsize = pageInfo.getBsize(), cpage = pageInfo.getCpage();
int psize = pageInfo.getPsize(), pcnt = pageInfo.getPcnt();
int spage = pageInfo.getSpage(), rcnt = pageInfo.getRcnt();
String schargs = "";
String uid = request.getParameter("uid");
if (uid == null) {
	uid = "";
} else {
	schargs += "&uid=" + uid;
}
String name = request.getParameter("name");
if (name == null) {
	name = "";
} else {
	schargs += "&name=" + name;
}
String p1 = request.getParameter("p1");
if (p1 == null) {
	p1 = "";
} else {
	schargs += "&p1=" + p1;
}
String p2 = request.getParameter("p2");
if (p2 == null) {
	p2 = "";
} else {
	schargs += "&p2=" + p2;
}
String p3 = request.getParameter("p3");
if (p3 == null) {
	p3 = "";
} else {
	schargs += "&p3=" + p3;
}
String gender = request.getParameter("gender");
if (gender == null) {
	gender = "";
} else {
	schargs += "&gender=" + gender;
}
String status = request.getParameter("status");
if (status == null)	{
	status = "";
} else {
	schargs += "&status=" + status;
}

%>
<style>
tr {
	height: 50px;
}

th {
	font-size: 20px;
}
hr {
	margin: 10px 0;
}

#page-contents {
	width: 1000px;
	margin: 50px 0 50px 500px;
	display: flex;

}

#page-contents h2 {
	margin:30px 0;
	font-size: 2.3em;
	
}
.schForm {
	display: flex;
	flex-direction: column;
    align-items: center;
	margin-left:20px;
}


.schBox {
	padding:20px;
	border:1px solid #000;
	border-radius: 5px;
}
.schBtn {
	width:200px;
	height:30px;
	font-size:20px;
	margin:10px 0;
}

.schName {
	margin:10px;
}
.phone {
	width:50px;
}
.page {
	text-align: center;
}

</style>
<script>
	function onlyNum(obj) {
		if (isNaN(obj.value)) {
			obj.value = "";
		}
	}
</script>
	<div id="page-contents">
		<div>
			<h2>회원 관리</h2>
			<table width="1000" align="center" border="1" cellpadding="3" cellspacing="0">
				<tr>
					<th width="20%">아이디</th>
					<th width="15%">이름</th>
					<th width="15%">생년월일</th>
					<th width="10%">성별</th>
					<th width="*">연락처</th>
					<th width="15%">상태</th>
				</tr>
				<input type="hidden" name="piid" value="" />
<%
	if (memberList.size() > 0) {
	for (int i = 0; i < memberList.size(); i++) {
		MemberInfo memberInfo = memberList.get(i);
		
		
		String mStatus = memberInfo.getMi_status();
		switch (mStatus) {
	case "a":
		mStatus = "정상";
		break;
	case "b":
		mStatus = "탈퇴";
		break;
		}
%>

				<tr align="center">
					<td><a href="member_view?miid=<%=memberInfo.getMi_id() %>"><%=memberInfo.getMi_id() %></a></td>
					<td><%=memberInfo.getMi_name() %></td>
					<td><%=memberInfo.getMi_birth() %></td>
					<td><%=memberInfo.getMi_gender() %></td>
					<td><%=memberInfo.getMi_phone() %></td>
					<td><%=mStatus %></td>
				</tr>
<%}
	}%>
			</table>
			<table width="100%" cellpadding="5" class="page">
			<tr>
			<td width="80%">
<%
if (rcnt > 0) {	// 게시글이 있으면 - 페이지 영역을 보여줌
	String lnk = "member_list?cpage=";
	pcnt = rcnt / psize;
	if (rcnt % psize > 0)	pcnt++;	// 전체 페이지 수(마지막 페이지 번호) 
	
	if (cpage == 1) {
		out.println("[처음]&nbsp;&nbsp;&nbsp;[이전]&nbsp;&nbsp;");
	} else {
		out.println("<a href='" + lnk + 1 + "'>[처음]</a>&nbsp;&nbsp;&nbsp;");
		out.println("<a href='" + lnk + (cpage - 1) + schargs + "'>[이전]</a>&nbsp;&nbsp;");
	}
	
	spage = (cpage - 1) / bsize * bsize + 1;	// 현재 블록에서의 시작 페이지 번호
	for (int i = 1, j = spage ; i <= bsize && j <= pcnt ; i++, j++) {
	// i : 블록에서 보여줄 페이지의 개수만큼 루프를 돌릴 조건으로 사용되는 변수
	// j : 실제 출력할 페이지 번호로 전체 페이지 개수(마지막 페이지 번호)를 넘지 않게 사용해야 함
		if (cpage == j) {
			out.println("&nbsp;<strong>" + j + "</strong>&nbsp;");
		} else {
			out.print("&nbsp;<a href='" + lnk + schargs + j +"'>");
			out.println(j + "</a>&nbsp;");
		}
	}
	
	if (cpage == pcnt) {
		out.println("&nbsp;&nbsp;[다음]&nbsp;&nbsp;&nbsp;[마지막]");
	} else {
		out.println("&nbsp;&nbsp;<a href='" + lnk + (cpage + 1) + schargs + "'>[다음]</a>");
		out.println("&nbsp;&nbsp;&nbsp;<a href='" + lnk + pcnt + schargs + "'>[마지막]</a>");
	}
}
%>
			</td>
			</tr>
			</table>
		</div>
		<div>
			<form class="schForm" name="frm">
				<div class="schBox" id="chkBox">
					<p class="schName">아이디</p>
					<input type="text" name="uid" placeholder="아이디" value="<%=uid %>" />
					<hr />
					<p class="schName">이름</p>
					<input type="text" name="name" placeholder="이름" value="<%=name %>" />
					<hr />
					<p class="schName">성별</p>
					<select name="gender">
						<option value="" <% if(gender.equals("")) { %>selected="selected"<% } %>>성별을 선택하세요.</option>
						<option value="남" <% if(gender.equals("남")) { %>selected="selected"<% } %>>남</option>
						<option value="여" <% if(gender.equals("여")) { %>selected="selected"<% } %>>여</option>
					</select>
					<hr />
					<p class="schName">전화번호</p>
					<select name="p1">
						<option value="" <% if (p1.equals("")) { %>selected="selected"<% } %>>선택</option>
						<option value="010" <% if (p1.equals("010")) { %>selected="selected"<% } %>>010</option>
						<option value="011" <% if (p1.equals("011")) { %>selected="selected"<% } %>>011</option>
						<option value="016" <% if (p1.equals("016")) { %>selected="selected"<% } %>>016</option>
						<option value="019" <% if (p1.equals("019")) { %>selected="selected"<% } %>>019</option>
					</select> - 
					<input type="text" name="p2" class="phone" placeholder="0000" onkeyup="onlyNum(this);" value="<%=p2 %>" /> - 
					<input type="text" name="p3" class="phone" placeholder="0000" onkeyup="onlyNum(this);" value="<%=p3 %>" />
					<hr />
					<p class="schName">회원 상태</p>
					<select name="status">
						<option value="" <% if(status.equals("")) { %>selected="selected"<% } %>>상태를 선택하세요.</option>
						<option value="a" <% if(status.equals("a")) { %>selected="selected"<% } %>>정상</option>
						<option value="b" <% if(status.equals("b")) { %>selected="selected"<% } %>>탈퇴</option>
					</select>
				</div>
				<p></p>
				<input type="submit" class="schBtn" value="적용하기" />
			</form>
		</div>
	</div>
</body>
</html>
