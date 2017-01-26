function checkboxes(editButtonId,deleteButtonId) {
    var inputElems = document.getElementsByTagName("input"),
        count = 0;
    for (var i = 0; i < inputElems.length; i++) {
        if (inputElems[i].type === "checkbox" && inputElems[i].checked === true) {
            count++;
        }
    }
    var editbuttonPhone = document.getElementById(editButtonId);
    var deleteButtonId = document.getElementById(deleteButtonId);
    if(count==0){
        deleteButtonId.disabled = true;
        editbuttonPhone.disabled = true;
    }else if (count == 1) {
        editbuttonPhone.disabled = false;
        deleteButtonId.disabled = false;
    } else {
        editbuttonPhone.disabled = true;
        deleteButtonId.disabled = false;
    }
}