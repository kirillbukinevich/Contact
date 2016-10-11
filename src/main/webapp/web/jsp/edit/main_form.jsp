<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<p><label>Имя</label></p>
<input type="text" class="form-control" name="first_name" id="first_name" placeholder="Введите ваше имя"
       value="${first_name}" pattern="^[^$.|?*+()]+$" required >
<p><label>Отчество</label></p>
<input type="text" class="form-control" name="patronymic" value="${patronymic}"
       placeholder="Введите ваше отчество" pattern="^[^$.|?*+()]+$" required>

<p><label>Фамилия</label></p>
<input type="text" class="form-control" name="last_name" value="${last_name}"
       placeholder="Введите вашу фамилию" pattern="^[^$.|?*+()]+$" required>

<p><label>Дата рождения</label></p>
<input type="date" class="form-control" id="date_of_birth" name="date_of_birth" value="${date_of_birth}"
       placeholder="Your birthday yyyy-mm-dd" onchange="checkDate()" required>

<label>Выберите пол</label>
<select class="select-style" name="gender" required
        oninvalid="setCustomValidity('выберите пол')"
        oninput="setCustomValidity('')" >
    <OPTION selected>
    <OPTION ${gender == 'женский' ? 'selected' : ''}> женский
    <OPTION ${gender == 'мужской' ? 'selected' : ''}> мужской
</SELECT><br><br>

<p><label>Национальность</label></p>
<input type="text" class="form-control" name="nationality" value="${nationality}"
       placeholder="Nationality">

<label>Семейный статус</label>
<select class="select-style" name="family_status" id="family_status">
    <OPTION selected>
    <OPTION ${family_status == 'брак' ? 'selected' : ' '}> брак
    <OPTION ${family_status == 'свободен' ? 'selected' : ' '}> свободен
</SELECT><br><br>

<p><label>Веб сайт</label></p>
<input type="url" class="form-control" name="web_site" id="web_site" value="${web_site}" placeholder="Веб сайт">

<p><label>Email</label></p>
<input type="email" class="form-control" name="email" id="email" value="${email}" placeholder="email" required>

<p><label>Место работы</label></p>
<input type="text" class="form-control" name="work_place" value="${work_place}"
       placeholder="Место работы">

<p><label>Страна</label></p>
<input type="text" class="form-control" name="country" id="country" value="${country}"
       placeholder="страна" pattern="^[^$.|?*+()]+$"
       oninvalid="setCustomValidity('Введите пожалуйста корректное название страны')"
       oninput="setCustomValidity('')" >
<p><label>Город</label></p>
<input type="text" class="form-control" name="city" id="city" value="${city}"
       placeholder="город" pattern="^[^$.|?*+()]+$"
       oninvalid="setCustomValidity('Введите пожалуйста корректное название города')"
       oninput="setCustomValidity('')" >

<p><label>Улица</label></p>
<input type="text" class="form-control" name="street" id="street" value="${street}"
       placeholder="улица" pattern="^[^$.|?*+()]+$"
       oninvalid="setCustomValidity('Введите пожалуйста корректное название улицы')"
       oninput="setCustomValidity('')" >

<p><label>Дом</label></p>
<input type="text" class="form-control" name="house" id="house" value="${house}"
       placeholder="дом" pattern="^[1-9]{1}[0-9]{0,2}[а-я]?$"
       oninvalid="setCustomValidity('Введите пожалуйста номер дома')"
       oninput="setCustomValidity('')" >

<p><label>Квартира</label></p>
<input type="text" class="form-control" name="flat" id="flat" value="${flat}"
       placeholder="квартира" pattern="^[1-9]{1}[0-9]{0,2}$"
       oninvalid="setCustomValidity('Введите пожалуйста номер квартиры')"
       oninput="setCustomValidity('')" >

<p><label>Индекс</label></p>
<input type="text" class="form-control" name="index" id="index" value="${index}"
       placeholder="индекс" pattern="^[0-9]{6}$"
       oninvalid="setCustomValidity('Введите пожалуйста ваш индекса')"
       oninput="setCustomValidity('')" >

