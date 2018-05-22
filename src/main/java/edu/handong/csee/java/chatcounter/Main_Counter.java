package edu.handong.csee.java.chatcounter;

import java.util.HashMap;
import java.util.ArrayList;


public class Main_Counter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataReader r = new DataReader();
		DataReaderForCSV rc = new DataReaderForCSV();
		DataReaderForTXT rt = new DataReaderForTXT();
		DataPrinter p = new DataPrinter();
		MessageFilter mf = new MessageFilter();
		
		ArrayList<String> data = new ArrayList<String>();
		HashMap<String, Integer> list = new HashMap<String, Integer>();
		
		r.getData(args[0]);
		rc.parseCSV(r.messageforCSV);
		rt.parseTXT(r.messageforTXT);
		data.addAll(rc.parsedCSVMessage);
		data.addAll(rt.parsedTXTMessage);

		data = mf.delDuplicates(data);
//		for(String str : data) {
//			if(str.contains("[조정훈]"))
//				System.out.println(str);
//		}
		list = r.countData(data);
		p.printResult(list);
	}

}
