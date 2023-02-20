package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;

/** 
 * Handles the initial file read/write for file generation
 * @author  	Cai Kaihang
 * @author  	Don Lim
 * @version 	1.0
 * @since   	2022-10-16
 */
public interface FileAccessController {
	static final String dirPath = "/MOBILMA/src/Database/LIVE/";
	static final String userDir = System.getProperty("user.dir").replace("\\", "/");

	// Will always read/write from database folder, if file DNE it will prompt generation, if starting dir is wrong then GG

	/**
	 * Gets the list of data from the file
	 * @return List		List of data
	 */
	public List getData();

	/**
	 * Overwrites the data that is stored in the file
	 * @param o		New data to be stored
	 */
	public void writeData(Object o);

	/**
	 * Checks if file exists given the fileName
	 * @param fileName		Name of the file to be checked
	 * @return boolean		True/False as to whether the file exists or not
	 */
	public static boolean verifyFile(String fileName) {
		String absolutePath = userDir + dirPath + fileName;
        File f = new File(absolutePath);
		// Checks if the file (1.) exists and (2.) is NOT a directory
        return (f.exists() && !f.isDirectory()) ? true : false;
	}

	/**
	 * To access and read the contents of a serialized file
	 * @param fileName		Name of the file to be read
	 * @return List			List of the data in the file-to-be-read
	 */
	public static List readSerializedObject(String fileName) {
		List pDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		String filePath = userDir.replace("\\", "/") + dirPath + fileName;

		try {
			fis = new FileInputStream(filePath);
			in = new ObjectInputStream(fis);
			pDetails = (ArrayList) in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return pDetails;
	}

	/**
	 * To overwrite/create a serialized file
	 * @param fileName		File name to use for the created file or the file name of the file to be overwritten
	 * @param list			Data to be written into the file
	 */
	public static void writeSerializedObject(String fileName, List list) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		String filePath = userDir.replace("\\", "/") + dirPath + fileName;

		try {
			fos = new FileOutputStream(filePath);
			out = new ObjectOutputStream(fos);
			out.writeObject(list);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * To delete a file
	 * @param fileName	File name of the file to be deleted from the pre-defined directory
	 */
	public static void deleteFile(String fileName) {
		String filePath = userDir.replace("\\", "/") + dirPath + fileName;

		File myObj = new File(filePath);
		if(myObj.delete()) {
			System.out.println("Deleted " + fileName);
		}
		else {
			System.out.println("Failed to delete " + fileName);
		}
	}
}

