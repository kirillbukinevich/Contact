function savePhotoChange() {
    var form = document.forms.popPhotoForm;
    performPhotoAjaxSubmit();

    form.parentNode.style.display = "none";
}

function deletePhoto() {
    var default_img = document.getElementById('default_avatar');
    default_img.style.display = 'inline-block';

    var img = default_img.parentNode.childNodes[3];
    img.style.display = "none";
    img.textContent = 'delete';

    var form = document.forms.popPhotoForm;
    form.parentNode.style.display = "none";
}
function performPhotoAjaxSubmit() {

    console.dir(document.getElementById("photo_name").files[0]);
    var sampleFile = document.getElementById("photo_name").files[0];
    var fileName = document.getElementById("photo_name").files[0].name;

    var fr = new FileReader();
    fr.onload = function () {
        var defaultImage = document.getElementById('default_avatar');
        defaultImage.style.display = 'none';
        var img = document.getElementById("avatar");
        img.src = fr.result;
        img.style.display = 'inline-block';
        img.textContent = fileName;
    };
    fr.readAsDataURL(sampleFile);

    var formdata = new FormData();

    formdata.append("photo", sampleFile);

    formdata.append("photo_name", fileName);

    formdata.append("command", 'UPDATE_PHOTO');

    var xhr = new XMLHttpRequest();

    xhr.open("POST", "/zcontact/upload", true);

    xhr.send(formdata);
}


