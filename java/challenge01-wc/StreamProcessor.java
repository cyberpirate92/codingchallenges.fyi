import java.io.IOException;
import java.io.InputStream;

public interface StreamProcessor {
    ProcessResult processStream(InputStream stream, ProcessingOptions options) throws IOException;
}
