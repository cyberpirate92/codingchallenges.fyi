package com.codingchallenges.challenge01.models;

import java.util.Map;
import java.util.List;

public record ParseResult(Map<Character, Integer> flags, List<String> otherArguments) {
    int argumentCount() {
        return flags.size() + otherArguments.size();
    }
}
