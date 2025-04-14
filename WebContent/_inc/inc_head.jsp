<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.time.*" %>
<%@ page import="vo.*" %>
<% String uri = (String)request.getAttribute("uri");
if (uri == null)	uri = "";
AdminInfo loginInfo = (AdminInfo) session.getAttribute("loginInfo");
String adminName = loginInfo.getAi_name();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" href="/src/css/inc_head.css">
<%--    <link rel="stylesheet" href="<%=request.getContextPath()%>src/css/inc_head.css">--%>
    <script src="../src/js/jquery-3.6.1.js"></script>
</head>
<body>
    <div class="header">
        <div class="nav">
            <div class="menu">
                <a href="sell_list"><h3>상품 관리</h3></a>
                <div class="sub <% if (uri.equals("sell_list") || uri.equals("sell_view") || uri.equals("non_confirm_sell_list") || uri.equals("product_list") || uri.equals("product_form") || uri.equals("buy_list") || uri.equals("buy_form") || uri.equals("product_stock_list")) { %>active<% } %>">
                    <a href="sell_list"><p <% if (uri.equals("sell_list") || uri.equals("sell_view") || uri.equals("non_confirm_sell_list")) { %>class="active-p"<% } %>>주문 관리</p></a>
                    <div class="sub-sub">
                        <a href="sell_list"><p <% if (uri.equals("sell_list")) { %>class="active-p"<% } %>> - 전체 주문</p></a>
                        <a href="non_confirm_sell_list"><p <% if (uri.equals("non_confirm_sell_list")) { %>class="active-p"<% } %>> - 장기간 미확정 주문</p></a>
                    </div>
                    <a href="product_list"><p <% if (uri.equals("product_list") || uri.equals("product_form")) { %>class="active-p"<% } %>>판매중인 상품 관리</p></a>
                    <a href="buy_list"><p <% if (uri.equals("buy_list") || uri.equals("buy_form")) { %>class="active-p"<% } %>>구매중인 상품 관리</p></a>
                    <a href="product_stock_list"><p <% if (uri.equals("product_stock_list")) { %>class="active-p"<% } %>>상품 재고 관리</p></a>
                </div>
            </div>
            <div class="menu">
                <a href="member_list"><h3>회원 관리</h3></a>
                <div class="sub <% if (uri.equals("member_list")) { %>active<% } %>">
                    <a href="member_list"><p <% if (uri.equals("member_list")) { %>class="active-p"<% } %>>회원 관리</p></a>
                </div>
            </div>
            <div class="menu">
                <a href="notice_list"><h3>게시판 관리</h3></a>
                <div class="sub <% if (uri.equals("notice_list") || uri.equals("review_list") || uri.equals("event_list")) { %>active<% } %>">
                    <a href="notice_list"><p <% if (uri.equals("notice_list")) { %>class="active-p"<% } %>>공지사항</p></a>
                    <a href="event_list"><p <% if (uri.equals("event_list")) { %>class="active-p"<% } %>>이벤트</p></a>
                    <a href="review_list"><p <% if (uri.equals("review_list")) { %>class="active-p"<% } %>>리뷰</p></a>
                    <a href="free_list"><p <% if (uri.equals("free_list")) { %>class="active-p"<% } %>>자유 게시판</p></a>
                    <a href="member_question_list"><p <% if (uri.equals("member_question_list")) { %>class="active-p"<% } %>>1 : 1 문의</p></a>
                    <div class="sub-sub <% if (!uri.equals("") && uri.indexOf("member_question") >= 0) { %>active<% } %>">
                        <a href="member_question_list"><p <% if (uri.equals("member_question_list")) { %>class="active-p"<% } %>> - 전체 문의</p></a>
                        <a href="member_question_na_list"><p<% if (uri.equals("member_question_na_list")) { %>class="active-p"<% } %>> - 답변 미 등록 문의</p></a>
                    </div>
                </div>
            </div>
            <div class="menu">
                <a href="chart_sales"><h3>통계</h3></a>
                <div class="sub <% if (!uri.equals("") && uri.indexOf("chart") >= 0) { %>active<% } %>">
                    <a href="chart_sales"><p <% if (uri.equals("chart_sales") || uri.equals("chart_profit")) { %>class="active-p"<% } %>>매출 통계</p></a>
                    <div class="sub-sub">
                        <a href="chart_sales"><p <% if (uri.equals("chart_sales")) { %>class="active-p"<% } %>> - 총 매출</p></a>
                        <a href="chart_profit"><p <% if (uri.equals("chart_profit")) { %>class="active-p"<% } %>> - 순 수익</p></a>
                    </div>
                    <a href="chart_brand"><p <% if (uri.equals("chart_brand") || uri.equals("chart_series") || uri.equals("chart_model")) { %>class="active-p"<% } %>>거래 통계</p></a>
                    <div class="sub-sub">
                        <a href="chart_brand"><p <% if (uri.equals("chart_brand")) { %>class="active-p"<% } %>> - 브랜드별</p></a>
                        <a href="chart_series"><p <% if (uri.equals("chart_series")) { %>class="active-p"<% } %>> - 시리즈별</p></a>
                        <a href="chart_model"><p <% if (uri.equals("chart_model")) { %>class="active-p"<% } %>> - 모델별</p></a>
                    </div>
                    <a href="chart_order"><p <% if (uri.equals("chart_order")) { %>class="active-p"<% } %>>월 별 거래량</p></a>
                </div>
            </div>
        </div>
    </div>
    <div class="login">
        <p><%=adminName %> 님 환영합니다.</p>
        <input type="button" value="로그아웃" onclick="javascript:location.href='logout.jsp'" />
    </div>