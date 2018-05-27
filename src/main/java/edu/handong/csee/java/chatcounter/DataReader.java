package edu.handong.csee.java.chatcounter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.FileInputStream;

/**
 * This class does the main role in this program.
 * It reads all the data and classify the messages to TXT and CSV.
 * 
 * @author jeongjinhyeog
 *
 */
public class DataReader {
	public ArrayList<String> messageForTXT = new ArrayList <String>();
	public ArrayList<String> messageForCSV = new ArrayList <String>();
	public ArrayList<String> messages;
	public String name;
	public String date;
	public String time;
	public String data;
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
		for(i = 0; i < Array.getLength(files); i++) {
			try {
				BufferedReader bufReader = new BufferedReader(
						new InputStreamReader(
								new FileInputStream(files[i]), "UTF-8"));
				String line = "";
				if(files[i].getName().endsWith(".txt")) {
					while((line  = bufReader.readLine()) != null) {
						messageForTXT.add(line);
					}
				}
				else while((line  = bufReader.readLine()) != null) {
					messageForCSV.add(line);
				}
				bufReader.close();
			}catch(FileNotFoundException e) {	
				System.out.println(e);
			}catch(IOException e) {
				System.out.println(e);
			}
		}
	}

}



