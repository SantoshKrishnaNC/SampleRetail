package org.santosh.utility;

import org.apache.commons.cli.MissingOptionException;
import org.santosh.exception.BillAmountNotAcceptedException;
import org.santosh.exception.RetailerBillAmountNotFoundException;

public class RetailerExitCodes {

	public static final int CLI_EXCEPTION = 1;
	public static final int CLI_MISSING_OPTION_EXCEPTION = 2;
	public static final int NEGATIVE_AMOUNT_EXCEPTION = 3;
	public static final int BILL_AMOUNT_NOT_PROVIDED_EXCEPTION = 4;
	
	private RetailerExitCodes() {}
	
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
