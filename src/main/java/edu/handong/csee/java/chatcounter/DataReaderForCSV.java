package edu.handong.csee.java.chatcounter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class DataReaderForCSV extends DataReader{
	public ArrayList<String> parsedCSVMessage = new ArrayList<String>();
	
	public void parseCSV(ArrayList<String> messageforCSV) {
		int date_end;
		int name_start;
		int name_end;
		int time_start;
		int time_end;
		int data_start;
		int hour;

		for(String readLine : messageforCSV) {
			System.out.println(readLine);
			if(readLine.startsWith("201")) {
				name_start = readLine.indexOf("\"");
				name_end = readLine.indexOf("\"", name_start+1);
				date_end = readLine.indexOf(" ");
				time_start = readLine.indexOf(" ")+1;
				time_end = readLine.indexOf(":")+2;
				data_start = readLine.indexOf("\"", name_end +1);
				date = readLine.substring(0, date_end);
				time = readLine.substring(time_start, time_end+1);
				hour = Integer.parseInt(time.substring(0, 2));
				if(hour > 12) time = "오후 " + (hour-12) + time.substring(2, time.length());
				else if(hour < 10) time = "오전 " +time.substring(0, time.length());
				else if(hour == 0) time = "오전 " + "12" + time.substring(2, time.length());
				else if(hour == 12) time = "오후 " + time.substring(0, time.length());
				else time = "오전 " + time;
				name = readLine.substring(name_start+1, name_end);
				data = readLine.substring(data_start+1, readLine.length()-1); 
				parsedCSVMessage.add("[" + date + "] " + "[" + name + "] "+"[" + time + "] " + data);		
			} 
		}
	}
}
