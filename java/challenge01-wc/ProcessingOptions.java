public record ProcessingOptions(boolean lineCount, boolean wordCount, boolean byteCount, boolean characterCount) {
    public ProcessingOptions() {
        this(true, true, true, false);
    }
}
