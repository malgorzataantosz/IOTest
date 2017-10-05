package ma.learn;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class StreamProcessorImplTest {

	private StreamProcessorImpl classUnderTest;

	@Before
	public void setUp() throws Exception {
		classUnderTest = new StreamProcessorImpl();
	}

	@Test
	public void testReadIntoLines() {
		String filename = "lorem.txt";
		String filesDir = "files";
		String inputPath = filesDir + File.separator + filename;

		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(inputPath);
		List<String> lines = classUnderTest.readIntoLines(inputStream);
		assertNotNull(lines);
		assertEquals(13, lines.size());
	}

	private static final String[] LINES = { "LINE 1", "LINE 2", "LINE 3", "LINE 4", "LINE 5", };

	@Test
	public void testWriteLines() throws Exception {
		List<String> lines = Arrays.asList(LINES);
		String fileName = "lines_Stream.txt";
		String filesDir = "files";
		String inputPath = filesDir + File.separator + fileName;
		OutputStream outputStream = new FileOutputStream(inputPath);
		classUnderTest.writeLines(lines, outputStream);

	}

	@Test
	public void testCopy() throws Exception {
		String inputFilename = "lorem.txt";
		String outputFilename = "lorem_copyStream.txt";
		String filesDir = "files";
		String inputPath = filesDir + File.separator + inputFilename;
		String outputPath = filesDir + File.separator + outputFilename;
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(inputPath);
		OutputStream outputStream = new FileOutputStream(outputPath);
		classUnderTest.copy(inputStream, outputStream);
		//verify the copy
		InputStream inputStreamOrigin = Thread.currentThread().getContextClassLoader().getResourceAsStream(inputPath);
		InputStream inputStreamCopy = Thread.currentThread().getContextClassLoader().getResourceAsStream(outputPath);
		List<String> linesOrigin = classUnderTest.readIntoLines(inputStreamOrigin);
		List<String> linesCopy = classUnderTest.readIntoLines(inputStreamCopy);
		assertEquals(inputStreamOrigin, inputStreamCopy);

	}

}
