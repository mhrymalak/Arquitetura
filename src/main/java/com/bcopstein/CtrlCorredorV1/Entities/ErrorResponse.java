package com.bcopstein.CtrlCorredorV1.Entities;

import java.util.ArrayList;

public class ErrorResponse {
    private final String errorTitle;
    private final ArrayList<String> errors;

    public ErrorResponse(String message){
        this.errors = new ArrayList<String>();
        this.errorTitle = message;
    }

    public void AddError(String error){
        this.errors.add(error);
    }
    public String getErrorResponse(){
        return this.errorTitle + ": {" + this.errors.toString() + "}";
    };
}
