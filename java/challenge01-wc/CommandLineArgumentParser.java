import java.util.ArrayList;
import java.util.HashSet;

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
        var providedFlags = new HashSet<Character>();

        for (var argument : arguments) {
            if (argument.charAt(0) == FlagPrefix) {
                for (int i = 1; i < argument.length(); i++) {
                    providedFlags.add(argument.charAt(i));
                }
            } else {
                otherArgs.add(argument);
            }
        }

        providedFlags.retainAll(validFlagSet);

        return new ParseResult(providedFlags, otherArgs);
    }

}
