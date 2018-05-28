package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * This class actually does the Counting job.
 * It receives ArrayList data which are already filtered.
 * A hashmap receives the counting result and is returned.
 * @author jeongjinhyeog
 *
 */
public class Counter {
	/**
	 * This is a method for counting.
	 * It finds a name from a line automatically and start counting.
	 * It returns the hashmap of counted result.
	 * @param list
	 * @return
	 */
	public HashMap<String, Integer> countData(ArrayList<String> list){
		HashMap<String, Integer> counted = new HashMap<String, Integer>();
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
