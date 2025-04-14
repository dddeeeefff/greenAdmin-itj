<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.time.*" %>
<%@ include file="../_inc/inc_head.jsp" %>
<%
request.setCharacterEncoding("utf-8");
int year = LocalDate.now().getYear();
if (request.getParameter("year") != null)		year = Integer.parseInt(request.getParameter("year"));
HashMap<String, HashMap<String, Integer>> buySeriesList = (HashMap<String, HashMap<String, Integer>>)request.getAttribute("buySeriesList");
HashMap<String, HashMap<String, Integer>> sellSeriesList = (HashMap<String, HashMap<String, Integer>>)request.getAttribute("sellSeriesList");
HashMap<String, Integer> series1 = buySeriesList.get("s23");
HashMap<String, Integer> series2 = buySeriesList.get("s22");
HashMap<String, Integer> series3 = buySeriesList.get("s21");
HashMap<String, Integer> series4 = buySeriesList.get("s20");
String[] months = {"1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"};
String data1 = "[";
String data2 = "[";
String data3 = "[";
String data4 = "[";
for (int i = 0; i < 6; i++) {
	data1 += series1.getOrDefault(months[i], 0) + ", ";
	data2 += series2.getOrDefault(months[i], 0) + ", ";
	data3 += series3.getOrDefault(months[i], 0) + ", ";
	data4 += series4.getOrDefault(months[i], 0) + ", ";
	if (i == 5) {
		data1 += series1.getOrDefault(months[i], 0) + "]";
		data2 += series2.getOrDefault(months[i], 0) + "]";
		data3 += series3.getOrDefault(months[i], 0) + "]";
		data4 += series4.getOrDefault(months[i], 0) + "]";
	}
}
%>
<link rel="stylesheet" href="/src/css/chart_series.css">
<script src="/src/js/Chart.min.js"></script>
<script src="/src/js/utils.js"></script>
<script src="/src/js/jquery-3.6.1.js"></script>
<script>
// 1 = buy / 1st half / 삼성, 2 = buy / 2nd half / 삼성, 3 = buy / 1st half / 애플, 4 = buy / 2nd half / 애플
// 5 = sell / 1st half / 삼성, 6 = sell / 2nd half / 삼성, 7 = sell / 1st half / 애플, 8 = sell / 2nd half / 애플 
var color = Chart.helpers.color;
var barChartData1 = {
	labels: ['1월', '2월', '3월', '4월', '5월', '6월'],
	datasets: [{
		label: 'S23 시리즈',
		backgroundColor: color('rgb(255, 102, 204)').alpha(0.5).rgbString(),
		borderColor: 'rgba(255, 102, 204, 0.5)',
		borderWidth: 1,
		data: <%=data1 %>
	}, {
		label: 'S22 시리즈',
		backgroundColor: color('rgb(132, 204, 255)').alpha(0.5).rgbString(),
		borderColor: 'rgba(132, 204, 255, 0.5)',
		borderWidth: 1,
		data: <%=data2 %>
	}, {
		label: 'S21 시리즈',
		backgroundColor: color('rgb(153, 255, 153)').alpha(0.5).rgbString(),
		borderColor: 'rgba(153, 255, 153, 0.5)',
		borderWidth: 1,
		data: <%=data3 %>
	}, {
		label: 'S20 시리즈',
		backgroundColor: color('rgb(255, 255, 102)').alpha(0.5).rgbString(),
		borderColor: 'rgba(255, 255, 102, 0.5)',
		borderWidth: 1,
		data: <%=data4 %>
	}]
};
<% 
data1 = "[";
data2 = "[";
data3 = "[";
data4 = "[";
for (int i = 6; i < months.length; i++) {
	data1 += series1.getOrDefault(months[i], 0) + ", ";
	data2 += series2.getOrDefault(months[i], 0) + ", ";
	data3 += series3.getOrDefault(months[i], 0) + ", ";
	data4 += series4.getOrDefault(months[i], 0) + ", ";
	if (i == 11) {
		data1 += series1.getOrDefault(months[i], 0) + "]";
		data2 += series2.getOrDefault(months[i], 0) + "]";
		data3 += series3.getOrDefault(months[i], 0) + "]";
		data4 += series4.getOrDefault(months[i], 0) + "]";
	}
}
%>
var barChartData2 = {
	labels: ['7월', '8월', '9월', '10월', '11월', '12월'],
	datasets: [{
		label: 'S23 시리즈',
		backgroundColor: color('rgb(255, 102, 204)').alpha(0.5).rgbString(),
		borderColor: 'rgba(255, 102, 204, 0.5)',
		borderWidth: 1,
		data: <%=data1 %>
	}, {
		label: 'S22 시리즈',
		backgroundColor: color('rgb(132, 204, 255)').alpha(0.5).rgbString(),
		borderColor: 'rgba(132, 204, 255, 0.5)',
		borderWidth: 1,
		data: <%=data2 %>
	}, {
		label: 'S21 시리즈',
		backgroundColor: color('rgb(153, 255, 153)').alpha(0.5).rgbString(),
		borderColor: 'rgba(153, 255, 153, 0.5)',
		borderWidth: 1,
		data: <%=data3 %>
	}, {
		label: 'S20 시리즈',
		backgroundColor: color('rgb(255, 255, 102)').alpha(0.5).rgbString(),
		borderColor: 'rgba(255, 255, 102, 0.5)',
		borderWidth: 1,
		data: <%=data4 %>
	}]
};
<%
data1 = "[";
data2 = "[";
data3 = "[";
data4 = "[";
series1 = buySeriesList.get("a14");
series2 = buySeriesList.get("a13");
series3 = buySeriesList.get("a12");
series4 = buySeriesList.get("a11");

for (int i = 0; i < 6; i++) {
	data1 += series1.getOrDefault(months[i], 0) + ", ";
	data2 += series2.getOrDefault(months[i], 0) + ", ";
	data3 += series3.getOrDefault(months[i], 0) + ", ";
	data4 += series4.getOrDefault(months[i], 0) + ", ";
	if (i == 5) {
		data1 += series1.getOrDefault(months[i], 0) + "]";
		data2 += series2.getOrDefault(months[i], 0) + "]";
		data3 += series3.getOrDefault(months[i], 0) + "]";
		data4 += series4.getOrDefault(months[i], 0) + "]";
	}
}
%>
var barChartData3 = {
	labels: ['1월', '2월', '3월', '4월', '5월', '6월'],
	datasets: [{
		label: '14 시리즈',
		backgroundColor: color('rgb(255, 102, 204)').alpha(0.5).rgbString(),
		borderColor: 'rgba(255, 102, 204, 0.5)',
		borderWidth: 1,
		data: <%=data1 %>
	}, {
		label: '13 시리즈',
		backgroundColor: color('rgb(132, 204, 255)').alpha(0.5).rgbString(),
		borderColor: 'rgba(132, 204, 255, 0.5)',
		borderWidth: 1,
		data: <%=data2 %>
	}, {
		label: '12 시리즈',
		backgroundColor: color('rgb(153, 255, 153)').alpha(0.5).rgbString(),
		borderColor: 'rgba(153, 255, 153, 0.5)',
		borderWidth: 1,
		data: <%=data3 %>
	}, {
		label: '11 시리즈',
		backgroundColor: color('rgb(255, 255, 102)').alpha(0.5).rgbString(),
		borderColor: 'rgba(255, 255, 102, 0.5)',
		borderWidth: 1,
		data: <%=data4 %>
	}]
};
<%
data1 = "[";
data2 = "[";
data3 = "[";
data4 = "[";
for (int i = 6; i < months.length; i++) {
	data1 += series1.getOrDefault(months[i], 0) + ", ";
	data2 += series2.getOrDefault(months[i], 0) + ", ";
	data3 += series3.getOrDefault(months[i], 0) + ", ";
	data4 += series4.getOrDefault(months[i], 0) + ", ";
	if (i == 11) {
		data1 += series1.getOrDefault(months[i], 0) + "]";
		data2 += series2.getOrDefault(months[i], 0) + "]";
		data3 += series3.getOrDefault(months[i], 0) + "]";
		data4 += series4.getOrDefault(months[i], 0) + "]";
	}
}
%>
var barChartData4 = {
	labels: ['7월', '8월', '9월', '10월', '11월', '12월'],
	datasets: [{
		label: '14 시리즈',
		backgroundColor: color('rgb(255, 102, 204)').alpha(0.5).rgbString(),
		borderColor: 'rgba(255, 102, 204, 0.5)',
		borderWidth: 1,
		data: <%=data1 %>
	}, {
		label: '13 시리즈',
		backgroundColor: color('rgb(132, 204, 255)').alpha(0.5).rgbString(),
		borderColor: 'rgba(132, 204, 255, 0.5)',
		borderWidth: 1,
		data: <%=data2 %>
	}, {
		label: '12 시리즈',
		backgroundColor: color('rgb(153, 255, 153)').alpha(0.5).rgbString(),
		borderColor: 'rgba(153, 255, 153, 0.5)',
		borderWidth: 1,
		data: <%=data3 %>
	}, {
		label: '11 시리즈',
		backgroundColor: color('rgb(255, 255, 102)').alpha(0.5).rgbString(),
		borderColor: 'rgba(255, 255, 102, 0.5)',
		borderWidth: 1,
		data: <%=data4 %>
	}]
};
<%
data1 = "[";
data2 = "[";
data3 = "[";
data4 = "[";
series1 = sellSeriesList.get("s23");
series2 = sellSeriesList.get("s22");
series3 = sellSeriesList.get("s21");
series4 = sellSeriesList.get("s20");
for (int i = 0; i < 6; i++) {
	data1 += series1.getOrDefault(months[i], 0) + ", ";
	data2 += series2.getOrDefault(months[i], 0) + ", ";
	data3 += series3.getOrDefault(months[i], 0) + ", ";
	data4 += series4.getOrDefault(months[i], 0) + ", ";
	if (i == 5) {
		data1 += series1.getOrDefault(months[i], 0) + "]";
		data2 += series2.getOrDefault(months[i], 0) + "]";
		data3 += series3.getOrDefault(months[i], 0) + "]";
		data4 += series4.getOrDefault(months[i], 0) + "]";
	}
}
%>
var barChartData5 = {
	labels: ['1월', '2월', '3월', '4월', '5월', '6월'],
	datasets: [{
		label: 'S23 시리즈',
		backgroundColor: color('rgb(255, 102, 204)').alpha(0.5).rgbString(),
		borderColor: 'rgba(255, 102, 204, 0.5)',
		borderWidth: 1,
		data: <%=data1 %>
	}, {
		label: 'S22 시리즈',
		backgroundColor: color('rgb(132, 204, 255)').alpha(0.5).rgbString(),
		borderColor: 'rgba(132, 204, 255, 0.5)',
		borderWidth: 1,
		data: <%=data2 %>
	}, {
		label: 'S21 시리즈',
		backgroundColor: color('rgb(153, 255, 153)').alpha(0.5).rgbString(),
		borderColor: 'rgba(153, 255, 153, 0.5)',
		borderWidth: 1,
		data: <%=data3 %>
	}, {
		label: 'S20 시리즈',
		backgroundColor: color('rgb(255, 255, 102)').alpha(0.5).rgbString(),
		borderColor: 'rgba(255, 255, 102, 0.5)',
		borderWidth: 1,
		data: <%=data4 %>
	}]
};
<%
data1 = "[";
data2 = "[";
data3 = "[";
data4 = "[";
for (int i = 6; i < months.length; i++) {
	data1 += series1.getOrDefault(months[i], 0) + ", ";
	data2 += series2.getOrDefault(months[i], 0) + ", ";
	data3 += series3.getOrDefault(months[i], 0) + ", ";
	data4 += series4.getOrDefault(months[i], 0) + ", ";
	if (i == 11) {
		data1 += series1.getOrDefault(months[i], 0) + "]";
		data2 += series2.getOrDefault(months[i], 0) + "]";
		data3 += series3.getOrDefault(months[i], 0) + "]";
		data4 += series4.getOrDefault(months[i], 0) + "]";
	}
}
%>
var barChartData6 = {
	labels: ['7월', '8월', '9월', '10월', '11월', '12월'],
	datasets: [{
		label: 'S23 시리즈',
		backgroundColor: color('rgb(255, 102, 204)').alpha(0.5).rgbString(),
		borderColor: 'rgba(255, 102, 204, 0.5)',
		borderWidth: 1,
		data: <%=data1 %>
	}, {
		label: 'S22 시리즈',
		backgroundColor: color('rgb(132, 204, 255)').alpha(0.5).rgbString(),
		borderColor: 'rgba(132, 204, 255, 0.5)',
		borderWidth: 1,
		data: <%=data2 %>
	}, {
		label: 'S21 시리즈',
		backgroundColor: color('rgb(153, 255, 153)').alpha(0.5).rgbString(),
		borderColor: 'rgba(153, 255, 153, 0.5)',
		borderWidth: 1,
		data: <%=data3 %>
	}, {
		label: 'S20 시리즈',
		backgroundColor: color('rgb(255, 255, 102)').alpha(0.5).rgbString(),
		borderColor: 'rgba(255, 255, 102, 0.5)',
		borderWidth: 1,
		data: <%=data4 %>
	}]
};
<%
data1 = "[";
data2 = "[";
data3 = "[";
data4 = "[";
series1 = sellSeriesList.get("a14");
series2 = sellSeriesList.get("a13");
series3 = sellSeriesList.get("a12");
series4 = sellSeriesList.get("a11");

for (int i = 0; i < 6; i++) {
	data1 += series1.getOrDefault(months[i], 0) + ", ";
	data2 += series2.getOrDefault(months[i], 0) + ", ";
	data3 += series3.getOrDefault(months[i], 0) + ", ";
	data4 += series4.getOrDefault(months[i], 0) + ", ";
	if (i == 5) {
		data1 += series1.getOrDefault(months[i], 0) + "]";
		data2 += series2.getOrDefault(months[i], 0) + "]";
		data3 += series3.getOrDefault(months[i], 0) + "]";
		data4 += series4.getOrDefault(months[i], 0) + "]";
	}
}
%>
var barChartData7 = {
	labels: ['1월', '2월', '3월', '4월', '5월', '6월'],
	datasets: [{
		label: '14 시리즈',
		backgroundColor: color('rgb(255, 102, 204)').alpha(0.5).rgbString(),
		borderColor: 'rgba(255, 102, 204, 0.5)',
		borderWidth: 1,
		data: <%=data1 %>
	}, {
		label: '13 시리즈',
		backgroundColor: color('rgb(132, 204, 255)').alpha(0.5).rgbString(),
		borderColor: 'rgba(132, 204, 255, 0.5)',
		borderWidth: 1,
		data: <%=data2 %>
	}, {
		label: '12 시리즈',
		backgroundColor: color('rgb(153, 255, 153)').alpha(0.5).rgbString(),
		borderColor: 'rgba(153, 255, 153, 0.5)',
		borderWidth: 1,
		data: <%=data3 %>
	}, {
		label: '11 시리즈',
		backgroundColor: color('rgb(255, 255, 102)').alpha(0.5).rgbString(),
		borderColor: 'rgba(255, 255, 102, 0.5)',
		borderWidth: 1,
		data: <%=data4 %>
	}]
};
<%
data1 = "[";
data2 = "[";
data3 = "[";
data4 = "[";
for (int i = 6; i < months.length; i++) {
	data1 += series1.getOrDefault(months[i], 0) + ", ";
	data2 += series2.getOrDefault(months[i], 0) + ", ";
	data3 += series3.getOrDefault(months[i], 0) + ", ";
	data4 += series4.getOrDefault(months[i], 0) + ", ";
	if (i == 11) {
		data1 += series1.getOrDefault(months[i], 0) + "]";
		data2 += series2.getOrDefault(months[i], 0) + "]";
		data3 += series3.getOrDefault(months[i], 0) + "]";
		data4 += series4.getOrDefault(months[i], 0) + "]";
	}
}
%>
var barChartData8 = {
	labels: ['7월', '8월', '9월', '10월', '11월', '12월'],
	datasets: [{
		label: '14 시리즈',
		backgroundColor: color('rgb(255, 102, 204)').alpha(0.5).rgbString(),
		borderColor: 'rgba(255, 102, 204, 0.5)',
		borderWidth: 1,
		data: <%=data1 %>
	}, {
		label: '13 시리즈',
		backgroundColor: color('rgb(132, 204, 255)').alpha(0.5).rgbString(),
		borderColor: 'rgba(132, 204, 255, 0.5)',
		borderWidth: 1,
		data: <%=data2 %>
	}, {
		label: '12 시리즈',
		backgroundColor: color('rgb(153, 255, 153)').alpha(0.5).rgbString(),
		borderColor: 'rgba(153, 255, 153, 0.5)',
		borderWidth: 1,
		data: <%=data3 %>
	}, {
		label: '11 시리즈',
		backgroundColor: color('rgb(255, 255, 102)').alpha(0.5).rgbString(),
		borderColor: 'rgba(255, 255, 102, 0.5)',
		borderWidth: 1,
		data: <%=data4 %>
	}]
};

window.onload = function() {
    chartDraw(1);
	var legendDiv = document.getElementById('legend-div');
	legendDiv.innerHTML = window.myBar1.generateLegend();
    $('.tab').first().addClass('active');
};

var brand = '삼성';
var series = ['S23', 'S22', 'S21', 'S20'];

$(function() {
    $('.tab').click(function() {
        $('.tab').removeClass('active');
        $(this).addClass('active');
        brand = $(this).text();
        if (brand == '삼성') {
            series = ['S23', 'S22', 'S21', 'S20'];
        } else {
            series = ['14', '13', '12', '11'];
        }
        chartDraw();
        var legendDiv = document.getElementById('legend-div');
        legendDiv.innerHTML = window.myBar1.generateLegend();
    })
});

function chartDraw() {
	$('#canvas').remove();
	$('#container').append('<canvas id="canvas"></canvas>');
    var ctx = document.getElementById('canvas').getContext('2d');
    var half = $('input:radio[name=h]:checked').val();
    var kind = $('input:radio[name=o]:checked').val();
    var halfStr = '상';
    var order = '구매';
    var num = 1;
    if (kind == 'b') {
        if (half == 1) {
            if (brand == '삼성') {
                num = 1;
            } else if (brand == '애플') {
                num = 3;
            }
        } else {
            halfStr = '하';
            if (brand == '삼성') {
                num = 2;
            } else {
                num = 4;
            }            
        }
    } else if (kind == 's') {
        order = '판매';
        if (half == 1) {
            if (brand == '삼성') {
                num = 5;
            } else {
                num = 7;
            }
        } else {
        	halfStr = '하';
            if (brand == '삼성') {
                num = 6;
            } else {
                num = 8;
            }
        }
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
										<div class="value-normal">` + series[0]+ `시리즈</div>
									</div>
                                    <div class="normal-wrap">
										<div class="box-normal" style="background-color: rgba(132, 204, 255, 0.5)"></div>
										<div class="value-normal">` + series[1] + `시리즈</div>
									</div>
                                    <div class="normal-wrap">
										<div class="box-normal" style="background-color: rgba(153, 255, 153, 0.5)"></div>
										<div class="value-normal">` + series[2] + `시리즈</div>
									</div>
                                    <div class="normal-wrap">
										<div class="box-normal" style="background-color: rgba(255, 255, 102, 0.5)"></div>
										<div class="value-normal">` + series[3] + `시리즈</div>
									</div>`
				return legend.outerHTML
			}, 
			title:{ 
				display:true, 
				text: halfStr + '반기 시리즈 별 ' + order + '량', 
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
            <div class="radios">
                <div class="radio-wrapper">
                    <input type="radio" name="o" value="b" onclick="chartDraw();" checked="checked" />
                    <label>구매</label>
                    <input type="radio" name="o" value="s" onclick="chartDraw();" />
                    <label>판매</label>
                </div>
                <div class="radio-wrapper">
                    <input type="radio" name="h" value="1" onclick="chartDraw();" checked="checked" />
                    <label>상반기</label>
                    <input type="radio" name="h" value="2" onclick="chartDraw();" />
                    <label>하반기</label>
                </div>
            </div>
            <div class="tab-wrap">
                <p class="tab">삼성</p>
                <p class="tab">애플</p>
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