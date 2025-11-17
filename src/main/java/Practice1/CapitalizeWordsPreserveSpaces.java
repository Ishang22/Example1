package Practice1;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Description:<br>
 * Date: 28/06/25-3:49 pm
 *
 * @author ishangarg
 * @since
 */
public class CapitalizeWordsPreserveSpaces {
    public static String capitalizeWords(String input) {
        if (input == null || input.isEmpty()) return input;

        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = true;

        for (char ch : input.toCharArray()) {
            if (Character.isWhitespace(ch)) {
                result.append(ch);
                capitalizeNext = true; // next non-space starts a new word
            } else if (capitalizeNext) {
                result.append(Character.toUpperCase(ch));
                capitalizeNext = false;
            } else {
                result.append(ch);
            }

        }

        return new String(result);
    }

    public static void main(String[] args) {
        String input = "   ishan garg      is great       ";
        String output = capitalizeWords(input);
        System.out.println("[" + output + "]");

        Stream.of(1,2).parallel().collect(Collectors.toList());
       // Stream.of(1,2,3,4,5).flatMap()
    }
}
