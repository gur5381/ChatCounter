package edu.handong.csee.java.chatcounter;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class DataReaderForTXT extends DataReader{
	static HashMap<String, Integer> counted = new HashMap<String, Integer>();
	
	public DataReaderForTXT() {
		super();
	}
	
	public HashMap<String, Integer> CountDataForTXT(ArrayList<String> list){
		
		for(String readLine : list) {
			int io_start = readLine.indexOf('['); 
			int io_end = readLine.indexOf(']');
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
