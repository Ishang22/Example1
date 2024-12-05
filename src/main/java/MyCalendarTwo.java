import java.util.TreeMap;

/**
 * Description:<br>
 * Date: 19/10/24-8:10 pm
 *
 * @author ishangarg
 * @since
 */
class MyCalendarTwo {
    TreeMap<Integer, Integer> map;
    int numMeetingsAllowed;

    public MyCalendarTwo() {
        map = new TreeMap<>();
        numMeetingsAllowed = 2;
    }

    // [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]
    // 5:    0
    // 10:   2
    // 15:   0
    // 20:  -1
    // 40:  -1
    // 50:   1
    // 60:  -1
    public boolean book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);

        int numBookings = 0;

        for (Integer key : map.keySet()) {
            numBookings += map.get(key);

            if (numBookings > numMeetingsAllowed) {
                map.put(start, map.get(start) - 1);
                map.put(end, map.get(end) + 1);

                return false;
            }
        }

        // T: O(n.logn)
        // S: O(n)

        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */
