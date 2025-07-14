package Practice3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Description:<br>
 * Date: 05/05/25-3:49 pm
 *
 * @author ishangarg
 * @since
 */
public class Example1 {
    public static void main(String args[])
    {
        String name = "ishan garg";
        Map<Character,Integer> m = new HashMap<>();
        for(char c : name.toCharArray())
        {
            m.put(c,m.getOrDefault(c,0)+1);
        }
        //  .collect(Collectors.toSet());
       // Map<String,List<Employee>> myList=arr.stream().collect(Collectors.groupingBy(i->i.getName(),Collectors.toList()));
    }
}
