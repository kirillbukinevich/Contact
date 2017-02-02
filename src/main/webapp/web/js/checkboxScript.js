function checkboxes() {
    var inputElems = document.getElementsByTagName("input"),
        count = 0;
    for (var i = 0; i < inputElems.length; i++) {
        if (inputElems[i].type === "checkbox" && inputElems[i].checked === true) {
            count++;
        }
    }
    var editbutton = document.getElementById("editbutton");
    editbutton.disabled = !!(count > 1 || count == 0);
}
function checkboxesForMail() {
    var inputElems = document.getElementsByTagName("input"),
        count = 0;
    for (var i = 0; i < inputElems.length; i++) {
        if (inputElems[i].type === "checkbox" && inputElems[i].checked === true) {
            count++;
        }
    }
    var sendmailbutton = document.getElementById("sendmailbutton");
    var deletebutton = document.getElementById("deletebutton");
    if (count == 0) {
        sendmailbutton.disabled = true;
        deletebutton.disabled = true;

    } else {
        sendmailbutton.disabled = false;
        deletebutton.disabled = false;
    }
}
function checkboxesEditDelete(inputClassName,editButtonId,deleteButtonId) {
    var inputs = document.getElementsByClassName(inputClassName);
    count = 0;
    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i].checked) {
            count++;
        }
    }
    var editbutton = document.getElementById(editButtonId);
    var deleteButton = document.getElementById(deleteButtonId);
    if(count==0){
        deleteButton.disabled = true;
        editbutton.disabled = true;
    }else if (count == 1) {
        editbutton.disabled = false;
        deleteButton.disabled = false;
    } else {
        editbutton.disabled = true;
        deleteButton.disabled = false;
    }
}
