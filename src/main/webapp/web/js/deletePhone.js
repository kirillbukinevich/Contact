function deletePhones() {
    var inputs = document.getElementsByClassName('check_selected_phone');
    var table = document.getElementById("phone_table");
    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i].checked) {
            var tr = inputs[i].parentNode.parentNode;
            table.deleteRow(tr.rowIndex);
            i--;
        }
    }
}