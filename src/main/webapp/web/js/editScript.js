function checkboxesEditDelete(inputClassName,editButtonId,deleteButtonId) {
    var inputs = document.getElementsByClassName(inputClassName);
    count = 0;
    console.log(inputs);
    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i].checked) {
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
function submitForm(isSubmit) {
    var target = document.forms.edit_form;
    console.dir(target);
        return false;
}