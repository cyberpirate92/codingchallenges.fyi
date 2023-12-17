import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringUtils {
    /**
     * A set of integer values representing the ASCII values of characters considered as whitespace by wc.
     * These values are referenced from: <a href="https://en.cppreference.com/w/cpp/string/wide/iswspace">...</a>
     * The values include:
     * 9  - Horizontal Tab
     * 10 - Line Feed
     * 11 - Vertical Tab
     * 12 - Form Feed
     * 13 - Carriage Return
     * 32 - Space
     */
    public static final Set<Integer> WhitespaceCharacters = new HashSet<>(List.of(9, 10, 11, 12, 13, 32 ));

    /**
     * This method calculates and returns the number of characters in a byte array when interpreted as a UTF-8 string.
     *
     * @param bytes The byte array to be converted to a UTF-8 string and counted.
     * @return The number of characters in the UTF-8 string representation of the input byte array.
     */
    public static long getUTF8CharacterCount(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8).length();
    }
}
