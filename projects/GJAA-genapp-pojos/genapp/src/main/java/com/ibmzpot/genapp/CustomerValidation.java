package com.ibmzpot.genapp;

import com.ibm.cics.server.Task;
import com.ibm.cics.server.Channel;
import com.ibm.cics.server.Container;
import com.ibm.cics.server.CicsConditionException;

public class CustomerValidation
{ 
    
    public static void main(String[] args) {
 
        Container outputContainer; 
        String inputString;
        String outputString;

        Task currentTask = Task.getTask();

        try {
            Channel currentChannel = currentTask.getCurrentChannel();
	    if (currentChannel != null) {
               Container inputContainer = currentChannel.getContainer("INPUT");
               if (inputContainer != null) {
                  inputString = inputContainer.getString();
                  outputString = isNumeric(inputString) ? "valid" : "error";   
               } else {
                 outputString = "error";
	       }
               outputContainer = currentChannel.createContainer("OUTPUT");
               outputContainer.putString(outputString); 
            }
        } catch (CicsConditionException cce) {       
            throw new RuntimeException(cce);
        }
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
           return false;
        }
        try {
           double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
           return false;
        }
        return true;
    }

}
