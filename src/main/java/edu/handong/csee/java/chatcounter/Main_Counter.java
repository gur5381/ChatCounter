package edu.handong.csee.java.chatcounter;

import java.util.HashMap;
import java.util.ArrayList;


/**
 * This is a main class for the chatcounter.
 * This class does the bridge role for all other classes.
 * 
 * @author jeongjinhyeog
 *
 */
public class Main_Counter {

	/**
	 * It receives the arguments from the users.
	 * If there is an arguments then it starts all the process.
	 * If not, it prints the help message.
	 * All the data and methods from other classes are utilized within this method.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DataReader r = new DataReader();
		DataReaderForCSV rc = new DataReaderForCSV();
		DataReaderForTXT rt = new DataReaderForTXT();
		DataPrinter p = new DataPrinter();
		MessageFilter mf = new MessageFilter();
		CommonsCLI cc = new CommonsCLI();
		Counter c = new Counter();
		ArrayList<String> data = new ArrayList<String>();
		HashMap<String, Integer> list = new HashMap<String, Integer>();

		if(cc.runCLI(args)) {
			r.getData(args[0]);
			rc.parseCSV(r.messageForCSV);
			rt.parseTXT(r.messageForTXT);
			data.addAll(rc.parsedCSVMessage);
			data.addAll(rt.parsedTXTMessage);

			data = mf.delDuplicates(data);		
			list = c.countData(data);

			data = p.printResult(list, args[1]);
			if(cc.print) {
				System.out.println("Kakao_id, count");
				for(String str : data) {
					System.out.println(str);
				}
			}
		}
	}
}
