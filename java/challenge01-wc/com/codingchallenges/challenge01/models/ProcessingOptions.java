package com.codingchallenges.challenge01.models;

public class ProcessingOptions {
    private final boolean lineCount;
    private final boolean wordCount;
    private final boolean byteCount;
    private final boolean characterCount;

    public ProcessingOptions() {
        this(true, true, true, false);
    }

    public ProcessingOptions(boolean lineCount, boolean wordCount, boolean byteCount, boolean characterCount) {
        this.lineCount = lineCount;
        this.wordCount = wordCount;
        this.byteCount = byteCount;
        this.characterCount = characterCount;
    }

    public boolean showLineCount() {
        return lineCount;
    }

    public boolean showWordCount() {
        return wordCount;
    }

    public boolean showByteCount() {
        return byteCount;
    }

    public boolean showCharacterCount() {
        return characterCount;
    }
}
