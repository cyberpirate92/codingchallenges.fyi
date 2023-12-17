package com.codingchallenges.challenge01.streamprocessors;

import com.codingchallenges.challenge01.streamprocessors.models.StreamProcessingResult;
import com.codingchallenges.challenge01.models.ProcessingOptions;
import com.codingchallenges.challenge01.utils.StringUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BufferedByteStreamProcessor implements StreamProcessor {

    @Override
    public StreamProcessingResult processStream(InputStream stream, ProcessingOptions options) throws IOException {
        final int BUFFER_SIZE = 16384;
        var bufferedInputStream = new BufferedInputStream(stream, BUFFER_SIZE);

        long lineCount = 0, wordCount = 0, characterCount = 0, byteCount = 0;
        int currentByte;
        boolean inWord = false;

        while ((currentByte = bufferedInputStream.read()) != -1) {
            if (options.characterCount()) {
                int characterByteCount = getByteCount(currentByte);
                if (characterByteCount > 1) {
                    characterCount += 1;
                    byteCount += characterByteCount;

                    // skip bytes as they belong to the same multibyte character
                    while (characterByteCount-- > 1) {
                        // TODO: Handle cases where stream ends abruptly due to malformed multibyte characters
                        bufferedInputStream.read();
                    }

                    // since all the bytes belong to the same character,
                    // there's no need for further processing
                    continue;
                } else {
                    characterCount++;
                }
            }

            boolean isWhitespaceChar = StringUtils.WhitespaceCharacters.contains(currentByte);
            if (isWhitespaceChar) {
                if (inWord) {
                    wordCount += 1;
                }
                inWord = false;
            }

            if (currentByte == 10) {
                lineCount += 1;
            } else if (!isWhitespaceChar) {
                inWord = true;
            }

            byteCount += 1;
        }

        if (inWord) {
            wordCount += 1;
        }

        return new StreamProcessingResult(lineCount, wordCount, byteCount, characterCount);
    }

    /**
     * Determine the byte count given the first byte of a possibly multibyte character
     * Unicode characters can range anywhere from 1-4 bytes.
     * The first byte of a multibyte character can be used to determine the number of bytes that form the character.
     * If the binary representation of the first byte
     *      - starts with 1111 xxxx (240 - 247), then it's a 4 byte character
     *      - starts with 1110 xxxx (224 - 239), then it's a 3 byte character
     *      - starts with 1100 xxxx (192 - 223), then it's a 2 byte character
     * @param byteValue The unsigned byte value in the range (0-255)
     * @return number of bytes
     */
    private static int getByteCount(int byteValue) {
        if (byteValue >= 0 && byteValue <= 127) {
            return 1;
        } else if (byteValue >= 192 && byteValue <= 223) {
            return 2;
        } else if (byteValue >= 224 && byteValue <= 239) {
            return 3;
        } else if (byteValue >= 240 && byteValue <= 247) {
            return 4;
        } else {
            // Illegal byte sequence? Ignoring this to allow processing malformed characters
            return 1;
        }
    }
}
