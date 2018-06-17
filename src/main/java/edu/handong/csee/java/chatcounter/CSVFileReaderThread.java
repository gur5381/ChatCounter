package edu.handong.csee.java.chatcounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * This class is to read a CSV file with a thread.
 * To be used as a thread it is using the Runnable interface.
 * Reading a CSV file is done by the BufferedReader. 
 * @author jeongjinhyeog
 *
 */
public class CSVFileReaderThread extends DataReader implements Runnable {
	/**
	 * This is an ArrayList type field named CSVMessages.
	 */
	public ArrayList<String> CSVMessages = new ArrayList<String>();
	
	/**
	 * This is a File type field named file.
	 */
	public File file;

	/**
	 * This is a constructor which receives the file data and store the data as file.
	 * @param file
	 */
	public CSVFileReaderThread(File file) {
		this.file = file;
	}

	/**
	 * This method is started when the thread.start() is called.
	 * As the start sign is started, this method start to parse the file.
	 */
	public void run() {
		parseCSV(file);
	}


	/**
	 * * This method does the parsing.
	 * It receives the file and finds name, time, date, data from the file.
	 * And add them as a sentence which is a same format as TXT message's.
	 * The parsing result go to the CSVmessages.
	 * @param file
	 */
	public void parseCSV(File file) {

		try {
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String line = "";
			while((line  = bufReader.readLine()) != null) {
				messageForCSV.add(line);
			}
			bufReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
				CSVMessages.add("[" + date + "] " + "[" + name + "] "+"[" + time + "] " + data);
			}
		}
	}
}

