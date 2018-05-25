package edu.handong.csee.java.chatcounter;
import java.util.ArrayList;
import java.util.List;

public class MessageFilter {
	static ArrayList<String> resultList;
	
	public ArrayList<String> delDuplicates(ArrayList<String> dataList){
		resultList = new ArrayList<String>();
		for(int i =0; i < dataList.size(); i++) {
			if(!resultList.contains(dataList.get(i)))
				resultList.add(dataList.get(i));
			else 
				if(dataList.get(i).contains("[김석진]"))
						System.out.println(dataList.get(i));
		}
		return resultList;
	}
	
}
