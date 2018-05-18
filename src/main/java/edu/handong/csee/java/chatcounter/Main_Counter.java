package edu.handong.csee.java.chatcounter;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Main_Counter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataReader r = new DataReader();
		DataReaderForTXT rt = new DataReaderForTXT();
		MessageFilter mf = new MessageFilter();
		HashMap<String, Integer> list = new HashMap<String, Integer>();
		ArrayList<String> data = new ArrayList<String>();
	
		data = r.getData(args[0]);
		data = mf.delDuplicates(data);
		
		for(String readLine : data) {
			System.out.println(readLine);
		}
		
		list =rt.CountDataForTXT(data);
		Iterator<String> nameList = list.keySet().iterator();
		System.out.println("kakao_id, count");
		while(nameList.hasNext()) {
			String key = nameList.next();
			System.out.println(key + ", " + list.get(key));
		}
		
			
	}

}
