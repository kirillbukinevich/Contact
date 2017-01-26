function Address() {
    this._id = undefined;
    this._countryName = undefined;
    this._cityName = undefined;
    this._streetName = undefined;
    this._houseNumber = undefined;
    this._flatNumber = undefined;
    this._index = undefined;
}
Address.prototype = {
    setId : function (id) {
        this._id = id;
    },
    getId : function () {
        return this._id;
    }
};
Address.prototype = {
    setCountryName : function (countryName) {
        this._countryName = countryName;
    },
    getCountryName : function () {
        return this._countryName;
    }
};
Address.prototype = {
    setCityName : function (cityName) {
        this._cityName = cityName;
    },
    getCityName : function () {
        return this._cityName;
    }
};
Address.prototype = {
    setStreetName : function (streetName) {
        this._streetName = streetName;
    },
    getStreetName : function () {
        return this._streetName;
    }
};
Address.prototype = {
    setHouseNumber : function (houseNumber) {
        this._houseNumber = houseNumber;
    },
    getHouseNumber : function () {
        return this._houseNumber;
    }
};
Address.prototype = {
    setFlatNumber : function (flatNumber) {
        this._flatNumber = flatNumber;
    },
    getFlatNumber : function () {
        return this._flatNumber;
    }
};
Address.prototype = {
    setIndex : function (index) {
        this._index = index;
    },
    getIndex : function () {
        return this._index;
    }
};