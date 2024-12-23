<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp"%>
<head>
    <title>Statistics</title>
    <script src="https://www.google.com/jsapi"></script>
</head>
<body>
<p>
    <%
        HttpSession session1 = request.getSession();
        Integer totalCount = (Integer) session1.getAttribute("totalCount");
        if (totalCount == null) {
            totalCount = 0;
            session1.setAttribute("totalCount", totalCount);

        }

        Integer CountVictorys = (Integer) session1.getAttribute("CountVictorys");
        if (CountVictorys == null) {
            CountVictorys = 0;
            session1.setAttribute("totalCount", totalCount);
        }


        %>
<div>
    <div style="color:white; padding-left: 20px;">
        <h2><%="Количество раз сыграно: " + totalCount%> </h2>
        <h2> <%="Количество побед: " + CountVictorys%> </h2>
    </div>
</div>

<script>
    google.load("visualization", "1", {packages:["corechart"]});
    google.setOnLoadCallback(drawChart);
    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['Результат', 'Количество'],
            ['Всего', <%=totalCount%>],
            ['Победы', <%=CountVictorys%>],
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

<br><br><br>
<div style="display: flex; justify-content: center; align-items: center; height: 50vh;">
    <div id="diagram" style="width: 600px; height: 500px;"></div>
</div>
</body>

