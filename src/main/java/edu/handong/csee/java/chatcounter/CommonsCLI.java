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
	private String path ="";
	private String file;
	private boolean help;

	/**
	 * This is a method which receives an array of arguments and run the CLI based on the arguments.
	 * If no argument is given, then it prints help and returns false!
	 * @param args
	 * @return
	 */
	public void runCLI(String[] args) {
		Options options = createOptions();

		if(parseOptions(options, args)) {
			if(help) {
				printHelp(options);
			}
		}
	}

	/**
	 * This method actually creates options.
	 * In this 4 options are created.
	 * I decided that the first argument should be the path and the second argument a target file name.
	 * @return
	 */
	public Options createOptions() {
		Options options = new Options();

		options.addOption(Option.builder("i")
				.desc("Path for the directory containing files.")
				.hasArg().argName("PATH").required().build());

		options.addOption(Option.builder("o")
				.desc("CSV file which is to store the result.")
				.hasArg().argName("FILE").required().build());

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
			path = cmd.getOptionValue("i");
			file = cmd.getOptionValue("o");
			help = cmd.hasOption("h");
		}catch(Exception e) {
			printHelp(options);
			System.out.println();
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

	/**
	 * This method returns the path.
	 * @return
	 */
	public String getPath() {
		return path;
	}

	/**
	 * This method return the file.
	 * @return
	 */
	public String getFile() {
		return file;
	}

}
