package Practice4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Description:<br>
 * Date: 01/09/25-3:07 pm
 *
 * @author ishangarg
 * @since
 */
public class Example3456 {

    public static void main(String args[])
    {

                List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50, 50);

                Integer secondLargest = numbers.stream()
                        .distinct()                        // remove duplicates
                        .sorted(Comparator.reverseOrder()) // sort descending
                        .skip(1)                           // skip largest
                        .findFirst()                       // get next
                        .orElseThrow(() -> new NoSuchElementException("No second largest"));

                System.out.println("Second largest: " + secondLargest);

        char arr[][]={
                      {'.', '.', '*', '.'},
                      {'.', '.', '.', '*'},
                      {'.', '.', '.', '.'},
                      {'*', '.', '.', '.'}
        };


        int arr1[][]={
                {0, 0,1, 0},
                {0, 0, 0, 1},
                {0, 0, 0, 0},
                {1, 0, 0, 0}
        };

        // x= 1
        // y=1

        int arr12[][]={
                {1, 1,1, 1},
                {1, 1, 1, 1},
                {1, 0, 1, 1},
                {1,1, 1, 1}
        };

        /*
        initilly x and y are zero

        for(in upperer row[0])
        if anyelement is 1 i will mark x= 1


         for(in  col[0])
        if anyelement is 1 i will mark y= 1

         nested loop starts from 1,1 and mark its 0 element as 1

         i will iterate over row rthen entrie coloun as 1

          i will iterate over 0 col rthen entrie row as 1


          if x=1 i will make row one as 1

          if y=1 i will make col one as 1




         */

    }

}
