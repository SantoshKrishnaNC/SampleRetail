package org.santosh.data;

import org.springframework.stereotype.Component;

/**
 * Class to hold values for Bill details
 * 
 * @author ncsan
 *
 */
@Component
public class BillDetails {

	private double billAmount;
	private boolean isEmployee;
	private boolean isAffiliate;
	private int customerEngagementInYears;
	private int discount;
	private boolean hasGroceries;
	private double groceriesAmount;

	public boolean isHasGroceries() {
		return hasGroceries;
	}

	public void setHasGroceries(boolean hasGroceries) {
		this.hasGroceries = hasGroceries;
	}

	public double getGroceriesAmount() {
		return groceriesAmount;
	}

	public void setGroceriesAmount(double groceriesAmount) {
		this.groceriesAmount = groceriesAmount;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}

	public boolean isEmployee() {
		return isEmployee;
	}

	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}

	public boolean isAffiliate() {
		return isAffiliate;
	}

	public void setAffiliate(boolean isAffiliate) {
		this.isAffiliate = isAffiliate;
	}

	public int getCustomerEngagementInYears() {
		return customerEngagementInYears;
	}

	public void setCustomerEngagementInYears(int customerEngagementInYears) {
		this.customerEngagementInYears = customerEngagementInYears;
	}

}
