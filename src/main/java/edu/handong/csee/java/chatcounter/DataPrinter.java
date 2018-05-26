package edu.handong.csee.java.chatcounter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import static java.util.stream.Collectors.*;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;

import static java.util.Map.Entry.*;

public class DataPrinter {
	
	
	public void printResult(HashMap<String, Integer> list, String fileName) {
		Map<String, Integer> sorted = list.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,LinkedHashMap::new));
		Iterator<String> it = sorted.keySet().iterator();
		try {
			FileWriter fw = new FileWriter(fileName, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			pw.println("Kakao_id, count");
			while(it.hasNext()) {
				String key = (String)it.next();
				pw.println(key + ", " + list.get(key));
			}
			pw.flush();
			pw.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}		
	}
	

}
