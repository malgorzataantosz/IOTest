package ma.learn;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FileProcessorImplTest {

	private FileProcessorImpl classUnderTest;

	@Before
	public void setUp() throws Exception {
		classUnderTest = new FileProcessorImpl();
	}

	@Test
	public void testReadIntoLines() {
		String filename = "lorem.txt";
		String filesDir = "files";
		String inputPath = filesDir + File.separator + filename;
		File inputFile = new File(inputPath);
		List<String> lines = classUnderTest.readIntoLines(inputFile);
		assertNotNull(lines);
		assertEquals(13, lines.size());
	}

	private static final String[] LINES = { "LINE 2", "LINE 3", "LINE 4", "LINE 5", };

	@Test
	public void testWriteLines() {
		List<String> lines = Arrays.asList(LINES);
		String fileName = "lines_File.txt";
		String filesDir = "files";
		String outputPath = filesDir + File.separator + fileName;
		File outputFile = new File(outputPath);
		if (outputFile.exists()) {
			outputFile.delete();
		}
		classUnderTest.writeLines(lines, outputFile);
	}

	@Test
	public void testCopy() {
		String inputFilename = "lorem.txt";
		String outputFilename = "lorem_copyFile.txt";
		String filesDir = "files";
		String inputPath = filesDir + File.separator + inputFilename;
		String outputPath = filesDir + File.separator + outputFilename;
		File inputFile = new File(inputPath);
		File outputFile = new File(outputPath);
		classUnderTest.copy(inputFile, outputFile);
		//verify the copy
		List<String> linesOrigin = classUnderTest.readIntoLines(inputFile);
		List<String> linesCopy = classUnderTest.readIntoLines(outputFile);
		assertEquals(linesOrigin, linesCopy);


	}
}
