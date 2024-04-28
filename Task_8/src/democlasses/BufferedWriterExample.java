package democlasses;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class BufferedWriterExample {
	public static void main(String[] args) {
		// Example using BufferedWriter and FileWriter to write to a file
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("balaji.txt"))) {
			writer.write("Hello, BufferedWriter!");
			writer.newLine();
			writer.write("I am Balaji Ravi.");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Example using BufferedWriter and OutputStreamWriter to write to System.out
		// (standard output)
		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
			writer.write("Hello, BufferedWriter!");
			writer.newLine();
			writer.write("This is another line.");
			writer.flush(); // Flush to ensure all data is written to the output stream
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


/*
 * BufferedWriter: 
 * This class writes text to a character-output stream with
 * efficiency by buffering characters, arrays, or strings.
 *  OutputStreamWriter:
 * This class is a bridge from character streams to byte streams. It writes
 * characters to a byte-oriented output stream using a specified charset.
 */


