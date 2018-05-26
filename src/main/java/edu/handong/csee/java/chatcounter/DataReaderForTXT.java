package edu.handong.csee.java.chatcounter;

//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
import java.util.ArrayList;


public class DataReaderForTXT extends DataReader{

	public ArrayList<String> parsedTXTMessage = new ArrayList<String>();
	
	public void parseTXT(ArrayList<String> messageforTXT) {
		int yearIndex=0;
		int monthIndex=0;
		int dayIndex=0;
		String year;
		String month;
		String day; 

		for(String readLine : messageforTXT) {
			//System.out.println(readLine);
			//if(readLine.contains("[남재창]")) System.out.println(readLine);
			if(readLine.startsWith("-")) {
				yearIndex = readLine.indexOf('년');
				monthIndex = readLine.indexOf('월');
				dayIndex = readLine.indexOf('일');
				year = readLine.substring(yearIndex-4, yearIndex);
				
				month = readLine.substring(yearIndex+2, monthIndex);
				if(month.length() == 1) month = "0" + month;
				day = readLine.substring(monthIndex+2, dayIndex);
				if(day.length() == 1) day = "0" + day;
				date = year + "-" + month + "-" + day;
			}
			else if(readLine.startsWith("["))	{
				readLine = readLine.replace("\"", "\"\"");
				//System.out.println("[" + date + "] " + readLine);
				parsedTXTMessage.add("[" + date + "] " + readLine );
			}
		}
	}
}
