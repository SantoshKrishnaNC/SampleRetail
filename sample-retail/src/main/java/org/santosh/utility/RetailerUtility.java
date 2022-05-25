package org.santosh.utility;

import org.apache.commons.cli.CommandLine;
import org.santosh.RetailerBillAmountNotFoundException;
import org.santosh.data.BillDetails;

public class RetailerUtility {

	public static void readInput(final CommandLine commandLine, final BillDetails billDetails)
			throws RetailerBillAmountNotFoundException {
		if (commandLine.hasOption("ba")) {
			billDetails.setBillAmount(Double.parseDouble(commandLine.getOptionValue("ba")));
		} else {
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
	}

	public static double calculateDiscount(BillDetails billDetails) {
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
				discountAmount = ((int) billAmount / 100) * 5;
				totalAmountPayable = billAmount - discountAmount;
			}
		} else {
			discountAmount = (billAmount - groceryAmount);
			totalAmountPayable = billAmount - (discountAmount * discountPercentage/100);
		}
		return totalAmountPayable;
	}

}
