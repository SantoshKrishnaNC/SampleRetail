package org.santosh;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.santosh.utility.RetailerExitCodes;
import org.santosh.utility.RetailerOptions;


public class RetailerOptionsUnitTests {
    private PrintStream console;
    private ByteArrayOutputStream bytes;

    @Before 
    public void setUp() throws Exception {
    	String key = "test";
        System.setProperty(key, "true");
        bytes   = new ByteArrayOutputStream();		  
        console = System.out;
        System.setOut(new PrintStream(bytes));
    }

    @After
    public void tearDown() {
    	System.clearProperty("test");
        System.setOut(console);
        RetailerOptions.cleanErrorCodes();
    }	

    @Test
    public void testParseEmptyOptionsFails() throws Exception {	
        String[] args = new String[0];	    

        RetailerOptions.parse(args);
        int code = RetailerExitCodes.CLI_MISSING_OPTION_EXCEPTION;
        assertTrue(RetailerOptions.getErrorCodes().contains(code));
    }
		
    @Test
    public void testHelpOptionOnlyWorks() throws Exception {
        String[] args = {"-h"};
        RetailerOptions.parse(args);
        assertTrue(RetailerOptions.getErrorCodes().contains(0));
        assertTrue(RetailerOptions.getErrorCodes().size() == 1);
    }
	
    @Test
    public void testHelpOptionWorks() throws Exception {
        String[] args = {"-h", "-r", "respons file"};
        RetailerOptions.parse(args);
        assertTrue(RetailerOptions.getErrorCodes().contains(0));
        assertTrue(RetailerOptions.getErrorCodes().size() == 1);        	
    }
    
    @Test
    public void testMessageBillAmountMissing() throws Exception {
        String[] args = {"-em", "-af"};
        RetailerOptions.parse(args);
        int code = RetailerExitCodes.CLI_MISSING_OPTION_EXCEPTION;
        assertTrue(RetailerOptions.getErrorCodes().contains(code));
    }
    
    @Test
    public void testContentOptionMissing() throws Exception {
        String[] args = {"-em", "-gr"};
        RetailerOptions.parse(args);
        int code = RetailerExitCodes.CLI_MISSING_OPTION_EXCEPTION;
        assertTrue(RetailerOptions.getErrorCodes().contains(code));
    }  
    
    @Test
    public void testRequiredOptionsGiven() throws Exception {
        String[] args = {"-em", "-gr", "-gra", "100.0", "-ba", "1000.0"};
        RetailerOptions.parse(args);
        assertTrue(RetailerOptions.getErrorCodes().isEmpty());
    }
    
}