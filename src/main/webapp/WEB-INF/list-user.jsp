<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp"%>
<head>
    <title>List User</title>
</head>
<body>
<head>
    <style>
        .container {
            display: flex;
            flex-wrap: wrap; /* Позволяет элементам переноситься на новую строку */
            gap: 10px; /* Расстояние между элементами */
            justify-content: center; /* Центровка элементов */
        }

        @media (max-width: 768px) {
            .container {
                flex-direction: column; /* В столбик */
                align-items: center; /* Центровка по горизонтали */
            }
        }
    </style>
</head>
<div class="container">
<c:forEach var="user" items="${requestScope.users}">
    <div class="d-flex mt-3 mb-3" style="width: 312px;">
        <img class="rounded-circle flex-shrink-0 me-3 fit-cover" width="99" height="99" src="assets/img/Noname.png" />
        <div>
            <p class="fw-bold mb-0" style="font-size: 32px;"><a href="edit-user?id=${user.id}">${user.login}</a></p>
            <p class="text-muted mb-0" style="font-size: 21px;"><a href="edit-user?id=${user.id}">${user.role}</a></p>
        </div>
    </div>
</c:forEach>
</div>
</body>
<%@include file="footer.jsp" %>

