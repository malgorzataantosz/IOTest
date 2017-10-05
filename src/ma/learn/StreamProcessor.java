package ma.learn;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface StreamProcessor {
	public List<String> readIntoLines(InputStream inputStream);
	public void writeLines(List<String> lines, OutputStream outputStream);
	public void copy(InputStream inputStream, OutputStream outputStream);

}
