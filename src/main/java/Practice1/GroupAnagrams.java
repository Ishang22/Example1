package Practice1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:<br>
 * Date: 28/08/25-10:35 pm
 *
 * @author ishangarg
 * @since
 */
class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();

        String[] strs = {"eat","tea","tan","ate","nat","bat"};

        List<List<String>> result = sol.groupAnagrams(strs);

        // Print result
        for (List<String> group : result) {
            System.out.println(group);
        }
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        for (String str : strs) {
            String s = sort(str);

            if (map.containsKey(s)) {
                list.get(map.get(s)).add(str);
            }
            else {
                List<String> l = new ArrayList<>();
                l.add(str);
                list.add(l);
                map.put(s, list.size() - 1);
            }

        }


        return list;
    }

    private String sort(String str) {
        char[] c = str.toCharArray();
        Arrays.sort(c);
        return new String(c);
    }
}
