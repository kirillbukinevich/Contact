<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- Trigger/Open The Modal -->

<!-- The Modal -->
<div id="searchModal" class="modal search-img">

    <!-- Modal content -->
    <div class="modal-content" style="width: 440px;">
        <div class="form">
            <div class="modal-header">
                <h3>Найти</h3>
            </div>
            <form method="post" action="controller">
                <div class="modal-body">
                    <div class="form-group">
                        <br>

                        <p><label for="find_first_name">Имя</label></p>
                        <input type="text" class="form-control" name="find_first_name" id="find_first_name">

                        <p><label for="find_last_name">Фамилия</label></p>
                        <input type="text" class="form-control" name="find_last_name" id="find_last_name">


                        <p><label for="find_patronymic">Отчество</label></p>
                        <input type="text" class="form-control" name="find_patronymic" id="find_patronymic">
                        <label>Выберите дату</label>
                        <select class="selectpicker" name="find_date_direction">
                            <OPTION selected>
                            <OPTION value="since"> с
                            <OPTION value="until"> до
                        </SELECT>
                        <input type="date" name="find_date_of_birth">
                        <br/>

                        <label>Выберите пол:</label>
                        <select class="selectpicker" name="find_gender">
                            <OPTION> мужской
                            <OPTION> женский
                            <OPTION selected>
                        </SELECT>
                        <br/>

                        <p><label for="find_nationality">гражданство</label></p>
                        <input type="text" class="form-control" name="find_nationality" id="find_nationality">

                        <label>Семейный статус:</label>
                        <select class="selectpicker" name="find_family_status">
                            <OPTION> брак
                            <OPTION> свободен
                            <OPTION selected>
                        </SELECT>
                        <br/>

                        <p><label>страна</label></p>
                        <input type="text" class="form-control" name="find_country">

                        <p><label>город</label></p>
                        <input type="text" class="form-control" name="find_city">

                        <p><label>улица</label></p>
                        <input type="text" class="form-control" name="find_street">

                        <p><label>дом</label></p>
                        <input type="text" class="form-control" name="find_house">

                        <p><label>квартира</label></p>
                        <input type="text" class="form-control" name="find_flat">

                        <p><label>индекс</label></p>
                        <input type="text" class="form-control" name="find_index">

                    </div>


                </div>
                <div class="modal-footer">
                    <button class="btn btn-trans btn-trans-success" name="command"
                            value="search">Поиск
                    </button>
                    <button class="btn btn-trans btn-trans-success" id="close_searchModal">Закрыть
                    </button>

                </div>
            </form>
        </div>
    </div>

</div>
