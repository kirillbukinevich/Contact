<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<p class="contact"><label >Ф.И.О.</label></p>
<input type="text" class="form-control" name="first_name" id = "first_name"  placeholder="Введите ваше имя"
       value="${first_name}" pattern="^[^$.|?*+()]+$"  required>
<input type="text" class="form-control" name="patronymic"  value="${patronymic}"
       placeholder="Введите ваше отчество" pattern="^[^$.|?*+()]+$" required >
<input type="text" class="form-control" name="last_name"  value="${last_name}"
       placeholder="Введите вашу фамилию" pattern="^[^$.|?*+()]+$" required >

<label>Дата рождения</label>
<input type="date" class="form-control" name="date_of_birth" value="${date_of_birth}"
       placeholder="Your birthday yyyy-mm-dd" required>

<label>Выберите пол</label>
<select class="select-style" name="gender" required>
    <OPTION selected>
    <OPTION ${gender == 'женский' ? 'selected' : ''}> женский
    <OPTION ${gender == 'мужской' ? 'selected' : ''}> мужской
</SELECT><br><br>

<label>Национальность</label>
<input type="text" class="form-control" name="nationality" value="${nationality}"
       placeholder="Nationality">

<label>Семейный статус</label>
<select class="select-style" name="family_status" id="family_status">
    <OPTION selected>
    <OPTION ${family_status == 'брак' ? 'selected' : ' '}> брак
    <OPTION ${family_status == 'свободен' ? 'selected' : ' '}> свободен
</SELECT><br><br>

<label>Веб сайт</label>
<input type="url" class="form-control" name="web_site" id = "web_site" value="${web_site}" placeholder="Веб сайт">
<label>Email</label>
<input type="email" class="form-control" name="email" id = "email" value="${email}" placeholder="email" required>
<label>Место работы</label>
<input type="text" class="form-control" name="work_place" value="${work_place}"
       placeholder="Место работы">

<p><label>Адрес</label></p>
<input type="text" class="form-control" name="country" id="country" value="${country}"
       placeholder="страна" pattern="^[^$.|?*+()]+$">
<input type="text" class="form-control" name="city" id="city" value="${city}"
       placeholder="город" pattern="^[^$.|?*+()]+$">
<input type="text" class="form-control" name="street" id="street" value="${street}"
       placeholder="улица" pattern="^[^$.|?*+()]+$">
<input type="text" class="form-control" name="house" id="house" value="${house}"
       placeholder="дом" pattern="^[1-9]{1}[0-9]{0,2}$" >
<input type="text" class="form-control" name="flat" id="flat" value="${flat}"
       placeholder="квартира" pattern="^[1-9]{1}[0-9]{0,2}$">
<input type="text" class="form-control" name="index" id="index" value="${index}"
       placeholder="индекс" pattern="^[0-9]{6}$">


