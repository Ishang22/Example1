import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public static int leastInterval(List<Character> tasks, int n) {
        Map<Character, Integer> map = new HashMap();

        for (char task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }

        // max heap to store the remaining tasks
        Queue<Integer> maxHeap = new PriorityQueue(Collections.reverseOrder());

        maxHeap.addAll(map.values());


        int result = 0;
        //   1  0
        while (maxHeap.size() > 0) {

            int time = 0;

            List<Integer> tmp = new ArrayList<>();

            for (int i = 0; i < n + 1; i++) {
                if (!maxHeap.isEmpty()) {
                    tmp.add(maxHeap.remove() - 1);
                    time++;
                }
            }

            for (int t : tmp) {
                if (t > 0) maxHeap.add(t);
            }

            result += maxHeap.isEmpty() ? time : n + 1;
        }


        return result;

    }

    public static void main(String[] args) {


        String multilineString = "Baeldung helps \n \n developers \n explore Java.";

        List<String> lines =
                 multilineString
                .lines()
                .filter(line -> !line.isBlank())
                .map(String::strip)
                .collect(Collectors.toList());

        //    assertThat(lines).containsExactly("Baeldung helps", "developers", "explore Java.");

        List<Character> strings = List.of('A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G');
        System.out.println("================" + leastInterval(strings, 2));


        ArrayList names = new ArrayList();
        names.add("Alice");
        names.add("Bob");
        ArrayList list = new ArrayList();
        list.add("A");
        list.add("B");
        names.addAll(list);

        System.out.println(names);

    }
}