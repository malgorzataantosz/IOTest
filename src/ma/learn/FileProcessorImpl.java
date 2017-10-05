package ma.learn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FileProcessorImpl implements FileProcessor {

	private static final Logger log = Logger.getLogger(FileProcessorImpl.class.getName());

	@Override
	public List<String> readIntoLines(File inputFile) {
		List<String> ret = new ArrayList<>();
		// JDK 1.8 try with resources
		try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
			String line = reader.readLine();
			while (line != null) {
				ret.add(line);
				line = reader.readLine();
			}
		} catch (IOException e) {
			String message = "Error occured while processing the file " + e.getLocalizedMessage();
			log.severe(message);
			e.printStackTrace();
			throw new RuntimeException(message, e);
		}
		return ret;
	}

	@Override
	public void writeLines(List<String> lines, File outputFile) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
			int lineNumber = 0;
			for (String line : lines) {
				if (lineNumber++ > 0) {
					writer.newLine();
				}
				writer.write(line);
			}
		} catch (IOException e) {
			String message = "Error occured while processing the file " + e.getLocalizedMessage();
			log.severe(message);
			e.printStackTrace();
			throw new RuntimeException(message, e);
		}
	}

	@Override
	public void copy(File inputFile, File outputFile) {
		List<String> lines = readIntoLines(inputFile);
		writeLines(lines, outputFile);
	}

}
