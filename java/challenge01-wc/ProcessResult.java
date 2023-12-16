public record ProcessResult(long lineCount, long wordCount, long byteCount, long characterCount) {
    public ProcessResult() {
        this(0L, 0L, 0L, 0L);
    }

    public ProcessResult(ProcessResult a, ProcessResult b) {
        this(a.lineCount + b.lineCount, a.wordCount + b.wordCount, a.byteCount + b.byteCount, a.characterCount + b.characterCount);
    }
}
