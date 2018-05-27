package edu.handong.csee.java.chatcounter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import static java.util.stream.Collectors.*;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;


/**
 * In this class data will sort the result and printed to the file.
 * Before putting the data into the file, the HashMap type data will be changed to iterator.
 * The data is sorted in the descending order.
 * @author jeongjinhyeog
 *
 */
public class DataPrinter {

	/**
	 * This method receives the Hashmap type data and the CSV file name.
	 * It first sorts the data according to key.
	 * Then, it writes the data into the CSV file.
	 * It returns the list for other uses.
	 * @param list
	 * @param fileName
	 * @return
	 */
	public ArrayList<String> printResult(HashMap<String, Integer> list, String fileName) {
		Map<String, Integer> sorted = list.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,LinkedHashMap::new));
		Iterator<String> it = sorted.keySet().iterator();
		ArrayList<String> temp = new ArrayList<String>();
		try {
			FileWriter fw = new FileWriter(fileName, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			pw.println("Kakao_id, count");
			while(it.hasNext()) {
				String key = (String)it.next();
				pw.println(key + ", " + list.get(key));
				temp.add(key + ", " + list.get(key));
			}
			pw.flush();
			pw.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}		
		return temp;
	}


}
