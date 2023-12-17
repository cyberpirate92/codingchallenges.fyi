package com.codingchallenges.challenge01.streamprocessors;

import com.codingchallenges.challenge01.streamprocessors.models.StreamProcessingResult;
import com.codingchallenges.challenge01.models.ProcessingOptions;

import java.io.IOException;
import java.io.InputStream;

public interface StreamProcessor {
    StreamProcessingResult processStream(InputStream stream, ProcessingOptions options) throws IOException;
}
