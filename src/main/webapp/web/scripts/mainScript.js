function checkboxes() {
    var inputElems = document.getElementsByTagName("input"),
        count = 0;
    for (var i = 0; i < inputElems.length; i++) {
        if (inputElems[i].type === "checkbox" && inputElems[i].checked === true) {
            count++;
        }
    }
    var editbutton = document.getElementById("editbutton");
    if (count > 1 || count == 0) {
        editbutton.disabled = true;

    } else {
        editbutton.disabled = false;
    }
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
