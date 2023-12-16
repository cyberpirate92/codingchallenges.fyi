import java.io.*;
import java.nio.charset.StandardCharsets;

public class BufferedStreamProcessor implements StreamProcessor {
    @Override
    public ProcessResult processStream(InputStream stream, ProcessingOptions options) throws IOException {
        long lineCount = 0, wordCount = 0, byteCount = 0, characterCount = 0;

        var streamReader = new BufferedReader(new InputStreamReader(stream));
        String line;

        final String newLine = System.getProperty("line.separator");

        while ((line = streamReader.readLine()) != null) {
            lineCount += 1;
            wordCount += StringUtils.countWords(line);
            characterCount += line.length();
            byteCount += line.getBytes(StandardCharsets.UTF_8).length + newLine.length();
        }

        return new ProcessResult(lineCount, wordCount, byteCount, characterCount);
    }
}
