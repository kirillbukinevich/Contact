package com.itechart.bukinevi.logic.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

public class RequestUtils {
    private RequestUtils(){}

    public static void addErrorMessage(HttpServletRequest request, String message){
        List<String> errors = getErrorMessages(request);
        errors.add(message);
    }

    private static List<String> getErrorMessages(HttpServletRequest request){
        @SuppressWarnings("unchecked")
        List<String> errors = (List<String>) request.getAttribute("errors");
        if(errors == null){
            errors = new LinkedList<>();
            request.setAttribute("errors", errors);
        }
        return errors;
    }
}
