package com.codingchallenges.challenge01.streamprocessors.models;

public record StreamProcessingResult(long lineCount, long wordCount, long byteCount, long characterCount, String filename) {
    public StreamProcessingResult() {
        this(0L, 0L, 0L, 0L, "");
    }

    public StreamProcessingResult(StreamProcessingResult a, StreamProcessingResult b, String filename) {
        this(a.lineCount + b.lineCount, a.wordCount + b.wordCount, a.byteCount + b.byteCount, a.characterCount + b.characterCount, filename == null ? "" : filename);
    }
}
