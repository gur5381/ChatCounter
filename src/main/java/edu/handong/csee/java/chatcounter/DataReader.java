package edu.handong.csee.java.chatcounter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * This class does the main role in this program.
 * It reads all the data and classify the messages to TXT and CSV.
 * In this class, we use threadpool to make the process much faster by using multiple threads.
 * The goal of this class is to return the messages to the main class.
 * 
 * @author jeongjinhyeog
 *
 */
public class DataReader{
	/**
	 * This is an arrayList type field named messages.
	 */
	public ArrayList<String> messages = new ArrayList<String>();
	/**
	 * This is an arrayList type field named messageForTXT.
	 */
	public ArrayList<String> messageForTXT = new ArrayList <String>();
	/**
	 * This is an arrayList type field named messageForCSV.
	 */
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
	 * If the strDir is wrong, it returns the message to retry with the right path.
	 * @param strDir
	 */
	public void getData(String strDir){
		try {
			File myDir = getDirectory(strDir);
			File[] files = getListOfFilesFromDirectory(myDir);
			readFiles(files);
		}catch(NullPointerException e) {
			System.out.println("Wrong input directory path. Retry with the right path.");
			System.exit(0);
		}
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
	 * For this computation this method uses Threadpool with the number of Cores in the CPU.
	 * each thread is called as a worker and does the role of reading a CSV or TXT file.
	 * @param files
	 */
	public void readFiles(File[] files){
		int i;
		ArrayList<CSVFileReaderThread> csvRunners = new ArrayList<CSVFileReaderThread>();
		ArrayList<TXTFileReaderThread> txtRunners = new ArrayList<TXTFileReaderThread>();
		int numOfCoresInMyCPU = Runtime.getRuntime().availableProcessors();
		ExecutorService executor = Executors.newFixedThreadPool(numOfCoresInMyCPU);
		ArrayList<Callable<Object>> calls = new ArrayList<Callable<Object>>();

		for(i = 0; i < Array.getLength(files); i++) {
			if(files[i].getName().endsWith(".csv")) {
				Runnable worker = new CSVFileReaderThread(files[i]);
				csvRunners.add((CSVFileReaderThread)worker);
				calls.add(Executors.callable(worker));
			}
		}

		for(i = 0; i < Array.getLength(files); i++) {
			if(files[i].getName().endsWith(".txt")) {
				Runnable worker = new TXTFileReaderThread(files[i]);
				txtRunners.add((TXTFileReaderThread)worker);
				calls.add(Executors.callable(worker));
			}
		}

		try {
			executor.invokeAll(calls);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}



		executor.shutdown();


		for(CSVFileReaderThread worker:csvRunners) {
			messages.addAll(worker.CSVMessages);
		}

		for(TXTFileReaderThread worker:txtRunners) {
			messages.addAll(worker.TXTMessages);
		}



	}
	/**
	 * This is to return the messages.
	 * @return
	 */
	public ArrayList<String> getMessages() {
		return messages;
	}
}




