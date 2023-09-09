import java.io.IOException;
import java.util.*;

//haunted house

public class Example6 {

    static class Person {
        int min;
        int max;
    }


    public static void main(String[] args) throws IOException {
        int N = 6;
        Person[] P = new Person[N];
        int[][] arr = {{1, 2}, {1, 4}, {0, 3}, {0, 1}, {3, 4}, {0, 2}};
        for (int n = 0; n < N; n++) {
            Person p = new Person();
            p.min = arr[n][0] + 1;
            p.max = arr[n][1] + 1;
            P[n] = p;
        }

        Arrays.sort(P, (Person o1, Person o2) -> o1.min - o2.min);

        // min Queue
        Queue<Person> queue = new PriorityQueue<Person>(N, (Person o1, Person o2) -> o1.max - o2.max);

        int answer = 0;
        int pIndex = 0;
        int max = 0;

        while (answer < N) {

            answer++;
            //{1,4} {1,2},{1,3},{2,3},{2,5},{4,5}

            while (pIndex < N && P[pIndex].min <= answer) {
                queue.add(P[pIndex]);
                pIndex++;
            }

            while (!queue.isEmpty()) {
                Person p = queue.peek();
                if (p.max >= answer) {
                    break;
                } else {
                    queue.poll();
                }
            }

            if (queue.size() >= answer) {
                max = answer;
            }
        }
        System.out.println(max);
    }


}
