package edu.handong.csee.java.chatcounter;

import java.util.HashMap;
import java.util.Iterator;

public class DataPrinter {
	Iterator<String> nameList;
	
	public void printResult(HashMap<String, Integer> list) {
		nameList = list.keySet().iterator();
		System.out.println("kakao_id, count");
		while(nameList.hasNext()) {
			String key = nameList.next();
			System.out.println(key + ", " + list.get(key));
		}
	}
}
