package Practice1;

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

        return result.toString();
    }

    public static void main(String[] args) {
        String input = "   ishan garg      is great       ";
        String output = capitalizeWords(input);
        System.out.println("[" + output + "]");
    }
}
/*
✅ Most Asked Java Streams Interview Questions
🔹 1. What is the difference between map() and flatMap()?
✅ Answer:

map() transforms each element independently.

flatMap() is used when each element is itself a stream (e.g., List<List<String>> → List<String>).

📌 Example:
listOfLists.stream().flatMap(s->s.stream).collect(Collectors.toList());
🔹 2. How do you remove duplicates from a list using streams?
✅ Answer:

List<String> unique = list.stream().distinct().collect(Collectors.toList());
🔹 3. How do you sort a list using streams?
✅ Answer:

list.stream().sorted().collect(Collectors.toList());

🔹 4. Difference between filter(), map(), reduce()?
Method	Purpose
filter	Filters elements using a condition
map	Transforms each element
reduce	Combines elements into a single result

🔹 5. How do you count the number of elements that match a condition?
✅ Answer:

long count = list.stream().filter(s -> s.startsWith("A")).count();
🔹 6. How do you convert a list to a map using streams?
✅ Answer:

Map<Integer, String> map = list.stream()
    .collect(Collectors.toMap(Employee::getId, Employee::getName));
🔹 7. How to find the first non-repeated character in a string using streams?
✅ Answer:

Character result = str.chars()
    .mapToObj(c -> (char) c)
    .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
    .entrySet().stream()
    .filter(e -> e.getValue() == 1)
    .map(Map.Entry::getKey)
    .findFirst()
    .orElse(null);
🔹 8. What is the difference between collect() and reduce()?
✅ Answer:

reduce() is a terminal operation used to produce a single result (sum, concat, etc.).

collect() is more powerful — can accumulate into List, Set, Map, etc.

🔹 9. How do you parallelize stream operations?
✅ Answer:
list.parallelStream().map(...).collect(...);
⚠️ Be careful — not always faster. Only helpful for CPU-bound, stateless, non-order-sensitive tasks.

🔹 10. How do you group data using streams? (like SQL GROUP BY)
✅ Answer:

Map<String, List<Employee>> grouped = list.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment));
 */