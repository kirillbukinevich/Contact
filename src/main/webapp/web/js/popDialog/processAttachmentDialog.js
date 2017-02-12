function getEditAttachment() {
    var inputs = document.getElementsByClassName('check_selected_file');
    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i].checked) {
            var tr = inputs[i].parentNode.parentNode;
            var data = tr.getElementsByTagName('p');
            var attachment = getAttachment(tr.rowIndex,inputs[i].value, data);
            fillInputAttachmentForm(document.forms.popAttachmentForm,attachment);
            inputs[i].checked = false;
        }
    }
}
function getAttachment(rowIndex,attachmentId, data) {
    var attachment = new Attachment;

    attachment.id = attachmentId;
    attachment.rowIndex = rowIndex;
    attachment.fileName = data[0].textContent;
    attachment.loadDate = data[1].textContent;
    attachment.comment = data[2].textContent;

    return attachment;
}
function fillInputAttachmentForm(form, attachment) {
    form.attachment_id.value = attachment.id;
    form.tableRowIndex.value = attachment.rowIndex;
    var h2 = form.getElementsByTagName('h2')[0];
    h2.textContent  = attachment.fileName;
    form.comment_attachment.value = attachment.comment;
  }
function cleanAttachForm() {
    var form =  document.forms.popAttachmentForm;
    form.attachment_id.value = "";
    form.tableRowIndex.value = "";
    form.getElementsByTagName('h2')[0].textContent  = "";
    form.file_name.value = "";
    form.comment_attachment.value = "";

}
function saveAttachmentChange() {
    var form =  document.forms.popAttachmentForm;
    var id = form.attachment_id.value;
    if(id == 0) {
        id = Math.floor(Math.random() * 10000000);
    }
    performAjaxSubmit(id);

    var table = document.getElementById("attachment_table");

    var row = table.insertRow();

    var cell0 = row.insertCell(0);
    var cell1 = row.insertCell(1);
    var cell2 = row.insertCell(2);
    var cell3 = row.insertCell(3);

// Add some text to the new cells:
    cell0.innerHTML = "<input type='checkbox' name='check_selected_file' class='check_selected_file' " +
        "value='" + id + "' onchange='checkboxesEditDelete(\"check_selected_file\",\"editAttachFile\",\"deleteAttachFile\")'>";
    cell1.innerHTML = "<p>" + document.getElementById("file_name").files[0].name + "</p>";
    var m = new Date();
    var dateString =
        m.getUTCFullYear() + "-" +
        ("0" + (m.getUTCMonth()+1)).slice(-2) + "-" +
        ("0" + m.getUTCDate()).slice(-2) + " " +
        ("0" + m.getUTCHours()).slice(-2) + ":" +
        ("0" + m.getUTCMinutes()).slice(-2) + ":" +
        ("0" + m.getUTCSeconds()).slice(-2);

    cell2.innerHTML = "<p>" + dateString + "</p>";
    cell3.innerHTML = "<p>" + form.comment_attachment.value + "</p>";

    if(form.parentNode.value == 'Edit_Attachment') {
        table.deleteRow(form.tableRowIndex.value);
    }
    cleanAttachForm();
    checkboxesEditDelete('check_selected_file','editAttachFile','deleteAttachFile');
    form.parentNode.style.display = "none";
}
function performAjaxSubmit(id) {

    var comment = document.getElementById("comment_attachment").value,
        fileName  = document.forms.popAttachmentForm.getElementsByTagName('h2')[0].textContent,
        command;
    if(fileName === ""){
        command = 'NEW_FILE';
    }else {
        command = 'EDIT_FILE'
    }

    var sampleFile = document.getElementById("file_name").files[0];
    console.dir(sampleFile);
    if(sampleFile.size <= 1e6) {
        var formdata = new FormData();


        formdata.append("id", id);

        formdata.append("comment_attachment", comment);

        formdata.append("file_name", sampleFile);

        formdata.append("command", command);

        var xhr = new XMLHttpRequest();

        xhr.open("POST", "/zcontact/upload", true);

        xhr.send(formdata);
    }else {
        alert("Максимальный размер файла 1 мегабайт");
        throw new Error("Максимальный размер файла 1 мегабайт");
    }
}


