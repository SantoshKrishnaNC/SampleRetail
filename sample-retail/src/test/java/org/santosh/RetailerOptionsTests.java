package org.santosh;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.santosh.utility.RetailerExitCodes;
import org.santosh.utility.RetailerOptions;

@RunWith(MockitoJUnitRunner.class)
public class RetailerOptionsTests {
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
        RetailerOptions.isTest = true;
        RetailerOptions.parse(args);
        int code = RetailerExitCodes.CLI_MISSING_OPTION_EXCEPTION;
        assertTrue(RetailerOptions.getErrorCodes().contains(code));
        RetailerOptions.isTest = false;
    }
		
    @Test
    public void testHelpOptionOnlyWorks() throws Exception {
        String[] args = {"-h"};
        RetailerOptions.isTest = true;
        RetailerOptions.parse(args);
        assertTrue(RetailerOptions.getErrorCodes().size() == 1);
        RetailerOptions.isTest = false;
    }
    
    @Test
    public void testMessageBillAmountMissing() throws Exception {
        String[] args = {"-em", "-af"};
        RetailerOptions.isTest = true;
        RetailerOptions.parse(args);
        int code = RetailerExitCodes.CLI_MISSING_OPTION_EXCEPTION;
        assertTrue(RetailerOptions.getErrorCodes().contains(code));
        RetailerOptions.isTest = false;
    }
    
    @Test
    public void testContentOptionMissing() throws Exception {
        String[] args = {"-em", "-gr"};
        RetailerOptions.isTest = true;
        RetailerOptions.parse(args);
        int code = RetailerExitCodes.CLI_MISSING_OPTION_EXCEPTION;
        assertTrue(RetailerOptions.getErrorCodes().contains(code));
        RetailerOptions.isTest = false;
    }
    
    @Test
    public void testRequiredOptionsGiven() throws Exception {
    	RetailerOptions.isTest = true;
        String[] args = {"-em", "-gr", "-gra", "100.0", "-ba", "1000.0"};
        RetailerOptions.parse(args);
        RetailerOptions.cleanErrorCodes();
        assertTrue(RetailerOptions.getErrorCodes().isEmpty());
        RetailerOptions.isTest = false;
    }
    
}