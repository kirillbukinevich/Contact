<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>


<!-- The Modal -->
<div id="phoneModal" class="modal phone-img">

    <!-- Modal content -->
    <form name="popPhoneForm" onsubmit="return savePhoneChange();" method="post" action="controller" id="edit_phone">
    <div class="modal-content">
        <div class="modal-header">
            <h2>${type_operation}</h2>
        </div>
        <div class="modal-body">
                <div class="form">
                    <div class="form-phone">
                        <input type="hidden" name="phone_id">
                        <input type="hidden" name="tableRowIndex">
                        <label>Введите код страны</label>
                        <input type="number" class="form-control" name="code_country" value="${code_country}"
                               id="code_country" min="0" max="999" required
                               oninvalid="setCustomValidity('Введите пожалуйста корректный код страны')"
                               oninput="setCustomValidity('')">


                        <label>Введите код оператора</label>
                        <input type="number" class="form-control" name="code_operator" value="${code_operator}"
                               id="code_operator" min="0" max="99" required
                               oninvalid="setCustomValidity('Введите пожалуйста корректный код оператора')"
                               oninput="setCustomValidity('')">


                        <label>Введите телефонный номер</label>
                        <input type="number" class="form-control" name="phone_number" value="${phone_number}"
                               id="phone_number" min="1000000" max="9999999" required
                               oninvalid="setCustomValidity('Введите пожалуйста корректный номер телефона')"
                               oninput="setCustomValidity('')">

                        <br>
                        <label>Выберите тип телефона</label>
                        <select class="select-style" name="phone_type">
                            <OPTION selected>
                            <OPTION ${phone_type == 'домашний' ? 'selected' : ' '}> домашний
                            <OPTION ${phone_type == 'мобильный' ? 'selected' : ' '}> мобильный
                        </SELECT><br><br>

                        <label>Комментарий</label>
                        <input type="text" class="form-control" name="comment" value="${comment_phone}">

                    </div>
                </div>

        </div>
        <div class="modal-footer">
            <button class="btn btn-trans btn-trans-success" name="command" value="${type_operation}">
                Сохранить
            </button>
            <button class="btn btn-trans btn-trans-success" id="close_phoneModal">Отменить
            </button>

        </div>
    </div>
    </form>

</div>

