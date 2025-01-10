<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<body>
<div class="row gy-4 gy-md-0" id="quest-div-send-name">
    <div class="col-md-6 d-xxl-flex pt-lg-0 mt-lg-0">
        <div class="border rounded p-xl-5 m-xl-5" style="background: #ffffff;">
            <img class="rounded img-fluid w-100 fit-cover pt-lg-0" style="min-height: 300px;" src="assets/img/UFO.gif" width="336" height="403">
        </div>
    </div>
    <div class="col-md-6 d-md-flex align-self-center align-items-md-center" style="font-size: 15px;background: #262626b6;box-shadow: inset 0px 7px rgb(255,255,255);">
        <div style="max-width: 350px;">
            <h2 class="text-uppercase fw-bold me-lg-0 pe-lg-0 pb-lg-0 mb-lg-0 pb-sm-3 pt-sm-0 mt-sm-2" style="font-size: 31px;text-align: center;color: rgb(255,255,255);"><br>квестовый поход</h2>
            <p class="my-3 pt-lg-0 mt-lg-4 me-lg-0 pe-lg-0 pb-lg-0 mb-sm-5 pb-sm-0" style="font-size: 18px;color: rgb(238,238,238);">
                <strong>Знакомство с экипажем</strong>
                <br>Когда ты поднялся на борт корабля, тебя поприветствовала девушка с черной палкой в руках:<br>
                <em>- Здравствуйте, командир! Я Зинаида - ваша помощница. Видите? Там в углу пьет кофе, наш штурман - сержант Перегарный Шлейф, под штурвалом спит бортмеханик - Черный Богнан, а фотографирует его Сергей Стальная Пятка - наш навигатор.</em>
            </p>
            <form id="formName">
            <input type="text" id="name" name="name" style="height: 35px; width: 178.828px; border-radius: 0px; font-size: 18px;" required><br>
            <button class="btn btn-dark btn-lg border rounded-0 me-2 mt-sm-2 ms-sm-0 mb-sm-2" type="submit" style="background: rgb(235,235,235);color: #293555;width: 178.828px;">Представиться</button>
            </form>
        </div>
    </div>
</div>
<script>
    // Привязываем обработчик события submit для формы с id="formName"
    $("#formName").submit(function (event) {

        // Отменяем стандартное поведение формы (перезагрузку страницы)
        // Это нужно, чтобы форма не отправлялась традиционным способом
        event.preventDefault();

        // Собираем все данные формы в строку в формате "name=value&name2=value2"
        // Используется метод .serialize() для того, чтобы передать все данные формы (включая поля ввода)
        var formData = $(this).serialize();

        $.ajax({
            url: 'quest-steps',
            method:'GET',
            data: formData + "&id=1",
            success: function (response){
                $("#quest-div-send-name").html(response);
            },
            error: function (xhr, status, error) {
                console.error("Произошла ошибка при отправке запроса:", status, error);
            }
        });
    });
</script>
</body>