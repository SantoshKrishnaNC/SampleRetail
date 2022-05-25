package org.santosh.utility;

import org.apache.commons.cli.MissingOptionException;

public class RetailerExitCodes {

	public static int CLI_EXCEPTION = 1;
	public static int CLI_MISSING_OPTION_EXCEPTION = 2;
	
	//Return the error code and message
	public static int getExceptionCode(Exception e) {
		if (e instanceof MissingOptionException) {
			return CLI_MISSING_OPTION_EXCEPTION;
		}
		return CLI_EXCEPTION;
	}
}
