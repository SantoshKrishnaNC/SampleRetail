package org.santosh.utility;

import org.apache.commons.cli.CommandLine;
import org.santosh.data.BillDetails;
import org.santosh.exception.BillAmountNotAcceptedException;
import org.santosh.exception.RetailerBillAmountNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is to handle the discounts on the bill amount
 * and also to load the input into Bill Details object.
 * 
 * @author ncsan
 *
 */
public class RetailerUtility {
	
	private static Logger LOGGER =  (Logger) LoggerFactory.getLogger(RetailerUtility.class.getName());

	/**
	 * This method is to take inputs from command line and populate the BillDetails data object 
	 * 
	 * @param commandLine
	 * @param billDetails
	 * @throws RetailerBillAmountNotFoundException
	 * @throws BillAmountNotAcceptedException
	 */
	public static void readInput(final CommandLine commandLine, final BillDetails billDetails)
			throws RetailerBillAmountNotFoundException, BillAmountNotAcceptedException {
		if (commandLine.hasOption("ba")) {
			billDetails.setBillAmount(Double.parseDouble(commandLine.getOptionValue("ba")));
		} else {
			LOGGER.error("Bill amount cannot be empty");
			throw new RetailerBillAmountNotFoundException("Bill amount cannot be empty");
		}
		if (commandLine.hasOption("em")) {
			billDetails.setEmployee(true);
			billDetails.setAffiliate(false);
			billDetails.setDiscount(30);
		} else if (commandLine.hasOption("af")) {
			billDetails.setEmployee(false);
			billDetails.setAffiliate(true);
			billDetails.setDiscount(10);
		} else if (commandLine.hasOption("ca")) {
			final int customerAssocaitionInYears = Integer.parseInt(commandLine.getOptionValue("ca"));
			billDetails.setAffiliate(false);
			billDetails.setEmployee(false);
			billDetails.setCustomerEngagementInYears(customerAssocaitionInYears);
			billDetails.setDiscount(5);
		}
		if (commandLine.hasOption("gr")) {
			final double groceryAmount = Double.parseDouble(commandLine.getOptionValue("gra"));
			billDetails.setHasGroceries(true);
			billDetails.setGroceriesAmount(groceryAmount);
		} else {
			billDetails.setHasGroceries(false);
			billDetails.setGroceriesAmount(0);
		}
		if(billDetails.getBillAmount()<0 || billDetails.getGroceriesAmount()<0) {
			LOGGER.error("Either bill amount or groceries amount is less than 0, please check.");
			throw new BillAmountNotAcceptedException("Check either bill amount or the grocery amount. They should be greater than 0.");
		}
	}

	/**
	 * This method calculates the discount based on if the customer is an 
	 * employee or if an employee is an affiliate or if the customer is having
	 * more than 2 years of engagement.
	 * 
	 * @param billDetails
	 * @return total bill amount to be paid after discounts
	 * @throws BillAmountNotAcceptedException
	 */
	public static double calculateDiscount(BillDetails billDetails) throws BillAmountNotAcceptedException {
		double groceryAmount = 0;
		double billAmount = 0;
		double totalAmountPayable = 0;
		int discountPercentage = 0;
		double discountAmount = 0;
		if (billDetails.isHasGroceries()) {
			groceryAmount = billDetails.getGroceriesAmount();
		}
		if (billDetails.isEmployee()) {
			discountPercentage = 30;
		} else if (billDetails.isAffiliate()) {
			discountPercentage = 10;
		} else if (billDetails.getCustomerEngagementInYears() >= 2) {
			discountPercentage = 5;
		}
		billAmount = billDetails.getBillAmount();
		if (discountPercentage == 0) {
			if (billAmount >= 100) {
				discountAmount = ((double) billAmount / 100) * 5;
				totalAmountPayable = billAmount - discountAmount;
			}
		} else {
			discountAmount = (billAmount - groceryAmount);
			if(discountAmount < 0) {
				throw new BillAmountNotAcceptedException("Groceries amount should be less than total bill amount");
			}
			totalAmountPayable = billAmount - (discountAmount * discountPercentage/100);
		}
		return totalAmountPayable;
	}

}
