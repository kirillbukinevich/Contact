function ContactPhone() {
    this._id = undefined;
    this._rowIndex = undefined;
    this._codeCountry = undefined;
    this._codeOperator = undefined;
    this._number = undefined;
    this._type = undefined;
    this._comment = undefined;
}
ContactPhone.prototype = {
    set id(id){
        this._id = id;
    },
    get id(){
        return this._id;
    },
    set rowIndex(rowIndex) {
        this._rowIndex = rowIndex;
    },
    get rowIndex() {
        return this._rowIndex;
    },
    set codeCountry(codeCountry) {
        this._codeCountry = codeCountry;
    },
    get codeCountry() {
        return this._codeCountry;
    },
    set codeOperator(codeOperator) {
        this._codeOperator = codeOperator;
    },
    get codeOperator() {
        return this._codeOperator;
    },
    set number(number) {
        this._number = number;
    },
    get number() {
        return this._number;
    },
    set type(type) {
        this._type = type;
    },
    get type() {
        return this._type;
    },
    set comment(comment) {
        this._comment = comment;
    },
    get comment() {
        return this._comment;
    }
};
