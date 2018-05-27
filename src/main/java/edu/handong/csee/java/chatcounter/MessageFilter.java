package edu.handong.csee.java.chatcounter;
import java.util.ArrayList;

/**
 * This class is for filtering the messages.
 * In this class, redundancy of messages is checked.
 * @author jeongjinhyeog
 *
 */
public class MessageFilter {
	static ArrayList<String> resultList;

	/**
	 * In this method, all the messages are filtered to have no redundant messages.
	 * This method returns the filtered messages as the result.
	 * @param dataList
	 * @return
	 */
	public ArrayList<String> delDuplicates(ArrayList<String> dataList){
		resultList = new ArrayList<String>();
		for(int i =0; i < dataList.size(); i++) {
			if(!resultList.contains(dataList.get(i)))
				resultList.add(dataList.get(i));
		}
		return resultList;
	}

}
