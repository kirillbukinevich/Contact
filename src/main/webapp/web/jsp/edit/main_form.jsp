<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div id='div-main-form'>
    <label id='label_first_name'><p>Имя</p>
        <input type="text" class="form-control" name="first_name" id="first_name" placeholder="Введите ваше имя"
               value="${first_name}" pattern="^\w+(-?)\w+$" required>
        <p style="color: red; display: none">Введите ваше имя например: (Иван или Анна-Мария)</p>
    </label>

    <label for="last_name"><p>Фамилия</p>
        <input type="text" class="form-control" name="last_name" id="last_name" value="${last_name}"
               placeholder="Введите вашу фамилию" pattern="^\w+(-?)\w+$" required>
        <p style="color: red; display: none">Введите вашу фамилию например: (Иванов или Джейд-Скайуокер)</p>
    </label>

    <label for="patronymic"><p>Отчество</p>
        <input type="text" class="form-control" name="patronymic" id="patronymic" value="${patronymic}"
               placeholder="Введите ваше отчество" pattern="^\w+(-?)\w+$" required>
        <p style="color: red; display: none">Введите ваше отчество например: (Иванович или Александр-Георгиевич)</p>
    </label>

    <label for="date_of_birth"><p>Дата рождения</p>
        <input type="date" class="form-control" id="date_of_birth" name="date_of_birth" value="${date_of_birth}"
               placeholder="Your birthday yyyy-mm-dd" onchange="checkDate()" required>
        <p style="color: red; display: none">Неверная дата</p>
    </label>

    <label><p>Выберите пол</p></label>
    <select class="select-style" name="gender" required
            oninvalid="setCustomValidity('выберите пол')"
            oninput="setCustomValidity('')">
        <OPTION selected>
        <OPTION ${gender == 'женский' ? 'selected' : ''}> женский
        <OPTION ${gender == 'мужской' ? 'selected' : ''}> мужской
    </SELECT>
    <br><br>


    <label><p>Национальность</p>
        <input type="text" class="form-control" name="nationality" value="${nationality}"
               placeholder="Nationality" pattern="^\w+((-?)|(\s?))\w+$">
        <p style="color: red; display: none">Введите корректную национальность</p>
    </label>

    <label><p>Семейный статус</p></label>
    <select class="select-style" name="family_status" id="family_status">
        <OPTION selected>
        <OPTION ${family_status == 'брак' ? 'selected' : ' '}> брак
        <OPTION ${family_status == 'свободен' ? 'selected' : ' '}> свободен
    </SELECT><br><br>

    <label><p>Веб сайт</p>
        <input type="url" class="form-control" name="web_site" id="web_site" value="${web_site}" placeholder="Веб сайт">
    </label>

    <label><p>Email</p>
    <input type="email" class="form-control" name="email" id="email" value="${email}" placeholder="email" required>
    </label>

    <label><p>Место работы</p></label>
    <input type="text" class="form-control" name="work_place" value="${work_place}"
           placeholder="Место работы">

    <label><p>Страна</p>
    <input type="text" class="form-control" name="country" id="country" value="${country}"
           placeholder="страна" pattern="^\w+((-?)|(\s?))\w+$"
           oninvalid="setCustomValidity('Введите пожалуйста корректное название страны')"
           oninput="setCustomValidity('')">
    <p style="color: red; display: none">Введите пожалуйста корректное название страны</p>
    </label>

    <label><p>Город</p>
    <input type="text" class="form-control" name="city" id="city" value="${city}"
           placeholder="город" pattern="^\w+((-?)|(\s?))\w+$"
           oninvalid="setCustomValidity('Введите пожалуйста корректное название города')"
           oninput="setCustomValidity('')">
        <p style="color: red; display: none">Введите пожалуйста корректное название города</p>
    </label>

    <label><p>Улица</p>
    <input type="text" class="form-control" name="street" id="street" value="${street}"
           placeholder="улица" pattern="^\w+((-?)|(\s?))\w+$"
           oninvalid="setCustomValidity('Введите пожалуйста корректное название улицы')"
           oninput="setCustomValidity('')">
    <p style="color: red; display: none">Введите пожалуйста корректное название улицы</p>
    </label>

    <label><p>Дом</p>
    <input type="text" class="form-control" name="house" id="house" value="${house}"
           placeholder="дом" pattern="^[1-9]{1}[0-9]{0,2}[а-я]?$"
           oninvalid="setCustomValidity('Введите пожалуйста номер дома')"
           oninput="setCustomValidity('')">
    <p style="color: red; display: none">Введите пожалуйста номер дома</p>
    </label>

    <label><p>Квартира</p>
    <input type="text" class="form-control" name="flat" id="flat" value="${flat}"
           placeholder="квартира" pattern="^[1-9]{1}[0-9]{0,2}$"
           oninvalid="setCustomValidity('Введите пожалуйста номер квартиры')"
           oninput="setCustomValidity('')">
    <p style="color: red; display: none">Введите пожалуйста номер квартиры</p>
    </label>

    <label><p>Индекс</p>
    <input type="text" class="form-control" name="index" id="index" value="${index}"
           placeholder="индекс" pattern="^[0-9]{6}$"
           oninvalid="setCustomValidity('Введите пожалуйста ваш индекса')"
           oninput="setCustomValidity('')">
    <p style="color: red; display: none">Введите пожалуйста ваш индекса</p>
    </label>

</div>
<script src="${pageContext.request.contextPath}/web/js/valid.js"></script>
<script src="${pageContext.request.contextPath}/web/js/validDate.js"></script>
