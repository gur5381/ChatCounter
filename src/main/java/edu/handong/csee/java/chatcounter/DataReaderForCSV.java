package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;

/**
 * This class parse the message from CSV files.
 * It inherits the methods and fields from DataReader.
 * All the data are separated and put into the same format with TXT data.  
 * @author jeongjinhyeog
 *
 */
public class DataReaderForCSV extends DataReader{
	private ArrayList<String> parsedCSVMessage = new ArrayList<String>();



	/**
	 * This method does the parsing.
	 * It finds name, time, date, data from a line.
	 * And add them as a sentence which is a same format as TXT message's.
	 * The parsing result go to the parsedCSVMessage.
	 * @param messageForCSV
	 */
	public void parseCSV(ArrayList<String> messageForCSV) {
		int date_end;
		int name_start;
		int name_end;
		int time_start;
		int time_end;
		int data_start;
		int hour;

		for(String readLine : messageForCSV) {
			if(readLine.startsWith("201")) {
				data = "";
				name_start = readLine.indexOf("\"");
				name_end = readLine.indexOf("\"", name_start+1);
				date_end = readLine.indexOf(" ");
				time_start = readLine.indexOf(" ")+1;
				time_end = readLine.indexOf(":")+2;
				data_start = readLine.indexOf("\"", name_end +1);
				date = readLine.substring(0, date_end);
				time = readLine.substring(time_start, time_end+1);
				hour = Integer.parseInt(time.substring(0, 2));

				if(hour == 0) time = "오전 " + "12" + time.substring(2, time.length());
				else if(0 < hour && hour < 10) time = "오전 " +time.substring(1, time.length());
				else if(hour == 11 || hour == 10) time = "오전 " + time;
				else if(hour == 12) time = "오후 " + time.substring(0, time.length());
				else if(hour > 12) time = "오후 " + (hour-12) + time.substring(2, time.length());

				name = readLine.substring(name_start+1, name_end);
				if(readLine.endsWith("\""))data = data + readLine.substring(data_start+1, readLine.length()-1); 
				else {
					data = data + readLine.substring(data_start+1, readLine.length()); 
				}

			}
			else if(readLine.equals("Date,User,Message"))continue;
			else if(readLine.endsWith("\"")) data = data + readLine.substring(0, readLine.length()-1);
			else data = data + readLine;
			if(readLine.endsWith("\"")) {
				if(data.equals("Photo"))data = "사진";
				parsedCSVMessage.add("[" + date + "] " + "[" + name + "] "+"[" + time + "] " + data);
			}
		}
	}

	/**
	 * This method is to return parsedCSVMessage.
	 * @return
	 */
	public ArrayList<String> getParsedCSVMessage() {
		return parsedCSVMessage;
	}
}
