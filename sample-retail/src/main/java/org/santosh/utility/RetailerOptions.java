package org.santosh.utility;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RetailerOptions {
	private static CommandLine commandLine = null;
	private static Options options = null;
	private static OptionGroup billAmountGroup = null;
	private static OptionGroup typeGroup = null;
	
	

	// Used for testing purposes
	private static boolean isTest;
	private static List<Integer> testErrorCodes = new ArrayList<>();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RetailerOptions.class.getName());

	public static List<Integer> getErrorCodes() {
		return testErrorCodes;
	}

	public static void addErrorCode(int errorCode) {
		testErrorCodes.add(errorCode);
	}

	public static void cleanErrorCodes() {
		testErrorCodes.clear();
	}

	public static CommandLine getCommandLine() {
		return commandLine;
	}
	
	// private constructor so that we cannot create objects.
	private RetailerOptions() {}

	/**
	 * Creates the options needed by command line interface
	 * 
	 * @return the options RetailCalculator can handle
	 */
	public static Options createRetailerOptions() {
		options = new Options();
		billAmountGroup = new OptionGroup();
		Option billAmountOpt = new Option("ba", "bill_amount", true, "total amount of the retails");
		billAmountGroup.addOption(billAmountOpt);
		options.addOptionGroup(billAmountGroup);
		typeGroup = new OptionGroup();
		typeGroup.addOption(new Option("em", "is_employee", false, "If the customer is an Employee, use this option."));
		typeGroup.addOption(
				new Option("af", "is_affiliate", false, "If the customer is an Affiliate, use this option."));
		typeGroup.addOption(new Option("ca", "customer_association", true,
				"Customer association in years, ex: 1, 2, etc Integer values only"));
		options.addOptionGroup(typeGroup);
		OptionGroup groceriesGroup = new OptionGroup();
		groceriesGroup.addOption(
				new Option("gr", "contains_groceries", false, "If the items contains groceries, use this option."));
		options.addOption(new Option("gra", "groceries_amount", true, "total amount for groceries"));
		options.addOptionGroup(groceriesGroup);
		options.addOption(new Option("h", "help", false, "Help"));
		return options;
	}

	/**
	 * Prints the help for this application and exits.
	 */
	public static void help(int errorCode) {
		// This prints out some help
		HelpFormatter formater = new HelpFormatter();
		formater.printHelp("java -jar", options);
		exit(errorCode);
	}

	/**
	 * Wrapper to call system exit making class easier to test.
	 * 
	 * @param errorCode
	 */
	public static void exit(int errorCode) {
		if (isTest())
			addErrorCode(errorCode);
		else
			System.exit(errorCode);
	}

	/**
	 * Parse the given arguments and act on them
	 * 
	 * @param args command line arguments
	 */
	public static void parse(String[] args) {
		createRetailerOptions();
		CommandLineParser parser = new DefaultParser();
		try {
			commandLine = parser.parse(options, args);
			afterParseChecks();
		} catch (Exception e) {
			LOGGER.error("Error in parsing the parameters, message : {}", e.getMessage());
			help(RetailerExitCodes.getExceptionCode(e));
		}
	}

	public static void afterParseChecks() throws MissingOptionException {
		if (commandLine.hasOption("h")) {
			LOGGER.info("You passed help flag.");
			help(0);
		} else {
			checkRequiredOptions();
		}
	}

	public static void checkRequiredOptions() throws MissingOptionException {
		OptionGroup[] groups = { billAmountGroup, typeGroup };
		for (OptionGroup group : groups) {
			ArrayList<Option> groupOptions = new ArrayList<>(group.getOptions());
			boolean groupIsGiven = false;
			for (Option option : groupOptions) {
				if (commandLine.hasOption(option.getOpt())) {
					groupIsGiven = true;
					break;
				}
			}
			if (!groupIsGiven) {
				throw new MissingOptionException(groupOptions);
			}
		}
	}

	/**
	 * Check if the CLI contain any options
	 * 
	 * @return true if options exists otherwise false
	 */
	public static boolean hasParsedOptions() {
		if (commandLine == null)
			return false;
		return commandLine.getOptions().length > 0;
	}

	public static boolean isTest() {
		return isTest;
	}

	public static void setTest(boolean isTest) {
		RetailerOptions.isTest = isTest;
	}

}