function deleteAttachments() {
    var inputs = document.getElementsByClassName('check_selected_file');
    var table = document.getElementById("attachment_table");
    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i].checked) {
            var tr = inputs[i].parentNode.parentNode;
            table.deleteRow(tr.rowIndex);
            i--;
        }
    }
}