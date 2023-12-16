import java.io.IOException;
import java.io.InputStream;

public class SimpleStreamProcessor implements StreamProcessor {
    @Override
    public ProcessResult processStream(InputStream stream, ProcessingOptions options) throws IOException {
        int lineCount = 0, wordCount = 0, characterCount = 0, byteCount = 0;
        return new ProcessResult(lineCount, wordCount, byteCount, characterCount);
    }
}
