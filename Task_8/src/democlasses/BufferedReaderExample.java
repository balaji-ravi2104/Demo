package democlasses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferedReaderExample {
	public static void main(String[] args) {
		// Example using BufferedReader and FileReader to read from a file
		try (BufferedReader reader = new BufferedReader(new FileReader("balaji.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Example using BufferedReader and InputStreamReader to read from System.in (standard input)
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.println("Enter some text:");
			String input = reader.readLine();
			System.out.println("You entered: " + input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/*
 * BufferedReader: This class reads text from a character-input stream with
 * efficiency by buffering characters, arrays, or lines. 
 * InputStreamReader: This class is a bridge from byte streams to character streams. It reads bytes and
 * decodes them into characters using a specified charset.
 */