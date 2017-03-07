window.onload=function(){
    if (haveMessages()) {
        InfoPopup();
    }
};


function InfoPopup() {
    var mainDiv = createMainDiv();
    addCloseButton();
    addTitle();
    addErrors();
    addInfo();

    function createMainDiv() {
        var mainDiv = document.createElement('div');
        mainDiv.id = 'info';
        mainDiv.classList.add('popup');
        mainDiv.style.display = 'block';
        document.body.appendChild(mainDiv);
        return mainDiv;
    }

    function addCloseButton() {
        var buttonClose = document.createElement('a');
        buttonClose.href = '#';
        buttonClose.onclick = closeInfo;
        mainDiv.appendChild(buttonClose);
    }

    function addTitle() {
        var title = document.createElement('h6');
        title.appendChild(document.createTextNode('Сообщения: '));
        mainDiv.appendChild(title);
    }

    function closeInfo() {
        document.body.removeChild(document.getElementById('info'));
    }

    function addErrors() {
        var infoDiv = document.createElement("div");
        mainDiv.appendChild(infoDiv);
        var errors = document.getElementById("errors");
        for(var i = 0; i < errors.elements.length; i++){
            var label = document.createElement("label");
            label.innerHTML = errors.elements[i].value + "\n";
            label.classList.add("errorLabel");
            infoDiv.appendChild(label);
        }
    }

    function addInfo() {
        var infoDiv = document.createElement("div");
        mainDiv.appendChild(infoDiv);
        var errors = document.getElementById("infoMessages");
        for(var i = 0; i < errors.elements.length; i++){
            var label = document.createElement("label");
            label.innerHTML = errors.elements[i].value + "\n";
            label.classList.add("messageLabel");
            infoDiv.appendChild(label);
        }
    }

}
function haveMessages() {
    var errors = document.getElementById("errors");
    if(errors.elements.length > 0){
        return true;
    }
    return false;
}

