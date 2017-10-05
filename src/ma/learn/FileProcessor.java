package ma.learn;

import java.io.File;
import java.util.List;

public interface FileProcessor {
	public List<String> readIntoLines(File inputFile);
	public void writeLines(List<String> lines, File outputFile);
	public void copy(File inputFile, File outputFile);

}
