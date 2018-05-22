package edu.handong.csee.java.chatcounter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.FileInputStream;


public class DataReader {
	public HashMap<String, Integer> counted = new HashMap<String, Integer>();
	public ArrayList<String> messageforTXT = new ArrayList <String>();
	public ArrayList<String> messageforCSV = new ArrayList <String>();
	public ArrayList<String> messages;
	public String name;
	public String date;
	public String time;
	public String data;
	
	public void getData(String strDir){
		File myDir = getDirectory(strDir);
		File[] files = getListOfFilesFromDirectory(myDir);
		readFiles(files);
	}
	
	public File getDirectory(String strDir) {
		File myDirectory = new File(strDir);
		return myDirectory;
	}
	
	public File[] getListOfFilesFromDirectory(File dataDir) {
		return dataDir.listFiles();
	}
	
	
	
	public void readFiles(File[] files){
		int i;
		for(i = 0; i < Array.getLength(files); i++) {
			try {
				BufferedReader bufReader = new BufferedReader(
						new InputStreamReader(
								new FileInputStream(files[i]), "UTF-8"));
				String line = "";
				if(files[i].getName().endsWith(".txt")) {
					while((line  = bufReader.readLine()) != null) {
						messageforTXT.add(line);
					}
				}
				else while((line  = bufReader.readLine()) != null) {
					messageforCSV.add(line);
				}
				bufReader.close();
			}catch(FileNotFoundException e) {	
				
			}catch(IOException e) {
			
				System.out.println(e);
			}
		}
	}
	
	public HashMap<String, Integer> countData(ArrayList<String> list){
		for(String readLine : list) {
			int io_start = readLine.indexOf('[', 11); 
			int io_end = readLine.indexOf(']', 12);
			int count;
			if(io_start>=0) {
				String name = readLine.substring(io_start+1, io_end);
				if(counted.get(name)!=null) {
					count = counted.get(name);
					count ++;
					counted.put(name, count);
				}
				else counted.put(name, 1);
			}
		}
		return counted;
	}
	
}



