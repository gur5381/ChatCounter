package edu.handong.csee.java.chatcounter;

/**
 * This class is to customize an exception which extends from Exception.
 * An exception occurs when the directory path is not given.
 * 
 * @author jeongjinhyeog
 *
 */
public class NoPathException extends Exception {

	/**
	 * This is a constructor to create a NoPathException.
	 */
	public NoPathException(){
		super();
	}

	/**
	 * This is a constructor which receives message and prints out that there is no path for the directory.
	 * @param message
	 */
	public NoPathException(String message){
		super(message);
		System.out.println("There is no path for the directory.");
	}
}
