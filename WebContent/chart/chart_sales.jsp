<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.time.*" %>
<%@ include file="../_inc/inc_head.jsp" %>
<%
request.setCharacterEncoding("utf-8");
int year = LocalDate.now().getYear();
if (request.getParameter("year") != null)		year = Integer.parseInt(request.getParameter("year")); 
HashMap<String, Integer> chartSalesList = (HashMap<String, Integer>)request.getAttribute("chartSalesList");
String data = "[";
double sales = 0.0;
if (chartSalesList.getOrDefault("1월", 0) > 0)		sales = Math.round(chartSalesList.getOrDefault("1월", 0) / 100000) / 10.0;
data += sales + ", ";
if (chartSalesList.getOrDefault("2월", 0) > 0)		sales = Math.round(chartSalesList.getOrDefault("2월", 0) / 100000) / 10.0;
else												sales = 0.0;
data += sales + ", ";
if (chartSalesList.getOrDefault("3월", 0) > 0)		sales = Math.round(chartSalesList.getOrDefault("3월", 0) / 100000) / 10.0;
else												sales = 0.0;
data += sales + ", ";
if (chartSalesList.getOrDefault("4월", 0) > 0)		sales = Math.round(chartSalesList.getOrDefault("4월", 0) / 100000) / 10.0;
else												sales = 0.0;
data += sales + ", ";
if (chartSalesList.getOrDefault("5월", 0) > 0)		sales = Math.round(chartSalesList.getOrDefault("5월", 0) / 100000) / 10.0;
else												sales = 0.0;
data += sales + ", ";
if (chartSalesList.getOrDefault("6월", 0) > 0)		sales = Math.round(chartSalesList.getOrDefault("6월", 0) / 100000) / 10.0;
else												sales = 0.0;
data += sales + "]";

%>
<link rel="stylesheet" href="src/css/chart_sales.css">
<script src="src/js/Chart.min.js"></script>
<script src="src/js/utils.js"></script>
<script src="src/js/jquery-3.6.1.js"></script>
<script>
// 1 = 1st half, 2 = 2nd half
var color = Chart.helpers.color;
var barChartData1 = {
	labels: ['1월', '2월', '3월', '4월', '5월', '6월'],
	datasets: [{
		label: '매출액',
		backgroundColor: color('rgb(255, 102, 204)').alpha(0.5).rgbString(),
		borderColor: 'rgba(255, 102, 204, 0.5)',
		borderWidth: 1,
		data: <%=data %>
	}]
};

<%
data = "[";
sales = 0.0;
if (chartSalesList.getOrDefault("7월", 0) > 0)		sales = Math.round(chartSalesList.getOrDefault("7월", 0) / 100000) / 10.0;
data += sales + ", ";
if (chartSalesList.getOrDefault("8월", 0) > 0)		sales = Math.round(chartSalesList.getOrDefault("8월", 0) / 100000) / 10.0;
else												sales = 0.0;
data += sales + ", ";
if (chartSalesList.getOrDefault("9월", 0) > 0)		sales = Math.round(chartSalesList.getOrDefault("9월", 0) / 100000) / 10.0;
else												sales = 0.0;
data += sales + ", ";
if (chartSalesList.getOrDefault("10월", 0) > 0)		sales = Math.round(chartSalesList.getOrDefault("10월", 0) / 100000) / 10.0;
else												sales = 0.0;
data += sales + ", ";
if (chartSalesList.getOrDefault("11월", 0) > 0)		sales = Math.round(chartSalesList.getOrDefault("11월", 0) / 100000) / 10.0;
else												sales = 0.0;
data += sales + ", ";
if (chartSalesList.getOrDefault("12월", 0) > 0)		sales = Math.round(chartSalesList.getOrDefault("12월", 0) / 100000) / 10.0;
else												sales = 0.0;
data += sales + "]";
%>

var barChartData2 = {
	labels: ['7월', '8월', '9월', '10월', '11월', '12월'],
	datasets: [{
		label: '매출액',
		backgroundColor: color('rgb(255, 102, 204)').alpha(0.5).rgbString(),
		borderColor: 'rgba(255, 102, 204, 0.5)',
		borderWidth: 1,
		data: <%=data %>
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
		type: 'bar',
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
										<div class="value-normal">매출액</div>
									</div>`
				return legend.outerHTML
			}, 
			title:{ 
				display:true, 
				text: half + '반기 월 별 총 매출', 
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
						<option value="2025" <%if (year == 2025) { %>selected="selected"<% } %>>2025년</option>
						<option value="2024" <%if (year == 2024) { %>selected="selected"<% } %>>2024년</option>
						<option value="2023" <%if (year == 2023) { %>selected="selected"<% } %>>2023년</option>
						<option value="2022" <%if (year == 2022) { %>selected="selected"<% } %>>2022년</option>
						<option value="2021" <%if (year == 2021) { %>selected="selected"<% } %>>2021년</option>
						<option value="2020" <%if (year == 2020) { %>selected="selected"<% } %>>2020년</option>
					</select>
				</form>
			</div>
            <div class="radios">
                <div class="radio-wrapper">
                    <input type="radio" name="h" value="1" onclick="change();" checked="checked" />
                    <label>상반기</label>
                    <input type="radio" name="h" value="2" onclick="change();" />
                    <label>하반기</label>
                </div>
            </div>
			<div id="container" class="container">
				<div id="legend-div" class="legend-div"></div>
				<canvas id="canvas"></canvas>
			</div>
			<div class="unit">
				<p>단위 : 백만원</p>
			</div>
        </div>
    </div>
</body>
</html>
