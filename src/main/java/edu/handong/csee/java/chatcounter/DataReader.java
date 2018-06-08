package edu.handong.csee.java.chatcounter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.File;


/**
 * This class does the main role in this program.
 * It reads all the data and classify the messages to TXT and CSV.
 * 
 * @author jeongjinhyeog
 *
 */
public class DataReader{
	public ArrayList<String> messages = new ArrayList<String>();

	public ArrayList<String> messageForTXT = new ArrayList <String>();
	public ArrayList<String> messageForCSV = new ArrayList <String>();
	/**
	 * This is a string type field named name.
	 */
	public String name;
	/**
	 * This is a string type field named date.
	 */
	public String date;
	/**
	 * This is a string type field named time.
	 */
	public String time;
	/**
	 * This is a string type field named data.
	 */
	public String data ="";

	/**
	 * This gets the path name from the arguments and starts reading the files in it.
	 * All the files in the folder will be stored and sent to the readFiles.
	 * @param strDir
	 */
	public void getData(String strDir){
		File myDir = getDirectory(strDir);
		File[] files = getListOfFilesFromDirectory(myDir);
		readFiles(files);
	}
	/**
	 * This method changes string type directory name to the file type.
	 * And it returns the file type directory.
	 * @param strDir
	 * @return
	 */
	public File getDirectory(String strDir) {
		File myDirectory = new File(strDir);
		return myDirectory;
	}

	/**
	 * This receives the File type directory.
	 * In this, all the files in the directory are put into the array and returned.
	 * @param dataDir
	 * @return
	 */
	public File[] getListOfFilesFromDirectory(File dataDir) {
		return dataDir.listFiles();
	}


	/**
	 * This method reads the data line by line and put them in the arrayList type message.
	 * In this, txt and csv files are divided and then put data separately.
	 * @param files
	 */
	public void readFiles(File[] files){
		int i;
		ArrayList<CSVFileReaderThread> csvWorkers = new ArrayList<CSVFileReaderThread>();
		ArrayList<Thread> lstThreads = new ArrayList<Thread>();
		ArrayList<TXTFileReaderThread> txtWorkers = new ArrayList<TXTFileReaderThread>();

		for(i = 0; i < Array.getLength(files); i++) {
			if(files[i].getName().endsWith(".csv")) {
				System.out.println("Read a file: " + files[i].getName());
				CSVFileReaderThread csvFileReader = new CSVFileReaderThread(files[i]);
				csvWorkers.add(csvFileReader);
				Thread worker = new Thread(csvFileReader);
				lstThreads.add(worker);
				worker.start();
			}
		}
		for(i = 0; i < Array.getLength(files); i++) {
			if(files[i].getName().endsWith(".txt")) {
				System.out.println("Read a file: " + files[i].getName());
				TXTFileReaderThread txtFileReader = new TXTFileReaderThread(files[i]);
				txtWorkers.add(txtFileReader);
				Thread worker = new Thread(txtFileReader);
				worker.start();
				lstThreads.add(worker);
			}
		}
		//join
		for(Thread thread:lstThreads) {
			try {
				thread.join();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}

		//merge all messages
		for(TXTFileReaderThread worker:txtWorkers) {
			messages.addAll(worker.messages);
		}

		for(CSVFileReaderThread worker:csvWorkers) {
			messages.addAll(worker.messages);
		}

	}

	public ArrayList<String> getMessages() {
		return messages;
	}
}




