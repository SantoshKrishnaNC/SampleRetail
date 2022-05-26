package org.santosh;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.santosh.utility.RetailerExitCodes;
import org.santosh.utility.RetailerOptions;

@RunWith(MockitoJUnitRunner.class)
class RetailerOptionsTests {
    private PrintStream console;
    private ByteArrayOutputStream bytes;

    @BeforeEach
    public void setUp() throws Exception {
    	String key = "test";
        System.setProperty(key, "true");
        bytes   = new ByteArrayOutputStream();		  
        console = System.out;
        System.setOut(new PrintStream(bytes));
    }

    @AfterEach
    public void tearDown() {
    	System.clearProperty("test");
        System.setOut(console);
        RetailerOptions.cleanErrorCodes();
    }	

    @Test
    void testParseEmptyOptionsFails() throws Exception {	
        String[] args = new String[0];
        RetailerOptions.setTest(true);
        RetailerOptions.parse(args);
        int code = RetailerExitCodes.CLI_MISSING_OPTION_EXCEPTION;
        assertTrue(RetailerOptions.getErrorCodes().contains(code));
        RetailerOptions.setTest(false);
    }
		
    @Test
    void testHelpOptionOnlyWorks() throws Exception {
        String[] args = {"-h"};
        RetailerOptions.setTest(true);
        RetailerOptions.parse(args);
        assertEquals(1, RetailerOptions.getErrorCodes().size());
        RetailerOptions.setTest(false);
    }
    
    @Test
    void testMessageBillAmountMissing() throws Exception {
        String[] args = {"-em", "-af"};
        RetailerOptions.setTest(true);
        RetailerOptions.parse(args);
        int code = RetailerExitCodes.CLI_EXCEPTION;
        assertTrue(RetailerOptions.getErrorCodes().contains(code));
        RetailerOptions.setTest(false);
    }
    
    @Test
    void testContentOptionMissing() throws Exception {
        String[] args = {"-em", "-gr"};
        RetailerOptions.setTest(true);
        RetailerOptions.parse(args);
        int code = RetailerExitCodes.CLI_MISSING_OPTION_EXCEPTION;
        assertTrue(RetailerOptions.getErrorCodes().contains(code));
        RetailerOptions.setTest(false);
    }
    
    @Test
    void testRequiredOptionsGiven() throws Exception {
    	RetailerOptions.setTest(true);
        String[] args = {"-em", "-gr", "-gra", "100.0", "-ba", "1000.0"};
        RetailerOptions.parse(args);
        RetailerOptions.cleanErrorCodes();
        assertTrue(RetailerOptions.getErrorCodes().isEmpty());
        RetailerOptions.setTest(false);
    }
    
}