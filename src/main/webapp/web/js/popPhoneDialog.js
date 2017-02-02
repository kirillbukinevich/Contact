function getEditPhone() {
    var inputs = document.getElementsByClassName('check_selected_phone');
    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i].checked) {
            var tr = inputs[i].parentNode.parentNode;
            console.log(inputs[i].value);
            var data = tr.getElementsByTagName('p');
            var contactPhone = getContactPhone(inputs[i].value,tr.rowIndex, data);
            fillInputPhoneForm(document.forms.popPhoneForm, contactPhone);
        }
    }
}
function getContactPhone(phoneId,rowIndex, data) {
    var contactPhone = new ContactPhone;
    var number = data[0].textContent.slice(1).split('-');
    contactPhone.id = phoneId;
    contactPhone.rowIndex = rowIndex;
    contactPhone.codeCountry = number[0];
    contactPhone.codeOperator = number[1];
    contactPhone.number = number[2];
    contactPhone.type = data[1].textContent;
    contactPhone.comment = data[2].textContent;

    return contactPhone;
}
function fillInputPhoneForm(form, contactPhone) {
    form.phone_id.value = contactPhone.id;
    form.tableRowIndex.value = contactPhone.rowIndex;
    form.code_country.value = contactPhone.codeCountry;
    form.code_operator.value = contactPhone.codeOperator;
    form.phone_number.value = contactPhone.number;
    var select = form.phone_type;
    switch (contactPhone.type){
        case 'домашний': select.selectedIndex = 1; break;
        case 'мобильный': select.selectedIndex = 2; break;
        default: select.selectedIndex = 0;
    }
    form.comment.value = contactPhone.comment;
}
function cleanPhoneForm() {
    var form =  document.forms.popPhoneForm;
    form.phone_id.value = "";
    form.tableRowIndex.value = "";
    form.code_country.value = "";
    form.code_operator.value = "";
    form.phone_number.value = "";
    form.phone_type.selectedIndex = 0;
    form.comment.value = "";
}
function savePhoneChange() {
    var form =  document.forms.popPhoneForm;
    var table = document.getElementById("phone_table");

    var row = table.insertRow();

    var cell0 = row.insertCell(0);
    var cell1 = row.insertCell(1);
    var cell2 = row.insertCell(2);
    var cell3 = row.insertCell(3);

// Add some text to the new cells:
    cell0.innerHTML = "<input type='checkbox' name='check_selected_phone' class='check_selected_phone' " +
        "value='" + form.phone_id.value + "' onchange='checkboxesEditDelete(\"check_selected_phone\",\"editPhone\",\"deletePhone\")'>";
    cell1.innerHTML = "<p>+" + form.code_country.value + "-" + form.code_operator.value + "-" + form.phone_number.value;
    cell2.innerHTML = "<p>" + form.phone_type.value + "</p>";
    cell3.innerHTML = "<p>" + form.comment.value + "</p>";

    if(form.parentNode.value == 'Edit_Phone') {
        table.deleteRow(form.tableRowIndex.value);
    }
    form.parentNode.style.display = "none";
    cleanPhoneForm();
    checkboxesEditDelete('check_selected_phone','editPhone','deletePhone');
    return false;
}


