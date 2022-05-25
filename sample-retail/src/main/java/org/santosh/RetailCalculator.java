package org.santosh;

import org.apache.commons.cli.CommandLine;
import org.santosh.data.BillDetails;
import org.santosh.exception.BillAmountNotAcceptedException;
import org.santosh.exception.RetailerBillAmountNotFoundException;
import org.santosh.utility.RetailerExitCodes;
import org.santosh.utility.RetailerOptions;
import org.santosh.utility.RetailerUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Main application to run the calculations for Retailer!
 *
 */
@SpringBootApplication
public class RetailCalculator implements CommandLineRunner {
	
	private static Logger LOGGER =  (Logger) LoggerFactory.getLogger(RetailCalculator.class.getName());

	@Autowired
	BillDetails billDetails;

	@Override
	public void run(String... args) throws Exception {
		RetailerOptions.parse(args);
		if (RetailerOptions.hasParsedOptions()) {
			handleOptions();
		}
	}

	/**
	 * Delegates actions depending on the passed arguments
	 * 
	 * @param commandLine command line arguments
	 * @throws RetailerBillAmountNotFoundException 
	 */
	private void handleOptions() throws RetailerBillAmountNotFoundException {
		CommandLine commandLine = RetailerOptions.getCommandLine();
		if (commandLine.hasOption("h")) {
			LOGGER.info("Options available for the sample retail application is listed below:");
			RetailerOptions.help(1);
		} else {
			try {
				RetailerUtility.readInput(commandLine, billDetails);
				double totalAmountPayable = RetailerUtility.calculateDiscount(billDetails);
				//Will remove once the logger issues are resolved.
				System.out.println("Total Amount Payable : " + totalAmountPayable);
				LOGGER.info("Total Amount Payable : " + totalAmountPayable);
			} catch (RetailerBillAmountNotFoundException |  BillAmountNotAcceptedException exp) { 
				LOGGER.error("Unable to process the request, please recheck the input and retry.");
				RetailerExitCodes.getExceptionCode(exp);
			}
		} 
	}

	
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(RetailCalculator.class);
		application.addInitializers(new SpringLoggingInitializer());
		application.setBannerMode(Banner.Mode.OFF);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.setLogStartupInfo(false);
		RetailerOptions.parse(args);
		application.run(args);
	}
}
