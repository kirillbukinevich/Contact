package com.itechart.bukinevi.logic.exceptions;

public class ExecutingCommandsException extends RuntimeException {

    private static final long serialVersionUID = 4192898854716106957L;

    public ExecutingCommandsException(){
        super();
    }

    public ExecutingCommandsException(String msg, Exception e){
        super(msg, e);
    }

    public ExecutingCommandsException(String msg){
        super(msg);
    }

    public ExecutingCommandsException(Exception e){
        super(e);
    }

}
