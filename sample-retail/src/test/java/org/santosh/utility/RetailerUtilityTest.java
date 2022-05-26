package org.santosh.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.santosh.data.BillDetails;
import org.santosh.exception.BillAmountNotAcceptedException;

@RunWith(MockitoJUnitRunner.class)
class RetailerUtilityTest {

	@Test
	void testCalculateEmployeeDiscount() throws BillAmountNotAcceptedException {
		BillDetails bd = new BillDetails();
		bd.setBillAmount(20);
		bd.setHasGroceries(true);
		bd.setGroceriesAmount(20);
		bd.setEmployee(true);
		bd.setDiscount(30);
		double calculateDiscount = RetailerUtility.calculateDiscount(bd);

		assertEquals(20, calculateDiscount);

	}

	@Test
	void testCalculateAffiliateDiscount() throws BillAmountNotAcceptedException {

		BillDetails bd = new BillDetails();
		bd.setBillAmount(20);
		bd.setHasGroceries(true);
		bd.setGroceriesAmount(20);
		bd.setAffiliate(true);

		double totalAmountAfterDiscount = RetailerUtility.calculateDiscount(bd);

		assertEquals(20, totalAmountAfterDiscount);

	}

	@Test
	void testCalculateDiscount() throws BillAmountNotAcceptedException {

		BillDetails bd = new BillDetails();
		bd.setBillAmount(20);
		bd.setHasGroceries(true);
		bd.setGroceriesAmount(20);

		double totalAmountAfterDiscount = RetailerUtility.calculateDiscount(bd);

		assertEquals(0, totalAmountAfterDiscount);

	}
}