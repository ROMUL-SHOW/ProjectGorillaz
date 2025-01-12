<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp"%>
<head>
    <title>Statistics</title>
    <script src="https://www.google.com/jsapi"></script>
    <style>
        table, th, td{border: 1px solid black !important; border-collapse: collapse !important;}
    th, td{padding: 15px;} th{text-align: left;}
    </style>
</head>
<body>
<p>
<div style="color:#3d3a40; padding-top: 10px; text-align: center; margin: 0 auto;">
        <h2><%="Общее кол-во раз сыграно: " + request.getSession().getAttribute("gamesPlayed")%> </h2>
        <h2> <%="Общее кол-во побед: " + request.getSession().getAttribute("gamesWon")%> </h2>
    </div>
<br>

<div style="display: flex; flex-wrap: wrap; justify-content: center; gap: 20px; align-items: flex-start;">
    <div id="diagram" style="flex: 1 1 300px; min-width: 300px; max-width: 500px; height: 400px;"></div>
    <div>
        <h5>Cтатистика по каждому пользователю</h5>
        <table style="width: 100%; max-width: 600px;">
            <tr>
                <th>Пользователь</th>
                <th>Процент побед %</th>
            </tr>
            <c:forEach var="user" items="${requestScope.users}">
                <c:if test="${user.statistic.gamesWon > '0'}">
                    <tr>
                        <td>${user.login}</td>
                        <td>
                            <fmt:formatNumber value="${user.statistic.gamesWon * 100.0 / user.statistic.gamesPlayed}" type="number" minFractionDigits="1" maxFractionDigits="1"/>%
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>
</div>

<script>
    google.load("visualization", "1", {packages:["corechart"]});
    google.setOnLoadCallback(drawChart);

    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['Результат', 'Количество'],
            ['Всего', <%=request.getSession().getAttribute("gamesPlayed")%>],
            ['Победы', <%=request.getSession().getAttribute("gamesWon")%>],
        ]);
        var options = {
            title: 'Общая статистика пользователей',
            pieSliceText: 'percentage',
            slices: {
                0: { offset: 0.02, color: '#00acea' },
                1: { offset: 0.02, color: '#0ed30e' }
            },
            chartArea: { width: '90%', height: '90%' }
        };
        var chart = new google.visualization.PieChart(document.getElementById('diagram'));
        chart.draw(data, options);
    }

    // Обновляем диаграмму при изменении размера окна
    window.addEventListener('resize', drawChart);
</script>



</body>
<%@include file="footer.jsp" %>
