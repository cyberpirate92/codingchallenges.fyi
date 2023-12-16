public class StringUtils {
    public static long countWords(String text) {
        boolean inWord = false;
        long wordCount = 0;
        final char[] delimiters = new char[] { ' ', '\r', '\n' };

        for (int i=0; i<text.length(); i++) {
            if (text.charAt(i) == delimiters[0] || text.charAt(i) == delimiters[1] || text.charAt(i) == delimiters[2]) {
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
