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
    this._photoName = undefined;

}

Employee.prototype = {
    set id(id) {
        this._id = id;
    },
    get id() {
        return this._id;
    },
    set firstName(firstName) {
        this._firstName = firstName;
    },
    get firstName() {
        return this._firstName;
    },
    set lastName(lastName) {
        this._lastName = lastName;
    },
    get lastName() {
        return this._lastName;
    },
    set  patronymic(patronymic) {
        this._patronymic = patronymic;
    },
    get  patronymic() {
        return this._patronymic;
    },
    set  dateOfBirth(dateOfBirth) {
        this._dateOfBirth = dateOfBirth;
    },
    get  dateOfBirth() {
        return this._dateOfBirth;
    },
    set gender(gender) {
        this._gender = gender;
    },
    get gender() {
        return this._gender;
    },
    set nationality(nationality) {
        this._nationality = nationality;
    },
    get nationality() {
        return this._nationality;
    },
    set familyStatus(familyStatus) {
        this._familyStatus = familyStatus;
    },
    get familyStatus() {
        return this._familyStatus;
    },
    set webSite(webSite) {
        this._webSite = webSite;
    },
    get webSite() {
        return this._webSite;
    },
    set email(email) {
        this._email = email;
    },
    get email() {
        return this._email;
    },
    set workPlace(workPlace) {
        this._workPlace = workPlace;
    },
    get workPlace() {
        return this._workPlace;
    },
    set address(address) {
        this._address = address;
    },
    get address() {
        return this._address;
    },
    set phoneList(phoneList) {
        this._phoneList = phoneList;
    },
    get phoneList() {
        return this._phoneList;
    },
    set attachmentList(attachmentList) {
        this._attachmentList = attachmentList;
    },
    get attachmentList() {
        return this._attachmentList;
    },
    set photoName(photoName) {
        this._photoName = photoName;
    },
    get photoName() {
        return this._photoName;
    }
};
