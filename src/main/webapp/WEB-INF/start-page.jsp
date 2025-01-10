<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp" %>
<head>
    <title>Quest</title>
</head>
<body>
<div class="container py-4 py-xl-5 pt-lg-4 pb-lg-2 mt-lg-0 mt-0 pt-5" id="quest-div">
    <div class="row gy-4 gy-md-0">
        <div class="col-md-6 d-xxl-flex pt-lg-0 mt-lg-0">
            <div class="border rounded p-xl-5 m-xl-5" style="background: #ffffff;">
                <img class="rounded img-fluid w-100 fit-cover pt-lg-0" style="min-height: 300px;"
                     src="assets/img/UFO.gif" width="336" height="403">
            </div>
        </div>
        <div class="col-md-6 d-md-flex align-self-center align-items-md-center"
             style="font-size: 15px;background: #262626b6;box-shadow: inset 0px 7px rgb(255,255,255);">
            <div style="max-width: 350px;">
                <h2 class="text-uppercase fw-bold me-lg-0 pe-lg-0 pb-lg-0 mb-lg-0 pb-sm-5"
                    style="font-size: 31px;text-align: center;color: rgb(255,255,255);"><br>квестовый поход</h2>
                <p class="my-3 pt-lg-0 mt-lg-4 me-lg-0 pe-lg-0 pb-lg-0"
                   style="font-size: 18px;color: rgb(238,238,238);">
                    <strong>Пролог</strong>
                    <br>Ты стоишь в космическом порту и готов подняться на борт своего корабля. Разве ты не об этом
                    мечтал? Стать капитаном галактического судна с экипажем, который будет совершать подвиги под твоим
                    командованием.
                    <br>Так что вперед!</p>
                <button class="btn btn-dark btn-lg border rounded me-2" type="button" id="btStart" name="start"
                        style="background: rgb(235,235,235);color: #293555;">Начать
                </button>
                <p>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $("#btStart").click(function () {
            $.ajax({
                url: '',
                method: 'GET',
                data: {                     // Параметры запроса
                    id: 'btStart',
                },
                success: function (response) {
                    $("#quest-div").html(response);
                }
            });
        });
    });
</script>
</body>
<%@include file="footer.jsp" %>
