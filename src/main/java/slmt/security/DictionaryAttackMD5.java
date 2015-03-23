package slmt.security;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class DictionaryAttackMD5 {
	
	private static int count = 0;
	private static final int REPORT_BASE = 100000;
	
	public static void main(String[] args) {
		String fileName, targetMD5, salt = "";
		
		// reads agruments
		if (args.length < 2) {
			System.out.println("Arguments: [Dictionary File] [MD5 Hash To Be Tested] ([Salt])");
			return;
		}
		fileName = args[0];
		targetMD5 = args[1];
		if (args.length > 2)
			salt = args[2];
			
		// checks the file
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println("File [" + file + "] doesn't exist.");
			return;
		}
		
		// reads the file and tests the target
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String testWord = null;
		
			while ((testWord = reader.readLine()) != null) {
				String hashedWord = MD5Hasher.hash(testWord + salt);
				if (hashedWord.equals(targetMD5))
					break;
				
				countAndReportStatus();
			}
			
			if (testWord == null)
				System.out.println("Not found.");
			else
				System.out.println("Found it!!! It's '" + testWord + "'.");
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void countAndReportStatus() {
		count++;

		if (count % REPORT_BASE == 0)
			System.out.println(count + " words has been tried.");
	}
}
