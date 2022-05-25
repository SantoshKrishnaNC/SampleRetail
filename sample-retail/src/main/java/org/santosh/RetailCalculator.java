package org.santosh;

import org.apache.commons.cli.CommandLine;
import org.santosh.data.BillDetails;
import org.santosh.utility.RetailerExitCodes;
import org.santosh.utility.RetailerOptions;
import org.santosh.utility.RetailerUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
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
	
	Logger LOGGER =  (Logger) LoggerFactory.getLogger(RetailCalculator.class.getName());

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
		handleLogging(commandLine);
		if (commandLine.hasOption("h")) {
			System.out.println("Options available for the sample retail application is listed below:");
			RetailerOptions.help(1);
		} else {
			try {
				RetailerUtility.readInput(commandLine, billDetails);
				double totalAmountPayable = RetailerUtility.calculateDiscount(billDetails);
				System.out.println("Total Amount Payable : " + totalAmountPayable);
			} catch (RetailerBillAmountNotFoundException exp) { 
				System.err.println("Unable to process the request, please recheck the input and retry.");
				RetailerExitCodes.getExceptionCode(exp);
			}
		} 
	}

	/**
	 * This method is used to set the debug logging on the application.
	 * 
	 * @param commandLine
	 */
	private void handleLogging(CommandLine commandLine) {
		if (!commandLine.hasOption("-d")) {
			System.setProperty("logging.level.root", "OFF");
			Logger log = (Logger) LoggerFactory.getLogger("ROOT");
			log.atLevel(Level.DEBUG);
		}
	}

	public static void main(String[] args) throws Exception {
		SpringApplication application = new SpringApplication(RetailCalculator.class);
		application.addInitializers(new SpringLoggingInitializer());
		application.setBannerMode(Banner.Mode.OFF);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.setLogStartupInfo(false);
		RetailerOptions.parse(args);
		application.run(args);
	}
}
