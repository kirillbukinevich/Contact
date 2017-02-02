/**
 * Created by aefrd on 27.01.2017.
 */
function Attachment() {
    this._id = undefined;
    this._rowIndex = undefined;
    this._fileName = undefined;
    this._loadDate = undefined;
    this._comment = undefined;
}
Attachment.prototype = {
    set id(id){
        this._id = id;
    },
    get id(){
        return this._id;
    },
    set rowIndex(rowIndex){
        this._rowIndex = rowIndex;
    },
    get rowIndex(){
        return this._rowIndex;
    },
    set fileName(fileName){
        this._fileName = fileName;
    },
    get fileName(){
        return this._fileName;
    },
    set loadDate(loadDate){
        this._loadDate = loadDate;
    },
    get loadDate(){
        return this._loadDate;
    },
    set comment(comment){
        this._comment = comment;
    },
    get comment(){
        return this._comment;
    }
};