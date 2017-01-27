/**
 * Created by aefrd on 26.01.2017.
 */
function Employee() {
    this._id = undefined;
    this._firstName = undefined;
    this._lastName = undefined;
    this._firstName = undefined;
    this._lastName = undefined;
    this._patronymic = undefined;
    this._dateOfBirth = undefined;
    this._gender = undefined;
    this._nationality = undefined;
    this._familyStatus = undefined;
    this._webSite = undefined;
    this._email = undefined;
    this._workPlace = undefined;
    this._address = undefined;
    this._phoneList = [];
    this._attachmentList = [];
    this._photo = [];

}

Employee.prototype = {
    setId : function (id) {
        this._id = id;
    },
    getId : function () {
        return this._id;
    }
};
Employee.prototype = {
    setFirstName : function (firstName) {
        this._firstName = firstName;
    },
    getFirstName : function () {
        return this._firstName;
    }
};
Employee.prototype = {
    setLastName : function (lastName) {
        this._lastName = lastName;
    },
    getLastName : function () {
        return this._lastName;
    }
};
Employee.prototype = {
    setPatronymic: function (patronymic) {
        this._patronymic = patronymic;
    },
    getPatronymic: function () {
        return this._patronymic;
    }
};
Employee.prototype = {
    setDateOfBirth : function (dateOfBirth) {
        this._dateOfBirth = dateOfBirth;
    },
    getDateOfBirth : function () {
        return this._dateOfBirth;
    }
};
Employee.prototype = {
    setGender : function (gender) {
        this._gender = gender;
    },
    getGender : function () {
        return this._gender;
    }
};
Employee.prototype = {
    setNationality : function (nationality) {
        this._nationality = nationality;
    },
    getNationality : function () {
        return this._nationality;
    }
};
Employee.prototype = {
    setFamilyStatus : function (familyStatus) {
        this._familyStatus = familyStatus;
    },
    getFamilyStatus : function () {
        return this._familyStatus;
    }
};
Employee.prototype = {
    setWebSite : function (webSite) {
        this._webSite = webSite;
    },
    getWebSite : function () {
        return this._webSite;
    }
};
Employee.prototype = {
    setEmail : function (email) {
        this._email = email;
    },
    getEmail : function () {
        return this._email;
    }
};
Employee.prototype = {
    setWorkPlace : function (workPlace) {
        this._workPlace = workPlace;
    },
    getWorkPlace : function () {
        return this._workPlace;
    }
};
Employee.prototype = {
    setAddress : function (address) {
        this._address = address;
    },
    getAddress : function () {
        return this._address;
    }
};
Employee.prototype = {
    setPhoneList : function (phoneList) {
        this._phoneList = phoneList;
    },
    getPhoneList : function () {
        return this._phoneList;
    }
};
Employee.prototype = {
    setAttachmentList : function (attachmentList) {
        this._attachmentList = attachmentList;
    },
    getAttachmentList : function () {
        return this._attachmentList;
    }
};
Employee.prototype = {
    setPhoto : function (photo) {
        this._photo = photo;
    },
    getPhoto : function () {
        return this._photo;
    }
};
