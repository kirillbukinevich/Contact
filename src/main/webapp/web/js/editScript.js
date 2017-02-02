function submitEditForm(command) {
    if(command === "cancel_edit"){
        location.reload();
    }
    var form = document.forms.edit_form;
    form.onsubmit = function () {
        return false;
    };
    if (document.getElementsByClassName('error').length == 0) {
        if (command === "contact") {
            console.dir(form.btnSave.validity);
            setTimeout(function () {
                form.action = "controller?command=contact";
                form.onsubmit = function () {
                    return true;
                };
                form.submit();
            }, 1500);
        }
    }
}

function processEditCommand(command) {
    console.log(command);
    switch (command) {
        case 'update_phone':
            popDialog('phoneModal', 'Add_Phone');
            break;
        case 'update_edit_phone':
            popDialog('phoneModal', 'Edit_Phone');
            break;
        case 'deletePhone':
            deletePhones();
            break;
        case 'update_attachment':
            popDialog('attachModal', 'Add_Attachment');
            break;
        case 'update_edit_attachment':
            popDialog('attachModal', 'Edit_Attachment');
            break;
        case  'deleteAttachFile':
            deleteAttachments();
            break;
        case 'contact':
            saveEmployee();
            break;
        case 'update_photo':
            popDialog('photoModal', 'Update_Photo');
            break;
    }
    submitEditForm(command);

}

(function () {
    var form = document.forms.edit_form;
    form.onclick = function (event) {
        console.dir(event.target);
        processEditCommand(event.target.parentNode.value);
    }
})();
function getPhoto() {

}
function saveEmployee() {
    if (document.getElementsByClassName('error').length == 0) {
        var employee = getEmployee();
        employee.photoName = document.getElementById("avatar").textContent;
        employee.phoneList = getPhoneArray();
        employee.attachmentList = getAttachmentArray();
        console.log(JSON.stringify(employee));
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("POST", "/zcontact/controller?command=save", true);
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send(JSON.stringify(employee));
    }
}
function getEmployee() {
    var employee = new Employee();
    var form = document.forms.edit_form;
    employee.id = form.employee_id.value;
    employee.firstName = form.first_name.value;
    employee.lastName = form.last_name.value;
    employee.patronymic = form.patronymic.value;
    employee.dateOfBirth = form.date_of_birth.value;
    employee.gender = form.gender.value;
    employee.nationality = form.nationality.value;
    employee.familyStatus = form.family_status.value;
    employee.webSite = form.web_site.value;
    employee.email = form.email.value;
    employee.workPlace = form.work_place.value;
    employee.address = getAddress(form);
    employee.phoneList = getPhoneArray();
    return employee;
}
function getAddress(form) {
    var address = new Address();
    address.countryName = form.country.value;
    address.cityName = form.city.value;
    address.streetName = form.street.value;
    address.houseNumber = form.house.value;
    address.flatNumber = form.flat.value;
    address.index = form.index.value;

    return address;
}

function getPhoneArray() {
    var phoneArray = [];
    var table = document.getElementById("phone_table");
    var rows = table.rows;
    for (var i = 1; i < rows.length; i++) {
        var phone = new ContactPhone(),
            td = rows[i].cells,
            numberParts = td[1].getElementsByTagName('p')[0].textContent.slice(1).split('-');

        phone.id = td[0].childNodes[0].value;
        phone.codeCountry = numberParts[0];
        phone.codeOperator = numberParts[1];
        phone.number = numberParts[2];
        phone.type = td[2].childNodes[0].textContent;
        phone.comment = td[3].childNodes[0].textContent;
        phoneArray.push(phone);
    }
    return phoneArray;
}
function getAttachmentArray() {
    var attachmentArray = [];
    var table = document.getElementById("attachment_table");
    var rows = table.rows;
    for (var i = 1; i < rows.length; i++) {
        var attachment = new Attachment(),
            td = rows[i].cells;

        attachment.id = td[0].childNodes[0].value;
        attachment.fileName = td[1].getElementsByTagName('p')[0].textContent;
        attachment.loadDate = td[2].childNodes[0].textContent;
        attachment.comment = td[3].childNodes[0].textContent;
        attachmentArray.push(attachment);
    }
    return attachmentArray;

}
