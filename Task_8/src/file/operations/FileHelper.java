package file.operations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Properties;

import exception.CustomException;
import message.ConstantMessage;
import utils.UtilMethods;

public class FileHelper {

	public void createFileAndAddValues(String fileName, String[] array, String delimiter)
			throws CustomException, IOException {
		UtilMethods.isNull(array, ConstantMessage.ARRAY_INPUT_NULL_MESSAGE);
		UtilMethods.isNull(fileName, ConstantMessage.INPUT_NULL_MESSAGE);
		File file = createFile(fileName);
		try (Writer fileWriter = createFileWriter(file); Writer bufferedWriter = getNewBufferWriter(fileWriter)) {
			for (String string : array) {
				writeOnFile(bufferedWriter, string, delimiter);
			}
		} catch (CustomException | IOException e) {
			throw new CustomException("Error in File Creating", e);
		}
	}

	public void createFileWithProperites(String fileName, String comment, Properties prop)
			throws CustomException, IOException {
		UtilMethods.isNull(prop, "Properties Cannot be Null");
		UtilMethods.isNull(fileName, ConstantMessage.INPUT_NULL_MESSAGE);
		UtilMethods.isNull(comment, "Comments Cannot Be Null in the Properties");
		File file = createFile(fileName);
		try (Writer fileWriter = createFileWriter(file); Writer bufferedWriter = getNewBufferWriter(fileWriter)) {
			storeProperties(prop, bufferedWriter, comment);
		} catch (CustomException | IOException e) {
			throw new CustomException("Error in File Creating With Properties", e);
		}
	}

	public Properties readProperties(String fileName) throws CustomException, IOException, FileNotFoundException {
		UtilMethods.isNull(fileName, ConstantMessage.File_NULL_MESSAGE);
		try (Reader fileReader = getFileReader(fileName); Reader bufferedReader = getBufferedReader(fileReader)) {
			Properties prop = getPropsData(bufferedReader);
			return prop;
		} catch (CustomException | IOException e) {
			throw new CustomException("Error in File Creating With Properties", e);
		}
	}

	private Reader getBufferedReader(Reader fileReader) throws CustomException {
		UtilMethods.isNull(fileReader, ConstantMessage.File_NULL_MESSAGE);
		return new BufferedReader(fileReader);
	}

	private Reader getFileReader(String fileName) throws CustomException, IOException, FileNotFoundException {
		UtilMethods.isNull(fileName, ConstantMessage.File_NULL_MESSAGE);
		return new FileReader(fileName);
	}

	private File createFile(String fileName) throws CustomException {
		UtilMethods.isNull(fileName, ConstantMessage.File_NULL_MESSAGE);
		return new File(fileName);
	}

	private Writer createFileWriter(File file) throws CustomException, IOException {
		UtilMethods.isNull(file, ConstantMessage.File_NULL_MESSAGE);
		System.out.println(file.getAbsolutePath());
		return new FileWriter(file.getAbsolutePath());
	}

	private Writer getNewBufferWriter(Writer fileWriter) throws CustomException {
		UtilMethods.isNull(fileWriter, ConstantMessage.File_NULL_MESSAGE);
		return new BufferedWriter(fileWriter);
	}

	private void writeOnFile(Writer bufferedWriter, String content, String delimiter)
			throws CustomException, IOException {
		UtilMethods.isNull(bufferedWriter, ConstantMessage.File_NULL_MESSAGE);
		UtilMethods.isNull(content, ConstantMessage.INPUT_NULL_MESSAGE);
		UtilMethods.isNull(delimiter, ConstantMessage.INPUT_NULL_MESSAGE);
		bufferedWriter.write(content + delimiter);
	}

	public Properties getNewProperties() {
		return new Properties();
	}

	public void setPropertiesData(Properties prop, String key, String value) throws CustomException {
		UtilMethods.isNull(prop, ConstantMessage.INPUT_NULL_MESSAGE);
		UtilMethods.isNull(key, ConstantMessage.INPUT_NULL_MESSAGE);
		UtilMethods.isNull(value, ConstantMessage.INPUT_NULL_MESSAGE);
		prop.setProperty(key, value);
	}

	private void storeProperties(Properties prop, Writer bufferedWriter, String comment)
			throws IOException, CustomException {
		UtilMethods.isNull(bufferedWriter, ConstantMessage.INPUT_NULL_MESSAGE);
		UtilMethods.isNull(bufferedWriter, ConstantMessage.INPUT_NULL_MESSAGE);
		UtilMethods.isNull(comment, ConstantMessage.INPUT_NULL_MESSAGE);
		prop.store(bufferedWriter, comment);
	}

	private Properties getPropsData(Reader bf) throws CustomException, IOException {
		UtilMethods.isNull(bf, ConstantMessage.INPUT_NULL_MESSAGE);
		Properties prop = getNewProperties();
		prop.load(bf);
		return prop;
	}

}
