<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.time.*" %>
<%@ include file="../_inc/inc_head.jsp" %>
<%
request.setCharacterEncoding("utf-8");
int year = LocalDate.now().getYear();
if (request.getParameter("year") != null)		year = Integer.parseInt(request.getParameter("year")); 
HashMap<String, Integer> orderBuyList = (HashMap<String, Integer>)request.getAttribute("orderBuyList");
HashMap<String, Integer> orderSellList = (HashMap<String, Integer>)request.getAttribute("orderSellList");
String[] months = {"1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"};
String dataA = "[";
for (int i = 0; i < 6; i++) {
	dataA += orderBuyList.getOrDefault(months[i], 0) + ", ";
	if (i == 5)		dataA += orderBuyList.getOrDefault(months[i], 0) + "]";
}
String dataB = "[";
for (int i = 0; i < 6; i++) {
	dataB += orderSellList.getOrDefault(months[i], 0) + ", ";
	if (i == 5)		dataB += orderSellList.getOrDefault(months[i], 0) + "]";
}
%>
<link rel="stylesheet" href="src/css/chart_order.css">
<script src="src/js/Chart.min.js"></script>
<script src="src/js/utils.js"></script>
<script src="src/js/jquery-3.6.1.js"></script>
<script>
// 1 = 1st half, 2 = 2nd half 
var color = Chart.helpers.color;
var barChartData1 = {
	labels: ['1월', '2월', '3월', '4월', '5월', '6월'],
	datasets: [{
		label: '구매',
		backgroundColor: color('rgb(255, 255, 255)').alpha(0).rgbString(),
		borderColor: 'rgba(255, 102, 204, 0.5)',
		borderWidth: 1,
		data: <%=dataA %>
	}, {
		label: '판매',
		backgroundColor: color('rgb(255, 255, 255)').alpha(0).rgbString(),
		borderColor: 'rgba(132, 204, 255, 0.5)',
		borderWidth: 1,
		data: <%=dataB %>
	}]
};
<%
dataA = "[";
for (int i = 6; i < months.length; i++) {
	dataA += orderBuyList.getOrDefault(months[i], 0) + ", ";
	if (i == 11)		dataA += orderBuyList.getOrDefault(months[i], 0) + "]";
}
dataB = "[";
for (int i = 6; i < months.length; i++) {
	dataB += orderSellList.getOrDefault(months[i], 0) + ", ";
	if (i == 11)		dataB += orderSellList.getOrDefault(months[i], 0) + "]";
}
%>
var barChartData2 = {
	labels: ['7월', '8월', '9월', '10월', '11월', '12월'],
	datasets: [{
		label: '구매',
		backgroundColor: color('rgb(255, 255, 255)').alpha(0).rgbString(),
		borderColor: 'rgba(255, 102, 204, 0.5)',
		borderWidth: 1,
		data: <%=dataA %>
	}, {
		label: '판매',
		backgroundColor: color('rgb(255, 255, 255)').alpha(0).rgbString(),
		borderColor: 'rgba(132, 204, 255, 0.5)',
		borderWidth: 1,
		data: <%=dataB %>
	}]
};

window.onload = function() {
    chartDraw(1);
	var legendDiv = document.getElementById('legend-div');
	legendDiv.innerHTML = window.myBar1.generateLegend();
};

function chartDraw(num) {
	$('#canvas').remove();
	$('#container').append('<canvas id="canvas"></canvas>');
    var ctx = document.getElementById('canvas').getContext('2d');
	var half = '상';
	if (num == 2) {
		half = '하';
	}
    window.myBar1 = new Chart(ctx, {
		type: 'line',
		data: eval('barChartData' + num),
		options: {
			responsive: true,
			legend:{ 
				display: false
			}, 
			legendCallback: function(chart) {
				let legend = document.createElement('div');
				legend.id = "legend-wrap"
				legend.innerHTML += `<div class="normal-wrap">
										<div class="box-normal" style="background-color: rgba(255, 102, 204, 0.5)"></div>
										<div class="value-normal">구매</div>
									</div>
                                    <div class="normal-wrap">
										<div class="box-normal" style="background-color: rgba(132, 204, 255, 0.5)"></div>
										<div class="value-normal">판매</div>
									</div>`
				return legend.outerHTML
			}, 
			title:{ 
				display:true, 
				text: half + '반기 월 별 거래량', 
				fontSize: 25
			}, 
			scales: {
				xAxes: [{
					gridLines: {
						display: false,
					}, 
					ticks: {
						fontSize: 25
					}
				}],
				yAxes: [{
					display: true,
					ticks: {
						fontSize: 25,
						beginAtZero: true   // minimum value will be 0.
					}
				}]
			}
		}
	});
}

function change() {
	chartDraw($('input:radio[name=h]:checked').val());
}

</script>
    <div class="page-contents">
        <div class="content-wrapper">
			<div class="select-wrapper">
				<form name="sch">
					<select name="year" onchange="this.form.submit();">
						<option value="2023" <%if (year == 2025) { %>selected="selected"<% } %>>2025년</option>
						<option value="2023" <%if (year == 2024) { %>selected="selected"<% } %>>2024년</option>
						<option value="2023" <%if (year == 2023) { %>selected="selected"<% } %>>2023년</option>
						<option value="2022" <%if (year == 2022) { %>selected="selected"<% } %>>2022년</option>
						<option value="2021" <%if (year == 2021) { %>selected="selected"<% } %>>2021년</option>
						<option value="2020" <%if (year == 2020) { %>selected="selected"<% } %>>2020년</option>
					</select>
				</form>
			</div>
			<div class="radio-wrapper">
				<input type="radio" name="h" value="1" onclick="change();" checked="checked" />
				<label>상반기</label>
				<input type="radio" name="h" value="2" onclick="change();" />
				<label>하반기</label>
			</div>
			<div id="container" class="container">
				<div id="legend-div" class="legend-div"></div>
				<canvas id="canvas"></canvas>
			</div>
			<div class="unit">
				<p>단위 : 대</p>
			</div>
        </div>
    </div>
</body>
</html>
