import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SimpleStreamProcessor implements StreamProcessor {

    private static final Set<Integer> WhitespaceCharacters = new HashSet<Integer>(List.of(9, 10, 11, 12, 13, 32 ));

    @Override
    public ProcessResult processStream(InputStream stream, ProcessingOptions options) throws IOException {
        long lineCount = 0, wordCount = 0, characterCount = 0, byteCount = 0;
        int currentByte = 0;
        boolean inWord = false;
        var byteBuffer = new ByteArrayOutputStream();

        while ((currentByte = stream.read()) != -1) {
            boolean isWhitespaceChar = WhitespaceCharacters.contains(currentByte);
            if (isWhitespaceChar) {
                if (inWord) {
                    wordCount += 1;
                }
                inWord = false;
            }

            byteBuffer.write(currentByte);
            if (currentByte == 10) {
                characterCount += getCharacterCount(byteBuffer.toByteArray());
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
        characterCount += getCharacterCount(byteBuffer.toByteArray());
        byteBuffer.close();

        return new ProcessResult(lineCount, wordCount, byteCount, characterCount);
    }

    private long getCharacterCount(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8).length();
    }
}
