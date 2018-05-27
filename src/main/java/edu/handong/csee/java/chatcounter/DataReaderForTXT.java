package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;

/**
 * This class parse the message from TXT files.
 * It inherits the methods and fields from DataReader.
 * All the data are separated and put into the same format with CSV data.  
 *
 * @author jeongjinhyeog
 *
 */
public class DataReaderForTXT extends DataReader{

	public ArrayList<String> parsedTXTMessage = new ArrayList<String>();

	/**
	 * This method is to parse message from TXT files.
	 * Time and date are mainly parsed because of various types of data.
	 * Final parsed messages put into the parsedTXTMessage.
	 * @param messageForTXT
	 */
	public void parseTXT(ArrayList<String> messageForTXT) {
		int yearIndex=0;
		int monthIndex=0;
		int dayIndex=0;
		int dayEndIndex=0;
		String year;
		String month;
		String day; 

		for(String readLine : messageForTXT) {
			if(readLine.startsWith("-")) {
				if(readLine.contains("- 201")) {
					yearIndex = readLine.indexOf('년');
					monthIndex = readLine.indexOf('월');
					dayIndex = readLine.indexOf('일');
					year = readLine.substring(yearIndex-4, yearIndex);

					month = readLine.substring(yearIndex+2, monthIndex);
					if(month.length() == 1) month = "0" + month;
					day = readLine.substring(monthIndex+2, dayIndex);
					if(day.length() == 1) day = "0" + day;

				}
				else {
					monthIndex = readLine.indexOf(',') + 2;
					dayIndex = readLine.indexOf(' ', monthIndex) + 1;
					dayEndIndex = readLine.indexOf(',', dayIndex);
					yearIndex = readLine.indexOf('2', dayEndIndex);
					year = readLine.substring(yearIndex, yearIndex+4);
					month = readLine.substring(monthIndex, dayIndex-1);
					day = readLine.substring(dayIndex, dayEndIndex);
					if(month.equals("January")) month = "01";
					else if(month.equals("Febuary")) month = "02";					else if(month.equals("Febuary")) month = "02";
					else if(month.equals("March")) month = "03";
					else if(month.equals("April")) month = "04";
					else if(month.equals("May")) month = "05";
					else if(month.equals("June")) month = "06";
					else if(month.equals("July")) month = "07";
					else if(month.equals("August")) month = "08";
					else if(month.equals("September")) month = "09";
					else if(month.equals("October")) month = "10";
					else if(month.equals("November")) month = "11";
					else if(month.equals("December")) month = "12";

				}
				date = year + "-" + month + "-" + day;

			}
			else if(readLine.startsWith("["))	{
				int firstclosebr = readLine.indexOf(']');
				int secondopenbr = readLine.indexOf('[', firstclosebr);
				int secondclosebr = readLine.indexOf(']', secondopenbr);

				time = readLine.substring(secondopenbr+1, secondclosebr);
				if(time.contains("AM")) {
					time = "오전 " + time.substring(0, time.length()-3);
					readLine = readLine.substring(0, secondopenbr+1) + time + readLine.substring(secondclosebr, readLine.length());
				}
				else if(time.contains("PM")) {
					time = "오후 " + time.substring(0, time.length()-3);
					readLine = readLine.substring(0, secondopenbr+1) + time + readLine.substring(secondclosebr, readLine.length());
				}

				readLine = readLine.replace("\"", "\"\"");

				parsedTXTMessage.add("[" + date + "] " + readLine );
			}
		}
	}
}
