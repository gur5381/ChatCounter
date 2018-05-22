package edu.handong.csee.java.chatcounter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;


public class DataReaderForTXT extends DataReader{
//	public ArrayList<String> parsedTXTMessage = new ArrayList<String>();
//	
//	public void parseTXT(ArrayList<String> messageforTXT) {
//		int year=0, month=0, day=0, hour=0, minute=0;
//		String name ="", time="", strMessage="";
//		for(String readLine : messageforTXT) {
//			String pattern;
//			Pattern r;
//			Matcher m;
//			if(readLine.startsWith("-")) {
//				pattern = "-+\\s([0-9]+).\\s([0-9]+).\\s([0-9]+).\\s-+";
//				r = Pattern.compile(pattern);
//				m = r.matcher(readLine);
//				if(m.find()) {
//					year = Integer.parseInt(m.group(1));
//					month = Integer.parseInt(m.group(2));
//					day = Integer.parseInt(m.group(3));
//					System.out.println(year + month + day);
//				}
//			}
//			else {
//				pattern = "\\[(.+)\\]\\s\\[..\\s([0-9]+:[0-9]+)\\]\\s(.+)";
//				r = Pattern.compile(pattern);
//				m = r.matcher(readLine);
//				if(m.find()) {
//					name = m.group(1);
//					time = m.group(2);
//					strMessage = m.group(3);
//				}
//				pattern = "([0-9]+):([0-9]+)";
//				r = Pattern.compile(pattern);
//				m = r.matcher(readLine);
//				if(m.find()) {
//					hour = Integer.parseInt(m.group(1));
//					minute = Integer.parseInt(m.group(2));
//				}
//				if(time.startsWith("[오후]")){
//					if(time.startsWith("[오후] 12"));
//					else hour += 12;
//				}
//				if(time.startsWith("[오전] 12")) hour -= 12;
//			}
//			String line = "[" + year + "-" + month + "-" + day + "] " + 
//					"[" + hour + ":" + minute + "] " +
//					"[" + name + "] " + "[" + strMessage + "]";
//			System.out.println(line);
//			parsedTXTMessage.add(line);
//		}
//	}
//	
//	
	
	
	
	
	
	
	
	
	
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
				parsedTXTMessage.add("[" + date + "] " + readLine );
			}
		}
	}
}
