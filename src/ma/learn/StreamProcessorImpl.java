package ma.learn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class StreamProcessorImpl implements StreamProcessor {

	private static final Logger log = Logger.getLogger(StreamProcessorImpl.class.getName());

	@Override
	public List<String> readIntoLines(InputStream inputStream) {
		List<String> ret = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			String line = reader.readLine();
			while (line != null) {
				ret.add(line);
				line = reader.readLine();
			}
		} catch (IOException e) {
			String message = "Error occured while processing the InputStream " + e.getLocalizedMessage();
			log.severe(message);
			e.printStackTrace();
			throw new RuntimeException(message, e);
		}
		return ret;
	}

	@Override
	public void writeLines(List<String> lines, OutputStream outputStream) {
		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {
			int lineNumber = 0;
			for (String line : lines) {
				if (lineNumber++ > 0) {
					writer.newLine();
				}
				writer.write(line);
			}
		} catch (IOException e) {
			String message = "Error occured while processing the OutputStream " + e.getLocalizedMessage();
			log.severe(message);
			e.printStackTrace();
			throw new RuntimeException(message, e);
		}
	}

	@Override
	public void copy(InputStream inputStream, OutputStream outputStream) {
		List<String> inputLines = readIntoLines(inputStream);
		writeLines(inputLines, outputStream);

	}

}
