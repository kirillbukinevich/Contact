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
    setId: function (id) {
        this._id = id;
    },
    getId: function () {
        return this._id;
    }
};
Address.prototype = {
    set countryName(countryName) {
        this._countryName = countryName;
    },
    get countryName() {
        return this._countryName;
    },
    set cityName(cityName) {
        this._cityName = cityName;
    },
    get cityName() {
        return this._cityName;
    },
    set streetName(streetName) {
        this._streetName = streetName;
    },
    get streetName() {
        return this._streetName;
    },
    set houseNumber(houseNumber) {
        this._houseNumber = houseNumber;
    },
    get houseNumber() {
        return this._houseNumber;
    },
    set flatNumber(flatNumber) {
        this._flatNumber = flatNumber;
    },
    get flatNumber() {
        return this._flatNumber;
    },
    set index(index) {
        this._index = index;
    },
    get index() {
        return this._index;
    }
};