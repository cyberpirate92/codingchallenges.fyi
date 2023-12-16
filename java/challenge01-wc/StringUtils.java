import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringUtils {
    private static final Set<Integer> WHITESPACES = new HashSet<Integer>(List.of(9, 10, 11, 12, 13, 32 ));

    public static long countWords(String text) {
        boolean inWord = false;
        long wordCount = 0;

        for (int i=0; i<text.length(); i++) {
            if (WHITESPACES.contains((int) text.charAt(i))) {
                if (inWord) {
                    wordCount += 1;
                }
                inWord = false;
            } else {
                inWord = true;
            }
        }

        return wordCount;
    }
}
