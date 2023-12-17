import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SimpleStreamProcessor implements StreamProcessor {
    @Override
    public ProcessResult processStream(InputStream stream, ProcessingOptions options) throws IOException {
        long lineCount = 0, wordCount = 0, characterCount = 0, byteCount = 0;
        int currentByte;
        boolean inWord = false;
        var byteBuffer = new ByteArrayOutputStream();

        while ((currentByte = stream.read()) != -1) {
            boolean isWhitespaceChar = StringUtils.WhitespaceCharacters.contains(currentByte);
            if (isWhitespaceChar) {
                if (inWord) {
                    wordCount += 1;
                }
                inWord = false;
            }

            byteBuffer.write(currentByte);
            if (currentByte == 10) {
                characterCount += StringUtils.getUTF8CharacterCount(byteBuffer.toByteArray());
                lineCount += 1;
                byteBuffer.reset();
            } else if (!isWhitespaceChar) {
                inWord = true;
            }
            byteCount += 1;
        }

        if (inWord) {
            wordCount += 1;
        }
        characterCount += StringUtils.getUTF8CharacterCount(byteBuffer.toByteArray());
        byteBuffer.close();

        return new ProcessResult(lineCount, wordCount, byteCount, characterCount);
    }
}
