<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- Trigger/Open The Modal -->
<button class="btn btn-trans btn-trans-success" id="myBtn5" onclick="popDialog('searchModal')"><span class="glyphicon glyphicon-search" ></span>
    Найти</button>

<!-- The Modal -->
<div id="searchModal" class="modal" >

    <!-- Modal content -->
    <div class="modal-content" style="width: 470px;">
        <div class="form">
            <form name="loginForm" method="post" action="controller">
                <div class="modal-header">
                    <h2>Найти</h2>
                </div>

                <div class="modal-body">
                    <div class="form-group">
                        <label for="find_last_name">Фамилия</label>
                        <input type="text" class="form-control" name="find_last_name" id="find_last_name">
                        <label for="find_first_name">Имя</label>
                        <input type="text" class="form-control" name="find_first_name" id="find_first_name">
                        <label for="find_patronymic">Отчество</label>
                        <input type="text" class="form-control" name="find_patronymic" id="find_patronymic">
                        <label>date</label>
                        <select class="selectpicker" name="find_date_direction">
                            <OPTION selected>
                            <OPTION> с
                            <OPTION> до
                        </SELECT>
                        <input type="date" name="find_date_of_birth">

                        <P>Выберите пол:
                            <select class="selectpicker" name="find_gender">
                                <OPTION> мужской
                                <OPTION> женский
                                <OPTION selected>
                            </SELECT>

                            <label for="find_nationality">гражданство</label>
                            <input type="text" class="form-control" name="find_nationality" id="find_nationality">

                            <select class="selectpicker" name="find_family_status">
                                <OPTION> брак
                                <OPTION> свободен
                                <OPTION selected>
                            </SELECT>
                            <label>страна</label>
                            <input type="text" class="form-control" name="find_country">
                            <label>город</label>
                            <input type="text" class="form-control" name="find_city">
                            <label>улица</label>
                            <input type="text" class="form-control" name="find_street">
                            <label>дом</label>
                            <input type="text" class="form-control" name="find_house">
                            <label>квартира</label>
                            <input type="text" class="form-control" name="find_flat">
                            <label>индекс</label>
                            <input type="text" class="form-control" name="find_index">

                    </div>


                </div>
                <div class="modal-footer">
                    <button class="btn btn-info" name="command"
                            value="search">Поиск
                    </button>
                    <button class="btn btn-info" id="close_searchModal">Закрыть
                    </button>

                </div>

            </form>
        </div>
    </div>

</div>
