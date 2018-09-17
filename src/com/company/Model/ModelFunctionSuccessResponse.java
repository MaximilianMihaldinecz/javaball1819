package com.company.Model;

/**
 * Represents a return value from a method.
 * Used as a typical communication between model and controller
 */
public class ModelFunctionSuccessResponse
{
    /**
     * True if the operation was successfull
     */
    public boolean success = true;

    /**
     * Contains error message if there was an error. Otherwise null
     */
    public String errormsg = null;
}
