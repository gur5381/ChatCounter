package edu.handong.csee.java.chatcounter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class DataReader {
	
	public ArrayList<String> getData(String strDir){
		File myDir = getDirectory(strDir);
		File[] files = getListOfFilesFromDirectory(myDir);
		ArrayList<String> message = readFiles(files);
		return message;
	}
	
	public File getDirectory(String strDir) {
		File myDirectory = new File(strDir);
		return myDirectory;
	}
	
	public File[] getListOfFilesFromDirectory(File dataDir) {
		return dataDir.listFiles();
	}
	
	public ArrayList <String> readFiles(File[] files){
		ArrayList <String> messages = new ArrayList <String>();
		int i;
		for(i = 0; i < Array.getLength(files); i++) {
			try {
				Scanner scan = new Scanner(files[i]);
				while(scan.hasNextLine()) {
					messages.add(scan.nextLine());
				}
				
			}catch(IOException e) {
				System.out.println(e);
			}
		}
		
		return messages;
	}	
	
}




