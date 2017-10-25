import java.io.*;
import java.util.*;  

/**
 * Handles the serialization of objects.
 *
 * @author Austin Ash
 * @author Jose Cruz
 */
 
class Serialization{  

	/**
     * Serializes an object and outputs to the given file location.
     *
     * @param data  The data to be serialized
	 * @param fileLocation  String representing the output file (such as /songs/library.txt)
	 *
	 * @return  True if the data was written without issues; otherwise false.
     */
	public static boolean writeData(Object data, String fileLocation) {
		try {
			FileOutputStream fileOut = new FileOutputStream(fileLocation);  
			ObjectOutputStream out = new ObjectOutputStream(fileOut);  
			out.writeObject(data);  
			out.flush();  
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	/**
     * Read s serialized file and returns the result after it has been parsed as an object
     *
     * @param fileLocation  A String representing the directory to look for MP3 files.
     *
	 * @return  Null if any errors occur; otherwise return the Object
	 */
	public static Object readData(String fileLocation) {
		try {
			ObjectInputStream input=new ObjectInputStream(new FileInputStream(fileLocation));  
			Object obj = (Object)input.readObject();  
			input.close(); 
			return obj;
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 * Unit test.
	 * 
	 * @param args  Command-line parameters for this test.  Currently unused.
	 */
	public static void main(String args[]) {
		
		//Random example of song data
		ArrayList<ArrayList> data = new ArrayList();  
		for(int i=1; i<=5000; i++){
			data.add(new ArrayList(Arrays.asList("Song"+i, "13:43", "./songs/Song"+i+".mp3")));
		}
		//Showing data before serializing
		System.out.println("Serializing the following to example.txt: ");
		//System.out.println(data);
		System.out.println("");
		
		try {
			Serialization.writeData(data,"example.txt");
		} catch(Exception e) {
			System.out.println("Couldn't read file");
		}
		
		//Showing retreived from the serialized file
		ArrayList<ArrayList> savedData= (ArrayList)Serialization.readData("example.txt");
		System.out.println("The following was de-serialized from example.txt:");
		//System.out.println(savedData);
  
	}

} 