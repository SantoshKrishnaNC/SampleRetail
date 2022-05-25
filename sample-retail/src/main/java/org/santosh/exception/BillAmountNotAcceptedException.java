package org.santosh.exception;

/**
 * Custom Exception to handle negative scenarios with bill payments.
 * 
 * @author ncsan
 *
 */
public class BillAmountNotAcceptedException extends Exception {

	private static final long serialVersionUID = 1L;

	public BillAmountNotAcceptedException(String message) {
		super(message);
	}

}
