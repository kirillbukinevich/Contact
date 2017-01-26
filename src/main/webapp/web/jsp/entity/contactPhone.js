function ContactPhone() {
    this._id = undefined;
    this._employeeID = undefined;
    this._codeCountry = undefined;
    this._codeOperator = undefined;
    this._number = undefined;
    this._type = undefined;
    this._comment = undefined;
}
ContactPhone.prototype = {
    setId : function (id) {
        this._id = id;
    },
    getId : function () {
        return this._id;
    }
};
ContactPhone.prototype = {
    setEmployeeID : function (employeeID) {
        this._employeeID = employeeID;
    },
    getEmployeeID : function () {
        return this._employeeID;
    }
};
ContactPhone.prototype = {
    setCodeCountry : function (codeCountry) {
        this._codeCountry = codeCountry;
    },
    getCodeCountry : function () {
        return this._codeCountry;
    }
};
ContactPhone.prototype = {
    setCodeOperator : function (codeOperator) {
        this._codeOperator = codeOperator;
    },
    getCodeOperator : function () {
        return this._codeOperator;
    }
};
ContactPhone.prototype = {
    setNumber : function (number) {
        this._number = number;
    },
    getNumber : function () {
        return this._number;
    }
};
ContactPhone.prototype = {
    setType : function (type) {
        this._type = type;
    },
    getType : function () {
        return this._type;
    }
};
ContactPhone.prototype = {
    setComment : function (comment) {
        this._comment = comment;
    },
    getComment : function () {
        return this._comment;
    }
};
