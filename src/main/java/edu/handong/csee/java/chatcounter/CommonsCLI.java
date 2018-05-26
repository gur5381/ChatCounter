package edu.handong.csee.java.chatcounter;

import static java.util.stream.Collectors.toMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class CommonsCLI {
	String path;
	boolean print;
	boolean help;
	
	public void runCLI(HashMap<String, Integer> list, String[] args) {
		Options options = createOptions();
		
		if(parseOptions(options, args)) {
			if(help) {
				printHelp(options);
				return;
			}
			
			if(print) {
				Map<String, Integer> sorted = list.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,LinkedHashMap::new));
				Iterator<String> it = sorted.keySet().iterator();
				System.out.println("Kakao_id, count");
				while(it.hasNext()) {
					String key = (String)it.next();
					System.out.println(key + ", " + list.get(key));
				}
			}
		}
		
	}
	
	public Options createOptions() {
		Options options = new Options();
		
		options.addOption(Option.builder("p").longOpt("print")
				.desc("Print the result of Message Counting!")
				.build());
		options.addOption(Option.builder("h").longOpt("help")
				.desc("Help")
				.build());
		
		return options;
	}
	
	public boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();
		
		try {
			CommandLine cmd = parser.parse(options, args);
			help = cmd.hasOption("h");
			print = cmd.hasOption("p");
		}catch(Exception e) {
			System.out.println("The result File has been created!");
			return false;
		}
		
		return true;
	}
	public void printHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		String header = "KakaoTalk Message Counting Program";
		String footer = "\nPlease report issues at https://github.com/gur5381/ChatCounter/issues";
		formatter.printHelp("ChatCounter", header, options, footer, true);
	}


}
