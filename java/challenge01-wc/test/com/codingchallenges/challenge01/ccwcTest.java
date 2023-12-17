package com.codingchallenges.challenge01;

import com.codingchallenges.challenge01.models.ProcessingOptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ccwcTest {
    private static Stream<Arguments> provideArgumentsForGenerateOptionsFromFlags() {
        return Stream.of(
                Arguments.of(
                        Map.ofEntries(
                            Map.entry('l', 1),
                            Map.entry('w', 2),
                            Map.entry('c', 3)
                        ), new ProcessingOptions(true, true, true, false)
                ),
                Arguments.of(
                        Map.ofEntries(
                                Map.entry('l', 1),
                                Map.entry('w', 2),
                                Map.entry('m', 3)
                        ), new ProcessingOptions(true, true, false, true)
                ),
                Arguments.of(
                        Map.ofEntries(
                                Map.entry('w', 1)
                        ), new ProcessingOptions(false, true, false, false)
                ),
                Arguments.of(
                        Map.ofEntries(
                                Map.entry('c', 1)
                        ), new ProcessingOptions(false, false, true, false)
                ),
                Arguments.of(
                        Map.ofEntries(
                                Map.entry('m', 1)
                        ), new ProcessingOptions(false, false, false, true)
                ),
                Arguments.of(
                        Map.ofEntries(
                                Map.entry('c', 1),
                                Map.entry('m', 2)
                        ), new ProcessingOptions(false, false, false, true)
                ),
                Arguments.of(
                        Map.ofEntries(
                                Map.entry('m', 1),
                                Map.entry('c', 2)
                        ), new ProcessingOptions(false, false, true, false)
                ),
                Arguments.of(
                        Map.of(), new ProcessingOptions(true, true, true, false)
                )
        );
    }

    @MethodSource("provideArgumentsForGenerateOptionsFromFlags")
    @ParameterizedTest()
    void generateOptionsFromFlags(Map<Character, Integer> flags, ProcessingOptions expectedOptions) {
        var actualOptions = ccwc.generateOptionsFromFlags(flags);
        assertEquals(expectedOptions.showLineCount(), actualOptions.showLineCount());
        assertEquals(expectedOptions.showWordCount(), actualOptions.showWordCount());
        assertEquals(expectedOptions.showByteCount(), actualOptions.showByteCount());
        assertEquals(expectedOptions.showCharacterCount(), actualOptions.showCharacterCount());
    }
}