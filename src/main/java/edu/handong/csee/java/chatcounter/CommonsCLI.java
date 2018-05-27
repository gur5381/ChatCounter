package edu.handong.csee.java.chatcounter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * This is a class for using Commons CLI.
 * In this class, we create options for help and print the result of the output file.
 * If you put [address] [file] -p, then you can see the result of counting on the screen. 
 * @author jeongjinhyeog
 *
 */
public class CommonsCLI {
	String path;
	String file;
	boolean print;
	boolean help;

	/**
	 * This is a method which receives an array of arguments and run the CLI based on the arguments.
	 * If no argument is given, then it prints help and returns false!
	 * @param args
	 * @return
	 */
	public boolean runCLI(String[] args) {
		Options options = createOptions();

		if(parseOptions(options, args)) {
			if(help) {
				printHelp(options);
			}
			return true;
		}

		return false;
	}

	/**
	 * This method actually creates options.
	 * In this 4 options are created.
	 * I decided that the first argument should be the path and the second argument a target file name.
	 * @return
	 */
	public Options createOptions() {
		Options options = new Options();

		options.addOption("Address", true, "Address of a folder containing files.");

		options.addOption("File", true, "Target file to receive data");

		options.addOption(Option.builder("p").longOpt("print")
				.desc("Print the result of Message Counting!")
				.build());
		options.addOption(Option.builder("h").longOpt("help")
				.desc("Help")
				.build());

		return options;
	}

	/**
	 * This is Option parsing method.
	 * the first and second argument data goes to path and file.
	 * If h or p options are given from the argument, help and print changes to true.
	 * @param options
	 * @param args
	 * @return
	 */
	public boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {
			CommandLine cmd = parser.parse(options, args);
			path = args[0];
			file = args[1];
			help = cmd.hasOption("h");
			print = cmd.hasOption("p");
		}catch(Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}
	/**
	 * This is a method for printing the help messages.
	 * I put the header of this program and the footer for those who want to report issues of my code.
	 * @param options
	 */
	public void printHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		String header = "KakaoTalk Message Counting Program";
		String footer = "\nPlease report issues at https://github.com/gur5381/ChatCounter/issues";
		formatter.printHelp("ChatCounter", header, options, footer, true);
	}


}
