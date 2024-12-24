<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container py-4 py-xl-5 pt-lg-4 pb-lg-2 mt-lg-0 mt-0 pt-5" id="quest-div">
    <div class="row gy-4 gy-md-0" style="background: #3bca97;">
        <div class="col-md-6 d-xxl-flex pt-lg-0 mt-lg-0">
            <div class="border rounded p-xl-5 m-xl-5" style="background: #ffffff;">
                <img class="rounded img-fluid w-100 fit-cover pt-lg-0" style="min-height: 300px;" src="assets/img/happy-end.gif" width="336" height="403"></div>
        </div>
        <div class="col-md-6 d-md-flex align-self-center align-items-md-center" style="font-size: 15px;background: #262626b6;box-shadow: inset 0px 7px rgb(255,255,255);">
            <div style="max-width: 350px;" class="pb-3">
                <p class="my-3 pt-lg-0 mt-lg-4 me-lg-0 pe-lg-0 pb-lg-0" style="font-size: 18px;color: rgb(238,238,238);">
                    <strong><%=session.getAttribute("questName")%> вернулся домой.</strong></p>
                <h2 class="text-uppercase fw-bold text-start me-lg-0 pe-lg-0 pb-lg-0 mb-lg-0 pb-sm-5" style="font-size: 31px;text-align: center;color: rgb(255,255,255);">ПОБЕДА!</h2>
                <p class="my-3 pt-lg-0 mt-lg-4 me-lg-0 pe-lg-0 pb-lg-0" style="font-size: 18px;color: rgb(238,238,238);"></p>
                <button class="btn btn-dark btn-lg border rounded me-2" type="button" id="start" style="background: rgb(235,235,235);color: #293555;">Начать сначала</button>
            </div>
        </div>
    </div>
</div>
<script>
    $("#start").click(function () {
        $.ajax({
            url: 'step-1', method:'GET', success: function (response){
                $("#quest-div").html(response);
            }
        });
    });
</script>
<p>
        <%
        HttpSession session1 = request.getSession();
        Integer CountVictorys = (Integer) session1.getAttribute("CountVictorys");
        if (CountVictorys == null) {
            CountVictorys = 1;
            session.setAttribute("CountVictorys", CountVictorys);
        } else {
            session.setAttribute("CountVictorys", ++CountVictorys);
        }
        %>