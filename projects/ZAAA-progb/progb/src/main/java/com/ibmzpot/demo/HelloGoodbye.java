package com.ibmzpot.demo;

import com.ibm.cics.server.Task;
import com.ibm.cics.server.Program;
import com.ibm.cics.server.Channel;
import com.ibm.cics.server.Container;
import com.ibm.cics.server.CicsConditionException;

public class HelloGoodbye {

    // PROGB
    public static void main(String[] args) {

        String helloMessage = "Hello from Java                 ";
        String goodbyeMessage = "Goodbye from Java               "; 

        Channel currentChannel;        // Current Channel - created by PROGA, shared with PROGB and PROGC
        Container progbInput;          // PROGB Input Container - created by PROGA
        Container progbOutput;         // PROGB Output Container - created by PROGB
        Container progcInput;          // PROGC Input Container - created by PROGB
        Container progcOutput;         // PROGC Output Container - created by PROGC
        String progaMessage;           // Content of PROGB Input Container
        String progcMessage;           // Content of PROGC Output Container

        Program progc = new Program();
        progc.setName("PROGC");
        Task currentTask = Task.getTask();

        try {
            currentChannel = currentTask.getCurrentChannel();
	    if (currentChannel != null) {
               // GET CONTAINER
               progbInput = currentChannel.getContainer("PROGB_INPUT");
               if (progbInput != null) {
                  progaMessage = progbInput.getString();
                  System.out.println(progaMessage); 
	       }
               // PUT CONTAINER
               progcInput = currentChannel.createContainer("PROGC_INPUT");
               progcInput.putString(helloMessage); 
               // LINK
               progc.link(currentChannel);
               // GET CONTAINER
               progcOutput = currentChannel.getContainer("PROGC_OUTPUT"); 
               if (progcOutput != null) {
                  progcMessage = progcOutput.getString();
                  System.out.println(progcMessage); 
	       }
               // PUT CONTAINER
               progbOutput = currentChannel.createContainer("PROGB_OUTPUT");
               progbOutput.putString(goodbyeMessage); 
            }
        } catch (CicsConditionException cce) {       
            throw new RuntimeException(cce);
        }
    }
}