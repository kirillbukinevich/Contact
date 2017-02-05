function popDialog(modalID, action) {
    var modal = document.getElementById(modalID);
    modal.value = action;
    if (action == 'Edit_Phone') {
        getEditPhone();
    }
    if (action == 'Edit_Attachment') {
        getEditAttachment();
    }
    modal.style.display = "block";
    var close_button = document.getElementById("close_" + modalID);
    close_button.onclick = function () {
        modal.style.display = "none";
        if (modalID == 'phoneModal') {
            cleanPhoneForm();
        } else if (modalID == 'attachModal') {
            cleanAttachForm();
        }
        return false;
    }
}

