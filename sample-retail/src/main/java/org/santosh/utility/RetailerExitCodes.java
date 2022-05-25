package org.santosh.utility;

import org.apache.commons.cli.MissingOptionException;
import org.santosh.exception.BillAmountNotAcceptedException;
import org.santosh.exception.RetailerBillAmountNotFoundException;

public class RetailerExitCodes {

	public static int CLI_EXCEPTION = 1;
	public static int CLI_MISSING_OPTION_EXCEPTION = 2;
	public static int NEGATIVE_AMOUNT_EXCEPTION = 3;
	public static int BILL_AMOUNT_NOT_PROVIDED_EXCEPTION = 4;
	
	//Return the error code and message
	public static int getExceptionCode(Exception e) {
		if (e instanceof MissingOptionException) {
			return CLI_MISSING_OPTION_EXCEPTION;
		} else if (e instanceof BillAmountNotAcceptedException) {
			return NEGATIVE_AMOUNT_EXCEPTION;
		} else if (e instanceof RetailerBillAmountNotFoundException) {
			return BILL_AMOUNT_NOT_PROVIDED_EXCEPTION;
		}
		return CLI_EXCEPTION;
	}
}
