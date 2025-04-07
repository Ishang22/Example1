package Practice1;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of Sliding window algorithm with timestamp and counter
 * (example: redis hash)
 */
public class RateLimit {

    int rateLimit;

    Map<String, LinkedList<Request>> userRequestMap = new ConcurrentHashMap<>();

    public RateLimit(int limit) {
        this.rateLimit = limit;
    }

    /**
     * Thread safe block being invoked by multiple threads
     *
     * @param user      username
     * @param timestamp timestamp of request
     * @return request allowed true/false
     */
    public synchronized boolean hit(String user, Instant timestamp) {

        if (!userRequestMap.containsKey(user)) {
            return addNewUser(user);
        } else {

            if (getTotalElpasedRequests(user) < rateLimit) {
                LinkedList<Request> requests = userRequestMap.get(user);
                requests.add(new Request(timestamp, 1));
                userRequestMap.put(user, requests);
                return true;
            } else {

                boolean actionTaken = false;

                for (int i = 0; i < userRequestMap.get(user).size(); i++) {
                    Duration duration = Duration.between(userRequestMap.get(user).get(i).getTimestamp(), timestamp);
                    // check for elapsed time greater than 1 minute (60 seconds)
                    // This can be passed as an argument at runtime to avoid hardcoding
                    if (duration.getSeconds() >= 60) {
                        userRequestMap.get(user).remove(i);
                        actionTaken = true;
                    } else {
                        break;
                    }
                }

                if (actionTaken) {
                    LinkedList<Request> requests = userRequestMap.get(user);
                    requests.add(new Request(timestamp, 1));
                    userRequestMap.put(user, requests);
                    return true;
                }

                return false;
            }

        }
    }

    public boolean addNewUser(String user) {
        LinkedList<Request> requests = new LinkedList<>();
        requests.add(new Request(Instant.now(), 1));
        userRequestMap.put(user, requests);
        System.out.println("New User added !! " + user);
        return true;
    }

    public Integer getTotalElpasedRequests(String user) {
        return userRequestMap.get(user).stream().mapToInt(Request::getCount).sum();
    }

}
/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++

/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++

//        Consumer →  Used in forEach.
//        Predicate → Used in filter .
//        Function →  Used in map    .

//Consumer in forEach:
//Yes, a Consumer is typically used in forEach. A Consumer is a functional interface representing an operation that accepts a single input argument and returns no result. It is often used in forEach to perform some action on each element of a collection or stream.
//
//java
//Copy code
//List<String> list = List.of("apple", "banana", "cherry");
//list.forEach(s -> System.out.println(s));  // `s -> System.out.println(s)` is a Consumer


//Predicate in filter:
//Correct. A Predicate is a functional interface that represents a condition (a boolean-valued function) applied to an input. It is used in filter to test elements and allow only those that satisfy the condition.
//
//java
//Copy code
//List<Integer> numbers = List.of(1, 2, 3, 4, 5);
//List<Integer> evens = numbers.stream()
//        .filter(n -> n % 2 == 0)  // `n -> n % 2 == 0` is a Predicate
//        .toList();


//Function in map:
//Not "functional," but rather a Function. A Function is a functional interface that takes one argument and returns a result. It is used in map to transform each element of a stream into another form.
//List<String> names = List.of("John", "Jane", "Jake");
//List<Integer> nameLengths = names.stream()
//        .map(name -> name.length())  // `name -> name.length()` is a Function
//        .toList();


//List<Integer> numbers = List.of(1, 2, 3, 4, 5);
// Using reduce with a BinaryOperator
//int sum = numbers.stream()
//        .reduce(0, (a, b) -> a + b); // `a + b` is a BinaryOperator
//        System.out.println("Sum: " + sum);


// List<String> myList = arr.stream().map(i->{return i.name;}).collect(Collectors.toList());

/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++

/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++
