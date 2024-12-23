<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String name = request.getParameter("name"); %>

<div class="container py-4 py-xl-5 pt-lg-4 pb-lg-2 mt-lg-0 mt-0 pt-5" id="quest-div">
    <div class="row gy-4 gy-md-0">
        <div class="col-md-6 d-xxl-flex pt-lg-0 mt-lg-0">
            <div class="border rounded p-xl-5 m-xl-5" style="background: #ffffff;">
                <img class="rounded img-fluid w-100 fit-cover pt-lg-0" style="min-height: 300px;height: 445px;" src="assets/img/president.gif" width="354" height="300">
            </div>
        </div>
        <div class="col-md-6 d-md-flex align-self-center align-items-md-center pb-3" style="font-size: 23px;background: rgb(39,42,52);box-shadow: inset 0px 7px 0px rgb(255,255,255), 0px 7px rgb(255,255,255);">
            <div style="max-width: 350px;">
                <p class="my-3 pt-lg-0 mt-lg-4 me-lg-0 pe-lg-0 pb-lg-0" style="font-size: 18px;color: rgb(238,238,238);">
                    <strong><span style="color: rgb(255, 255, 255);"><%=session.getAttribute("questName")%> принял вызов.</span></strong>
                </p>
                <h2 class="fw-semibold me-lg-0 pe-lg-0 pb-lg-0 mb-lg-0" style="font-size: 31px;color: rgb(255,255,255);">Подняться на мостик к капитану?<br><br></h2>
                <button class="btn btn-dark btn-lg border rounded me-2" type="button" id="accept" style="background: rgb(4,197,0);color: rgb(255,255,255);">Подняться</button>
                <button class="btn btn-dark btn-lg border rounded me-2" type="button" id="decline-2" name="Отклонить вызов" style="background: #e70000;">Отказаться</button>
            </div>
        </div>
    </div>
</div>
<script>
    $("#decline-2").click(function () {
        $.ajax({
            url: 'game-over', method:'GET',
            data: {                     // Параметры запроса
                id: 'decline-2',         // Параметр 'id' с значением 'decline-2'
                reason: 'refusal'        // Дополнительный параметр 'reason'
            },
            success: function (response){
                $("#quest-div").html(response);
            }
        });
    });
    $("#accept").click(function () {
        $.ajax({
            url: 'step-3', method:'GET', success: function (response){
                $("#quest-div").html(response);
            }
        });
    });
</script>
