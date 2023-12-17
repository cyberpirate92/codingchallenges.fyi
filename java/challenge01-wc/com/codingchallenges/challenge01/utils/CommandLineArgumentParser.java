package com.codingchallenges.challenge01.utils;

import com.codingchallenges.challenge01.models.ParseResult;

import java.util.*;

public class CommandLineArgumentParser {
    private static final char FlagPrefix = '-';

    public static ParseResult parseArguments(String[] arguments) {
        return parseArguments(arguments, new char[0]);
    }

    public static ParseResult parseArguments(String[] arguments, char[] validFlags) {
        var validFlagSet = new HashSet<Character>();
        for (var flag : validFlags) {
            validFlagSet.add(flag);
        }

        var otherArgs = new ArrayList<String>();
        Map<Character, Integer> providedFlags = new HashMap<>();

        int pos = 1;
        for (var argument : arguments) {
            if (argument.charAt(0) == FlagPrefix) {
                for (int i = 1; i < argument.length(); i++) {
                    providedFlags.put(argument.charAt(i), pos++);
                }
            } else {
                otherArgs.add(argument);
            }
        }

        providedFlags.keySet().retainAll(validFlagSet);
        return new ParseResult(providedFlags, otherArgs);
    }

}
