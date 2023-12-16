import java.util.Set;
import java.util.List;

public record ParseResult(Set<Character> flags, List<String> otherArguments) {
    int argumentCount() {
        return flags.size() + otherArguments.size();
    }
}
