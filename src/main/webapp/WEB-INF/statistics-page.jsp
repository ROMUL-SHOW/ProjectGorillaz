<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp"%>
<head>
    <title>Statistics</title>
    <script src="https://www.google.com/jsapi"></script>
</head>
<body>
<p>
    <div style="color:#3d3a40; padding-left: 20px;">
        <h2><%="Количество раз сыграно: " + request.getSession().getAttribute("totalCount")%> </h2>
        <h2> <%="Количество побед: " + request.getSession().getAttribute("CountVictory")%> </h2>
    </div>
<br>
<div style="display: flex; justify-content: center;  height: 50vh;">
    <div id="diagram" style="width: 600px; height: 500px;"></div>
</div>



<script>
    google.load("visualization", "1", {packages:["corechart"]});
    google.setOnLoadCallback(drawChart);
    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['Результат', 'Количество'],
            ['Всего', <%=request.getSession().getAttribute("totalCount")%>],
            ['Победы', <%=request.getSession().getAttribute("CountVictory")%>],
        ]);
        var options = {
            title: 'Статистика',
            pieSliceText: 'percentage',
            slices: {
                0: { offset: 0.02, color: '#00acea' },  // Сегмент "Общее количество игр" — голубой
                1: { offset: 0.02, color: '#0ed30e' }   // Сегмент "Победы" — зеленый
            }
        };
        var chart = new google.visualization.PieChart(document.getElementById('diagram'));
        chart.draw(data, options);
    }
</script>
</body>

