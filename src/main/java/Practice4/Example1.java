package Practice4;

/**
 * Description:<br>
 * Date: 08/07/25-5:13 pm
 *
 * @author ishangarg
 * @since
 *
 * Given an array arr[] of n positive integers. The task is to find the sum of the maximum sum subsequence of the given array such that the integers in the subsequence are sorted in strictly increasing order.
 * Examples:
 * Input: arr[] = [1, 101, 2, 3, 100]
 * Output: 106
 * Explanation: The maximum sum of a increasing sequence is obtained from [1, 2, 3, 100].
 * Input: arr[] = [4, 1, 2, 3]
 * Output: 6
 * Explanation: The maximum sum of a increasing sequence is obtained from [1, 2, 3].
 *
 */
public class Example1 {

    public static void main(String args[])
    {
        int arr[]={10};
        int sum =arr[0];
        int lastSelectedElement=arr[0];
        int maxSum=0;

        for(int i=0;i<arr.length;i++)
        {
            if(i!=0 && (lastSelectedElement<arr[i]))
            {
                lastSelectedElement=arr[i];
                //3
                sum=sum+arr[i];
                //6
            }

            if(lastSelectedElement>arr[i])
            {
                sum=sum-lastSelectedElement;
                //0
                lastSelectedElement=arr[i];
                //1
                sum=sum+arr[i];
                //1
            }

            if(sum>maxSum)
            {
                maxSum=sum;
                //6
            }

        }


        System.out.println(maxSum);

        /*
        inventory -
        {
        _id :1_1
        name:
        despriton:
        }


        properties_table
        _id :2
        inventory_ID:1_1
        type: size
        value : s

        _id :3
        inventory_ID:1_1
        type: size
        value : m

        inventory_ID:1_1
        type: size
        value : l


          _id :4
        inventory_ID:1_1
        type: color
        value : black
        _id :5
        inventory_ID:1_1
       type: color
        value : black
          _id :5
        inventory_ID:1_1
        type: color
        value : brown


           _id :6
        inventory_ID:1_1
        type: memory
        value : 64


            _id :6
        inventory_ID:1_1
        type: memory
        value : 128

         */


    }
}
